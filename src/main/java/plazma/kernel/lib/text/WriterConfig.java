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

package plazma.kernel.lib.text;

public class WriterConfig {
        
    public static final int INLINE_NODE_LIMIT = 80;
    public static final int INLINE_NODE_MIN = 10;
    public static final int INLINE_NODE_MAX = 1000;

    public static final String INDENT_SPACE = "space";
    public static final String INDENT_TAB = "tab";
    public static final String INDENT_DEFAULT= INDENT_SPACE;

    public static final String INDENT_TAB_VALUE = "\t";
    public static final String INDENT_NO_VALUE = "";
    public static final String INDENT_SPACE_VALUE = " ";
    public static final String INDENT_SPACE_VALUE_2 = "  ";
    public static final String INDENT_SPACE_VALUE_3 = "   ";
    public static final String INDENT_SPACE_VALUE_4 = "    ";
    public static final String INDENT_DEFAULT_VALUE = INDENT_SPACE_VALUE_2;


    public static final String QUOTE_NONE = "none";
    public static final String QUOTE_SINGLE = "single";
    public static final String QUOTE_DOUBLE = "double";
    public static final String QUOTE_DEFAULT = QUOTE_DOUBLE;

    public static final String QUOTE_NONE_VALUE = "";
    public static final String QUOTE_SINGLE_VALUE = "'";
    public static final String QUOTE_DOUBLE_VALUE = "\"";
    public static final String QUOTE_DEFAULT_VALUE = QUOTE_DOUBLE_VALUE;


    public WriterConfig() {
        super();
    }
    
    public int normalizeInlineNodeLimit(int value) {
        if (value < 1) {
            return INLINE_NODE_LIMIT; // < 1 -> DEF
        }
        if (value < INLINE_NODE_MIN) {
            return INLINE_NODE_MIN;  // < MIN -> MIN
        }
        if (value > INLINE_NODE_MAX) {
            return INLINE_NODE_MAX;  // > MAX -> MAX
        }
        return value;
    }

    /**
     * Return real indent (tab or 1/2 spaces) by config indent 
     * @param value
     * @return
     */
    public String normalizeIndentValue(String value) {
        if (value == null) {
            return INDENT_DEFAULT_VALUE;
        }

        if (value.equals("tab")) {
            return INDENT_TAB_VALUE;
        }

        // TODO: What about '<n>', <n>space, <n>tab
        if (value.equals("0")) {
            return INDENT_NO_VALUE;
        }
        if (value.equals("1")) {
            return INDENT_SPACE_VALUE;
        }
        if (value.equals("2")) {
            return INDENT_SPACE_VALUE_2;
        }
        if (value.equals("3")) {
            return INDENT_SPACE_VALUE_3;
        }
        if (value.equals("4")) {
            return INDENT_SPACE_VALUE_4;
        }

        return INDENT_DEFAULT_VALUE;
    }

    public String normalizeQuoteValue(String value) {
        return value == QUOTE_SINGLE ? QUOTE_SINGLE_VALUE : QUOTE_DOUBLE_VALUE; // TODO: What about 'none'
    }

    ////

    public String toString() {
        return "WriterConfig[]";
    }


}
