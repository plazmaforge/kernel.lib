#include <string>

#include "yamltokenizer.h"
#include "YamlTokenizerContext.h"
#include "plazma/lib/str/strlib.h"
#include "plazma/lib/text/textlib.h"

using namespace ext;

namespace yamltokenizer {

    // tokenize
    
    StringList* tokenizeYamlFromText(const std::string& input) {
        return tokenizeYamlFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeYamlFromArray(const char* input) {
        int size = strlib::size(input);
        return tokenizeYamlFromArray(input, size);
    }

    StringList* tokenizeYamlFromArray(const char* input, int size) {
        if (input == nullptr || size <= 0) {
            return nullptr;
        }
        
        yaml::YamlTokenizerContext* context = new yaml::YamlTokenizerContext();
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