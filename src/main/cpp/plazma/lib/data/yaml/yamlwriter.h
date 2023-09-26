#ifndef PLAZMA_LIB_DATA_YAML_YAML_WRRITER_H
#define PLAZMA_LIB_DATA_YAML_YAML_WRRITER_H

#include <string>

#include "plazma/lib/data/node/Node.h"
#include "YamlWriterConfig.h"
#include "YamlAppendable.h"

namespace yamlwriter {

    // text

    std::string writeYamlToText(node::Node* root);

    std::string writeYamlToText(yaml::YamlWriterConfig* config, node::Node* root);

    // console

    void writeYamlToConsole(node::Node* root);

    void writeYamlToConsole(yaml::YamlWriterConfig* config, node::Node* root);

    //

    void writeLevelSpace(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, int level);

    ////

    void writeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeYamlNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeJsonNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    ////

    void writeAttributeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeYamlAttributeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeJsonAttributeNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeObjectNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeYamlObjectNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeJsonObjectNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeArrayNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeYamlArrayNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeJsonArrayNode(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    ////

    void writeYamlAttributes(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeJsonAttributes(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeChildren(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeYamlChildren(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeYamlChildren_(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

    void writeJsonChildren(yaml::YamlWriterConfig* config, yaml::YamlAppendable* buf, node::Node* node, int level);

}
#endif // PLAZMA_LIB_DATA_YAML_YAML_WRRITER_H