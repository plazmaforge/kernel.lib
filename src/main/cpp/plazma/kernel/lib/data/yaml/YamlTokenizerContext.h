//#pragma once
#ifndef PLAZMA_KERNEL_DATA_YAML_YAML_TOKENIZER_CONTEXT_H
#define PLAZMA_KERNEL_DATA_YAML_YAML_TOKENIZER_CONTEXT_H

#include <string>

#include "plazma/kernel/lib/text/TokenizerContext.h"

using namespace text;

namespace yaml{

    class YamlTokenizerContext: public TokenizerContext {

        public:
            YamlTokenizerContext();
            
            ~YamlTokenizerContext();

            void updateState(std::string separator);

            std::string toString();

    };

}
#endif // PLAZMA_KERNEL_DATA_YAML_YAML_TOKENIZER_CONTEXT_H