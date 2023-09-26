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

import plazma.lib.text.WriterConfig;

public class XmlWriterConfig extends WriterConfig {

    public static final int XML_INLINE_NODE_LIMIT = 80;
    public static final int XML_INLINE_NODE_MIN = 10;
    public static final int XML_INLINE_NODE_MAX = 1000;

    public static final String XML_INDENT_SPACE = "space";
    public static final String XML_INDENT_TAB = "tab";

    ////

    public static final String INDENT_TAB_VALUE = "\t";
    public static final String INDENT_SPACE_VALUE = " ";
    public static final String INDENT_SPACE_VALUE_2 = "  ";

    public boolean inlineFlag;         // Output all text in one line
    public boolean inlineNodeFlag;     // Output node text in one line
    public int inlineNodeLimit;        // Max lenght of text for inline node
    public String indent;              // Indent node char: 'space', 'tab'
    public String tagCase;             // Tag name case: 'lower', 'upper', 'camel' ...
    public String attributeCase;       // Tag name case: 'lower', 'upper', 'camel' ...
    public String attributeQuote;      // Attribute value quote: 'single', 'double', 'none'
    public boolean trimAttribute;      // Trim attributes
    public boolean skipComment;        // Skip all comments
    public boolean skipMeta;           // Skip <?...?>
    public boolean skipDTD;            // Skip <!DOCTYPE...>
    public boolean colorized;

    

    public XmlWriterConfig() {
        inlineFlag = false;
        inlineNodeFlag = false;
        inlineNodeLimit = XML_INLINE_NODE_LIMIT;
        indent = "space";              // 'space', 'tab'
        tagCase = "";                  // 'lower', 'upper', 'camel' ...
        attributeCase = "";            // 'lower', 'upper', 'camel' ...
        attributeQuote = "";           // 'single', 'double', 'none'
        trimAttribute = false;         // RAW
        skipComment = false;           // Use comments
        skipDTD = false;               // Use <!DOCTYPE...>
        colorized = false;
        
    }

    public int getInlineNodeLimit() {
        return normalizeInlineNodeLimit(inlineNodeLimit);
    }

    /**
     * Return real indent (tab or 1/2 spaces)
     * 
     * @return
     */
    public String getIndentValue() {
        return normalizeIndentValue(indent);
    }

    public String getAttributeQuoteValue() {
        return normalizeQuoteValue(attributeQuote);
    }

    public String toString() {
        return "XmlWriterConfig[]";
    }

}
