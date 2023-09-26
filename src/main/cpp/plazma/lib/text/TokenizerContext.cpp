#include <string>

#include "TokenizerContext.h"

namespace text {

    TokenizerContext::TokenizerContext() {
        useEscape = true;
        inParseState = true; // !IMPORTANT! - true
        inTextState = false;

        isStartQuote = false;
        isEndQuote = false;
    }

    TokenizerContext::~TokenizerContext() {
        //cout << "Destroy TokenizerContext: " << endl;
        //destroy();
    }

    bool TokenizerContext::isFindQuote(char ch) {
        // Start or End: 'ch' is quote
        return ((isStartQuote || isEndQuote) && ((ch != '\'' || (ch == '\'' && inParseState)) && (ch != '"' || (ch == '"' && inParseState))));
    }

    void TokenizerContext::updateState(std::string separator) {
        // do nothing by default
    }

    bool TokenizerContext::isQuoteChar(char ch) {
        // All chars are candidates for quotes by default
        return true;
    }

    bool TokenizerContext::isSeparatorChar(char ch) {
        // All chars are candidates for separators by default
        return true;
    }

    bool TokenizerContext::isEmptySeparators() {
        return separators.empty();
    }

    bool TokenizerContext::isEmptyExcludeSeparators() {
        return excludeSeparators.empty();
    }

    bool TokenizerContext::isEmptyFlexSeparators() {
        return flexSeparators.empty();
    }

    bool TokenizerContext::isEmptyStartQuotes() {
        return startQuotes.empty();
    }

    bool TokenizerContext::isEmptyEndQuotes() {
        return endQuotes.empty();
    }

    bool TokenizerContext::isEmptyQuotes() {
        return isEmptyStartQuotes() && isEmptyEndQuotes();
    }

    std::string TokenizerContext::toString() {
        return "TokenizerContext[]";
    }

}
