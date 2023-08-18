#include <string>
#include <vector>

#include "xmltokenizer.h"
#include "XmlTokenizerContext.h"
#include "plazma/kernel/lib/str/strlib.h"
#include "plazma/kernel/lib/text/textlib.h"

using namespace ext;

// https://github.com/apache/xerces-c
// https://github.com/apache/xerces-c/tree/master/src/xercesc/parsers
// https://github.com/apache/xalan-c/blob/master/src/xalanc/XercesParserLiaison/XercesParserLiaison.cpp

namespace xmltokenizer {

    // tokenize
    
    StringList* tokenizeXmlFromText(const std::string& input) {
        return tokenizeXmlFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeXmlFromArray(const char* input) {
        int size = strlib::size(input);
        return tokenizeXmlFromArray(input, size);
    }

    StringList* tokenizeXmlFromArray(const char* input, int size) {
        if (input == nullptr || size <= 0) {
            return nullptr;
        }

        xml::XmlTokenizerContext* context = new xml::XmlTokenizerContext();
        context->separators = SEPARATORS;
        context->excludeSeparators = EXCLUDE_SEPARATORS;
        context->flexSeparators = FLEX_SEPARATORS;
        context->startQuotes = START_QUOTES;
        context->endQuotes = END_QUOTES;

        StringList* tokens = textlib::tokenize(context, input, size);
        return tokens;
    }

    //

    std::string getTokensText(StringList* tokens) {
        return textlib::getTokensText(tokens);
    }

}