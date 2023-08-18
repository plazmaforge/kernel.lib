#ifndef PLAZMA_KERNEL_DATA_XML_XML2YAML_CONVERTER_H
#define PLAZMA_KERNEL_DATA_XML_XML2YAML_CONVERTER_H

#include "Xml2BaseConverter.h"

namespace xml {

    class Xml2YamlConverter: public Xml2BaseConverter {

        public:
            Xml2YamlConverter();
            ~Xml2YamlConverter();

            std::string toString();

        protected:            
            virtual node::Node* createDocumentNode();
            virtual node::Node* createObjectNode();
            virtual node::Node* createArrayNode();
            virtual node::Node* createAttributeNode();

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_XML2YAML_CONVERTER_H