#ifndef PLAZMA_KERNEL_DATA_XML_XML2BASE_CONVERTER_H
#define PLAZMA_KERNEL_DATA_XML_XML2BASE_CONVERTER_H

#include "plazma/kernel/lib/data/node/BaseNodeConverter.h"

namespace xml {

    class Xml2BaseConverter: public node::BaseNodeConverter {

        public:
            Xml2BaseConverter();
            ~Xml2BaseConverter();

            std::string toString();

        protected:
            virtual node::Node* convertDocumentNode(node::Node* original);
            virtual node::Node* convertNode(node::Node* original);
            //virtual void convertAttrbutes(node::Node* original, node::Node* node);

            virtual std::string getTextType(int nodeType);

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_XML2BASE_CONVERTER_H