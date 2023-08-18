#include <string>
#include "YamlConsoleAppendable.h"
#include "plazma/kernel/lib/sys/syslib.h"

namespace yaml {

    YamlConsoleAppendable::YamlConsoleAppendable() {}

    YamlConsoleAppendable::~YamlConsoleAppendable() {}

    ////

    void YamlConsoleAppendable::setColorized(bool colorized) {
        this->colorized = colorized;
    }

    void YamlConsoleAppendable::append(const std::string &str) {
        syslib::print(str);
    }

    void YamlConsoleAppendable::appendObject(const std::string &str) {
        appendWrap(str, DEFAULT_OBJECT_COLOR);
    }

    void YamlConsoleAppendable::appendArray(const std::string &str) {
        appendWrap(str, DEFAULT_ARRAY_COLOR);
    }

    void YamlConsoleAppendable::appendAttributeName(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    void YamlConsoleAppendable::appendAttributeValue(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    void YamlConsoleAppendable::appendText(const std::string &str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    ////

    void YamlConsoleAppendable::appendWrap(const std::string &str, int color) {
        if (!colorized) {
            syslib::print(str);
            return;
        }
        syslib::print(str, color);
    }

}
