#include <string>

#include "JsonTokenizerContext.h"

using namespace json;

namespace json {

    JsonTokenizerContext::JsonTokenizerContext() {
    }

    JsonTokenizerContext::~JsonTokenizerContext() {
        //cout << "Destroy JsonTokenizerContext: " << this->name << endl;
        //destroy();
    }
    
    std::string JsonTokenizerContext::toString() {
        return "JsonTokenizerContext[]";
    }

}
