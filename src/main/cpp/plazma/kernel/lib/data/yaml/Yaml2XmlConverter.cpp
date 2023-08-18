#include "Yaml2XmlConverter.h"
#include "plazma/kernel/lib/data/yaml/yamlparser.h"
//#include "plazma/kernel/lib/data/xml/xmlparser.h"
#include "plazma/kernel/lib/str/strlib.h"

namespace yaml {

    Yaml2XmlConverter::Yaml2XmlConverter() {}

    Yaml2XmlConverter::~Yaml2XmlConverter() {}

    ////

    std::string Yaml2XmlConverter::toString() {
        return "Yaml2XmlConverter";
    }

    ////

    bool Yaml2XmlConverter::isObjectType(int nodeType) {
        return nodeType == yamlparser::OBJECT_TYPE;
    }

    bool Yaml2XmlConverter::isArrayType(int nodeType) {
        return nodeType == yamlparser::ARRAY_TYPE;
    }

    bool Yaml2XmlConverter::isAttributeType(int nodeType) {
        return nodeType == yamlparser::ATTRIBUTE_TYPE;
    }

}
