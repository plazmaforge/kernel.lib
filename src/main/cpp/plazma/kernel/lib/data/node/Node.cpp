#include <algorithm>
#include "Node.h"

// https://stackoverflow.com/questions/42072073/equivalent-linkedhashmap-in-c
// https://codereview.stackexchange.com/questions/197814/a-c-implementation-of-linkedhashmap

// https://github.com/simongog/sdsl-lite

namespace node {

    Node::Node() {
        this->type = 0;
        this->subType = 0;
        this->children = nullptr;
        this->attributes = nullptr;
        this->indent = 0;
    }

    Node::~Node() {
        //cout << "Destroy Node: " << this->name << endl;
        destroy();
    }

    ////

    std::string Node::getName() {
        return this->name;
    }

    void Node::setName(const std::string &name) {
        this->name = name;
    }

    bool Node::hasName() {
        return !this->name.empty();
    }

    ext::ustring Node::getName2() {
        return this->name2;
    }

    void Node::setName2(const ext::ustring &name) {
        this->name2 = name;
    }

    std::string Node::getText() {
        return this->text;
    }

    void Node::setText(const std::string &text) {
        this->text = text;
    }

    bool Node::hasText() {
        return !this->text.empty();
    }

    int Node::getType() {
        return this->type;
    }

    void Node::setType(int type) {
        this->type = type;
    }

    int Node::getSubType() {
        return this->subType;
    }

    void Node::setSubType(int subType) {
        this->subType = subType;
    }

    int Node::getParentType() {
        if (this->parent == nullptr) {
            return 0;
        }
        return this->parent->type;
    }

    int Node::getParentSubType() {
        if (this->parent == nullptr) {
            return 0;
        }
        return this->parent->subType;
    }

    //

    Node* Node::getParent() {
        return this->parent;
    }

    void Node::setParent(Node* parent) {
        this->parent = parent;
    }

    //// UTILS

    //int indexOf(Node* element) {
    //    auto it = find(children->begin(), children->end(), element);
    //    return (it != children->end()) ? (it - children->begin()) : -1;
    //}

    //bool contains(vector<string> &vector, const string& value) {
    //    return indexOf(vector, value) != -1;
    //}

    //void removeByIndex(vector<string> &vector, const int index) {
    //    vector.erase(vector.begin() + index);
    //}


    //

    void Node::addChild(Node* child) {
        if (child == nullptr) {
            // TODO: error
            return;
        }
        child->setParent(this);
        if (children == nullptr) {
            children = new std::vector<Node*>();
        }
        children->push_back(child);
    }

    void Node::removeChild(Node* child) {
        if (child == nullptr) {
            // TODO: error
            return;
        }
        if (children == nullptr) {
            return;
        }
        //TODO: WIN
        //std::remove(children->begin(), children->end(), child);
        children->erase(std::remove(children->begin(), children->end(), child), children->end());
    }

    Node* Node::getChild(int index) {
        if (index < 0 || index >= getChildCount()) {
            return nullptr;
        }
        return children->at(index);
    }

    Node* Node::firstChild() {
        if (!hasChildren()) {
            return nullptr;
        }
        return children->at(0);
    }

    Node* Node::lastChild() {
        if (!hasChildren()) {
            return nullptr;
        }
        return children->at(getChildCount() - 1);
    }

    std::vector<Node*>* Node::getChildren() {
        return children;
    }

    bool Node::hasChildren() {
        return children != nullptr && !children->empty();
    }

    int Node::getChildCount() {
        return children == nullptr ? 0 : children->size();
    }

    //

    void Node::addAttribute(const std::string &name, const std::string &value) {
        if (attributes == nullptr) {
            attributes = new AttributeList();
        }
        attributes->addAttribute(name, value);
    }

    void Node::removeAttribute(const std::string &name) {
        if (attributes == nullptr) {
            return;
        }
        attributes->removeAttribute(name);
    }

    std::string Node::getAttributeName(const int index) {
        if (attributes == nullptr) {
            return ""; // nullptr
        }
        return attributes->getAttributeName(index);
    }

    std::string Node::getAttributeValue(const int index) {
        if (attributes == nullptr) {
            return ""; // nullptr
        }
        return attributes->getAttributeValue(index);
    }

    bool Node::hasAttributes() {
        return attributes == nullptr ? false : attributes->hasAttributes();
    }

    int Node::getAttributeCount() {
        return attributes == nullptr ? 0 : attributes->getAttributeCount();
    }

    ////

    std::string Node::toString() {
        return "Node [name=" + name
        + ", type=" + std::to_string(type) 
        + ", parentType=" + std::to_string(getParentType())
        + ", attributes=" + std::to_string(getAttributeCount()) 
        + ", children=" + std::to_string(getChildCount())
        + "]";
    }

    ////

    void Node::destroy() {
        destroyAttributes();
        destroyChildren();
    }

    void Node::destroyAttributes() {
        if (attributes == nullptr) {
            return;
        }
        delete attributes;
    }

    void Node::destroyChildren() {
        if (children == nullptr) {
            return;
        }
        for (Node *child : *children) {
            if (child == nullptr) {
                continue;
            }
            delete child;
        }
        delete children;
    }

    Node* Node::clone() {
        
        Node* node = new Node();

        node->setType(this->getType());
        node->setSubType(this->getSubType());
        node->setName(this->getName());
        node->setName2(this->getName2()); // TODO
        node->setText(this->getText());
        node->setIndent(this->getIndent());

        if (this->hasAttributes()) {
            int count = this->getAttributeCount();
            for (int i = 0; i < count; i++) {
                std::string name = this->getAttributeName(i);
                std::string value = this->getAttributeValue(i);
                node->addAttribute(name, value);
            }

        }

        if (this->hasChildren()) {
            int count = this->getChildCount();
            for (int i = 0; i < count; i++) {
                Node* child = this->getChild(i);
                node->addChild(child == nullptr ? nullptr : child->clone());
            }
        }

        return node;
    }

    int Node::getLevel()  { // 0 - getParent() == nullptr
        if (parent == nullptr) {
            return 0;
        }
        
        //Node* next = parent;
        //int level = 0;
        //while (next != nullptr) {
        //    level++;
        //    next = next->getParent();
        //}

        Node* next = this;
        int level = 0;
        while ( (next = next->getParent()) != nullptr) {
            level++;
        }

        return level;
    }

    void Node::setIndent(int indent) {
        this->indent = indent;
    }
    
    int Node::getIndent() {
        return this->indent;
    }

    bool Node::isType(int type, int subType) {
        return this->getType() == type && this->getSubType() == subType;
    }

    bool Node::isType(int type) {
        return this->getType() == type;
    }

    bool Node::isSubType(int subType) {
        return this->getSubType() == subType;
    }


    bool Node::isParentType(int type) {
        if (this->parent == nullptr) {
            return false;
        }
        return this->parent->getType() == type;
    }

    bool Node::isParentType(int type, int subType) {
        if (this->parent == nullptr) {
            return false;
        }
        return this->parent->getType() == type && this->parent->getSubType() == subType;
    }

    bool Node::isParentSubType(int subType) {
        if (this->parent == nullptr) {
            return false;
        }
        return this->parent->getSubType() == subType;
    }


}
