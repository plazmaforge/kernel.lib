#include <string>

#include "XmlParserContext.h"

namespace xml {

    XmlParserContext::XmlParserContext() {
        currEvent = 0;
        nodeEvent = 0;

        currTag = nullptr;
        currAttribute = nullptr;

        currNode = nullptr;
    }

    XmlParserContext::~XmlParserContext() {
        if (currTag != nullptr) {
            delete currTag;
        }
        if (currAttribute != nullptr) {
            delete currAttribute;
        }
        //cout << "Destroy XmlParserContext: " << endl;
        //destroy();
    }

    std::string XmlParserContext::toString() {
        return "XmlParserContext[]";
    }

}
