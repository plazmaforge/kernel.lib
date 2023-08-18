#ifndef PLAZMA_KERNEL_DATA_XML_XMLLIB_H
#define PLAZMA_KERNEL_DATA_XML_XMLLIB_H

#include <string>

#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/ext/StringList.h"
#include "XmlReaderConfig.h"
#include "XmlWriterConfig.h"

using namespace ext;

namespace xmllib {

    // tokenize

    StringList* tokenizeXmlFromText(const std::string& input);

    StringList* tokenizeXmlFromArray(const char* input);

    StringList* tokenizeXmlFromArray(const char* input, int size);

    StringList* tokenizeXmlFromFile(const std::string& fileName);

    // parse
    
    node::Node* parseXmlFromTokens(StringList* tokens);

    node::Node* parseXmlFromTokens(xml::XmlReaderConfig* config, StringList* tokens);

    // read

    node::Node* readXmlFromText(const std::string& input);

    node::Node* readXmlFromText(xml::XmlReaderConfig* config, const std::string& input);

    //

    node::Node* readXmlFromArray(const char* input);

    node::Node* readXmlFromArray(xml::XmlReaderConfig* config, const char* input);

    //
    
    node::Node* readXmlFromFile(const std::string& fileName);

    node::Node* readXmlFromFile(xml::XmlReaderConfig* config, const std::string& fileName);
    
    // write

    std::string writeXmlToText(node::Node* root);

    std::string writeXmlToText(xml::XmlWriterConfig* config, node::Node* root);

    // write: console

    void writeXmlToConsole(node::Node* root);

    void writeXmlToConsole(xml::XmlWriterConfig* config, node::Node* root);

    // write: file

    void writeXmlToFile(const std::string& fileName, node::Node* root);

    void writeXmlToFile(const std::string& fileName, xml::XmlWriterConfig* config, node::Node* root);

    // convert

    node::Node* convertXmlToJson(node::Node* node);

    node::Node* convertXmlToYaml(node::Node* node);

    //

    std::string getTokensText(StringList* tokens);

}
#endif // PLAZMA_KERNEL_DATA_XML_XMLLIB_H