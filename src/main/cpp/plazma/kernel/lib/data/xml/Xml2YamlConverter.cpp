#include "Xml2YamlConverter.h"
#include "plazma/kernel/lib/data/yaml/yamlparser.h"
#include "plazma/kernel/lib/data/xml/xmlparser.h"
#include "plazma/kernel/lib/str/strlib.h"

namespace xml {

    Xml2YamlConverter::Xml2YamlConverter() {}

    Xml2YamlConverter::~Xml2YamlConverter() {}

    ////

    std::string Xml2YamlConverter::toString() {
        return "Xml2YamlConverter";
    }

    ////

    node::Node* Xml2YamlConverter::createDocumentNode() {
        return createObjectNode();
    }

    node::Node* Xml2YamlConverter::createObjectNode() {
        node::Node* node = new node::Node();
        node->setType(yamlparser::OBJECT_TYPE);
        return node;
    }

    node::Node* Xml2YamlConverter::createArrayNode() {
        node::Node* node = new node::Node();
        node->setType(yamlparser::ARRAY_TYPE);
        return node;
    }

    node::Node* Xml2YamlConverter::createAttributeNode() {
        node::Node* node = new node::Node();
        node->setType(yamlparser::ATTRIBUTE_TYPE);
        return node;
    }

}
