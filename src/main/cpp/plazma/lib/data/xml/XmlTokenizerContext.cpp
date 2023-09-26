#include "XmlTokenizerContext.h"

namespace xml {

    XmlTokenizerContext::XmlTokenizerContext() {
    }

    XmlTokenizerContext::~XmlTokenizerContext() {
        //cout << "Destroy XmlTokenizerContext: " << this->name << endl;
        //destroy();
    }

    void XmlTokenizerContext::updateState(std::string separator) {
        if (separator.empty()) {
            return;
        }

        // Implementation for XML
        if (separator.at(0) == '<' && separator != "<![CDATA[" && separator != "<!--") {
            inParseState = true;  // set
        } else if (separator.at(0) == '>' || separator == "<![CDATA[" || separator == "<!--") {
            inParseState = false; // reset
        }

        if ( (separator == "<![CDATA[" || separator == "<!--" || separator == "[") ) {
            inTextState = true;  // set
        } else if (separator == "]]>" || separator == "-->" || separator == "]") {
            inTextState = false; // reset
        }
        
    }


    bool XmlTokenizerContext::isQuoteChar(char ch) {
        return (ch == '<'
        || ch == '\'' 
        || ch == '"' 
        || ch == '-' 
        || ch == ']');
    }

    bool XmlTokenizerContext::isSeparatorChar(char ch) {
        return (ch == '<' 
        || ch == '>' 
        || ch == '\'' 
        || ch == '"' 
        || ch == ' ' 
        || ch == '=' 
        //|| ch == '?' 
        || ch == '-' 
        || ch == '\r'
        || ch == '\n'
        || ch == '/'
        || ch == ']');
    }

    std::string XmlTokenizerContext::toString() {
        return "XmlTokenizerContext[]";
    }

}
