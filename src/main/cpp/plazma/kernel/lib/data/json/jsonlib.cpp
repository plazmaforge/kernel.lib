#include <string>

#include "jsonlib.h"
#include "jsontokenizer.h"
#include "jsonparser.h"
#include "jsonwriter.h"
#include "Json2XmlConverter.h"
#include "Json2YamlConverter.h"

#include "plazma/kernel/lib/io/iolib.h"
#include "plazma/kernel/lib/text/textlib.h"

using namespace ext;

namespace jsonlib {

    // tokenize

    StringList* tokenizeJsonFromText(const std::string &input) {
        return jsontokenizer::tokenizeJsonFromArray(input.c_str(), input.length());
    }

    StringList* tokenizeJsonFromArray(const char* input) {
        return jsontokenizer::tokenizeJsonFromArray(input);
    }

    StringList* tokenizeJsonFromArray(const char* input, int size) {
        return jsontokenizer::tokenizeJsonFromArray(input, size);
    }

    StringList* tokenizeJsonFromFile(const std::string &fileName) {
        char *text = iolib::readChars(fileName);
        return tokenizeJsonFromArray(text);
    }

    // parse
    
    node::Node* parseJsonFromTokens(StringList* tokens) {
        return jsonparser::parseJsonFromTokens(tokens);
    }

    node::Node* parseJsonFromTokens(json::JsonReaderConfig* config, StringList* tokens) {
        return jsonparser::parseJsonFromTokens(config, tokens);
    }

    // read

    node::Node* readJsonFromText(const std::string &input) {
        StringList* tokens = jsontokenizer::tokenizeJsonFromText(input);
        return jsonparser::parseJsonFromTokens(tokens);
    }

    node::Node* readJsonFromText(json::JsonReaderConfig* config, const std::string &input) {
        StringList* tokens = jsontokenizer::tokenizeJsonFromText(input);
        return jsonparser::parseJsonFromTokens(config, tokens);
    }

    //

    node::Node* readJsonFromArray(const char* input) {
        StringList* tokens = jsontokenizer::tokenizeJsonFromArray(input);
        return parseJsonFromTokens(tokens);
    }

    node::Node* readJsonFromArray(json::JsonReaderConfig* config, const char* input) {
        StringList* tokens = jsontokenizer::tokenizeJsonFromArray(input);
        return parseJsonFromTokens(config, tokens);
    }

    //
    
    node::Node* readJsonFromFile(const std::string &fileName) {
        char *text = iolib::readChars(fileName);
        return readJsonFromArray(text);
    }

    node::Node* readJsonFromFile(json::JsonReaderConfig* config, const std::string &fileName) {
        char *text = iolib::readChars(fileName);
        return readJsonFromArray(config, text);
    }

    // write

    std::string writeJsonToText(node::Node* root) {
        return jsonwriter::writeJsonToText(root);
    }

    std::string writeJsonToText(json::JsonWriterConfig* config, node::Node* root) {
        return jsonwriter::writeJsonToText(config, root);
    }

    // write: console

    void writeJsonToConsole(node::Node* root) {
        jsonwriter::writeJsonToConsole(root);
    }

    void writeJsonToConsole(json::JsonWriterConfig* config, node::Node* root) {
        jsonwriter::writeJsonToConsole(config, root);
    }

    // write: file

    void writeJsonToFile(const std::string &fileName, node::Node* root) {
        // TODO: Use jsonwriter::writeJsonToFile(fileName, config, root);
        std::string text = writeJsonToText(root);
        iolib::writeText(fileName, text);
    }

    void writeJsonToFile(const std::string &fileName, json::JsonWriterConfig* config, node::Node* root) {
        // TODO: Use jsonwriter::writeJsonToFile(fileName, config, root);
        std::string text = writeJsonToText(config, root);
        iolib::writeText(fileName, text);
    }

    // convert

    node::Node* convertJsonToXml(node::Node* node) {
        json::Json2XmlConverter* converter = new json::Json2XmlConverter();
        node::Node* result = converter->convert(node);
        if (converter != nullptr) {
            delete converter;
        }        
        return result;
    }

    node::Node* convertJsonToYaml(node::Node* node) {
        json::Json2YamlConverter* converter = new json::Json2YamlConverter();
        node::Node* result = converter->convert(node);
        if (converter != nullptr) {
            delete converter;
        }        
        return result;
    }

    //

    std::string getTokensText(StringList* tokens) {
        return jsontokenizer::getTokensText(tokens);
    }


}