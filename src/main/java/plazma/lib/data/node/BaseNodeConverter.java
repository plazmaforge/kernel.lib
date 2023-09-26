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

package plazma.lib.data.node;

import java.util.Map;

import plazma.lib.str.StrLib;

public abstract class BaseNodeConverter implements NodeConverter {
    
    protected boolean useAttributesAsChildren;
    
    protected abstract Node convertDocumentNode(Node node);
    
    protected abstract Node convertNode(Node node);

    public Node convert(Node node) {
        if (node == null) {
            return null;
        }
        Node result = convertDocumentNode(node);
        return result;
    }

    public void convertChildren(Node original, Node node) {
        if (original == null) {
            return;
        }

        if (!original.hasChildren()) {
            return;
        }

        int count = original.getChildCount();
        Node  originalChild = null;
        Node  child = null;

        for (int i = 0; i < count; i++) {
            originalChild = original.getChild(i);
            child = convertNode(originalChild);
            if (child == null) {
                continue;
            }
            node.addChild(child);
        }
    }

    public void convertAttrbutes(Node original, Node node) {
        if (useAttributesAsChildren) {
            convertAttrbutesAsChildren(original, node);
        } else {
            convertAttrbutesAsIs(original, node);
        }        
    }

    public void convertAttrbutesAsIs(Node original, Node node) {
        if (original == null) {
            return;
        }

        if (!original.hasAttributes()) {
            return;
        }

        //int count = original.getAttributeCount();
        //for (int i = 0; i < count; i++) {
            
        Map<String, String> attributes = original.getAttributes();

        for (String name : attributes.keySet()) {
            String value = attributes.get(name);
            
            //String name = original.getAttributeName(i);
            //String value = original.getAttributeValue(i);
            node.addAttribute(name, value);
        }

    }

    public void convertAttrbutesAsChildren(Node original, Node node) {
        if (original == null) {
            return;
        }

        if (!original.hasAttributes()) {
            return;
        }

        //int count = original.getAttributeCount();
        //for (int i = 0; i < count; i++) {
        
        Map<String, String> attributes = original.getAttributes();

        for (String name : attributes.keySet()) {
            String value = attributes.get(name);
            
            //String name = original.getAttributeName(i);
            //String value = original.getAttributeValue(i);

            Node attrNode = createAttributeNode();

            attrNode.setName(name);

            // TODO: Fix empty value or skip it
            if (StrLib.isEmpty(value)) {
                value = emptyText();
            }

            attrNode.setText(value);

            node.addChild(attrNode);

        }

    }


    ////

    protected String emptyText() {
        return "\"\"";
    }

    protected boolean isSkipNode(int nodeType) {
        return false; // by default
    }

    protected String getTextType(int nodeType) {
        return ""; // by default
    }

    ////

    protected boolean isDocumentType(int nodeType) {
        return false; // by default
    }

    protected boolean isObjectType(int nodeType) {
        return false; // by default
    }

    protected boolean isArrayType(int nodeType) {
        return false; // by default
    }

    protected boolean isAttributeType(int nodeType) {
        return false; // by default
    }

    protected boolean isTextType(int nodeType) {
        return false; // by default
    }

    protected boolean isCommentType(int nodeType) {
        return false; // by default
    }
    
    protected int convertNodeType(int nodeType) {
        return 0; // by default
    }

    protected int convertNodeSubType(int nodeSubType) {
        return 0; // by default
    }

    ////

    public Node createDocumentNode() {
        return createNode(); // by default
    }

    public Node createObjectNode() {
        return createNode(); // by default
    }

    public Node createArrayNode() {
        return createNode(); // by default
    }

    public Node createAttributeNode() {
        return createNode(); // by default
    }

    public Node createNode() {
        return new Node();
    }

    ////

    // Clone implementation
    public Node convertCloneNode(Node original) {

        if (original == null) {
            return null;
        }

        int nodeType = original.getType();
        if (isSkipNode(nodeType)) {
            return null;
        }

        return original.clone();
    }

    // Default implementation
    public Node convertDefaultNode(Node original) {

        if (original == null) {
            return null;
        }

        int nodeType = original.getType();
        if (isSkipNode(nodeType)) {
            return null;
        }

        Node node = new Node();

        // Default implementation
        node.setType(convertNodeType(original.getType()));
        node.setSubType(convertNodeSubType(original.getSubType()));

        node.setName(original.getName());
        node.setText(original.getText());
        node.setIndent(original.getIndent());

        ///////////////////////////////////////////////////////////

        convertAttrbutes(original, node);

        convertChildren(original, node);

        ///////////////////////////////////////////////////////////

        return node;
    }

    ////

    public String toString() {
        return "BaseNodeConverter";
    }
    

}
