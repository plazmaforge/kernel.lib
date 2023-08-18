//#pragma once
#ifndef PLAZMA_KERNEL_DATA_JSON_JSON_WRITER_CONFIG_H
#define PLAZMA_KERNEL_DATA_JSON_JSON_WRITER_CONFIG_H

#include <string>

#include "plazma/kernel/lib/text/WriterConfig.h"

using namespace text;

namespace json {

    class JsonWriterConfig: public WriterConfig {

        public:
            JsonWriterConfig();
            ~JsonWriterConfig();

            std::string getIndentValue();
            std::string getAttributeQuoteValue();

            std::string toString();

        public:
            bool inlineFlag;             // Output all text in one line
            std::string indent;          // Indent node char: 'space', 'tab'
            std::string attributeCase;   // Tag name case: 'lower', 'upper', 'camel' ...
            std::string attributeQuote;  // Attribute value quote: 'single', 'double', 'none'
            bool trimAttribute;          // Trim attribute value
            bool colorized;
            
    };

}
#endif // PLAZMA_KERNEL_DATA_JSON_JSON_WRITER_CONFIG_H