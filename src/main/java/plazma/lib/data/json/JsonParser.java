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

package plazma.lib.data.json;

import plazma.lib.data.node.Node;
import static plazma.lib.text.PrinterHelper.*;

import java.util.HashMap;

public class JsonParser {
    
    // Event types

    public static final int NONE = 0;

    // Object
    
    public static final int START_OBJECT = 1;

    public static final int END_OBJECT = 2;

    // Array

    public static final int START_ARRAY = 3;

    public static final int END_ARRAY = 4;

    // Attribute

    public static final int START_ATTRIBUTE_NAME = 5;

    public static final int START_ATTRIBUTE_EQ = 6;

    public static final int START_ATTRIBUTE_VALUE = 7;

    public static final int END_ATTRIBUTE = 8;

    // Value

    public static final int START_ARRAY_VALUE = 9;

    public static final int END_ARRAY_VALUE = 10;

    // Node

    public static final int START_NODE = 1001;

    public static final int END_NODE = 1002;
    

    // Node types

    public static final int OBJECT_TYPE = 1;

    public static final int ARRAY_TYPE = 2;

    public static final int ATTRIBUTE_TYPE = 3;

    
    public static final String MESSAGE_TOKEN                            = "TOKEN                 : ";
    public static final String MESSAGE_TEXT_TOKEN                       = "Text token            : ";

    public static final String MESSAGE_START_OBJECT                     = "Start Object";
    public static final String MESSAGE_START_ARRAY                      = "Start Array";
    public static final String MESSAGE_START_ARRAY_VALUE                = "Start Array Value     : ";
    public static final String MESSAGE_START_ATTRIBUTE                  = "Start Attribute       : ";
    public static final String MESSAGE_START_ATTRIBUTE_VALUE            = "Start Attribute Value : ";

    public static final String MESSAGE_END_OBJECT                       = "End Object";    
    public static final String MESSAGE_END_ARRAY                        = "End Array";
    public static final String MESSAGE_END_ARRAY_VALUE                  = "End Array Value";
    public static final String MESSAGE_END_ATTRIBUTE                    = "End Attribute";

    ////

    public static final String ERROR_INVALID_JSON_ATTRIBUTE_NAME        = "Invalid JSON Structure: Attribute name nust be setting before ':'";
    public static final String ERROR_CURRENT_NODE_IS_NULL               = "JSON Parser: Current Node is NULL";
    public static final String ERROR_RETURN_INCONSISTENT_DATA           = "JSON Parser: Return inconsistent data";


    public Node parseJsonFromTokens(String[] tokens) {
        Node node = parseJsonFromTokens(null, tokens);
        return node;
    }

    public Node parseJsonFromTokens(JsonReaderConfig config, String[] tokens) {

        Node root = new Node();
        root.setType(OBJECT_TYPE);

        if (tokens == null || tokens.length == 0) {
            return root;
        }

        JsonParserContext context = new JsonParserContext();

        context.prevEvent = NONE;
        context.currEvent = NONE;
        context.nodeEvent = NONE;
        context.currAttribute = null;

        context.level = 0;
        context.map = new HashMap<>();
        context.currNode = root;

        if (config != null) {
            context.verbose = config.verbose;
            context.verboseToken = config.verboseToken;
            context.verboseText = config.verboseText;
        }

        context.isEmptyContainer = false;
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

            if (context.token.equals("{")) {

                onStartObject(context);

            } else if (context.token.equals("}")) {

                onEndObject(context);

            } else if (context.token.equals("[")) {

                onStartArray(context);

            } else if (context.token.equals("]")) {

                onEndArray(context);
                
            } else if (context.token.equals(",")) {

                onComma(context);

            } else if (context.token.equals(":")) {

                onStartAttributeEq(context);

            } else {

                // processing any text
                printEventItem(context.verboseText, MESSAGE_TEXT_TOKEN, context.token);

                // ATTRIBUTE-EVENT
                if (context.currEvent == START_ATTRIBUTE_EQ) {

                    onStartAttributeValue(context);

                // OBJECT-EVENT
                } else if (context.currEvent == START_OBJECT) {

                    onStartAttributeName(context);

                // ARRAY-EVENT
                } else if (context.currEvent == START_ARRAY) {

                    onStartArrayValue(context);
    
                } else {
                    error("Lost token: ", context.token);
                }

            }
        }

        return root;
    }
    
    //// PROCESING ////

    protected void onStartObject(JsonParserContext context) {

        printEvent(context.verbose, MESSAGE_START_OBJECT);
        // printEventItem(context.verbose, currNode.getType());

        context.currEvent = START_OBJECT;
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        Node node = new Node(); // NEW-OBJECT
        node.setType(OBJECT_TYPE);

        context.currNode.addChild(node);
        context.currNode = node;

        context.level++;
        // map[context.level] = context.currTag;
    }

    protected void onEndObject(JsonParserContext context) {

        printEvent(context.verbose, MESSAGE_END_OBJECT);

        // OBJECT-EVENT
        if (context.nodeEvent != START_NODE) {
            // if (context.currEvent != START_OBJECT) {

            // TODO: DISABLE: Use context.prevNodeEvent
            // error("Invalid JSON Structure: Object must be open by '{' before
            // close '}'"); cout << context.nodeEvent << endl;
        }

        context.prevEvent = context.currEvent;
        context.currEvent = END_OBJECT;
        context.nodeEvent = END_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        // printEventItem(context.verbose, "Curr: " + currNode.toString());

        context.isEmptyContainer =
            context.currNode.getType() == OBJECT_TYPE &&
            !context.currNode.hasChildren();

        // CONTAINER-LEVEL
        if (!context.isEmptyContainer ||
            (context.isEmptyContainer && context.prevEvent == END_OBJECT)) {

            // Level up
            context.level--;
            context.currNode = context.currNode.getParent();
            // printEventItem(context.verbose, "Prev: " +
            // currNode.toString());
        }

        // ATTRIBUTE LEVEL
        if (context.currNode.getType() != ATTRIBUTE_TYPE &&
            context.currNode.getParentType() == ATTRIBUTE_TYPE) {

            // Level up
            context.level--;
            context.currNode = context.currNode.getParent();
            // printEventItem(context.verbose, "Prev: " +
            // currNode.toString());
        }

    }

    protected void onStartArray(JsonParserContext context) {

        printEvent(context.verbose, MESSAGE_START_ARRAY);

        context.currEvent = START_ARRAY;
        context.nodeEvent = START_NODE;
        context.currAttribute = null;

        Node node = new Node(); // NEW-ARRAY
        node.setType(ARRAY_TYPE);

        context.currNode.addChild(node);
        context.currNode = node;

        context.level++;
        // map[context.level] = context.currTag;
    }

    protected void onEndArray(JsonParserContext context) {

        printEvent(context.verbose, MESSAGE_END_ARRAY);

        // ARRAY-EVENT
        if (context.nodeEvent != START_NODE) {
            // if (context.currEvent != START_ARRAY) {

            // TODO: DISABLE: Use context.prevNodeEvent
            // error("Invalid JSON Structure: Array must be open by '[' before
            // close ']'");
        }

        context.prevEvent = context.currEvent;
        context.currEvent = END_ARRAY;
        context.nodeEvent = END_NODE;
        context.currAttribute = null;

        context.map.put(context.level, null);

        // printEventItem(context.verbose, "Curr: " + currNode.toString());

        context.isEmptyContainer =
            context.currNode.getType() == ARRAY_TYPE &&
            !context.currNode.hasChildren();

        // CONTAINER-LEVEL
        if (!context.isEmptyContainer ||
            (context.isEmptyContainer && context.prevEvent == END_ARRAY)) {

            // Level up
            context.level--;
            context.currNode = context.currNode.getParent();
            // printEventItem(context.verbose, "Prev: " +
            // currNode.toString());
        }

        // ATTRIBUTE LEVEL
        if (context.currNode.getType() != ATTRIBUTE_TYPE &&
            context.currNode.getParentType() == ATTRIBUTE_TYPE) {

            // Level up
            context.level--;
            context.currNode = context.currNode.getParent();
            // printEventItem(context.verbose, "Prev: " +
            // currNode.toString());
        }

    }

    protected void onComma(JsonParserContext context) {

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

        // Level up
        context.level--;
        context.currNode = context.currNode.getParent();
    }

    protected void onStartAttributeEq(JsonParserContext context) {

        // ATTRIBUTE-EVENT
        if (context.currEvent != START_ATTRIBUTE_NAME) {
            error(ERROR_INVALID_JSON_ATTRIBUTE_NAME);
        }

        context.currEvent = START_ATTRIBUTE_EQ;
    }

    protected void onStartAttributeValue(JsonParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE_VALUE, toSafeString(context.currAttribute), "=", context.token);

        context.currEvent = START_ATTRIBUTE_VALUE;

        // OLD !!!
        // Node node = new Node(); // NEW-ATTRIBUTE
        // node.setName(context.currAttribute);
        // node.setText(context.token);
        // currNode.addChild(node);
        // currNode = node;
        //
        // context.level++;

        // NEW !!!
        context.currNode.setText(context.token);

        context.currAttribute = null;
    }

    protected void onStartAttributeName(JsonParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ATTRIBUTE, context.token);
        // printEventItem(context.verbose, currNode.getType());
        // printEventItem(context.verbose, currNode.getName());

        context.currEvent = START_ATTRIBUTE_NAME;
        context.currAttribute = context.token;

        // NEW !!!
        Node node = new Node(); // NEW-ATTRIBUTE
        node.setName(context.currAttribute);
        node.setType(ATTRIBUTE_TYPE);

        context.currNode.addChild(node);
        context.currNode = node;

        context.level++;
    }

    protected void onStartArrayValue(JsonParserContext context) {

        printEventItem(context.verbose, MESSAGE_START_ARRAY_VALUE, context.token);
        // printEventItem(context.verbose, currNode.getType());

        context.currEvent = START_ARRAY_VALUE;
        context.currAttribute = null;

        Node node = new Node(); // NEW-ARRAY-VALUE
        node.setText(context.token);
        context.currNode.addChild(node);
        context.currNode = node;

        context.level++;
    }

}
