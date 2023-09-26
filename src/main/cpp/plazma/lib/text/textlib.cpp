#include <string>
#include <vector>

#include "textlib.h"
#include "plazma/lib/math/mathlib.h"
//#include "plazma/lib/ext/Array.h"
//#include "plazma/lib/ext/List.h"
#include "plazma/lib/ext/StringList.h"

using namespace ext;
using namespace text;

namespace textlib {

    //// Array Lib

    void fill(int *array, int count, int value) {
        if (array == nullptr || count == 0) {
            return;
        }
        for (int i = 0; i < count; i++) {
            array[i] = value;
        }
    }

    void init(int *array, int count) {
        fill(array, count, 0);
    }

    ////

    void copy(const char* input, char* output, int offset, int count) {
        for (int i = 0; i < count; i++) {
            output[i] = input[i + offset];
        }
    }

    ////

    bool in(std::string* array, int count, std::string &str) {
        if (count == 0 || str.empty()) {
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (str == array[i]) {
                return true;
            }
        }
        return false;
    }

    // ALT
    bool isLikeToken(const char *input, int size, int offset, std::string &token, char ch) {
        if (token.empty()) {
            return false;
        }

        // Check first char
        if (ch != token.at(0)) {
            return false;
        }

        if (token.length() == 1) {
            return true;
        }

        if (offset + token.length() >= size) {
            return false;
        }

        for (int i = 0; i < token.length(); i++) {
            if (token.at(i) != input[i + offset]) {
                return false;
            }
        }
        return true;
    }

    // ALT
    int findTokenIndex(const char* input, int size, int offset, std::string* tokens, int tokenCount, char ch) {

        int index = -1;
        std::string* curr = nullptr;
        std::string* token = nullptr;

        for (int i = 0; i < tokenCount; i++) {
            token = &tokens[i];

            // ALT
            if (!isLikeToken(input, size, offset, *token, ch)) {
                continue;
            }

            if (curr == nullptr || curr->empty() || curr->length() < token->length()) {
                curr = token;
                index = i;
            }
        }
        return index;
    }

    // ALT
    int findQuoteIndex(TokenizerContext* context, const char* input, int size, int offset, std::string* quotes, int quoteCount, char ch) {

        if (quoteCount == 0 || ch == '\0') {
            return -1;
        }

        // EXP: Short check
        //if (!context->isQuoteChar(ch)) {
        //    return -1;
        //}

        // ALT2
        return findTokenIndex(input, size, offset, quotes, quoteCount, ch);
    }

    // ALT
    int findSeparatorIndex(TokenizerContext* context, const char* input, int size, int offset, std::string* separators, int separatorCount, char ch) {
        if (separatorCount == 0 || ch == '\0') {
            return -1;
        }

        // EXP: Short check
        //if (!context->isSeparatorChar(ch)) {
        //    return -1;
        //}

        // ALT2
        return findTokenIndex(input, size, offset, separators, separatorCount, ch);
    }

    bool inQuote(int* quoteLevel, int quoteCount) {
        for (int k = 0; k < quoteCount; k++) {
            if (quoteLevel[k] > 0) {
                return true;
            }
        }
        return false;
    }

    void updateQuote(int* quoteLevel, int quoteCount, int quoteIndex, bool isStartQuote, bool isEndQuote) {

        // Change quote level
        if (isStartQuote && isEndQuote ) { 
            // identical
            if (quoteLevel[quoteIndex] == 0) {
                quoteLevel[quoteIndex] = 1;
            } else {
                quoteLevel[quoteIndex] = 0;
            }
        } else {

            if (isStartQuote) {
                // Start quote: increment level
                quoteLevel[quoteIndex] = quoteLevel[quoteIndex] + 1;
            } else if (isEndQuote) {
                // End quote: decrement level
                quoteLevel[quoteIndex] = quoteLevel[quoteIndex] - 1;
                if (quoteLevel[quoteIndex] < 0) {
                    quoteLevel[quoteIndex] = 0;
                }
            }
        }	
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    StringList* tokenize(TokenizerContext* context, const char* input, int size) {

        // Create result
        StringList *result = new StringList();

        if (input == nullptr || size <= 0) {
            // String is null - no processing
            return result;
        }
        if (context == nullptr || context->isEmptySeparators()) {
            // Separators are empty - no processing
            result->add(input, 0, size);
            return result;
        }

        bool includeSeparator = context->isEmptyExcludeSeparators();        
        int offset = 0; // offset buffer
        int count = 0;  // count in buffer

        std::string* startQuotes = context->startQuotes.data();
        std::string* endQuotes = context->endQuotes.data();

        // prepare quote arrays
        if (!context->startQuotes.empty() && context->endQuotes.empty()) {
            endQuotes = context->startQuotes.data();
        } else if (context->startQuotes.empty() && !context->endQuotes.empty()) {
            startQuotes = context->endQuotes.data();
        }

        int quoteCount = (context->isEmptyQuotes()) ? 0 : mathlib::min((int) context->startQuotes.size(), (int) context->endQuotes.size());

        // Quote level (count)
        int* quoteLevel = new int[quoteCount];
        int i = 0;

        // fill '0': C++ only
        init(quoteLevel, quoteCount);

        // EXP
        int separatorCount = context->separators.size();
        std::string* separators = context->separators.data();

        int excludeSeparatorCount = context->excludeSeparators.size();
        std::string* excludeSeparators = context->excludeSeparators.data();

        int flexSeparatorCount = context->flexSeparators.size();
        std::string* flexSeparators = context->flexSeparators.data();

        // Reset context
        context->inTextState = false;

        // Processing input and ignore separators if we are in quote tree
        while (i < size) {

            char ch = input[i];

            // CHECK-1: ESCAPE: If 'useEscape' = true and in quote then ignore \' and \"
            if (ch == '\\' && context->useEscape && (i + 1) < size) {
                char ch2 = input[i + 1];
                if ((ch2 == '\'' || ch2 == '\"') && inQuote(quoteLevel, quoteCount)) {
                    i += 2;     // shiift: 2
                    count += 2; // shiift: 2
                    continue;
                }
            }

            // Reset quote context
            context->isStartQuote = false;
            context->isEndQuote = false;

            bool isFindQuote = false;
            int quoteIndex = -1;
            int tokenLen = 0; // tokenLen: CHAR, QUOTE, SEPARATOR 


            // For non TextState only
            if (!context->inTextState) {

                int startQuoteIndex = -1;
                int endQuoteIndex = -1;

                // CHECK-2: QUOTE: start, end
                startQuoteIndex = findQuoteIndex(context, input, size, offset + count, startQuotes, quoteCount, ch);
                endQuoteIndex = findQuoteIndex(context, input, size, offset + count, endQuotes, quoteCount, ch);

                if (startQuoteIndex > -1) {
                    context->isStartQuote = true;
                    quoteIndex = startQuoteIndex;
                    tokenLen = startQuotes[quoteIndex].length();
                }

                if (endQuoteIndex > -1) {
                    context->isEndQuote = true;
                    quoteIndex = endQuoteIndex;
                    tokenLen = endQuotes[quoteIndex].length();
                }

                // CHECK-R: Real quote for 'parse' state only
                // Start or End: 'ch' is quote
                isFindQuote = context->isFindQuote(ch);
            }
            

            // If real quote then update quote level and shift
            if (isFindQuote) {

                // Change quote level
                updateQuote(quoteLevel, quoteCount, quoteIndex, context->isStartQuote, context->isEndQuote);
                i += tokenLen;     // shift: quoteLen
                count += tokenLen; // shift: quoteLen

            } else {

                // Check quote state
                bool inQuoteState = inQuote(quoteLevel, quoteCount);

                // In quote mode we don't analyse separators
                if (inQuoteState) {
                    // symbol
                    i++;     // shift: next char
                    count++; // shift: count buffer
                    continue;
                }

                // CHECK-3: SEPARATOR
                int separatorIndex = findSeparatorIndex(context, input, size, offset + count, separators, separatorCount, ch);
                std::string separator;
                bool isFlexSeparator = false;
                bool inTextPrev = false;

                if (separatorIndex > -1) {
                    separator = separators[separatorIndex];
                    isFlexSeparator = in(flexSeparators, flexSeparatorCount, separator);
                    inTextPrev = context->inTextState;

                    // For like XML: XML Node content is not parsable
                    context->updateState(separator);

                    if (inTextPrev && context->inTextState) {
                        separatorIndex = -1;
                    }
                }

                //if (separatorIndex != -1) {
                //    cout << "SEPARATOR-INDEX:" << separatorIndex << endl;
                //    cout << "SEPARATOR-VALUE:" << separator << endl;
                //}

                // CHECK-4: SEPARATOR or SYMBOL
                if (separatorIndex > -1 
                    && (!isFlexSeparator || (isFlexSeparator && context->inParseState))) {

                    //cout << "SEPARATOR:" << separator << endl;

                    // separator
                    if (count > 0) {
                        // flush before chars
                        // ADD: token
                        result->add(input, offset, count);
                    }

                    if (includeSeparator || !in(excludeSeparators, excludeSeparatorCount, separator)) {
                        // ADD: separator
                        result->add(input, i, separator.length());
                    }

                    if (offset <= size - 1) {
                        offset = i + separator.length(); // next char
                        i = offset;     // next char
                        count = 0;      // reset buffer
                    }

                } else {        
                    // symbol
                    i++;     // next char
                    count++; // count in buffer
                }
            }
        }

        if (count > 0) {
            // flush before chars
            // ADD: token
            result->add(input, offset, count);
        }

        // destroy
        delete[] quoteLevel;

        return result;
    }

    std::string getLinesText(StringList* lines) {
        return getLinesText(lines, "", "\n", false);
    }

    std::string getLinesText(StringList* lines, std::string prefix, std::string suffix, bool convert) {
        std::string buf;
        if (lines == nullptr) {
            return buf;
        }
        int count = lines->size();
        std::string line;
        for (int i = 0; i < count; i++) {
            if (!prefix.empty()) {
                buf.append(prefix);
            }

            line = lines->get(i);

            if (convert) {

                // \ b (backspace BS, Unicode \u0008)
                // \ t (horizontal tab HT, Unicode \u0009)
                // \ n (linefeed LF, Unicode \u000a)
                // \ f (form feed FF, Unicode \u000c)
                // \ r (carriage return CR, Unicode \u000d)

                if (line == "\b") {
                    line = "[BS]";
                } else if (line == "\t") {
                    line = "[HT]";
                } else if (line == "\v") {
                    line = "[VT]";
                } else  if (line == "\f") {
                    line = "[FF]";
                } else if (line == "\n") {
                    line = "[LF]";
                } else if (line == "\r") {
                    line = "[CR]";
                } else if (line == "\r\n") {
                    line = "[CRLF]";
                }

            }

            buf.append(line);
            
            if (!suffix.empty()) {
                buf.append(suffix);
            }
        }
        return buf;
    }

    std::string getTokensText(StringList* tokens) {
        return getLinesText(tokens, ">> ", "\n", true);
    }



}