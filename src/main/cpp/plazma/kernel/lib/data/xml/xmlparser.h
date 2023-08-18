#ifndef PLAZMA_KERNEL_DATA_XML_XML_PARSER_H
#define PLAZMA_KERNEL_DATA_XML_XML_PARSER_H

#include "XmlParserContext.h"
#include "XmlReaderConfig.h"
#include "plazma/kernel/lib/data/node/Node.h"
#include "plazma/kernel/lib/ext/StringList.h"

using namespace ext;

namespace xmlparser {

    const int NONE = 0;
    
    // Global events (tag, attribute)

    const int START_TAG = 1;                         // <, </

    const int END_TAG = 2;                           // >, />

    const int START_TAG_NAME = 3;

    //

    const int START_ATTRIBUTE_NAME = 4;

    const int START_ATTRIBUTE_EQ = 5;                // =

    const int START_ATTRIBUTE_VALUE = 6;

    //
    
    const int START_COMMA = 7;                       // ,

    //

    const int START_COMMENT_TAG = 8;                 // <!--
    
    const int START_CDATA_SECTION_TAG = 9;           // <![CDATA[

    const int START_PROLOG_TAG = 10;                 // <?xml

    const int START_PROCESSING_INSTRUCTION_TAG = 11; // <?

    const int START_DOCUMENT_TYPE_TAG = 12;          // <!DOCTYPE

    const int START_DTD_TAG = 13;                    // <!

    const int START_SCRIPT_TAG = 14;                 // <%

    //

    const int START_BR = 15;                         // [

    const int START_QT = 16;                         // (

    //

    const int END_COMMENT_TAG = 17;                  // -->
    
    const int END_CDATA_SECTION_TAG = 18;            // ]]>

    const int END_PROCESSING_INSTRUCTION_TAG = 19;   // ?>

    const int END_SCRIPT_TAG = 20;                   // %>

    //

    const int END_BR = 21;                           // ]

    const int END_QT = 22;                           // )

    // Node events

    const int START_NODE = 1001;

    const int END_NODE = 1002;

    const int END_SINGLE_NODE = 1003;

    // Node types

    /**
     * The node is an <code>Element</code>.
     */
    const int ELEMENT_NODE              = 1;
    /**
     * The node is an <code>Attr</code>.
     */
    const int ATTRIBUTE_NODE            = 2;
    /**
     * The node is a <code>Text</code> node.
     */
    const int TEXT_NODE                 = 3;
    /**
     * The node is a <code>CDATASection</code>.
     */
    const int CDATA_SECTION_NODE        = 4;
    /**
     * The node is an <code>EntityReference</code>.
     */
    const int ENTITY_REFERENCE_NODE     = 5;
    /**
     * The node is an <code>Entity</code>.
     */
    const int ENTITY_NODE               = 6;
    /**
     * The node is a <code>ProcessingInstruction</code>.
     */
    const int PROCESSING_INSTRUCTION_NODE = 7;
    /**
     * The node is a <code>Comment</code>.
     */
    const int COMMENT_NODE              = 8;
    /**
     * The node is a <code>Document</code>.
     */
    const int DOCUMENT_NODE             = 9;
    /**
     * The node is a <code>DocumentType</code>.
     */
    const int DOCUMENT_TYPE_NODE        = 10;
    /**
     * The node is a <code>DocumentFragment</code>.
     */
    const int DOCUMENT_FRAGMENT_NODE    = 11;
    /**
     * The node is a <code>Notation</code>.
     */
    const int NOTATION_NODE             = 12;


    //// ALT: DOCTYPE NODE ////

    const int ELEMENT_DECL_NODE         = 21;

    const int ATTRLIST_DECL_NODE        = 22;

    const int ENTITY_DECL_NODE          = ENTITY_NODE;

    const int NOTATION_DECL_NODE        = NOTATION_NODE;


    //// ALT: SCRIPT NODE ////

    const int SCRIPT_NODE               = 23;

    ////

    node::Node* parseXmlFromTokens(StringList* tokens);

    node::Node* parseXmlFromTokens(xml::XmlReaderConfig* config, StringList* tokens); 

    //// START-TAG ////

    void onStartTag(xml::XmlParserContext* context);

    void onStartPrologTag(xml::XmlParserContext* context);

    void onStartProcessingInstructionTag(xml::XmlParserContext* context);

    void onStartDocumentTypeTag(xml::XmlParserContext* context);

    void onStartDTDTag(xml::XmlParserContext* context);

    void onStartScriptTag(xml::XmlParserContext* context); // ALT

    void onStartCommentTag(xml::XmlParserContext* context);

    void onStartCDATASectionTag(xml::XmlParserContext* context);

    //// END-TAG ////

    void onEndTag(xml::XmlParserContext* context);

    void onEndProcessingInstructionTag(xml::XmlParserContext* context);

    void onEndScriptTag(xml::XmlParserContext* context); // ALT

    void onEndCommentTag(xml::XmlParserContext* context);

    void onEndCDATASectionTag(xml::XmlParserContext* context);

    //// TOKEN ////

    void onStartAttributeEq(xml::XmlParserContext* context);

    ////

    void onStartAttributeName(xml::XmlParserContext* context);

    void onStartAttributeValue(xml::XmlParserContext* context);

    void onStartAttributeNameValue(xml::XmlParserContext* context);

    void onStartAttributeNameValue2(xml::XmlParserContext* context);

    //// START-NODE ////

    void onStartNode(xml::XmlParserContext* context, int type, const char* name);

    void onStartNode(xml::XmlParserContext* context);

    //// END-NODE ////

    void onEndSingleNode(xml::XmlParserContext* context);

    void onEndNode(xml::XmlParserContext* context);

    //// TEXT ////

    void onText(xml::XmlParserContext* context);

    //// HELPER /////

    void openNode(xml::XmlParserContext* context, int type, const char* name);

    void closeNode(xml::XmlParserContext* context);

    bool isIgnoreContentByEvent(int event);

    //// UTILS ////

    std::string normalizeAttribute(char* str);

    std::string normalizeText(char* str, bool trimText, bool skipBlank);

    std::string normalizeText(char* str);

    std::string normalizeName(char* str);
    
}
#endif // PLAZMA_KERNEL_DATA_XML_XML_PARSER_H