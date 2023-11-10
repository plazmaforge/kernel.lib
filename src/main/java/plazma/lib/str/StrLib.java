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

package plazma.lib.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import plazma.lib.array.ArrayLib;

public class StrLib {
    
    // Functions
    
    /////////////////////////////////////////////////////////////////////////////////
    //
    // 1.1 empty, blank, size
    //
    // - isEmpty(String str)                                               - check empty
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
    // - normalizeSafe(String str)                                         - trim, '' - > ''
    // - normalizeBlank(String str, boolean trimAll, boolean trimBlank)
    // - normalizeQuoted(String str)                                       - trim in quoted value: "\' text    \'" - > "\'text\'" 
    //
    // - emptyIfNull(String str)                                           - null -> ''
    //
    // - nullIfEmpty(String str)                                           - '' -> null
    //
    // - defaultIfNull(String str, String defaultStr)                      - str == null ? defaultStr : str
    // - defaultIfEmpty(String str, String defaultStr)                     - isEmpty(str) ? defaultStr : str
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
    // - contains(String str, char ch)
    // - contains(String str, String substr)
    //
    // - find(String str, char ch)
    // - find(String str, char ch, int pos)
    // - find(String str, String substr)
    // - find(String str, String substr, int pos)
    //
    // - findFirst(String str, char ch)
    // - findFirst(String str, char ch, int pos)
    // - findFirst(String str, String substr)
    // - findFirst(String str, String substr, int pos)
    //
    // - findLast(String str, char ch)
    // - findLast(String str, char ch, int pos)
    // - findLast(String str, String substr)
    // - findLast(String str, String substr, int pos)
    //
    // - findFirstOf(String str, char ch)
    // - findFirstOf(String str, char ch, int pos)
    // - findFirstOf(String str, String terms)
    // - findFirstOf(String str, String terms, int pos)
    //
    // - findLastOf(String str, char ch)
    // - findLastOf(String str, char ch, int pos)
    // - findLastOf(String str, String terms)
    // - findLastOf(String str, String terms, int pos)
    //
    // - findFirstNotOf(String str, char ch)
    // - findFirstNotOf(String str, char ch, int pos)
    // - findFirstNotOf(String str, String terms)
    // - findFirstNotOf(String str, String terms, int pos)
    //
    // - findLastNotOf(String str, char ch)
    // - findLastNotOf(String str, char ch, int pos)
    // - findLastNotOf(String str, String terms)
    // - findLastNotOf(String str, String terms, int pos)

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
    // - trunc(String str, int len, boolean ellipsis)
    //
    // - left(String str, int len)
    // - right(String str, int len)
    
    /////////////////////////////////////////////////////////////////////////////////
    // 3.1
    //
    // - capitalize(String str)                                            - capitalize("abc") = "Abc"
    // - capitalize(String str, boolean forceRest)                         - capitalize("abC") = "Abc"
    // - capitalize(String str, Locale Locale locale)
    // - capitalize(String str, boolean forceRest, Locale locale)
    //
    // - decapitalize(String str)                                          - decapitalize("Abc") = "abc"
    // - decapitalize(String str, boolean forceRest)                       - decapitalize("AbC") = "abc"
    // - decapitalize(String str, Locale locale)
    // - decapitalize(String str, boolean forceRest, Locale locale)
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
    // - toCamelCase(String str, String separators)
    // - toCamelCase(String str, boolean capitalize)
    // - toCamelCase(String str, String separators, boolean capitalize)
    //
    // - toSnakeCase(String str)                                            - toSnakeCase("PropertyName") = "property_name"
    // - toSnakeCase(String str, String separators)
    // - toSnakeCase(String str, String boolean upper)
    // - toSnakeCase(String str, String separators, boolean upper)
    //
    // - toKebabCase(String str)                                            - toKebabCase("PropertyName") = "property-name"
    // - toKebabCase(String str, String separators)
    // - toKebabCase(String str, String boolean upper)
    // - toKebabCase(String str, String separators, boolean upper)
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
    // - countChars(String str, char ch)
    // - countStrings(String str, String findStr)
    // - countWords(String str)
    // - countWords(String str, String[] seperators)
    // - countLines(String str)
    
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
    
    public static final char SPACE_CHAR = ' ';
    
    public static final String TRIM_CHARS = " \t\n\r\f\u000B"; // \v -> \u000B

    public static final String ELLIPSIS = "...";

    public static final int ELLIPSIS_LEN = ELLIPSIS.length();

    public static final char DEFAULT_PAD = ' ';

    public static final String DEFAULT_SEPARATORS = " \t\n\r\f\u000B"; // \v -> \u000B

    public static final String DEFAULT_WORD_SEPARATORS = DEFAULT_SEPARATORS + ".,;'(){}[]!?+/=<>*&^%$#@`~|\\";

    public static final String DEFAULT_CASE_SEPARATOR = "_";

    public static final String DEFAULT_CASE_SEPARATORS_ = " -_";

    public static final String DEFAULT_CASE_SEPARATORS_A = " -_A";

    public static final String DEFAULT_ALTER_CASE_SEPARATOR = "-"; // XML

    public static final String DEFAULT_SNAKE_CASE_SEPARATOR = "_"; // SNAKE

    public static final String DEFAULT_KEBAB_CASE_SEPARATOR = "-"; // KEBAB

    public static final boolean DEFAULT_FORCE_CAPITALIZE = false;

    public static final boolean DEFAULT_CAMEL_CASE_CAPITALIZE = true; // 'first_name' -> 'FirstName'

    public static final int INDEX_NOT_FOUND = -1;

    ///////////////////////////////////
    // Case Operations
    ///////////////////////////////////
    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR

    public static final int CO_NONE       = 0;
    public static final int CO_LOWER      = 1;
    public static final int CO_UPPER      = 2;
    public static final int CO_LOWER_CHAR = 3;
    public static final int CO_UPPER_CHAR = 4;
    //
    public static final int CO_COUNT = 4;
    

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
    
    public static final int CT_NONE       = 0;
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
    //
    public static final int CT_COUNT      = 10;

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

    public static boolean isBlank(String str) {
        int len = length(str);
        if (len == 0) {
            return true;
        }
        for (int i = 0; i < len; i++) {
            if (!_isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    // Internal
    private static boolean _isWhitespace(char ch) {
        return ch <= ' ';        
    }

    public static int length(String str) {
        return str == null ? 0 : str.length();
    }

    public static int size(String str) {
        return length(str);
    }

    //

    public static boolean equals(String str1, String str2) {
        if (str1 == str2) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    public static boolean equalsContent(String str, CharSequence cs) {
        if (str == null && cs == null) {
            return true;
        }
        
        if (str == null || cs == null) {
            return false;
        }
        return str.contentEquals(cs);
    }

    public static boolean equalsContent(String str, char[] array) {
        if (str == null && array == null) {
            return true;
        }        
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
        str = trim(str);
        return isEmpty(str) ? null : str;
    }

    public static String normalizeSafe(String str) {
        str = trim(str);
        return isEmpty(str) ? EMPTY_STRING : str;
    }

    public static String normalizeBlank(String str, boolean trimAll, boolean trimBlank) {
        if (str == null) {
            return null;
        }
        
        // 'trimAll' flag overrides 'trimBlank' flag for each type
        // If 'trimAll' is true we will skip empty text after normalization
        // 'trimBlank' for non normalize text only

        if (!trimAll && !trimBlank) {
            return str;
        }

        if (!trimAll) {
            
            // trimBlank=true, because we have condition (!trimAll && !trimBlank) before
            // Analyze blank
            return isBlank(str) ? null : str;            
        }
        
        return normalize(str);
    }
    
    public static String normalizeQuoted(String str) {
        return normalizeQuoted(str, true);
    }

    public static String normalizeQuoted(String str, boolean forceQuote) {
        if (str == null) {
            return null;
        }

        // " text  " -> "text": Normalize in quoted value
        
        if (isQuoted(str)) {
            str = unquote(str);
        }
        str = normalize(str);
        
        if (str == null) {
            if (!forceQuote) {
                return null;                
            }
            str = "";
        }
        
        str = quote(str);
        return str;
    }

    public static String emptyIfNull(String str) {
        return str == null ? EMPTY_STRING : str;
    }

    
    public static String nullIfEmpty(String str) {
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
        return str.trim(); // ch <= ' '        
    }

    public static String trimAll(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.trim(); // ch <= ' '        
        //return trim(str, TRIM_CHARS); // " \t\n\r\f\u000B":  \v -> \u000B 
    }

    public static String trimSpace(String str) {
        return trim(str, SPACE_CHAR); // ' ' - space only
    }

    public static String trim(String str, char ch) {
        if (isEmpty(str)) {
            return str;
        }
        
        int posFirst = findFirstNotOf(str, ch);
        if (posFirst == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }

        int posLast = findLastNotOf(str, ch);        
        if (posLast == INDEX_NOT_FOUND) {
            return EMPTY_STRING; // ???
        }
        
        return str.substring(posFirst, posLast + 1);
    }

    public static String trim(String str, String terms) {
        if (isEmpty(str)) {
            return str;
        }
        
        int posFirst = findFirstNotOf(str, terms);
        if (posFirst == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }

        int posLast = findLastNotOf(str, terms);
        if (posLast == INDEX_NOT_FOUND) {
            return EMPTY_STRING; // ???
        }

        return str.substring(posFirst, posLast + 1);
    }

    // left trim
    public static String ltrim(String str) {
        if (isEmpty(str)) {
            return str;
        }
        
        // See String.trim(): ch <= ' '
        
        int len = str.length();
        int index = 0;

        while (index < len && _isWhitespace(str.charAt(index))) {
            index++;
        }        
        return index > 0 ? str.substring(index) : str;
        //return ltrim(str, SPACE_CHAR);
    }

    // left trim
    public static String ltrim(String str, char ch) {
        if (isEmpty(str)) {
            return str;
        }

        int pos = findFirstNotOf(str, ch);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }

        return str.substring(pos);
    }

    // left trim
    public static String ltrim(String str, String terms) {
        if (isEmpty(str) || isEmpty(terms)) {
            return str;
        }

        int pos = findFirstNotOf(str, terms);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }

        return str.substring(pos);
    }

    // right trim
    public static String rtrim(String str) {
        if (isEmpty(str)) {
            return str;
        }
        
        // See String.trim(): ch <= ' '
        
        int len = str.length();
        int index = len - 1;

        while (index >= 0 && _isWhitespace(str.charAt(index))) {
            index--;
        }
        return index < len ? str.substring(0, index + 1) : str;
        //return rtrim(str, SPACE_CHAR);
    }

    // right trim
    public static String rtrim(String str, char ch) {
        if (isEmpty(str)) {
            return str;
        }

        int pos = findLastNotOf(str, ch);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }

        return str.substring(0, pos + 1);
    }

    // right trim
    public static String rtrim(String str, String terms) {
        if (isEmpty(str) || isEmpty(terms)) {
            return str;
        }

        int pos = findLastNotOf(str, terms);
        if (pos == INDEX_NOT_FOUND) {
            return EMPTY_STRING;
        }

        return str.substring(0, pos + 1);
    }

    //// 1.4
    
    public static boolean contains(String str, char ch) {
        if (isEmpty(str)) {
            return false;            
        }
        
        // "".indexOf('\0')   = -1
        // " ".indexOf('\0')  = -1
                
        return str.indexOf(ch) > -1;
    }

    public static boolean contains(String str, String substr) {
        if (isEmpty(str) || isEmpty(substr)) {
            return false;            
        }
        
        // "".indexOf("")    = 0 (!): str.charAt(0): StringIndexOutOfBoundsException
        // " ".indexOf("")   = 0 (!): str.charAt(0): str.charAt(0) != "" 
        
        // String str = "";
        // String substr = "";
        // int index = str.indexOf(substr); // 0
        // if (index > -1) {
        //    System.out.println(str.charAt(index)); // StringIndexOutOfBoundsException: String index out of range: 0
        // }        
                
        return str.indexOf(substr) > -1;
    }

    //// find    

    public static int find(String str, char ch) {
        return findFirst(str, ch, 0);
    }
    
    public static int find(String str, char ch, int pos) {
        return findFirst(str, ch, pos);        
    }

    public static int find(String str, String substr) {
        return findFirst(str, substr, 0);
    }
    
    public static int find(String str, String substr, int pos) {
        return findFirst(str, substr, pos);
    }

    //// findFirst

    public static int findFirst(String str, char ch) {
        return findFirst(str, ch, 0);
    }
    
    public static int findFirst(String str, char ch, int pos) {
        return findFirstOf(str, ch, pos); // findFirst = findFirstOf(ch) 
    }
        
    public static int findFirst(String str, String substr) {
        return findFirst(str, substr, 0);
    }

    public static int findFirst(String str, String substr, int pos) {
        if (isEmpty(str) || isEmpty(substr)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }
        
        return str.indexOf(substr, pos);
    }

    //// findLast

    public static int findLast(String str, char ch) {
        return findLast(str, ch, (str == null ? 0 : str.length() - 1));
    }
    
    public static int findLast(String str, char ch, int pos) {
        return findLastOf(str, ch, pos); // findLast(ch) = findLastOf(ch)
    }

    public static int findLast(String str, String substr) {
        return findLast(str, substr, (str == null ? 0 : str.length() - 1));
    }

    public static int findLast(String str, String substr, int pos) {
        if (isEmpty(str) || isEmpty(substr)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }
        
        return str.lastIndexOf(str, pos);
    }
    
    //// findFirstOf

    public static int findFirstOf(String str, char ch) {
        return findFirstOf(str, ch, 0);
    }
    
    public static int findFirstOf(String str, char ch, int pos) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }
        
        return str.indexOf(ch, pos);
    }
    
    //
        
    public static int findFirstOf(String str, String terms) {
        return findFirstOf(str, terms, 0);        
    }

    public static int findFirstOf(String str, String terms, int pos) {
        if (isEmpty(str) || isEmpty(terms)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }
        
        for (int index = pos; index < len; index++) {
            if (contains(terms, str.charAt(index))) {    
                return index;
            }
        }
        
        return INDEX_NOT_FOUND;                
    }
    
    //// findLastOf

    public static int findLastOf(String str, char ch) {
        return findLastOf(str, ch, (str == null ? 0 : str.length() - 1));
    }
    
    public static int findLastOf(String str, char ch, int pos) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }
        
        return str.lastIndexOf(ch, pos);
    }

    public static int findLastOf(String str, String terms) {
        return findLastOf(str, terms, (str == null ? 0 : str.length() - 1));        
    }

    public static int findLastOf(String str, String terms, int pos) {
        if (isEmpty(str) || isEmpty(terms)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }
        
        for (int index = pos; index >= 0; index--) {
            if (contains(terms, str.charAt(index))) {
                return index;
            }
        }
        
        return INDEX_NOT_FOUND;
    }

    //// findFirstNotOf

    public static int findFirstNotOf(String str, char ch) {
        return findFirstNotOf(str, ch, 0);
    }

    public static int findFirstNotOf(String str, char ch, int pos) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }

        for (int index = pos; index < len; index++) {
            if (str.charAt(index) != ch) {
                return index;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static int findFirstNotOf(String str, String terms) {
        return findFirstNotOf(str, terms, 0);
    }

    public static int findFirstNotOf(String str, String terms, int pos) {
        if (isEmpty(str) || isEmpty(terms)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }

        for (int index = pos; index < len; index++) {
            if (!contains(terms, str.charAt(index))) {
                return index;
            }
        }
        
        return INDEX_NOT_FOUND;
    }
    
    //// findLastNotOf

    public static int findLastNotOf(String str, char ch) {
        return findLastNotOf(str, ch, (str == null ? 0 : str.length() - 1));
    }

    public static int findLastNotOf(String str, char ch, int pos) {
        if (isEmpty(str)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }

        for (int index = pos; index >= 0; index--) {
            if (str.charAt(index) != ch) {
                return index;
            }
        }
        
        return INDEX_NOT_FOUND;
    }

    public static int findLastNotOf(String str, String terms) {
        return findLastNotOf(str, terms, (str == null ? 0 : str.length() - 1));
    }

    public static int findLastNotOf(String str, String terms, int pos) {
        if (isEmpty(str) || isEmpty(terms)) {
            return INDEX_NOT_FOUND;
        }
        
        int len = str.length();
        if (pos < 0 || pos >= len) {
            return INDEX_NOT_FOUND;
        }

        for (int index = pos; index >= 0; index--) {
            if (!contains(terms, str.charAt(index))) {
                return index;
            }
        }
        
        return INDEX_NOT_FOUND;
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
            return EMPTY_STRING;
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
        
        if (n == 1) {
            return String.valueOf(ch);            
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
        
        if (len < 1 || isEmpty(pad)) { // isEmpty(pad): no padding
            return str;
        }
        
        int strLen = str.length();
        int padLen = pad.length();
        if (len <= strLen) {
            return str;
        }
        
        int fillLen = len - strLen;
        int padCount = fillLen / padLen;
        if (fillLen % padLen > 0) { // ceil
            padCount++;            
        }        
        String fill = replicate(pad, padCount);        
        if (fill.length() > fillLen) {
            return fill.substring(0, fillLen) + str;            
        } else {
            return fill + str;
        }
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
        if (len < 1) {
            return str;
        }
        
        int strLen = str.length();
        if (len <= strLen) {
            return str;
        }
        
        int padCount = len - strLen;
        return replicate(pad, padCount) + str;        
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

        if (len < 1 || isEmpty(pad)) {      // isEmpty(pad): no padding
            return str;
        }
        
        int strLen = str.length();
        int padLen = pad.length();
        if (len <= strLen) {
            return str;
        }
        
        int fillLen = len - strLen;
        int padCount = fillLen / padLen;
        if (fillLen % padLen > 0) { // ceil
            padCount++;            
        }
        String fill = replicate(pad, padCount);
        
        if (fill.length() > fillLen) {
            return str + fill.substring(0, fillLen);            
        } else {
            return str + fill;
        }        
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

        if (len < 1) {
            return str;
        }
        
        int strLen = str.length();
        if (len <= strLen) {
            return str;
        }

        int padCount = len - strLen;
        return str + replicate(pad, padCount);
    }

    //// 2.3

    // fill

    public static String fill(String str, int len) {
        return fill(str, len, DEFAULT_PAD);
    }

    public static String fill(String str, int len, String pad) {
        if (str == null) {
            return null;
        }
        
        // hard format: <=len or empty
        if (len < 1) {
            return EMPTY_STRING;
        }
        
        int strLen = str.length();
        if (strLen == len) {
            return str;
        }
        
        // strong format: pad or trunc
        if (strLen < len) {
            // add <pad> to right side
            return rpad(str, len, pad);
        } else {
            // remove chars from right side
            return trunc(str, len, true); // ellipsis
        }
    }

    public static String fill(String str, int len, char pad) {
        if (str == null) {
            return null;
        }
        
        // hard format: <=len or empty
        if (len < 1) {
            return EMPTY_STRING;
        }
        
        int strLen = str.length();
        if (strLen == len) {
            return str;
        }
        
        // hard format: pad or trunc
        if (strLen < len) {
            // add <pad> to right side
            return rpad(str, len, pad);
        } else {
            // remove chars from right side
            return trunc(str, len, true); // ellipsis
        }
    }
    
    // ellipsis
    public static String ellipsis(String str, int len) {
        return trunc(str, len, true); // ellipsis
    }

    // trunc
    public static String trunc(String str, int len) {
        return trunc(str, len, false);
    }

    // trunc
    public static String trunc(String str, int len, boolean ellipsis) {
        if (str == null) {
            return null;
        }
        
        // soft format
        if (len < 1) {
            return str;
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
        
        // hard format: <=len or empty
        if (len < 1) {
            return EMPTY_STRING;
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
        
        // hard format: <=len or empty
        if (len < 1) {
            return EMPTY_STRING;
        }
        
        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substring(strLen - len);
    }

    //// 3.1

    /**
     * Capitalize string: 
     * 
     * capitalize("name") = "Name" 
     * capitalize("n")    = "N"
     * capitalize("NAME") = "NAME"
     * 
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        return capitalize(str, DEFAULT_FORCE_CAPITALIZE, null);
    }

    /**
     * Capitalize string: 
     * 
     * capitalize("name", true)  = "Name" 
     * capitalize("name", false) = "Name"
     * capitalize("nAmE", false) = "nAmE" 
     * capitalize("nAmE", true)  = "Name"
     * 
     * @param str
     * @param forceRest - if forceRest is true then update last characters (lower case)
     * @return
     */
    public static String capitalize(String str, boolean forceRest) {
        return capitalize(str, forceRest, null);
    }

    public static String capitalize(String str, Locale locale) {
        return capitalize(str, DEFAULT_FORCE_CAPITALIZE, locale);
    }

    public static String capitalize(String str, boolean forceRest, Locale locale) {
        if (isEmpty(str)) {
            return str;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (str.length() == 1) {
            return str.substring(0, 1).toUpperCase(locale);
        }
        return str.substring(0, 1).toUpperCase(locale) + (forceRest ? str.substring(1).toLowerCase(locale) : str.substring(1)); 
    }

    /**
     * Decapitalize string:
     *  
     * decapitalize("Name") = "name" 
     * decapitalize("N")    = "n"
     * decapitalize("NAME") = "nNAME"
     * 
     * @param str
     * @return
     */
    public static String decapitalize(String str) {
        return decapitalize(str, DEFAULT_FORCE_CAPITALIZE, null);
    }

    /**
     * Decapitalize string: 
     * 
     * capitalize("Name", false) = "name" 
     * capitalize("Name", true)  = "nAME"
     * capitalize("NaMe", false) = "naMe" 
     * capitalize("NaMe", true)  = "nAME"
     * 
     * @param str
     * @param forceRest - if forceRest is true then update last characters (upper case)
     * @return
     */
    public static String decapitalize(String str, boolean forceRest) {
        return decapitalize(str, forceRest, null);
    }

    public static String decapitalize(String str, Locale locale) {
        return decapitalize(str, DEFAULT_FORCE_CAPITALIZE, locale);
    }

    public static String decapitalize(String str, boolean forceRest, Locale locale) {
        if (isEmpty(str)) {
            return str;
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (str.length() == 1) {
            return str.substring(0, 1).toLowerCase(locale);
        }
        return str.substring(0, 1).toLowerCase(locale) + (forceRest ? str.substring(1).toUpperCase(locale) : str.substring(1)); 
    }

    //

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
        return upper ? str.toUpperCase(locale) : str.toLowerCase(locale);
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
     * @param separators
     * @return
     */
    public static String toCamelCase(String str, String separators) {
        return toCamelCase(str, separators, DEFAULT_CAMEL_CASE_CAPITALIZE);
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
    public static String toCamelCase(String str, String separators, boolean capitalize) {
        return toTypeCase(str, (capitalize ? "Camel" : "camel"), separators, null);
    }

    ////

    /**
     * Convert a string to 'Snake Case'
     * 
     * @param str
     * @return
     */
    public static String toSnakeCase(String str) {
        return toSnakeCase(str, null, false);
    }

    /**
     * Convert a string to 'Snake Case'
     * 
     * @param str
     * @param separators
     * @return
     */
    public static String toSnakeCase(String str, String separators) {
        return toSnakeCase(str, separators, false);
    }

    /**
     * Convert 'Camel Case' string to 'Snake Case'
     * 
     * @param str
     * @param upper
     * @return
     */
    public static String toSnakeCase(String str, boolean upper) {
        return toSnakeCase(str, null, upper);
    }

    /**
     * Convert a string to 'Snake Case'
     * 
     * @param str
     * @param separators
     * @param upper
     * @return
     */
    public static String toSnakeCase(String str, String separators, boolean upper) {
        return toTypeCase(str, (upper ? "SNAKE" : "snake"), separators, SNAKE_CONNECTOR);
    }

    ////

    /**
     * Convert a string to 'Kebab Case'
     * 
     * @param str
     * @return
     */
    public static String toKebabCase(String str) {
        return toKebabCase(str, null, false);
    }

    /**
     * Convert a string to 'Kebab Case'
     * 
     * @param str
     * @param separators
     * @return
     */
    public static String toKebabCase(String str, String separators) {
        return toKebabCase(str, separators, false);
    }

    /**
     * Convert a string to 'Kebab Case'
     * 
     * @param str
     * @param upper
     * @return
     */
    public static String toKebabCase(String str, boolean upper) {
        return toKebabCase(str, null, upper);
    }

    /**
     * Convert a string to 'Kebab Case'
     * 
     * @param str
     * @param separator
     * @param upper
     * @return
     */
    public static String toKebabCase(String str, String separators, boolean upper) {
        return toTypeCase(str, (upper ? "KEBAB" : "kebab"), separators, KEBAB_CONNECTOR);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    
    // splitOp: separators and A (Upper Char)
    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR
    private static List<String> _splitOp(String str, String separators) {
        if (str == null) {
            return null;
        }

        List<String> result = new ArrayList<String>();
        if (isEmpty(str) || isEmpty(separators)) {
            result.add(str);
            return result;
        }

        int strLen = str.length();
        int sepLen = separators.length();

        char ch = 0;
        char separator = 0;
        boolean find = false;
        
        int pos = 0;
        int end = 0;
        int i = 0;
        int j = 0;

        while (i < strLen) {

            ch = str.charAt(i);
            find = false;
            j = 0;

            // Find a separator
            while (j < sepLen) {
                separator = separators.charAt(j);
                if (separator == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    if (Character.isUpperCase(ch)) {
                        find = true;
                        break;
                    }
                } else if (ch == separator) {
                    find = true;
                    break;
                }
                j++;
            }

            if (find) {
                
                end = i;
                
                if (pos < end) {
                    result.add(str.substring(pos, end));
                }                    

                if (separator == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    pos = end;          // include 'Upper Char'
                } else {
                    pos = end + 1;      // skip separator
                }

            }
            
            i++;

        }
        
        if (pos < strLen) {
            result.add(str.substring(pos));            
        }           

        return result;
    }
    
    private static String _transformToken(String token, int caseOp, boolean first) {
        if (isEmpty(token)) {
            return token;            
        }
        
        if (caseOp == CO_LOWER_CHAR) {                                           // camelCase
            if (first) {
                return token.substring(0, 1).toLowerCase() + token.substring(1); // lower char (first)
            } else {
                return token.substring(0, 1).toUpperCase() + token.substring(1); // UPPER char (first)
            }
        } else if (caseOp == CO_UPPER_CHAR) {                                    // CamelCase, PascalCase
            return token.substring(0, 1).toUpperCase() + token.substring(1);     // UPPER char (first)
        } else if (caseOp == CO_LOWER) {  
            return token.toLowerCase();                                          // lower case
        } else if (caseOp == CO_UPPER) {
            return token.toUpperCase();                                          // UPPER case
        }

        return token;
    }
    
    
    private static void _transformOp(List<String> tokens, int caseOp) {
        if (tokens == null || tokens.isEmpty()) {
            return;            
        }
        
        // No transformation
        if (caseOp == CO_NONE) {
            return;
        }
        
        for (int i = 0; i < tokens.size(); i++) {
            tokens.set(i, _transformToken(tokens.get(i), caseOp, i == 0));
        }
    }

    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR
    private static String _toCaseOp(String str, String separators, String connector, int caseOp) {

        if (isBlank(str)) {
            return str;
        }

        StringBuilder result = new StringBuilder();
        List<String> tokens = _splitOp(str, separators);
        _transformOp(tokens, caseOp);

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

    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR
    private static String _toTypeCase(String str, String type, String separators, String connector) {

        if (isEmpty(str)) {
            return str;
        }

        int code = getCaseCode(type);
        if (code == 0) {
            // Invalid case code
            return str;
        }

        // SIMPLE CASE
        if (code == CT_lowercase) {
            return toCase(str, false); // lower case
        } else if (code == CT_UPPERCASE) {
            return toCase(str, true);  // UPPER case
        }

        // COMPLEX CASE
        String _separators = (isEmpty(separators) ? DEFAULT_CASE_SEPARATORS_A : separators);
        
        String _connector = connector;
        if (code == CT_kebab_case || code == CT_KEBAB_CASE || code == CT_Kebab_Case) {
            _connector = (isEmpty(connector) ? KEBAB_CONNECTOR :connector);
        } else if (code == CT_snake_case || code  == CT_SNAKE_CASE || code == CT_Snake_Case) {
            _connector = (isEmpty(connector) ? SNAKE_CONNECTOR : connector);
        }
            
        int _caseOp = getCaseOp(code);
        
        return _toCaseOp(str, _separators, _connector, _caseOp);

        // UNKNOWN CASE: use 'separators', 'connector'
        // _toCaseOp(str, separators, connector, CO_NONE);

    }

    // Return case op by case code
    public static int getCaseOp(int code) {
        
        if (code == CT_camelCase) {            
            return CO_LOWER_CHAR;
            
        } else if (code == CT_PascalCase) {            
            return CO_UPPER_CHAR;
            
        } else if (code == CT_kebab_case) {            
            return CO_LOWER;
            
        } else if (code == CT_KEBAB_CASE) {
            return CO_UPPER;
            
        } else if (code == CT_Kebab_Case) {
            return CO_UPPER_CHAR;
            
        } else if (code == CT_snake_case) {
            return CO_LOWER;
            
        } else if (code == CT_SNAKE_CASE) {
            return CO_UPPER;
            
        } else if (code == CT_Snake_Case) {
            return CO_UPPER_CHAR;
        }
        
        return CO_NONE;
        
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
    public static int getCaseCode(String type) {

        if (type == null) {
            return CT_NONE;
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

        return CT_NONE;

    }

    public static String toTypeCase(String str, String type) {
        return _toTypeCase(str, type, DEFAULT_CASE_SEPARATORS_A, null);
    }

    public static String toTypeCase(String str, String type, String separators, String connector) {
        return _toTypeCase(str, type, separators, connector);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

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
        if (isEmpty(str) || isEmpty(prefix)) {
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
        if (isEmpty(str) || isEmpty(suffix)) {
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

    // TODO: countUniqueChars(str), countUniqueWords(str), countUniqueLines(str)

    public static int countChars(String str, char ch) {
        if (isEmpty(str)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static int countStrings(String str, String substr) {
        if (isEmpty(str) || isEmpty(substr)) {
            return 0;
        }
        
        int pos = 0;
        int count = 0;
        while ((pos = find(str, substr, pos)) != INDEX_NOT_FOUND) {
            pos += substr.length();
            count++;
        }
        return count;
    }

    ////

    public static int countWords(String str) {
        return countWords(str, null);
    }

    public static int countWords(String str, String separators) {
        String[] words = splitWords(str, separators);
        return words == null ? 0 : words.length;
    }

    public static int countLines(String str) {
        String[] lines = splitLines(str);
        return lines == null ? 0 : lines.length;
    }

    //// 6.1

    // Non RegExp
    public static String replaceAll(String str, String s1, String s2) {
        if (str == null) {
            return null;
        }
        if (s1 == null || s2 == null) {
            return str;
        }
        if (s1 == s2) {
            return str;
        }
        if (s1.equals(s2)) {
            return str;
        }

        int index = str.indexOf(s1);
        if (index == INDEX_NOT_FOUND) {
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
            return str;

        }
        if (newValues == null) {
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
    
    public static String[] split(String str, char separator) {
        return splitBySeparator(str, separator);
    } 

    public static String[] split(String str, String separator) {
        return splitBySeparator(str, separator);
    } 

    ////
    
    public static String[] splitBySeparator(String str, char separator) {
        return tokenizeBySeparator(str, separator, false, true);
    } 

    public static String[] splitBySeparator(String str, char separator, boolean preverseAll) {
        return tokenizeBySeparator(str, separator, false, preverseAll);
    } 
    
    //

    public static String[] splitBySeparator(String str, String separator) {
        return tokenizeBySeparator(str, separator, false, true);
    }

    public static String[] splitBySeparator(String str, String separator, boolean preserveAll) {
        return tokenizeBySeparator(str, separator, false, preserveAll);
    }
    
    //

    public static String[] splitBySeparators(String str, String separators) {
        return tokenizeBySeparators(str, separators, false, true);
    }

    public static String[] splitBySeparators(String str, String separators, boolean preserveAll) {
        return tokenizeBySeparators(str, separators, false, preserveAll);
    }

    ////

//    public static String[] split(String str, String seperators) {
//        return split(str, seperators, false, false);
//    } 
//
//    public static String[] split(String str, String seperators, boolean include, boolean trim) {
//        if (isEmpty(str)) {
//            return EMPTY_STRING_ARRAY;
//        }
//        if (seperators == null) {
//            return new String[] { str };
//        }
//        StringTokenizer tokens = new StringTokenizer(str, seperators, include);
//        String[] result = new String[tokens.countTokens()];
//        int i = 0;
//        String token;
//        while (tokens.hasMoreTokens()) {
//            token = tokens.nextToken();
//            if (trim) {
//                token = trim(token);                
//            }
//            result[i++] = token;
//        }
//        return result;
//    }
    
    
    ////
    
    public static void trimArray(String[] array) {
        if (array == null || array.length == 0) {
            return;            
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = trim(array[i]);            
        }
    }
    
    ////
        
    public static String[] splitTrim(String str, String separator) {
        return splitTrimBySeparator(str, separator);
    }

    public static String[] splitTrimBySeparator(String str, String separator) {
        String[] res = splitBySeparator(str, separator);
        trimArray(res);
        return res;
    }

    public static String[] splitTrimBySeparators(String str, String separators) {
        String[] res = splitBySeparator(str, separators);
        trimArray(res);
        return res;
    }

    public static String[] splitWorker(final String str, final char separatorChar, final boolean preserveAllTokens) {
        
        if (str == null) {
            return null;
        }
        
        final int len = str.length();
        if (len == 0) {
            return new String[0];
        }
        
        final List<String> list = new ArrayList<>();
        int i = 0;
        int start = 0;
        boolean match = false;
        boolean lastMatch = false;
        
        while (i < len) {
            if (str.charAt(i) == separatorChar) {
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        
        return list.toArray(new String[0]);
    }
    
    ////
    
    public static String[] tokenizeBySeparator(String str, char separator, boolean includeAll, boolean preserveAll) {
        if (isEmpty(str)) {
            return EMPTY_STRING_ARRAY;
        }
        
        int start = 0;
        int end = 0;
        int sep_len = 1;
        String token;
        List<String> result = new ArrayList<String>();
        
        while ((end = find(str, separator, start)) != INDEX_NOT_FOUND) {
            if (end - start == 0) {
                if (preserveAll && !includeAll) {
                    result.add(EMPTY_STRING);
                }
            } else {
                token = str.substring(start, end); // end - start
                result.add(token);
            }            

            if (includeAll) {
                token = str.substring(end, end + sep_len); // sep_len
                result.add(token);
            }

            start = end + sep_len;

        }
        
        if (start == str.length()) {
            if (preserveAll && !includeAll) {
                result.add(EMPTY_STRING);
            }
        } else {
            result.add(str.substring(start));
        }
        
        return result.toArray(new String[0]);
    }

    public static String[] tokenizeBySeparator(String str, String separator, boolean includeAll, boolean preserveAll) {
        if (isEmpty(str)) {
            return EMPTY_STRING_ARRAY;
        }
        if (isEmpty(separator)) {
            return new String[] {str};
        }

        int start = 0;
        int end = 0;
        int sep_len = separator.length();
        String token;
        List<String> result = new ArrayList<String>();
        
        while ((end = find(str, separator, start)) != INDEX_NOT_FOUND) {
            if (end - start == 0) {
                if (preserveAll && !includeAll) {
                    result.add(EMPTY_STRING);
                }
            } else {
                token = str.substring(start, end); // end - start
                result.add(token);
            }            

            if (includeAll) {
                token = str.substring(end, end + sep_len); // sep_len
                result.add(token);
            }

            start = end + sep_len;

        }
        
        if (start == str.length()) {
            if (preserveAll && !includeAll) {
                result.add(EMPTY_STRING);
            }
        } else {
            result.add(str.substring(start));
        }
        
        return result.toArray(new String[0]);
    }
    
    public static String[] tokenizeBySeparators(String str, String separators, boolean includeAll, boolean preserveAll) {
        if (isEmpty(str)) {
            return EMPTY_STRING_ARRAY;
        }
        if (isEmpty(separators)) {
            return new String[] {str};
        }

        int start = 0;
        int end = 0;
        int sep_len = 1;
        String token;
        List<String> result = new ArrayList<String>();
        
        while ((end = findFirstOf(str, separators, start)) != INDEX_NOT_FOUND) {
            if (end - start == 0) {
                if (preserveAll && !includeAll) {
                    result.add(EMPTY_STRING);
                }
            } else {
                token = str.substring(start, end); // end - start
                result.add(token);
            }            

            if (includeAll) {
                token = str.substring(end, end + sep_len); // sep_len
                result.add(token);
            }

            start = end + sep_len;

        }
        
        if (start == str.length()) {
            if (preserveAll && !includeAll) {
                result.add(EMPTY_STRING);
            }
        } else {
            result.add(str.substring(start));
        }
        
        return result.toArray(new String[0]);
    }
    
    ////
    
    public static String[] splitWords(String str) {
        return splitWords(str, null);
    }

    public static String[] splitWords(String str, String separators) {
        if (separators == null) {
            separators = DEFAULT_WORD_SEPARATORS;
        }
        return splitBySeparators(str, separators, false);
    }

    public static String[] splitLines(String str) {
        if (isEmpty(str)) {
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
        if (array == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;        
        //return CltLib.toList(array);
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
