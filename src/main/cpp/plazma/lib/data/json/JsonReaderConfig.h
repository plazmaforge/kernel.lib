#ifndef PLAZMA_LIB_DATA_JSON_JSON_READER_CONFIG_H
#define PLAZMA_LIB_DATA_JSON_JSON_READER_CONFIG_H

#include <string>

#include "plazma/lib/text/ReaderConfig.h"

using namespace text;

namespace json {

    class JsonReaderConfig: public ReaderConfig {

        public:
            JsonReaderConfig();

            ~JsonReaderConfig();

            std::string toString();

        public:

            bool trimAttribute;    // Trim attributes
            bool skipComment;      // Skip all comments            

    };

}
#endif // PLAZMA_LIB_DATA_JSON_JSON_READER_CONFIG_H