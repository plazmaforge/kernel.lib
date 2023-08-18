//#pragma once
#ifndef PLAZMA_KERNEL_LIB_TEXT_WRITER_CONFIG_H
#define PLAZMA_KERNEL_LIB_TEXT_WRITER_CONFIG_H

#include <string>

namespace text {

    const int INLINE_NODE_LIMIT = 80;
    const int INLINE_NODE_MIN = 10;
    const int INLINE_NODE_MAX = 1000;

    const std::string INDENT_SPACE = "space";
    const std::string INDENT_TAB = "tab";
    const std::string INDENT_DEFAULT= INDENT_SPACE;

    const std::string INDENT_TAB_VALUE = "\t";
    const std::string INDENT_NO_VALUE = "";
    const std::string INDENT_SPACE_VALUE = " ";
    const std::string INDENT_SPACE_VALUE_2 = "  ";
    const std::string INDENT_SPACE_VALUE_3 = "   ";
    const std::string INDENT_SPACE_VALUE_4 = "    ";
    const std::string INDENT_DEFAULT_VALUE = INDENT_SPACE_VALUE_2;


    const std::string QUOTE_NONE = "none";
    const std::string QUOTE_SINGLE = "single";
    const std::string QUOTE_DOUBLE = "double";
    const std::string QUOTE_DEFAULT = QUOTE_DOUBLE;

    const std::string QUOTE_NONE_VALUE = "";
    const std::string QUOTE_SINGLE_VALUE = "'";
    const std::string QUOTE_DOUBLE_VALUE = "\"";
    const std::string QUOTE_DEFAULT_VALUE = QUOTE_DOUBLE_VALUE;

    class WriterConfig {

        public:
            WriterConfig();
            ~WriterConfig();

            virtual std::string toString();

        protected:
            int normalizeInlineNodeLimit(int value);
            std::string normalizeIndentValue(const std::string& value);
            std::string normalizeQuoteValue(const std::string& value);

    };

}

#endif // PLAZMA_KERNEL_LIB_TEXT_WRITER_CONFIG_H