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

package plazma.kernel.lib.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import plazma.kernel.lib.array.ArrayLib;
import plazma.kernel.lib.collection.CollectionLib;

public class StrLib {
    
    // Functions
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1 empty, blank, size
    //
    // - isEmpty(String str)                                               - check empty
    // - isEmpty(String str, boolean trim)                                 - trim, check empty
    //
    // - isBlank(String str)                                               - check blank
    // - size(String str)                                                  - length
    //
    // - equals(String str1, String str2)
    //
    // - equalsContent(String str, CharSequence cs)
    // - equalsContent(String str, char[] array)
    //
    // 1.2 normalization
    //
    // - normalize(String str)                                             - trim, '' - > null
    // - emptyIfNull(String str)                                           - null -> ''
    //
    // - nullIfEmpty(String str)                                           - '' -> null
    // - nullIfEmpty(String str, boolean trim)                             - trim, '' -> null
    //
    // - defaultIfNull(String str, String defaultStr)                      - str == null ? defaultStr: str
    // - defaultIfEmpty(String str, String defaultStr)                     - isEmpty(str) ? defaultStr: str
    //
    // 1.3 trim (left, right)
    // 
    // - trim(String str)                                                  - all trim (left, right)
    // - trim(String str, char ch)
    //
    // - ltrim(String str)                                                 - left trim
    // - ltrim(String str, char ch)
    //
    // - rtrim(String str)                                                 - right trim
    // - rtrim(String str, char ch)
    //
    // 1.4
    //
    // - findFirstNotOf(String str, char ch)
    // - findFirstNotOf(String str, char ch, int start)
    //
    // - findLastNotOf(String str, char ch)
    // - findLastNotOf(String str, char ch, int end)

    /////////////////////////////////////////////////////////////////////////////////
    // 2.1
    //
    // - replicate(String str, int n)                                      - replicate("abc", 3) = "abcabcabc" : repeat (?)
    // - replicate(char ch, int n)                                         - replicate('a', 3) = "aaa"
    //
    // 2.2
    //
    // - lpad(String str, int len)                                         - lpad("abc", 5)      = "  abc"
    // - lpad(String str, int len, String pad)                             - lpad("abc", 5, "*") = "**abc"
    // - lpad(String str, int len, char pad)                               - lpad("abc", 5, '*') = "**abc"
    //
    // - rpad(String str, int len)                                         - rpad("abc", 5)      = "abc  "
    // - rpad(String str, int len, String pad)                             - rpad("abc", 5, "*") = "abc**"
    // - rpad(String str, int len, char pad)                               - rpad("abc", 5, '*') = "abc**"
    //
    // 2.3
    //
    // - fill(String str, int len)
    // - fill(String str, int len, String pad)
    // - fill(String str, int len, char pad)
    //
    // - ellipsis(String str, int len)
    //
    // - trunc(String str, int len)
    // - trunc(String str, int len, boolean trim, boolean ellipsis)
    //
    // - left(String str, int len)
    // - right(String str, int len)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 3.1
    //
    // - capitalize(String str)                                            - capitalize("abc") = "Abc"
    // - capitalize(String str, boolean force)                             - capitalize("abC") = "Abc"
    // - capitalize(String str, Locale Locale locale)
    // - capitalize(String str, boolean force, Locale locale)
    //
    // - decapitalize(String str)                                          - decapitalize("Abc") = "abc"
    // - decapitalize(String str, boolean force)                           - decapitalize("AbC") = "abc"
    // - decapitalize(String str, Locale locale)
    // - decapitalize(String str, boolean force, Locale locale)
    //
    // - upper(String str)                                                 - upper("aBc") = "ABC" 
    // - upper(String str, Locale locale)
    //
    // - lower(String str)                                                 - lower("Abc") = "abc" 
    // - lower(String str, Locale locale)    
    //
    // - toUpperCase(String str)
    // - toUpperCase(String str, Locale locale)
    //
    // - toLowerCase(String str)
    // - toLowerCase(String str, Locale locale)
    //
    // - toCase(String str, boolean upper)
    // - toCase(String str, boolean upper, Locale locale)
    // - toCase(char ch, boolean upper)
    //
    // - toCamelCase(String str)                                           - toCamelCase("property_name") = "PropertyName"
    // - toCamelCase(String str, String separator)
    // - toCamelCase(String str, boolean capitalize)
    // - toCamelCase(String str, String separator, boolean capitalize)
    //
    // - toSnakeCase(String str)                                            - toSnakeCase("PropertyName") = "property_name"
    // - toSnakeCase(String str, String separator)
    // - toSnakeCase(String str, String boolean upper)
    // - toSnakeCase(String str, String separator, boolean upper)
    // - toSnakeCase(String str, String separator, boolean upper, boolean trim) -
    //
    // - toKebabCase(String str)                                            - toKebabCase("PropertyName") = "property-name"
    // - toKebabCase(String str, String separator)
    // - toKebabCase(String str, String boolean upper)
    // - toKebabCase(String str, String separator, boolean upper)
    // - toKebabCase(String str, String separator, boolean upper, boolean trim) -
    //
    // - reverse(String str)                                               - reverse("abc") = "cba"

    /////////////////////////////////////////////////////////////////////////////////
    // 4.1
    //
    // - startsWith(String str, String prefix)                             - startsWith("myfile.txt", "myfile") = true
    // - endsWith(String str, String suffix)                               - endsWith("myfile.txt", ".txt") = true
    //
    // - hasPrefix(String str, String prefix)                              - [alias]: hasPrefix("myfile.txt", "myfile") = true
    // - hasSuffix(String str, String suffix)                              - [alias]: hasSuffix("myfile.txt", ".txt") = true
    //
    // - startsWithIgnoreCase(String str, String prefix)                   - startsWithIgnoreCase("myfile.txt", "MyFile") = true
    // - endsWithIgnoreCase(String str, String suffix)                     - endsWithIgnoreCase("myfile.txt", ".TxT") = true
    //
    // - hasPrefixIgnoreCase(String str, String prefix)                    - hasPrefixIgnoreCase("myfile.txt", "MyFile") = true
    // - hasSuffixIgnoreCase(String str, String suffix)                    - hasSuffixIgnoreCase("myfile.txt", ".TxT") = true
    //
    // 4.2
    // 
    // - removePrefix(String str, String prefix)
    // - removePrefixes(String str, String[] prefixes)
    //
    // - removeSuffix(String str, String suffix)
    // - removeSuffixes(String str, String[] suffixes)
    //
    // 4.3
    // 
    // - isQuoted(String str)
    // - isQuoted(String str, String startQuote, String endQuote)
    // - needQuote(String str)
    // - needQuote(String str, String startQuote, String endQuote)
    // - quote(String str)
    // - quote(String str, String startQuote, String endQuote)
    // - unquote(String str)
    // - unquote(String str, String startQuote, String endQuote)
    //
    // 4.4
    // 
    // - isColumnSeparator(char ch)
    // - isColumnText(char[] array)
    // - isColumnText(char[] array, int len)
    // - isColumnText(String str)
    // - isLineText(char[] array)
    // - isLineText(char[] array, int len)
    // - isLineText(String str)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 5.1
    //
    // - countChar(String str, char ch)
    // - countString(String str, String findStr)
    // - countWord(String str)
    // - countWord(String str, String[] seperators)
    // - countLine(String str)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 6.1
    //  
    // - replaceAll(String str, String s1, String s2)
    // - replaceAll(String str, String[] oldValues, String[] newValues)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 7.1
    //      
    // - split(String str, String seperators)
    // - split(String str, String seperators, boolean include)
    // 
    // - splitWords(String str)
    // - splitWords(String str, String seperator)
    // - splitLines(String str)
	
    /////////////////////////////////////////////////////////////////////////////////
    // 8.1
    //      
    // - toString(String[] array)
    //
    // - toWordArray(String str) {
    // - toWordArray(String str, String separators)
    // - toLineArray(String str)
    //
    // 8.2
    //
    // - toWordList(String str)
    // - toWordList(String str, String separators)
    // - toLineList(String str)
    //
    // - toList(String[] array)    
    
    /////////////////////////////////////////////////////////////////////////////////
    // 9.1
    //  
    // - isDigit(char ch)
    // - isLetter(char ch)
    // - isAlpha(char ch)
    //
    // - isWhitespace(char ch)                                             -
    // - isDot(char ch)                                                    -
    // - isUnderline(char ch)                                              -
    // - isIdentifier(String str)                                          -
    //
    // 9.2
    //
    // - isUpperCase(char ch)
    // - isLowerCase(char ch)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 10.1 ISO-LATIN-1
    //  
    // - isISODigit(char ch)
    // - isISOUpperLetter(char ch)
    // - isISOLowerLetter(char ch)
    // - isISOLetter(char ch)
    //
    // - isISOWhitespace(char ch)
    // - isISODot(char ch)
    // - isISOUnderline(char ch)
    // - isISOIdentifier(String str)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 11.1
    //
    // - maxLen(String array[])                                     - May be rename to 'maxLength' ?
    //

    
    
    
    // https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/StringUtils.java
    // https://www.codota.com/code/java/methods/org.cybergarage.util.StringUtil/findFirstNotOf
    // https://www.codota.com/code/java/methods/com.github.abrarsyed.jastyle.ASUtils/findFirstNotOf
    // https://www.codota.com/code/java/classes/com.github.abrarsyed.jastyle.ASUtils
    
    // Name Convention: 
    //
    // - camelCase
    // - CamelCase
    // - snake_case
    // - Camel_Snake_Case
    // - SCREAMING_SNAKE_CASE, MACRO_CASE, CONSTANT_CASE
    // - kebab-case, dash-case, lisp-case
    // ...

    // https://en.wikipedia.org/wiki/Naming_convention_(programming)#Multiple-word_identifiers

    
    
    public static final String EMPTY_STRING = "";

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    public static final String BLANK_STRING = " ";

    public static final char BLANK_CHAR = ' ';

    public static final String ELLIPSIS = "...";

    public static final int ELLIPSIS_LEN = ELLIPSIS.length();

    public static final String DEFAULT_PAD = " ";

    public static final String DEFAULT_SEPARATORS = " \t\n\r\f";

    public static final String DEFAULT_WORD_SEPARATORS = DEFAULT_SEPARATORS + ".,;'(){}[]!?+/=<>*&^%$#@`~|\\";

    public static final String DEFAULT_CASE_SEPARATOR = "_";

    public static final String DEFAULT_CASE_SEPARATORS_ = "-_";

    public static final String DEFAULT_CASE_SEPARATORS_A = "-_A";

    public static final String DEFAULT_ALTER_CASE_SEPARATOR = "-"; // XML

    public static final String DEFAULT_SNAKE_CASE_SEPARATOR = "_"; // SNAKE

    public static final String DEFAULT_KEBAB_CASE_SEPARATOR = "-"; // KEBAB

    public static final boolean DEFAULT_FORCE_CAPITALIZE = false;

    public static final boolean DEFAULT_CAMEL_CASE_CAPITALIZE = true; // 'first_name' -> 'FirstName'

    public static final int INDEX_NOT_FOUND = -1;

    ///////////////////////////////////
    // Case Operations
    ///////////////////////////////////
    // caseOp =  1: 'myname': lowercase
    // caseOp =  2: 'MYNAME': UPPERCASE
    // caseOp =  3: 'myName': camelCase
    // caseOp =  4: 'MyName': PascalCase

    public static final int CO_NONE       = 0;
    public static final int CO_lowercase  = 1;
    public static final int CO_UPPERCASE  = 2;
    public static final int CO_camelCase  = 3;
    public static final int CO_PascalCase = 4;

    ////////////////////////////////////
    // Case Types
    ////////////////////////////////////
    // -  1. lowercase
    // -  2. UPPERCASE
    // -  3. camelCase
    // -  4. CamelCase, PascalCase
    // -  5. snake_case
    // -  6. SNAKE_CASE, MACRO_CASE, CONSTANT_CASE
    // -  7. Snake_Case
    // -  8. kebab-case, dash-case, train-case, lisp-case
    // -  9. KEBAB-CASE, DASH-CASE, TRAIN-CASE, COBOL-CASE
    // - 10. Kebab-Case, Dash-Case, Train-Case, HTTP-Header-Case
    ////////////////////////////////////

    public static final int CT_lowercase  = 1;
    public static final int CT_UPPERCASE  = 2;
    public static final int CT_camelCase  = 3;
    public static final int CT_PascalCase = 4;
    public static final int CT_snake_case = 5;
    public static final int CT_SNAKE_CASE = 6;
    public static final int CT_Snake_Case = 7;
    public static final int CT_kebab_case = 8;
    public static final int CT_KEBAB_CASE = 9;
    public static final int CT_Kebab_Case = 10;

    public static final String SNAKE_CONNECTOR = "_"; // shake_case
    public static final String KEBAB_CONNECTOR = "-"; // kebab-case

    ////

    public static final Quote SINGLE_QUOTE = new Quote("''"); // ''
    public static final Quote DOUBLE_QUOTE = new Quote("\"\""); // ""
    public static final Quote ARRAY_QUOTE = new Quote("[]"); // []
    public static final Quote FUNCTION_QUOTE = new Quote("()"); // ()
    public static final Quote OBJECT_QUOTE = new Quote("{}"); // {}

    public static final Quote[] QUOTES = {
            SINGLE_QUOTE, // single
            DOUBLE_QUOTE, // double
            ARRAY_QUOTE, 
            FUNCTION_QUOTE, 
            OBJECT_QUOTE
            };

    public static final Quote[] SD_QUOTES = {
            SINGLE_QUOTE, 
            DOUBLE_QUOTE
            };

    public static final class Quote {

        private char left;
        private char right;

        private String leftString;
        private String rightString;

        public Quote(char left, char right) {
            init(left, right);
        }

        public Quote(String quotes) {
            assert (quotes != null);
            assert (quotes.length() == 2);
            char[] array = quotes.toCharArray();
            init(array[0], array[1]);
        }

        private void init(char left, char right) {
            this.left = left;
            this.right = right;

            this.leftString = new String(new char[] { left });
            this.rightString = new String(new char[] { right });
        }

        public char getLeft() {
            return left;
        }

        public char getRight() {
            return right;
        }

        public String getLeftString() {
            return leftString;
        }

        public String getRightString() {
            return rightString;
        }

        public boolean isIdentical() {
            return left == right;
        }
    }

    private StrLib() {
    }

    //// 1.1

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(String str, boolean trim) {
        if (trim) {
            str = trim(str);
        }
        return isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int len = length(str);
        if (len == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int length(String str) {
        return str == null ? 0 : str.length();
    }

    public static int size(String str) {
        return length(str);
    }

    //

    public static boolean equals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    public static boolean equalsContent(String str, CharSequence cs) {
        if (str == null || cs == null) {
            return false;
        }
        return str.contentEquals(cs);
    }

    public static boolean equalsContent(String str, char[] array) {
        if (str == null || array == null) {
            return false;
        }
        int len = str.length();
        if (len != array.length) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (array[i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    //// 1.2

    public static String normalize(String str) {
        str = trimAll(str);
        return isEmpty(str) ? null : str;
    }
    
    public static String normalizeBlank(String str, boolean trimText, boolean skipBlank) {
        if (str == null) {
            return null;
        }
        
        // 'trimText' flag overrides 'skipBlank' flag for each type
        // If 'trimText' is true we will skip empty text after normalization
        // 'skipBlank' for non normalize text only

        if (!trimText && !skipBlank) {
            return str;
        }

        if (!trimText) {
            return isBlank(str) ? null : str;            
        }
        
        return normalize(str);
    }
    
    public static String normalizeQuote(String str) {
        if (str == null) {
            return null;
        }
        String text = str;

        // "text" -> " text  ": Normalize in quote

        if (isQuoted(text)) {
            text = unquote(text);
        }
        text = normalize(text);
        text = quote(text == null ? "" : text);
        return text;
    }

    public static String emptyIfNull(String str) {
        return str == null ? EMPTY_STRING : str;
    }

    public static String nullIfEmpty(String str) {
        return nullIfEmpty(str, false);
    }

    public static String nullIfEmpty(String str, boolean trim) {
        if (trim) {
            str = trim(str);
        }
        return isEmpty(str) ? null : str;
    }

    public static String defaultIfNull(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    //// 1.3

    // trim
    public static String trim(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.trim();
    }

    public static String trimAll(String str) {
        if (isEmpty(str)) {
            return str;
        }

        return trim(str, " \t\n\r\f"); // \v - ?
        // return str.trim();
    }

    public static String trim(String str, char ch) {
        if (isEmpty(str)) {
            return str;
        }
        int posFirst = findFirstNotOf(str, ch); // TODO: ch <= ' ' (?): See String.trim()

        // check last char
        if (posFirst == length(str) - 1) {
            return EMPTY_STRING;
        }

        int posLast = findLastNotOf(str, ch); // TODO: ch <= ' ' (?): See String.trim()

        // not found: left, right
        if (posFirst == INDEX_NOT_FOUND && posLast == INDEX_NOT_FOUND) {
            return str;
        }

        // not found: right
        if (posLast == INDEX_NOT_FOUND) {
            return str.substring(posFirst + 1);
        }

        return str.substring(posFirst + 1, posLast);
    }

    public static String trim(String str, String terms) {
        if (isEmpty(str)) {
            return str;
        }
        int posFirst = findFirstNotOf(str, terms); // TODO: ch <= ' ' (?): See String.trim()

        // check last char
        if (posFirst == length(str) - 1) {
            return EMPTY_STRING;
        }

        int posLast = findLastNotOf(str, terms); // TODO: ch <= ' ' (?): See String.trim()

        // not found: left, right
        if (posFirst == INDEX_NOT_FOUND && posLast == INDEX_NOT_FOUND) {
            return str;
        }

        // not found: right
        if (posLast == INDEX_NOT_FOUND) {
            return str.substring(posFirst + 1);
        }

        return str.substring(posFirst + 1, posLast);
    }

    // left trim
    public static String ltrim(String str) {
        return ltrim(str, BLANK_CHAR);
    }

    // left trim
    public static String ltrim(String str, char ch) {
        if (isEmpty(str)) {
            return str;
        }

        int pos = findFirstNotOf(str, ch); // TODO: ch <= ' ' (?): See String.trim()

        // not found
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }

        // check last char
        if (pos == length(str) - 1) {
            return EMPTY_STRING;
        }

        return str.substring(pos + 1);
    }

    // left trim
    public static String ltrim(String str, String terms) {
        if (isEmpty(str) || isEmpty(terms)) {
            return str;
        }

        int pos = findFirstNotOf(str, terms); // TODO: ch <= ' ' (?): See String.trim()

        // not found
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }

        // check last char
        if (pos == length(str) - 1) {
            return EMPTY_STRING;
        }

        return str.substring(pos + 1);
    }

    // right trim
    public static String rtrim(String str) {
        return rtrim(str, BLANK_CHAR);
    }

    // right trim
    public static String rtrim(String str, char ch) {
        if (isEmpty(str)) {
            return str;
        }

        int pos = findLastNotOf(str, ch); // TODO: ch <= ' ' (?): See String.trim()

        // not found
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }

        // check first char
        if (pos == 0) {
            return EMPTY_STRING;
        }

        return str.substring(0, pos);
    }

    // right trim
    public static String rtrim(String str, String terms) {
        if (isEmpty(str) || isEmpty(terms)) {
            return str;
        }

        int pos = findLastNotOf(str, terms); // TODO: ch <= ' ' (?): See String.trim()

        // not found
        if (pos == INDEX_NOT_FOUND) {
            return str;
        }

        // check first char
        if (pos == 0) {
            return EMPTY_STRING;
        }

        return str.substring(0, pos);
    }

    //// 1.4

    public static int findFirstNotOf(String str, char ch) {
        return findFirstNotOf(str, ch, 0);
    }

    public static int findFirstNotOf(String str, char ch, int start) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        int len = str.length();
        if (start < 0 || start > len - 1) {
            return INDEX_NOT_FOUND;
        }
        int pos = INDEX_NOT_FOUND;

        for (int index = start; index < len; index++) {
            if (str.charAt(index) != ch) {
                break;
            }
            pos = index;
        }
        return pos;
    }

    public static int findFirstNotOf(String str, String terms) {
        return findFirstNotOf(str, terms, 0);
    }

    public static int findFirstNotOf(String str, String terms, int start) {
        if (isEmpty(str) || isEmpty(terms)) {
            return INDEX_NOT_FOUND;
        }
        int len = str.length();
        if (start < 0 || start > len - 1) {
            return INDEX_NOT_FOUND;
        }
        int pos = INDEX_NOT_FOUND;

        for (int index = start; index < len; index++) {
            if (!in(str.charAt(index), terms)) {
                break;
            }
            // for (int index2 = 0; index2 < terms.length(); index2++) {
            // if (str.charAt(index) != terms.charAt(index2)) {
            // return pos;
            // }
            // pos = index;
            // }
            pos = index;
        }
        return pos;
    }

    private static boolean in(char ch, String elements) {
        for (int index2 = 0; index2 < elements.length(); index2++) {
            if (ch == elements.charAt(index2)) {
                return true;
            }
        }
        return false;
    }

    public static int findLastNotOf(String str, char ch) {
        return findLastNotOf(str, ch, (str == null ? 0 : str.length() - 1));
    }

    public static int findLastNotOf(String str, char ch, int end) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        int len = str.length();
        if (end < 0 || end > len - 1) {
            return INDEX_NOT_FOUND;
        }
        int pos = INDEX_NOT_FOUND;

        for (int index = end; index >= 0; index--) {
            if (str.charAt(index) != ch) {
                break;
            }
            pos = index;
        }
        return pos;
    }

    public static int findLastNotOf(String str, String terms) {
        return findLastNotOf(str, terms, (str == null ? 0 : str.length() - 1));
    }

    public static int findLastNotOf(String str, String terms, int end) {
        if (isEmpty(str) || isEmpty(terms)) {
            return INDEX_NOT_FOUND;
        }
        int len = str.length();
        if (end < 0 || end > len - 1) {
            return INDEX_NOT_FOUND;
        }
        int pos = INDEX_NOT_FOUND;

        for (int index = end; index >= 0; index--) {
            if (!in(str.charAt(index), terms)) {
                break;
            }
            // for (int index2 = 0; index2 < terms.length(); index2++) {
            // if (str.charAt(index) != terms.charAt(index2)) {
            // return pos;
            // }
            // }
            pos = index;
        }
        return pos;
    }

    //// 2.1

    /**
     * Return <b>n</b> copy of <b>str</b> For example: replicate("*", 4) = "****"
     * replicate("*", 0) = "" replicate("-.", 4) = "-.-.-.-."
     * 
     * @param str
     * @param n
     * @return
     */
    public static String replicate(String str, int n) {
        if (isEmpty(str)) {
            return str;
        }
        if (n < 1) {
            return BLANK_STRING;
        }
        if (n == 1) {
            return str;
        }
        StringBuilder buf = new StringBuilder(str.length() * n);
        for (int i = 0; i < n; i++) {
            buf.append(str);
        }
        return buf.toString();
    }

    /**
     * Return <b>n</b> copy of <b>ch</b> For example: replicate('*', 4) = "****"
     * replicate('*', 0) = ""
     * 
     * @param ch
     * @param n
     * @return
     */
    public static String replicate(char ch, int n) {
        if (n < 1) {
            return EMPTY_STRING;
        }
        char[] chars = new char[n];
        Arrays.fill(chars, ch);
        return new String(chars);
    }

    //// 2.2

    /**
     * Return left pad a string with ' '.
     * 
     * @param str
     * @param len
     * @return
     */
    public static String lpad(String str, int len) {
        return lpad(str, len, DEFAULT_PAD);
    }

    /**
     * Return left pad a string with a specified string.
     * 
     * @param str
     * @param len
     * @param pad
     * @return
     */
    public static String lpad(String str, int len, String pad) {
        if (str == null) {
            return null;
        }
        if (pad == null || !isValidPadLen(len)) {
            return str;
        }
        int strLen = str.length();
        int padLen = pad.length();
        if (len <= strLen || padLen == 0) {
            return str;
        }
        int ln = len - strLen;
        ln = (int) Math.ceil((double) ln / padLen);
        String s = replicate(pad, ln) + str;
        return s.substring(s.length() - len);
    }

    /**
     * Return left pad a string with a specified character.
     * 
     * @param str
     * @param len
     * @param pad
     * @return
     */
    public static String lpad(String str, int len, char pad) {
        if (str == null) {
            return null;
        }
        if (pad == 0 || !isValidPadLen(len)) {
            return str;
        }
        char[] ch = new char[1];
        ch[0] = pad;
        return lpad(str, len, new String(ch));
    }

    // rpad

    /**
     * Return right pad a string with ' '.
     * 
     * @param str
     * @param len
     * @return
     */
    public static String rpad(String str, int len) {
        return rpad(str, len, DEFAULT_PAD);
    }

    /**
     * Return right pad a string with a specified string.
     * 
     * @param str
     * @param len
     * @param pad
     * @return
     */
    public static String rpad(String str, int len, String pad) {
        if (str == null) {
            return null;
        }
        if (pad == null || !isValidPadLen(len)) {
            return str;
        }
        int strLen = str.length();
        int padLen = pad.length();
        if (len <= strLen || padLen == 0) {
            return str;
        }
        int ln = len - strLen;
        ln = (int) Math.ceil((double) ln / padLen);
        String s = str + replicate(pad, ln);
        return s.substring(0, len);
    }

    /**
     * Return right pad a string with a specified character.
     * 
     * @param str
     * @param len
     * @param pad
     * @return
     */
    public static String rpad(String str, int len, char pad) {
        if (str == null) {
            return null;
        }
        if (pad == 0 || !isValidPadLen(len)) {
            return str;
        }
        char[] ch = new char[1];
        ch[0] = pad;
        return rpad(str, len, new String(ch));
    }

    private static boolean isValidPadLen(int len) {
        return len > 0;
    }

    //// 2.3

    // fill

    public static String fill(String str, int len) {
        return fill(str, len, DEFAULT_PAD);
    }

    public static String fill(String str, int len, String pad) {

        // format returns not null string always
        if (str == null || len < 1) {
            return EMPTY_STRING;
        }
        int strLen = str.length();
        if (strLen == len) {
            return str;
        }
        if (strLen < len) {
            // add <pad> to right side
            return rpad(str, len, pad);
        } else {
            // remove chars from right side
            return trunc(str, len, true, true);
        }
    }

    public static String fill(String str, int len, char pad) {
        return fill(str, len, String.valueOf(pad));
    }
    
    // ellipsis
    public static String ellipsis(String str, int len) {
        return trunc(str, len, true, true);
    }

    // trunc
    public static String trunc(String str, int len) {
        return trunc(str, len, true, false);
    }

    // trunc
    public static String trunc(String str, int len, boolean trim, boolean ellipsis) {
        if (str == null) {
            return null;
        }
        if (len < 1) {
            return str;
        }
        if (trim) {
            str = str.trim();
        }
        if (str.length() <= len) {
            return str;
        }
        if (ellipsis) {
            if (len <= ELLIPSIS_LEN) {
                return str.substring(0, len);
            }
            return str.substring(0, len - ELLIPSIS_LEN) + ELLIPSIS;
        }
        return str.substring(0, len);
    }

    // left
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substring(0, len);
    }

    // right
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substring(strLen - len);
    }

    //// 3.1

    // TODO: What about: inverse, toInverseCase(): 'aBcdE' -> 'AbCDe'

    /**
     * Capitalize string: capitalize("name") = "Name" capitalize("n") = "N"
     * capitalize("NAME") = "NAME"
     * 
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        return _toCapitalize(str, true, DEFAULT_FORCE_CAPITALIZE, null);
    }

    /**
     * Capitalize string: capitalize("name", true) = "Name" capitalize("name",
     * false) = "Name" capitalize("nAmE", false) = "nAmE" capitalize("nAmE", true) =
     * "Name"
     * 
     * @param str
     * @param force - if true force update last characters (lower case)
     * @return
     */
    public static String capitalize(String str, boolean force) {
        return _toCapitalize(str, true, force, null);
    }

    public static String capitalize(String str, Locale locale) {
        return _toCapitalize(str, true, DEFAULT_FORCE_CAPITALIZE, locale);
    }

    public static String capitalize(String str, boolean force, Locale locale) {
        return _toCapitalize(str, true, force, locale);
    }

    /**
     * Decapitalize string: capitalize("Name") = "name" capitalize("N") = "n"
     * capitalize("NAME") = "nNAME"
     * 
     * @param str
     * @return
     */
    public static String decapitalize(String str) {
        return _toCapitalize(str, false, DEFAULT_FORCE_CAPITALIZE, null);
    }

    /**
     * Decapitalize string: capitalize("Name", false) = "name" capitalize("Name",
     * true) = "name" capitalize("NaMe", false) = "naMe" capitalize("NaMe", true) =
     * "name"
     * 
     * @param str
     * @param force - if true force update last characters (lower case)
     * @return
     */
    public static String decapitalize(String str, boolean force) {
        return _toCapitalize(str, false, force, null);
    }

    public static String decapitalize(String str, Locale locale) {
        return _toCapitalize(str, false, DEFAULT_FORCE_CAPITALIZE, locale);
    }

    public static String decapitalize(String str, boolean force, Locale locale) {
        return _toCapitalize(str, false, force, locale);
    }

    //

    static String _toCapitalize(String str, boolean upper, boolean force, Locale locale) {
        if (isEmpty(str)) {
            return str;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (str.length() == 1) {
            return __toFirstCharCase(str, upper, locale);
        }
        String rest = str.substring(1);
        return __toFirstCharCase(str, upper, locale) + (force ? __toCase(rest, false, locale) : rest);
    }

    ////

    public static String upper(String str) {
        return toUpperCase(str);
    }

    // upper(locale)
    public static String upper(String str, Locale locale) {
        return toUpperCase(str, locale);
    }

    // lower
    public static String lower(String str) {
        return toLowerCase(str);
    }

    // lower(locale)
    public static String lower(String str, Locale locale) {
        return toLowerCase(str, locale);
    }

    ////

    public static String toUpperCase(String str) {
        return toCase(str, true, null);
    }

    // toUpperCse(locale)
    public static String toUpperCase(String str, Locale locale) {
        return toCase(str, true, locale);
    }

    // toLowerCse
    public static String toLowerCase(String str) {
        return toCase(str, false, null);
    }

    // toLowerCase(locale)
    public static String toLowerCase(String str, Locale locale) {
        return toCase(str, false, null);
    }

    // toCase
    public static String toCase(String str, boolean upper) {
        return toCase(str, upper, null);
    }

    // toCase(locale)
    public static String toCase(String str, boolean upper, Locale locale) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return __toCase(str, upper, locale);
    }

    //

    // toFirstCharCase
    // internal
    private static String __toFirstCharCase(String str, boolean upper, Locale locale) {
        return __toCase(str.substring(0, 1), upper, locale);
    }

    // toCase(String)
    // internal
    private static String __toCase(String str, boolean upper, Locale locale) {
        return upper ? str.toUpperCase() : str.toLowerCase();
    }

    ////

    /**
     * Convert a string to Camel Case (default separator is "_")
     * toCamelCase("property_name") = "PropertyName"
     * toCamelCase("property_first_name") = "PropertyFirstName"
     * 
     * @param str
     * @return
     */
    public static String toCamelCase(String str) {
        return toCamelCase(str, null, DEFAULT_CAMEL_CASE_CAPITALIZE);
    }

    /**
     * Convert a string to Camel Case toCamelCase("property-name", "-") =
     * "PropertyName" toCamelCase("property-first-name", "-",) = "PropertyFirstName"
     * 
     * @param str
     * @param separator
     * @return
     */
    public static String toCamelCase(String str, String separator) {
        return toCamelCase(str, separator, DEFAULT_CAMEL_CASE_CAPITALIZE);
    }

    /**
     * Convert a string to Camel Case toCamelCase("property_name", false) =
     * "propertyName" toCamelCase("property_name", true) = "PropertyName"
     * 
     * @param str
     * @param capitalize
     * @return
     */
    public static String toCamelCase(String str, boolean capitalize) {
        return toCamelCase(str, null, capitalize);
    }

    /**
     * Convert a string to Camel Case toCamelCase("property-name", "-", false) =
     * "propertyName" toCamelCase("property-first-name", "-", false) =
     * "propertyFirstName" toCamelCase("property-first-name", "-", true) =
     * "PropertyFirstName"
     * 
     * @param str
     * @param separator
     * @param capitalize
     * @return
     */
    public static String toCamelCase(String str, String separator, boolean capitalize) {
        return toTypeCase(str, (capitalize ? "Camel" : "camel"), separator, null);
    }

    ////

    /**
     * Convert 'Camel Case' string to 'Snake Case'
     * 
     * @param str
     * @return
     */
    public static String toSnakeCase(String str) {
        return toSnakeCase(str, null, false, false);
    }

    /**
     * Convert 'Camel Case' string to 'Snake Case'
     * 
     * @param str
     * @param separator
     * @return
     */
    public static String toSnakeCase(String str, String separator) {
        return toSnakeCase(str, separator, false, false);
    }

    /**
     * Convert 'Camel Case' string to 'Snake Case'
     * 
     * @param str
     * @param upper
     * @return
     */
    public static String toSnakeCase(String str, boolean upper) {
        return toSnakeCase(str, null, upper, false);
    }

    /**
     * Convert 'Camel Case' string to 'Snake Case'
     * 
     * @param str
     * @param separator
     * @param upper
     * @return
     */
    public static String toSnakeCase(String str, String separator, boolean upper) {
        return toSnakeCase(str, separator, upper, false);
    }

    /**
     * Convert 'Camel Case' string to 'Snake Case'
     * 
     * @param str
     * @param separator
     * @param upper
     * @param trim
     * @return
     */
    public static String toSnakeCase(String str, String separator, boolean upper, boolean trim) {
        if (isEmpty(str)) {
            return str;
        }
        if (trim) {
            str = trim(str);
            if (isEmpty(str)) {
                return str;
            }
        }
        // TODO: We use separator as connector!
        // Add 'connector' parameter
        String separators = null;
        String connector = separator;

        // TODO: Maybe remove it because it check in toTypeCase
        if (connector == null) {
            connector = DEFAULT_SNAKE_CASE_SEPARATOR;
        }

        return toTypeCase(str, (upper ? "SNAKE" : "snake"), separators, connector);
    }

    ////

    /**
     * Convert 'Camel Case' string to 'Kebab Case'
     * 
     * @param str
     * @return
     */
    public static String toKebabCase(String str) {
        return toKebabCase(str, null, false, false);
    }

    /**
     * Convert 'Camel Case' string to 'Kebab Case'
     * 
     * @param str
     * @param separator
     * @return
     */
    public static String toKebabCase(String str, String separator) {
        return toKebabCase(str, separator, false, false);
    }

    /**
     * Convert 'Camel Case' string to 'Kebab Case'
     * 
     * @param str
     * @param upper
     * @return
     */
    public static String toKebabCase(String str, boolean upper) {
        return toKebabCase(str, null, upper, false);
    }

    /**
     * Convert 'Camel Case' string to 'Kebab Case'
     * 
     * @param str
     * @param separator
     * @param upper
     * @return
     */
    public static String toKebabCase(String str, String separator, boolean upper) {
        return toKebabCase(str, separator, upper, false);
    }

    /**
     * Convert 'Camel Case' string to 'Kebab Case'
     * 
     * @param str
     * @param separator
     * @param upper
     * @param trim
     * @return
     */
    public static String toKebabCase(String str, String separator, boolean upper, boolean trim) {
        if (isEmpty(str)) {
            return str;
        }
        if (trim) {
            str = trim(str);
            if (isEmpty(str)) {
                return str;
            }
        }
        // TODO: We use separator as connector!
        // Add 'connector' parameter
        String separators = null;
        String connector = separator;

        // TODO: Maybe remove it because it check in toTypeCase
        if (connector == null) {
            connector = DEFAULT_KEBAB_CASE_SEPARATOR;
        }

        return toTypeCase(str, (upper ? "KEBAB" : "kebab"), separators, connector);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    // Normalize Case Operation by Token position
    private static int _normalizeCaseOpByToken(int caseOp, boolean first) {
        int _caseOp = caseOp;
        if (caseOp == CO_camelCase) {
            // camelCase: [0] -> 'lowercase', [1..n] -> 'PascalCase'
            _caseOp = (first ? CO_lowercase : CO_PascalCase);
        }
        return _caseOp;
    }

    // Normalize Case Operation by Char position
    private static int _normalizeCaseOpByChar(int caseOp, boolean first) {
        // TODO
        return caseOp;
    }

    // https://www.techiedelight.com/append-char-end-string-cpp/
    //
    // caseOp =  1: 'myname': lowercase    
    // caseOp =  2: 'MYNAME': UPPERCASE
    // caseOp =  3: 'myName': camelCase
    // caseOp =  4: 'MyName': PascalCase

    private static void _flushOp(List<String> result, StringBuilder buf, int caseOp) {
        int _caseOp = caseOp;
        char ch = 0;

        // StringBuilder str = new StringBuilder();
        // char[] bufArr = buf.toString().toCharArray();

        for (int i = 0; i < buf.length(); i++) {
            _caseOp = caseOp;
            ch = buf.charAt(i);

            if (caseOp == CO_PascalCase) {
                if (i == 0) {
                    _caseOp = CO_UPPERCASE; // upper char
                }
                // else {
                // _caseOp = CO_lowercase; // lower char
                // }
            }

            // WARNING: We don't use 'caseOp' in this code because it is lower case always
            if (_caseOp == CO_lowercase) {
                ch = Character.toLowerCase(ch);
                buf.setCharAt(i, ch);
            } else if (_caseOp == CO_UPPERCASE) {
                ch = Character.toUpperCase(ch);
                buf.setCharAt(i, ch);
            }

            // str.append(1, ch);
            // str += ch; // Add char to string
        }

        result.add(buf.toString()); // Add buffer to result
        buf.setLength(0); // Clear buffer

    }

    // splitOp: separators and A (Upper Char)
    // caseOp =  1: 'myname': lowercase
    // caseOp =  2: 'MYNAME': UPPERCASE
    // caseOp =  3: 'myName': camelCase
    // caseOp =  4: 'MyName': PascalCase
    private static List<String> _splitOp(String str, String separators, int caseOp) {
        if (str == null) {
            return null;
        }

        List<String> result = new ArrayList<String>();
        if (isEmpty(str) || isEmpty(separators)) {
            result.add(str);
            return result;
        }

        char[] strArr = str.toCharArray();
        char[] delArr = separators.toCharArray();

        int strLen = str.length();
        int delLen = separators.length();

        char ch = 0;
        char del = 0;
        boolean find = false;
        StringBuilder buf = new StringBuilder(); // c++: vector<char> ?
        int _caseOp = caseOp;
        boolean first = true;

        for (int i = 0; i < strLen; i++) {

            ch = strArr[i]; // c++: str[i] ?
            find = false;

            // Find a separator
            for (int j = 0; j < delLen; j++) {
                del = delArr[j];
                if (del == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    if (Character.isUpperCase(ch)) {
                        find = true;
                        break;
                    }
                } else if (ch == delArr[j]) {
                    find = true;
                    break;
                }
            }

            if (find) {

                // flush
                if (buf.length() > 0) {
                    _caseOp = _normalizeCaseOpByToken(caseOp, first);
                    _flushOp(result, buf, _caseOp);
                    first = false;
                }

                // Add separator: optinal
                boolean include = false;
                if (include || del == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    buf.append(ch);
                }

            } else {

                // Add char
                buf.append(ch);
            }

        }

        // flush
        if (buf.length() > 0) {
            _caseOp = _normalizeCaseOpByToken(caseOp, first);
            _flushOp(result, buf, _caseOp);
        }

        return result;

    }

    // caseOp =  1: 'myname': lowercase
    // caseOp =  2: 'MYNAME': UPPERCASE
    // caseOp =  3: 'myName': camelCase
    // caseOp =  4: 'MyName': PascalCase
    private static String _toCaseOp(String str, String separators, String connector, int caseOp) {

        if (isEmpty(str)) {
            return str;
        }

        StringBuilder result = new StringBuilder();
        List<String> tokens = _splitOp(str, separators, caseOp);

        boolean hasConnector = !isEmpty(connector);

        for (int i = 0; i < tokens.size(); i++) {
            if (i > 0 && hasConnector) {
                result.append(connector);
            }
            result.append(tokens.get(i));
        }

        return result.toString();
    }

    // -  1. lowercase, [lower]~
    // -  2. UPPERCASE, [upper]~
    // -  3. camelCase, [camel]~
    // -  4. CamelCase, PascalCase, [pascal]~
    // -  5. snake_case, [snake]
    // -  6. SNAKE_CASE, MACRO_CASE, CONSTANT_CASE, [SNAKE], [macro]~
    // -  7. Snake_Case, [Snake]
    // -  8. kebab-case, dash-case, train-case, lisp-case, [kebab]
    // -  9. KEBAB-CASE, DASH-CASE, TRAIN-CASE, COBOL-CASE, [KEBAB], [cobol]~
    // - 10. Kebab-Case, Dash-Case, Train-Case, HTTP-Header-Case, [Kebab], [http]~

    // caseOp =  1: 'myname': lowercase
    // caseOp =  2: 'MYNAME': UPPERCASE
    // caseOp =  3: 'myName': camelCase
    // caseOp =  4: 'MyName': PascalCase
    private static String _toTypeCase(String str, String type, String separators, String connector) {

        if (str == null || str.isEmpty()) {
            return str;
        }

        int code = toCaseCode(type);
        if (code <= 0) {
            // Invalid case code
            return str;
        }

        // SIMPLE CASE
        if (code == CT_lowercase) {
            return toCase(str, false); // lower case
        } else if (code == CT_UPPERCASE) {
            return toCase(str, true); // UPPER case
        }

        // COMPLEX CASE
        String _separators = (isEmpty(separators) ? DEFAULT_CASE_SEPARATORS_A : separators);

        if (code == CT_camelCase) {
            return _toCaseOp(str, _separators, connector, CO_camelCase); // lower first char
            
        } else if (code == CT_PascalCase) {
            return _toCaseOp(str, _separators, connector, CO_PascalCase); // upper first char

        } else if (code == CT_kebab_case) {
            return _toCaseOp(str, _separators, (isEmpty(connector) ? KEBAB_CONNECTOR : connector), CO_lowercase);
            
        } else if (code == CT_KEBAB_CASE) {
            return _toCaseOp(str, _separators, (isEmpty(connector) ? KEBAB_CONNECTOR : connector), CO_UPPERCASE);
            
        } else if (code == CT_Kebab_Case) {
            return _toCaseOp(str, _separators, (isEmpty(connector) ? KEBAB_CONNECTOR : connector), CO_PascalCase);
            
        } else if (code == CT_snake_case) {
            return _toCaseOp(str, _separators, (isEmpty(connector) ? SNAKE_CONNECTOR : connector), CO_lowercase);
            
        } else if (code == CT_SNAKE_CASE) {
            return _toCaseOp(str, _separators, (isEmpty(connector) ? SNAKE_CONNECTOR : connector), CO_UPPERCASE);
            
        } else if (code  == CT_Snake_Case) {
            return _toCaseOp(str, _separators, (isEmpty(connector) ? SNAKE_CONNECTOR : connector), CO_PascalCase);
        }


        // UNKNOWN CASE: use 'separators', 'connector'
        // _toCaseOp(str, separators, connector, CO_NONE);

        return str;

    }

    // -  1. lowercase, [lower]~
    // -  2. UPPERCASE, [upper]~
    // -  3. camelCase, [camel]~
    // -  4. CamelCase, PascalCase, [pascal]~
    // -  5. snake_case, [snake]
    // -  6. SNAKE_CASE, MACRO_CASE, CONSTANT_CASE, [SNAKE], [macro]~
    // -  7. Snake_Case, [Snake]
    // -  8. kebab-case, dash-case, train-case, lisp-case, [kebab]
    // -  9. KEBAB-CASE, DASH-CASE, TRAIN-CASE, COBOL-CASE, [KEBAB], [cobol]~
    // - 10. Kebab-Case, Dash-Case, Train-Case, HTTP-Header-Case, [Kebab], [http]~

    // Return case code by case type
    public static int toCaseCode(String type) {

        if (type == null) {
            return 0;
        }

        if (type.equals("lower")) {
            return CT_lowercase;         // lowercase

        } else if (type.equals("upper")) {
            return CT_UPPERCASE;         // UPPERCASE

        } else if (type.equals("camel")) {
            return CT_camelCase;         // camelCase

        } else if (type.equals("Camel") 
                || type.equals("Pascal") 
                || type.equals("pascal")) {
            return CT_PascalCase;        // PascalCase

        } else if (type.equals("snake")) {
            return CT_snake_case;        // snake_case

        } else if (type.equals("SNAKE") 
                || type.equals("MACRO")
                || type.equals("macro")) {
            return CT_SNAKE_CASE;        // SNAKE_CASE

        } else if (type.equals("Snake")) {
            return CT_Snake_Case;        // Snake_Case

        } else if (type.equals("kebab") 
                || type.equals("dash")
                || type.equals("train") 
                || type.equals("lisp")) {
            return CT_kebab_case;        // kebab-case

        } else if (type.equals("KEBAB") 
                || type.equals("DASH")
                || type.equals("TRAIN") 
                || type.equals("COBOL")
                || type.equals("cobol")) {
            return CT_KEBAB_CASE;        // KEBAB-CASE

        } else if (type.equals("Kebab")
                || type.equals("Dash")
                || type.equals("Train")) {
            return CT_Kebab_Case;       // Kebab_Case
        }

        return 0;

    }

    public static String toTypeCase(String str, String type) {
        return _toTypeCase(str, type, DEFAULT_CASE_SEPARATORS_A, null);
    }

    public static String toTypeCase(String str, String type, String separators, String connector) {
        return _toTypeCase(str, type, separators, connector);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////

    public static String reverse(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char[] array = str.toCharArray();
        ArrayLib.reverse(array);
        return toString(array);
    }

    //// 4.1

    /**
     * Tests if this string starts with the specified prefix.
     * 
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWith(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        return str.startsWith(prefix);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endsWith(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        return str.endsWith(suffix);
    }

    //

    /**
     * Tests if this string starts with the specified prefix.
     * 
     * @param str
     * @param prefix
     * @return
     */
    public static boolean hasPrefix(String str, String prefix) {
        return startsWith(str, prefix);
    }

    /**
     * Tests if this string ends with the specified suffix.
     * 
     * @param str
     * @param suffix
     * @return
     */
    public static boolean hasSuffix(String str, String suffix) {
        return endsWith(str, suffix);
    }

    // TODO: What about: startsWithIgnoreCase(str, prefix) -> startsWith(str,
    // prefix, true) {

    /**
     * Return true if <code>str</code> starts with <code>prefix</code> (ignore case)
     * 
     * @param str
     * @param prefix
     * @return
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        return str.toUpperCase().startsWith(prefix.toUpperCase());
    }

    /**
     * Return true if <code>str</code> ends with <code>suffix</code> (ignore case)
     * 
     * @param str
     * @param suffix
     * @return
     */
    public static boolean endsWithIgnoreCase(String str, String suffix) {
        if (str == null || suffix == null) {
            return false;
        }
        return str.toUpperCase().endsWith(suffix.toUpperCase());
    }

    //

    /**
     * Return true if <code>str</code> starts with <code>prefix</code> (ignore case)
     * 
     * @param str
     * @param prefix
     * @return
     */
    public static boolean hasPrefixWithIgnoreCase(String str, String prefix) {
        return startsWithIgnoreCase(str, prefix);
    }

    /**
     * Return true if <code>str</code> ends with <code>suffix</code> (ignore case)
     * 
     * @param str
     * @param suffix
     * @return
     */
    public static boolean hasSuffixIgnoreCase(String str, String suffix) {
        return endsWithIgnoreCase(str, suffix);
    }

    //// 4.2

    /**
     * Remove prefix from string
     * 
     * @param str
     * @param prefix
     * @return
     */
    public static String removePrefix(String str, String prefix) {
        if (str == null || prefix == null || str.isEmpty() || prefix.isEmpty() || (!str.startsWith(prefix))) {
            return str;
        }
        if (str.length() == prefix.length()) {
            return EMPTY_STRING; // remove all - empty string
        }
        return str.substring(prefix.length());
    }

    /**
     * Remove prefixes from string
     * 
     * @param str
     * @param prefixes
     * @return
     */
    public static String removePrefixes(String str, String[] prefixes) {
        if (str == null || prefixes == null || prefixes.length == 0) {
            return str;
        }
        for (String prefix : prefixes) {
            if (prefix == null || prefix.isEmpty()) {
                continue;
            }
            // Remove first found prefix
            if (startsWith(str, prefix)) { // TODO: double check in removePrefix. We cannot remove this check!
                return removePrefix(str, prefix);
            }
        }
        return str;
    }

    /**
     * Remove suffix from string
     * 
     * @param str
     * @param suffix
     * @return
     */
    public static String removeSuffix(String str, String suffix) {
        if (str == null || suffix == null || str.isEmpty() || suffix.isEmpty() || (!str.endsWith(suffix))) {
            return str;
        }
        if (str.length() == suffix.length()) {
            return EMPTY_STRING; // remove all - empty string
        }
        return str.substring(0, str.length() - suffix.length());
    }

    /**
     * Remove suffixes from string
     * 
     * @param str
     * @param suffixes
     * @return
     */
    public static String removeSuffixes(String str, String[] suffixes) {
        if (str == null || suffixes == null || suffixes.length == 0) {
            return str;
        }
        for (String suffix : suffixes) {
            if (suffix == null || suffix.isEmpty()) {
                continue;
            }
            // Remove first found suffix
            if (endsWith(str, suffix)) { // TODO: double check in removeSuffix. We cannot remove this check!
                return removeSuffix(str, suffix);
            }
        }
        return str;
    }

    //// 4.3

    public static boolean isQuoted(String str) {
        if (isEmpty(str)) {
            return false;
        }
        // default quote: '' or ""
        return (isQuoted(str, "'", "'") || isQuoted(str, "\"", "\""));
    }

    /**
     * Test if this string is in quotes
     * 
     * @param str
     * @param startQuote
     * @param endQuote
     * @return
     */
    public static boolean isQuoted(String str, String startQuote, String endQuote) {
        if (isEmpty(str) || isEmpty(startQuote) || isEmpty(endQuote)) {
            return false;
        }
        return startsWith(str, startQuote) && endsWith(str, endQuote);
    }

    public static boolean needQuote(String str) {
        // TODO: What about 'abc" or "abc' or 'abc'def'
        // default quote: '' or ""
        return !isQuoted(str);
    }

    public static boolean needQuote(String str, String startQuote, String endQuote) {
        // TODO: What about 'abc" or "abc' or 'abc'def'
        // default quote: '' or ""
        return !isQuoted(str, startQuote, endQuote);
    }

    ////

    // quote by default: "
    public static String quote(String str) {
        return quote(str, "\"", "\"");
    }

    // quote
    public static String quote(String str, String startQuote, String endQuote) {
        if (str == null) {
            return null; // We can't quote null. But we can quote empty string
        }
        return startQuote + str + endQuote;
    }

    // unquote by default: ', "
    public static String unquote(String str) {
        if (isEmpty(str)) {
            return str;
        }
        if (!isQuoted(str, "'", "'") && !isQuoted(str, "\"", "\"")) {
            return str;
        }
        // Java: start, end (exclude)
        return str.substring(1, str.length() - 1);
    }

    // unquote
    public static String unquote(String str, String startQuote, String endQuote) {
        if (isEmpty(str)) {
            return str;
        }
        if (!isQuoted(str, startQuote, endQuote)) {
            return str;
        }
        // Java: start, end (exclude)
        return str.substring(startQuote.length(), str.length() - endQuote.length());
    }

    //// 4.4

    // isColumnSeparator

    /**
     * Return true if 'ch' is column separator by default
     * 
     * @param ch
     * @return
     */
    public static boolean isColumnSeparator(char ch) {
        return (ch == '\r' || ch == '\n' || ch == '\t');
    }

    // isColumnText

    public static boolean isColumnText(char[] array) {
        if (array == null || array.length == 0) {
            return false; // by default inline (isColumnText = false)
        }
        return isColumnText(array, array.length);
    }

    public static boolean isColumnText(char[] array, int len) {
        if (array == null || array.length == 0 || len < 1) {
            return false; // by default inline (isColumnText = false)
        }
        char ch;
        for (int i = 0; i < len; i++) {
            ch = array[i];
            if (isColumnSeparator(ch)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isColumnText(String str) {
        if (isEmpty(str)) {
            return false; // by default inline (isColumnText = false)
        }
        return isColumnText(str.toCharArray(), str.length());
    }

    // isLineText

    public static boolean isLineText(char[] array) {
        if (array == null || array.length == 0) {
            return true; // by default inline
        }
        return isLineText(array, array.length);
    }

    public static boolean isLineText(char[] array, int len) {
        if (array == null || array.length == 0 || len < 1) {
            return true; // by default inline
        }
        return !isColumnText(array, len);
    }

    public static boolean isLineText(String str) {
        if (isEmpty(str)) {
            return true; // by default: inline
        }
        return isLineText(str.toCharArray(), str.length());
    }

    //// 5.1

    // TODO: countUniqueChar(str), countUniqueWord(str), countUniqueLine(str)

    public static int countChar(String str, char ch) {
        if (isEmpty(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                count++;
            }
        }
        return count;
    }

    public static int countString(String str, String findStr) {
        if (isEmpty(str) || isEmpty(findStr)) {
            return 0;
        }
        int length = str.length();
        int findLength = findStr.length();
        if (findLength > length) {
            return 0;
        }

        char[] chars = str.toCharArray();
        char[] findChars = findStr.toCharArray();

        if (findLength == length) {
            return _equalsCharArray(chars, findChars) ? 1 : 0;
        }

        int count = 0;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (i + findLength > length) {
                break;
            }

            if (_equalsCharArray(chars, findChars, i)) {
                count++;
                i += findLength;
            } else {
                i++;
            }
        }
        return count;
    }

    ////

    private static boolean _equalsCharArray(char[] c1, char[] c2) {
        if (c1 == null || c2 == null) {
            return false;
        }

        if (c1.length != c2.length) {
            return false;
        }
        int lenght = c1.length;
        for (int i = 0; i < lenght; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean _equalsCharArray(char[] c1, char[] c2, int start1) {
        if (c1 == null || c2 == null) {
            return false;
        }
        int length = start1 + c2.length;
        if (length > c1.length) {
            return false;
        }

        // if (c1.length != c2.length) {
        // return false;
        // }
        // int lenght = c1.length;

        for (int i = start1; i < length; i++) {
            if (c1[i] != c2[i - start1]) {
                return false;
            }
        }
        return true;
    }

    ////

    public static int countWord(String str) {
        return countWord(str, null);
    }

    public static int countWord(String str, String seperators) {
        str = normalize(str);
        if (str == null) {
            return 0;
        }
        if (seperators == null) {
            seperators = DEFAULT_WORD_SEPARATORS;
        }
        String[] words = split(str, seperators);
        return words == null ? 0 : words.length;
    }

    public static int countLine(String str) {
        str = normalize(str);
        if (str == null) {
            return 0;
        }
        String[] lines = splitLines(str);
        return lines == null ? 0 : lines.length;
    }

    //// 6.1

    // Non RegExp
    public static String replaceAll(String str, String s1, String s2) {
        if (str == null) {
            // null: return
            return null;
        }
        if (s1 == null || s2 == null) {
            // null: return
            return str;
        }
        if (s1 == s2) {
            // ==: return
            return str;
        }
        if (s1.equals(s2)) {
            // equals: return
            return str;
        }

        int index = str.indexOf(s1);
        if (index == INDEX_NOT_FOUND) {
            // not found - return original string
            return str;
        }
        int patLen = s1.length();
        int pos = 0;
        StringBuilder buf = new StringBuilder();
        while (index >= 0) {
            buf.append(str.substring(pos, index));
            buf.append(s2);
            pos = index + patLen;
            index = str.indexOf(s1, pos);
        }
        buf.append(str.substring(pos));
        return buf.toString();
    }

    // See ArrayLib.replaceAll
    // Non RegExp
    public static String replaceAll(String str, String[] oldValues, String[] newValues) {
        if (oldValues == null) {
            // TODO: Exception
            return str;

        }
        if (newValues == null) {
            // TODO: Exception
            return str;
        }
        int size = Math.min(oldValues.length, newValues.length);
        if (size == 0) {
            return str;
        }
        String oldValue = null;
        String newValue = null;
        String result = str;
        for (int i = 0; i < size; i++) {
            oldValue = oldValues[i];
            newValue = newValues[i];
            result = replaceAll(str, oldValue, newValue); // TODO: maybe optimization
        }
        return result;
    }

    //// 7.1

    public static String[] split(String str, String seperators) {
        return split(str, seperators, false);
    }

    public static String[] split(String str, String seperators, boolean include) {
        if (isEmpty(str)) {
            return EMPTY_STRING_ARRAY;
        }
        if (seperators == null) {
            return new String[] { str };
        }
        StringTokenizer tokens = new StringTokenizer(str, seperators, include);
        String[] result = new String[tokens.countTokens()];
        int i = 0;
        while (tokens.hasMoreTokens()) {
            result[i++] = tokens.nextToken();
        }
        return result;
    }

    public static String[] splitWords(String str) {
        return splitWords(str, null);
    }

    public static String[] splitWords(String str, String seperators) {
        str = normalize(str);
        if (str == null) {
            return EMPTY_STRING_ARRAY;
        }
        if (seperators == null) {
            seperators = DEFAULT_WORD_SEPARATORS;
        }
        return split(str, seperators);
    }

    public static String[] splitLines(String str) {
        if (str == null || str.isEmpty()) {
            return EMPTY_STRING_ARRAY;
        }
        return str.split("[\\r]?\\n");
    }

    //// 8.1

    public static String toString(char[] array) {
        return array == null ? null : new String(array);
    }

    public static String[] toWordArray(String str) {
        return splitWords(str);
    }

    public static String[] toWordArray(String str, String separators) {
        return splitWords(str, separators);
    }

    public static String[] toLineArray(String str) {
        return splitLines(str);
    }

    //// 8.2

    public static List<String> toWordList(String str) {
        return toList(splitWords(str));
    }

    public static List<String> toWordList(String str, String separators) {
        return toList(splitWords(str, separators));
    }

    public static List<String> toLineList(String str) {
        return toList(splitLines(str));
    }

    public static List<String> toList(String[] array) {
        return CollectionLib.toList(array);
    }

    //// 9.1

    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public static boolean isDigit(Character ch) {
        return ch == null ? false : isDigit(ch);
    }

    //

    public static boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    public static boolean isLetter(Character ch) {
        return ch == null ? false : isLetter(ch);
    }

    //

    public static boolean isAlpha(char ch) {
        return Character.isAlphabetic(ch);
    }

    public static boolean isAlpha(Character ch) {
        return ch == null ? false : isAlpha(ch);
    }

    //

    public static boolean isWhitespace(char ch) {
        return Character.isWhitespace(ch);
    }

    public static boolean isWhitespace(Character ch) {
        return ch == null ? false : isWhitespace(ch);
    }

    //

    public static boolean isDot(char ch) {
        return ch == '.'; // '.', 46
    }

    public static boolean isDot(Character ch) {
        return ch == null ? false : isDot(ch);
    }

    //

    public static boolean isUnderline(char ch) {
        return ch == '_'; // '_', 95
    }

    public static boolean isUnderline(Character ch) {
        return ch == null ? false : isUnderline(ch);
    }

    ////

    public static boolean isIdentifierStart(char ch) {
        return isUnderline(ch) || isLetter(ch); // $ ???
    }

    public static boolean isIdentifierPart(char ch) {
        return isUnderline(ch) || isLetter(ch) || isDigit(ch);
    }

    public static boolean isIdentifier(String str) {
        if (isEmpty(str)) {
            return false;
        }
        char[] array = str.toCharArray();
        if (array.length == 0) {
            return false; // strange code
        }
        char first = array[0];
        if (array.length == 1) {
            return isLetter(first); // letter only: 'a', 'b', 'c'. but '_' is not correct for one char
        }

        if (!isIdentifierStart(first)) {
            return false; // identifier start only
        }
        boolean isUnderline = isUnderline(first);
        for (int i = 1; i < array.length; i++) {
            if (isUnderline) {
                if (!isIdentifierStart(array[i])) {
                    return false;
                }
                if (!isUnderline(array[i])) {
                    isUnderline = false;
                }
            } else {
                if (!isIdentifierPart(array[i])) {
                    return false; // identifier part only
                }
            }
        }
        if (isUnderline) {
            return false; // all char is '_'
        }
        return true;
    }

    //// 9.2

    public static boolean isUpperCase(char ch) {
        return Character.isUpperCase(ch);
    }

    public static boolean isUpperCase(Character ch) {
        return ch == null ? false : isUpperCase(ch);
    }

    //

    public static boolean isLowerCase(char ch) {
        return Character.isLowerCase(ch);
    }

    public static boolean _isLowerCase(Character ch) {
        return ch == null ? false : isLowerCase(ch);
    }

    //// 10.1 ISO-LATIN-1 Block

    // start - include
    // end - include
    private static boolean isRange(char ch, int start, int end) {
        return ch >= start && ch <= end;
    }

    // ISO-LATIN-1
    public static boolean isISODigit(char ch) {
        return isRange(ch, '0', '9'); // '0'..'9', 48..57
    }

    // ISO-LATIN-1
    public static boolean isISODigit(Character ch) {
        return ch == null ? false : isISODigit(ch);
    }

    // ISO-LATIN-1
    public static boolean isISOUpperLetter(char ch) {
        return isRange(ch, 'A', 'Z'); // 'A'..'Z', 65..90
    }

    // ISO-LATIN-1
    public static boolean isISOUpperLetter(Character ch) {
        return ch == null ? false : isISOUpperLetter(ch);
    }

    // ISO-LATIN-1
    public static boolean isISOLowerLetter(char ch) {
        return isRange(ch, 'a', 'z'); // 'a'..'z', 97..122
    }

    // ISO-LATIN-1
    public static boolean isISOLowerLetter(Character ch) {
        return ch == null ? false : isISOLowerLetter(ch);
    }

    // ISO-LATIN-1
    public static boolean isISOLetter(char ch) {
        return isISOUpperLetter(ch) || isISOLowerLetter(ch);
    }

    // ISO-LATIN-1
    public static boolean isISOLetter(Character ch) {
        return ch == null ? false : (isISOUpperLetter(ch) || isISOLowerLetter(ch));
    }

    // ISO-LATIN-1
    public static boolean isISOWhitespace(char ch) {
        return ch == ' '; // ' ', 32
    }

    // ISO-LATIN-1
    public static boolean isISOWhitespace(Character ch) {
        return ch == null ? false : isISOWhitespace(ch);
    }

    // ISO-LATIN-1
    public static boolean isISODot(char ch) {
        return ch == '.'; // '.', 46
    }

    // ISO-LATIN-1
    public static boolean isISODot(Character ch) {
        return ch == null ? false : isISODot(ch);
    }

    // ISO-LATIN-1
    public static boolean isISOUnderline(char ch) {
        return ch == '_'; // '_', 95
    }

    // ISO-LATIN-1
    public static boolean isISOUnderline(Character ch) {
        return ch == null ? false : isISOUnderline(ch);
    }

    ////

    // ISO-LATIN-1
    public static boolean isISOIdentifierStart(char ch) {
        return isISOUnderline(ch) || isISOLetter(ch); // $ ???
    }

    // ISO-LATIN-1
    public static boolean isISOIdentifierPart(char ch) {
        return isISOUnderline(ch) || isISOLetter(ch) || isISODigit(ch);
    }

    // ISO-LATIN-1
    public static boolean isISOIdentifier(String str) {
        if (isEmpty(str)) {
            return false;
        }
        char[] array = str.toCharArray();
        if (array.length == 0) {
            return false; // strange code
        }
        char first = array[0];
        if (array.length == 1) {
            return isISOLetter(first); // letter only: 'a', 'b', 'c'. but '_' is not correct for one char
        }

        if (!isISOIdentifierStart(first)) {
            return false; // identifier start only
        }
        boolean isUnderline = isISOUnderline(first);
        for (int i = 1; i < array.length; i++) {
            if (isUnderline) {
                if (!isISOIdentifierStart(array[i])) {
                    return false;
                }
                if (!isISOUnderline(array[i])) {
                    isUnderline = false;
                }
            } else {
                if (!isISOIdentifierPart(array[i])) {
                    return false; // identifier part only
                }
            }
        }
        if (isUnderline) {
            return false; // all char is '_'
        }
        return true;
    }

    //// 11.1

    /**
     * Return max length of string element in array
     * 
     * @param array
     * @return
     */
    public static int maxLen(String[] array) {
        if (array == null) {
            return 0;
        }
        int result = 0;
        String str;
        for (int i = 0; i < array.length; i++) {
            str = array[i];
            if (str.length() > result) {
                result = str.length();
            }
        }
        return result;
    }
    
}
