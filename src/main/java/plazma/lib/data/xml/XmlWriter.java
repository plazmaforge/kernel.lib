/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.lib.data.xml;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import plazma.lib.data.node.Node;
import plazma.lib.io.IOLib;
import plazma.lib.str.StrLib;
import plazma.lib.sys.SysLib;

import static plazma.lib.data.xml.XmlWriterConfig.*;

/**
 * 
 * XML Writer
 * 
 * @author ohapon
 *
 */
public class XmlWriter {

    // text

    public String writeXmlToText(Node root) {
        return writeXmlToText(null, root);
    }

    public String writeXmlToText(XmlWriterConfig config, Node root) {
        if (root == null) {
            return "";
        }
        XmlAppendable buf = new XmlStringAppendable();
        writeXmlChildren(config, buf, root, -1);

        return buf.toString();
    }

    // file

    public void writeXmlToFile(String fileName, Node root) throws IOException {
        writeXmlToFile(fileName, null, root);
    }

    public void writeXmlToFile(String fileName, XmlWriterConfig config, Node root) throws IOException {
        String text = writeXmlToText(config, root);
        IOLib.writeText(fileName, text);
    }

    //

    public void writeXmlToFile(File file, Node root) throws IOException {
        writeXmlToFile(file, null, root);
    }

    public void writeXmlToFile(File file, XmlWriterConfig config, Node root) throws IOException {
        String text = writeXmlToText(config, root);
        IOLib.writeText(file, text);
    }
    
    //
    
    public void writeXmlToConsole(Node root) {
        writeXmlToConsole(null, root);
        
    }
    
    public void writeXmlToConsole(XmlWriterConfig config, Node root) {
        if (root == null) {
            return;
        }
        
        boolean flush = SysLib.isSupportEscapeCode(); // TODO: optional
        
        XmlAppendable buf = null;        
        if (flush) {
            buf = new XmlStringAppendable();            
        } else {
            buf = new XmlConsoleAppendable();
        }
                
        if (config != null) {
            buf.setColorized(config.colorized && SysLib.isStdOutEnabled());                
        }            
        
        writeXmlChildren(config, buf, root, -1);
        
        if (flush) {
            // Flush XmlStringAppendable to console
            SysLib.print(buf.toString());            
        }        
        
    }
        
    ////

    protected void writeXmlNode(XmlWriterConfig config, XmlAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }
        
        level++;
                
        int nodeType = node.getType();

        if (nodeType == XmlParser.TEXT_NODE) {

            writeXmlTextNode(config, buf, node, level);

        } else if (nodeType ==  XmlParser.COMMENT_NODE) {

            writeXmlCommentNode(config, buf, node, level);

        } else if (nodeType ==  XmlParser.CDATA_SECTION_NODE) {

            writeXmlCDATANode(config, buf, node, level);

        }  else {

            writeXmlElementNode(config, buf, node, level);

        }
        
        level--;        
    }
    
    ////
    
    protected void writeXmlTextNode(XmlWriterConfig config, XmlAppendable buf, Node node, int level) {

        // #text

        if (!node.hasText()) {
            // TODO: Maybe ignore
            //append(buf, "");
            return;
        }

        // CHECK-INLINE MODE for only one TextNode
        boolean oneTextChild = false;
        Node parentNode = node.getParent();

        if (parentNode != null && parentNode.getChildCount() == 1) {
            oneTextChild = true;
        }

        if (!oneTextChild) {
            writeLine(config, buf);
            writeLevelSpace(config, buf, level);
        }

        if (node.hasText()) {
            String text = node.getText();
            append(buf, text);
        }
    }

    protected void writeXmlCommentNode(XmlWriterConfig config, XmlAppendable buf, Node node, int level) {

        // #comment

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);

        if (!node.hasText()) {
            // TODO: Maybe ignore
            append(buf, "<!---->");
            return;
        }

        // START-TAG
        append(buf, "<!--");

        String text = node.getText();

        boolean inlineText = true; // isInlineText(config, text); // TODO: inlne by default

        if (inlineText) {
            append(buf, text);
        } else {
            writeLine(config, buf);
            writeLevelSpace(config, buf, level);
            if (!isInline(config)) {
                // Add one space for text indent
                append(buf, " ");
            }
            append(buf, text);
        }

        if (!inlineText) {
            writeLine(config, buf);
            writeLevelSpace(config, buf, level);
        }

        // END-TAG
        append(buf, "-->");
    }    

    protected void writeXmlCDATANode(XmlWriterConfig config, XmlAppendable buf, Node node, int level) {

        // #cdata-section
        
        writeLine(config, buf);
        writeLevelSpace(config, buf, level);

        if (!node.hasText()) {
            // TODO: Maybe ignore
            append(buf, "<![CDATA[]]>");
            return;
        }

        // START-TAG
        append(buf, "<![CDATA[");

        String text = node.getText();
        appendCDATA(buf, text);

        // END-TAG
        append(buf, "]]>");
    }
    
    
    protected void writeXmlElementNode(XmlWriterConfig config, XmlAppendable buf, Node node, int level) {

        String text = node.getText();
        boolean hasChildren = node.hasChildren();
        boolean hasText = node.hasText();
        boolean isSingle = isSingleNode(node);
        boolean inlineFlag = isInline(config);
        boolean inlineText = isInlineText(config, text);

        String tagName = node.getName();
        int nodeType = node.getType();

        // TODO: Separate NodeType: DOCUMENT_TYPE_NODE,
        // PROCESSING_INSTRUCTION_NODE...

        // #element

        // TRANSFORM-TAG-NAME
        if (hasTagCase(config) && !isSpecNode(nodeType)) {
            String tagCase = getTagCase(config);
            tagName = toTypeCase(tagName, tagCase);
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);

        ///////////////////////////////////////////////////////////////
        // START-TAG
        writeStartTag(config, buf, node, level, tagName, nodeType);
        ///////////////////////////////////////////////////////////////

        if (isSingle) {
            return;
        }

        if (hasText) {

            if (nodeType == XmlParser.DOCUMENT_TYPE_NODE) {
                inlineText = true;
                text = " [" + text + "]";
            }

            if (inlineText && !hasChildren) {
                append(buf, text);
            } else {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
                if (!inlineFlag) {
                    // Add one space for text indent
                    append(buf, " ");
                }
                append(buf, text);
            }
        }

        if (hasChildren) {
            writeXmlChildren(config, buf, node, level);
        }

        // CHECK-INLINE MODE for only one TextNode
        boolean oneTextChild = false;
        if (node.getChildCount() == 1) {
            Node firstNode = node.getChild(0);
            if (firstNode != null &&
                firstNode.getType() == XmlParser.TEXT_NODE) {
                oneTextChild = true;
            }
        }

        // if (!inlineText || hasChildren) {
        if (!oneTextChild && (!inlineText || hasChildren)) {
            writeLine(config, buf);
            writeLevelSpace(config, buf, level);
        }

        ///////////////////////////////////////////////////////////////
        // END-TAG
        writeEndTag(config, buf, node, level, tagName, nodeType);
        ///////////////////////////////////////////////////////////////
    }

    ////
    
    protected void writeXmlAttributes(XmlWriterConfig config, XmlAppendable buf, Node node) {
        if (node == null) {
            return;
        }

        if (!node.hasAttributes()) {
            return;
        }

        int count = node.getAttributeCount();
        //String attributeName = null;
        String attributeValue = null;
        boolean trimAttribute = isTrimAttribute(config);

        int nodeType= node.getType();
        boolean specNode = isSpecNode(nodeType);
        
        //for (int i = 0; i < count; i++) {
        
        Map<String, String> attributes = node.getAttributes();

        for (String attributeName : attributes.keySet()) {
            attributeValue = attributes.get(attributeName);

            //attributeName = node.getAttributeName(i);
            //attributeValue = node.getAttributeValue(i);

            // NORMALIZE-ATTRIBUTE-VALUE
            if (trimAttribute) {
                attributeValue = StrLib.trim(attributeValue);
            }

            if (StrLib.isEmpty(attributeValue)) {

                if (specNode) {
                    append(buf, " ");
                    appendAttributeName(buf, attributeName);
                }

                // skip
                continue;
            }

            // TRANSFORM-ATTRIBUTE-NAME
            attributeName = transformAttributeName(config, attributeName);

            // TRANSFORM-ATTRIBUTE-VALUE
            attributeValue = transformAttributeValue(config, attributeValue);
            
            append(buf, " ");
            appendAttributeName(buf, attributeName);
            append(buf, "=");
            appendAttributeValue(buf, attributeValue);
        }
    }


    protected void writeXmlChildren(XmlWriterConfig config, XmlAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }
        if (!node.hasChildren()) {
            return;
        }

        for (Node child : node.getChildren()) {
            writeXmlNode(config, buf, child, level);
        }
    }
    
    ////
    
    protected void append(XmlAppendable buf, String str) {
        buf.append(str);
    }

    protected void appendTag(XmlAppendable buf, String str) {
        buf.appendTag(str);
    }

    protected void appendTagName(XmlAppendable buf, String str) {
        buf.appendTagName(str);
    }

    protected void appendAttributeName(XmlAppendable buf, String str) {
        buf.appendAttributeName(str);
    }

    protected void appendAttributeValue(XmlAppendable buf, String str) {
        buf.appendAttributeValue(str);
    }

    protected void appendText(XmlAppendable buf, String str) {
        buf.appendText(str);
    }

    protected void appendCDATA(XmlAppendable buf, String str) {
        buf.appendCDATA(str);
    }
    
    ////
        
    protected void writeLine(XmlWriterConfig config, XmlAppendable buf) {
        if (isInline(config)) {
            // inline flag -> no write new line
            return;
        }
        buf.append("\n");
    }

    protected void writeLevelSpace(XmlWriterConfig config, XmlAppendable buf, int level) {

        if (isInline(config)) {
            // inline flag -> no write level space
            return;
        }

        String indent = getIndentValue(config);

        for (int i = 0; i < level; i++) {
            buf.append(indent);
        }
    }
    
    ///

    protected boolean isInlineText(XmlWriterConfig config, String text) {
        if (text == null || !isInlineNode(config)) {
            return false; 
        }
        return text.length() < getInlineNodeLimit(config) && StrLib.isLineText(text);
    }

    protected boolean isSingleNode(Node node) {
        if (node == null) {
            return false;
        }
        return !node.hasChildren() && !node.hasText();
    }

    ////

    protected void writeStartTag(XmlWriterConfig config, XmlAppendable buf, Node node, int level, String tagName, int nodeType) {

        boolean specNode = isSpecNode(nodeType);

        // START-TAG
        if (specNode) {
            if (nodeType == XmlParser.PROCESSING_INSTRUCTION_NODE) {
                appendTag(buf, "<?");
            } else {
                appendTag(buf, "<!");
            }
        } else {
            appendTag(buf, "<");
        }

        appendTagName(buf, tagName);

        writeXmlAttributes(config, buf, node);

        if (isSingleNode(node)) {
            // END-TAG: single tag
            if (specNode) {
                if (nodeType == XmlParser.PROCESSING_INSTRUCTION_NODE) {
                    appendTag(buf, " ?>");
                } else {
                    appendTag(buf, ">");
                }                                
            } else {
                appendTag(buf, "/>");
            }
            return;
        }

        // END-TAG
        if (nodeType != XmlParser.DOCUMENT_TYPE_NODE) {
            appendTag(buf, ">");
        }

    }
    

    protected void writeEndTag(XmlWriterConfig config, XmlAppendable buf, Node node, int level, String tagName, int nodeType) {

        boolean specNode = isSpecNode(nodeType);

        // END-TAG
        if (specNode) {
            if (nodeType == XmlParser.PROCESSING_INSTRUCTION_NODE) {
                // TODO: Set in single node only
                appendTag(buf, " ?>");
            } else {
                appendTag(buf, ">");
            }
        } else {
            appendTag(buf, "</");
            appendTagName(buf, tagName);
            appendTag(buf, ">");
        }
    }

    //// UTILS ////

    /**
     * Return true if config use inline flag
     * 
     * @param config
     * @return
     */
    protected boolean isInline(XmlWriterConfig config) {
        return config == null ? false : config.inlineFlag;
    }
    
    protected boolean isInlineNode(XmlWriterConfig config) {
        return config == null ? false : config.inlineNodeFlag;
    }

    protected int getInlineNodeLimit(XmlWriterConfig config) {
        return config == null ? INLINE_NODE_LIMIT : config.getInlineNodeLimit();
    }    

    /**
     * Return real indent (tab or 1/2 spaces) by config indent
     * 
     * @param config
     * @return
     */
    protected String getIndentValue(XmlWriterConfig config) {
        return config == null ? INDENT_DEFAULT_VALUE : config.getIndentValue();
    }

    protected String getAttributeQuoteValue(XmlWriterConfig config) {
        return config == null ? QUOTE_DEFAULT_VALUE : config.getAttributeQuoteValue();
    }
    
    protected boolean hasTagCase(XmlWriterConfig config) {
        return config == null ? false : !StrLib.isEmpty(config.tagCase);
    }

    protected String getTagCase(XmlWriterConfig config) {
        return config == null ? null : config.tagCase;
    }

    protected boolean hasAttributeCase(XmlWriterConfig config) {
        return config == null ? false : !StrLib.isEmpty(config.attributeCase);
    }

    protected String getAttributeCase(XmlWriterConfig config) {
        return config == null ? null : config.attributeCase;
    }

    protected boolean isTrimAttribute(XmlWriterConfig config) {
        return config == null ? false : config.trimAttribute;
    }

    ////

    protected boolean isSpecNode(int nodeType) {
        return (nodeType == XmlParser.PROCESSING_INSTRUCTION_NODE ||
                nodeType == XmlParser.DOCUMENT_TYPE_NODE

                // ALT: DOCTYPE
                || nodeType == XmlParser.ENTITY_DECL_NODE ||
                nodeType == XmlParser.ELEMENT_DECL_NODE ||
                nodeType == XmlParser.ATTRLIST_DECL_NODE ||
                nodeType == XmlParser.NOTATION_DECL_NODE);
    }

    ////

    protected String toTypeCase(String str, String type) {
        return StrLib.toTypeCase(str, type);
    }        
    
    protected String transformAttributeName(XmlWriterConfig config, String attributeName) {
        if (StrLib.isEmpty(attributeName)) {
            return attributeName;
        }
        String result = attributeName;
        if (hasAttributeCase(config)) {
            result = toTypeCase(result, getAttributeCase(config));
        }
        return result;
    }

    protected String transformAttributeValue(XmlWriterConfig config, String attributeValue) {
        if (StrLib.isEmpty(attributeValue)) {
            return attributeValue;
        }
        String result = attributeValue;
        boolean trimAttribute = isTrimAttribute(config);
        String quote = getAttributeQuoteValue(config);
        boolean isQuoted = StrLib.isQuoted(result);
        
        if (isQuoted) {
            String oldQuote = result.substring(0, 1);
            String newQuote = oldQuote;
            boolean needAttributeQuote = oldQuote != quote;
            boolean needTransformValue = false;

            if (needAttributeQuote || trimAttribute) {
                needTransformValue = true;
                if (needAttributeQuote) {
                    newQuote = quote;
                };
            }

            if (needTransformValue) {

                // TODO: Use requote(str, quoteStartOld, quoteEndOld,
                // quoteStartNew, quoteEndNew, trim)
                result = StrLib.unquote(result);
                if (trimAttribute) {
                    result = StrLib.trim(result);
                }
                result = StrLib.quote(result, newQuote, newQuote);
            }

        } else {

            if (trimAttribute) {
                result = StrLib.trim(result);
            }
            result = StrLib.quote(result, quote, quote);
        }

        return result;
    }

}
