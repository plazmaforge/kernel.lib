#include <string>

#include "YamlTokenizerContext.h"

using namespace yaml;

namespace yaml {

    YamlTokenizerContext::YamlTokenizerContext() {
    }

    YamlTokenizerContext::~YamlTokenizerContext() {
        //cout << "Destroy YamlTokenizerContext: " << this->name << endl;
        //destroy();
    }
    
    void YamlTokenizerContext::updateState(std::string separator) {
        if (separator.empty()) {
            return;
        }

        if ( (separator == "#") ) {
            inTextState = true;  // set
        } else if (separator == "\r\n" || separator == "\r" || separator == "\n") {
            inTextState = false; // reset
        }
        
    }

    std::string YamlTokenizerContext::toString() {
        return "YamlTokenizerContext[]";
    }

}
