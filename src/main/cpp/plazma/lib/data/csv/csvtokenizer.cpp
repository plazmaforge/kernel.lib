#include <iostream>
#include <fstream>
#include <sstream>

#include <string>

#include "csvtokenizer.h"
#include "plazma/lib/text/textlib.h"

using namespace ext;

namespace csvtokenizer {

    // tokenize
    
    StringList* tokenizeCsvFromText(const std::string& input) {
        return tokenizeCsvFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeCsvFromArray(const char* input, int size) {
        if (input == nullptr || size <= 0) {
            return nullptr;
        }

        // TODO: Use CsvTokenizerContext
        TokenizerContext* context = new TokenizerContext();
        context->separators = SEPARATORS;
        context->excludeSeparators = EXCLUDE_SEPARATORS;
        context->flexSeparators = FLEX_SEPARATORS;
        context->startQuotes = START_QUOTES;
        context->endQuotes = END_QUOTES;

        StringList* tokens = textlib::tokenize(context, input, size);
        return tokens;
    }

}