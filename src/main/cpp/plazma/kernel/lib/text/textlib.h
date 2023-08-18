#ifndef PLAZMA_KERNEL_LIB_TEXT_TEXTLIB_H
#define PLAZMA_KERNEL_LIB_TEXT_TEXTLIB_H

#include "plazma/kernel/lib/ext/StringList.h"
#include "TokenizerContext.h"

using namespace ext;
using namespace text;

namespace textlib {

    StringList* tokenize(TokenizerContext* context, const char* input, int size);

    std::string getLinesText(StringList* lines);

    std::string getLinesText(StringList* lines, std::string prefix, std::string suffix, bool convert);

    std::string getTokensText(StringList* tokens);

        
}

#endif // PLAZMA_KERNEL_LIB_TEXT_TEXTLIB_H