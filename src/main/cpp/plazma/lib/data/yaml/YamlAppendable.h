#ifndef PLAZMA_KERNEL_LIB_DATA_YAML_YAML_APPENDABLE_H
#define PLAZMA_KERNEL_LIB_DATA_YAML_YAML_APPENDABLE_H

#include <string>
#include "plazma/lib/ext/Appendable.h"
#include "plazma/lib/sys/syslib.h"

namespace yaml {

  const int DEFAULT_OBJECT_COLOR = COLOR_GREEN;               // "\x1b[92m";
  const int DEFAULT_ARRAY_COLOR = COLOR_LIGHT_BLUE;          // "\x1b[36m";
  const int DEFAULT_ATTRIBUTE_NAME_COLOR = COLOR_MAGENTA;     // "\x1b[35m";
  const int DEFAULT_ATTRIBUTE_VALUE_COLOR = COLOR_DARK_GREEN; // "\x1b[32m";
  const int DEFAULT_TEXT_COLOR = COLOR_LIGHT_BLUE; //COLOR_BLUE;                  // "\x1b[94m";
  const int DEFAULT_CDATA_COLOR = COLOR_ORANGE;               // "\x1b[33m";

    class YamlAppendable : public ext::Appendable {

        public:

          YamlAppendable();

          virtual ~YamlAppendable();

          ////

          virtual void setColorized(bool colorized) = 0;

          virtual void appendObject(const std::string &str) = 0;

          virtual void appendArray(const std::string &str) = 0;

          virtual void appendAttributeName(const std::string &str) = 0;

          virtual void appendAttributeValue(const std::string &str) = 0;

          virtual void appendText(const std::string &str) = 0;

    };

}
#endif // PLAZMA_KERNEL_LIB_DATA_YAML_YAML_APPENDABLE_H