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

package plazma.kernel.lib.data.yaml;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.io.IOLib;
import plazma.kernel.lib.str.StrLib;
import plazma.kernel.lib.sys.SysLib;

import static plazma.kernel.lib.data.yaml.YamlWriterConfig.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YamlWriter {

    // text

    public String writeYamlToText(Node root) {
        return writeYamlToText(null, root);
    }

    public String writeYamlToText(YamlWriterConfig config, Node root) {
        if (root == null) {
            return "";
        }

        YamlStringAppendable buf = new YamlStringAppendable();

        writeChildren(config, buf, root, -1);

        String result = buf.toString();

        return result;
    }
    
    
    // file

    public void writeYamlToFile(String fileName, Node root) throws IOException {
        writeYamlToFile(fileName, null, root);
    }

    public void writeYamlToFile(String fileName, YamlWriterConfig config, Node root) throws IOException {
        String text = writeYamlToText(config, root);
        IOLib.writeText(fileName, text);
    }

    //

    public void writeYamlToFile(File file, Node root) throws IOException {
        writeYamlToFile(file, null, root);
    }

    public void writeYamlToFile(File file, YamlWriterConfig config, Node root) throws IOException {
        String text = writeYamlToText(config, root);
        IOLib.writeText(file, text);
    }    

    // console

    public void writeYamlToConsole(Node root) {
        writeYamlToConsole(null, root);
    }

    public void writeYamlToConsole(YamlWriterConfig config, Node root) {
        if (root == null) {
            return;
        }

        boolean flush = SysLib.isSupportEscapeCode(); // TODO: optional

        YamlAppendable buf = null;
        if (flush) {
            buf = new YamlStringAppendable();
        } else {
            buf = new YamlConsoleAppendable();
        }

        if (config != null) {
            buf.setColorized(config.colorized && SysLib.isStdOutEnabled());
        }

        writeYamlChildren(config, buf, root, -1);   

        if (flush) {
            // Flush YamlStringAppendable to console
            SysLib.print(buf.toString());
        }
     
    }

    //

    protected String getIndentValue(YamlWriterConfig config) {
        return INDENT_SPACE_VALUE;
    }

    protected String getAttributeQuoteValue(YamlWriterConfig config) {
        return config == null ? QUOTE_DEFAULT_VALUE : config.getAttributeQuoteValue();
    }

    ////

    protected boolean hasAttributeCase(YamlWriterConfig config) {
        return config == null ? false : !StrLib.isEmpty(config.attributeCase);
    }

    protected String getAttributeCase(YamlWriterConfig config) {
        return config == null ? null : config.attributeCase;
    }

    protected boolean isTrimAttribute(YamlWriterConfig config) {
        return config == null ? false : config.trimAttribute;
    }

    ////

    protected String toTypeCase(String str, String type) {
        return StrLib.toTypeCase(str, type);
    }

    boolean isArray = false;

    protected String transformYamlAttributeName(YamlWriterConfig config, String attributeName) {
        String result = attributeName;
        if (StrLib.isEmpty(result)) {
            return result;
        }
        if (hasAttributeCase(config)) {
            result = toTypeCase(attributeName, getAttributeCase(config));
        }

        //YAML: UNQUOTE
        //TODO: Why it was quoted?
        boolean isQuoted = StrLib.isQuoted(attributeName);
        if (isQuoted) {
            result = StrLib.unquote(attributeName);
        }

        return result;
    }

    protected String transformAttributeName(YamlWriterConfig config, String attributeName) {
        String result = attributeName;
        if (StrLib.isEmpty(result)) {
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

    protected String transformAttributeValue(YamlWriterConfig config, String attributeValue) {
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

    ////

    protected void append(YamlAppendable buf, String str) {
        buf.append(str);
    }

    protected void appendObject(YamlAppendable buf, String str) {
        buf.appendObject(str);
    }

    protected void appendArray(YamlAppendable buf, String str) {
        buf.appendArray(str);
    }

    protected void appendAttributeName(YamlAppendable buf, String str) {
        buf.appendAttributeName(str);
    }

    protected void appendAttributeValue(YamlAppendable buf, String str) {
        buf.appendAttributeValue(str);
    }

    protected void appendText(YamlAppendable buf, String str) {
        buf.appendText(str);
    }

    ////

    protected void writeLine(YamlWriterConfig config, YamlAppendable buf) {
        buf.append("\n");
    }

    protected void writeLevelSpace(YamlWriterConfig config, YamlAppendable buf, int level) {
        
        String indent = getIndentValue(config);

        for (int i = 0; i < level; i++) {
            buf.append(indent);
        }
    }

    protected void writeLevelSpace_(YamlWriterConfig config, YamlAppendable buf, int level) {
        if (!isArray) {
            writeLevelSpace(config, buf, level);
            return;
        }

        isArray = false; // reset        
        String indent = getIndentValue(config);

        // TODO: Temp solution: For indent ' ' only (1 space)
        int len = indent.length() * level;
        if (len <= 2) {
            //TODO
            buf.append("- ");
            return;
        }

        for (int i = 0; i < level - 2; i++) {
            buf.append(indent);
        }

        buf.append("- ");
    }

    ////

    protected boolean isEmptyNode(Node node) {
        if (node == null) {
            return true;
        }
        return !node.hasAttributes() && !node.hasChildren() && !node.hasText();
    }

    protected boolean isJsonNode(Node node) {
        if (node == null) {
            return false;
        }

        return node.isSubType(YamlParser.JSON_OBJECT_SUBTYPE)
        || node.isSubType(YamlParser.JSON_ARRAY_SUBTYPE)
        || node.isParentSubType(YamlParser.JSON_OBJECT_SUBTYPE)
        || node.isParentSubType(YamlParser.JSON_ARRAY_SUBTYPE);
    }

    ////

    protected void writeNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (isJsonNode(node)) {
            writeJsonNode(config, buf, node, level);
        } else {
            writeYamlNode(config, buf, node, level);
        }
    }

    protected void writeYamlNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        int nodeType = node.getType();

        if (nodeType == YamlParser.ATTRIBUTE_TYPE) {

            // Yaml
            writeYamlAttributeNode(config, buf, node, level);

        } else if (nodeType == YamlParser.OBJECT_TYPE) {

            // Yaml
            writeYamlObjectNode(config, buf, node, level);

        } else if (nodeType == YamlParser.ARRAY_TYPE) {

            // Yaml
            writeYamlArrayNode(config, buf, node, level);

        } else {
            if (node.hasText()) {
                writeLine(config, buf);
                writeLevelSpace_(config, buf, level);

                String text = node.getText();

                appendText(buf, text);
            }
        }

        level--;
    }

    protected void writeJsonNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        int nodeType = node.getType();

        if (nodeType == YamlParser.ATTRIBUTE_TYPE) {

            // Json
            writeJsonAttributeNode(config, buf, node, level);

        } else if (nodeType == YamlParser.OBJECT_TYPE) {

            // Json
            writeJsonObjectNode(config, buf, node, level);

        } else if (nodeType == YamlParser.ARRAY_TYPE) {

            // Json
            writeJsonArrayNode(config, buf, node, level);

        } else {
            if (node.hasText()) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);

                String text = node.getText();

                appendText(buf, text);
            }
        }

        level--;
    }

    ////

    protected void writeAttributeNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (isJsonNode(node)) {
            writeJsonAttributeNode(config, buf, node, level);
        } else {
            writeYamlAttributeNode(config, buf, node, level);
        }
    }

    protected void writeYamlAttributeNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {

        boolean trimAttribute = isTrimAttribute(config);

        String attributeName = node.getName();
        String attributeValue = node.getText();

        // NORMALIZE-ATTRIBUTE-VALUE
        if (trimAttribute) {
            attributeValue = StrLib.trim(attributeValue);
        }

        if (StrLib.isEmpty(attributeValue)) {
            // skip
            // continue;
        }

        // TRANSFORM-ATTRIBUTE-NAME
        attributeName = transformYamlAttributeName(config, attributeName);

        // TRANSFORM-ATTRIBUTE-VALUE
        // attributeValue = transformAttributeValue(config, attributeValue);

        writeLine(config, buf);
        writeLevelSpace_(config, buf, level);

        appendAttributeName(buf, attributeName);
        append(buf, ": ");

        if (node.hasChildren()) {
            writeYamlNode(config, buf, node.getChild(0), level++);
        } else {
            appendAttributeValue(buf, attributeValue);
        }
    }

    protected void writeJsonAttributeNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {

        boolean trimAttribute = isTrimAttribute(config);

        String attributeName = node.getName();
        String attributeValue = node.getText();

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

    protected void writeObjectNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (isJsonNode(node)) {
            writeJsonObjectNode(config, buf, node, level);
        } else {
            writeYamlObjectNode(config, buf, node, level);
        }
    }

    protected void writeYamlObjectNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {

        if (isEmptyNode(node)) {

            if (node.getParentType() != YamlParser.ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendObject(buf, "{}");
            return;
        }

        if (node.hasAttributes()) {
            writeYamlAttributes(config, buf, node, level); // TODO
        }

        if (node.hasChildren()) {
            writeYamlChildren(config, buf, node, level);
        }

        // if (hasText) {
        //    buf.append(text);
        //}

    }

    protected void writeJsonObjectNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {

        if (isEmptyNode(node)) {

            if (node.getParentType() != YamlParser.ATTRIBUTE_TYPE) {
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
            writeYamlAttributes(config, buf, node, level); // TODO
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

    protected void writeArrayNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (isJsonNode(node)) {
            writeJsonArrayNode(config, buf, node, level);
        } else {
            writeYamlArrayNode(config, buf, node, level);
        }
    }

    protected void writeYamlArrayNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {

        if (isEmptyNode(node)) {

            if (node.getParentType() != YamlParser.ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendArray(buf, "[]");
            return;
        }

        if (node.hasChildren()) {
            writeYamlChildren_(config, buf, node, level);
        }

    }

    protected void writeJsonArrayNode(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {

        if (isEmptyNode(node)) {

            if (node.getParentType() != YamlParser.ATTRIBUTE_TYPE) {
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


    ////

    protected void writeYamlAttributes(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
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

        //for (int i = 0; i < count; i++) {
        
        Map<String, String> attributes = node.getAttributes();
        int i = 0;

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

            writeLine(config, buf);
            writeLevelSpace(config, buf, level + 1);

            appendAttributeName(buf, attributeName);
            append(buf, ": ");
            appendAttributeValue(buf, attributeValue);
        }
    }

    protected void writeJsonAttributes(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
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

        //for (int i = 0; i < count; i++) {
            
        Map<String, String> attributes = node.getAttributes();
        int i = 0;

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

    protected void writeChildren(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (isJsonNode(node)) {
            writeJsonChildren(config, buf, node, level);
        } else {
            writeYamlChildren(config, buf, node, level);
        }
    }

    protected void writeYamlChildren(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }

        if (!node.hasChildren()) {
            return;
        }

        for (Node child : node.getChildren()) {
            writeYamlNode(config, buf, child, level);
        }
    }

    protected void writeYamlChildren_(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
        if (node == null) {
            return;
        }

        if (!node.hasChildren()) {
            return;
        }

        for (Node child : node.getChildren()) {

            // Force array flag for each element, because we reset this flag in writeLevelSpace_(...)
            isArray = true;

            writeYamlNode(config, buf, child, level);
        }

        isArray = false;
    }


    protected void writeJsonChildren(YamlWriterConfig config, YamlAppendable buf, Node node, int level) {
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
            writeYamlNode(config, buf, child, level);
            i++;
        }
    }

}
