//#pragma once
#ifndef PLAZMA_KERNEL_DATA_XML_XML_WRITER_CONFIG_H
#define PLAZMA_KERNEL_DATA_XML_XML_WRITER_CONFIG_H

#include <string>

#include "plazma/kernel/lib/text/WriterConfig.h"

using namespace text;

namespace xml {

    class XmlWriterConfig: public WriterConfig {

        public:
            XmlWriterConfig();

            ~XmlWriterConfig();

            int getInlineNodeLimit();

            std::string getIndentValue();

            std::string getAttributeQuoteValue();

            std::string toString();

        public:
            bool inlineFlag;             // Output all text in one line

            bool inlineNodeFlag;         // Output node text in one line

            int inlineNodeLimit;         // Max lenght of text for inline node

            std::string indent;          // Indent node char: 'space', 'tab'

            std::string tagCase;         // Tag name case: 'lower', 'upper', 'camel' ...

            std::string attributeCase;   // Tag name case: 'lower', 'upper', 'camel' ...

            std::string attributeQuote;  // Attribute value quote: 'single', 'double', 'none'

            bool trimAttribute;          // Trim attributes

            bool skipComment;            // Skip all comments
            
            bool skipMeta;               // Skip <?...?>

            bool skipDTD;                // Skip <!DOCTYPE...>

            bool colorized;

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_XML_WRITER_CONFIG_H