#include <string>
#include <map>

#include "yamlparser.h"
#include "plazma/kernel/lib/text/printer_helper.h"
#include "plazma/kernel/lib/str/strlib.h"

using namespace ext;

namespace yamlparser {

    static const char* MESSAGE_TOKEN                            = "TOKEN                 : ";
    static const char* MESSAGE_TEXT_TOKEN                       = "Text token            : ";

    static const char* MESSAGE_START_OBJECT                     = "Start Object";
    static const char* MESSAGE_START_JSON_OBJECT                = "Start JSON Object";
    static const char* MESSAGE_START_ARRAY                      = "Start Array";
    static const char* MESSAGE_START_JSON_ARRAY                 = "Start JSON Array";
    static const char* MESSAGE_START_ARRAY_VALUE                = "Start Array Value     : ";
    static const char* MESSAGE_START_ARRAY_MARKER_SP            = "Array Marker [SP]     : ";
    static const char* MESSAGE_START_ARRAY_MARKER_NL            = "Array Marker [NL]     : ";
    static const char* MESSAGE_START_ATTRIBUTE                  = "Start Attribute       : ";
    static const char* MESSAGE_START_ATTRIBUTE_VALUE            = "Start Attribute Value : ";
    static const char* MESSAGE_START_COMMENT                    = "Start Comment         : ";

    static const char* MESSAGE_END_OBJECT                       = "End Object";
    static const char* MESSAGE_END_JSON_OBJECT                  = "End JSON Object";
    static const char* MESSAGE_END_ARRAY                        = "End Array";
    static const char* MESSAGE_END_JSON_ARRAY                   = "End JSON Array";
    static const char* MESSAGE_END_ARRAY_VALUE                  = "End Array Value";
    static const char* MESSAGE_END_ARRAY_MARKER_SP              = "End Array Marker [SP]";
    static const char* MESSAGE_END_ARRAY_MARKER_NL              = "End Array Marker [NL]";
    static const char* MESSAGE_END_ARRAY_MARKER_SP_FIRST        = "End Array Marker [SP] [FIRST]";
    static const char* MESSAGE_END_ARRAY_MARKER_NL_FIRST        = "End Array Marker [NL] [FIRST]";
    static const char* MESSAGE_END_ATTRIBUTE                    = "End Attribute";
    static const char* MESSAGE_END_EQ                           = "End EQ";
    static const char* MESSAGE_END_OTHER                        = "End Other ";
    static const char* MESSAGE_END_COMMENT                      = "End Comment           : ";
    static const char* MESSAGE_ADD_VALUE_LINE                   = "Add value line        : ";

    ////

    static const char* ERROR_INVALID_YAML_ATTRIBUTE_NAME        = "YAML Parser: Invalid YAML Structure - attribute name must be setting before ':'";
    static const char* ERROR_CURRENT_NODE_IS_NULL               = "YAML Parser: Current Node is NULL";
    static const char* ERROR_RETURN_INCONSISTENT_DATA           = "YAML Parser: Return inconsistent data";
    static const char* ERROR_ADD_ATTRIBUTE                      = "YAML Parser: Cannot add attribute";
    static const char* ERROR_FATAL                              = "YAML Parser: Fatal error";

    ////

    bool isIgnoreIndent(int subType) {
        return subType == yamlparser::JSON_OBJECT_SUBTYPE
        || subType == yamlparser::JSON_ARRAY_SUBTYPE;
    }

    bool isIgnoreIndent(node::Node* node) {
        if (node == nullptr) {
            return false;
        }

        // Current Node
        if (isIgnoreIndent(node->getSubType())) {
            return true;
        }

        // Parent Node
        node::Node* parent = node->getParent();
        if (parent == nullptr) {
            return false;
        }

        return isIgnoreIndent(parent->getSubType());
    }

    bool isIgnoreIndent(yaml::YamlParserContext* context) {
        return isIgnoreIndent(context->currNode);
    }

    ////

    bool isType(node::Node* node, int type, int subType) {
        return node == nullptr ? false : node->isType(type, subType);
    }

    bool isType(node::Node* node, int type) {
        return node == nullptr ? false : node->isType(type);
    }

    ////

    bool isObjectType(node::Node* node) {
        return isType(node, OBJECT_TYPE);
    }

    bool isJsonObjectType(node::Node* node) {
        return isType(node, OBJECT_TYPE, JSON_OBJECT_SUBTYPE);
    }

    // TODO: Why need Wrapper Object ? Use Object only

    bool isWrapperObjectType(node::Node* node) {
        return isType(node, OBJECT_TYPE, WRAPPER_OBJECT_SUBTYPE);
    }

    bool isArrayType(node::Node* node) {
        return isType(node, ARRAY_TYPE);
    }

    bool isJsonArrayType(node::Node* node) {
        return isType(node, ARRAY_TYPE, JSON_ARRAY_SUBTYPE);
    }

    bool isAttributeType(node::Node* node) {
        return isType(node, ATTRIBUTE_TYPE);
    }

    bool isContainerType(node::Node* node) {
        return isObjectType(node) || isArrayType(node);
    }

    ////

    bool isEmptyType(node::Node* node, int type) {
        return isType(node, type) && !node->hasChildren();
    }

    bool isParentAttributeType(node::Node* node) {
        if (node == nullptr) {
            return false;
        }

        // Current Node is not AttributeType
        if (isType(node, ATTRIBUTE_TYPE)) {
            return false;
        }

        // Parent Node is AttributeType
        return isType(node->getParent(), ATTRIBUTE_TYPE);
    }

    ////

    node::Node* createNode() {
        node::Node* node = new node::Node();
        return node;
    }

    node::Node* createNode(int type) {
        node::Node* node = createNode();
        node->setType(type);
        return node;
    }

    node::Node* createNode(int type, int subType) {
        node::Node* node = createNode();
        node->setType(type);
        node->setSubType(subType);
        return node;
    }

    node::Node* createNullNode() {
        node::Node* node = createNode();
        node->setText("empty"); // TODO: STUB
        return node;
    }

    node::Node* createValueNode(const std::string &text) {
        node::Node* node = createNode();
        node->setText(text);
        return node;
    }

    ////

    int getArrayIndent(yaml::YamlParserContext* context) {
        return context->spaceCount + 1; // Array Marker: '- '
    }

    node::Node* createArrayNode(yaml::YamlParserContext* context) {
        node::Node* node = createNode(ARRAY_TYPE);
        node->setIndent(getArrayIndent(context));
        return node;
    }

    node::Node* createArrayValueNode(yaml::YamlParserContext* context, const std::string &text) {
        node::Node* node = createValueNode(text);
        node->setIndent(getArrayIndent(context));
        return node;
    }

    node::Node* createArrayNullNode(yaml::YamlParserContext* context) {
        node::Node* node = createNullNode();
        node->setIndent(getArrayIndent(context));
        return node;
    }

    node::Node* createJsonArrayNode(yaml::YamlParserContext* context) {
        node::Node* node = createNode(ARRAY_TYPE, JSON_ARRAY_SUBTYPE);
        return node;
    }

    ////

    bool isStartValue(yaml::YamlParserContext* context) {
        return context->currEvent == START_ATTRIBUTE_VALUE || context->currEvent == START_ARRAY_VALUE;
    }

    bool isStartArrayMarker_SP(yaml::YamlParserContext* context) {
        return context->currEvent == START_ARRAY_MARKER_SP_FIRST || context->currEvent == START_ARRAY_MARKER_SP;
    }

    bool isStartArrayMarker_NL(yaml::YamlParserContext* context) {
        return context->currEvent == START_ARRAY_MARKER_NL_FIRST || context->currEvent == START_ARRAY_MARKER_NL;
    }

    ////

    void closeValueNode(yaml::YamlParserContext* context) {

        //printEventItem(context->verbose, "Start CloseValueNode");

        // Start Value only
        if (!isStartValue(context)) {
            return;
        }

        if (context->currEvent == START_ATTRIBUTE_VALUE) {
            context->currEvent = START_OBJECT;
        } else if (context->currEvent == START_ARRAY_VALUE) {
            context->currEvent = START_ARRAY;
        }

        context->nodeEvent = START_NODE;
        //context->currAttribute = nullptr;      // Why? Don't touch attributes
        context->map[context->level] = nullptr;

        // Level UP
        context->levelUp();

        //printEventItem(context->verboseDebug, "End CloseValueNode");
        // TODO: Maybe Sync Node?
    }

    bool flushLines(yaml::YamlParserContext* context, const char* attribute) {

        //printEventItem(context->verboseDebug, "Start FlushLines");

        if (!context->multiValue) {
            return false;
        }

        context->multiValue = false; // reset
        int realSize = context->getRealSize();

        // Multi Line start from 2 lines
        if (realSize < 2) {
            return false;
        }

        std::string text = context->getFirstLines(attribute);        
        strlib::_trim(text); // IMPORTANT! Because we have 'value ' (with last SP)

        context->currNode->setText(text);
        context->clearLines();

        //printEventItem(context->verboseDebug, "End FlushLines");

        return true;
    }


    // DISABLED
    void checkValueNode(yaml::YamlParserContext* context, bool isNewLineComment) {

        printEventItem(context->verboseDebug, "Start CheckValueNode, isNewLineComment=" + std::to_string(isNewLineComment));

        // Check by MultiValue flag
        if (!context->multiValue) {
            return;
        }

        // Check by Event
        if (!isStartValue(context)) {
            return;
        }

        bool flush = flushLines(context, nullptr);

        //if (flush) {
            //closeValueNode(context);
        //}
        
    }

    ////

    std::string toNodeString(node::Node* node) {
        if (node == nullptr) {
            return "[NUL]";
        }
        return "Node[name=" + node->getName()
        + ", type=" + std::to_string(node->getType())
        + ", subType=" + std::to_string(node->getSubType())
        + ", indent=" + std::to_string(node->getIndent())
        + ", text=" + node->getText() + "]";
    }

    node::Node* parseYamlFromTokens(StringList* tokens) {
        node::Node* node = parseYamlFromTokens(nullptr, tokens);
        return node;
    }

    node::Node* parseYamlFromTokens(yaml::YamlReaderConfig* config, StringList* tokens) {

        node::Node* root = createNode(OBJECT_TYPE);

        if (tokens == nullptr || tokens->size() == 0) {
            return root;
        }

        yaml::YamlParserContext* context = new yaml::YamlParserContext();

        context->prevEvent = NONE;
        context->currEvent = START_OBJECT; //NONE; // YAML OBJECT
        context->nodeEvent = NONE;
        context->currAttribute = nullptr;

        context->level = 0;
        //map<int, char*> map;
        //
        context->currNode = root;
        context->lastNode = root;
        //
        if (config != nullptr) {
            context->verbose = config->verbose;
            context->verboseToken = config->verboseToken;
            context->verboseText = config->verboseText;
        }
        //
        //context->verboseDebug = true;

        int tokenLen = tokens->size();

        for (int i = 0; i < tokenLen; i++) {

            // Check Current Node
            //////////////////////////////////////

            if (context->currNode == nullptr && context->errorCode == 0) {
                context->errorCode = 1;
                context->errorMessage = ERROR_CURRENT_NODE_IS_NULL;
            }

            // Check Error Code
            //////////////////////////////////////

            if (context->errorCode > 0) {

                if (context->errorMessage.empty()) {
                    context->errorMessage  = ERROR_FATAL;
                }

                error(context->errorMessage);                    
                                
                if (context->isOptimsticMode) {                    
                    // Inconsistent Data
                    warn(ERROR_RETURN_INCONSISTENT_DATA);
                    return root;
                }

                delete root;
                delete context;

                return nullptr;
            }

            // Get token
            //////////////////////////////////////

            context->token = tokens->get(i); 

            if (context->token == nullptr) {
                printEventItem(context->verbose, "Start NULL");
                continue;
            }

            printEvent(context->verboseToken, MESSAGE_TOKEN, context->token);

            // Check NewLine
            //////////////////////////////////////

            bool _isNewLine = context->checkNewLine(context->token);

            if (_isNewLine) {

                printEventItem(context->verbose, "Start [NL]");

                // In JSON segment
                if (isIgnoreIndent(context)) {
                    continue;
                }

                if (isStartValue(context)) {    

                    // Add NewLine to lines buffer
                    context->addLine("\n");

                } else if (isStartArrayMarker_SP(context)) {

                    // Restore prev event
                    context->currEvent = context->prevEvent;

                    // Start array marker '-[NL]'
                    onStartArrayMarker_NL(context);
                }

                // If NewLine alredy - skip
                if (context->isNewLine) {

                    context->resetBlankLineState();

                    // Skip next NewLine
                    continue;
                }

                context->isNewLine = _isNewLine;
                onNewLine(context);

                context->resetBlankLineState();

                // Next iteration
                continue;

            } else {
                // Reset NewLine flag
                context->isNewLine = _isNewLine;
            }

            // Check BlankLine / NewBlankLine
            //////////////////////////////////////

            int _spaceCount = context->getSpaceCount(context->token);
            bool _isBlankLine = _spaceCount > 0;
            bool _isNewBlankLine = false;

            if (_isBlankLine) {

                // In JSON segment
                if (isIgnoreIndent(context)) {
                    continue;
                }

                _isNewBlankLine = context->checkNextNewLine(tokens, tokenLen, i);

                if (_isNewBlankLine) {

                    if (isStartValue(context)) {    

                        // Add NewLine to lines buffer
                        context->addLine("\n");

                    } if (isStartArrayMarker_SP(context)) {

                        // Restore prev event
                        context->currEvent = context->prevEvent;

                        // Start array marker '-[NL]'
                        onStartArrayMarker_NL(context);

                    }

                    printEventItem(context->verbose, "Start [NBL]+" + std::to_string(_spaceCount));
                    context->spaceCount = 0;           // RESET: because BlankLine with '\n'

                    // If NewBlankLine alredy - skip
                    if (context->isNewBlankLine) {

                        context->isBlankLine = _isBlankLine;

                        // Next noken
                        i++;

                        // Skip next NewBlankLine
                        continue;

                    }

                } else {
                    printEventItem(context->verbose, "Start [BL]+" + std::to_string(_spaceCount));
                    context->spaceCount = _spaceCount; // SET: because it is BlankLine (many spaces)
                }

            }

            ////

            context->isBlankLine = _isBlankLine;
            context->isNewBlankLine = _isNewBlankLine;

            ////

            if (_isNewBlankLine) {

                //onNewBlankLine(context);

                // Next noken
                i++;

                continue;
            }

            if (_isBlankLine) {

                //onBlankLine(context);

                continue;
            }


            // Check Token
            //////////////////////////////////////

            if (eq(context->token, "{")) {
                
                int currIndent = context->currNode->getIndent();

                onStartJsonObject(context);

                context->currNode->setIndent(currIndent);


            } else if (eq(context->token, "}")) {

                onEndJsonObject(context);

            } else if (eq(context->token, "[")) {

                onStartJsonArray(context);

            } else if (eq(context->token, "]")) {

                onEndJsonArray(context);
                
            } else if (eq(context->token, ",")) {

                onComma(context);

            //////////////////////////////////////
            
            // TODO
            // Check: ':,' ':}' ':]'

            //} else if (eq(context->token, ":")) {
            //    error("Char ':' is not correct");

            } else if (eq(context->token, ": ")) {

                onStartAttributeEq(context);

            } else if (eq(context->token, ":\r")
                || eq(context->token, ":\n")  
                || eq(context->token, ":\r\n")) {

                onStartAttributeEq(context);

            } else if (eq(context->token, "- ")) {

                onStartArrayMarker_SP(context);

            } else if (eq(context->token, "-\r")
                || eq(context->token, "-\n")  
                || eq(context->token, "-\r\n")) {

                onStartArrayMarker_NL(context);

            } else if (eq(context->token, "#")) {

                onStartComment(context);

            } else {

                // processing any text
                printEventItem(context->verboseText, MESSAGE_TEXT_TOKEN, context->token);

                // VALUE-EVENT - ': '
                if (context->currEvent == START_ATTRIBUTE_VALUE || context->currEvent == START_ARRAY_VALUE) {

                    onStartLineValue(context);

                // ATTRIBUTE-EVENT - ': '    
                } else if (context->currEvent == START_ATTRIBUTE_EQ) {

                    onStartAttributeValue(context);

                // OBJECT-EVENT
                } else if (context->currEvent == START_OBJECT || context->currEvent == START_JSON_OBJECT) {

                    onStartAttributeName(context);

                // ARRAY-EVENT
                } else if (context->currEvent == START_ARRAY || context->currEvent == START_JSON_ARRAY) {

                    onStartArrayValue(context);
    
                // COMMENT-EVENT
                } else if (context->currEvent == START_COMMENT) {

                    onEndComment(context);

                } else if (context->currEvent == START_ARRAY_MARKER_NL_FIRST || context->currEvent == START_ARRAY_MARKER_NL) {

                    context->currEvent = END_ARRAY;

                    onEndArray(context);

                    if (context->currNode == nullptr) {
                        // ERROR
                        continue;
                    }

                    context->lastNode = context->currNode;

                    onStartAttributeName(context);

                } else if (context->currEvent == START_ARRAY_MARKER_SP_FIRST || context->currEvent == START_ARRAY_MARKER_SP) {
                     
                     int indent = getArrayIndent(context);

                     lookupNode(context, indent);

                     if (context->currNode == nullptr) {
                         // ERROR
                         continue;
                     }

                     if (context->currEvent == START_ARRAY_MARKER_SP_FIRST) {

                         // If -[SP] is first - create new array 

                         // Create new array
                         onStartArray(context);

                     }

                     ////

                     onStartArrayValue(context);

                     //context->currNode->setIndent(context->spaceCount + 1); // IMPORTANT!

                } else if (context->currEvent == END_OBJECT 
                     || context->currEvent == END_JSON_OBJECT
                     || context->currEvent == END_ARRAY
                     || context->currEvent == END_JSON_ARRAY
                     ) {


                    if (isType(context->currNode, OBJECT_TYPE )) {

                        onStartAttributeName(context);

                    } else if (isType(context->currNode, ARRAY_TYPE )) {

                        onStartArrayValue(context);

                    } else {

                        error("Lost token: ", context->token, ",  Curr Event: " + std::to_string(context->currEvent));
                    }

                } else {

                    error("Lost token: ", context->token, ",  Curr Event: " + std::to_string(context->currEvent));
                }

            }

        }
        

        delete context;

        return root;
    }

    void onStartObject(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_OBJECT);

        context->currEvent = START_OBJECT;
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        node::Node* node = createNode(OBJECT_TYPE); // NEW-OBJECT
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);
    }

    void onStartJsonObject(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_JSON_OBJECT);

        context->currEvent = START_JSON_OBJECT;
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        node::Node* node = createNode(OBJECT_TYPE, JSON_OBJECT_SUBTYPE); // NEW-JSON-OBJECT
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);
    }

    bool tryEndNode(yaml::YamlParserContext* context, int type, int event, int thisEvent) {
        // printEventItem(context->verboseDebug, "Curr: " + currNode->toString());

        bool isEmptyContainer = isEmptyType(context->currNode, type);
        bool changed = false;

        // CONTAINER-LEVEL
        if (!isEmptyContainer || (isEmptyContainer && thisEvent == event)) {

            // Level UP
            context->levelUp();

            if (context->currNode == nullptr) {
                // ERROR
                return false;
            }
            changed = true;
            // printEventItem(context->verboseDebug, "Prev: " + currNode->toString());
        }

        // ATTRIBUTE LEVEL
        if (isParentAttributeType(context->currNode)) {

            // Level UP
            context->levelUp();

            if (context->currNode == nullptr) {
                // ERROR
                return false;
            }
            changed = true;
            // printEventItem(context->verboseDebug, "Prev: " + currNode->toString());
        }

        return changed;
    }

    void onEndObject(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_OBJECT);

        // OBJECT-EVENT
        if (context->nodeEvent != START_NODE) {
            // if (context->currEvent != START_OBJECT) {

            // TODO: DISABLE: Use context->prevNodeEvent
            // error("Invalid YAML Structure: Object must be open before
            // close '}'"); cout << context->nodeEvent << endl;
        }

        int thisEvent = context->currEvent;
        context->currEvent = END_OBJECT;
        context->nodeEvent = END_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        tryEndNode(context, OBJECT_TYPE, END_OBJECT, thisEvent);

    }

    void onEndJsonObject(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_JSON_OBJECT);

        // OBJECT-EVENT
        if (context->nodeEvent != START_NODE) {
            // if (context->currEvent != START_OBJECT) {

            // TODO: DISABLE: Use context->prevNodeEvent
            // error("Invalid JSON Structure: Object must be open by '{' before
            // close '}'"); cout << context->nodeEvent << endl;
        }

        int thisEvent = context->currEvent;
        context->currEvent = END_JSON_OBJECT;
        context->nodeEvent = END_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        tryEndNode(context, OBJECT_TYPE, END_JSON_OBJECT, thisEvent);

        //////////////////////////////////////////////////////////////////////

        if (context->currNode == nullptr) {
            // ERROR
            return;
        }

        context->lastNode = context->currNode;

        /////////////////////////////////////////////////////////////////////

    }

    void onStartArray(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_ARRAY);
        printEventItem(context->verboseDebug, "SPACE COUNT: ", std::to_string(context->spaceCount));

        context->currEvent = START_ARRAY;
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        node::Node* node = createArrayNode(context); // NEW-ARRAY
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);
    }

    void onStartJsonArray(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_JSON_ARRAY);

        context->currEvent = START_JSON_ARRAY;
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        node::Node* node = createJsonArrayNode(context); // NEW-JSON-ARRAY
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);
    }

    void onEndArray(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_ARRAY);

        // ARRAY-EVENT
        if (context->nodeEvent != START_NODE) {
            // if (context->currEvent != START_ARRAY) {

            // TODO: DISABLE: Use context->prevNodeEvent
            // error("Invalid YAML Structure: Array must be open before
            // close ']'");
        }

        int thisEvent = context->currEvent;
        context->currEvent = END_ARRAY;
        context->nodeEvent = END_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        bool changed = tryEndNode(context, ARRAY_TYPE, END_ARRAY, thisEvent);

        // TODO: IMPORTANT !!! Why?
        //if (changed) {
        //    context->lastNode = context->currNode;
        //}

    }

    void onEndJsonArray(yaml::YamlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_JSON_ARRAY);

        // ARRAY-EVENT
        if (context->nodeEvent != START_NODE) {
            // if (context->currEvent != START_ARRAY) {

            // TODO: DISABLE: Use context->prevNodeEvent
            // error("Invalid JSON Structure: Array must be open by '[' before
            // close ']'");
        }

        int thisEvent = context->currEvent;
        context->currEvent = END_JSON_ARRAY;
        context->nodeEvent = END_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        tryEndNode(context, ARRAY_TYPE, END_JSON_ARRAY, thisEvent);

        //////////////////////////////////////////////////////////////////////

        if (context->currNode == nullptr) {
            // ERROR
            return;
        }

        context->lastNode = context->currNode;

        /////////////////////////////////////////////////////////////////////

    }

    void onComma(yaml::YamlParserContext* context) {

        // TODO: nodeEvent == START_OBJECT_NODE / START_ARRAY_NODE

        if (context->currEvent == START_ARRAY_VALUE) {
            printEventItem(context->verbose, MESSAGE_END_ARRAY_VALUE);
            context->currEvent = START_ARRAY;
        } else {
            printEventItem(context->verbose, MESSAGE_END_ATTRIBUTE);
            context->currEvent = START_OBJECT;
        }

        // if (context->objectEvent != START_OBJECT) {
        //    error("Invalid JSON Structure: Object must be open by '{' before
        //    close ", context->token);
        //}

        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        // Level UP
        context->levelUp();

        //////////////////////////////////////////////////////////////////////

        context->lastNode = context->currNode;

        /////////////////////////////////////////////////////////////////////

    }

    ////

    // '-[NL]': Array Marker with New Line
    // See onComma !!!
    void onStartArrayMarker_NL(yaml::YamlParserContext* context) {

        if (context->currEvent == START_ATTRIBUTE_EQ) {

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_EQ);

            // Create new array
            onStartArray(context);            

            context->currEvent = START_ARRAY_MARKER_NL_FIRST;

        } else if (context->currEvent == START_ATTRIBUTE_VALUE) {
            
            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_ATTRIBUTE);

            // TODO: ERROR

        } else if (context->currEvent == START_ARRAY_MARKER_NL_FIRST) {

            // ARRAY LEVEL

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_ARRAY_MARKER_NL_FIRST);

            onStartArrayNullValue_2(context);

            context->currEvent = START_ARRAY_MARKER_NL;

        } else if (context->currEvent == START_ARRAY_MARKER_NL || context->currEvent == START_ARRAY_VALUE) {

            // ELEMENT LEVEL - GO UP

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_NL
            , context->currEvent == START_ARRAY_MARKER_NL ? MESSAGE_END_ARRAY_MARKER_NL : MESSAGE_END_ARRAY_VALUE);

            // Level UP
            context->levelUp();
            
            if (context->currNode == nullptr) {
                // ERROR
                return;
            }

            ////

            onStartArrayNullValue(context);

            context->currEvent = START_ARRAY_MARKER_NL;
            
        } else if (context->currEvent == START_ARRAY) {

            // ARRAY LEVEL

            onStartArrayNullValue(context);

            context->currEvent = START_ARRAY_MARKER_NL;

        } else {

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_OTHER + std::to_string(context->currEvent));

            // TODO

        }

    }

    // '-[SP]': Array Marker with Space
    // See onComma !!!
    void onStartArrayMarker_SP(yaml::YamlParserContext* context) {

        context->prevEvent = context->currEvent;

        if (context->currEvent == START_ATTRIBUTE_EQ) {

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_EQ);
            context->currEvent = START_ARRAY_MARKER_SP_FIRST;
            
        } else if (context->currEvent == START_ATTRIBUTE_VALUE) {
            
            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ATTRIBUTE);
            context->currEvent = START_ARRAY_MARKER_SP;

            // TODO: ERROR

        } else if (context->currEvent == START_ARRAY_MARKER_NL_FIRST) {

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ARRAY_MARKER_NL_FIRST);
            context->currEvent = START_ARRAY_MARKER_SP;

        } else if (context->currEvent == START_ARRAY_MARKER_NL) {

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ARRAY_MARKER_NL);
            context->currEvent = START_ARRAY_MARKER_SP;

        } else if (context->currEvent == START_ARRAY_VALUE) {

            // ELEMENT LEVEL - FLUSH TEXT

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ARRAY_VALUE);
            printEventItem(context->verboseDebug, "SPACE COUNT: ", std::to_string(context->spaceCount));

            // CLOSE ARRAY VALUE

            flushLines(context, nullptr); // flush without attribute (nullptr)

            context->currEvent = START_ARRAY_MARKER_SP;

        } else {

            printEventItem(context->verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_OTHER + std::to_string(context->currEvent));
            context->currEvent = START_ARRAY_MARKER_SP;

        }
        
    }

    // See onComma !!!
    void onNewLine(yaml::YamlParserContext* context) {

        if (isStartValue(context)) {
            printEventItem(context->verboseDebug, "[NL] IN VALUE");
            context->multiValue = true; // start multi value
            return;
        }

        // JSON Object/Array only. 
        // TODO: What about isIndent?
        if (context->currEvent != END_JSON_OBJECT && context->currEvent != END_JSON_ARRAY) {
            return;
        }

        context->currEvent = START_OBJECT;        
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        // Level UP
        context->levelUp();

    }

    void onStartAttributeEq(yaml::YamlParserContext* context) {

        printEventItem(context->verbose, "Start EQ [ ]");

        // Start Value only
        if (!isStartValue(context)) {
            context->currEvent = START_ATTRIBUTE_EQ;
            return;
        }

        int thisEvent = context->currEvent;

        if (thisEvent == START_ATTRIBUTE_VALUE) {
            printEventItem(context->verboseDebug, "CEQT_NODE: " + toNodeString(context->currNode));
        } else {
            printEventItem(context->verboseDebug, "CEQR_NODE: " + toNodeString(context->currNode));
        }

        ////////////////////////////////////////////////////////////////////////////////

        bool flush = flushLines(context, context->currAttribute); // flush with attrbute

        if (flush) {

            // Close Value Node -> Level UP

            context->token = context->currAttribute;
            closeValueNode(context);                

            if (context->currNode == nullptr) {
                // ERROR
                return;
            }

            context->lastNode = context->currNode;

            if (thisEvent == START_ARRAY_VALUE) {

                // Level UP
                context->levelUp();

                if (context->currNode == nullptr) {
                    // ERROR
                    return;
                }

                // Create CONTAINER/WRAPPER for attribute node (up)
                int currIndent = context->currNode->getIndent();
                onStartObject(context);

                // Transfer indent
                context->currNode->setIndent(currIndent);
                context->currNode->setSubType(WRAPPER_OBJECT_SUBTYPE);

                // Restore currEvent after onStartObject
                context->currEvent = START_ATTRIBUTE_VALUE;

            }

            // Create Attribute Name
            onStartAttributeName(context);
            context->currEvent = START_ATTRIBUTE_EQ;

            return;

        }

        ////////////////////////////////////////////////////////

        // ATTRIBUTE_VALUE -> ATTRIBUTE_NAME: Convert prev attribute value to attribute name
        context->token = context->currAttribute;

        printEventItem(context->verbose, "VALUE -> ATTR " + std::to_string(context->getLineSize()) + " ", context->token); 

        int currIndent = context->currNode->getIndent();

        if (thisEvent == START_ATTRIBUTE_VALUE) {

            printEventItem(context->verboseDebug, "ATTR_VALUE -> OBJ");

            // Reset Node Text
            context->currNode->setText("");

            // Create CONTAINER/WRAPPER for attribute node (up)
            onStartObject(context);

        } else {

            printEventItem(context->verboseDebug, "ARRAY_VALUE -> OBJ");

            context->currEvent = START_OBJECT;
            context->nodeEvent = START_NODE;
            context->currAttribute = nullptr;

            context->currNode->setType(OBJECT_TYPE); // SET-OBJECT: Convert Value to Object

        }
            
        // Transfer indent
        context->currNode->setIndent(currIndent);
        context->currNode->setSubType(WRAPPER_OBJECT_SUBTYPE);

        // Restore currEvent after onStartObject
        context->currEvent = START_ATTRIBUTE_VALUE;

        if (thisEvent == START_ATTRIBUTE_VALUE) {

            // Create Attribute Name
            onStartAttributeName(context);

        } else {

            // Create Attribute Name
            onStartAttributeName_2(context);

            // Transfer indent
            context->currNode->setIndent(currIndent); // IMPORTANT!

        }

        context->currEvent = START_ATTRIBUTE_EQ;

        // ATTRIBUTE-EVENT
        //if (context->currEvent != START_ATTRIBUTE_NAME) {
        //    error(ERROR_INVALID_YAML_ATTRIBUTE_NAME);
        //}

    }

    void onStartAttributeValue(yaml::YamlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE_VALUE, toSafeString(context->currAttribute), "=", context->token);

        // Clear lines buffer
        context->clearLines();

        context->currEvent = START_ATTRIBUTE_VALUE;

        /////////////////////////////////////////////////////////

        std::string text = context->token;

        context->addLine(text); // NO TRIM !!! Need spaces !!!

        // Normalize attribute value
        strlib::_trim(text);
        
        /////////////////////////////////////////////////////////        

        context->currNode->setText(text);        // Add first line only. Other lines in future
        context->currAttribute = context->token; // IMPORTANT! Attrbute candidate // nullptr;

        printEventItem(context->verboseDebug, "CVAL_NODE: " + toNodeString(context->currNode));
    }

    void onStartAttributeName(yaml::YamlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE, context->token);

        if (context->currEvent == START_ATTRIBUTE_VALUE) {

            // ATTRIBUTE_VALUE -> ATTRIBUTE_NAME
            printEventItem(context->verboseDebug, "VALUE -> ATTR [+]");

        }

        context->currEvent = START_ATTRIBUTE_NAME;
        context->currAttribute = context->token;
        
        int indent = context->getIndent(context->currAttribute);

        /////////////////////////////////////////////////////////

        tryLookupNode(context, indent);

        if (context->errorCode > 0) {
            return;
        }

        /////////////////////////////////////////////////////////

        std::string text = context->currAttribute;

        // Normalize attribute name
        strlib::_trim(text);
        
        /////////////////////////////////////////////////////////

        node::Node* node = createNode(ATTRIBUTE_TYPE); // NEW-ATTRIBUTE
        node->setIndent(indent); // IMPORTANT!
        node->setName(text);
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);

    }

    void onStartAttributeName_2(yaml::YamlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE, context->token);

        context->currEvent = START_ATTRIBUTE_NAME;
        context->currAttribute = context->token;
        
        /////////////////////////////////////////////////////////

        std::string text = context->currAttribute;

        // Normalize attribute name
        strlib::_trim(text);
        
        /////////////////////////////////////////////////////////

        node::Node* node = createNode(ATTRIBUTE_TYPE); // NEW-ATTRIBUTE
        node->setName(text);
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);

    }

    void onStartArrayValue(yaml::YamlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ARRAY_VALUE, context->token);
        printEventItem(context->verboseDebug, "SPACE COUNT: ", std::to_string(context->spaceCount));

        // Clear lines buffer
        context->clearLines();

        context->currEvent = START_ARRAY_VALUE;
        context->currAttribute = nullptr;        

        /////////////////////////////////////////////////////////

        std::string text = context->token;

        context->addLine(text); // NO TRIM !!! Need spaces !!!

        // Normalize attribute value
        strlib::_trim(text);
        
        /////////////////////////////////////////////////////////

        node::Node* node = createArrayValueNode(context, text); // NEW-ARRAY-VALUE
        context->currNode->addChild(node);

        context->currAttribute = context->token; // IMPORTANT! Attrbute candidate // nullptr;

        // Level DOWN
        context->levelDown(node, true);
    }

    void onStartArrayNullValue(yaml::YamlParserContext* context) {

        printEventItem(context->verboseDebug, "SPACE COUNT: ", std::to_string(context->spaceCount));

        context->currEvent = START_ARRAY_VALUE;
        context->currAttribute = nullptr;

        node::Node* node = createArrayNullNode(context); // NEW-ARRAY-VALUE
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);
    }

    void onStartArrayNullValue_2(yaml::YamlParserContext* context) {

        printEventItem(context->verboseDebug, "SPACE COUNT: ", std::to_string(context->spaceCount));

        context->currEvent = START_ARRAY_VALUE;
        context->currAttribute = nullptr;

        // 1
        node::Node* node = createArrayNullNode(context); // NEW-ARRAY-VALUE-1
        context->currNode->addChild(node);

        // 2
        node = createArrayNullNode(context);             // NEW-ARRAY-VALUE-2
        context->currNode->addChild(node);

        // Level DOWN
        context->levelDown(node, true);
    }

    void onStartLineValue(yaml::YamlParserContext* context) {

        printEventItem(context->verboseText, MESSAGE_ADD_VALUE_LINE, context->token);

        //////////////////////////////////////////////////////

        std::string text = context->token;

        // TODO: Check indent. If indent s not correct set error and return !!!

        context->addLine(text); // NO TRIM !!! Need spaces !!!

        context->currAttribute = context->token; // Attrbute candidate

        /////////////////////////////////////////////////////                    
        
    }

    void onStartComment(yaml::YamlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_COMMENT, context->token);

        // Save currEvent
        if (context->currEvent != START_COMMENT) {
            // IMPORTANT! For non comment event only. 
            // Because we can replace orignal event
            context->prevEvent = context->currEvent;
            context->currEvent = START_COMMENT;
        }
    }

    void onEndComment(yaml::YamlParserContext* context) {

        //context->isRestored = true;

        printEventItem(context->verbose, MESSAGE_END_COMMENT, context->token);

        // Restore currEvent
        context->currEvent = context->prevEvent;
        //context->currEvent = END_COMMENT;

        // TODO: Skip comment?

        // TODO: Start Cmment Node.
        // Add it to Current Node 
        // or set Inline Comment of Current Node (if it without New Line)
        // Don't change CurrentNode!
        // Don't go down!
    }

    void lookupNode(yaml::YamlParserContext* context, int indent) {

        if (context == nullptr) {
            // ERROR
            return;
        }

        node::Node* currNode = context->currNode;
        node::Node* lastNode = context->lastNode;

        if (lastNode == nullptr) {
            // DEBUG
            printEventItem(context->verboseDebug, "INDENT -> STAY THIS [LAST is NULL]"); // ERROR
            return;
        }

        int currIndent = currNode->getIndent();
        int lastIndent = lastNode->getIndent();        

        if (currNode == lastNode) {

            // DEBUG
            printEventItem(context->verboseDebug, "CURR == LAST");

        } else {

            // DEBUG
            printEventItem(context->verboseDebug, "CURR <> LAST");

        }

        // DEBUG
        printEventItem(context->verboseDebug, "CURR_INDENT: " + std::to_string(currIndent));
        printEventItem(context->verboseDebug, "LAST_INDENT: " + std::to_string(lastIndent));
        printEventItem(context->verboseDebug, "THIS_INDENT: " + std::to_string(indent));


        if (isIgnoreIndent(context->currNode)) {

            // DEBUG
            printEventItem(context->verboseDebug, "INDENT -> STAY THIS [JSON]");
            return;
        }

        ////

        node::Node* parent = currNode->getParent();
                
        if (indent == currIndent) {
            
            // GO UP (1): PARENT

            if (parent == nullptr) {
                
                // DEBUG
                printEventItem(context->verboseDebug, "INDENT -> STAY THIS [ROOT]");
                return;
            }

            int count = 1;

            if (isWrapperObjectType(currNode) && isAttributeType(parent)) {
                parent = parent->getParent();
                count++;
            } else if (isWrapperObjectType(parent) && isAttributeType(parent->getParent())) {

                // TODO: WARNING !!!
                //parent = parent->getParent()->getParent();
                //count++;
            }


            //if (isWrapperObjectType(currNode) && isAttributeType(parent)) {
            //    parent = parent->getParent();
            //    count++;
            //}
            
            //if (isWrapperObjectType(parent) && isAttributeType(parent->getParent())) {
            //    parent = parent->getParent()->getParent();
            //    count++;
            //}

            // DEBUG
            if (count > 1) {
                printEventItem(context->verboseDebug, "INDENT -> GO PARENT (" + std::to_string(count) + ")");
            } else {
                printEventItem(context->verboseDebug, "INDENT -> GO PARENT");
            }

            // Level UP (N)
            context->levelUp(parent, count); // No Sync - Later            

            return;
        }

        ////
        
        if (indent > currIndent) {

            // DEBUG
            printEventItem(context->verboseDebug, "INDENT -> STAY THIS");
            return;

        }

        // DEBUG
        printEventItem(context->verboseDebug, "INDENT -> GO PARENT [-]");

        // Level UP
        context->levelUp(parent); // No Sync - Later

        lookupNode(context, indent);

    }

    void tryLookupNode(yaml::YamlParserContext* context, int indent) {

        //printEventItem(context->verboseDebug, "CURR_NODE: " + toNodeString(context->currNode));
        //printEventItem(context->verboseDebug, "LAST_NODE: " + toNodeString(context->lastNode));

        if (context->lastNode != nullptr && !isIgnoreIndent(context->currNode)) {
            int lastIndent = context->lastNode->getIndent();
            if (indent > lastIndent && !isContainerType(context->lastNode)) {
                std::string name = context->currAttribute;
                context->errorCode = 1;
                context->errorMessage = "YAML Parser: Cannot add attribute '" + name + "'. Last Node is not container: type=" + std::to_string(context->lastNode->getType());
                return;
            }
        }

        lookupNode(context, indent);

        if (context->currNode == nullptr) {
            std::string name = context->currAttribute;
            context->errorCode = 1;            
            context->errorMessage = "YAML Parser: Cannot add attribute '" + name + "'. Found Node is NULL";
            return;
        }

        //printEventItem(context->verboseDebug, "REAL_NODE: " + toNodeString(context->currNode));

        if (!isContainerType(context->currNode)) {
            std::string name = context->currAttribute;
            context->errorCode = 1;
            context->errorMessage = "YAML Parser: Cannot add attribute '" + name + "'. Found Node is not container: type=" + std::to_string(context->currNode->getType());
            return;
        }

    }


}