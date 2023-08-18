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

import plazma.kernel.lib.text.TokenizerContext;

public class XmlTokenizerContext extends TokenizerContext {

    public XmlTokenizerContext() {
        super();
    }

    ////

    public void updateState(String separator) {

        if (separator == null || separator.isEmpty()) {
            return;
        }

        // Implementation for XML
        if (separator.charAt(0) == '<' && !separator.equals("<![CDATA[") && !separator.equals("<!--")) {
            inParseState = true;  // set
        } else if (separator.charAt(0) == '>' || separator.equals("<![CDATA[") || separator.equals("<!--")) {
            inParseState = false; // reset
        }

        if ((separator.equals("<![CDATA[") || separator.equals("<!--"))) {
            inTextState = true;   // set
        } else if (separator.equals("]]>") || separator.equals("-->")) {
            inTextState = false; // reset
        }

    }

    public boolean isQuoteChar(char ch) {
        return (ch == '<' 
                || ch == '\'' 
                || ch == '"' 
                || ch == '-' 
                || ch == ']');
    }

    public boolean isSeparatorChar(char ch) {
        return (ch == '<' 
                || ch == '>' 
                || ch == '\'' 
                || ch == '"' 
                || ch == ' ' 
                || ch == '=' 
                //|| ch == '?' 
                || ch == '-'
                || ch == '\r' 
                || ch == '\n' 
                || ch == '/' 
                || ch == ']');
    }

    public String toString() {
        return "XmlTokenizerContext[]";
    }

}
