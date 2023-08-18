#include <string>

#include "JsonWriterConfig.h"

namespace json {

    JsonWriterConfig::JsonWriterConfig() {
        inlineFlag = false;
        indent = "space";                // 'space', 'tab'
        attributeCase = "";              // 'lower', 'upper', 'camel' ...
        attributeQuote = "";             // 'single', 'double', 'none'
        trimAttribute = false;           // trim = false
        colorized = false;
    }

    JsonWriterConfig::~JsonWriterConfig() {
        //cout << "Destroy JsonWriterConfig: " << endl;
        //destroy();
    }

    ////

    /*
     Return real indent (tab or 1/2 spaces) by config indent
    */
    std::string JsonWriterConfig::getIndentValue() {
        return normalizeIndentValue(indent);
    }

    std::string JsonWriterConfig::getAttributeQuoteValue() {
        return normalizeQuoteValue(attributeQuote);
    }

    std::string JsonWriterConfig::toString() {
        return "JsonWriterConfig[]";
    }

}
