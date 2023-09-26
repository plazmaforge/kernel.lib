#include "Node.h"
#include "BaseNodeConverter.h"

namespace node {

    BaseNodeConverter::BaseNodeConverter() {}

    BaseNodeConverter::~BaseNodeConverter() {}

    ////

    node::Node* BaseNodeConverter::convert(node::Node* node) {
        if (node == nullptr) {
            return nullptr;
        }
        node::Node* result = convertDocumentNode(node);
        return result;
    }

    void BaseNodeConverter::convertChildren(node::Node* original, node::Node* node) {
        if (original == nullptr) {
            return;
        }

        if (!original->hasChildren()) {
            return;
        }

        int count = original->getChildCount();
        node::Node *originalChild = nullptr;
        node::Node *child = nullptr;

        for (int i = 0; i < count; i++) {
            originalChild = original->getChild(i);
            child = convertNode(originalChild);
            if (child == nullptr) {
                continue;
            }
            node->addChild(child);
        }
    }

    void BaseNodeConverter::convertAttrbutes(node::Node* original, node::Node* node) {
        if (useAttributesAsChildren) {
            convertAttrbutesAsChildren(original, node);
        } else {
            convertAttrbutesAsIs(original, node);
        }        
    }

    void BaseNodeConverter::convertAttrbutesAsIs(node::Node* original, node::Node* node) {
        if (original == nullptr) {
            return;
        }

        if (!original->hasAttributes()) {
            return;
        }

        int count = original->getAttributeCount();
        for (int i = 0; i < count; i++) {
            std::string name = original->getAttributeName(i);
            std::string value = original->getAttributeValue(i);
            node->addAttribute(name, value);
        }

    }

    void BaseNodeConverter::convertAttrbutesAsChildren(node::Node* original, node::Node* node) {
        if (original == nullptr) {
            return;
        }

        if (!original->hasAttributes()) {
            return;
        }

        int count = original->getAttributeCount();
        for (int i = 0; i < count; i++) {
            std::string name = original->getAttributeName(i);
            std::string value = original->getAttributeValue(i);

            node::Node* attrNode = createAttributeNode();

            attrNode->setName(name);

            // TODO: Fix empty value or skip it
            if (value.empty()) {
                value = emptyText();
            }

            attrNode->setText(value);

            node->addChild(attrNode);

        }

    }


    ////

    std::string BaseNodeConverter::emptyText() {
        return "\"\"";
    }

    bool BaseNodeConverter::isSkipNode(int nodeType) {
        return false; // by default
    }

    std::string BaseNodeConverter::getTextType(int nodeType) {
        return ""; // by default
    }

    ////

    bool BaseNodeConverter::isDocumentType(int nodeType) {
        return false; // by default
    }

    bool BaseNodeConverter::isObjectType(int nodeType) {
        return false; // by default
    }

    bool BaseNodeConverter::isArrayType(int nodeType) {
        return false; // by default
    }

    bool BaseNodeConverter::isAttributeType(int nodeType) {
        return false; // by default
    }

    bool BaseNodeConverter::isTextType(int nodeType) {
        return false; // by default
    }

    bool BaseNodeConverter::isCommentType(int nodeType) {
        return false; // by default
    }
    
    int BaseNodeConverter::convertNodeType(int nodeType) {
        return 0; // by default
    }

    int BaseNodeConverter::convertNodeSubType(int nodeSubType) {
        return 0; // by default
    }

    ////

    node::Node* BaseNodeConverter::createDocumentNode() {
        return createNode(); // by default
    }

    node::Node* BaseNodeConverter::createObjectNode() {
        return createNode(); // by default
    }

    node::Node* BaseNodeConverter::createArrayNode() {
        return createNode(); // by default
    }

    node::Node* BaseNodeConverter::createAttributeNode() {
        return createNode(); // by default
    }

    node::Node* BaseNodeConverter::createNode() {
        return new node::Node();
    }

    ////

    // Clone implementation
    node::Node* BaseNodeConverter::convertCloneNode(node::Node* original) {

        if (original == nullptr) {
            return nullptr;
        }

        int nodeType = original->getType();
        if (isSkipNode(nodeType)) {
            return nullptr;
        }

        return original->clone();
    }

    // Default implementation
    node::Node* BaseNodeConverter::convertDefaultNode(node::Node* original) {

        if (original == nullptr) {
            return nullptr;
        }

        int nodeType = original->getType();
        if (isSkipNode(nodeType)) {
            return nullptr;
        }

        node::Node* node = new Node();

        // Default implementation
        node->setType(convertNodeType(original->getType()));
        node->setSubType(convertNodeSubType(original->getSubType()));

        node->setName(original->getName());
        node->setName2(original->getName2()); // TODO
        node->setText(original->getText());
        node->setIndent(original->getIndent());

        ///////////////////////////////////////////////////////////

        convertAttrbutes(original, node);

        convertChildren(original, node);

        ///////////////////////////////////////////////////////////

        return node;
    }

    ////

    std::string BaseNodeConverter::toString() {
        return "BaseNodeConverter";
    }

}
