#include "Yaml2JsonConverter.h"
#include "plazma/kernel/lib/data/yaml/yamlparser.h"
#include "plazma/kernel/lib/data/json/jsonparser.h"
#include "plazma/kernel/lib/str/strlib.h"

namespace yaml {

    Yaml2JsonConverter::Yaml2JsonConverter() {}

    Yaml2JsonConverter::~Yaml2JsonConverter() {}

    ////

    node::Node* Yaml2JsonConverter::convertDocumentNode(node::Node* original) {
        return convertNode(original); // by default
    }

    node::Node* Yaml2JsonConverter::convertNode(node::Node* original) {
        return convertDefaultNode(original); // by default
    }


    std::string Yaml2JsonConverter::toString() {
        return "Yaml2JsonConverter";
    }

    ////

    bool Yaml2JsonConverter::isObjectType(int nodeType) {
        return nodeType == yamlparser::OBJECT_TYPE;
    }

    bool Yaml2JsonConverter::isArrayType(int nodeType) {
        return nodeType == yamlparser::ARRAY_TYPE;
    }

    bool Yaml2JsonConverter::isAttributeType(int nodeType) {
        return nodeType == yamlparser::ATTRIBUTE_TYPE;
    }

    int Yaml2JsonConverter::convertNodeType(int nodeType) {
        if (nodeType == yamlparser::OBJECT_TYPE) {
            return jsonparser::OBJECT_TYPE;
        } else if (nodeType == yamlparser::ARRAY_TYPE) {
            return jsonparser::ARRAY_TYPE;
        } else if (nodeType == yamlparser::ATTRIBUTE_TYPE) {
            return jsonparser::ATTRIBUTE_TYPE;
        }
        return 0; // by default
    }

    int Yaml2JsonConverter::convertNodeSubType(int nodeSubType) {
        return 0; // by default
    }


}
