#ifndef PLAZMA_LIB_DATA_JSON_JSONLIB_H
#define PLAZMA_LIB_DATA_JSON_JSONLIB_H

#include <string>

#include "plazma/lib/data/node/Node.h"
#include "plazma/lib/ext/StringList.h"
#include "JsonReaderConfig.h"
#include "JsonWriterConfig.h"

using namespace ext;

namespace jsonlib {

    // tokenize

    StringList* tokenizeJsonFromText(const std::string& input);

    StringList* tokenizeJsonFromArray(const char* input);

    StringList* tokenizeJsonFromArray(const char* input, int size);

    StringList* tokenizeJsonFromFile(const std::string& fileName);

    // parse
    
    node::Node* parseJsonFromTokens(StringList* tokens);

    node::Node* parseXmlFromTokens(json::JsonReaderConfig* config, StringList* tokens);

    // read

    node::Node* readJsonFromText(const std::string& input);

    node::Node* readJsonFromText(json::JsonReaderConfig* config, const std::string& input);

    //

    node::Node* readJsonFromArray(const char* input);

    node::Node* readJsonFromArray(json::JsonReaderConfig* config, const char* input);

    //
    
    node::Node* readJsonFromFile(const std::string& fileName);

    node::Node* readJsonFromFile(json::JsonReaderConfig* config, const std::string& fileName);
    
    // write: console

    std::string writeJsonToText(node::Node* root);

    std::string writeJsonToText(json::JsonWriterConfig* config, node::Node* root);

    // write: console

    void writeJsonToConsole(node::Node* root);

    void writeJsonToConsole(json::JsonWriterConfig* config, node::Node* root);

    // write: file

    void writeJsonToFile(const std::string& fileName, node::Node* root);

    void writeJsonToFile(const std::string& fileName, json::JsonWriterConfig* config, node::Node* root);

    // convert

    node::Node* convertJsonToXml(node::Node* node);

    node::Node* convertJsonToYaml(node::Node* node);

    //

    std::string getTokensText(StringList* tokens);

 }
 #endif // PLAZMA_LIB_DATA_JSON_JSONLIB_H