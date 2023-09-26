#include <string>
#include <vector>
#include <iostream>

#include "plazma/lib/data/node/Node.h"
#include "plazma/lib/str/strlib.h"
#include "plazma/lib/str/strlib2.h"
#include "plazma/lib/io/iolib.h"

#include "xmlwriter.h"
#include "xmlparser.h"
#include "XmlWriterConfig.h"
#include "XmlStringAppendable.h"
#include "XmlConsoleAppendable.h"

namespace xmlwriter {

    // text

    std::string writeXmlToText(node::Node* root) {
        return writeXmlToText(nullptr, root);
    }

    std::string writeXmlToText(xml::XmlWriterConfig* config, node::Node* root) {
        if (root == nullptr) {
            return "";
        }

        xml::XmlStringAppendable* buf = new xml::XmlStringAppendable();

        writeXmlChildren(config, buf, root, -1);        
        std::string result = buf->toString();

        if (buf != nullptr) {
            delete buf;
        }

        return result;
    }

    // console

    void writeXmlToConsole(node::Node* root) {
        writeXmlToConsole(nullptr, root);
    }

    void writeXmlToConsole(xml::XmlWriterConfig* config, node::Node* root) {
        if (root == nullptr) {
            return;
        }

        bool flush = syslib::isSupportEscapeCode(); // TODO: optional

        xml::XmlAppendable* buf = nullptr;
        if (flush) {
            buf = new xml::XmlStringAppendable();
        } else {
            buf = new xml::XmlConsoleAppendable();
        }

        if (config != nullptr) {
            buf->setColorized(config->colorized && syslib::isStdOutEnabled());
        }

        writeXmlChildren(config, buf, root, -1);

        if (flush) {
            // Flush XmlStringAppendable to console
            syslib::print(((xml::XmlStringAppendable*) buf)->toString());
        }

        if (buf != nullptr) {
            delete buf;
        }

    }

    //

    /*
     Return true if config use inline flag
    */
    bool isInline(xml::XmlWriterConfig* config) {
        return config == nullptr ? false : config->inlineFlag;
    }

    bool isInlineNode(xml::XmlWriterConfig* config) {
        return config == nullptr ? false : config->inlineNodeFlag;
    }

    int getInlineNodeLimit(xml::XmlWriterConfig* config) {
        return config == nullptr ? INLINE_NODE_LIMIT : config->getInlineNodeLimit();
    }

    /*
     Return real indent (tab or 2 spaces) by config indent
    */
    std::string getIndentValue(xml::XmlWriterConfig* config) {
        return config == nullptr ? INDENT_DEFAULT_VALUE : config->getIndentValue();
    }

    std::string getAttributeQuoteValue(xml::XmlWriterConfig* config) {
        return config == nullptr ? QUOTE_DEFAULT_VALUE : config->getAttributeQuoteValue();
    }

    bool hasTagCase(xml::XmlWriterConfig* config) {
        return config == nullptr ? false : !config->tagCase.empty();
    }

    std::string getTagCase(xml::XmlWriterConfig* config) {
        return config == nullptr ? "" : config->tagCase;
    }

    bool hasAttributeCase(xml::XmlWriterConfig* config) {
        return config == nullptr ? false : !config->attributeCase.empty();
    }

    std::string getAttributeCase(xml::XmlWriterConfig* config) {
        return config == nullptr ? "" : config->attributeCase;
    }

    bool isTrimAttribute(xml::XmlWriterConfig* config) {
        return config == nullptr ? false : config->trimAttribute;
    }

    ////

    bool isSpecNode(int nodeType) {
        return (nodeType == xmlparser::PROCESSING_INSTRUCTION_NODE ||
                nodeType == xmlparser::DOCUMENT_TYPE_NODE

                // ALT: DOCTYPE
                || nodeType == xmlparser::ENTITY_DECL_NODE ||
                nodeType == xmlparser::ELEMENT_DECL_NODE ||
                nodeType == xmlparser::ATTRLIST_DECL_NODE ||
                nodeType == xmlparser::NOTATION_DECL_NODE);
    }

    ////

    std::string toTypeCase(const std::string &str, const std::string &type) {
        return strlib::toTypeCase(str, type);
    }


    std::string transformAttributeName(xml::XmlWriterConfig* config, const std::string &attributeName) {
        if (attributeName.empty()) {
            return attributeName;
        }
        std::string result = attributeName;
        if (hasAttributeCase(config)) {
            result = toTypeCase(result, getAttributeCase(config));
        }
        return result;
    }

    std::string transformAttributeValue(xml::XmlWriterConfig* config, const std::string &attributeValue) {
        if (attributeValue.empty()) {
            return attributeValue;
        }
        std::string result = attributeValue;
        bool trimAttribute = isTrimAttribute(config);
        std::string quote = getAttributeQuoteValue(config);
        bool isQuoted = strlib::isQuoted(result);
        
        if (isQuoted) {
            std::string oldQuote = result.substr(0, 1);
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
                result = strlib::unquote(result);
                if (trimAttribute) {
                    result = strlib::trim(result);
                }
                result = strlib::quote(result, newQuote, newQuote);
            }

        } else {

            if (trimAttribute) {
                result = strlib::trim(result);
            }
            result = strlib::quote(result, quote, quote);
        }

        return result;
    }

    ////

    void append(xml::XmlAppendable* buf, const std::string &str) {
        buf->append(str);
    }

    void appendTag(xml::XmlAppendable* buf, const std::string &str) {
        buf->appendTag(str);
    }

    void appendTagName(xml::XmlAppendable* buf, const std::string &str) {
        buf->appendTagName(str);
    }

    void appendAttributeName(xml::XmlAppendable* buf, const std::string &str) {
        buf->appendAttributeName(str);
    }

    void appendAttributeValue(xml::XmlAppendable* buf, const std::string &str) {
        buf->appendAttributeValue(str);
    }

    void appendText(xml::XmlAppendable* buf, const std::string &str) {
        buf->appendText(str);
    }

    void appendCDATA(xml::XmlAppendable* buf, const std::string &str) {
        buf->appendCDATA(str);
    }

    ///

    void writeLine(xml::XmlWriterConfig* config, xml::XmlAppendable* buf) {
        if (isInline(config)) {
            // inline flag -> no write new line
            return;
        }
        buf->append("\n");
    }

    void writeLevelSpace(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, int level) {
        
        if (isInline(config)) {
            // inline flag -> no write level space
            return;
        }

        std::string indent = getIndentValue(config);

        for (int i = 0; i < level; i++) {
            buf->append(indent);
        }
    }

    ///

    bool isInlineText(xml::XmlWriterConfig* config, const std::string& text) {
        if (!isInlineNode(config)) {
            return false; 
        }
        return text.size() < getInlineNodeLimit(config) && strlib::isLineText(text);
    }

    bool isSingleNode(node::Node* node) {
        if (node == nullptr) {
            return false;
        }
        return !node->hasChildren() && !node->hasText();
    }

    ////

    void writeStartTag(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level, const std::string& tagName, int nodeType) {

        bool specNode = isSpecNode(nodeType);

        // START-TAG
        if (specNode) {
            if (nodeType == xmlparser::PROCESSING_INSTRUCTION_NODE) {
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
                if (nodeType == xmlparser::PROCESSING_INSTRUCTION_NODE) {
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
        if (nodeType != xmlparser::DOCUMENT_TYPE_NODE) {
            appendTag(buf, ">");
        }

    }

    void writeEndTag(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level, const std::string& tagName, int nodeType) {

        bool specNode = isSpecNode(nodeType);

        // END-TAG
        if (specNode) {
            if (nodeType == xmlparser::PROCESSING_INSTRUCTION_NODE) {
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

    ////

    void writeXmlNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        level++;

        // TODO: NORMALIZE text? 
        // But we normalized text in lexer mode
        // May be it is optional

        //string tagName;
        //ustring tagNameU = node->getName2();
        //if (getTagCase(config) == "upper") {
            //wstring tagNameW = iolib::ustring_to_wstring(tagNameU);
            //stringlib2::_toCaseW(tagNameW, true);
            //tagName = iolib::wstring_to_utf8(tagNameW);

            //stringlib2::_toCase(tagNameU, true);
            //tagName = iolib::ustring_to_utf8(tagNameU);
        //} else {
        //    tagName = iolib::ustring_to_utf8(tagNameU);
        //}

        int nodeType = node->getType();

        if (nodeType == xmlparser::TEXT_NODE) {

            writeXmlTextNode(config, buf, node, level);

        } else if (nodeType == xmlparser::COMMENT_NODE) {

            writeXmlCommentNode(config, buf, node, level);

        } else if (nodeType == xmlparser::CDATA_SECTION_NODE) {

            writeXmlCDATANode(config, buf, node, level);

        } else {

            writeXmlElementNode(config, buf, node, level);

        }

        level--;
    }

    void writeXmlTextNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level) {

        // #text

        if (!node->hasText()) {
            // TODO: Maybe ignore
            //append(buf, "");
            return;
        }

        // CHECK-INLINE MODE for only one TextNode
        bool oneTextChild = false;
        node::Node *parentNode = node->getParent();

        if (parentNode != nullptr && parentNode->getChildCount() == 1) {
            oneTextChild = true;
        }

        if (!oneTextChild) {
            writeLine(config, buf);
            writeLevelSpace(config, buf, level);
        }

        if (node->hasText()) {
            std::string text = node->getText();
            append(buf, text);
        }
    }

    void writeXmlCommentNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level) {

        // #comment

        writeLine(config, buf);
        writeLevelSpace(config, buf, level);

        if (!node->hasText()) {
            // TODO: Maybe ignore
            append(buf, "<!---->");
            return;
        }

        // START-TAG
        append(buf, "<!--");

        std::string text = node->getText();

        bool inlineText = true; // isInlineText(config, text); // TODO: inlne by default

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

    void writeXmlCDATANode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level) {

        // #cdata-section
        
        writeLine(config, buf);
        writeLevelSpace(config, buf, level);

        if (!node->hasText()) {
            // TODO: Maybe ignore
            append(buf, "<![CDATA[]]>");
            return;
        }

        // START-TAG
        append(buf, "<![CDATA[");

        std::string text = node->getText();
        appendCDATA(buf, text);

        // END-TAG
        append(buf, "]]>");
    }

    void writeXmlElementNode(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level) {

        std::string text = node->getText();
        bool hasChildren = node->hasChildren();
        bool hasText = node->hasText();
        bool isSingle = isSingleNode(node);
        bool inlineFlag = isInline(config);
        bool inlineText = isInlineText(config, text);

        std::string tagName = node->getName();
        int nodeType = node->getType();

        // TODO: Separate NodeType: DOCUMENT_TYPE_NODE,
        // PROCESSING_INSTRUCTION_NODE...

        // #element

        // TRANSFORM-TAG-NAME
        if (hasTagCase(config) && !isSpecNode(nodeType)) {
            std::string tagCase = getTagCase(config);
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

            if (nodeType == xmlparser::DOCUMENT_TYPE_NODE) {
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
        bool oneTextChild = false;
        if (node->getChildCount() == 1) {
            node::Node *firstNode = node->getChild(0);
            if (firstNode != nullptr && firstNode->getType() == xmlparser::TEXT_NODE) {
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

    void writeXmlAttributes(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node) {
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

        int nodeType= node->getType();
        bool specNode = isSpecNode(nodeType);
        
        for (int i = 0; i < count; i++) {

            attributeName = node->getAttributeName(i);
            attributeValue = node->getAttributeValue(i);

            // NORMALIZE-ATTRIBUTE-VALUE
            if (trimAttribute) {
                attributeValue = strlib::trim(attributeValue);
            }

            if (attributeValue.empty()) {

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

    void writeXmlChildren(xml::XmlWriterConfig* config, xml::XmlAppendable* buf, node::Node* node, int level) {
        if (node == nullptr) {
            return;
        }

        if (!node->hasChildren()) {
            return;
        }

        for (node::Node* child : *(node->getChildren())) {
            writeXmlNode(config, buf, child, level);
        }
    }


}