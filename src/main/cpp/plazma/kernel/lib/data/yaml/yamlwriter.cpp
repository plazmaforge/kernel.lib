#include <string>
#include <iostream>

#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/str/strlib.h"

#include "yamlwriter.h"
#include "yamlparser.h"
#include "YamlWriterConfig.h"
#include "YamlStringAppendable.h"
#include "YamlConsoleAppendable.h"

namespace yamlwriter {

    // text

    std::string writeYamlToText(node::Node* root) {
        return writeYamlToText(nullptr, root);
    }

    std::string writeYamlToText(yaml::YamlWriterConfig* config, node::Node* root) {
        if (root == nullptr) {
            return "";
        }

        yaml::YamlStringAppendable* buf = new yaml::YamlStringAppendable();

        writeChildren(config, buf, root, -1);

        std::string result = buf->toString();

        if (buf != nullptr) {
            delete buf;
        }

        return result;
    }

    // console

    void writeYamlToConsole(node::Node* root) {
        writeYamlToConsole(nullptr, root);
    }

    void writeYamlToConsole(yaml::YamlWriterConfig* config, node::Node* root) {
        if (root == nullptr) {
            return;
        }

        bool flush = syslib::isSupportEscapeCode(); // TODO: optional

        yaml::YamlAppendable* buf = nullptr;
        if (flush) {
            buf = new yaml::YamlStringAppendable();
        } else {
            buf = new yaml::YamlConsoleAppendable();
        }

        if (config != nullptr) {
            buf->setColorized(config->colorized && syslib::isStdOutEnabled());
        }

        writeYamlChildren(config, buf, root, -1);   

        if (flush) {
            // Flush YamlStringAppendable to console
            syslib::print(((yaml::YamlStringAppendable*) buf)->toString());
        }
     
        if (buf != nullptr) {
            delete buf;
        }

    }

    //

    std::string getIndentValue(yaml::YamlWriterConfig* config) {
        return INDENT_SPACE_VALUE;
    }

    std::string getAttributeQuoteValue(yaml::YamlWriterConfig* config) {
        return config == nullptr ? QUOTE_DEFAULT_VALUE : config->getAttributeQuoteValue();
    }

    ////

    bool hasAttributeCase(yaml::YamlWriterConfig* config) {
        return config == nullptr ? false : !config->attributeCase.empty();
    }

    std::string getAttributeCase(yaml::YamlWriterConfig* config) {
        return config == nullptr ? "" : config->attributeCase;
    }

    bool isTrimAttribute(yaml::YamlWriterConfig* config) {
        return config == nullptr ? false : config->trimAttribute;
    }

    ////

    std::string toTypeCase(const std::string &str, const std::string &type) {
        return strlib::toTypeCase(str, type);
    }

    bool isArray = false;

    std::string transformYamlAttributeName(yaml::YamlWriterConfig* config, const std::string &attributeName) {
        std::string result = attributeName;
        if (result.empty()) {
            return result;
        }
        if (hasAttributeCase(config)) {
            result = toTypeCase(attributeName, getAttributeCase(config));
        }

        //YAML: UNQUOTE
        //TODO: Why it was quoted?
        bool isQuoted = strlib::isQuoted(attributeName);
        if (isQuoted) {
            result = strlib::unquote(attributeName);
        }

        return result;
    }

    std::string transformAttributeName(yaml::YamlWriterConfig* config, const std::string &attributeName) {
        std::string result = attributeName;
        if (result.empty()) {
            return result;
        }
        if (hasAttributeCase(config)) {
            result = toTypeCase(attributeName, getAttributeCase(config));
        }
        bool isQuoted = strlib::isQuoted(attributeName);
        if (!isQuoted) {
            result = strlib::quote(attributeName);
        }
        return result;
    }

    std::string transformAttributeValue(yaml::YamlWriterConfig* config, const std::string &attributeValue) {
        std::string result = attributeValue;

        // We transform empty value, because we need to quote it
        bool trimAttribute = isTrimAttribute(config);
        std::string quote = getAttributeQuoteValue(config);

        bool isQuoted = strlib::isQuoted(attributeValue);
        
        if (isQuoted) {
            std::string oldQuote = attributeValue.substr(0, 1);
            std::string newQuote = oldQuote;
            bool needAttributeQuote = oldQuote != quote;
            bool needTransformValue = false;

            if (needAttributeQuote || trimAttribute) {
                needTransformValue = true;
                if (needAttributeQuote) {
                    newQuote = quote;
                };
            }

            if (needTransformValue) {

                // TODO: Use requote(str, quoteStartOld, quoteEndOld,
                // quoteStartNew, quoteEndNew, trim)
                result = strlib::unquote(attributeValue);
                if (trimAttribute) {
                    result = strlib::trim(attributeValue);
                }
                result = strlib::quote(attributeValue, newQuote, newQuote);
            }

        } else {

            if (trimAttribute) {
                result = strlib::trim(attributeValue);
            }
            result = strlib::quote(attributeValue, quote, quote);
        }

        return result;
    }

    ////

    void append(yaml::YamlAppendable* buf, const std::string &str) {
        buf->append(str);
    }

    void appendObject(yaml::YamlAppendable* buf, const std::string &str) {
        buf->appendObject(str);
    }

    void appendArray(yaml::YamlAppendable* buf, const std::string &str) {
        buf->appendArray(str);
    }

    void appendAttributeName(yaml::YamlAppendable* buf, const std::string &str) {
        buf->appendAttributeName(str);
    }

    void appendAttributeValue(yaml::YamlAppendable* buf, const std::string &str) {
        buf->appendAttributeValue(str);
    }

    void appendText(yaml::YamlAppendable* buf, const std::string &str) {
        buf->appendText(str);
    }

    ////

    void writeLine(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf) {
        buf->append("\n");
    }

    void writeLevelSpace(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, int level) {
        
        std::string indent = getIndentValue(config);

        for (int i = 0; i < level; i++) {
            buf->append(indent);
        }
    }

    void writeLevelSpace_(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, int level) {
        if (!isArray) {
            writeLevelSpace(config, buf, level);
            return;
        }

        isArray = false; // reset        
        std::string indent = getIndentValue(config);

        // TODO: Temp solution: For indent ' ' only (1 space)
        int len = indent.length() * level;
        if (len <= 2) {
            //TODO
            buf->append("- ");
            return;
        }

        for (int i = 0; i < level - 2; i++) {
            buf->append(indent);
        }

        buf->append("- ");
    }

    ////

    bool isEmptyNode(node::Node* node) {
        if (node == nullptr) {
            return true;
        }
        return !node->hasAttributes() && !node->hasChildren() && !node->hasText();
    }

    bool isJsonNode(node::Node* node) {
        if (node == nullptr) {
            return false;
        }

        return node->isSubType(yamlparser::JSON_OBJECT_SUBTYPE)
        || node->isSubType(yamlparser::JSON_ARRAY_SUBTYPE)
        || node->isParentSubType(yamlparser::JSON_OBJECT_SUBTYPE)
        || node->isParentSubType(yamlparser::JSON_ARRAY_SUBTYPE);
    }

    ////

    void writeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (isJsonNode(node)) {
            writeJsonNode(config, buf, node, level);
        } else {
            writeYamlNode(config, buf, node, level);
        }
    }

    void writeYamlNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        int nodeType = node->getType();

        if (nodeType == yamlparser::ATTRIBUTE_TYPE) {

            // Yaml
            writeYamlAttributeNode(config, buf, node, level);

        } else if (nodeType == yamlparser::OBJECT_TYPE) {

            // Yaml
            writeYamlObjectNode(config, buf, node, level);

        } else if (nodeType == yamlparser::ARRAY_TYPE) {

            // Yaml
            writeYamlArrayNode(config, buf, node, level);

        } else {
            if (node->hasText()) {
                writeLine(config, buf);
                writeLevelSpace_(config, buf, level);

                std::string text = node->getText();

                appendText(buf, text);
            }
        }

        level--;
    }

    void writeJsonNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        int nodeType = node->getType();

        if (nodeType == yamlparser::ATTRIBUTE_TYPE) {

            // Json
            writeJsonAttributeNode(config, buf, node, level);

        } else if (nodeType == yamlparser::OBJECT_TYPE) {

            // Json
            writeJsonObjectNode(config, buf, node, level);

        } else if (nodeType == yamlparser::ARRAY_TYPE) {

            // Json
            writeJsonArrayNode(config, buf, node, level);

        } else {
            if (node->hasText()) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);

                std::string text = node->getText();

                appendText(buf, text);
            }
        }

        level--;
    }

    ////

    void writeAttributeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (isJsonNode(node)) {
            writeJsonAttributeNode(config, buf, node, level);
        } else {
            writeYamlAttributeNode(config, buf, node, level);
        }
    }

    void writeYamlAttributeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        // std::cout << ">>ATTRIBUTE_TYPE\n";

        bool trimAttribute = isTrimAttribute(config);

        std::string attributeName = node->getName();
        std::string attributeValue = node->getText();

        // NORMALIZE-ATTRIBUTE-VALUE
        if (trimAttribute) {
            attributeValue = strlib::trim(attributeValue);
        }

        if (attributeValue.empty()) {
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

        if (node->hasChildren()) {
            writeYamlNode(config, buf, node->getChild(0), level++);
        } else {
            appendAttributeValue(buf, attributeValue);
        }
    }

    void writeJsonAttributeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        // std::cout << ">>JSON_ATTRIBUTE_TYPE\n";

        bool trimAttribute = isTrimAttribute(config);

        std::string attributeName = node->getName();
        std::string attributeValue = node->getText();

        // NORMALIZE-ATTRIBUTE-VALUE
        if (trimAttribute) {
            attributeValue = strlib::trim(attributeValue);
        }

        if (attributeValue.empty()) {
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

        if (node->hasChildren()) {
            writeJsonNode(config, buf, node->getChild(0), level++);
        } else {
            appendAttributeValue(buf, attributeValue);
        }
    }

    void writeObjectNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (isJsonNode(node)) {
            writeJsonObjectNode(config, buf, node, level);
        } else {
            writeYamlObjectNode(config, buf, node, level);
        }
    }

    void writeYamlObjectNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {

        // std::cout << ">>OBJECT_TYPE\n";

        if (isEmptyNode(node)) {

            if (node->getParentType() != yamlparser::ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendObject(buf, "{}");
            return;
        }

        if (node->hasAttributes()) {
            writeYamlAttributes(config, buf, node, level); // TODO
        }

        if (node->hasChildren()) {
            writeYamlChildren(config, buf, node, level);
        }

        // if (hasText) {
        //    buf.append(text);
        //}

    }

    void writeJsonObjectNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {

        // std::cout << ">>JSON_OBJECT_TYPE\n";

        if (isEmptyNode(node)) {

            if (node->getParentType() != yamlparser::ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendObject(buf, "{}");
            return;
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendObject(buf, "{");

        if (node->hasAttributes()) {
            writeYamlAttributes(config, buf, node, level); // TODO
        }

        if (node->hasChildren()) {
            writeJsonChildren(config, buf, node, level);
        }

        // if (hasText) {
        //    buf.append(text);
        //}

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendObject(buf, "}");
    }

    void writeArrayNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (isJsonNode(node)) {
            writeJsonArrayNode(config, buf, node, level);
        } else {
            writeYamlArrayNode(config, buf, node, level);
        }
    }

    void writeYamlArrayNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {

        // std::cout << ">>ARRAY_TYPE\n";

        if (isEmptyNode(node)) {

            if (node->getParentType() != yamlparser::ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendArray(buf, "[]");
            return;
        }

        if (node->hasChildren()) {
            writeYamlChildren_(config, buf, node, level);
        }

    }

    void writeJsonArrayNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {

        // std::cout << ">>JSON_ARRAY_TYPE\n";

        if (isEmptyNode(node)) {

            if (node->getParentType() != yamlparser::ATTRIBUTE_TYPE) {
                writeLine(config, buf);
                writeLevelSpace(config, buf, level);
            }

            appendArray(buf, "[]");
            return;
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendArray(buf, "[");

        if (node->hasChildren()) {
            writeJsonChildren(config, buf, node, level);
        }

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);
        appendArray(buf, "]");
    }


    ////

    void writeYamlAttributes(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        if (!node->hasAttributes()) {
            return;
        }

        int count = node->getAttributeCount();
        std::string attributeName = "" ; // null;
        std::string attributeValue = "" ; // null;
        bool trimAttribute = isTrimAttribute(config);

        for (int i = 0; i < count; i++) {

            attributeName = node->getAttributeName(i);
            attributeValue = node->getAttributeValue(i);

            // NORMALIZE-ATTRIBUTE-VALUE
            if (trimAttribute) {
                attributeValue = strlib::trim(attributeValue);
            }

            // TODO: SKIP?
            if (attributeValue.empty()) {
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

    void writeJsonAttributes(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        if (!node->hasAttributes()) {
            return;
        }

        int count = node->getAttributeCount();
        std::string attributeName = "" ; // null;
        std::string attributeValue = "" ; // null;
        bool trimAttribute = isTrimAttribute(config);

        for (int i = 0; i < count; i++) {

            attributeName = node->getAttributeName(i);
            attributeValue = node->getAttributeValue(i);

            // NORMALIZE-ATTRIBUTE-VALUE
            if (trimAttribute) {
                attributeValue = strlib::trim(attributeValue);
            }

            // TODO: SKIP?
            if (attributeValue.empty()) {
                // skip
                //continue;
            }

            // TRANSFORM-ATTRIBUTE-NAME
            attributeName = transformAttributeName(config, attributeName);

            // TRANSFORM-ATTRIBUTE-VALUE
            //attributeValue = transformAttributeValue(config, attributeValue);

            if (i > 0) {
                buf->append(",");
            }

            writeLine(config, buf);
            writeLevelSpace(config, buf, level + 1);

            appendAttributeName(buf, attributeName);
            append(buf, ": ");
            appendAttributeValue(buf, attributeValue);
        }
    }

    void writeChildren(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (isJsonNode(node)) {
            writeJsonChildren(config, buf, node, level);
        } else {
            writeYamlChildren(config, buf, node, level);
        }
    }

    void writeYamlChildren(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        if (!node->hasChildren()) {
            return;
        }

        for (node::Node* child : *(node->getChildren())) {
            writeYamlNode(config, buf, child, level);
        }
    }

    void writeYamlChildren_(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        if (!node->hasChildren()) {
            return;
        }

        for (node::Node* child : *(node->getChildren())) {

            // Force array flag for each element, because we reset this flag in writeLevelSpace_(...)
            isArray = true;

            writeYamlNode(config, buf, child, level);
        }

        isArray = false;
    }


    void writeJsonChildren(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        if (!node->hasChildren()) {
            return;
        }

        int i = 0;
        for (node::Node* child : *(node->getChildren())) {
            if (i > 0) {
                buf->append(",");
            }
            writeYamlNode(config, buf, child, level);
            i++;
        }
    }


}