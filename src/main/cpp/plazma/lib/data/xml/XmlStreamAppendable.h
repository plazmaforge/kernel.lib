#ifndef PLAZMA_KERNEL_LIB_DATA_XML_XML_STREAM_APPENDABLE_H
#define PLAZMA_KERNEL_LIB_DATA_XML_XML_STREAM_APPENDABLE_H

#include <string>
#include "XmlAppendable.h"

namespace xml {

    class XmlStreamAppendable : public XmlAppendable {

        public:

          XmlStreamAppendable();

          ~XmlStreamAppendable();

          void append(const std::string &str);

        //private:

        //  std::string buf;

    };

}
#endif // PLAZMA_KERNEL_LIB_DATA_XML_XML_STREAM_APPENDABLE_H