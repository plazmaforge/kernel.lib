#include <string>
#include "XmlStringAppendable.h"
#include "plazma/kernel/lib/sys/syslib.h"

namespace xml {

    XmlStringAppendable::XmlStringAppendable() {}

    XmlStringAppendable::~XmlStringAppendable() {}

    ////

    void XmlStringAppendable::setColorized(bool colorized) {
        this->colorized = colorized;
    }

    void XmlStringAppendable::append(const std::string &str) {
        buf.append(str);
    }

    void XmlStringAppendable::appendTag(const std::string &str) {
        appendWrap(str, DEFAULT_TAG_COLOR);
    }

    void XmlStringAppendable::appendTagName(const std::string &str) {
        appendWrap(str, DEFAULT_TAG_NAME_COLOR);
    }

    void XmlStringAppendable::appendAttributeName(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    void XmlStringAppendable::appendAttributeValue(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    void XmlStringAppendable::appendText(const std::string &str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    void XmlStringAppendable::appendCDATA(const std::string &str) {
        appendWrap(str, DEFAULT_CDATA_COLOR);
    }

    ////

    void XmlStringAppendable::appendWrap(const std::string &str, int color) {
        if (!colorized) {
            buf.append(str);
            return;
        }
        buf.append(syslib::getPrintColor(color));
        buf.append(str);
        buf.append(syslib::getPrintResetColor());
    }

    std::string XmlStringAppendable::toString() {
        return buf;
    }

}
