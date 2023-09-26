#include <string>

#include "JsonParserContext.h"

namespace json {

    JsonParserContext::JsonParserContext() {

        currEvent = 0;
        prevEvent = 0;
        nodeEvent = 0;

        currAttribute = nullptr;
        currNode = nullptr;
        isEmptyContainer = false;

    }

    JsonParserContext::~JsonParserContext() {
        //currAttribute = nullptr;
        //currNode = nullptr;

        if (currAttribute != nullptr) {
            delete currAttribute;
        }
        //if (currNode != nullptr) {
        //    delete currNode;
        //}

        //cout << "Destroy JsonParserContext: " << endl;
    }

    std::string JsonParserContext::toString() {
        return "JsonParserContext[]";
    }

}
