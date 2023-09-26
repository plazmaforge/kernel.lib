#ifndef PLAZMA_KERNEL_DATA_JSON_JSON2XML_CONVERTER_H
#define PLAZMA_KERNEL_DATA_JSON_JSON2XML_CONVERTER_H

#include "plazma/lib/data/xml/Base2XmlConverter.h"

namespace json {

    class Json2XmlConverter: public xml::Base2XmlConverter {

        public:
            Json2XmlConverter();
            ~Json2XmlConverter();
            
            std::string toString();

        protected:
            virtual bool isObjectType(int nodeType);
            virtual bool isArrayType(int nodeType);
            virtual bool isAttributeType(int nodeType);

    };

}
#endif // PLAZMA_KERNEL_DATA_JSON_JSON2XML_CONVERTER_H