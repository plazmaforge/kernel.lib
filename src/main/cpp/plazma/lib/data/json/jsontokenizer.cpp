#include <string>

#include "jsontokenizer.h"
#include "JsonTokenizerContext.h"
#include "plazma/lib/str/strlib.h"
#include "plazma/lib/text/textlib.h"

using namespace ext;

namespace jsontokenizer {

    // tokenize
    
    StringList* tokenizeJsonFromText(const std::string& input) {
        return tokenizeJsonFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeJsonFromArray(const char* input) {
        int size = strlib::size(input);
        return tokenizeJsonFromArray(input, size);
    }

    StringList* tokenizeJsonFromArray(const char* input, int size) {
        if (input == nullptr || size <= 0) {
            return nullptr;
        }
        
        json::JsonTokenizerContext* context = new json::JsonTokenizerContext();
        context->separators = SEPARATORS;
        context->excludeSeparators = EXCLUDE_SEPARATORS;
        context->flexSeparators = FLEX_SEPARATORS;
        context->startQuotes = START_QUOTES;
        context->endQuotes = END_QUOTES;

        StringList* tokens = textlib::tokenize(context, input, size);
        return tokens;
    }

    std::string getTokensText(StringList* tokens) {
        return textlib::getTokensText(tokens);
    }

}