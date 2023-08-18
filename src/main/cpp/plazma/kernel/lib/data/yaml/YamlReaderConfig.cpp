#include "YamlReaderConfig.h"

namespace yaml {

    YamlReaderConfig::YamlReaderConfig() {

        trimAttribute = true;     // TRIM
        skipComment = false;
    }

    YamlReaderConfig::~YamlReaderConfig() {
        //cout << "Destroy YamlReaderConfig: " << endl;
        //destroy();
    }

    ////

    std::string YamlReaderConfig::toString() {
        return "YamlReaderConfig[]";
    }

}
