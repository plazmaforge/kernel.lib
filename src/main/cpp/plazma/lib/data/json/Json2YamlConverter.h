#ifndef PLAZMA_LIB_DATA_JSON_JSON2YAML_CONVERTER_H
#define PLAZMA_LIB_DATA_JSON_JSON2YAML_CONVERTER_H

#include "plazma/lib/data/node/BaseNodeConverter.h"

namespace json {

    class Json2YamlConverter: public node::BaseNodeConverter {

        public:
            Json2YamlConverter();
            ~Json2YamlConverter();

            std::string toString();

        protected:
            virtual node::Node* convertDocumentNode(node::Node* node);
            virtual node::Node* convertNode(node::Node* node);

            virtual bool isObjectType(int nodeType);
            virtual bool isArrayType(int nodeType);
            virtual bool isAttributeType(int nodeType);

            virtual int convertNodeType(int nodeType);
            virtual int convertNodeSubType(int nodeSubType);

    };

}
#endif // PLAZMA_LIB_DATA_JSON_JSON2YAML_CONVERTER_H