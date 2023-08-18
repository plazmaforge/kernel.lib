#include <string>
#include <map>

#include "jsonparser.h"
#include "plazma/kernel/lib/text/printer_helper.h"
//#include "plazma/kernel/lib/str/strlib.h"

using namespace ext;

namespace jsonparser {

    static const char* MESSAGE_TOKEN                            = "TOKEN                 : ";
    static const char* MESSAGE_TEXT_TOKEN                       = "Text token            : ";

    static const char* MESSAGE_START_OBJECT                     = "Start Object";
    static const char* MESSAGE_START_ARRAY                      = "Start Array";
    static const char* MESSAGE_START_ARRAY_VALUE                = "Start Array Value     : ";
    static const char* MESSAGE_START_ATTRIBUTE                  = "Start Attribute       : ";
    static const char* MESSAGE_START_ATTRIBUTE_VALUE            = "Start Attribute Value : ";

    static const char* MESSAGE_END_OBJECT                       = "End Object";    
    static const char* MESSAGE_END_ARRAY                        = "End Array";
    static const char* MESSAGE_END_ARRAY_VALUE                  = "End Array Value";
    static const char* MESSAGE_END_ATTRIBUTE                    = "End Attribute";

    ////

    static const char* ERROR_INVALID_JSON_ATTRIBUTE_NAME        = "Invalid JSON Structure: Attribute name nust be setting before ':'";
    static const char* ERROR_CURRENT_NODE_IS_NULL               = "JSON Parser: Current Node is NULL";
    static const char* ERROR_RETURN_INCONSISTENT_DATA           = "JSON Parser: Return inconsistent data";

    node::Node* parseJsonFromTokens(StringList* tokens) {
        node::Node* node = parseJsonFromTokens(nullptr, tokens);
        return node;
    }

    node::Node* parseJsonFromTokens(json::JsonReaderConfig* config, StringList* tokens) {

        node::Node* root = new node::Node();
        root->setType(OBJECT_TYPE);

        if (tokens == nullptr || tokens->size() == 0) {
            return root;
        }

        json::JsonParserContext* context = new json::JsonParserContext();

        context->prevEvent = NONE;
        context->currEvent = NONE;
        context->nodeEvent = NONE;
        context->currAttribute = nullptr;

        context->level = 0;
        //map<int, char*> map;
        context->currNode = root;

        if (config != nullptr) {
            context->verbose = config->verbose;
            context->verboseToken = config->verboseToken;
            context->verboseText = config->verboseText;
        }

        context->isEmptyContainer = false;
        int tokenLen = tokens->size();

        for (int i = 0; i < tokenLen; i++) {

            // Check Current Node
            //////////////////////////////////////

            if (context->currNode == nullptr) {
                error(ERROR_CURRENT_NODE_IS_NULL);

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
                continue;
            }

            printEvent(context->verboseToken, MESSAGE_TOKEN, context->token);

            if (eq(context->token, "{")) {

                onStartObject(context);

            } else if (eq(context->token, "}")) {

                onEndObject(context);

            } else if (eq(context->token, "[")) {

                onStartArray(context);

            } else if (eq(context->token, "]")) {

                onEndArray(context);
                
            } else if (eq(context->token, ",")) {

                onComma(context);

            } else if (eq(context->token, ":")) {

                onStartAttributeEq(context);

            } else {

                // processing any text
                printEventItem(context->verboseText, MESSAGE_TEXT_TOKEN, context->token);

                // ATTRIBUTE-EVENT
                if (context->currEvent == START_ATTRIBUTE_EQ) {

                    onStartAttributeValue(context);

                // OBJECT-EVENT
                } else if (context->currEvent == START_OBJECT) {

                    onStartAttributeName(context);

                // ARRAY-EVENT
                } else if (context->currEvent == START_ARRAY) {

                    onStartArrayValue(context);
    
                } else {
                    error("Lost token: ", context->token);
                }

            }
        }

        delete context;

        return root;
    }

    void onStartObject(json::JsonParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_OBJECT);
        // printEventItem(context->verbose, currNode->getType());

        context->currEvent = START_OBJECT;
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        node::Node *node = new node::Node(); // NEW-OBJECT
        node->setType(OBJECT_TYPE);

        context->currNode->addChild(node);
        context->currNode = node;

        context->level++;
        // map[context->level] = context->currTag;
    }

    void onEndObject(json::JsonParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_OBJECT);

        // OBJECT-EVENT
        if (context->nodeEvent != START_NODE) {
            // if (context->currEvent != START_OBJECT) {

            // TODO: DISABLE: Use context->prevNodeEvent
            // error("Invalid JSON Structure: Object must be open by '{' before
            // close '}'"); cout << context->nodeEvent << endl;
        }

        context->prevEvent = context->currEvent;
        context->currEvent = END_OBJECT;
        context->nodeEvent = END_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        // printEventItem(context->verbose, "Curr: " + currNode->toString());

        context->isEmptyContainer =
            context->currNode->getType() == jsonparser::OBJECT_TYPE &&
            !context->currNode->hasChildren();

        // CONTAINER-LEVEL
        if (!context->isEmptyContainer ||
            (context->isEmptyContainer && context->prevEvent == END_OBJECT)) {

            // Level up
            context->level--;
            context->currNode = context->currNode->getParent();
            // printEventItem(context->verbose, "Prev: " +
            // currNode->toString());
        }

        // ATTRIBUTE LEVEL
        if (context->currNode->getType() != jsonparser::ATTRIBUTE_TYPE &&
            context->currNode->getParentType() == jsonparser::ATTRIBUTE_TYPE) {

            // Level up
            context->level--;
            context->currNode = context->currNode->getParent();
            // printEventItem(context->verbose, "Prev: " +
            // currNode->toString());
        }

    }

    void onStartArray(json::JsonParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_ARRAY);

        context->currEvent = START_ARRAY;
        context->nodeEvent = START_NODE;
        context->currAttribute = nullptr;

        node::Node *node = new node::Node(); // NEW-ARRAY
        node->setType(ARRAY_TYPE);

        context->currNode->addChild(node);
        context->currNode = node;

        context->level++;
        // map[context->level] = context->currTag;
    }

    void onEndArray(json::JsonParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_ARRAY);

        // ARRAY-EVENT
        if (context->nodeEvent != START_NODE) {
            // if (context->currEvent != START_ARRAY) {

            // TODO: DISABLE: Use context->prevNodeEvent
            // error("Invalid JSON Structure: Array must be open by '[' before
            // close ']'");
        }

        context->prevEvent = context->currEvent;
        context->currEvent = END_ARRAY;
        context->nodeEvent = END_NODE;
        context->currAttribute = nullptr;

        context->map[context->level] = nullptr;

        // printEventItem(context->verbose, "Curr: " + currNode->toString());

        context->isEmptyContainer =
            context->currNode->getType() == jsonparser::ARRAY_TYPE &&
            !context->currNode->hasChildren();

        // CONTAINER-LEVEL
        if (!context->isEmptyContainer ||
            (context->isEmptyContainer && context->prevEvent == END_ARRAY)) {

            // Level up
            context->level--;
            context->currNode = context->currNode->getParent();
            // printEventItem(context->verbose, "Prev: " +
            // currNode->toString());
        }

        // ATTRIBUTE LEVEL
        if (context->currNode->getType() != jsonparser::ATTRIBUTE_TYPE &&
            context->currNode->getParentType() == jsonparser::ATTRIBUTE_TYPE) {

            // Level up
            context->level--;
            context->currNode = context->currNode->getParent();
            // printEventItem(context->verbose, "Prev: " +
            // currNode->toString());
        }

    }

    void onComma(json::JsonParserContext* context) {

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

        // Level up
        context->level--;
        context->currNode = context->currNode->getParent();
    }

    void onStartAttributeEq(json::JsonParserContext* context) {

        // ATTRIBUTE-EVENT
        if (context->currEvent != START_ATTRIBUTE_NAME) {
            error(ERROR_INVALID_JSON_ATTRIBUTE_NAME);
        }

        context->currEvent = START_ATTRIBUTE_EQ;
    }

    void onStartAttributeValue(json::JsonParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE_VALUE, toSafeString(context->currAttribute), "=", context->token);

        context->currEvent = START_ATTRIBUTE_VALUE;

        // OLD !!!
        // node::Node *node = new node::Node(); // NEW-ATTRIBUTE
        // node->setName(context->currAttribute);
        // node->setText(context->token);
        // currNode->addChild(node);
        // currNode = node;
        //
        // context->level++;

        // NEW !!!
        context->currNode->setText(context->token);

        context->currAttribute = nullptr;
    }

    void onStartAttributeName(json::JsonParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE, context->token);
        // printEventItem(context->verbose, currNode->getType());
        // printEventItem(context->verbose, currNode->getName());

        context->currEvent = START_ATTRIBUTE_NAME;
        context->currAttribute = context->token;

        // NEW !!!
        node::Node *node = new node::Node(); // NEW-ATTRIBUTE
        node->setName(context->currAttribute);
        node->setType(ATTRIBUTE_TYPE);

        context->currNode->addChild(node);
        context->currNode = node;

        context->level++;
    }

    void onStartArrayValue(json::JsonParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ARRAY_VALUE, context->token);
        // printEventItem(context->verbose, currNode->getType());

        context->currEvent = START_ARRAY_VALUE;
        context->currAttribute = nullptr;

        node::Node *node = new node::Node(); // NEW-ARRAY-VALUE
        node->setText(context->token);
        context->currNode->addChild(node);
        context->currNode = node;

        context->level++;
    }


}