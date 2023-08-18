#include <string>

#include "xmlparser.h"
#include "XmlParserContext.h"
#include "XmlReaderConfig.h"
#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/text/ParserContext.h"
#include "plazma/kernel/lib/text/printer_helper.h"
#include "plazma/kernel/lib/str/strlib.h"
#include "plazma/kernel/lib/io/iolib.h"

using namespace ext;
using namespace text;
using namespace xml;

namespace xmlparser {

    static const char* MESSAGE_TOKEN                            = "TOKEN                 : ";
    static const char* MESSAGE_TEXT_TOKEN                       = "Text token            : ";
    static const char* MESSAGE_START_TAG                        = "Start Tag";
    static const char* MESSAGE_START_PROLOG_TAG                 = "Start Tag             : <?xml";
    static const char* MESSAGE_START_PROCESSING_INSTRUCTION_TAG = "Start Tag             : <?";
    static const char* MESSAGE_START_DOCUMENT_TYPE_TAG          = "Start Tag             : <!DOCTYPE";
    static const char* MESSAGE_START_DTD_TAG                    = "Start Tag             : <!";
    static const char* MESSAGE_START_SCRIPT_TAG                 = "Start Tag             : <%";
    static const char* MESSAGE_START_COMMENT_TAG                = "Start Tag             : #comment";
    static const char* MESSAGE_START_CDATA_SECTION_TAG          = "Start Tag             : #cdata-section";    

    static const char* MESSAGE_END_TAG                          = "End Tag               : ";
    static const char* MESSAGE_END_PROCESSING_INSTRUCTION_TAG   = "End Tag               : ?";
    static const char* MESSAGE_END_SCRIPT_TAG                   = "End Tag               : %>";

    static const char* MESSAGE_START_NODE                       = "Start Node            : ";
    static const char* MESSAGE_END_NODE                         = "End Node              : ";
    static const char* MESSAGE_END_SINGLE_NODE                  = "End Node [/]          : ";

    static const char* MESSAGE_START_ATTRIBUTE                  = "Start Attribute       : ";
    static const char* MESSAGE_START_ATTRIBUTE_VALUE            = "Start Attribute Value : ";

    static const char* MESSAGE_SET_ATTRIBUTE_VALUE              = "Set Attribute Value   : ";
    static const char* MESSAGE_SET_ATTRIBUTE_VALUE_             = "Set Attribute Value ^ : "; 

    static const char* MESSAGE_SET_TEXT                         = "Set TEXT              : ";
    static const char* MESSAGE_SET_COMMENT                      = "Set COMMENT           : ";
    static const char* MESSAGE_SET_CDATA_SECTION                = "Set CDATA             : ";
    static const char* MESSAGE_SET_BR                           = "Set []                : ";

    ////

    static const char* ERROR_INVALID_XML_TAG                    = "Invalid XML Structure: Tag must be open by '<' or '</' before close ";
    static const char* ERROR_INVALID_XML_COMMENT_TAG            = "Invalid XML Structure: Tag must be open by '<!--' before close '-->'";
    static const char* ERROR_INVALID_XML_CDATA_SECTION_TAG      = "Invalid XML Structure: Tag must be open by '<![CDATA[' before close ']]>'";
    static const char* ERROR_INVALID_XML_ATTRIBUTE_NAME         = "Invalid XML Structure: Attribute name must be setting before '='";

    static const char* ERROR_CURRENT_NODE_IS_NULL               = "XML Parser: Current Node is NULL";
    static const char* ERROR_RETURN_INCONSISTENT_DATA           = "XML Parser: Return inconsistent data";

    ////

    static const char* DOCUMENT_TYPE_NODE_NAME                  = "#document-type";
    static const char* SCRIPT_NODE_NAME                         = "#script";
    static const char* COMMENT_NODE_NAME                        = "#comment";
    static const char* CDATA_SECTION_NODE_NAME                  = "#cdata-section";
    static const char* TEXT_NODE_NAME                           = "#text";

    node::Node* parseXmlFromTokens(StringList* tokens) {
        node::Node* node = parseXmlFromTokens(nullptr, tokens);
        return node;
    }

    node::Node* parseXmlFromTokens(xml::XmlReaderConfig* config, StringList* tokens) {
        node::Node* root = new node::Node();
        if (tokens == nullptr || tokens->size() == 0) {
            return root;
        }

        xml::XmlParserContext* context = new xml::XmlParserContext();

        context->currEvent = NONE;
        context->nodeEvent = NONE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        context->level = 0;
        context->currNode = root;

        if (config != nullptr) {
            context->verbose = config->verbose;
            context->verboseToken = config->verboseToken;
            context->verboseText = config->verboseText;
        }

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

            if (eq(context->token, "<") || eq(context->token, "</")) {

                onStartTag(context);

            } else if (eq(context->token, ">") || eq(context->token, "/>")) {

                onEndTag(context);

            } else if (eq(context->token, "=")) {

                onStartAttributeEq(context);

            } else if (eq(context->token, "<!--")) {

                onStartCommentTag(context);
            
            } else if (eq(context->token, "-->")) {

                onEndCommentTag(context);

            } else if (eq(context->token, "<![CDATA[")) {

                onStartCDATASectionTag(context);
            
            } else if (eq(context->token, "]]>")) {

                onEndCDATASectionTag(context);

            //////////////////////////////////////////////////

            } else if (eq(context->token, "<?")) {

                onStartProcessingInstructionTag(context);

            } else if (eq(context->token, "?>")) {

                onEndProcessingInstructionTag(context);

            } else if (eq(context->token, "<!")) {

                onStartDTDTag(context);

            } else if (eq(context->token, "<%")) {

                onStartScriptTag(context);

            } else if (eq(context->token, "%>")) {

                onEndScriptTag(context);

            //////////////////////////////////////////////////

            } else if (eq(context->token, "[")) {

                context->currEvent = START_BR;

            } else if (eq(context->token, "]")) {

                context->currEvent = END_BR;

            //////////////////////////////////////////////////

            } else {

                // Processing any text
                printEventItem(context->verboseText, MESSAGE_TEXT_TOKEN, context->token);

                // ATTRIBUTE-EVENT
                if (context->currEvent == START_ATTRIBUTE_EQ) {
                    
                    onStartAttributeValue(context);

                // ATTRIBUTE-EVENT
                } else if (context->currEvent == START_TAG_NAME 
                    || context->currEvent == START_ATTRIBUTE_VALUE) {

                    onStartAttributeName(context);

                // ATTRIBUTE-EVENT
                } else if (context->currEvent == START_ATTRIBUTE_NAME) {

                    onStartAttributeNameValue(context);

                // TAG-EVENT
                } else if (context->currEvent == START_TAG 
                    || context->currEvent == START_PROCESSING_INSTRUCTION_TAG
                    || context->currEvent == START_DTD_TAG) {

                    int currEvent = context->currEvent;

                    context->currEvent = START_TAG_NAME;
                    context->currTag = context->token;

                    // <, </, <?, <!
                    if (context->nodeEvent == START_NODE) {

                        // <TAG-NAME: DOWN
                        if (currEvent == START_PROCESSING_INSTRUCTION_TAG) {
                            // <?
                            onStartNode(context, PROCESSING_INSTRUCTION_NODE, context->token); // <?tag
                        } else if (currEvent == START_DTD_TAG) {
                            // <!
                            onStartNode(context, DOCUMENT_TYPE_NODE, context->token);          // <!tag
                        } else {
                            // <
                            onStartNode(context, ELEMENT_NODE, context->token);                // <tag
                        }
                        
                    } else if (context->nodeEvent == END_NODE) {

                        // </TAG-NAME: UP
                        onEndNode(context);

                    } else {
                        // TODO: Fix and use strlib::isBlank()
                        if (!strlib::normalize(context->token).empty()) {
                            error("Lost token: ", context->token, " in START_TAG");
                        }
                        //error("Lost token: ", context->token, " in START_TAG");
                    }
                    
                // TAG-EVENT
                } else if (context->currEvent == END_TAG
                || context->currEvent == END_PROCESSING_INSTRUCTION_TAG // Mybe: EROOR
                || context->currEvent == START_COMMENT_TAG
                || context->currEvent == START_CDATA_SECTION_TAG
                || context->currEvent == START_BR) {
                    onText(context);
                }  else {
                    // TODO: Fix and use strlib::isBlank()
                    if (!strlib::normalize(context->token).empty()) {
                        error("Lost token: ", context->token);
                    }
                    //error("Lost token: ", context->token);
                }

            }
        }

        delete context;

        return root;
    }

    //// PROCESSING ////

    //// START_TAG ////

    void onStartTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_TAG);

        context->currEvent = START_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;
        
        if (eq(context->token, "</")) {
            // SAX: endElement
            context->nodeEvent = END_NODE;
        }

        // NEXT: START-NODE
    }

    void onStartPrologTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_PROLOG_TAG);

        context->currEvent = START_PROLOG_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = "xml"; // TODO
        context->currAttribute = nullptr;

        // Force create Node end DOWN
        onStartNode(context, PROCESSING_INSTRUCTION_NODE, "xml");

    }

    void onStartProcessingInstructionTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_PROCESSING_INSTRUCTION_TAG);

        context->currEvent = START_PROCESSING_INSTRUCTION_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        // NEXT: START-NODE
    }

    void onStartDocumentTypeTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_DOCUMENT_TYPE_TAG);

        context->currEvent = START_DOCUMENT_TYPE_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = (char*) DOCUMENT_TYPE_NODE_NAME;
        context->currAttribute = nullptr;

        // Force create Node end DOWN
        onStartNode(context, DOCUMENT_TYPE_NODE, DOCUMENT_TYPE_NODE_NAME);

    }

    void onStartDTDTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_DTD_TAG);

        context->currEvent = START_DTD_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        // NEXT: START-NODE
    }

    void onStartScriptTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_SCRIPT_TAG);

        // Html - Non XML
        context->currEvent = START_SCRIPT_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = (char*) SCRIPT_NODE_NAME;
        context->currAttribute = nullptr;

        onStartNode(context, SCRIPT_NODE, SCRIPT_NODE_NAME);
    }

    void onStartCommentTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_COMMENT_TAG);

        context->currEvent = START_COMMENT_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = (char*) COMMENT_NODE_NAME;
        context->currAttribute = nullptr;

        if (isIgnoreContentByEvent(context->currEvent)) {
            context->nodeEvent = NONE;
            return;
        }

        onStartNode(context, COMMENT_NODE, COMMENT_NODE_NAME);

    }

    void onStartCDATASectionTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_START_CDATA_SECTION_TAG);

        context->currEvent = START_CDATA_SECTION_TAG;
        context->nodeEvent = START_NODE;
        context->currTag = (char*) CDATA_SECTION_NODE_NAME;
        context->currAttribute = nullptr;

        // Force create Node end DOWN
        onStartNode(context, CDATA_SECTION_NODE, CDATA_SECTION_NODE_NAME);

    }


    //// END-TAG ////

    void onEndTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_TAG, context->currTag);
        
        // TAG-EVENT
        if (context->currEvent != START_TAG 
            && context->currEvent != START_TAG_NAME 
            && context->currEvent != START_ATTRIBUTE_NAME 
            && context->currEvent != START_ATTRIBUTE_VALUE) {

            // if (context->nodeEvent != START_NODE) {
            // cout << endl << context->currEvent << " " << context->nodeEvent
            // << endl;
            error(ERROR_INVALID_XML_TAG, context->token);
        }

        // SAX: eq(context->token, ">") : startElement

        context->currEvent = END_TAG;
        context->nodeEvent = END_NODE;
        context->currTag = nullptr;
        
        if (eq(context->token, ">") && context->currAttribute != nullptr) {

            // Special mode to add lost/last single attribute (without value)
            onStartAttributeNameValue2(context);
        }

        context->currAttribute = nullptr;

        if (eq(context->token, ">") && context->currNode->getType() == DOCUMENT_TYPE_NODE) {

            // Special mode to clode <!DOCTYPE node
            onEndNode(context);

        } else if (eq(context->token, "/>")) {

            // Special mode to close single node            
            context->nodeEvent = END_SINGLE_NODE;

            onEndSingleNode(context);

        }

    }

    void onEndProcessingInstructionTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_PROCESSING_INSTRUCTION_TAG, context->currTag);

        context->currEvent = END_PROCESSING_INSTRUCTION_TAG;
        context->nodeEvent = END_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        onEndNode(context);
    }

    void onEndScriptTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_SCRIPT_TAG);

        // Html - Non XML
        context->currEvent = END_SCRIPT_TAG;
        context->nodeEvent = END_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        onEndNode(context);
    }

    void onEndCommentTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_TAG, COMMENT_NODE_NAME);

        // TAG-EVENT
        if (context->currEvent != START_COMMENT_TAG) {
            error(ERROR_INVALID_XML_COMMENT_TAG);
        }

        // SAX: eq(context->token, ">") : startElement

        context->currEvent = END_COMMENT_TAG;
        context->nodeEvent = END_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        if (isIgnoreContentByEvent(context->currEvent)) {
            return;
        }

        // Force UP
        onEndNode(context);

    }

    void onEndCDATASectionTag(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_TAG, CDATA_SECTION_NODE_NAME);

        // TAG-EVENT
        if (context->currEvent != START_CDATA_SECTION_TAG) {
            error(ERROR_INVALID_XML_CDATA_SECTION_TAG);
        }

        // SAX: eq(context->token, ">") : startElement

        context->currEvent = END_CDATA_SECTION_TAG;
        context->nodeEvent = END_NODE;
        context->currTag = nullptr;
        context->currAttribute = nullptr;

        // Force UP
        onEndNode(context);

    }


    //// PROCESSING NODE EVENTS ////

    //// TOKEN ////

    void onStartAttributeEq(xml::XmlParserContext* context) {

        // ATTRIBUTE-EVENT
        if (context->currEvent != START_ATTRIBUTE_NAME) {
            // cout << context->currEvent << endl;
            error(ERROR_INVALID_XML_ATTRIBUTE_NAME);
        }
        context->currEvent = START_ATTRIBUTE_EQ;
    }

    void onStartAttributeName(xml::XmlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE, context->token);

        context->currEvent = START_ATTRIBUTE_NAME;
        context->currAttribute = context->token;
        //context->currNode->addAttribute(normalizeName(context->currAttribute), "");
    }

    void onStartAttributeValue(xml::XmlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_SET_ATTRIBUTE_VALUE, toSafeString(context->currAttribute), "=", context->token);

        context->currEvent = START_ATTRIBUTE_VALUE;

        context->currNode->addAttribute(normalizeName(context->currAttribute), normalizeAttribute(context->token));
        context->currAttribute = nullptr;
    }

    void onStartAttributeNameValue(xml::XmlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_SET_ATTRIBUTE_VALUE_, toSafeString(context->currAttribute)); 
        printEventItem(context->verbose, MESSAGE_START_ATTRIBUTE_VALUE, context->token);

        context->currEvent = START_ATTRIBUTE_NAME;

        // Add previous attribute with empty value <?my x y z my?>
        context->currNode->addAttribute(normalizeName(context->currAttribute), "");
        context->currAttribute = context->token;
    }

    void onStartAttributeNameValue2(xml::XmlParserContext* context) {

        printEventItem(context->verbose, MESSAGE_SET_ATTRIBUTE_VALUE_, toSafeString(context->currAttribute));

        // Add previous attribute with empty value <?my x y z my?>
        context->currNode->addAttribute(normalizeName(context->currAttribute), "");
    }


    //// START-NODE ////

    void onStartNode(xml::XmlParserContext* context, int type, const char* name) {

        printEvent(context->verbose, MESSAGE_START_NODE, name);

        // Level down
        openNode(context, type, name);

    }

    void onStartNode(xml::XmlParserContext* context) {
        onStartNode(context, 0, context->token);
    }


    //// END-NODE ////

    void onEndSingleNode(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_SINGLE_NODE, context->currNode->getName());

        // Level up
        closeNode(context);

    }

    void onEndNode(xml::XmlParserContext* context) {

        printEvent(context->verbose, MESSAGE_END_NODE, context->token);

        // Level up
        closeNode(context);

    }

    //// TEXT ////

    void onText(xml::XmlParserContext* context) {
        if (context->token == nullptr) {
            return;
        }

        if (isIgnoreContentByEvent(context->currEvent)) {
            return;
        }

        // 'isNormalze' flag overrides 'skipEmpty' flag for each type (Text, Comment, CData)
        // If 'isNormalze' is true we will skip empty text after normalization

        bool trimText = false;         // RAW
        bool trimComment = false;      // RAW
        bool trimCData = false;        // RAW

        bool skipEmptyText = true;     // SKIP
        bool skipEmptyComment = false;
        bool skipEmptyCData = false;

        std::string text;

        if (context->currEvent == END_TAG) {

            // TEXT
            text = normalizeText(context->token, trimText, skipEmptyText);
            if (text.empty()) {
                return;
            }
 
            //context->currNode->setText(text);
            //printEventItem(context->verbose, "Set TEXT              : " + context->currNode->getName() + ", Text: " + text);

            ////

            context->currEvent = NONE;
            context->nodeEvent = START_NODE;
            context->currTag = (char*) TEXT_NODE_NAME;;
            context->currAttribute = nullptr;

            onStartNode(context, TEXT_NODE, (char*) TEXT_NODE_NAME); // ==>

            context->currNode->setText(text);
            printEventItem(context->verbose, MESSAGE_SET_TEXT + context->currNode->getName() + ", Text: " + text);

            onEndNode(context);                                     // <==

            context->currEvent = NONE;
            context->nodeEvent = END_NODE;
            context->currTag = nullptr;
            context->currAttribute = nullptr;

            ////

        } else if (context->currEvent == START_COMMENT_TAG) {

            // COMMENT
            text = normalizeText(context->token, trimComment, skipEmptyComment);
            if (text.empty()) {
                return;
            }
 
            context->currNode->setText(text);
            printEventItem(context->verbose, MESSAGE_SET_COMMENT + context->currNode->getName() + ", Text: " + text);        


        } else if (context->currEvent == START_CDATA_SECTION_TAG) {

            // CDATA
            text = normalizeText(context->token, trimCData, skipEmptyCData);
            if (text.empty()) {
                return;
            }
 
            context->currNode->setText(text);
            printEventItem(context->verbose, MESSAGE_SET_CDATA_SECTION + context->currNode->getName() + ", Text: " + text);

        } else if (context->currEvent == START_BR) {

            // []
            text = normalizeText(context->token, false, false);
            if (text.empty()) {
                return;
            }
 
            context->currNode->setText(text);
            printEventItem(context->verbose, MESSAGE_SET_BR + context->currNode->getName() + ", Text: " + text);

        } else {
            // TODO: error
        }

    }

    //// HELPER /////

    void openNode(xml::XmlParserContext* context, int type, const char* name) {

        node::Node *node = new node::Node(); // NEW-NODE
        node->setType(type);
        node->setName(name);

        //node->setName2(iolib::utf8_to_ustring(name));
        //printEvent2(context->verbose, "Start Node-2          : ", node->getName2());

        context->currNode->addChild(node);
        context->currNode = node;

        // Level down
        context->level++;
        context->map[context->level] = context->currTag;
    }

    void closeNode(xml::XmlParserContext* context) {

        context->map[context->level] = nullptr;

        // Level up
        context->level--;
        context->currNode = context->currNode->getParent();

    }

    bool isIgnoreContentByEvent(int event) {

        // Not implemented yet
        if (event == START_PROLOG_TAG

        || event == START_PROCESSING_INSTRUCTION_TAG 
        || event == END_PROCESSING_INSTRUCTION_TAG 

        || event == START_DOCUMENT_TYPE_TAG
        || event == START_DTD_TAG


        || event == START_SCRIPT_TAG 
        || event == END_SCRIPT_TAG) {
            return true;
        }

        return false;

        // TODO: optional
        //
        // 'ignore-decl' - ignore declaration: prolog, pi, doctype, dtd
        // 'ignore-prolog'
        // 'ignore-pi'
        // 'ignore-doctype
        // 'ignore-dtd
        // 'ignore-comment'
        // 
        //return event == START_COMMENT_TAG 
        //|| event == END_COMMENT_TAG;

    }

    //// UTILS ////

    std::string normalizeAttribute(char* str) {
        if (str == nullptr) {
            return "";
        }
        return strlib::normalizeQuote(str);
    }

    std::string normalizeText(char* str, bool trimText, bool skipBlank) {
        if (str == nullptr) {
            return "";
        }        
        return strlib::normalizeBlank(str, trimText, skipBlank);
    }

    std::string normalizeText(char* str) {
        return normalizeText(str, true, true);
    }

    std::string normalizeName(char* str) {
        return normalizeText(str, true, true);
    }


}