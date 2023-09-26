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

package plazma.lib.data.xml;

import plazma.lib.data.json.JsonParser;
import plazma.lib.data.node.Node;

public class Xml2JsonConverter extends Xml2BaseConverter {

    public Node createDocumentNode() {
        return createObjectNode();
    }

    public Node createObjectNode() {
        Node node = new Node();
        node.setType(JsonParser.OBJECT_TYPE);
        return node;
    }

    public Node createArrayNode() {
        Node node = new Node();
        node.setType(JsonParser.ARRAY_TYPE);
        return node;
    }

    public Node createAttributeNode() {
        Node node = new Node();
        node.setType(JsonParser.ATTRIBUTE_TYPE);
        return node;
    }

    public String toString() {
        return "Xml2JsonConverter";
    }

}
