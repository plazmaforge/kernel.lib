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

import java.util.Map;

import plazma.kernel.lib.data.node.Node;
import plazma.kernel.lib.text.ParserContext;

public class XmlParserContext extends ParserContext {

    public int currEvent = 0;
    public int nodeEvent = 0;

    public String currTag;
    public String currAttribute;
    public Node currNode;
    
    public Map<Integer, String> map;

    public XmlParserContext() {
        super();
    }

    public String toString() {
        return "XmlParserContext[]";
    }

    public void destroy() {
        if (map != null) {
            map.clear();
            map = null;
        }
    }

}
