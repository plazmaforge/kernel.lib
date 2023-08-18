#ifndef PLAZMA_KERNEL_DATA_YAML_YAML_READER_CONFIG_H
#define PLAZMA_KERNEL_DATA_YAML_YAML_READER_CONFIG_H

#include <string>

#include "plazma/kernel/lib/text/ReaderConfig.h"

using namespace text;

namespace yaml {

    class YamlReaderConfig: public ReaderConfig {

        public:
            YamlReaderConfig();

            ~YamlReaderConfig();

            std::string toString();

        public:

            bool trimAttribute;    // Trim attributes
            bool skipComment;      // Skip all comments            

    };

}
#endif // PLAZMA_KERNEL_DATA_YAML_YAML_READER_CONFIG_H