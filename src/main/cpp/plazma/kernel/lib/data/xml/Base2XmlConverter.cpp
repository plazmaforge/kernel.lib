#include "Base2XmlConverter.h"
#include "plazma/kernel/lib/data/xml/xmlparser.h"
#include "plazma/kernel/lib/str/strlib.h"

namespace xml {

    Base2XmlConverter::Base2XmlConverter() {}

    Base2XmlConverter::~Base2XmlConverter() {}

    ////

    std::string Base2XmlConverter::toString() {
        return "Base2XmlConverter";
    }

    ////

    node::Node* Base2XmlConverter::convertDocumentNode(node::Node* original) {
        // TODO
        return convertNode(original);
    }

    ////    

    node::Node* Base2XmlConverter::convertNode(node::Node* original) {

        bool unwrapArray = true;
        bool unwrapObject = true;

        ///////////////////////////////////////////////////////////

        if (original == nullptr) {
            return nullptr;
        }

        int nodeType = original->getType();
        //if (isSkipXmlNode(nodeType)) {
        //    return nullptr;
        //}

        node::Node* node = nullptr;

        ///////////////////////////////////////////////////////////

        bool hasAttributes = original->hasAttributes();
        bool hasChildren = original->hasChildren();
        //bool singleNode = !hasAttributes && !hasChildren;

        node::Node* originalChild = nullptr;
        node::Node* child = nullptr;

        std::string name;
        std::string text;

        node = new node::Node();

        if (isAttributeType(nodeType)) {

            name = original->getName();

            name = strlib::unquote(name);

            node->setName(name);

            originalChild = hasChildren ? original->getChild(0) : nullptr;

            // Simple attribute value: 10, 3.14, 'Hello'
            if (originalChild == nullptr) {
                text = original->getText();
                node->setText(text);
                return node;
            }

            ////

            if (unwrapArray && isArrayType(originalChild->getType()) && originalChild->hasChildren()) {

                // unwrap #array
                
                if (isSimpleChildren(originalChild)) {
                    text = toChildrenText(originalChild);
                    node->setText(text);
                    return node;
                }

            }

            if (unwrapObject && isObjectType(originalChild->getType()) /*&& originalChild->getChildCount() == 1*/) {

                // unwrap #object
                
                convertChildren(originalChild, node);
                return node;                                
            }

            ////
            
            child = convertNode(originalChild);

            if (child != nullptr) {
                node->addChild(child);
            }

        } else if (isObjectType(nodeType)) {

            /*
            if (node->getParent() == nullptr && original->getChildCount() == 1) {

                node->setName("#object-2");

                original = original->getChild(0);

                child = convertBaseNode(original);

                if (child != nullptr) {
                    node->addChild(child);
                }

                return node;

                
                //int t = original->getChild(0)->getType();
                //if (isAttributeType(t)) {
                //    original = original->getChild(0);
                //    delete node;
                //    node = convertBaseNode(node);
                //    return node;
                //}
                
            }
            */

            node->setName("#object");

            if (hasChildren) {
                convertChildren(original, node);
            }            

        } else if (isArrayType(nodeType)) {

            node->setName("#array");

            if (hasChildren) {
                convertChildren(original, node);
            }

        } else {

            if (hasChildren) {
                node->setName("#container");
                convertChildren(original, node);

            } else {
                node->setName("#text");
                text = original->getText();
                node->setText(text);
            }

        }

        return node;

        ///////////////////////////////////////////////////////////

    }

    bool Base2XmlConverter::isSimpleChildren(node::Node* node) {
        if (node == nullptr) {
            return true;
        }

        int count = node->getChildCount();
        node::Node* child = nullptr;

        for (int i = 0; i < count; i++) {
            child = node->getChild(i);
            if (child->hasChildren()) {
                return false;
            }
        }
        return true;
    }

    std::string Base2XmlConverter::toChildrenText(node::Node* node) {
        std::string text;
        if (node == nullptr) {
            return text;
        }

        int count = node->getChildCount();
        node::Node* child = nullptr;

        for (int i = 0; i < count; i++) {
            child = node->getChild(i);
            if (i > 0) {
                text.append(", ");
            }
            text.append(child->getText());
        }

        return text;
    }


}
