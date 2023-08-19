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

package plazma.kernel.lib.data.json;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.io.IOLib;
import plazma.kernel.lib.str.StrLib;
import plazma.kernel.lib.sys.SysLib;

import static plazma.kernel.lib.data.json.JsonWriterConfig.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonWriter {

    // text

    public String writeJsonToText(Node root) {
        return writeJsonToText(null, root);
    }

    public String writeJsonToText(JsonWriterConfig config, Node root) {
        if (root == null) {
            return "";
        }

        JsonStringAppendable buf = new JsonStringAppendable();

        writeJsonChildren(config, buf, root, -1);
        String result = buf.toString();

        return result;
    }
    
    // file

    public void writeJsonToFile(String fileName, Node root) throws IOException {
        writeJsonToFile(fileName, null, root);
    }

    public void writeJsonToFile(String fileName, JsonWriterConfig config, Node root) throws IOException {
        String text = writeJsonToText(config, root);
        IOLib.writeText(fileName, text);
    }

    //

    public void writeJsonToFile(File file, Node root) throws IOException {
        writeJsonToFile(file, null, root);
    }

    public void writeJsonToFile(File file, JsonWriterConfig config, Node root) throws IOException {
        String text = writeJsonToText(config, root);
        IOLib.writeText(file, text);
    }
    
    // console

    public void writeJsonToConsole(Node root) {
        writeJsonToConsole(null, root);
    }

    public void writeJsonToConsole(JsonWriterConfig config, Node root) {
        if (root == null) {
            return;
        }

        boolean flush = SysLib.isSupportEscapeCode(); // TODO: optional

        JsonAppendable buf = null;
        if (flush) {
            buf = new JsonStringAppendable();
        } else {
            buf = new JsonConsoleAppendable();
        }

        if (config != null) {
            buf.setColorized(config.colorized && SysLib.isStdOutEnabled());
        }

        writeJsonChildren(config, buf, root, -1);

        if (flush) {
            // Flush JsonStringAppendable to console
            SysLib.print(buf.toString());
        }

    }

    ////

    protected void append(JsonAppendable buf, String str) {
        buf.append(str);
    }

    protected void appendObject(JsonAppendable buf, String str) {
        buf.appendObject(str);
    }

    protected void appendArray(JsonAppendable buf, String str) {
        buf.appendArray(str);
    }

    protected void appendAttributeName(JsonAppendable buf, String str) {
        buf.appendAttributeName(str);
    }

    protected void appendAttributeValue(JsonAppendable buf, String str) {
        buf.appendAttributeValue(str);
    }

    protected void appendText(JsonAppendable buf, String str) {
        buf.appendText(str);
    }

    ////

    protected void writeLine(JsonWriterConfig config, JsonAppendable buf) {
        if (isInline(config)) {
            // inline flag . no write new line
            return;
        }

        buf.append("\n");
    }

    protected void writeLevelSpace(JsonWriterConfig config, JsonAppendable buf, int level) {
        if (isInline(config)) {
            // inline flag . no write level space
            return;
        }

        String indent = getIndentValue(config);
        for (int i = 0; i < level; i++) {
            buf.append(indent);
        }
    }

    ////

    protected boolean isEmptyNode(Node node) {
        if (node == null) {
            return true;
        }
        return !node.hasAttributes() && !node.hasChildren() && !node.hasText();
    }

    protected void writeJsonNode(JsonWriterConfig config, JsonAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        String text = node.getText();
        boolean hasAttributes = node.hasAttributes();
        boolean hasChildren = node.hasChildren();  
        boolean hasText = node.hasText();
        
        String nodeName = node.getName();
        int nodeType = node.getType();

        if (nodeType == JsonParser.ATTRIBUTE_TYPE) {

            writeJsonAttributeNode(config, buf, node, level);

        } else if (nodeType == JsonParser.OBJECT_TYPE) {

            writeJsonObjectNode(config, buf, node, level);

        } else if (nodeType == JsonParser.ARRAY_TYPE) {

            writeJsonArrayNode(config, buf, node, level);

        } else {
            if (hasText) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);

                appendText(buf, text);
            }
        }

        level--;
    }

    protected void writeJsonAttributeNode(JsonWriterConfig config, JsonAppendable buf, Node node, int level) {

        String attributeName = null;
        String attributeValue = null;
        boolean trimAttribute = isTrimAttribute(config);

        attributeName = node.getName();
        attributeValue = node.getText();

        // NORMALIZE-ATTRIBUTE-VALUE
        if (trimAttribute) {
            attributeValue = StrLib.trim(attributeValue);
        }

        if (StrLib.isEmpty(attributeValue)) {
            // skip
            // continue;
        }

        // TRANSFORM-ATTRIBUTE-NAME
        attributeName = transformAttributeName(config, attributeName);

        // TRANSFORM-ATTRIBUTE-VALUE
        // attributeValue = transformAttributeValue(config, attributeValue);

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);

        appendAttributeName(buf, attributeName);
        append(buf, ": ");

        if (node.hasChildren()) {
            writeJsonNode(config, buf, node.getChild(0), level++);
        } else {
            appendAttributeValue(buf, attributeValue);
        }
    }

    protected void writeJsonObjectNode(JsonWriterConfig config, JsonAppendable buf, Node node, int level) {

        if (isEmptyNode(node)) {

            if (node.getParentType() != JsonParser.ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendObject(buf, "{}");
            return;
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendObject(buf, "{");

        if (node.hasAttributes()) {
            writeJsonAttributes(config, buf, node, level); // TODO
        }

        if (node.hasChildren()) {
            writeJsonChildren(config, buf, node, level);
        }

        // if (hasText) {
        //    buf.append(text);
        //}

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendObject(buf, "}");
    }

    protected void writeJsonArrayNode(JsonWriterConfig config, JsonAppendable buf, Node node, int level) {

        if (isEmptyNode(node)) {

            if (node.getParentType() != JsonParser.ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendArray(buf, "[]");
            return;
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendArray(buf, "[");

        if (node.hasChildren()) {
            writeJsonChildren(config, buf, node, level);
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendArray(buf, "]");
    }

    protected void writeJsonAttributes(JsonWriterConfig config, JsonAppendable buf, Node node, int level) {
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

        Map<String, String> attributes = node.getAttributes();
        int i = 0;
        
        //for (int i = 0; i < count; i++) {

        for (String attributeName : attributes.keySet()) {
            attributeValue = attributes.get(attributeName);
                      
            //attributeName = node.getAttributeName(i);
            //attributeValue = node.getAttributeValue(i);

            // NORMALIZE-ATTRIBUTE-VALUE
            if (trimAttribute) {
                attributeValue = StrLib.trim(attributeValue);
            }

            // TODO: SKIP?
            if (StrLib.isEmpty(attributeValue)) {
                // skip
                //continue;
            }

            // TRANSFORM-ATTRIBUTE-NAME
            attributeName = transformAttributeName(config, attributeName);

            // TRANSFORM-ATTRIBUTE-VALUE
            //attributeValue = transformAttributeValue(config, attributeValue);

            if (i > 0) {
                buf.append(",");
            }

            writeLine(config, buf);
            writeLevelSpace(config, buf, level + 1);

            appendAttributeName(buf, attributeName);
            append(buf, ": ");
            appendAttributeValue(buf, attributeValue);
            
            i++;

        }
    }

    protected void writeJsonChildren(JsonWriterConfig config, JsonAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }

        if (!node.hasChildren()) {
            return;
        }

        int i = 0;
        for (Node child : node.getChildren()) {
            if (i > 0) {
                buf.append(",");
            }
            writeJsonNode(config, buf, child, level);
            i++;
        }
    }
    
    //// UTILS ////

    /*
     Return true if config use inline flag
    */
    protected boolean isInline(JsonWriterConfig config) {
        return config != null ? false : config.inlineFlag;
    }

    /*
     Return real indent (tab or 2 spaces) by config indent
    */
    protected String getIndentValue(JsonWriterConfig config) {
        return config == null ? INDENT_DEFAULT_VALUE : config.getIndentValue();
    }

    protected String getAttributeQuoteValue(JsonWriterConfig config) {
        return config == null ? QUOTE_DEFAULT_VALUE : config.getAttributeQuoteValue();
    }

    ////

    protected boolean hasAttributeCase(JsonWriterConfig config) {
        return config == null ? false : !StrLib.isEmpty(config.attributeCase);
    }

    protected String getAttributeCase(JsonWriterConfig config) {
        return config == null ? "" : config.attributeCase;
    }

    protected boolean isTrimAttribute(JsonWriterConfig config) {
        return config == null ? false : config.trimAttribute;
    }

    ////

    protected String toTypeCase(String str, String type) {
        return StrLib.toTypeCase(str, type);
    }

    protected String transformAttributeName(JsonWriterConfig config, String attributeName) {
        String result = attributeName;
        if (result.isEmpty()) {
            return result;
        }
        if (hasAttributeCase(config)) {
            result = toTypeCase(attributeName, getAttributeCase(config));
        }
        boolean isQuoted = StrLib.isQuoted(attributeName);
        if (!isQuoted) {
            result = StrLib.quote(attributeName);
        }
        return result;
    }

    protected String transformAttributeValue(JsonWriterConfig config, String attributeValue) {
        String result = attributeValue;

        // We transform empty value, because we need to quote it
        boolean trimAttribute = isTrimAttribute(config);
        String quote = getAttributeQuoteValue(config);

        boolean isQuoted = StrLib.isQuoted(attributeValue);
        
        if (isQuoted) {
            String oldQuote = attributeValue.substring(0, 1);
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
                result = StrLib.unquote(attributeValue);
                if (trimAttribute) {
                    result = StrLib.trim(attributeValue);
                }
                result = StrLib.quote(attributeValue, newQuote, newQuote);
            }

        } else {

            if (trimAttribute) {
                result = StrLib.trim(attributeValue);
            }
            result = StrLib.quote(attributeValue, quote, quote);
        }

        return result;
    }

}
