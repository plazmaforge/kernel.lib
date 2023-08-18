#include <string>
#include "JsonConsoleAppendable.h"
#include "plazma/kernel/lib/sys/syslib.h"

namespace json {

    JsonConsoleAppendable::JsonConsoleAppendable() {}

    JsonConsoleAppendable::~JsonConsoleAppendable() {}

    ////

    void JsonConsoleAppendable::setColorized(bool colorized) {
        this->colorized = colorized;
    }

    void JsonConsoleAppendable::append(const std::string &str) {
        syslib::print(str);
    }

    void JsonConsoleAppendable::appendObject(const std::string &str) {
        appendWrap(str, DEFAULT_OBJECT_COLOR);
    }

    void JsonConsoleAppendable::appendArray(const std::string &str) {
        appendWrap(str, DEFAULT_ARRAY_COLOR);
    }

    void JsonConsoleAppendable::appendAttributeName(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    void JsonConsoleAppendable::appendAttributeValue(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    void JsonConsoleAppendable::appendText(const std::string &str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    ////

    void JsonConsoleAppendable::appendWrap(const std::string &str, int color) {
        if (!colorized) {
            syslib::print(str);
            return;
        }
        syslib::print(str, color);
    }

}
