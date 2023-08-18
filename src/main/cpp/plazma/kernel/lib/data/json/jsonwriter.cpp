#include <string>
#include <iostream>

#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/str/strlib.h"

#include "jsonwriter.h"
#include "jsonparser.h"
#include "JsonWriterConfig.h"
#include "JsonStringAppendable.h"
#include "JsonConsoleAppendable.h"

namespace jsonwriter {

    // text

    std::string writeJsonToText(node::Node* root) {
        return writeJsonToText(nullptr, root);
    }

    std::string writeJsonToText(json::JsonWriterConfig* config, node::Node* root) {
        if (root == nullptr) {
            return "";
        }

        json::JsonStringAppendable* buf = new json::JsonStringAppendable();

        writeJsonChildren(config, buf, root, -1);
        std::string result = buf->toString();

        if (buf != nullptr) {
            delete buf;
        }

        return result;
    }

    // console

    void writeJsonToConsole(node::Node* root) {
        writeJsonToConsole(nullptr, root);
    }

    void writeJsonToConsole(json::JsonWriterConfig* config, node::Node* root) {
        if (root == nullptr) {
            return;
        }

        bool flush = syslib::isSupportEscapeCode(); // TODO: optional

        json::JsonAppendable* buf = nullptr;
        if (flush) {
            buf = new json::JsonStringAppendable();
        } else {
            buf = new json::JsonConsoleAppendable();
        }

        if (config != nullptr) {
            buf->setColorized(config->colorized && syslib::isStdOutEnabled());
        }

        writeJsonChildren(config, buf, root, -1);

        if (flush) {
            // Flush JsonStringAppendable to console
            syslib::print(((json::JsonStringAppendable*) buf)->toString());
        }

        if (buf != nullptr) {
            delete buf;
        }

    }

    //

    /*
     Return true if config use inline flag
    */
    bool isInline(json::JsonWriterConfig* config) {
        return config != nullptr ? false : config->inlineFlag;
    }

    /*
     Return real indent (tab or 2 spaces) by config indent
    */
    std::string getIndentValue(json::JsonWriterConfig* config) {
        return config == nullptr ? INDENT_DEFAULT_VALUE : config->getIndentValue();
    }

    std::string getAttributeQuoteValue(json::JsonWriterConfig* config) {
        return config == nullptr ? QUOTE_DEFAULT_VALUE : config->getAttributeQuoteValue();
    }

    ////

    bool hasAttributeCase(json::JsonWriterConfig* config) {
        return config == nullptr ? false : !config->attributeCase.empty();
    }

    std::string getAttributeCase(json::JsonWriterConfig* config) {
        return config == nullptr ? "" : config->attributeCase;
    }

    bool isTrimAttribute(json::JsonWriterConfig* config) {
        return config == nullptr ? false : config->trimAttribute;
    }

    ////

    std::string toTypeCase(const std::string &str, const std::string &type) {
        return strlib::toTypeCase(str, type);
    }

    std::string transformAttributeName(json::JsonWriterConfig* config, const std::string &attributeName) {
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

    std::string transformAttributeValue(json::JsonWriterConfig* config, const std::string &attributeValue) {
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

    void append(json::JsonAppendable* buf, const std::string &str) {
        buf->append(str);
    }

    void appendObject(json::JsonAppendable* buf, const std::string &str) {
        buf->appendObject(str);
    }

    void appendArray(json::JsonAppendable* buf, const std::string &str) {
        buf->appendArray(str);
    }

    void appendAttributeName(json::JsonAppendable* buf, const std::string &str) {
        buf->appendAttributeName(str);
    }

    void appendAttributeValue(json::JsonAppendable* buf, const std::string &str) {
        buf->appendAttributeValue(str);
    }

    void appendText(json::JsonAppendable* buf, const std::string &str) {
        buf->appendText(str);
    }

    ////

    void writeLine(json::JsonWriterConfig* config, json::JsonAppendable* buf) {
        if (isInline(config)) {
            // inline flag -> no write new line
            return;
        }

        buf->append("\n");
    }

    void writeLevelSpace(json::JsonWriterConfig* config, json::JsonAppendable* buf, int level) {
        if (isInline(config)) {
            // inline flag -> no write level space
            return;
        }

        std::string indent = getIndentValue(config);
        for (int i = 0; i < level; i++) {
            buf->append(indent);
        }
    }

    ////

    bool isEmptyNode(node::Node* node) {
        if (node == nullptr) {
            return true;;
        }
        return !node->hasAttributes() && !node->hasChildren() && !node->hasText();
    }

    void writeJsonNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        std::string text = node->getText();
        bool hasAttributes = node->hasAttributes();
        bool hasChildren = node->hasChildren();  
        bool hasText = node->hasText();
        
        std::string nodeName = node->getName();
        int nodeType = node->getType();

        if (nodeType == jsonparser::ATTRIBUTE_TYPE) {

            writeJsonAttributeNode(config, buf, node, level);

        } else if (nodeType == jsonparser::OBJECT_TYPE) {

            writeJsonObjectNode(config, buf, node, level);

        } else if (nodeType == jsonparser::ARRAY_TYPE) {

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

    void writeJsonAttributeNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level) {
        // std::cout << ">>ATTRIBUTE_TYPE\n";

        std::string attributeName = "";  // null;
        std::string attributeValue = ""; // null;
        bool trimAttribute = isTrimAttribute(config);

        attributeName = node->getName();
        attributeValue = node->getText();

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

    void writeJsonObjectNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level) {

        // std::cout << ">>OBJECT_TYPE\n";

        if (isEmptyNode(node)) {

            if (node->getParentType() != jsonparser::ATTRIBUTE_TYPE) {
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
            writeJsonAttributes(config, buf, node, level); // TODO
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

    void writeJsonArrayNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level) {

        // std::cout << ">>ARRAY_TYPE\n";

        if (isEmptyNode(node)) {

            if (node->getParentType() != jsonparser::ATTRIBUTE_TYPE) {
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

    void writeJsonAttributes(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level) {
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

    void writeJsonChildren(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level) {
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
            writeJsonNode(config, buf, child, level);
            i++;
        }
    }


}