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

import plazma.lib.text.TextLib;

public class XmlTokenizer {

    public static final String[] WS_SEPARATORS = {" ", "\r\n", "\r", "\n", "\t", "\f"};

    public static final String[] SEPARATORS = {"<", ">", "</", "/>", "<?", "?>", "<!", "!>", "<!--", "-->", "<![CDATA[", "]]>", "=", " ", "\r\n", "\r", "\n", "\t", "\f"};

    // Separate [ALWAYS] but exclude in tokens
    public static final String[] EXCLUDE_SEPARATORS = WS_SEPARATORS;

    // Separate [IN PARSE MODE ONLY] but include if it is not exclude separator
    public static final String[] FLEX_SEPARATORS = WS_SEPARATORS;

    public static final String[] START_QUOTES = {"'", "\""};

    public static final String[] END_QUOTES = {"'", "\""};

    public String[] tokenizeXmlFromText(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        char[] array = input.toCharArray();
        return tokenizeXmlFromArray(array);
    }

    public String[] tokenizeXmlFromArray(char[] input) {
        if (input == null || input.length == 0) {
            return null;
        }

        XmlTokenizerContext context = new XmlTokenizerContext();
        context.separators = SEPARATORS;
        context.excludeSeparators = EXCLUDE_SEPARATORS;
        context.flexSeparators = FLEX_SEPARATORS;
        context.startQuotes = START_QUOTES;
        context.endQuotes = END_QUOTES;

        String[] tokens = TextLib.tokenize(context, input);
        return tokens;

    }

}
