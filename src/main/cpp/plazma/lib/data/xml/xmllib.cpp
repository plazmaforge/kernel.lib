#include <string>

#include "xmllib.h"
#include "xmltokenizer.h"
#include "xmlparser.h"
#include "xmlwriter.h"
#include "Xml2JsonConverter.h"
#include "Xml2YamlConverter.h"

#include "plazma/lib/io/iolib.h"
#include "plazma/lib/text/textlib.h"

using namespace ext;

namespace xmllib {

    // tokenize

    StringList* tokenizeXmlFromText(const std::string& input) {
        return xmltokenizer::tokenizeXmlFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeXmlFromArray(const char* input) {
        return xmltokenizer::tokenizeXmlFromArray(input);
    }

    StringList* tokenizeXmlFromArray(const char* input, int size) {
        return xmltokenizer::tokenizeXmlFromArray(input, size);
    }

    StringList* tokenizeXmlFromFile(const std::string& fileName) {
        char *text = iolib::readChars(fileName);
        return tokenizeXmlFromArray(text);
    }

    // parse
    
    node::Node* parseXmlFromTokens(StringList* tokens) {
        return xmlparser::parseXmlFromTokens(tokens);
    }

    node::Node* parseXmlFromTokens(xml::XmlReaderConfig* config, StringList* tokens) {
        return xmlparser::parseXmlFromTokens(config, tokens);
    }

    // read

    node::Node* readXmlFromText(const std::string& input) {
        StringList* tokens = xmltokenizer::tokenizeXmlFromText(input);
        return parseXmlFromTokens(tokens);
    }

    node::Node* readXmlFromText(xml::XmlReaderConfig* config, const std::string& input) {
        StringList* tokens = xmltokenizer::tokenizeXmlFromText(input);
        return parseXmlFromTokens(config, tokens);
    }

    //

    node::Node* readXmlFromArray(const char* input) {
        StringList* tokens = xmltokenizer::tokenizeXmlFromArray(input);
        return parseXmlFromTokens(tokens);
    }

    node::Node* readXmlFromArray(xml::XmlReaderConfig* config, const char* input) {
        StringList* tokens = xmltokenizer::tokenizeXmlFromArray(input);
        return parseXmlFromTokens(config, tokens);
    }

    //

    node::Node* readXmlFromFile(const std::string& fileName) {
        char *text = iolib::readChars(fileName);
        return readXmlFromArray(text);;
    }

    node::Node* readXmlFromFile(xml::XmlReaderConfig* config, const std::string& fileName) {
        char *text = iolib::readChars(fileName);
        return readXmlFromArray(config, text);;
    }

    // write: text

    std::string writeXmlToText(node::Node* root) {
        return xmlwriter::writeXmlToText(root);
    }

    std::string writeXmlToText(xml::XmlWriterConfig* config, node::Node* root) {
        return xmlwriter::writeXmlToText(config, root);
    }

    // write: console

    void writeXmlToConsole(node::Node* root) {
        xmlwriter::writeXmlToConsole(root);
    }

    void writeXmlToConsole(xml::XmlWriterConfig* config, node::Node* root) {
        xmlwriter::writeXmlToConsole(config, root);
    }

    // write: file

    void writeXmlToFile(const std::string& fileName, node::Node* root) {
        // TODO: Use xmlwriter::writeXmlToFile(fileName, config, root);
        std::string text = writeXmlToText(root);
        iolib::writeText(fileName, text);
    }

    void writeXmlToFile(const std::string& fileName, xml::XmlWriterConfig* config, node::Node* root) {
        // TODO: Use xmlwriter::writeXmlToFile(fileName, config, root);
        std::string text = writeXmlToText(config, root);
        iolib::writeText(fileName, text);
    }

    // convert

    node::Node* convertXmlToJson(node::Node* node) {
        xml::Xml2JsonConverter* converter = new xml::Xml2JsonConverter();
        node::Node* result = converter->convert(node);
        if (converter != nullptr) {
            delete converter;
        }        
        return result;
    }

    node::Node* convertXmlToYaml(node::Node* node) {
        xml::Xml2YamlConverter* converter = new xml::Xml2YamlConverter();
        node::Node* result = converter->convert(node);
        if (converter != nullptr) {
            delete converter;
        }        
        return result;
    }

    //

    std::string getTokensText(StringList* tokens) {
        return xmltokenizer::getTokensText(tokens);
    }



}