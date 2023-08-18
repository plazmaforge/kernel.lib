//#pragma once
#ifndef PLAZMA_KERNEL_DATA_XML_XML_TOKENIZER_CONTEXT_H
#define PLAZMA_KERNEL_DATA_XML_XML_TOKENIZER_CONTEXT_H

#include <string>

#include "plazma/kernel/lib/text/TokenizerContext.h"

using namespace text;

namespace xml {

    class XmlTokenizerContext: public TokenizerContext {

        public:
        
            XmlTokenizerContext();

            ~XmlTokenizerContext();

            void updateState(std::string separator);

            bool isQuoteChar(char ch);

            bool isSeparatorChar(char ch);

            std::string toString();

    };

}
#endif // PLAZMA_KERNEL_DATA_XML_XML_TOKENIZER_CONTEXT_H