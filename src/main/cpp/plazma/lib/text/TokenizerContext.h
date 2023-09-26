#ifndef PLAZMA_LIB_TEXT_TOKENIZER_CONTEXT_H
#define PLAZMA_LIB_TEXT_TOKENIZER_CONTEXT_H

#include <string>
#include <vector>

namespace text {

    class TokenizerContext {

        public:
        
            TokenizerContext();

            virtual ~TokenizerContext();

            virtual bool isFindQuote(char ch);

            virtual void updateState(std::string separator);

            virtual bool isQuoteChar(char ch);

            virtual bool isSeparatorChar(char ch);

            bool isEmptySeparators();

            bool isEmptyExcludeSeparators();

            bool isEmptyFlexSeparators();

            bool isEmptyStartQuotes();

            bool isEmptyEndQuotes();

            bool isEmptyQuotes();

            virtual std::string toString();

        public:

            std::vector<std::string> separators;

            std::vector<std::string> excludeSeparators;

            std::vector<std::string> flexSeparators;

            std::vector<std::string> startQuotes;

            std::vector<std::string> endQuotes;

            bool useEscape = true;

            bool inParseState = true;   // 'false' for XML

            bool inTextState = false;

            bool isStartQuote = false;  // start quote flag

            bool isEndQuote = false;    // end quote flag

    };

}

#endif // PLAZMA_LIB_TEXT_TOKENIZER_CONTEXT_H