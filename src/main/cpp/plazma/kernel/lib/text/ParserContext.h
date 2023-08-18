//#pragma once
#ifndef PLAZMA_KERNEL_LIB_TEXT_PARSER_CONTEXT_H
#define PLAZMA_KERNEL_LIB_TEXT_PARSER_CONTEXT_H

#include <string>

namespace text {

    class ParserContext {

        public:

            ParserContext();

            virtual ~ParserContext();

            bool eq(const char *str1, const char *str2); // TODO: Utils

            virtual std::string toString();

        public:

            int level = 0;

            bool verbose = false;
            bool verboseToken = false;
            bool verboseText = false;
            bool verboseDebug = false;
            
            char* token = nullptr;
            bool isOptimsticMode = true;
            int errorCode = 0;
            std::string errorMessage;

    };

}

#endif // PLAZMA_KERNEL_LIB_TEXT_PARSER_CONTEXT_H