#include "JsonReaderConfig.h"

namespace json {

    JsonReaderConfig::JsonReaderConfig() {

        trimAttribute = true;     // TRIM
        skipComment = false;
    }

    JsonReaderConfig::~JsonReaderConfig() {
        //cout << "Destroy JsonReaderConfig: " << endl;
        //destroy();
    }

    ////

    std::string JsonReaderConfig::toString() {
        return "JsonReaderConfig[]";
    }

}
