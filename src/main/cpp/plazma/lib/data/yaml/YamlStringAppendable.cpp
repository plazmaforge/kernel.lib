#include <string>
#include "YamlStringAppendable.h"
#include "plazma/lib/sys/syslib.h"

namespace yaml {

    YamlStringAppendable::YamlStringAppendable() {}

    YamlStringAppendable::~YamlStringAppendable() {}

    ////

    void YamlStringAppendable::setColorized(bool colorized) {
        this->colorized = colorized;
    }

    void YamlStringAppendable::append(const std::string &str) {
        buf.append(str);
    }

    void YamlStringAppendable::appendObject(const std::string &str) {
        appendWrap(str, DEFAULT_OBJECT_COLOR);
    }

    void YamlStringAppendable::appendArray(const std::string &str) {
        appendWrap(str, DEFAULT_ARRAY_COLOR);
    }

    void YamlStringAppendable::appendAttributeName(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    void YamlStringAppendable::appendAttributeValue(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    void YamlStringAppendable::appendText(const std::string &str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    ////

    void YamlStringAppendable::appendWrap(const std::string &str, int color) {
        if (!colorized) {
            buf.append(str);
            return;
        }
        buf.append(syslib::getPrintColor(color));
        buf.append(str);
        buf.append(syslib::getPrintResetColor());
    }

    std::string YamlStringAppendable::toString() {
        return buf;
    }

}
