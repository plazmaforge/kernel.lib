#include <string>
#include "XmlConsoleAppendable.h"
#include "plazma/kernel/lib/sys/syslib.h"

namespace xml {

    XmlConsoleAppendable::XmlConsoleAppendable() {}

    XmlConsoleAppendable::~XmlConsoleAppendable() {}

    ////

    void XmlConsoleAppendable::setColorized(bool colorized) {
        this->colorized = colorized;
    }

    void XmlConsoleAppendable::append(const std::string &str) {
        syslib::print(str);
    }

    void XmlConsoleAppendable::appendTag(const std::string &str) {
        appendWrap(str, DEFAULT_TAG_COLOR);
    }

    void XmlConsoleAppendable::appendTagName(const std::string &str) {
        appendWrap(str, DEFAULT_TAG_NAME_COLOR);
    }

    void XmlConsoleAppendable::appendAttributeName(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_NAME_COLOR);
    }

    void XmlConsoleAppendable::appendAttributeValue(const std::string &str) {
        appendWrap(str, DEFAULT_ATTRIBUTE_VALUE_COLOR);
    }

    void XmlConsoleAppendable::appendText(const std::string &str) {
        appendWrap(str, DEFAULT_TEXT_COLOR);
    }

    void XmlConsoleAppendable::appendCDATA(const std::string &str) {
        appendWrap(str, DEFAULT_CDATA_COLOR);
    }

    ////

    void XmlConsoleAppendable::appendWrap(const std::string &str, int color) {
        if (!colorized) {
            syslib::print(str);
            return;
        }
        syslib::print(str, color);
    }

}
