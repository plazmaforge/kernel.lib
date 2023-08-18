#include "Xml2JsonConverter.h"
#include "plazma/kernel/lib/data/json/jsonparser.h"
#include "plazma/kernel/lib/data/xml/xmlparser.h"
#include "plazma/kernel/lib/str/strlib.h"

namespace xml {

    Xml2JsonConverter::Xml2JsonConverter() {}

    Xml2JsonConverter::~Xml2JsonConverter() {}

    ////

    std::string Xml2JsonConverter::toString() {
        return "Xml2JsonConverter";
    }

    ////

    node::Node* Xml2JsonConverter::createDocumentNode() {
        return createObjectNode();
    }

    node::Node* Xml2JsonConverter::createObjectNode() {
        node::Node* node = new node::Node();
        node->setType(jsonparser::OBJECT_TYPE);
        return node;
    }

    node::Node* Xml2JsonConverter::createArrayNode() {
        node::Node* node = new node::Node();
        node->setType(jsonparser::ARRAY_TYPE);
        return node;
    }

    node::Node* Xml2JsonConverter::createAttributeNode() {
        node::Node* node = new node::Node();
        node->setType(jsonparser::ATTRIBUTE_TYPE);
        return node;
    }

}
