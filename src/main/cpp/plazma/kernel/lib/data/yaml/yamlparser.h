#ifndef PLAZMA_KERNEL_DATA_YAML_YAML_PARSER_H
#define PLAZMA_KERNEL_DATA_YAML_YAML_PARSER_H

#include <string>

#include "YamlParserContext.h"
#include "YamlReaderConfig.h"
#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/ext/StringList.h"

using namespace ext;

namespace yamlparser {

    // Event types

    const int NONE = 0;

    // Object
    
    const int START_OBJECT = 1;           // YAML OBJECT

    const int END_OBJECT = 2;             // YAML OBJECT

    //
    
    const int START_JSON_OBJECT = 10;     // JSON OBJECT

    const int END_JSON_OBJECT = 20;       // JSON OBJECT

    // Array
    
    const int START_ARRAY = 3;            // YAML ARRAY

    const int END_ARRAY = 4;              // YAML ARRAY

    //

    const int START_JSON_ARRAY = 30;      // JSON ARRAY

    const int END_JSON_ARRAY = 40;        // JSON ARRAY

    // Attribute

    const int START_ATTRIBUTE_NAME = 5;

    const int START_ATTRIBUTE_EQ = 6;

    const int START_ATTRIBUTE_VALUE = 7;

    const int END_ATTRIBUTE = 8;

    // Value

    const int START_ARRAY_VALUE = 9;

    const int END_ARRAY_VALUE = 10;

    const int START_ARRAY_MARKER_NL_FIRST  = 9999;

    const int START_ARRAY_MARKER_NL        = 9998;

    const int START_ARRAY_MARKER_SP_FIRST  = 9997;

    const int START_ARRAY_MARKER_SP        = 9996;



    const int START_COMMENT = 91;

    const int END_COMMENT = 92;


    const int START_NEW_LINE = 93;

    const int START_IGNORE_LINE = 94;


    // Node

    const int START_NODE = 1001;

    const int END_NODE = 1002;
    

    // Node types

    const int OBJECT_TYPE = 1;

    const int ARRAY_TYPE = 2;

    const int ATTRIBUTE_TYPE = 3;

    //

    const int JSON_OBJECT_SUBTYPE = 10;

    const int JSON_ARRAY_SUBTYPE = 20;

    const int WRAPPER_OBJECT_SUBTYPE = 70;


    node::Node* parseYamlFromTokens(StringList* tokens);

    node::Node* parseYamlFromTokens(yaml::YamlReaderConfig* config, StringList* tokens);

    //

    void onStartObject(yaml::YamlParserContext* context);

    void onStartJsonObject(yaml::YamlParserContext* context);

    void onEndObject(yaml::YamlParserContext* context);

    void onEndJsonObject(yaml::YamlParserContext* context);

    void onStartArray(yaml::YamlParserContext* context);

    void onStartJsonArray(yaml::YamlParserContext* context);

    void onEndArray(yaml::YamlParserContext* context);

    void onEndJsonArray(yaml::YamlParserContext* context);

    // '-[NL]
    void onStartArrayMarker_NL(yaml::YamlParserContext* context);

    // '- '
    void onStartArrayMarker_SP(yaml::YamlParserContext* context);

    void onComma(yaml::YamlParserContext* context);

    void onBlankLine(yaml::YamlParserContext* context);

    void onNewBlankLine(yaml::YamlParserContext* context);

    void onNewLine(yaml::YamlParserContext* context);

    void onStartAttributeEq(yaml::YamlParserContext* context);

    void onStartAttributeValue(yaml::YamlParserContext* context);

    void onStartAttributeName(yaml::YamlParserContext* context);

    void onStartAttributeName_2(yaml::YamlParserContext* context);

    void onStartArrayValue(yaml::YamlParserContext* context);

    void onStartArrayNullValue(yaml::YamlParserContext* context);

    void onStartArrayNullValue_2(yaml::YamlParserContext* context);

    void onStartLineValue(yaml::YamlParserContext* context);

    void onStartComment(yaml::YamlParserContext* context);

    void onEndComment(yaml::YamlParserContext* context);

    void lookupNode(yaml::YamlParserContext* context, int indent);

    void tryLookupNode(yaml::YamlParserContext* context, int indent);

}
#endif // PLAZMA_KERNEL_DATA_YAML_YAML_PARSER_H