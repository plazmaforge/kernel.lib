#ifndef PLAZMA_LIB_DATA_JSON_JSON_PARSER_H
#define PLAZMA_LIB_DATA_JSON_JSON_PARSER_H

#include <string>

#include "JsonParserContext.h"
#include "JsonReaderConfig.h"
#include "plazma/lib/data/node/Node.h"
#include "plazma/lib/ext/StringList.h"

using namespace ext;

namespace jsonparser {

    // Event types

    const int NONE = 0;

    // Object
    
    const int START_OBJECT = 1;

    const int END_OBJECT = 2;

    // Array

    const int START_ARRAY = 3;

    const int END_ARRAY = 4;

    // Attribute

    const int START_ATTRIBUTE_NAME = 5;

    const int START_ATTRIBUTE_EQ = 6;

    const int START_ATTRIBUTE_VALUE = 7;

    const int END_ATTRIBUTE = 8;

    // Value

    const int START_ARRAY_VALUE = 9;

    const int END_ARRAY_VALUE = 10;

    // Node

    const int START_NODE = 1001;

    const int END_NODE = 1002;
    

    // Node types

    const int OBJECT_TYPE = 1;

    const int ARRAY_TYPE = 2;

    const int ATTRIBUTE_TYPE = 3;

    node::Node* parseJsonFromTokens(StringList* tokens); 

    node::Node* parseJsonFromTokens(json::JsonReaderConfig* config, StringList* tokens);

    ////

    void onStartObject(json::JsonParserContext* context);

    void onEndObject(json::JsonParserContext* context);

    void onStartArray(json::JsonParserContext* context);

    void onEndArray(json::JsonParserContext* context);

    void onComma(json::JsonParserContext* context);

    void onStartAttributeEq(json::JsonParserContext* context);

    void onStartAttributeValue(json::JsonParserContext* context);

    void onStartAttributeName(json::JsonParserContext* context);

    void onStartArrayValue(json::JsonParserContext* context);

}
#endif // PLAZMA_LIB_DATA_JSON_JSON_PARSER_H