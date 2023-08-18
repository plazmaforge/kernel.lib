#ifndef PLAZMA_KERNEL_DATA_XML_BASE2XML_CONVERTER_H
#define PLAZMA_KERNEL_DATA_XML_BASE2XML_CONVERTER_H

#include "plazma/kernel/lib/data/node/BaseNodeConverter.h"

namespace xml {

    class Base2XmlConverter: public node::BaseNodeConverter {

        public:
            Base2XmlConverter();
            ~Base2XmlConverter();

            std::string toString();

        protected:
            virtual node::Node* convertDocumentNode(node::Node* original);
            virtual node::Node* convertNode(node::Node* original);

            virtual bool isSimpleChildren(node::Node* node);
            virtual std::string toChildrenText(node::Node* node);

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_BASE2XML_CONVERTER_H