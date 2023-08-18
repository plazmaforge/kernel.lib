#include "XmlReaderConfig.h"

namespace xml {

    XmlReaderConfig::XmlReaderConfig() {

        trimAttribute = true;     // TRIM

        trimText = false;         // RAW
        trimComment = false;      // RAW
        trimCData = false;        // RAW

        skipEmptyText = true;     // SKIP
        skipEmptyComment = false;
        skipEmptyCData = false;

        skipComment = false;
        skipMeta = false;
        skipDTD = false;

    }

    XmlReaderConfig::~XmlReaderConfig() {
        //cout << "Destroy XmlReaderConfig: " << endl;
        //destroy();
    }

    ////

    std::string XmlReaderConfig::toString() {
        return "XmlReaderConfig[]";
    }

}
