#include <string>

#include "ReaderConfig.h"

namespace text {

    ReaderConfig::ReaderConfig() {
    }

    ReaderConfig::~ReaderConfig() {
        //cout << "Destroy ReaderConfig: " << endl;
        //destroy();
    }

    ////

    std::string ReaderConfig::toString() {
        return "ReaderConfig[]";
    }

}
