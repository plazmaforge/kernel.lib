#ifndef PLAZMA_KERNEL_LIB_DATA_YAML_YAML_CONSOLE_APPENDABLE_H
#define PLAZMA_KERNEL_LIB_DATA_YAML_YAML_CONSOLE_APPENDABLE_H

#include <string>
#include "YamlAppendable.h"

namespace yaml {

    class YamlConsoleAppendable : public YamlAppendable {

        public:

          YamlConsoleAppendable();

          ~YamlConsoleAppendable();

          ////

          void setColorized(bool colorized);

          void append(const std::string &str);

          void appendObject(const std::string &str);

          void appendArray(const std::string &str);

          void appendAttributeName(const std::string &str);

          void appendAttributeValue(const std::string &str);

          void appendText(const std::string &str);

          ////

          void appendWrap(const std::string &str, int color);

        public:

          bool colorized = false;

    };

}
#endif // PLAZMA_KERNEL_LIB_DATA_YAML_YAML_CONSOLE_APPENDABLE_H