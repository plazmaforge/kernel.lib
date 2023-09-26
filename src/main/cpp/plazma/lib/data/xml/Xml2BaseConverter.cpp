#include "Xml2BaseConverter.h"
#include "plazma/lib/data/xml/xmlparser.h"
#include "plazma/lib/str/strlib.h"

namespace xml {

    Xml2BaseConverter::Xml2BaseConverter() {
        useAttributesAsChildren = true;
    }

    Xml2BaseConverter::~Xml2BaseConverter() {}

    ////

    std::string Xml2BaseConverter::toString() {
        return "Xml2BaseConverter";
    }

    ////

    node::Node* Xml2BaseConverter::convertDocumentNode(node::Node* original) {

        useAttributesAsChildren = true;

        /*
        node::Node* document = createDocumentNode();
        node::Node* root = createObjectNode();

        document->addChild(root);

        node::Node* node = convertNode(orginal->getChild(0));
        if (node != nullptr) {
            root->addChild(node);
        }

        return document;
        */

        return convertNode(original);
    }

    node::Node* Xml2BaseConverter::convertNode(node::Node* original) {

        ///////////////////////////////////////////////////////////

        if (original == nullptr) {
            return nullptr;
        }

        int nodeType = original->getType();
        if (isSkipNode(nodeType)) {
            return nullptr;
        }

        node::Node* node = nullptr;

        ///////////////////////////////////////////////////////////

        std::string text = original->getText();
        std::string textType = getTextType(nodeType);

        ///////////////////////////////////////////////////////////

        if (!textType.empty()) {

            //TODO: Check empty and blank
            //if (text.empty()) {
            //    return nullptr;
            //}

            if (!text.empty() && !strlib::isQuoted(text)) {
                text = strlib::quote(text);                
            }

            node = createAttributeNode();

            node->setName(textType);
            node->setText(text);    

            return node;
        }

        ///////////////////////////////////////////////////////////

        bool hasAttributes = original->hasAttributes();
        bool hasChildren = original->hasChildren();
        bool singleNode = !hasAttributes && !hasChildren;

        //if (singleNode) {
            //TODO: Check empty and blank
            //if (text.empty()) {
            //    return nullptr;
            //}

        //}

        node = createAttributeNode();

        node->setName(original->getName());
        node->setName2(original->getName2()); // TODO

        // TODO: Fix empty value or skip it
        if (text.empty()) {
            text = emptyText();
        }

        node->setText(text);

        if (singleNode) {
            return node;
        }

        ///////////////////////////////////////////////////////////

        node::Node* objNode = createObjectNode();

        node->addChild(objNode);

        ///////////////////////////////////////////////////////////

        convertAttrbutes(original, objNode);

        convertChildren(original, objNode);

        ///////////////////////////////////////////////////////////

        return node;
    }


    //void Xml2BaseConverter::convertAttrbutes(node::Node* original, node::Node* node) {
    //    convertAttrbutesAsChildren(original, node);
    //}

    std::string Xml2BaseConverter::getTextType(int nodeType) {
        if (nodeType == xmlparser::TEXT_NODE) {
            return "#text";
        } else if (nodeType == xmlparser::CDATA_SECTION_NODE) {
            return "#cdata";
        } else if (nodeType == xmlparser::COMMENT_NODE) {
            return "#comment";
        }
        return "";
    }

}
