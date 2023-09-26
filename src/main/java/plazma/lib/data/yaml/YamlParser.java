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

package plazma.lib.data.yaml;

import plazma.lib.data.node.Node;
import plazma.lib.str.StrLib;
import static plazma.lib.text.PrinterHelper.*;

public class YamlParser {
    
    
    // Event types

    public static final int NONE = 0;

    // Object
    
    public static final int START_OBJECT = 1;           // YAML OBJECT

    public static final int END_OBJECT = 2;             // YAML OBJECT

    //
    
    public static final int START_JSON_OBJECT = 10;     // JSON OBJECT

    public static final int END_JSON_OBJECT = 20;       // JSON OBJECT

    // Array
    
    public static final int START_ARRAY = 3;            // YAML ARRAY

    public static final int END_ARRAY = 4;              // YAML ARRAY

    //

    public static final int START_JSON_ARRAY = 30;      // JSON ARRAY

    public static final int END_JSON_ARRAY = 40;        // JSON ARRAY

    // Attribute

    public static final int START_ATTRIBUTE_NAME = 5;

    public static final int START_ATTRIBUTE_EQ = 6;

    public static final int START_ATTRIBUTE_VALUE = 7;

    public static final int END_ATTRIBUTE = 8;

    // Value

    public static final int START_ARRAY_VALUE = 9;

    public static final int END_ARRAY_VALUE = 10;

    public static final int START_ARRAY_MARKER_NL_FIRST  = 9999;

    public static final int START_ARRAY_MARKER_NL        = 9998;

    public static final int START_ARRAY_MARKER_SP_FIRST  = 9997;

    public static final int START_ARRAY_MARKER_SP        = 9996;



    public static final int START_COMMENT = 91;

    public static final int END_COMMENT = 92;


    public static final int START_NEW_LINE = 93;

    public static final int START_IGNORE_LINE = 94;


    // Node

    public static final int START_NODE = 1001;

    public static final int END_NODE = 1002;
    

    // Node types

    public static final int OBJECT_TYPE = 1;

    public static final int ARRAY_TYPE = 2;

    public static final int ATTRIBUTE_TYPE = 3;

    //

    public static final int JSON_OBJECT_SUBTYPE = 10;

    public static final int JSON_ARRAY_SUBTYPE = 20;

    public static final int WRAPPER_OBJECT_SUBTYPE = 70;

    
    public static final String MESSAGE_TOKEN                            = "TOKEN                 : ";
    public static final String MESSAGE_TEXT_TOKEN                       = "Text token            : ";

    public static final String MESSAGE_START_OBJECT                     = "Start Object";
    public static final String MESSAGE_START_JSON_OBJECT                = "Start JSON Object";
    public static final String MESSAGE_START_ARRAY                      = "Start Array";
    public static final String MESSAGE_START_JSON_ARRAY                 = "Start JSON Array";
    public static final String MESSAGE_START_ARRAY_VALUE                = "Start Array Value     : ";
    public static final String MESSAGE_START_ARRAY_MARKER_SP            = "Array Marker [SP]     : ";
    public static final String MESSAGE_START_ARRAY_MARKER_NL            = "Array Marker [NL]     : ";
    public static final String MESSAGE_START_ATTRIBUTE                  = "Start Attribute       : ";
    public static final String MESSAGE_START_ATTRIBUTE_VALUE            = "Start Attribute Value : ";
    public static final String MESSAGE_START_COMMENT                    = "Start Comment         : ";

    public static final String MESSAGE_END_OBJECT                       = "End Object";
    public static final String MESSAGE_END_JSON_OBJECT                  = "End JSON Object";
    public static final String MESSAGE_END_ARRAY                        = "End Array";
    public static final String MESSAGE_END_JSON_ARRAY                   = "End JSON Array";
    public static final String MESSAGE_END_ARRAY_VALUE                  = "End Array Value";
    public static final String MESSAGE_END_ARRAY_MARKER_SP              = "End Array Marker [SP]";
    public static final String MESSAGE_END_ARRAY_MARKER_NL              = "End Array Marker [NL]";
    public static final String MESSAGE_END_ARRAY_MARKER_SP_FIRST        = "End Array Marker [SP] [FIRST]";
    public static final String MESSAGE_END_ARRAY_MARKER_NL_FIRST        = "End Array Marker [NL] [FIRST]";
    public static final String MESSAGE_END_ATTRIBUTE                    = "End Attribute";
    public static final String MESSAGE_END_EQ                           = "End EQ";
    public static final String MESSAGE_END_OTHER                        = "End Other ";
    public static final String MESSAGE_END_COMMENT                      = "End Comment           : ";
    public static final String MESSAGE_ADD_VALUE_LINE                   = "Add value line        : ";

    ////

    public static final String ERROR_INVALID_YAML_ATTRIBUTE_NAME        = "YAML Parser: Invalid YAML Structure - attribute name must be setting before ':'";
    public static final String ERROR_CURRENT_NODE_IS_NULL               = "YAML Parser: Current Node is NULL";
    public static final String ERROR_RETURN_INCONSISTENT_DATA           = "YAML Parser: Return inconsistent data";
    public static final String ERROR_ADD_ATTRIBUTE                      = "YAML Parser: Cannot add attribute";
    public static final String ERROR_FATAL                              = "YAML Parser: Fatal error";

    ////

    public Node parseYamlFromTokens(String[] tokens) {
        Node node = parseYamlFromTokens(null, tokens);
        return node;
    }

    public Node parseYamlFromTokens(YamlReaderConfig config, String[] tokens) {

        Node root = createNode(OBJECT_TYPE);

        if (tokens == null || tokens.length == 0) {
            return root;
        }

        YamlParserContext context = new YamlParserContext();

        context.prevEvent = NONE;
        context.currEvent = START_OBJECT; //NONE; // YAML OBJECT
        context.nodeEvent = NONE;
        context.currAttribute = null;

        context.level = 0;
        //map<int, char*> map;
        //
        context.currNode = root;
        context.lastNode = root;
        //
        if (config != null) {
            context.verbose = config.verbose;
            context.verboseToken = config.verboseToken;
            context.verboseText = config.verboseText;
        }
        //
        //context.verboseDebug = true;
        
        context.init();

        int tokenLen = tokens.length;

        for (int i = 0; i < tokenLen; i++) {

            // Check Current Node
            //////////////////////////////////////

            if (context.currNode == null && context.errorCode == 0) {
                context.errorCode = 1;
                context.errorMessage = ERROR_CURRENT_NODE_IS_NULL;
            }

            // Check Error Code
            //////////////////////////////////////

            if (context.errorCode > 0) {

                if (context.errorMessage == null || context.errorMessage.isEmpty()) {
                    context.errorMessage  = ERROR_FATAL;
                }

                error(context.errorMessage);                    
                                
                if (context.isOptimsticMode) {                    
                    // Inconsistent Data
                    warn(ERROR_RETURN_INCONSISTENT_DATA);
                    return root;
                }

                //delete root;
                //delete context;

                return null;
            }

            // Get token
            //////////////////////////////////////

            context.token = tokens[i]; 

            if (context.token == null) {
                printEventItem(context.verbose, "Start NULL");
                continue;
            }

            printEvent(context.verboseToken, MESSAGE_TOKEN, context.token);

            // Check NewLine
            //////////////////////////////////////

            boolean _isNewLine = context.checkNewLine(context.token);

            if (_isNewLine) {

                printEventItem(context.verbose, "Start [NL]");

                // In JSON segment
                if (isIgnoreIndent(context)) {
                    continue;
                }

                if (isStartValue(context)) {    

                    // Add NewLine to lines buffer
                    context.addLine("\n");

                } else if (isStartArrayMarker_SP(context)) {

                    // Restore prev event
                    context.currEvent = context.prevEvent;

                    // Start array marker '-[NL]'
                    onStartArrayMarker_NL(context);
                }

                // If NewLine already - skip
                if (context.isNewLine) {

                    context.resetBlankLineState();

                    // Skip next NewLine
                    continue;
                }

                context.isNewLine = _isNewLine;
                onNewLine(context);

                context.resetBlankLineState();

                // Next iteration
                continue;

            } else {
                // Reset NewLine flag
                context.isNewLine = _isNewLine;
            }

            // Check BlankLine / NewBlankLine
            //////////////////////////////////////

            int _spaceCount = context.getSpaceCount(context.token);
            boolean _isBlankLine = _spaceCount > 0;
            boolean _isNewBlankLine = false;

            if (_isBlankLine) {

                // In JSON segment
                if (isIgnoreIndent(context)) {
                    continue;
                }

                _isNewBlankLine = context.checkNextNewLine(tokens, tokenLen, i);

                if (_isNewBlankLine) {

                    if (isStartValue(context)) {    

                        // Add NewLine to lines buffer
                        context.addLine("\n");

                    } if (isStartArrayMarker_SP(context)) {

                        // Restore prev event
                        context.currEvent = context.prevEvent;

                        // Start array marker '-[NL]'
                        onStartArrayMarker_NL(context);

                    }

                    printEventItem(context.verbose, "Start [NBL]+" + _spaceCount);
                    context.spaceCount = 0;           // RESET: because BlankLine with '\n'

                    // If NewBlankLine alredy - skip
                    if (context.isNewBlankLine) {

                        context.isBlankLine = _isBlankLine;

                        // Next noken
                        i++;

                        // Skip next NewBlankLine
                        continue;

                    }

                } else {
                    printEventItem(context.verbose, "Start [BL]+" + _spaceCount);
                    context.spaceCount = _spaceCount; // SET: because it is BlankLine (many spaces)
                }

            }

            ////

            context.isBlankLine = _isBlankLine;
            context.isNewBlankLine = _isNewBlankLine;

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

            if (eq(context.token, "{")) {
                
                int currIndent = context.currNode.getIndent();

                onStartJsonObject(context);

                context.currNode.setIndent(currIndent);


            } else if (eq(context.token, "}")) {

                onEndJsonObject(context);

            } else if (eq(context.token, "[")) {

                onStartJsonArray(context);

            } else if (eq(context.token, "]")) {

                onEndJsonArray(context);
                
            } else if (eq(context.token, ",")) {

                onComma(context);

            //////////////////////////////////////
            
            // TODO
            // Check: ':,' ':}' ':]'

            //} else if (eq(context.token, ":")) {
            //    error("Char ':' is not correct");

            } else if (eq(context.token, ": ")) {

                onStartAttributeEq(context);

            } else if (eq(context.token, ":\r")
                || eq(context.token, ":\n")  
                || eq(context.token, ":\r\n")) {

                onStartAttributeEq(context);

            } else if (eq(context.token, "- ")) {

                onStartArrayMarker_SP(context);

            } else if (eq(context.token, "-\r")
                || eq(context.token, "-\n")  
                || eq(context.token, "-\r\n")) {

                onStartArrayMarker_NL(context);

            } else if (eq(context.token, "#")) {

                onStartComment(context);

            } else {

                // processing any text
                printEventItem(context.verboseText, MESSAGE_TEXT_TOKEN, context.token);

                // VALUE-EVENT - ': '
                if (context.currEvent == START_ATTRIBUTE_VALUE || context.currEvent == START_ARRAY_VALUE) {

                    onStartLineValue(context);

                // ATTRIBUTE-EVENT - ': '    
                } else if (context.currEvent == START_ATTRIBUTE_EQ) {

                    onStartAttributeValue(context);

                // OBJECT-EVENT
                } else if (context.currEvent == START_OBJECT || context.currEvent == START_JSON_OBJECT) {

                    onStartAttributeName(context);

                // ARRAY-EVENT
                } else if (context.currEvent == START_ARRAY || context.currEvent == START_JSON_ARRAY) {

                    onStartArrayValue(context);
    
                // COMMENT-EVENT
                } else if (context.currEvent == START_COMMENT) {

                    onEndComment(context);

                } else if (context.currEvent == START_ARRAY_MARKER_NL_FIRST || context.currEvent == START_ARRAY_MARKER_NL) {

                    context.currEvent = END_ARRAY;

                    onEndArray(context);

                    if (context.currNode == null) {
                        // ERROR
                        continue;
                    }

                    context.lastNode = context.currNode;

                    onStartAttributeName(context);

                } else if (context.currEvent == START_ARRAY_MARKER_SP_FIRST || context.currEvent == START_ARRAY_MARKER_SP) {
                     
                     int indent = getArrayIndent(context);

                     lookupNode(context, indent);

                     if (context.currNode == null) {
                         // ERROR
                         continue;
                     }

                     if (context.currEvent == START_ARRAY_MARKER_SP_FIRST) {

                         // If -[SP] is first - create new array 

                         // Create new array
                         onStartArray(context);

                     }

                     ////

                     onStartArrayValue(context);

                     //context.currNode.setIndent(context.spaceCount + 1); // IMPORTANT!

                } else if (context.currEvent == END_OBJECT 
                     || context.currEvent == END_JSON_OBJECT
                     || context.currEvent == END_ARRAY
                     || context.currEvent == END_JSON_ARRAY
                     ) {


                    if (isType(context.currNode, OBJECT_TYPE )) {

                        onStartAttributeName(context);

                    } else if (isType(context.currNode, ARRAY_TYPE )) {

                        onStartArrayValue(context);

                    } else {

                        error("Lost token: ", context.token, ",  Curr Event: " + context.currEvent);
                    }

                } else {

                    error("Lost token: ", context.token, ",  Curr Event: " + context.currEvent);
                }

            }

        }
        

        //delete context;

        return root;
    }
    
    //// PROCESSING ////

    protected void onStartObject(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_OBJECT);

        context.currEvent = START_OBJECT;
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        Node node = createNode(OBJECT_TYPE); // NEW-OBJECT
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onStartJsonObject(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_JSON_OBJECT);

        context.currEvent = START_JSON_OBJECT;
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        Node node = createNode(OBJECT_TYPE, JSON_OBJECT_SUBTYPE); // NEW-JSON-OBJECT
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onEndObject(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_OBJECT);

        // OBJECT-EVENT
        if (context.nodeEvent != START_NODE) {
            // if (context.currEvent != START_OBJECT) {

            // TODO: DISABLE: Use context.prevNodeEvent
            // error("Invalid YAML Structure: Object must be open before
            // close '}'"); cout << context.nodeEvent << endl;
        }

        int thisEvent = context.currEvent;
        context.currEvent = END_OBJECT;
        context.nodeEvent = END_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        tryEndNode(context, OBJECT_TYPE, END_OBJECT, thisEvent);

    }

    protected void onEndJsonObject(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_JSON_OBJECT);

        // OBJECT-EVENT
        if (context.nodeEvent != START_NODE) {
            // if (context.currEvent != START_OBJECT) {

            // TODO: DISABLE: Use context.prevNodeEvent
            // error("Invalid JSON Structure: Object must be open by '{' before
            // close '}'"); cout << context.nodeEvent << endl;
        }

        int thisEvent = context.currEvent;
        context.currEvent = END_JSON_OBJECT;
        context.nodeEvent = END_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        tryEndNode(context, OBJECT_TYPE, END_JSON_OBJECT, thisEvent);

        //////////////////////////////////////////////////////////////////////

        if (context.currNode == null) {
            // ERROR
            return;
        }

        context.lastNode = context.currNode;

        /////////////////////////////////////////////////////////////////////

    }

    protected void onStartArray(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_ARRAY);
        printEventItem(context.verboseDebug, "SPACE COUNT: " + context.spaceCount);

        context.currEvent = START_ARRAY;
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        Node node = createArrayNode(context); // NEW-ARRAY
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onStartJsonArray(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_START_JSON_ARRAY);

        context.currEvent = START_JSON_ARRAY;
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        Node node = createJsonArrayNode(context); // NEW-JSON-ARRAY
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onEndArray(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_ARRAY);

        // ARRAY-EVENT
        if (context.nodeEvent != START_NODE) {
            // if (context.currEvent != START_ARRAY) {

            // TODO: DISABLE: Use context.prevNodeEvent
            // error("Invalid YAML Structure: Array must be open before close ']'");
        }

        int thisEvent = context.currEvent;
        context.currEvent = END_ARRAY;
        context.nodeEvent = END_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        boolean changed = tryEndNode(context, ARRAY_TYPE, END_ARRAY, thisEvent);

        // TODO: IMPORTANT !!! Why?
        //if (changed) {
        //    context.lastNode = context.currNode;
        //}

    }

    protected void onEndJsonArray(YamlParserContext context) {

        printEvent(context.verbose, MESSAGE_END_JSON_ARRAY);

        // ARRAY-EVENT
        if (context.nodeEvent != START_NODE) {
            // if (context.currEvent != START_ARRAY) {

            // TODO: DISABLE: Use context.prevNodeEvent
            // error("Invalid JSON Structure: Array must be open by '[' before
            // close ']'");
        }

        int thisEvent = context.currEvent;
        context.currEvent = END_JSON_ARRAY;
        context.nodeEvent = END_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        tryEndNode(context, ARRAY_TYPE, END_JSON_ARRAY, thisEvent);

        //////////////////////////////////////////////////////////////////////

        if (context.currNode == null) {
            // ERROR
            return;
        }

        context.lastNode = context.currNode;

        /////////////////////////////////////////////////////////////////////

    }

    protected void onComma(YamlParserContext context) {

        // TODO: nodeEvent == START_OBJECT_NODE / START_ARRAY_NODE

        if (context.currEvent == START_ARRAY_VALUE) {
            printEventItem(context.verbose, MESSAGE_END_ARRAY_VALUE);
            context.currEvent = START_ARRAY;
        } else {
            printEventItem(context.verbose, MESSAGE_END_ATTRIBUTE);
            context.currEvent = START_OBJECT;
        }

        // if (context.objectEvent != START_OBJECT) {
        //    error("Invalid JSON Structure: Object must be open by '{' before
        //    close ", context.token);
        //}

        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        // Level UP
        context.levelUp();

        //////////////////////////////////////////////////////////////////////

        context.lastNode = context.currNode;

        /////////////////////////////////////////////////////////////////////

    }

    ////

    // '-[NL]': Array Marker with New Line
    // See onComma !!!
    protected void onStartArrayMarker_NL(YamlParserContext context) {

        if (context.currEvent == START_ATTRIBUTE_EQ) {

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_EQ);

            // Create new array
            onStartArray(context);            

            context.currEvent = START_ARRAY_MARKER_NL_FIRST;

        } else if (context.currEvent == START_ATTRIBUTE_VALUE) {
            
            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_ATTRIBUTE);

            // TODO: ERROR

        } else if (context.currEvent == START_ARRAY_MARKER_NL_FIRST) {

            // ARRAY LEVEL

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_ARRAY_MARKER_NL_FIRST);

            onStartArrayNullValue_2(context);

            context.currEvent = START_ARRAY_MARKER_NL;

        } else if (context.currEvent == START_ARRAY_MARKER_NL || context.currEvent == START_ARRAY_VALUE) {

            // ELEMENT LEVEL - GO UP

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_NL
            , context.currEvent == START_ARRAY_MARKER_NL ? MESSAGE_END_ARRAY_MARKER_NL : MESSAGE_END_ARRAY_VALUE);

            // Level UP
            context.levelUp();
            
            if (context.currNode == null) {
                // ERROR
                return;
            }

            ////

            onStartArrayNullValue(context);

            context.currEvent = START_ARRAY_MARKER_NL;
            
        } else if (context.currEvent == START_ARRAY) {

            // ARRAY LEVEL

            onStartArrayNullValue(context);

            context.currEvent = START_ARRAY_MARKER_NL;

        } else {

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_NL, MESSAGE_END_OTHER + context.currEvent);

            // TODO

        }

    }

    // '-[SP]': Array Marker with Space
    // See onComma !!!
    protected void onStartArrayMarker_SP(YamlParserContext context) {

        context.prevEvent = context.currEvent;

        if (context.currEvent == START_ATTRIBUTE_EQ) {

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_EQ);
            context.currEvent = START_ARRAY_MARKER_SP_FIRST;
            
        } else if (context.currEvent == START_ATTRIBUTE_VALUE) {
            
            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ATTRIBUTE);
            context.currEvent = START_ARRAY_MARKER_SP;

            // TODO: ERROR

        } else if (context.currEvent == START_ARRAY_MARKER_NL_FIRST) {

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ARRAY_MARKER_NL_FIRST);
            context.currEvent = START_ARRAY_MARKER_SP;

        } else if (context.currEvent == START_ARRAY_MARKER_NL) {

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ARRAY_MARKER_NL);
            context.currEvent = START_ARRAY_MARKER_SP;

        } else if (context.currEvent == START_ARRAY_VALUE) {

            // ELEMENT LEVEL - FLUSH TEXT

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_ARRAY_VALUE);
            printEventItem(context.verboseDebug, "SPACE COUNT: " + context.spaceCount);

            // CLOSE ARRAY VALUE

            flushLines(context, null); // flush without attribute (null)

            context.currEvent = START_ARRAY_MARKER_SP;

        } else {

            printEventItem(context.verbose, MESSAGE_START_ARRAY_MARKER_SP, MESSAGE_END_OTHER + context.currEvent);
            context.currEvent = START_ARRAY_MARKER_SP;

        }
        
    }

    // See onComma !!!
    protected void onNewLine(YamlParserContext context) {

        if (isStartValue(context)) {
            printEventItem(context.verboseDebug, "[NL] IN VALUE");
            context.multiValue = true; // start multi value
            return;
        }

        // JSON Object/Array only. 
        // TODO: What about isIndent?
        if (context.currEvent != END_JSON_OBJECT && context.currEvent != END_JSON_ARRAY) {
            return;
        }

        context.currEvent = START_OBJECT;        
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        // Level UP
        context.levelUp();

    }

    protected void onStartAttributeEq(YamlParserContext context) {

        printEventItem(context.verbose, "Start EQ [ ]");

        // Start Value only
        if (!isStartValue(context)) {
            context.currEvent = START_ATTRIBUTE_EQ;
            return;
        }

        int thisEvent = context.currEvent;

        if (thisEvent == START_ATTRIBUTE_VALUE) {
            printEventItem(context.verboseDebug, "CEQT_NODE: " + toNodeString(context.currNode));
        } else {
            printEventItem(context.verboseDebug, "CEQR_NODE: " + toNodeString(context.currNode));
        }

        ////////////////////////////////////////////////////////////////////////////////

        boolean flush = flushLines(context, context.currAttribute); // flush with attrbute

        if (flush) {

            // Close Value Node . Level UP

            context.token = context.currAttribute;
            closeValueNode(context);                

            if (context.currNode == null) {
                // ERROR
                return;
            }

            context.lastNode = context.currNode;

            if (thisEvent == START_ARRAY_VALUE) {

                // Level UP
                context.levelUp();

                if (context.currNode == null) {
                    // ERROR
                    return;
                }

                // Create CONTAINER/WRAPPER for attribute node (up)
                int currIndent = context.currNode.getIndent();
                onStartObject(context);

                // Transfer indent
                context.currNode.setIndent(currIndent);
                context.currNode.setSubType(WRAPPER_OBJECT_SUBTYPE);

                // Restore currEvent after onStartObject
                context.currEvent = START_ATTRIBUTE_VALUE;

            }

            // Create Attribute Name
            onStartAttributeName(context);
            context.currEvent = START_ATTRIBUTE_EQ;

            return;

        }

        ////////////////////////////////////////////////////////

        // ATTRIBUTE_VALUE . ATTRIBUTE_NAME: Convert prev attribute value to attribute name
        context.token = context.currAttribute;

        printEventItem(context.verbose, "VALUE . ATTR " + context.getLineSize() + " ", context.token); 

        int currIndent = context.currNode.getIndent();

        if (thisEvent == START_ATTRIBUTE_VALUE) {

            printEventItem(context.verboseDebug, "ATTR_VALUE . OBJ");

            // Reset Node Text
            context.currNode.setText("");

            // Create CONTAINER/WRAPPER for attribute node (up)
            onStartObject(context);

        } else {

            printEventItem(context.verboseDebug, "ARRAY_VALUE . OBJ");

            context.currEvent = START_OBJECT;
            context.nodeEvent = START_NODE;
            context.currAttribute = null;

            context.currNode.setType(OBJECT_TYPE); // SET-OBJECT: Convert Value to Object

        }
            
        // Transfer indent
        context.currNode.setIndent(currIndent);
        context.currNode.setSubType(WRAPPER_OBJECT_SUBTYPE);

        // Restore currEvent after onStartObject
        context.currEvent = START_ATTRIBUTE_VALUE;

        if (thisEvent == START_ATTRIBUTE_VALUE) {

            // Create Attribute Name
            onStartAttributeName(context);

        } else {

            // Create Attribute Name
            onStartAttributeName_2(context);

            // Transfer indent
            context.currNode.setIndent(currIndent); // IMPORTANT!

        }

        context.currEvent = START_ATTRIBUTE_EQ;

        // ATTRIBUTE-EVENT
        //if (context.currEvent != START_ATTRIBUTE_NAME) {
        //    error(ERROR_INVALID_YAML_ATTRIBUTE_NAME);
        //}

    }

    protected void onStartAttributeValue(YamlParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE_VALUE, toSafeString(context.currAttribute), "=", context.token);

        // Clear lines buffer
        context.clearLines();

        context.currEvent = START_ATTRIBUTE_VALUE;

        /////////////////////////////////////////////////////////

        String text = context.token;

        context.addLine(text); // NO TRIM !!! Need spaces !!!

        // Normalize attribute value
        text = StrLib.trim(text);
        
        /////////////////////////////////////////////////////////        

        context.currNode.setText(text);        // Add first line only. Other lines in future
        context.currAttribute = context.token; // IMPORTANT! Attrbute candidate // null;

        printEventItem(context.verboseDebug, "CVAL_NODE: " + toNodeString(context.currNode));
    }

    protected void onStartAttributeName(YamlParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE, context.token);

        if (context.currEvent == START_ATTRIBUTE_VALUE) {

            // ATTRIBUTE_VALUE . ATTRIBUTE_NAME
            printEventItem(context.verboseDebug, "VALUE . ATTR [+]");

        }

        context.currEvent = START_ATTRIBUTE_NAME;
        context.currAttribute = context.token;
        
        int indent = context.getIndent(context.currAttribute);

        /////////////////////////////////////////////////////////

        tryLookupNode(context, indent);

        if (context.errorCode > 0) {
            return;
        }

        /////////////////////////////////////////////////////////

        String text = context.currAttribute;

        // Normalize attribute name
        text = StrLib.trim(text);
        
        /////////////////////////////////////////////////////////

        Node node = createNode(ATTRIBUTE_TYPE); // NEW-ATTRIBUTE
        node.setIndent(indent); // IMPORTANT!
        node.setName(text);
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);

    }

    protected void onStartAttributeName_2(YamlParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE, context.token);

        context.currEvent = START_ATTRIBUTE_NAME;
        context.currAttribute = context.token;
        
        /////////////////////////////////////////////////////////

        String text = context.currAttribute;

        // Normalize attribute name
        text = StrLib.trim(text);
        
        /////////////////////////////////////////////////////////

        Node node = createNode(ATTRIBUTE_TYPE); // NEW-ATTRIBUTE
        node.setName(text);
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);

    }

    protected void onStartArrayValue(YamlParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ARRAY_VALUE, context.token);
        printEventItem(context.verboseDebug, "SPACE COUNT: " + context.spaceCount);

        // Clear lines buffer
        context.clearLines();

        context.currEvent = START_ARRAY_VALUE;
        context.currAttribute = null;        

        /////////////////////////////////////////////////////////

        String text = context.token;

        context.addLine(text); // NO TRIM !!! Need spaces !!!

        // Normalize attribute value
        text = StrLib.trim(text);
        
        /////////////////////////////////////////////////////////

        Node node = createArrayValueNode(context, text); // NEW-ARRAY-VALUE
        context.currNode.addChild(node);

        context.currAttribute = context.token; // IMPORTANT! Attrbute candidate // null;

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onStartArrayNullValue(YamlParserContext context) {

        printEventItem(context.verboseDebug, "SPACE COUNT: " + context.spaceCount);

        context.currEvent = START_ARRAY_VALUE;
        context.currAttribute = null;

        Node node = createArrayNullNode(context); // NEW-ARRAY-VALUE
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onStartArrayNullValue_2(YamlParserContext context) {

        printEventItem(context.verboseDebug, "SPACE COUNT: " + context.spaceCount);

        context.currEvent = START_ARRAY_VALUE;
        context.currAttribute = null;

        // 1
        Node node = createArrayNullNode(context); // NEW-ARRAY-VALUE-1
        context.currNode.addChild(node);

        // 2
        node = createArrayNullNode(context);             // NEW-ARRAY-VALUE-2
        context.currNode.addChild(node);

        // Level DOWN
        context.levelDown(node, true);
    }

    protected void onStartLineValue(YamlParserContext context) {

        printEventItem(context.verboseText, MESSAGE_ADD_VALUE_LINE, context.token);

        //////////////////////////////////////////////////////

        String text = context.token;

        // TODO: Check indent. If indent s not correct set error and return !!!

        context.addLine(text); // NO TRIM !!! Need spaces !!!

        context.currAttribute = context.token; // Attrbute candidate

        /////////////////////////////////////////////////////                    
        
    }

    protected void onStartComment(YamlParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_COMMENT, context.token);

        // Save currEvent
        if (context.currEvent != START_COMMENT) {
            // IMPORTANT! For non comment event only. 
            // Because we can replace orignal event
            context.prevEvent = context.currEvent;
            context.currEvent = START_COMMENT;
        }
    }

    protected void onEndComment(YamlParserContext context) {

        //context.isRestored = true;

        printEventItem(context.verbose, MESSAGE_END_COMMENT, context.token);

        // Restore currEvent
        context.currEvent = context.prevEvent;
        //context.currEvent = END_COMMENT;

        // TODO: Skip comment?

        // TODO: Start Cmment Node.
        // Add it to Current Node 
        // or set Inline Comment of Current Node (if it without New Line)
        // Don't change CurrentNode!
        // Don't go down!
    }
    
    //// HELPER ////
        
    protected boolean isIgnoreIndent(int subType) {
        return subType == JSON_OBJECT_SUBTYPE
        || subType == JSON_ARRAY_SUBTYPE;
    }

    protected boolean isIgnoreIndent(Node node) {
        if (node == null) {
            return false;
        }

        // Current Node
        if (isIgnoreIndent(node.getSubType())) {
            return true;
        }

        // Parent Node
        Node parent = node.getParent();
        if (parent == null) {
            return false;
        }

        return isIgnoreIndent(parent.getSubType());
    }

    protected boolean isIgnoreIndent(YamlParserContext context) {
        return isIgnoreIndent(context.currNode);
    }

    ////

    protected boolean isType(Node node, int type, int subType) {
        return node == null ? false : node.isType(type, subType);
    }

    protected boolean isType(Node node, int type) {
        return node == null ? false : node.isType(type);
    }

    ////

    protected boolean isObjectType(Node node) {
        return isType(node, OBJECT_TYPE);
    }

    protected boolean isJsonObjectType(Node node) {
        return isType(node, OBJECT_TYPE, JSON_OBJECT_SUBTYPE);
    }

    // TODO: Why need Wrapper Object ? Use Object only

    protected boolean isWrapperObjectType(Node node) {
        return isType(node, OBJECT_TYPE, WRAPPER_OBJECT_SUBTYPE);
    }

    protected boolean isArrayType(Node node) {
        return isType(node, ARRAY_TYPE);
    }

    protected boolean isJsonArrayType(Node node) {
        return isType(node, ARRAY_TYPE, JSON_ARRAY_SUBTYPE);
    }

    protected boolean isAttributeType(Node node) {
        return isType(node, ATTRIBUTE_TYPE);
    }

    protected boolean isContainerType(Node node) {
        return isObjectType(node) || isArrayType(node);
    }

    ////

    protected boolean isEmptyType(Node node, int type) {
        return isType(node, type) && !node.hasChildren();
    }

    protected boolean isParentAttributeType(Node node) {
        if (node == null) {
            return false;
        }

        // Current Node is not AttributeType
        if (isType(node, ATTRIBUTE_TYPE)) {
            return false;
        }

        // Parent Node is AttributeType
        return isType(node.getParent(), ATTRIBUTE_TYPE);
    }

    ////

    protected Node createNode() {
        Node node = new Node();
        return node;
    }

    protected Node createNode(int type) {
        Node node = createNode();
        node.setType(type);
        return node;
    }

    protected Node createNode(int type, int subType) {
        Node node = createNode();
        node.setType(type);
        node.setSubType(subType);
        return node;
    }

    protected Node createNullNode() {
        Node node = createNode();
        node.setText("empty"); // TODO: STUB
        return node;
    }

    protected Node createValueNode(String text) {
        Node node = createNode();
        node.setText(text);
        return node;
    }

    ////

    protected int getArrayIndent(YamlParserContext context) {
        return context.spaceCount + 1; // Array Marker: '- '
    }

    protected Node createArrayNode(YamlParserContext context) {
        Node node = createNode(ARRAY_TYPE);
        node.setIndent(getArrayIndent(context));
        return node;
    }

    protected Node createArrayValueNode(YamlParserContext context, String text) {
        Node node = createValueNode(text);
        node.setIndent(getArrayIndent(context));
        return node;
    }

    protected Node createArrayNullNode(YamlParserContext context) {
        Node node = createNullNode();
        node.setIndent(getArrayIndent(context));
        return node;
    }

    protected Node createJsonArrayNode(YamlParserContext context) {
        Node node = createNode(ARRAY_TYPE, JSON_ARRAY_SUBTYPE);
        return node;
    }

    ////

    protected boolean isStartValue(YamlParserContext context) {
        return context.currEvent == START_ATTRIBUTE_VALUE || context.currEvent == START_ARRAY_VALUE;
    }

    protected boolean isStartArrayMarker_SP(YamlParserContext context) {
        return context.currEvent == START_ARRAY_MARKER_SP_FIRST || context.currEvent == START_ARRAY_MARKER_SP;
    }

    protected boolean isStartArrayMarker_NL(YamlParserContext context) {
        return context.currEvent == START_ARRAY_MARKER_NL_FIRST || context.currEvent == START_ARRAY_MARKER_NL;
    }

    ////

    protected void closeValueNode(YamlParserContext context) {

        //printEventItem(context.verbose, "Start CloseValueNode");

        // Start Value only
        if (!isStartValue(context)) {
            return;
        }

        if (context.currEvent == START_ATTRIBUTE_VALUE) {
            context.currEvent = START_OBJECT;
        } else if (context.currEvent == START_ARRAY_VALUE) {
            context.currEvent = START_ARRAY;
        }

        context.nodeEvent = START_NODE;
        //context.currAttribute = null;      // Why? Don't touch attributes
        context.map.put(context.level, null);

        // Level UP
        context.levelUp();

        //printEventItem(context.verboseDebug, "End CloseValueNode");
        // TODO: Maybe Sync Node?
    }

    protected boolean flushLines(YamlParserContext context, String attribute) {

        //printEventItem(context.verboseDebug, "Start FlushLines");

        if (!context.multiValue) {
            return false;
        }

        context.multiValue = false; // reset
        int realSize = context.getRealSize();

        // Multi Line start from 2 lines
        if (realSize < 2) {
            return false;
        }

        String text = context.getFirstLines(attribute);        
        text = StrLib.trim(text); // IMPORTANT! Because we have 'value ' (with last SP)

        context.currNode.setText(text);
        context.clearLines();

        //printEventItem(context.verboseDebug, "End FlushLines");

        return true;
    }


    // DISABLED
    protected void checkValueNode(YamlParserContext context, boolean isNewLineComment) {

        printEventItem(context.verboseDebug, "Start CheckValueNode, isNewLineComment=" + isNewLineComment);

        // Check by MultiValue flag
        if (!context.multiValue) {
            return;
        }

        // Check by Event
        if (!isStartValue(context)) {
            return;
        }

        boolean flush = flushLines(context, null);

        //if (flush) {
            //closeValueNode(context);
        //}
        
    }

    ////

    protected String toNodeString(Node node) {
        if (node == null) {
            return "[NUL]";
        }
        return "Node[name=" + node.getName()
        + ", type=" + node.getType()
        + ", subType=" + node.getSubType()
        + ", indent=" + node.getIndent()
        + ", text=" + node.getText() + "]";
    }
    
    //// HELPER ////

    protected void lookupNode(YamlParserContext context, int indent) {

        if (context == null) {
            // ERROR
            return;
        }

        Node currNode = context.currNode;
        Node lastNode = context.lastNode;

        if (lastNode == null) {
            // DEBUG
            printEventItem(context.verboseDebug, "INDENT . STAY THIS [LAST is NULL]"); // ERROR
            return;
        }

        int currIndent = currNode.getIndent();
        int lastIndent = lastNode.getIndent();        

        if (currNode == lastNode) {

            // DEBUG
            printEventItem(context.verboseDebug, "CURR == LAST");

        } else {

            // DEBUG
            printEventItem(context.verboseDebug, "CURR <> LAST");

        }

        // DEBUG
        printEventItem(context.verboseDebug, "CURR_INDENT: " + currIndent);
        printEventItem(context.verboseDebug, "LAST_INDENT: " + lastIndent);
        printEventItem(context.verboseDebug, "THIS_INDENT: " + indent);


        if (isIgnoreIndent(context.currNode)) {

            // DEBUG
            printEventItem(context.verboseDebug, "INDENT . STAY THIS [JSON]");
            return;
        }

        ////

        Node parent = currNode.getParent();
                
        if (indent == currIndent) {
            
            // GO UP (1): PARENT

            if (parent == null) {
                
                // DEBUG
                printEventItem(context.verboseDebug, "INDENT . STAY THIS [ROOT]");
                return;
            }

            int count = 1;

            if (isWrapperObjectType(currNode) && isAttributeType(parent)) {
                parent = parent.getParent();
                count++;
            } else if (isWrapperObjectType(parent) && isAttributeType(parent.getParent())) {

                // TODO: WARNING !!!
                //parent = parent.getParent().getParent();
                //count++;
            }


            //if (isWrapperObjectType(currNode) && isAttributeType(parent)) {
            //    parent = parent.getParent();
            //    count++;
            //}
            
            //if (isWrapperObjectType(parent) && isAttributeType(parent.getParent())) {
            //    parent = parent.getParent().getParent();
            //    count++;
            //}

            // DEBUG
            if (count > 1) {
                printEventItem(context.verboseDebug, "INDENT . GO PARENT (" + count + ")");
            } else {
                printEventItem(context.verboseDebug, "INDENT . GO PARENT");
            }

            // Level UP (N)
            context.levelUp(parent, count); // No Sync - Later            

            return;
        }

        ////
        
        if (indent > currIndent) {

            // DEBUG
            printEventItem(context.verboseDebug, "INDENT . STAY THIS");
            return;

        }

        // DEBUG
        printEventItem(context.verboseDebug, "INDENT . GO PARENT [-]");

        // Level UP
        context.levelUp(parent); // No Sync - Later

        lookupNode(context, indent);

    }

    protected void tryLookupNode(YamlParserContext context, int indent) {

        //printEventItem(context.verboseDebug, "CURR_NODE: " + toNodeString(context.currNode));
        //printEventItem(context.verboseDebug, "LAST_NODE: " + toNodeString(context.lastNode));

        if (context.lastNode != null && !isIgnoreIndent(context.currNode)) {
            int lastIndent = context.lastNode.getIndent();
            if (indent > lastIndent && !isContainerType(context.lastNode)) {
                String name = context.currAttribute;
                context.errorCode = 1;
                context.errorMessage = "YAML Parser: Cannot add attribute '" + name + "'. Last Node is not container: type=" + context.lastNode.getType();
                return;
            }
        }

        lookupNode(context, indent);

        if (context.currNode == null) {
            String name = context.currAttribute;
            context.errorCode = 1;            
            context.errorMessage = "YAML Parser: Cannot add attribute '" + name + "'. Found Node is NULL";
            return;
        }

        //printEventItem(context.verboseDebug, "REAL_NODE: " + toNodeString(context.currNode));

        if (!isContainerType(context.currNode)) {
            String name = context.currAttribute;
            context.errorCode = 1;
            context.errorMessage = "YAML Parser: Cannot add attribute '" + name + "'. Found Node is not container: type=" + context.currNode.getType();
            return;
        }

    }

    protected boolean tryEndNode(YamlParserContext context, int type, int event, int thisEvent) {
        // printEventItem(context.verboseDebug, "Curr: " + currNode.toString());

        boolean isEmptyContainer = isEmptyType(context.currNode, type);
        boolean changed = false;

        // CONTAINER-LEVEL
        if (!isEmptyContainer || (isEmptyContainer && thisEvent == event)) {

            // Level UP
            context.levelUp();

            if (context.currNode == null) {
                // ERROR
                return false;
            }
            changed = true;
            // printEventItem(context.verboseDebug, "Prev: " + currNode.toString());
        }

        // ATTRIBUTE LEVEL
        if (isParentAttributeType(context.currNode)) {

            // Level UP
            context.levelUp();

            if (context.currNode == null) {
                // ERROR
                return false;
            }
            changed = true;
            // printEventItem(context.verboseDebug, "Prev: " + currNode.toString());
        }

        return changed;
    }

    //// UTILS ////

    protected boolean eq(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;            
        }
        return str1.equals(str2);        
    }

}
