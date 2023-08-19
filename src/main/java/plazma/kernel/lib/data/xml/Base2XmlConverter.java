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

import plazma.kernel.lib.data.node.BaseNodeConverter;
import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.str.StrLib;

public abstract class Base2XmlConverter extends BaseNodeConverter {

    public Node convertDocumentNode(Node original) {
        // TODO
        return convertNode(original);
    }

    public Node convertNode(Node original) {

        boolean unwrapArray = true;
        boolean unwrapObject = true;

        ///////////////////////////////////////////////////////////

        if (original == null) {
            return null;
        }

        int nodeType = original.getType();
        //if (isSkipXmlNode(nodeType)) {
        //    return null;
        //}

        Node node = null;

        ///////////////////////////////////////////////////////////

        boolean hasAttributes = original.hasAttributes();
        boolean hasChildren = original.hasChildren();
        //bool singleNode = !hasAttributes && !hasChildren;

        Node originalChild = null;
        Node child = null;

        String name = null;
        String text = null;

        node = new Node();

        if (isAttributeType(nodeType)) {

            name = original.getName();

            name = StrLib.unquote(name);

            node.setName(name);

            originalChild = hasChildren ? original.getChild(0) : null;

            // Simple attribute value: 10, 3.14, 'Hello'
            if (originalChild == null) {
                text = original.getText();
                node.setText(text);
                return node;
            }

            ////

            if (unwrapArray && isArrayType(originalChild.getType()) && originalChild.hasChildren()) {

                // unwrap #array
                
                if (isSimpleChildren(originalChild)) {
                    text = toChildrenText(originalChild);
                    node.setText(text);
                    return node;
                }

            }

            if (unwrapObject && isObjectType(originalChild.getType()) /*&& originalChild.getChildCount() == 1*/) {

                // unwrap #object
                
                convertChildren(originalChild, node);
                return node;                                
            }

            ////
            
            child = convertNode(originalChild);

            if (child != null) {
                node.addChild(child);
            }

        } else if (isObjectType(nodeType)) {

            /*
            if (node.getParent() == null && original.getChildCount() == 1) {

                node.setName("#object-2");

                original = original.getChild(0);

                child = convertBaseNode(original);

                if (child != null) {
                    node.addChild(child);
                }

                return node;

                
                //int t = original.getChild(0).getType();
                //if (isAttributeType(t)) {
                //    original = original.getChild(0);
                //    delete node;
                //    node = convertBaseNode(node);
                //    return node;
                //}
                
            }
            */

            node.setName("#object");

            if (hasChildren) {
                convertChildren(original, node);
            }            

        } else if (isArrayType(nodeType)) {

            node.setName("#array");

            if (hasChildren) {
                convertChildren(original, node);
            }

        } else {

            if (hasChildren) {
                node.setName("#container");
                convertChildren(original, node);

            } else {
                node.setName("#text");
                text = original.getText();
                node.setText(text);
            }

        }

        return node;

        ///////////////////////////////////////////////////////////

    }

    public boolean isSimpleChildren(Node node) {
        if (node == null) {
            return true;
        }

        int count = node.getChildCount();
        Node child = null;

        for (int i = 0; i < count; i++) {
            child = node.getChild(i);
            if (child.hasChildren()) {
                return false;
            }
        }
        return true;
    }

    public String toChildrenText(Node node) {
        if (node == null) {
            return null;
        }
        StringBuilder text = new StringBuilder();
        int count = node.getChildCount();
        Node child = null;

        for (int i = 0; i < count; i++) {
            child = node.getChild(i);
            if (i > 0) {
                text.append(", ");
            }
            text.append(child.getText());
        }

        return text.toString();
    }

    public String toString() {
        return "Base2XmlConverter";
    }

}
