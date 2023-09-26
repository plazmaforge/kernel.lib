#include <string>
#include <cstring>

#include "ParserContext.h"

namespace text {

    ParserContext::ParserContext() {
        level = 0;
        
        verbose = false;
        verboseToken = false;
        verboseText = false;
        verboseDebug = false;

        token = nullptr;
        isOptimsticMode = true;
    }

    ParserContext::~ParserContext() {
        //cout << "Destroy ParserContext: " << endl;
        //destroy();
    }

    bool ParserContext::eq(const char *str1, const char *str2) {
        return strcmp(str1, str2) == 0;
    }

    ////

    std::string ParserContext::toString() {
        return "ParserContext[]";
    }

}
