#ifndef PLAZMA_LIB_DATA_CSV_CSVLIB_H
#define PLAZMA_LIB_DATA_CSV_CSVLIB_H

#include <string>

#include "plazma/lib/ext/StringList.h"

using namespace ext;

namespace csvlib {

    // tokenize

    StringList* tokenizeCsvFromText(const std::string& input);

    StringList* tokenizeCsvFromArray(const char* input);

    StringList* tokenizeCsvFromArray(const char* input, int size);
    
}
#endif // PLAZMA_LIB_DATA_CSV_CSVLB_H