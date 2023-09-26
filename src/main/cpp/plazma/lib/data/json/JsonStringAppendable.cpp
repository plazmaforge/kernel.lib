#include <string>
#include "JsonStringAppendable.h"
#include "plazma/lib/sys/syslib.h"

namespace json {

    JsonStringAppendable::JsonStringAppendable() {}

    JsonStringAppendable::~JsonStringAppendable() {}

    ////

    void JsonStringAppendable::setColorized(bool colorized) {
        this->colorized = colorized;
    }

    void JsonStringAppendable::append(const std::string &str) {
        buf.append(str);
    }

    void JsonStringAppendable::appendObject(const std::string &str) {
        appendWrap(str, DEFAULT_OBJECT_COLOR);
    }

    void JsonStringAppendable::appendArray(const std::string &str) {
        appendWrap(str, DEFAULT_ARRAY_COLOR);
    }

    void JsonStringAppendable::appendAttributeName(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    void JsonStringAppendable::appendAttributeValue(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    void JsonStringAppendable::appendText(const std::string &str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    ////

    void JsonStringAppendable::appendWrap(const std::string &str, int color) {
        if (!colorized) {
            buf.append(str);
            return;
        }
        buf.append(syslib::getPrintColor(color));
        buf.append(str);
        buf.append(syslib::getPrintResetColor());
    }

    std::string JsonStringAppendable::toString() {
        return buf;
    }

}
