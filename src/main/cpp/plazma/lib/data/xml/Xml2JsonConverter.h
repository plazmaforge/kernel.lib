#ifndef PLAZMA_LIB_DATA_XML_XML2JSON_CONVERTER_H
#define PLAZMA_LIB_DATA_XML_XML2JSON_CONVERTER_H

#include "Xml2BaseConverter.h"

namespace xml {

    class Xml2JsonConverter: public Xml2BaseConverter {

        public:
            Xml2JsonConverter();
            ~Xml2JsonConverter();

            std::string toString();

        protected:            
            virtual node::Node* createDocumentNode();
            virtual node::Node* createObjectNode();
            virtual node::Node* createArrayNode();
            virtual node::Node* createAttributeNode();            

    };

}
#endif // PLAZMA_LIB_DATA_XML_XML2JSON_CONVERTER_H