#ifndef PLAZMA_KERNEL_LIB_DATA_XML_XML_CONSOLE_APPENDABLE_H
#define PLAZMA_KERNEL_LIB_DATA_XML_XML_CONSOLE_APPENDABLE_H

#include <string>
#include "XmlAppendable.h"

namespace xml {

    class XmlConsoleAppendable : public XmlAppendable {

        public:

          XmlConsoleAppendable();

          ~XmlConsoleAppendable();

          ////

          void setColorized(bool colorized);

          void append(const std::string &str);

          void appendTag(const std::string &str);

          void appendTagName(const std::string &str);

          void appendAttributeName(const std::string &str);

          void appendAttributeValue(const std::string &str);

          void appendText(const std::string &str);

          void appendCDATA(const std::string &str);

          ////

          void appendWrap(const std::string &str, int color);

        public:

          bool colorized = false;

    };

}
#endif // PLAZMA_KERNEL_LIB_DATA_XML_XML_CONSOLE_APPENDABLE_H