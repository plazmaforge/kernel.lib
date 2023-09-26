#ifndef PLAZMA_LIB_DATA_JSON_JSON_TOKENIZER_CONTEXT_H
#define PLAZMA_LIB_DATA_JSON_JSON_TOKENIZER_CONTEXT_H

#include <string>

#include "plazma/lib/text/TokenizerContext.h"

using namespace text;

namespace json {

    class JsonTokenizerContext: public TokenizerContext {

        public:
            JsonTokenizerContext();
            
            virtual ~JsonTokenizerContext();

            std::string toString();

    };

}
#endif // PLAZMA_LIB_DATA_JSON_JSON_TOKENIZER_CONTEXT_H