//#pragma once
#ifndef PLAZMA_KERNEL_LIB_TEXT_READER_CONFIG_H
#define PLAZMA_KERNEL_LIB_TEXT_READER_CONFIG_H

#include <string>

namespace text {


    class ReaderConfig {

        public:
            ReaderConfig();
            ~ReaderConfig();

            virtual std::string toString();

        public:
        
            bool verbose = false;
            bool verboseToken = false;
            bool verboseText = false;

    };

}
#endif // PLAZMA_KERNEL_LIB_TEXT_READER_CONFIG_H