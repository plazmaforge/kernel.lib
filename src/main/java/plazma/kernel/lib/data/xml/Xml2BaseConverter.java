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

public abstract class Xml2BaseConverter extends BaseNodeConverter {

    public Xml2BaseConverter() {
        useAttributesAsChildren = true;
    }

    public Node convertDocumentNode(Node original) {

        useAttributesAsChildren = true;

        /*
        Node document = createDocumentNode();
        Node root = createObjectNode();

        document.addChild(root);

        Node node = convertNode(orginal.getChild(0));
        if (node != null) {
            root.addChild(node);
        }

        return document;
        */

        return convertNode(original);
    }

    public Node convertNode(Node original) {

        ///////////////////////////////////////////////////////////

        if (original == null) {
            return null;
        }

        int nodeType = original.getType();
        if (isSkipNode(nodeType)) {
            return null;
        }

        Node node = null;

        ///////////////////////////////////////////////////////////

        String text = original.getText();
        String textType = getTextType(nodeType);

        ///////////////////////////////////////////////////////////

        if (!StrLib.isEmpty(textType)) {

            //TODO: Check empty and blank
            //if (StrLib.isEmpty(text)) {
            //    return null;
            //}

            if (!StrLib.isEmpty(text) && !StrLib.isQuoted(text)) {
                text = StrLib.quote(text);                
            }

            node = createAttributeNode();

            node.setName(textType);
            node.setText(text);    

            return node;
        }

        ///////////////////////////////////////////////////////////

        boolean hasAttributes = original.hasAttributes();
        boolean hasChildren = original.hasChildren();
        boolean singleNode = !hasAttributes && !hasChildren;

        //if (singleNode) {
            //TODO: Check empty and blank
            //if (StrLib.isEmpty(text)) {
            //    return null;
            //}

        //}

        node = createAttributeNode();

        node.setName(original.getName());

        // TODO: Fix empty value or skip it
        if (StrLib.isEmpty(text)) {
            text = emptyText();
        }

        node.setText(text);

        if (singleNode) {
            return node;
        }

        ///////////////////////////////////////////////////////////

        Node objNode = createObjectNode();

        node.addChild(objNode);

        ///////////////////////////////////////////////////////////

        convertAttrbutes(original, objNode);

        convertChildren(original, objNode);

        ///////////////////////////////////////////////////////////

        return node;
    }


    //void convertAttrbutes(Node original, Node node) {
    //    convertAttrbutesAsChildren(original, node);
    //}

    public String getTextType(int nodeType) {
        if (nodeType == XmlParser.TEXT_NODE) {
            return "#text";
        } else if (nodeType == XmlParser.CDATA_SECTION_NODE) {
            return "#cdata";
        } else if (nodeType == XmlParser.COMMENT_NODE) {
            return "#comment";
        }
        return null;
    }

    public String toString() {
        return "Xml2BaseConverter";
    }

}
