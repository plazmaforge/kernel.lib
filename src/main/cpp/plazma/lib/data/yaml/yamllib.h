#ifndef PLAZMA_LIB_DATA_YAML_YAMLLIB_H
#define PLAZMA_LIB_DATA_YAML_YAMLLIB_H

#include <string>

#include "plazma/lib/data/node/Node.h"
#include "plazma/lib/ext/StringList.h"

#include "YamlReaderConfig.h"
#include "YamlWriterConfig.h"

using namespace ext;

namespace yamllib {

    // tokenize

    StringList* tokenizeYamlFromText(const std::string& input);

    StringList* tokenizeYamlFromArray(const char* input);

    StringList* tokenizeYamlFromArray(const char* input, int size);

    StringList* tokenizeYamlFromFile(const std::string& fileName);

    // parse
    
    node::Node* parseYamlFromTokens(StringList* tokens);

    node::Node* parseYamlFromTokens(yaml::YamlReaderConfig* config, StringList* tokens);

    // read

    node::Node* readYamlFromText(const std::string& input);

    node::Node* readYamlFromText(yaml::YamlReaderConfig* config, const std::string& input);

    //

    node::Node* readYamlFromArray(const char* input);

    node::Node* readYamlFromArray(yaml::YamlReaderConfig* config, const char* input);

    //
    
    node::Node* readYamlFromFile(const std::string& fileName);

    node::Node* readYamlFromFile(yaml::YamlReaderConfig* config, const std::string& fileName);
    
    // write: console

    std::string writeYamlToText(node::Node* root);

    std::string writeYamlToText(yaml::YamlWriterConfig* config, node::Node* root);

    // write: console

    void writeYamlToConsole(node::Node* root);

    void writeYamlToConsole(yaml::YamlWriterConfig* config, node::Node* root);

    // write: file

    void writeYamlToFile(const std::string& fileName, node::Node* root);

    void writeYamlToFile(const std::string& fileName, yaml::YamlWriterConfig* config, node::Node* root);

    // convert

    node::Node* convertYamlToXml(node::Node* node);

    node::Node* convertYamlToJson(node::Node* node);

    //

    std::string getTokensText(StringList* tokens);    

 }
 #endif // PLAZMA_LIB_DATA_YAML_YAMLLIB_H