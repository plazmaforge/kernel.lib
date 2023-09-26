#ifndef PLAZMA_KERNEL_DATA_CSV_CSV_TOKENIZER_H
#define PLAZMA_KERNEL_DATA_CSV_CSV_TOKENIZER_H

#include <string>
#include <vector>

#include "plazma/lib/ext/StringList.h"

using namespace ext;

namespace csvtokenizer {

    const std::vector<std::string> SEPARATORS = {","};

    const std::vector<std::string> EXCLUDE_SEPARATORS = {","};

    const std::vector<std::string> FLEX_SEPARATORS = {}; //{" ", "\r\n", "\r", "\n"};

    const std::vector<std::string> START_QUOTES = {"'", "\""};
    
    const std::vector<std::string> END_QUOTES = {"'", "\""};

    // tokenize

    StringList* tokenizeCsvFromText(const std::string& input);

    StringList* tokenizeCsvFromArray(const char* input, int size);
    
}
#endif // PLAZMA_KERNEL_DATA_CSV_CSV_TOKENIZER_H