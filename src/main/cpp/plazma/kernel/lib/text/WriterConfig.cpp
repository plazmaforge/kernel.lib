#include <string>

#include "WriterConfig.h"

namespace text {

    WriterConfig::WriterConfig() {
    }

    WriterConfig::~WriterConfig() {
        //cout << "Destroy WriterConfig: " << endl;
        //destroy();
    }

    ////

    int WriterConfig::normalizeInlineNodeLimit(int value) {
        if (value < 1) {
            return INLINE_NODE_LIMIT; // < 1 -> DEF
        }
        if (value < INLINE_NODE_MIN) {
            return INLINE_NODE_MIN;  // < MIN -> MIN
        }
        if (value > INLINE_NODE_MAX) {
            return INLINE_NODE_MAX;  // > MAX -> MAX
        }
        return value;
    }

    /*
     Return real indent (tab or 1/2 spaces) by config indent
    */
    std::string WriterConfig::normalizeIndentValue(const std::string& value) {
        if (value == "tab") {
            return INDENT_TAB_VALUE;
        }

        // TODO: What about '<n>', <n>space, <n>tab
        if (value == "0") {
            return INDENT_NO_VALUE;
        }
        if (value == "1") {
            return INDENT_SPACE_VALUE;
        }
        if (value == "2") {
            return INDENT_SPACE_VALUE_2;
        }
        if (value == "3") {
            return INDENT_SPACE_VALUE_3;
        }
        if (value == "4") {
            return INDENT_SPACE_VALUE_4;
        }

        return INDENT_DEFAULT_VALUE;
    }

    std::string WriterConfig::normalizeQuoteValue(const std::string& value) {
        return value == QUOTE_SINGLE ? QUOTE_SINGLE_VALUE : QUOTE_DOUBLE_VALUE; // TODO: What about 'none'
    }

    ////

    std::string WriterConfig::toString() {
        return "WriterConfig[]";
    }

}
