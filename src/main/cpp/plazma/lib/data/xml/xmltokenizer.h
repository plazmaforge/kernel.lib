#ifndef PLAZMA_KERNEL_DATA_XML_XML_TOKENIZER_H
#define PLAZMA_KERNEL_DATA_XML_XML_TOKENIZER_H

#include <string>
#include <vector>

#include "plazma/lib/ext/StringList.h"

using namespace ext;

namespace xmltokenizer {

    const std::vector<std::string> WS_SEPARATORS = {" ", "\r\n", "\r", "\n", "\t", "\f"};

    const std::vector<std::string> SEPARATORS = {"<", ">", "</", "/>", "<?", "?>", "[", "]", "<!", "<!--", "-->", "<![CDATA[", "]]>", "=", " ", "\r\n", "\r", "\n", "\t", "\f"};

    // Separate [ALWAYS] but exclude in tokens
    const std::vector<std::string> EXCLUDE_SEPARATORS = WS_SEPARATORS;

    // Separate [IN PARSE MODE ONLY] but include if it is not exclude separator
    const std::vector<std::string> FLEX_SEPARATORS = WS_SEPARATORS;

    const std::vector<std::string> START_QUOTES = {"'", "\""};

    const std::vector<std::string> END_QUOTES = {"'", "\""};

    // tokenize

    StringList* tokenizeXmlFromText(const std::string& input);

    StringList* tokenizeXmlFromArray(const char* input);

    StringList* tokenizeXmlFromArray(const char* input, int size);

    //

    std::string getTokensText(StringList* tokens);

}
#endif // PLAZMA_KERNEL_DATA_XML_XML_TOKENIZER_H