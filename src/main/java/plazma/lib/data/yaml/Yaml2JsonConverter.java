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

import plazma.lib.data.json.JsonParser;
import plazma.lib.data.node.BaseNodeConverter;
import plazma.lib.data.node.Node;

public class Yaml2JsonConverter extends BaseNodeConverter {

    public Node convertDocumentNode(Node original) {
        return convertNode(original); // by default
    }

    public Node convertNode(Node original) {
        return convertDefaultNode(original); // by default
    }

    ////

    public boolean isObjectType(int nodeType) {
        return nodeType == YamlParser.OBJECT_TYPE;
    }

    public boolean isArrayType(int nodeType) {
        return nodeType == YamlParser.ARRAY_TYPE;
    }

    public boolean isAttributeType(int nodeType) {
        return nodeType == YamlParser.ATTRIBUTE_TYPE;
    }

    public int convertNodeType(int nodeType) {
        if (nodeType == YamlParser.OBJECT_TYPE) {
            return JsonParser.OBJECT_TYPE;
        } else if (nodeType == YamlParser.ARRAY_TYPE) {
            return JsonParser.ARRAY_TYPE;
        } else if (nodeType == YamlParser.ATTRIBUTE_TYPE) {
            return JsonParser.ATTRIBUTE_TYPE;
        }
        return 0; // by default
    }

    public int convertNodeSubType(int nodeSubType) {
        return 0; // by default
    }

    public String toString() {
        return "Yaml2JsonConverter";
    }

}
