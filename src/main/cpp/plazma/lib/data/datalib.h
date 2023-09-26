#ifndef PLAZMA_LIB_DATA_DATALIB_H
#define PLAZMA_LIB_DATA_DATALIB_H

#include <string>

#include "plazma/lib/data/node/Node.h"

namespace datalib {

    node::Node* readFromText(const std::string& input);
    
}

#endif // PLAZMA_LIB_DATA_DATALIB_H