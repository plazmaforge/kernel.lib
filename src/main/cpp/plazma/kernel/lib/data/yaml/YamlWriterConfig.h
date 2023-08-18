#ifndef PLAZMA_KERNEL_DATA_YAML_YAML_WRITER_CONFIG_H
#define PLAZMA_KERNEL_DATA_YAML_YAML_WRITER_CONFIG_H

#include <string>

#include "plazma/kernel/lib/text/WriterConfig.h"

using namespace text;

namespace yaml {

    class YamlWriterConfig: public WriterConfig {

        public:
            YamlWriterConfig();
            ~YamlWriterConfig();

            std::string getAttributeQuoteValue();

            std::string toString();

        public:
            std::string attributeCase;   // Tag name case: 'lower', 'upper', 'camel' ...
            std::string attributeQuote;  // Attribute value quote: 'single', 'double', 'none'
            bool trimAttribute;          // Trim attribute value
            bool colorized;
            
    };

}
#endif // PLAZMA_KERNEL_DATA_YAML_YAML_WRITER_CONFIG_H