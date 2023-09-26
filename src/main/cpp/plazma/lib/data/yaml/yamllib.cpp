#include <string>

#include "yamllib.h"
#include "yamltokenizer.h"
#include "yamlparser.h"

#include "yamlwriter.h"
#include "Yaml2XmlConverter.h"
#include "Yaml2JsonConverter.h"

#include "plazma/lib/io/iolib.h"
#include "plazma/lib/text/textlib.h"

using namespace ext;

namespace yamllib {

    // tokenize

    StringList* tokenizeYamlFromText(const std::string& input) {
        return yamltokenizer::tokenizeYamlFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeYamlFromArray(const char* input) {
        return yamltokenizer::tokenizeYamlFromArray(input);
    }

    StringList* tokenizeYamlFromArray(const char* input, int size) {
        return yamltokenizer::tokenizeYamlFromArray(input, size);
    }

    StringList* tokenizeYamlFromFile(const std::string& fileName) {
        char *text = iolib::readChars(fileName);
        return tokenizeYamlFromArray(text);
    }

    // parse
    
    node::Node* parseYamlFromTokens(StringList* tokens) {
        return yamlparser::parseYamlFromTokens(tokens);
    }

    node::Node* parseYamlFromTokens(yaml::YamlReaderConfig* config, StringList* tokens) {
        return yamlparser::parseYamlFromTokens(config, tokens);
    }

    // read

    node::Node* readYamlFromText(const std::string& input) {
        StringList* tokens = yamltokenizer::tokenizeYamlFromText(input);
        return yamlparser::parseYamlFromTokens(tokens);
    }

    node::Node* readYamlFromText(yaml::YamlReaderConfig* config, const std::string& input) {
        StringList* tokens = yamltokenizer::tokenizeYamlFromText(input);
        return yamlparser::parseYamlFromTokens(config, tokens);
    }

    //

    node::Node* readYamlFromArray(const char* input) {
        StringList* tokens = yamltokenizer::tokenizeYamlFromArray(input);
        return parseYamlFromTokens(tokens);
    }

    node::Node* readYamlFromArray(yaml::YamlReaderConfig* config, const char* input) {
        StringList* tokens = yamltokenizer::tokenizeYamlFromArray(input);
        return parseYamlFromTokens(config, tokens);
    }

    //

    node::Node* readYamlFromFile(const std::string& fileName) {
        char *text = iolib::readChars(fileName);
        return readYamlFromArray(text);
    }

    node::Node* readYamlFromFile(yaml::YamlReaderConfig* config, const std::string& fileName) {
        char *text = iolib::readChars(fileName);
        return readYamlFromArray(config, text);
    }

    // write

    std::string writeYamlToText(node::Node* root) {
        return yamlwriter::writeYamlToText(root);
    }

    std::string writeYamlToText(yaml::YamlWriterConfig* config, node::Node* root) {
        return yamlwriter::writeYamlToText(config, root);
    }

    // write: console

    void writeYamlToConsole(node::Node* root) {
        yamlwriter::writeYamlToConsole(root);
    }

    void writeYamlToConsole(yaml::YamlWriterConfig* config, node::Node* root) {
        yamlwriter::writeYamlToConsole(config, root);
    }

    // write: file

    void writeYamlToFile(const std::string& fileName, node::Node* root) {
        // TODO: Use yamlwriter::writeYamlToFile(fileName, config, root);
        std::string text = writeYamlToText(root);
        iolib::writeText(fileName, text);
    }

    void writeYamlToFile(const std::string& fileName, yaml::YamlWriterConfig* config, node::Node* root) {
        // TODO: Use yamlwriter::writeYamlToFile(fileName, config, root);
        std::string text = writeYamlToText(config, root);
        iolib::writeText(fileName, text);
    }

    // convert

    node::Node* convertYamlToXml(node::Node* node) {
        yaml::Yaml2XmlConverter* converter = new yaml::Yaml2XmlConverter();
        node::Node* result = converter->convert(node);
        if (converter != nullptr) {
            delete converter;
        }        
        return result;
    }

    node::Node* convertYamlToJson(node::Node* node) {
        yaml::Yaml2JsonConverter* converter = new yaml::Yaml2JsonConverter();
        node::Node* result = converter->convert(node);
        if (converter != nullptr) {
            delete converter;
        }        
        return result;
    }

    //

    std::string getTokensText(StringList* tokens) {
        return yamltokenizer::getTokensText(tokens);
    }    

}