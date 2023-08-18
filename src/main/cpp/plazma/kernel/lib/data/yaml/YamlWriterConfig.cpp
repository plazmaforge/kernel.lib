#include <string>

#include "YamlWriterConfig.h"

namespace yaml {

    YamlWriterConfig::YamlWriterConfig() {
        attributeCase = "";              // 'lower', 'upper', 'camel' ...
        attributeQuote = "";             // 'single', 'double', 'none'
        trimAttribute = false;           // trim = false
        colorized = false;
    }

    YamlWriterConfig::~YamlWriterConfig() {
        //cout << "Destroy YamlWriterConfig: " << endl;
        //destroy();
    }

    ////

    std::string YamlWriterConfig::getAttributeQuoteValue() {
        return normalizeQuoteValue(attributeQuote);
    }

    std::string YamlWriterConfig::toString() {
        return "YamlWriterConfig[]";
    }

}
