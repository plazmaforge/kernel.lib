#ifndef PLAZMA_KERNEL_DATA_JSON_JSON_WRRITER_H
#define PLAZMA_KERNEL_DATA_JSON_JSON_WRRITER_H

#include <string>

#include "plazma/lib/data/node/Node.h"
#include "JsonWriterConfig.h"
#include "JsonAppendable.h"

namespace jsonwriter {

    // text

    std::string writeJsonToText(node::Node* root);

    std::string writeJsonToText(json::JsonWriterConfig* config, node::Node* root);

    // console

    void writeJsonToConsole(node::Node* root);

    void writeJsonToConsole(json::JsonWriterConfig* config, node::Node* root);

    //

    void writeLevelSpace(json::JsonWriterConfig* config, json::JsonAppendable* buf, int level);

    void writeJsonNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level);

    void writeJsonAttributeNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level);

    void writeJsonObjectNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level);

    void writeJsonArrayNode(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level);

    //

    void writeJsonAttributes(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level);

    void writeJsonChildren(json::JsonWriterConfig* config, json::JsonAppendable* buf, node::Node* node, int level);

}
#endif // PLAZMA_KERNEL_DATA_JSON_JSON_WRRITER_H