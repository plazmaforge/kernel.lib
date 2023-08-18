#ifndef PLAZMA_KERNEL_DATA_JSON_JSON_TOKENIZER_H
#define PLAZMA_KERNEL_DATA_JSON_JSON_TOKENIZER_H

#include <string>
#include <vector>

#include "plazma/kernel/lib/ext/StringList.h"

using namespace ext;

namespace jsontokenizer {

    const std::vector<std::string> SEPARATORS = {"{", "}", "[", "]", ",", ":", " ", "\r\n", "\r", "\n", "\t", "\f"};

    const std::vector<std::string> EXCLUDE_SEPARATORS = {" ", "\r\n", "\r", "\n", "\t", "\f"};

    const std::vector<std::string> FLEX_SEPARATORS = {};

    const std::vector<std::string> START_QUOTES = {"'", "\""};
    
    const std::vector<std::string> END_QUOTES = {"'", "\""};

    // tokenize

    StringList* tokenizeJsonFromText(const std::string& input);

    StringList* tokenizeJsonFromArray(const char* input);

    StringList* tokenizeJsonFromArray(const char* input, int size);

    //

    std::string getTokensText(StringList* tokens);

}
#endif // PLAZMA_KERNEL_DATA_JSON_JSON_TOKENIZER_H