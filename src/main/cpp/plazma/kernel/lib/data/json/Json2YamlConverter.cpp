#include "Json2YamlConverter.h"
#include "plazma/kernel/lib/data/json/jsonparser.h"
#include "plazma/kernel/lib/data/yaml/yamlparser.h"
#include "plazma/kernel/lib/str/strlib.h"

namespace json {

    Json2YamlConverter::Json2YamlConverter() {}

    Json2YamlConverter::~Json2YamlConverter() {}

    ////

    node::Node* Json2YamlConverter::convertDocumentNode(node::Node* original) {
        return convertNode(original); // by default
    }

    node::Node* Json2YamlConverter::convertNode(node::Node* original) {
        return convertDefaultNode(original); // by default
    }


    std::string Json2YamlConverter::toString() {
        return "Json2YamlConverter";
    }

    ////

    bool Json2YamlConverter::isObjectType(int nodeType) {
        return nodeType == jsonparser::OBJECT_TYPE;
    }

    bool Json2YamlConverter::isArrayType(int nodeType) {
        return nodeType == jsonparser::ARRAY_TYPE;
    }

    bool Json2YamlConverter::isAttributeType(int nodeType) {
        return nodeType == jsonparser::ATTRIBUTE_TYPE;
    }

    int Json2YamlConverter::convertNodeType(int nodeType) {
        if (nodeType == jsonparser::OBJECT_TYPE) {
            return yamlparser::OBJECT_TYPE;
        } else if (nodeType == jsonparser::ARRAY_TYPE) {
            return yamlparser::ARRAY_TYPE;
        } else if (nodeType == jsonparser::ATTRIBUTE_TYPE) {
            return yamlparser::ATTRIBUTE_TYPE;
        }
        return 0; // by default
    }

    int Json2YamlConverter::convertNodeSubType(int nodeSubType) {
        return 0; // by default
    }


}
