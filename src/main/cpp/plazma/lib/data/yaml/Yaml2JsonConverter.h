#ifndef PLAZMA_KERNEL_DATA_YAML_YAML2JSON_CONVERTER_H
#define PLAZMA_KERNEL_DATA_YAML_YAML2JSON_CONVERTER_H

#include "plazma/lib/data/node/BaseNodeConverter.h"

namespace yaml {

    class Yaml2JsonConverter: public node::BaseNodeConverter {

        public:
            Yaml2JsonConverter();
            ~Yaml2JsonConverter();

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
#endif // PLAZMA_KERNEL_DATA_YAML_YAML2JSON_CONVERTER_H