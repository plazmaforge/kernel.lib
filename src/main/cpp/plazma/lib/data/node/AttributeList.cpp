#include <string>
#include <algorithm>

#include "AttributeList.h"

// https://stackoverflow.com/questions/42072073/equivalent-linkedhashmap-in-c
// https://codereview.stackexchange.com/questions/197814/a-c-implementation-of-linkedhashmap

// https://github.com/simongog/sdsl-lite

namespace node {

    AttributeList::AttributeList() {
        this->names = nullptr;
        this->values = nullptr;
    }

    AttributeList::~AttributeList() {
        //cout << "Destroy AttributeList" << endl;
        destroy();
    }

    //// UTILS

    int indexOf(std::vector<std::string> &vector, const std::string& element) {
        auto it = std::find(vector.begin(), vector.end(), element);
        return (it != vector.end()) ? (it - vector.begin()) : -1;
    }

    bool contains(std::vector<std::string> &vector, const std::string& value) {
        return indexOf(vector, value) != -1;
    }

    void removeByIndex(std::vector<std::string> &vector, const int index) {
        vector.erase(vector.begin() + index);
    }

    ////

    void AttributeList::addAttribute(const std::string &name, const std::string &value) {
        if (names == nullptr) {
            names = new std::vector<std::string>();
            values = new std::vector<std::string>();
        }
        int index = indexOf(*names, name);
        if (index == -1) {
            // Not found: new
            names->push_back(name);
            values->push_back(value);
        } else {
            // Found: set
            names->at(index) = name;
            values->at(index) = value;
        }
    }

    void AttributeList::removeAttribute(const std::string &name) {
        if (names == nullptr) {
            return;
        }
        int index = indexOf(*names, name);
        if (index == -1) {
            // Not found
            return;
        }
        removeByIndex(*names, index);
        removeByIndex(*values, index);
    }

    std::string AttributeList::getAttribute(const std::string &name) {
        return getAttributeValue(name);
    }

    std::string AttributeList::getAttributeName(const int index) {
        if (names == nullptr) {
            return ""; // null
        }
        if (index < 0 && index >= names->size()) {
            // Not found
            return ""; // null
        }
        return names->at(index);
    }

    std::string AttributeList::getAttributeValue(const int index) {
        if (values == nullptr) {
            return ""; // null
        }
        if (index < 0 && index >= values->size()) {
            // Not found
            return ""; // null
        }
        return values->at(index);
    }

    std::string AttributeList::getAttributeValue(const std::string &name) {
        if (values == nullptr) {
            return ""; // null
        }
        int index = indexOf(*names, name);
        if (index == -1) {
            // Not found
            return ""; // null
        }
        return values->at(index);
    }

    bool AttributeList::hasAttributes() {
        return getAttributeCount() > 0;
    }

    int AttributeList::getAttributeCount() {
        return names == nullptr ? 0 : names->size();
    }

    ////

    std::string AttributeList::toString() {
        return "AttributeList[attributes=" + std::to_string(getAttributeCount()) + "]";
    }

    ////

    void AttributeList::destroy() {
        if (this->names != nullptr) {
            delete this->names;
        }
        if (this->values != nullptr) {
            delete this->values;
        }
    }

}
