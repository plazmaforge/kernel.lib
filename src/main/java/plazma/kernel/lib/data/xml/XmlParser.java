/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.kernel.lib.data.xml;

import java.util.HashMap;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.str.StrLib;
import static plazma.kernel.lib.text.PrinterHelper.*;

public class XmlParser {

    public static final int NONE = 0;

    // Global events (tag, attribute)
    
    public static final int START_TAG = 1;                         // <, </

    public static final int END_TAG = 2;                           // >, />

    public static final int START_TAG_NAME = 3;

    //

    public static final int START_ATTRIBUTE_NAME = 4;

    public static final int START_ATTRIBUTE_EQ = 5;               // =

    public static final int START_ATTRIBUTE_VALUE = 6;
    
    //
    
    public static final int START_COMMA = 7;                       // ,

    //

    public static final int START_COMMENT_TAG = 8;                 // <!--
    
    public static final int START_CDATA_SECTION_TAG = 9;           // <![CDATA[

    public static final int START_PROLOG_TAG = 10;                 // <?xml

    public static final int START_PROCESSING_INSTRUCTION_TAG = 11; // <?

    public static final int START_DOCUMENT_TYPE_TAG = 12;          // <!DOCTYPE

    public static final int START_DTD_TAG = 13;                    // <!

    public static final int START_SCRIPT_TAG = 14;                 // <%

    //

    public static final int START_BR = 15;                         // [

    public static final int START_QT = 16;                         // (

    //

    public static final int END_COMMENT_TAG = 17;                  // -.
    
    public static final int END_CDATA_SECTION_TAG = 18;            // ]]>

    public static final int END_PROCESSING_INSTRUCTION_TAG = 19;   // ?>

    public static final int END_SCRIPT_TAG = 20;                   // %>

    //

    public static final int END_BR = 21;                           // ]

    public static final int END_QT = 22;                           // )

    // Node events

    public static final int START_NODE = 1001;

    public static final int END_NODE = 1002;

    public static final int END_SINGLE_NODE = 1003;
    

    // Node types

    /**
     * The node is an <code>Element</code>.
     */
    public static final int ELEMENT_NODE              = 1;
    /**
     * The node is an <code>Attr</code>.
     */
    public static final int ATTRIBUTE_NODE            = 2;
    /**
     * The node is a <code>Text</code> node.
     */
    public static final int TEXT_NODE                 = 3;
    /**
     * The node is a <code>CDATASection</code>.
     */
    public static final int CDATA_SECTION_NODE        = 4;
    /**
     * The node is an <code>EntityReference</code>.
     */
    public static final int ENTITY_REFERENCE_NODE     = 5;
    /**
     * The node is an <code>Entity</code>.
     */
    public static final int ENTITY_NODE               = 6;
    /**
     * The node is a <code>ProcessingInstruction</code>.
     */
    public static final int PROCESSING_INSTRUCTION_NODE = 7;
    /**
     * The node is a <code>Comment</code>.
     */
    public static final int COMMENT_NODE              = 8;
    /**
     * The node is a <code>Document</code>.
     */
    public static final int DOCUMENT_NODE             = 9;
    /**
     * The node is a <code>DocumentType</code>.
     */
    public static final int DOCUMENT_TYPE_NODE        = 10;
    /**
     * The node is a <code>DocumentFragment</code>.
     */
    public static final int DOCUMENT_FRAGMENT_NODE    = 11;
    /**
     * The node is a <code>Notation</code>.
     */
    public static final int NOTATION_NODE             = 12;


    //// ALT: DOCTYPE NODE ////

    public static final int ELEMENT_DECL_NODE         = 21;

    public static final int ATTRLIST_DECL_NODE        = 22;

    public static final int ENTITY_DECL_NODE          = ENTITY_NODE;

    public static final int NOTATION_DECL_NODE        = NOTATION_NODE;


    //// ALT: SCRIPT NODE ////

    public static final int SCRIPT_NODE               = 23;

    ////
    
    public static final String MESSAGE_TOKEN                            = "TOKEN                 : ";
    public static final String MESSAGE_TEXT_TOKEN                       = "Text token            : ";
    public static final String MESSAGE_START_TAG                        = "Start Tag";
    public static final String MESSAGE_START_PROLOG_TAG                 = "Start Tag             : <?xml";
    public static final String MESSAGE_START_PROCESSING_INSTRUCTION_TAG = "Start Tag             : <?";
    public static final String MESSAGE_START_DOCUMENT_TYPE_TAG          = "Start Tag             : <!DOCTYPE";
    public static final String MESSAGE_START_DTD_TAG                    = "Start Tag             : <!";
    public static final String MESSAGE_START_SCRIPT_TAG                 = "Start Tag             : <%";
    public static final String MESSAGE_START_COMMENT_TAG                = "Start Tag             : #comment";
    public static final String MESSAGE_START_CDATA_SECTION_TAG          = "Start Tag             : #cdata-section";    

    public static final String MESSAGE_END_TAG                          = "End Tag               : ";
    public static final String MESSAGE_END_PROCESSING_INSTRUCTION_TAG   = "End Tag               : ?";
    public static final String MESSAGE_END_SCRIPT_TAG                   = "End Tag               : %>";

    public static final String MESSAGE_START_NODE                       = "Start Node            : ";
    public static final String MESSAGE_END_NODE                         = "End Node              : ";
    public static final String MESSAGE_END_SINGLE_NODE                  = "End Node [/]          : ";

    public static final String MESSAGE_START_ATTRIBUTE                  = "Start Attribute       : ";
    public static final String MESSAGE_START_ATTRIBUTE_VALUE            = "Start Attribute Value : ";

    public static final String MESSAGE_SET_ATTRIBUTE_VALUE              = "Set Attribute Value   : ";
    public static final String MESSAGE_SET_ATTRIBUTE_VALUE_             = "Set Attribute Value ^ : "; 

    public static final String MESSAGE_SET_TEXT                         = "Set TEXT              : ";
    public static final String MESSAGE_SET_COMMENT                      = "Set COMMENT           : ";
    public static final String MESSAGE_SET_CDATA_SECTION                = "Set CDATA             : ";
    public static final String MESSAGE_SET_BR                           = "Set []                : ";

    ////

    public static final String ERROR_INVALID_XML_TAG                    = "Invalid XML Structure: Tag must be open by '<' or '</' before close ";
    public static final String ERROR_INVALID_XML_COMMENT_TAG            = "Invalid XML Structure: Tag must be open by '<!--' before close '-->'";
    public static final String ERROR_INVALID_XML_CDATA_SECTION_TAG      = "Invalid XML Structure: Tag must be open by '<![CDATA[' before close ']]>'";
    public static final String ERROR_INVALID_XML_ATTRIBUTE_NAME         = "Invalid XML Structure: Attribute name must be setting before '='";

    public static final String ERROR_CURRENT_NODE_IS_NULL               = "XML Parser: Current Node is NULL";
    public static final String ERROR_RETURN_INCONSISTENT_DATA           = "XML Parser: Return inconsistent data";

    ////

    public static final String DOCUMENT_TYPE_NODE_NAME                  = "#document-type";
    public static final String SCRIPT_NODE_NAME                         = "#script";
    public static final String COMMENT_NODE_NAME                        = "#comment";
    public static final String CDATA_SECTION_NODE_NAME                  = "#cdata-section";
    public static final String TEXT_NODE_NAME                           = "#text";

        
    public Node parseXmlFromTokens(String[] tokens) {
        return parseXmlFromTokens(null, tokens);        
    }
    
    public Node parseXmlFromTokens(XmlReaderConfig config, String[] tokens) {

        Node root = new Node();
        if (tokens == null || tokens.length == 0) {
            return root;
        }

        XmlParserContext context = new XmlParserContext();

        context.currEvent = NONE;
        context.nodeEvent = NONE;
        context.currTag = null;
        context.currAttribute = null;

        context.level = 0;
        context.map = new HashMap<>();
        context.currNode = root;
        context.verbose = false; // TODO: set in up
        
        if (config != null) {
            context.verbose = config.verbose;
            context.verboseToken = config.verboseToken;
            context.verboseText = config.verboseText;
        }

        int tokenLen = tokens.length;

        for (int i = 0; i < tokenLen; i++) {
            
            // Check Current Node
            //////////////////////////////////////

            if (context.currNode == null) {
                error(ERROR_CURRENT_NODE_IS_NULL);

                if (context.isOptimsticMode) {
                    // Inconsistent Data
                    warn(ERROR_RETURN_INCONSISTENT_DATA);
                    return root;
                }

                return null;
            }
            
            // Get token
            //////////////////////////////////////            

            context.token = tokens[i];
            
            
            if (context.token == null) {
                continue;
            }
            
            printEvent(context.verboseToken, MESSAGE_TOKEN, context.token);

            if (context.token.equals("<") || context.token.equals("</")) {

                onStartTag(context);

            } else if (context.token.equals(">") || context.token.equals("/>")) {

                onEndTag(context);

            } else if (context.token.equals("=")) {

                onStartAttributeEq(context);
                
            } else if (context.token.equals("<!--")) {
                
                onStartCommentTag(context);

            } else if (context.token.equals("-->")) {

                onEndCommentTag(context);                

            } else if (context.token.equals("<![CDATA[")) {

                onStartCDATASectionTag(context);

            } else if (context.token.equals("]]>")) {

                onEndCDATASectionTag(context);
                
             //////////////////////////////////////////////////
                
            } else if (context.token.equals("<?")) {

                onStartProcessingInstructionTag(context);

            } else if (context.token.equals("?>")) {

                onEndProcessingInstructionTag(context);

            } else if (context.token.equals("<!")) {

                onStartDTDTag(context);

            } else if (context.token.equals("<%")) {

                onStartScriptTag(context);

            } else if (context.token.equals("%>")) {

                onEndScriptTag(context);

            //////////////////////////////////////////////////


            } else if (context.token.equals("[")) {

                context.currEvent = START_BR;

            } else if (context.token.equals("]")) {

                context.currEvent = END_BR;


            //////////////////////////////////////////////////
                

            } else {

                // Processing any text
                printEventItem(context.verbose, MESSAGE_TEXT_TOKEN, context.token);

                // ATTRIBUTE-EVENT
                if (context.currEvent == START_ATTRIBUTE_EQ) {

                    onStartAttributeValue(context);

                // ATTRIBUTE-EVENT
                } else if (context.currEvent == START_TAG_NAME 
                    || context.currEvent == START_ATTRIBUTE_VALUE) {

                    onStartAttributeName(context);

                // ATTRIBUTE-EVENT
                } else if (context.currEvent == START_ATTRIBUTE_NAME) {

                    onStartAttributeNameValue(context);

                // TAG-EVENT
                } else if (context.currEvent == START_TAG 
                    || context.currEvent == START_PROCESSING_INSTRUCTION_TAG
                    || context.currEvent == START_DTD_TAG) {
                    
                    int currEvent = context.currEvent;

                    context.currEvent = START_TAG_NAME;
                    context.currTag = context.token;

                    // <, </, <?, <!
                    if (context.nodeEvent == START_NODE) {
                        
                        // <TAG-NAME: DOWN
                        if (currEvent == START_PROCESSING_INSTRUCTION_TAG) {
                            // <?
                            onStartNode(context, PROCESSING_INSTRUCTION_NODE, context.token); // <?tag
                        } else if (currEvent == START_DTD_TAG) {
                            // <!
                            onStartNode(context, DOCUMENT_TYPE_NODE, context.token);          // <!tag
                        } else {
                            // <
                            onStartNode(context, ELEMENT_NODE, context.token);                // <tag
                        }

                    } else if (context.nodeEvent == END_NODE) {

                        // </TAG-NAME: UP
                        onEndNode(context);
                    } else {
                        // TODO: Fix and use StrLib.isBlank()
                        if (StrLib.normalize(context.token) != null) {
                            error("Lost token: ", context.token, " in START_TAG");
                        }
                        //error("Lost token: ", context.token, " in START_TAG");
                    }
                    
                // TAG-EVENT
                } else if (context.currEvent == END_TAG 
                        || context.currEvent == END_PROCESSING_INSTRUCTION_TAG // Mybe: EROOR
                        || context.currEvent == START_COMMENT_TAG
                        || context.currEvent == START_CDATA_SECTION_TAG
                        || context.currEvent == START_BR) {

                    onText(context);

                }  else {
                    // TODO: Fix and use StrLib.isBlank()
                    if (StrLib.normalize(context.token) != null) {
                        error("Lost token: ", context.token, " in START_TAG");
                    }
                    //error("Lost token: ", context.token, " in START_TAG");
                }

            }

        }

        return root;

    }
    
    //// PROCESSING ////

    //// START_TAG ////
    
    protected void onStartTag(XmlParserContext context) {
        context.currEvent = START_TAG;
        context.nodeEvent = START_NODE; // NONE
        context.currTag = null;
        context.currAttribute = null;

        if (context.token.equals("</")) {

            // SAX: endElement
            context.nodeEvent = END_NODE;
        }
        
        // NEXT: START-NODE

    }

    protected void onStartPrologTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_PROLOG_TAG);

        context.currEvent = START_PROLOG_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = "xml"; // TODO
        context.currAttribute = null;

        // Force create Node end DOWN
        onStartNode(context, PROCESSING_INSTRUCTION_NODE, "xml");

    }

    protected void onStartProcessingInstructionTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_PROCESSING_INSTRUCTION_TAG);

        context.currEvent = START_PROCESSING_INSTRUCTION_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = null;
        context.currAttribute = null;

        // NEXT: START-NODE
    }

    protected void onStartDocumentTypeTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_DOCUMENT_TYPE_TAG);

        context.currEvent = START_DOCUMENT_TYPE_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = DOCUMENT_TYPE_NODE_NAME;
        context.currAttribute = null;

        // Force create Node end DOWN
        onStartNode(context, DOCUMENT_TYPE_NODE, DOCUMENT_TYPE_NODE_NAME);

    }

    protected void onStartDTDTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_DTD_TAG);

        context.currEvent = START_DTD_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = null;
        context.currAttribute = null;

        // NEXT: START-NODE
    }

    protected void onStartScriptTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_SCRIPT_TAG);

        // Html - Non XML
        context.currEvent = START_SCRIPT_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = SCRIPT_NODE_NAME;
        context.currAttribute = null;

        onStartNode(context, SCRIPT_NODE, SCRIPT_NODE_NAME);
    }

    protected void onStartCommentTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_COMMENT_TAG);

        context.currEvent = START_COMMENT_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = COMMENT_NODE_NAME;
        context.currAttribute = null;

        if (isIgnoreContentByEvent(context.currEvent)) {
            context.nodeEvent = NONE;
            return;
        }

        onStartNode(context, COMMENT_NODE, COMMENT_NODE_NAME);

    }

    protected void onStartCDATASectionTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_CDATA_SECTION_TAG);

        context.currEvent = START_CDATA_SECTION_TAG;
        context.nodeEvent = START_NODE;
        context.currTag = CDATA_SECTION_NODE_NAME;
        context.currAttribute = null;

        // Force create Node end DOWN
        onStartNode(context, CDATA_SECTION_NODE, CDATA_SECTION_NODE_NAME);

    }


    //// END-TAG ////
    
    protected  void onEndTag(XmlParserContext context) {
        printEvent(context.verbose, MESSAGE_END_TAG + context.currTag);

        // TAG-EVENT
        if (context.currEvent != START_TAG 
                && context.currEvent != START_TAG_NAME
                && context.currEvent != START_ATTRIBUTE_NAME 
                && context.currEvent != START_ATTRIBUTE_VALUE) {
            error(ERROR_INVALID_XML_TAG, context.token);
        }

        // SAX: eq(context.token, ">") : startElement

        context.currEvent = END_TAG;
        context.nodeEvent = END_NODE;
        context.currTag = null;

        if (context.token.equals(">") && context.currAttribute != null) {

            // Special mode to add lost/last single attribute (without value)
            onStartAttributeNameValue2(context);
        }

        context.currAttribute = null;

        if (context.token.equals(">") && context.currNode.getType() == DOCUMENT_TYPE_NODE) {

            // Special mode to clode <!DOCTYPE node
            onEndNode(context);

        } else if (context.token.equals("/>")) {
            context.nodeEvent = END_SINGLE_NODE;

            onEndSingleNode(context);

        }

    }
    
    protected void onEndProcessingInstructionTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_PROCESSING_INSTRUCTION_TAG, context.currTag);

        context.currEvent = END_PROCESSING_INSTRUCTION_TAG;
        context.nodeEvent = END_NODE;
        context.currTag = null;
        context.currAttribute = null;

        onEndNode(context);
    }

    protected void onEndScriptTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_SCRIPT_TAG);

        // Html - Non XML
        context.currEvent = END_SCRIPT_TAG;
        context.nodeEvent = END_NODE;
        context.currTag = null;
        context.currAttribute = null;

        onEndNode(context);
    }

    protected void onEndCommentTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_TAG, COMMENT_NODE_NAME);

        // TAG-EVENT
        if (context.currEvent != START_COMMENT_TAG) {
            error(ERROR_INVALID_XML_COMMENT_TAG);
        }

        // SAX: eq(context.token, ">") : startElement

        context.currEvent = END_COMMENT_TAG;
        context.nodeEvent = END_NODE;
        context.currTag = null;
        context.currAttribute = null;

        if (isIgnoreContentByEvent(context.currEvent)) {
            return;
        }

        // Force UP
        onEndNode(context);

    }

    protected void onEndCDATASectionTag(XmlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_TAG, CDATA_SECTION_NODE_NAME);

        // TAG-EVENT
        if (context.currEvent != START_CDATA_SECTION_TAG) {
            error(ERROR_INVALID_XML_CDATA_SECTION_TAG);
        }

        // SAX: eq(context.token, ">") : startElement

        context.currEvent = END_CDATA_SECTION_TAG;
        context.nodeEvent = END_NODE;
        context.currTag = null;
        context.currAttribute = null;

        // Force UP
        onEndNode(context);

    }

    //// PROCESSING NODE EVENTS ////

    //// TOKEN ////

    protected void onStartAttributeEq(XmlParserContext context) {
        // ATTRIBUTE-EVENT
        if (context.currEvent != START_ATTRIBUTE_NAME) {
            error(ERROR_INVALID_XML_ATTRIBUTE_NAME);
        }
        context.currEvent = START_ATTRIBUTE_EQ;
    }

    protected void onStartAttributeName(XmlParserContext context) {
        
        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE, context.token);

        context.currEvent = START_ATTRIBUTE_NAME;
        context.currAttribute = context.token;
        //context.currNode.addAttribute(normalizeName(context.currAttribute), null);
    }

    protected void onStartAttributeValue(XmlParserContext context) {
        
        printEventItem(context.verbose, MESSAGE_SET_ATTRIBUTE_VALUE, toSafeString(context.currAttribute), "=", context.token);

        context.currEvent = START_ATTRIBUTE_VALUE;

        context.currNode.addAttribute(normalizeName(context.currAttribute), normalizeAttribute(context.token));
        context.currAttribute = null;
        
    }

    protected void onStartAttributeNameValue(XmlParserContext context) {
        
        printEventItem(context.verbose, MESSAGE_SET_ATTRIBUTE_VALUE_, toSafeString(context.currAttribute)); 
        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE_VALUE, context.token);
                
        context.currEvent = START_ATTRIBUTE_NAME;

        // Add previous attribute with empty value <?my x y z my?>
        context.currNode.addAttribute(normalizeName(context.currAttribute), null);                
        context.currAttribute = context.token;
    }
        
    protected void onStartAttributeNameValue2(XmlParserContext context) {

        printEventItem(context.verbose, MESSAGE_SET_ATTRIBUTE_VALUE_, toSafeString(context.currAttribute));

        // Add previous attribute with empty value <?my x y z my?>
        context.currNode.addAttribute(normalizeName(context.currAttribute), null);
    }
    
    //// START-NODE ////

    protected void onStartNode(XmlParserContext context, int type, String name) {

        printEvent(context.verbose, MESSAGE_START_NODE, name);

        // Level down
        openNode(context, type, name);
    }

    protected void onStartNode(XmlParserContext context) {
        onStartNode(context, 0, context.token);        
    }

    protected void onEndSingleNode(XmlParserContext context) {        
        
        printEvent(context.verbose, MESSAGE_END_SINGLE_NODE, context.currNode.getName());

        // Level up
        closeNode(context);
    }

    protected void onEndNode(XmlParserContext context) {
                
        printEvent(context.verbose, MESSAGE_END_NODE, context.token);

        // Level up
        closeNode(context);
    }

    protected void onText(XmlParserContext context) {

        if (context.token == null) {
            return;
        }
        
        if (isIgnoreContentByEvent(context.currEvent)) {
            return;
        }

        // 'isNormalze' flag overrides 'skipEmpty' flag for each type (Text, Comment, CData)
        // If 'isNormalze' is true we will skip empty text after normalization

        boolean trimText = false;         // RAW
        boolean trimComment = false;      // RAW
        boolean trimCData = false;        // RAW

        boolean skipEmptyText = true;     // SKIP
        boolean skipEmptyComment = false;
        boolean skipEmptyCData = false;
        
        
        String text = null;

        if (context.currEvent == END_TAG) {

            // TEXT
            text = normalizeText(context.token, trimText, skipEmptyText);
            if (text == null) {
                return;
            }
 
            ////

            context.currEvent = NONE;
            context.nodeEvent = START_NODE;
            context.currTag = TEXT_NODE_NAME;;
            context.currAttribute = null;

            onStartNode(context, TEXT_NODE, TEXT_NODE_NAME); // ==>

            context.currNode.setText(text);
            printEventItem(context.verbose, MESSAGE_SET_TEXT + context.currNode.getName() + ", Text: " + text);

            onEndNode(context);                              // <==

            context.currEvent = NONE;
            context.nodeEvent = END_NODE;
            context.currTag = null;
            context.currAttribute = null;

            ////

        } else if (context.currEvent == START_COMMENT_TAG) {

            // COMMENT
            text = normalizeText(context.token, trimComment, skipEmptyComment);
            if (text == null) {
                return;
            }
 
            context.currNode.setText(text);
            printEventItem(context.verbose, MESSAGE_SET_COMMENT + context.currNode.getName() + ", Text: " + text);        


        } else if (context.currEvent == START_CDATA_SECTION_TAG) {

            // CDATA
            text = normalizeText(context.token, trimCData, skipEmptyCData);
            if (text == null) {
                return;
            }
 
            context.currNode.setText(text);
            printEventItem(context.verbose, MESSAGE_SET_CDATA_SECTION + context.currNode.getName() + ", Text: " + text);

        } else if (context.currEvent == START_BR) {

            // []
            text = normalizeText(context.token, false, false);
            if (text == null) {
                return;
            }
 
            context.currNode.setText(text);
            printEventItem(context.verbose, MESSAGE_SET_BR + context.currNode.getName() + ", Text: " + text);

        } else {
            // TODO: error
        }
        
    }
    
    //// HELPER ////

    protected void openNode(XmlParserContext context, int type, String name) {

        Node node = new Node(); // NEW-NODE
        node.setType(type);
        node.setName(name);

        context.currNode.addChild(node);
        context.currNode = node;

        // Level down
        context.level++;
        context.map.put(context.level, context.currTag);
    }

    protected void closeNode(XmlParserContext context) {

        context.map.put(context.level, null);

        // Level up
        context.level--;
        context.currNode = context.currNode.getParent();

    }

    protected boolean isIgnoreContentByEvent(int event) {

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
    
    protected String normalizeAttribute(String str) {
        return StrLib.normalizeQuote(str);
    }

    protected String normalizeText(String str, boolean trimText, boolean skipBlank) {
        return StrLib.normalizeBlank(str, trimText, skipBlank);
    }

    protected String normalizeText(String str) {
        return normalizeText(str, true, true);
    }

    protected String normalizeName(String str) {
        return normalizeText(str, true, true);
    }


}
