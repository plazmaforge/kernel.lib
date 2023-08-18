#ifndef PLAZMA_KERNEL_DATA_YAML_YAML2XML_CONVERTER_H
#define PLAZMA_KERNEL_DATA_YAML_YAML2XML_CONVERTER_H

#include "plazma/kernel/lib/data/xml/Base2XmlConverter.h"

namespace yaml {

    class Yaml2XmlConverter: public xml::Base2XmlConverter {

        public:
            Yaml2XmlConverter();
            ~Yaml2XmlConverter();

            std::string toString();

        protected:
            virtual bool isObjectType(int nodeType);
            virtual bool isArrayType(int nodeType);
            virtual bool isAttributeType(int nodeType);
    };

}
#endif // PLAZMA_KERNEL_DATA_YAML_YAML2XML_CONVERTER_H