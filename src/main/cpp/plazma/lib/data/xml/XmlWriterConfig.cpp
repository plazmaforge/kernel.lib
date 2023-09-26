#include "XmlWriterConfig.h"

namespace xml {

    XmlWriterConfig::XmlWriterConfig() {
        inlineFlag = false;
        inlineNodeFlag = false;
        inlineNodeLimit = INLINE_NODE_LIMIT;
        indent = "space";                // 'space', 'tab'
        tagCase = "";                    // 'lower', 'upper', 'camel' ...
        attributeCase = "";              // 'lower', 'upper', 'camel' ...
        attributeQuote = "";             // 'single', 'double', 'none'
        trimAttribute = false;           // RAW
        skipComment = false;             // Use comments
        skipMeta = false;                // Use <?...?>
        skipDTD = false;                 // Use <!DOCTYPE...>
        colorized = false;
    }

    XmlWriterConfig::~XmlWriterConfig() {
        //cout << "Destroy XmlWriterConfig: " << endl;
        //destroy();
    }

    ////

    int XmlWriterConfig::getInlineNodeLimit() {
        return normalizeInlineNodeLimit(inlineNodeLimit);
    }

    /*
     Return real indent (tab or 1/2 spaces) by config indent
    */
    std::string XmlWriterConfig::getIndentValue() {
        return normalizeIndentValue(indent);
    }

    std::string XmlWriterConfig::getAttributeQuoteValue() {
        return normalizeQuoteValue(attributeQuote);
    }

    std::string XmlWriterConfig::toString() {
        return "XmlWriterConfig[]";
    }

}
