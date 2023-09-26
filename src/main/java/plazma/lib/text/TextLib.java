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

package plazma.lib.text;

import java.util.ArrayList;
import java.util.List;

import plazma.lib.data.xml.XmlTokenizerContext;

public class TextLib {

    /**
     * Tokenize string and return tokens.
     * 
     * @param str
     * @param seperators
     * @param include
     * @return
     */
    public static String[] tokenize(String str, String separators, boolean include) {
        return tokenize(str, separators == null ? null : separators.toCharArray(), include);
    }

    /**
     * Tokenize string and return tokens.
     * 
     * @param str
     * @param seperators
     * @param include
     * @return
     */
    public static String[] tokenize(String str, char[] separators, boolean include) {
        return tokenize(str, separators, null);
    }

    /**
     * Tokenize string and return tokens. Use token separators and exclude parsing
     * tokens in standard quotes
     * 
     * @param str
     * @param separators
     * @param excludeSeparators
     * @return
     */
    public static String[] tokenize(String str, char[] separators, char[] excludeSeparators) {
        return tokenize(str, separators, excludeSeparators, null, null);
    }

    /**
     * Tokenize string and return tokens. Use token separators and exclude parsing
     * tokens in standard quotes
     * 
     * @param str
     * @param separators
     * @param excludeSeparators
     * @param startQuotes
     * @param endQuotes
     * @return
     */
    public static String[] tokenize(String str, char[] separators, char[] excludeSeparators, char[] startQuotes,
            char[] endQuotes) {
        TokenizerContext context = new XmlTokenizerContext();
        context.separators = toStringArray(separators);
        context.excludeSeparators = toStringArray(excludeSeparators);
        context.flexSeparators = null;
        context.startQuotes = toStringArray(startQuotes);
        context.endQuotes = toStringArray(endQuotes);

        return tokenize(context, str);
    }

    private static String[] toStringArray(char[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return new String[0];
        }
        String[] result = new String[array.length];
        char[] element = null;
        for (int i = 0; i < array.length; i++) {
            element = new char[] { array[i] };
            result[i] = new String(element);
        }
        return result;
    }

    ////

    public static String[] tokenize(TokenizerContext context, String str) {
        char[] input = str.toCharArray();
        return tokenize(context, input);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GENERAL
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String[] tokenize(TokenizerContext context, char[] input) {

        if (input == null) {
            // String is null - no processing
            return null;
        }

        if (context.isEmptySeparators()) {
            // Separators are empty - no processing
            return new String[] { new String(input) };
        }

        // Create result
        List<String> result = new ArrayList<String>();
        boolean includeSeparator = context.isEmptyExcludeSeparators();

        int offset = 0; // offset buffer
        int count = 0; // count in buffer

        // prepare quote arrays
        if (context.startQuotes != null && context.endQuotes == null) {
            context.endQuotes = context.startQuotes;

        } else if (context.startQuotes == null && context.endQuotes != null) {
            context.startQuotes = context.endQuotes;
        }

        int quoteCount = (context.startQuotes == null && context.endQuotes == null) ? 0
                : Math.min(context.startQuotes.length, context.endQuotes.length);

        // Quote level (count)
        int[] quoteLevel = new int[quoteCount];
        int i = 0;
        int size = input.length;

        // Reset context
        context.inTextState = false;

        // Processing input and ignore separators if we are in quote tree
        while (i < size) {

            char ch = input[i];

            // CHECK-1: ESCAPE: If useEscape then ignore \' and \"
            if (ch == '\\' && context.useEscape && (i + 1) < size) {

                char ch2 = input[i + 1];

                if ((ch2 == '\'' || ch2 == '\"') && inQuote(quoteLevel)) {
                    i += 2;
                    count += 2;
                    continue;
                }
            }

            // Reset quote context
            context.isStartQuote = false;
            context.isEndQuote = false;

            boolean isFindQuote = false;
            int quoteIndex = -1;
            int tokenLen = 0; // tokenLen: CHAR, QUOTE, SEPARATOR

            // For non TextState only
            if (!context.inTextState) {

                int startQuoteIndex = -1;
                int endQuoteIndex = -1;

                // CHECK-2: QUOTE: start, end
                startQuoteIndex = findQuoteIndex(context, input, offset + count, context.startQuotes, ch);
                endQuoteIndex = findQuoteIndex(context, input, offset + count, context.endQuotes, ch);

                if (startQuoteIndex > -1) {
                    context.isStartQuote = true;
                    quoteIndex = startQuoteIndex;
                    tokenLen = context.startQuotes[quoteIndex].length();
                }

                if (endQuoteIndex > -1) {
                    context.isEndQuote = true;
                    quoteIndex = endQuoteIndex;
                    tokenLen = context.endQuotes[quoteIndex].length();
                }

                // CHECK-R: Real quote for 'parse' state only
                // Start or End: 'ch' is quote
                isFindQuote = context.isFindQuote(ch);

            }

            // If real quote then update quote level and shift
            if (isFindQuote) {

                // Change quote level
                updateQuote(quoteLevel, quoteIndex, context.isStartQuote, context.isEndQuote);
                i += tokenLen; // shift: quoteLen
                count += tokenLen; // shift: quoteLen

            } else {
                boolean inQuoteState = inQuote(quoteLevel);

                // In quote mode we don't analyse separators
                if (inQuoteState) {
                    // symbol
                    i++; // next char
                    count++; // count buffer
                    continue;
                }

                // CHECK-3: SEPARATOR
                int separatorIndex = findSeparatorIndex(context, input, offset + count, context.separators, ch);
                String separator = null;
                boolean isFlexSeparator = false;
                boolean inTextPrev = false;

                if (separatorIndex > -1) {
                    separator = context.separators[separatorIndex];
                    isFlexSeparator = in(context.flexSeparators, separator);
                    inTextPrev = context.inTextState;

                    // For like XML: XML Node content is not parsable
                    context.updateState(separator);

                    if (inTextPrev && context.inTextState) {
                        separatorIndex = -1;
                    }
                }

                // CHECK-4: SEPARATOR or SYMBOL
                if (separatorIndex > -1 && (!isFlexSeparator || (isFlexSeparator && context.inParseState))) {

                    if (count > 0) {

                        // flush before chars
                        // ADD: token
                        String token = new String(input, offset, count);
                        result.add(token);
                    }

                    // separator
                    if (includeSeparator || !in(context.excludeSeparators, separator)) {

                        // ADD: separator
                        result.add(separator);
                    }

                    if (offset <= size - 1) {
                        offset = i + separator.length(); // next char
                        i = offset; // next char
                        count = 0; // reset buffer
                    }
                } else {

                    // symbol
                    i++; // next char
                    count++; // count in buffer
                }

            }

        }

        if (count > 0) {

            // flush before chars
            // ADD: token
            String token = new String(input, offset, count);
            result.add(token);
        }

        return result.toArray(new String[0]);
    }

    ////

    private static int findTokenIndex(char[] input, int offset, String[] tokens, char ch) {

        int index = -1;
        String curr = null;
        String token = null;

        for (int i = 0; i < tokens.length; i++) {
            token = tokens[i];
            if (!isLikeToken(input, offset, token, ch)) {
                continue;
            }
            if (curr == null || curr.length() < token.length()) {
                curr = token;
                index = i;
            }
        }
        return index;
    }

    // ALT
    private static int findQuoteIndex(TokenizerContext context, char[] input, int offset, String[] separators, char ch) {
        if (separators == null || separators.length == 0 || ch == '\0') {
            return -1;
        }

        // EXP: Short check
        // if (!context.isQuoteChar(ch)) {
        // return -1;
        // }

        return findTokenIndex(input, offset, separators, ch);
    }

    // ALT
    private static int findSeparatorIndex(TokenizerContext context, char[] input, int offset, String[] separators,
            char ch) {
        if (separators == null || separators.length == 0 || ch == '\0') {
            return -1;
        }

        // EXP: Short check
        // if (!context.isSeparatorChar(ch)) {
        // return -1;
        // }

        return findTokenIndex(input, offset, separators, ch);
    }

    private static boolean isLikeToken(char[] input, int offset, String token, char ch) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        // Check first char
        if (ch != token.charAt(0)) {
            return false;
        }

        if (token.length() == 1) {
            return true;
        }

        if (offset + token.length() >= input.length) {
            return false;
        }

        for (int i = 0; i < token.length(); i++) {
            if (token.charAt(i) != input[i + offset]) {
                return false;
            }
        }
        return true;
    }

    private static boolean in(String[] array, String str) {
        if (array == null || array.length == 0 || str == null) {
            return false;
        }

        // WARNING! Need sort for correct result
        // int index = Arrays.binarySearch(array, str);
        // return index > -1;

        for (int i = 0; i < array.length; i++) {
            if (str.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean inQuote(int[] quoteLevel) {
        for (int k = 0; k < quoteLevel.length; k++) {
            if (quoteLevel[k] > 0) {
                return true;
            }
        }
        return false;
    }

    private static void updateQuote(int[] quoteLevel, int quoteIndex, boolean isStart, boolean isEnd) {
        // Change quote level
        if (isStart && isEnd) { // identical
            if (quoteLevel[quoteIndex] == 0) {
                quoteLevel[quoteIndex] = 1;
            } else {
                quoteLevel[quoteIndex] = 0;
            }
        } else {
            if (isStart) {

                // Start quote: increment level
                quoteLevel[quoteIndex] = quoteLevel[quoteIndex] + 1;
            } else if (isEnd) {

                // End quote: decrement level
                quoteLevel[quoteIndex] = quoteLevel[quoteIndex] - 1;
                if (quoteLevel[quoteIndex] < 0) {
                    quoteLevel[quoteIndex] = 0;
                }
            }
        }
        
    }

}
