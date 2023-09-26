#include "Json2XmlConverter.h"
#include "plazma/lib/data/json/jsonparser.h"
//#include "plazma/lib/data/xml/xmlparser.h"
#include "plazma/lib/str/strlib.h"

namespace json {

    Json2XmlConverter::Json2XmlConverter() {}

    Json2XmlConverter::~Json2XmlConverter() {}

    ////

    std::string Json2XmlConverter::toString() {
        return "Json2XmlConverter";
    }

    ////

    bool Json2XmlConverter::isObjectType(int nodeType) {
        return nodeType == jsonparser::OBJECT_TYPE;
    }

    bool Json2XmlConverter::isArrayType(int nodeType) {
        return nodeType == jsonparser::ARRAY_TYPE;
    }

    bool Json2XmlConverter::isAttributeType(int nodeType) {
        return nodeType == jsonparser::ATTRIBUTE_TYPE;
    }

}
