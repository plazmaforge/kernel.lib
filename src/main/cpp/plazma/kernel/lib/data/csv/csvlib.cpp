
#include <string>

#include "csvlib.h"
#include "csvtokenizer.h"
#include "plazma/kernel/lib/str/strlib.h"

using namespace ext;

namespace csvlib {

    // tokenize

    StringList* tokenizeCsvFromText(const std::string& input) {
        return csvtokenizer::tokenizeCsvFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeCsvFromArray(const char* input) {
        int size = strlib::size(input);
        return tokenizeCsvFromArray(input, size);
    }

    StringList* tokenizeCsvFromArray(const char* input, int size) {
        return csvtokenizer::tokenizeCsvFromArray(input, size);
    }

}