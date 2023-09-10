
#include <cstring>
#include <algorithm>

#include <sstream>
#include <vector>
#include <cmath>

#include "strlib.h"

// Functions

/////////////////////////////////////////////////////////////////////////////////
//
// 1.1 empty, size
//
// - isEmpty(const string  &str)                                                - check empty
// - isEmpty(const string  &str, bool trim)                                     - trim, check empty
//
// - isBlank(const string  &str)                                                - check blank
// - size(const string  &str)                                                   - length
//
// - equals(const char* str1, const char* str2)
// - equals(const string  &str1, const string  &str2)
//
// - equalsContent(const string  &str, ???)                                     - N/A
//
// 1.2 
//
// - normalize(const string  &str)                                              - trim
// - defIfEmpty(const string  &str, const string  &defStr)                      - str.empty() ? defStr: str
//
// 1.3 trim (left, right)
// 
// - trim(const string  &str)                                                   - all trim (left, right)
// - trim(const string  &str, char ch)           
//
// - ltrim(const string  &str)                                                  - left trim
// - ltrim(const string  &str, char ch)
//
// - rtrim(const string  &str)                                                  - right trim
// - rtrim(const string  &str, char ch)
//
// 1.4
//
// - findFirstNotOf(const string  &str, char ch)
// - findFirstNotOf(const string  &str, char ch, int start)                     - N/A
//
// - findLastNotOf(const string  &str, ch)
// - findLastNotOf(const string  &str, char ch, int end)                        - N/A

/////////////////////////////////////////////////////////////////////////////////
// 2.1
//
// - replicate(const string  &str, int n)                                       - replicate("abc", 3) = 'abcabcabc' : repeat (?)
// - replicate(char ch, int n)                                                  - replicate('a', 3) = "aaa"
//
// 2.2 lpad, rpad
//
// - lpad(const string  &str, int len)                                          - lpad("abc", 5) = "  abc"
// - lpad(const string  &str, int len, string  pad)                             - lpad("abc", 5, "*") = "**abc"
// - lpad(const string  &str, int len, char pad)                                - lpad("abc", 5, '*') = "**abc"
//
// - rpad(const string  &str, int len)                                          - rpad("abc", 5) = "abc  "
// - rpad(const string  &str, int len, string  pad)                             - rpad("abc", 5, "*") = "abc**""
// - rpad(const string  &str, int len, char pad)                                - rpad("abc", 5, '*') = "abc**""
//
// 2.3
//
// - formatString(const string  &str, int len)                                  - 
// - formatString(const string  &str, int len, const string  &pad)              -  
// - formatString(const string  &str, int len, const char &pad)                 -  
//
// - shortString(const string  &str, int len)                                   - 
//
// - trunc(const string  &str, int len)                                         - 
// - trunc(const string  &str, int len, bool trim, bool ellipsis)               - 
//
// - left(const string  &str, int len)                                          - 
// - right(const string  &str, int len)                                         - 
//

/////////////////////////////////////////////////////////////////////////////////
// 3.1
//
// - capitalize(const string  &str)		                                       - capitalize("abc") = "Abc"
// - capitalize(const string  &str, bool force)		                           - capitalize(("abC") = "Abc"
// - capitalize(const string  &str, const string  &locale)                     - N/A
// - capitalize(const string  &str, bool force, const string  &locale)         - N/A
//
// - decapitalize(const string  &str)		                                   - decapitalize("Abc") = "abc"
// - decapitalize(const string  &str, bool force)	                           - decapitalize("AbC") = "abc"
// - decapitalize(const string  &str, const string  &locale)                   - N/A
// - decapitalize(const string  &str, bool force, const string  &locale)       - N/A
//
// - upper(const string  &str)			                                       - upper("aBc") = "ABC" 
// - upper(const string  &str, const string  &locale)                          - N/A
//
// - lower(const string  &str)			                                       - lower("Abc") = "abc" 
// - lower(const string  &str, const string  &locale)                          - N/A
//
// - toUpperCase(const string  &str)
// - toUpperCase(const string  &str, const string  &locale)                    - N/A
//
// - toLowerCase(const string  &str)
// - toLowerCase(const string  &str, const string  &locale)                    - N/A
//
// - toCase(const string  &str, bool upper)
// - toCase(const string  &str, bool upper, const string  &locale)             - N/A
// - toCase(char ch, bool upper);
//
// - toCamelCase(const string  &str)		                                   - toCamelCase("property_name") = "PropertyName"
// - toCamelCase(const string  &str, const string  &separator)                 - 
// - toCamelCase(const string  &str, bool capitalize)                          - 
// - toCamelCase(const string  &str, const string  &separator, bool capitalize)- 
//
// - toSnakeCase(const string  &str)		                                    - toSnakeCase("PropertyName") = "property_name"
// - toSnakeCase(const string  &str, const string  &separator)                  - 
// - toSnakeCase(const string  &str, bool upper)                                -  
// - toSnakeCase(const string  &str, const string  &separator, bool upper)      -  
// - toSnakeCase(const string  &str, const string  &separator, bool upper, bool trim) -  
//
// - toKebabCase(const string  &str)		                                    - toKebabCase("PropertyName") = "property-name"
// - toKebabCase(const string  &str, const string  &separator)                  - 
// - toKebabCase(const string  &str, const string  &separator, bool upper)      -  
// - toKebabCase(const string  &str, bool upper)                                -  
// - toKebabCase(const string  &str, const string  &separator, bool upper, bool trim) -  
//
// - toTypeCase(const string  &str, const string  &type)
// - toTypeCase(const string  &str, const string  &type, const string  &separators, const string  &connector)
//
// - reverse(const string  &str)			                                     - reverse("abc") = "cba"
//

/////////////////////////////////////////////////////////////////////////////////
// 4.1
//
// - startsWith(const string  &str, const string  &prefix)                       - startsWith("myfile.txt", "myfile") = true
// - endsWith(const string  &str, const string  &suffix)                         - endsWith("myfile.txt", ".txt") = true
//
// - hasPrefix(const string  &str, const string  &prefix)                        - [alias]: hasPrefix("myfile.txt", "myfile") = true
// - hasSuffix(const string  &str, const string  &suffix)                        - [alias]: hasSuffix("myfile.txt", ".txt") = true
//
// - startsWithIgnoreCase(const string  &str, const string  &prefix)             - startsWithIgnoreCase("myfile.txt", "MyFile") = true
// - endsWithIgnoreCase(const string  &str, const string  &suffix)               - endsWithIgnoreCase("myfile.txt", ".TxT") = true
//
// - hasPrefixIgnoreCase(const string  &str, const string  &prefix)              - [alias]: hasPrefixIgnoreCase("myfile.txt", "MyFile") = true
// - hasSuffixIgnoreCase(const string  &str, const string  &suffix)              - [alias]: hasSuffixIgnoreCase("myfile.txt", ".TxT") = true
//
// 4.2
// 
// - removePrefix(const string  &str, const string  &prefix)                     -
// - removePrefixes(const string  &str, const vector<string> &prefixes)          - 
//
// - removeSuffix(const string  &str, const string  &suffix)                     -
// - removeSuffixes(const string  &str, const vector<string> &suffixes)          - 
//
// 4.3
// 
// - isQuoted(const string  &str)                                                -
// - isQuoted(const string  &str, const string  &startQuote, const string  &endQuote)
// - needQuote(const string  &str)                                               -
// - needQuote(const string  &str, const string  &startQuote, const string  &endQuote)
// - quote(const string  &str)                                                   -
// - quote(const string  &str, const string  &startQuote, const string  &endQuote)
// - unquote(const string  &str)                                                 -
// - unquote(const string  &str, const string  &startQuote, const string  &endQuote)
//
// 4.4
// 
// - isColumnSeparator(const char ch)                                            -
// - isColumnText(const char* array, int len)                                    -
// - isColumnText(const string  &str)                                            -
// - isLineText(const char* array, int len)                                      -
// - isLineText(const string  &str)                                              -

/////////////////////////////////////////////////////////////////////////////////
// 5.1
//
// - countChar(const string  &str, char ch)
// - countString(const string  &str, const string  &findStr)
// - countWord(const string  &str)
// - countWord(const string  &str, const string  &separators)
// - countLine(const string  &str)

/////////////////////////////////////////////////////////////////////////////////
// 6.1
//  
// - replaceAll(const string  &str, const string  &s1, const string  &s2)
// - replaceAll(const string  &str, const vector<string> &oldValues, const vector<string> &newValues)
    
/////////////////////////////////////////////////////////////////////////////////
// 7.1
//      
// - split(const string  &str, const string  &separators)
// - split(const string  &str, const string  &separators, bool include)          - N/A
// 
// - splitWords(const string  &str)
// - splitWords(const string  &str, const string  &separators)
// - splitLines(const string  &str)

/////////////////////////////////////////////////////////////////////////////////
// 8.1
//      
// - toString(const char[] array)
// - toString(const vector<string> values)
// - toString(const vector<string> values, const string  &separator)
//
// - toWordArray(const string  &str)                                            - N/A ???
// - toWordArray(const string  &str, const string  &separators)                 - N/A ???
//
// - toLineArray(const string  &str)                                            - N/A ???
//
// 8.2
//
// - toWordList(const string  &str)                                             - 
// - toWordList(const string  &str, const string  &separators)                  - 
//
// - toLineList(const string  &str)                                             - 
// - toList(array)                                                              - N/A ???    

/////////////////////////////////////////////////////////////////////////////////
// 9.1
//  
// - isDigit(char ch)                                                          -
// - isLetter(char ch)                                                         -
// - isAlpha(char ch)                                                          - ???
//
// - isWhitespace(char ch)
// - isDot(char ch)
// - isUnderline(char ch)
// - isIdentifier(const string  &str)
//
// 9.2
//
// - isUpperCase(char ch)
// - isLowerCase(char ch)

/////////////////////////////////////////////////////////////////////////////////
// 10.1 ISO-LATIN-1
//  
// - isISODigit(char ch)                                                       - N/A ???
//
// - isISOLetter(char ch)                                                      - N/A ???
// - isISOUpperLetter(char ch)                                                 - N/A ???
// - isISOLowerLetter(char ch)                                                 - N/A ???
//
// - isISOWhitespace(char ch)                                                  - N/A ???
// - isISODot(char ch)                                                         - N/A ???
// - isISOUnderline(char ch)                                                   - N/A ???
// - isISOIdentifier(char str)                                                 - N/A ???

/////////////////////////////////////////////////////////////////////////////////
// 11.1 
//  
// - maxLen(const string  array[], int len)                                     - May be rename to 'maxLength' ?
//



// https://github.com/sumeetchhetri/ffead-cpp/blob/master/src/modules/common/StringUtil.cpp
// https://rosettacode.org/wiki/Repeat_a_string
// https://rosettacode.org/wiki/Repeat_a_string#C.2B.2B
// https://rosettacode.org/wiki/Real_constants_and_functions

// https://ravesli.com/urok-57-vvedenie-v-std-string/

// https://github.com/apache/xerces-c/blob/master/src/xercesc/util/XMLString.cpp

// Name Convention: 
//
// - camelCase
// - CamelCase, PascalCase
// - snake_case
// - Snake_Case
// - SNAKE_CASE, MACRO_CASE, CONSTANT_CASE
// - kebab-case, dash-case, lisp-case
// ...

// Real using:
//
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

// https://en.wikipedia.org/wiki/Naming_convention_(programming)#Multiple-word_identifiers


namespace strlib {

    //// 1.1

    bool isEmpty(const std::string &str) {
        return str.empty();
    }

    bool isEmpty(const std::string &str, bool trim) {
        if (!trim) {
            return str.empty();
        }
        return isBlank(str);
    }

    bool isEmpty(char ch) {
        return ch == '\0';
    }

    bool isBlank(const std::string &str) {
        if (str.empty()) {
            return true;
        }
        int len = str.length();
        // " \t\n\r\f\v"  /v - ?
        char ch = 0;
        for (int i = 0; i < len; i++) {
            ch = str[i];
            //if (str[i] != BLANK_CHAR) {
            if (ch != ' ' 
              && ch != '\t' 
              && ch != '\n'
              && ch != '\r'
              && ch != '\f'
              && ch != '\v'
              ) {
                return false;
            }
        }
        return true;
    }

    //

    int size(const char* str) {
        return str == nullptr ? 0 : strlen(str); // str.length()
    }

    int size(const std::string &str) {
        return str.size(); // str.length()
    }

    ////

    bool equals(const char* str1, const char* str2) {
        if (str1 == nullptr || str2 == nullptr) {
            return false;
        }
        return strcmp(str1, str2) == 0;
    }

    bool equals(const std::string &str1, const std::string &str2) {
        return equals(str1.c_str(), str2.c_str());
    }

    //// 1.2

    /*
     Return normalized std::string 
    */
    std::string normalize(const std::string &str) {
        return trimAll(str);
    }

    void _normalize(std::string &str) {
        _trimAll(str);
    }

    std::string normalizeBlank(const std::string &str, bool trimText, bool skipBlank) {
        std::string strn = str;
        _normalizeBlank(strn, trimText, skipBlank);
        return strn;
    }

    void _normalizeBlank(std::string &str, bool trimText, bool skipBlank) {
        if (str.empty()) {
            return;
        }
        
        // 'trimText' flag overrides 'skipBlank' flag for each type
        // If 'trimText' is true we will skip empty text after normalization
        // 'skipBlank' for non normalize text only

        if (!trimText && !skipBlank) {
            return;
        }

        if (!trimText) {
            if (!isBlank(str)) {
                return;
            }
            str.clear();
            return;
        }

        _normalize(str);
    }

    std::string normalizeQuote(const std::string &str) {
        std::string strn = str;
        _normalizeQuote(strn);
        return strn;
    }

    void _normalizeQuote(std::string &str) {
        if (str.empty()) {
            return;
        }
        if (isQuoted(str)) {
            _unquote(str);
        }

        _normalize(str);
        _quote(str);
    }

    /*
     Return 'defStr' if 'str' is empty
     defIfEmpty("", "")     = ""
     defIfEmpty("", "-")    = "-"
     defIfEmpty("abc", "-") = "abc"
    */
    std::string defIfEmpty(const std::string &str, const std::string &defStr) {
        return isEmpty(str) ? defStr : str;
    }

    //// 1.3

    // trim (left, right)

    /*
     Trim a std::string  by ' '
     trim("  abc  ") = "abc"
     trim("  abc")   = "abc"
     trim("abc  ")   = "abc"
    */
    std::string trim(const std::string &str) {
        std::string strn = str;
        _trim(strn, BLANK_CHAR);
        return strn;
    }

    /*
     Trim a std::string  by 'ch'
     trim("--abc--", '-') = "abc"
     trim("--abc", '-')   = "abc"
     trim("abc--", '-')   = "abc"
    */
    std::string trim(const std::string &str, const char ch) {
        std::string strn = str;
        _trim(strn, ch);
        return strn;
    }

    std::string trim(const std::string &str, const char* ch) {
        std::string strn = str;
        if (ch == nullptr) {
            return strn;
        }
        _trim(strn, ch);
        return strn;
    }

    void _trim(std::string &str) {
        _trim(str, BLANK_CHAR);
    }

    void _trim(std::string &str, const char ch) {
        _ltrim(str, ch);
        _rtrim(str, ch);
    }

    void _trim(std::string &str, const char* ch) {
        if (ch == nullptr) {
            return;
        }
        _ltrim(str, ch);
        _rtrim(str, ch);
    }

    std::string trimAll(const std::string &str) {
        std::string strn = str;
        _trimAll(strn);
        return strn;
    }

    void _trimAll(std::string &str) {
        _trim(str, " \t\n\r\f\v"); // /v - ?
    }

    // ltrim

    std::string ltrim(const std::string &str) {
        std::string strn = str;
        _ltrim(strn, BLANK_CHAR);
        return strn;
    }

    std::string ltrim(const std::string &str, const char ch) {
        std::string strn = str;
        _ltrim(strn, ch);
        return strn;
    }

    std::string ltrim(const std::string &str, const char* ch) {
        std::string strn = str;
        if (ch == nullptr) {
            return strn;
        }
        _ltrim(strn, ch);
        return strn;
    }

    void _ltrim(std::string &str) {
        _ltrim(str, BLANK_CHAR);
    }

    void _ltrim(std::string &str, const char ch) {
        str.erase(0, str.find_first_not_of(ch)); // prefixing spaces
    }

    void _ltrim(std::string &str, const char* ch) {
        if (ch == nullptr) {
            return;
        }
        str.erase(0, str.find_first_not_of(ch)); // prefixing spaces
    }


    // rtrim

    std::string rtrim(const std::string &str) {
        std::string strn = str;
        _rtrim(strn, BLANK_CHAR);
        return strn;
    }

    std::string rtrim(const std::string &str, const char ch) {
        std::string strn = str;
        _rtrim(strn, ch);
        return strn;
    }

    std::string rtrim(const std::string &str, const char* ch) {
        std::string strn = str;
        if (ch == nullptr) {
            return strn;
        }
        _rtrim(strn, ch);
        return strn;
    }

    void _rtrim(std::string &str) {
        _rtrim(str, BLANK_CHAR);
    }

    void _rtrim(std::string &str, const char ch) {
        str.erase(str.find_last_not_of(ch) + 1); // surfixing spaces
    }

    void _rtrim(std::string &str, const char* ch) {
        if (ch == nullptr) {
            return;
        }
        str.erase(str.find_last_not_of(ch) + 1); // surfixing spaces
    }


    //// 1.4 

    // findFirstNotOf/LastNotOf

    int findFirstNotOf(const std::string &str, const char ch) {
        return str.find_first_not_of(ch);
    }

    int findLastNotOf(const std::string &str, const char ch) {
        return str.find_last_not_of(ch);
    }

    //// 2.1

    std::string replicate(const std::string &str, int n) {
        // TODO: Use std::string  constructor
        std::string result;
        if (isEmpty(str) || n < 1) {
            //result = EMPTY_STRING; // TODO: copy std::string 
            return result;
        }

        result.reserve(str.length() * n); // avoid repeated reallocation
        for (int i = 0; i < n; i++) {
            result += str;
        }
        return result;
    }

    std::string replicate(const char ch, int n) {
        if (isEmpty(ch) || n < 1) {
            return "";
        }
        std::string result(n, ch);
        return result;
    }

    //// 2.2 lpad, rpad

    // lpad

    std::string lpad(const std::string &str, int len) {
        return lpad(str, len, DEFAULT_PAD);
    }

    std::string lpad(const std::string &str, int len, const std::string pad) {
        std::string result;
        if (isEmpty(str)) {
            //result = EMPTY_STRING; // TODO: copy std::string 
            return result;
        }

        if (isEmpty(pad) || len < 1) {
            result = str; // TODO: copy std::string 
            return result;
        }

        int strLen = str.length();
        int padLen = pad.length();

        // TODO: What about truncate std::string  (...)
        if (len <= strLen) {
            return str;
        }
        int ln = len - strLen;

        ln = (int) ceil((double) ln / padLen);
        result = replicate(pad, ln) + str;
        return result.substr(result.length() - len);
    }

    //

    std::string lpad(const std::string &str, int len, const char pad) {
        std::string strpad(1, pad);
        return lpad(str, len, strpad);
    }

    // rpad

    std::string rpad(const std::string &str, int len) {
        return lpad(str, len, DEFAULT_PAD);
    }

    std::string rpad(const std::string &str, int len, const std::string pad) {
        std::string result;
        if (isEmpty(str)) {
            //result = EMPTY_STRING; // TODO: copy std::string 
            return result;
        }

        if (isEmpty(pad) || len < 1) {
            result = str; // TODO: copy std::string 
            return result;
        }

        int strLen = str.length();
        int padLen = pad.length();

        // TODO: What about truncate std::string  (...)
        if (len <= strLen) {
            return str;
        }
        int ln = len - strLen;

        ln = (int) ceil((double) ln / padLen);
        result = str + replicate(pad, ln);
        return result.substr(0, len);
    }

    //

    std::string rpad(const std::string &str, int len, const char pad) {
        std::string strpad(1, pad);
        return rpad(str, len, strpad);
    }

    //// 2.3

    std::string formatString(const std::string &str, int len) {
        return formatString(str, len, DEFAULT_PAD);
    }

    std::string formatString(const std::string &str, int len, const std::string &pad) {
        std::string strn;
        if (str.empty() || len < 1) {
            //strn = EMPTY_STRING;
            return strn;
        }
        int strLen = str.length();
        if (strLen == len) {
            strn = str;
            return strn;
        }
        if (strLen < len) {
            // add <pad> to right side
            strn = rpad(str, len, pad);
            
        } else {
            // remove chars from right side
            strn = trunc(str, len, true, true);
        }
        return strn;
    }

    std::string formatString(const std::string &str, int len, const char &pad) {
        std::string strpad(1, pad);
        return formatString(str, len, strpad);
    }

    std::string shortString(const std::string &str, int len) {
        std::string strn = str;
        // trunc with ellipsis ('...') by default
        _trunc(strn, len, true, true); 
        return strn;
    }

    std::string trunc(const std::string &str, int len) {
        std::string strn = str;
        _trunc(strn, len, true, false);
        return strn;
    }

    std::string trunc(const std::string &str, int len, bool trim, bool ellipsis) {
        std::string strn = str;
        _trunc(strn, len, trim, ellipsis);
        return strn;
    }

    void _trunc(std::string &str, int len, bool trim, bool ellipsis) {
        if (str.empty()) {
            return;
        }
        if (len < 1) {
            return;
        }
        if (trim) {
            _trim(str);
        }
        if (str.length() <= len) {
            return;
        }
        if (ellipsis) {
            if (len <= ELLIPSIS_LEN) {
                str = str.substr(0, len);                       // modify
            }
            str = str.substr(0, len - ELLIPSIS_LEN) + ELLIPSIS; // modify
        }
        str = str.substr(0, len);                               // modify
    }

    std::string left(const std::string &str, int len) {
        if (str.empty()) {
            return str;
        }
        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substr(0, len);
    }

    std::string right(const std::string &str, int len) {
        if (str.empty()) {
            return str;
        }
        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substr(strLen - len);
    }

    //// 3.1

    // capitalze

    std::string capitalize(const std::string &str) {
        std::string strn = str;
        _toCapitalize(strn, true, DEFAULT_FORCE_CAPITALIZE); // upper = true
        return strn;
    }

    std::string capitalize(const std::string &str, bool force) {
        std::string strn = str;
        _toCapitalize(strn, true, force); // upper = true
        return strn;
    }

    void _capitalize(std::string &str) {
        _toCapitalize(str, true, DEFAULT_FORCE_CAPITALIZE);  // upper = true
    }

    void _capitalize(std::string &str, bool force) {
        _toCapitalize(str, true, force);  // upper = true
    }

    // decapitalze

    std::string decapitalize(const std::string &str) {
        std::string strn = str;
        _toCapitalize(strn, false, DEFAULT_FORCE_CAPITALIZE); // upper = false
        return strn;
    }

    std::string decapitalize(const std::string &str, bool force) {
        std::string strn = str;
        _toCapitalize(strn, false, force); // upper = false
        return strn;
    }

    void _decapitalize(std::string &str) {
        _toCapitalize(str, false, DEFAULT_FORCE_CAPITALIZE); // upper = false
    }

    void _decapitalize(std::string &str, bool force) {
        _toCapitalize(str, false, force); // upper = false
    }

    // General capitalize/decapitalize: _toCapitalize
    // internal
    void _toCapitalize(std::string &str, bool upper, bool force) {
        if (str.empty()) {
            return;
        }
        if (str.length() > 1 && force) {
            _toCase(str, false);
        }
        str[0] = upper ? toupper(str[0]) : tolower(str[0]);
    }

    ////

    // upper/lower

    std::string upper(const std::string &str) {
        return toUpperCase(str);
    }

    void _upper(std::string &str) {
        _toUpperCase(str);
    }

    std::string lower(const std::string &str) {
        return toLowerCase(str);
    }

    void _lower(std::string &str) {
        _toLowerCase(str);
    }

    // toUpper/LowerCase

    std::string toUpperCase(const std::string &str) {
        std::string strn = str;
        _toCase(strn, true);
        return strn;
    }

    void _toUpperCase(std::string &str) {
        _toCase(str, true);
    }

    std::string toLowerCase(const std::string &str) {
        std::string strn = str;
        _toCase(strn, false);
        return strn;
    }

    void _toLowerCase(std::string &str) {
        _toCase(str, false);
    }

    // toUpper/LowerCase: std::string 
    std::string toCase(const std::string &str, bool upper) {
        std::string strn = str;
        _toCase(strn, upper);
        return strn;
    }
    
    
    // toUpper/LowerCase: std::string 
    void _toCase(std::string &str, bool upper) {
        // TODO: WIN: #include <algorithm>      
        std::transform(str.begin(), str.end(), str.begin(), upper ? ::toupper : ::tolower);

        //int len = str.length();
        //if (len == 0) {
        //  return;
        //}

        //if (upper) {
        //  for (int i = 0; i < len; i++) str[i] = toupper(str[i]);
        //} else {
        //  for (int i = 0; i < len; i++) str[i] = tolower(str[i]);
        //} 

    }
    

    // toUpper/LowerCase: char
    char toCase(char ch, bool upper) {
        return upper ? toupper(ch) : tolower(ch);
    }

    // toCamelCase

    std::string toCamelCase(const std::string &str) {
        std::string strn = str;
        _toCamelCase(strn, "", DEFAULT_CAMEL_CASE_CAPITALIZE);
        return strn;
    }

    std::string toCamelCase(const std::string &str, const std::string &separator) {
        std::string strn = str;
        _toCamelCase(strn, separator, DEFAULT_CAMEL_CASE_CAPITALIZE);
        return strn;
    }

    std::string toCamelCase(const std::string &str, bool capitalize) {
        std::string strn = str;
        _toCamelCase(strn, "", capitalize);
        return strn;
    }

    std::string toCamelCase(const std::string &str, const std::string &separator, bool capitalize) {
        std::string strn = str;
        _toCamelCase(strn, separator, capitalize);
        return strn;
    }

    void _toCamelCase(std::string &str, const std::string &separator, bool capitalize) {
        if (str.empty()) {
            return;
        }

        const std::string type = (capitalize ? "Camel" : "camel");
        const std::string separators = separator;
        const std::string connector = "";

        _toTypeCase(str, type, separators, connector);
    }

    ////

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string &str) {
        std::string strn = str;
        _toSnakeCase(strn, "", false, false);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string &str, const std::string &separator) {
        std::string strn = str;
        _toSnakeCase(strn, separator, false, false);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string &str, bool upper) {
        std::string strn = str;
        _toSnakeCase(strn, "", upper, false);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string &str, const std::string &separator, bool upper) {
        std::string strn = str;
        _toSnakeCase(strn, separator, upper, false);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string &str, const std::string &separator, bool upper, bool trim) {
        std::string strn = str;
        _toSnakeCase(strn, separator, upper, trim);
        return strn;
    }

    // toSnakeCase: snake_case
    void _toSnakeCase(std::string &str, const std::string &separator, bool upper, bool trim) {
        if (str.empty()) {
            return;
        }
        if (trim) {
            _trim(str);
            if (str.empty()) {
                return;
            }
        }
        // TODO: We use separator as connector!
        // Add 'connector' parameter
        const std::string type = (upper ? "SNAKE" : "snake");
        const std::string separators = "";
        const std::string connector = separator;

        _toTypeCase(str, type, separators, connector);
    }

    ////

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string &str) {
        std::string strn = str;
        _toKebabCase(strn, "", false, false);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string &str, const std::string &separator) {
        std::string strn = str;
        _toKebabCase(strn, separator, false, false);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string &str, bool upper) {
        std::string strn = str;
        _toKebabCase(strn, "", upper, false);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string &str, const std::string &separator, bool upper) {
        std::string strn = str;
        _toKebabCase(strn, separator, upper, false);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string &str, const std::string &separator, bool upper, bool trim) {
        std::string strn = str;
        _toKebabCase(strn, separator, upper, trim);
        return strn;
    }

    // toKebabCase: kebab-case
    void _toKebabCase(std::string &str, const std::string &separator, bool upper, bool trim) {
        if (str.empty()) {
            return;
        }
        if (trim) {
            _trim(str);
            if (str.empty()) {
                return;
            }
        }
        // TODO: We use separator as connector!
        // Add 'connector' parameter
        const std::string type = (upper ? "KEBAB" : "kebab");
        const std::string separators = "";
        const std::string connector = separator;

        _toTypeCase(str, type, separators, connector);
    }

    ////

    // Normalize Case Operation by Token position
    int _normalizeCaseOpByToken(int caseOp, bool first) {
        int _caseOp = caseOp;
        if (caseOp == CO_camelCase) {
            // camelCase: [0] -> 'lowercase', [1..n] -> 'PascalCase'
            _caseOp = (first ? CO_lowercase : CO_PascalCase);
        }
        return _caseOp;
    }

    // Normalize Case Operation by Char position
    int _normalizeCaseOpByChar(int caseOp, bool first) {
        // TODO
        return caseOp;
    }

    // https://www.techiedelight.com/append-char-end-string-cpp/
    //
    // caseOp = -1: 'myname': lowercase 
    // caseOp =  1: 'MYNAME': UPPERCASE 
    // caseOp =  2: 'myName': camelCase
    // caseOp =  3: 'MyName': PascalCase
    void _flushOp(std::vector<std::string> *result, std::vector<char> *buf, const int caseOp) {
        int _caseOp = caseOp;
        char ch = 0;
        std::string str;

        for (int i = 0; i < buf->size(); i++) {
            _caseOp = caseOp;
            ch = buf->at(i);

            if (caseOp == CO_PascalCase) {
                if (i == 0) {
                    _caseOp = CO_UPPERCASE; // upper char
                }
                // else {
                //    _caseOp = CO_lowercase; // lower char 
                //}
            }

            // WARNING: We don't use 'caseOp' in this code because it is lower case always
            if (_caseOp == CO_lowercase) {
                ch = tolower(ch);
            } else if (_caseOp == CO_UPPERCASE) {
                ch = toupper(ch);
            }

            //str.append(1, ch);
            str += ch;          // Add char to std::string 
        }

        result->push_back(str); // Add buffer to result
        buf->clear();           // Clear buffer

    }


    // splitOp: separators and A (Upper Char)
    // caseOp = -1: 'myname': lowercase 
    // caseOp =  1: 'MYNAME': UPPERCASE 
    // caseOp =  2: 'myName': camelCase
    // caseOp =  3: 'MyName': PascalCase
    std::vector<std::string> _splitOp(const std::string &str, const std::string &separators, const int caseOp) {

        std::vector<std::string> result;
        if (str.empty() || separators.empty()) {
            result.push_back(str);
            return result;
        }

        //const char* strArr = str.c_str();
        const char* delArr = separators.c_str();

        int strLen = str.length();
        int delLen = separators.length();

        char ch = 0;
        char del = 0;
        bool find = false;
        std::vector<char> buf;
        int _caseOp = caseOp;
        bool first = true;

        for (int i = 0; i < strLen; i++) {

            ch = str[i];
            find = false;

            // Find a separator
            for (int j = 0; j < delLen; j++) {
                del = delArr[j];
                if (del == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    if (isupper(ch)) {
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
                if (buf.size() > 0) {
                    _caseOp = _normalizeCaseOpByToken(caseOp, first);
                    _flushOp(&result, &buf, _caseOp);
                    first = false;
                }

                // Add separator: optinal
                bool include = false;
                if (include || del == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    buf.push_back(ch);
                }

            } else {

                // Add char
                buf.push_back(ch);
            }

        }

        // flush
        if (buf.size() > 0) {
            _caseOp = _normalizeCaseOpByToken(caseOp, first);
            _flushOp(&result, &buf, _caseOp);
        }

        return result;

    }

    // caseOp = -1: 'myname': lowercase 
    // caseOp =  1: 'MYNAME': UPPERCASE 
    // caseOp =  2: 'myName': camelCase
    // caseOp =  3: 'MyName': PascalCase
    void _toCaseOp(std::string &str, const std::string &separators, const std::string &connector, const int caseOp) {
        if (str.empty()) {
            return;
        }
        std::string strn = str; // copy std::string 
        std::vector<std::string> tokens = _splitOp(strn, separators, caseOp);

        //vector<std::string> result;
        bool hasConnector = !connector.empty();
        str.clear();
        for (int i = 0; i < tokens.size(); i++) {
            if (i > 0 && hasConnector) {
                str.append(connector);
            }
            str.append(tokens.at(i));
        }
    }

    void _toTypeCase(std::string &str, const std::string &type) {
        _toTypeCase(str, type, DEFAULT_CASE_SEPARATORS_A, "");
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

    // caseOp = -1: 'myname': lowercase 
    // caseOp =  1: 'MYNAME': UPPERCASE 
    // caseOp =  2: 'myName': camelCase
    // caseOp =  3: 'MyName': PascalCase
    void _toTypeCase(std::string &str, const std::string &type, const std::string &separators, const std::string &connector) {

        if (str.empty()) {
            return;
        }

        int code = toCaseCode(type); 
        if (code <= 0) {
            // Invalid case code
            return;
        }

        //cout << "separators:" << separators << endl;
        //cout << "connector: " << connector << endl;

        // SIMPLE CASE
        if (code == CT_lowercase) {
            _toCase(str, false);                 
            return; // lower case
        } else if (code == CT_UPPERCASE) {
            _toCase(str, true);                  
            return; // UPPER case
        }

        // COMPLEX CASE
        std::string _separators = (separators.empty() ? DEFAULT_CASE_SEPARATORS_A : separators);

        if (code == CT_camelCase) {
            _toCaseOp(str, _separators, connector, CO_camelCase); // lower first char
            return; // camelCase

        } else if (code == CT_PascalCase) {
            _toCaseOp(str, _separators, connector, CO_PascalCase); // upper first char
            return; // CamelCase

        } else if (code == CT_kebab_case) {
            _toCaseOp(str, _separators, (connector.empty() ? KEBAB_CONNECTOR : connector), CO_lowercase);
            return; // kebab-case

        } else if (code == CT_KEBAB_CASE) {
            _toCaseOp(str, _separators, (connector.empty() ? KEBAB_CONNECTOR : connector), CO_UPPERCASE);
            return; // KEBAB-CASE

        } else if (code == CT_Kebab_Case) {
            _toCaseOp(str, _separators, (connector.empty() ? KEBAB_CONNECTOR : connector), CO_PascalCase);
            return; // Kebab_Case

        } else if (code == CT_snake_case) {
            _toCaseOp(str, _separators, (connector.empty() ? SNAKE_CONNECTOR : connector), CO_lowercase);
            return; // snake_case

        } else if (code  == CT_SNAKE_CASE) {
            _toCaseOp(str, _separators, (connector.empty() ? SNAKE_CONNECTOR : connector), CO_UPPERCASE);
            return; // SNAKE_CASE

        } else if (code  == CT_Snake_Case) {
            _toCaseOp(str, _separators, (connector.empty() ? SNAKE_CONNECTOR : connector), CO_PascalCase);
            return; // Snake_Case
        }

        // UNKNOWN CASE: use 'separators', 'connector'
        //_toCaseOp(str, separators, connector, CO_NONE);

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
    int toCaseCode(const std::string &type) {

        if (type == "lower") {               
            return CT_lowercase;         // lowercase

        } else if (type == "upper") {
            return CT_UPPERCASE;         // UPPERCASE

        } else if (type == "camel") {
            return CT_camelCase;         // camelCase

        } else if (type == "Camel" 
            || type == "Pascal" 
            || type == "pascal") {
            return CT_PascalCase;        // PascalCase

        } else if (type == "snake") {
            return CT_snake_case;        // snake_case

        } else if (type == "SNAKE"
            || type == "MACRO" 
            || type == "macro") {
            return CT_SNAKE_CASE;        // SNAKE_CASE

        } else if (type == "Snake") {
            return CT_Snake_Case;        // Snake_Case

        } else if (type == "kebab" 
            || type == "dash"
            || type == "train"
            || type == "lisp") {
            return CT_kebab_case;        // kebab-case

        } else if (type == "KEBAB" 
            || type == "DASH"
            || type == "TRAIN"
            || type == "COBOL" 
            || type == "cobol") {
            return CT_KEBAB_CASE;        // KEBAB-CASE

        } else if ("Kebab"
            || type == "Dash"
            || type == "Train") {
            return CT_Kebab_Case;        // Kebab_Case
        }

        return 0;
        
    }

    std::string toTypeCase(const std::string &str, const std::string &type) {
        std::string strn = str;
        _toTypeCase(strn, type, DEFAULT_CASE_SEPARATORS_A, "");
        return strn;
    }

    std::string toTypeCase(const std::string &str, const std::string &type, const std::string &separators, const std::string &connector) {
        std::string strn = str;        
        _toTypeCase(strn, type, separators, connector);
        return strn;
    }

    // reverse

    std::string reverse(const std::string &str) {
        std::string strn = str;
        _reverse(strn);
        return strn;
    }

    void _reverse(std::string &str) {
        //TODO: WIN: #include <algorithm>
        std::reverse(str.begin(), str.end());
    }

    //// 4.1

    // startsWith, endsWith

    bool startsWith(const std::string &str, const std::string &prefix) {
        return str.size() >= prefix.size() && str.compare(0, prefix.size(), prefix) == 0;
    }

    bool startsWith2(const std::string &str, const std::string &prefix) {
        return str.size() >= prefix.size() && str.substr(0, prefix.size()) == prefix; // Myabe 'compare()' is better
    }

    bool endsWith(const std::string &str, const std::string &suffix) {
        return str.size() >= suffix.size() && str.compare(str.size() - suffix.size(), str.size(), suffix) == 0;
    }

    // hasPrefix, hasSuffix

    bool hasPrefix(const std::string &str, const std::string &prefix) {
        return startsWith(str, prefix);
    }

    bool hasSuffix (const std::string &str, const std::string &suffix) {
        return endsWith(str, suffix);
    }

    // startsWithIgnoreCase, endsWithIgnoreCase

    bool startsWithIgnoreCase(const std::string &str, const std::string &prefix) {
        std::string strn = lower(str);
        std::string prefixn = lower(prefix);
        return startsWith(strn, prefixn);
    }

    bool endsWithIgnoreCase(const std::string &str, const std::string &suffix) {
        std::string strn = lower(str);
        std::string suffixn = lower(suffix);
        return endsWith(strn, suffixn);
    }

    // hasPrefixIgnoreCase, hasSuffixIgnoreCase

    bool hasPrefixIgnoreCase(const std::string &str, const std::string &prefix) {
        return startsWithIgnoreCase(str, prefix);
    }

    bool hasSuffixIgnoreCase(const std::string &str, const std::string &suffix) {
        return endsWithIgnoreCase(str, suffix);
    }

    //// 4.2

    std::string  removePrefix(const std::string &str, const std::string &prefix) {
        std::string strn = str;
        _removePrefix(strn, prefix);
        return strn;
    }

    void _removePrefix(std::string &str, const std::string &prefix) {
        if (!hasPrefix(str, prefix)) {
            return;
        }
        if (str.length() == prefix.length()) {
            str = EMPTY_STRING;
            return;
        }
        str = str.substr(prefix.length());
    }

    //

    std::string  removePrefixes(const std::string &str, const std::vector<std::string> &prefixes) {
        std::string strn = str;
        _removePrefixes(strn, prefixes);
        return strn;
    }

    void _removePrefixes(std::string &str, const std::vector<std::string> &prefixes) {
        if (str.empty() || prefixes.empty()) {
            return;
        }
        std::string prefix;
        for (int i = 0; i < prefixes.size(); i++) {
            prefix = prefixes.at(i);
            if (prefix.empty()) {
                continue;
            }
            // Remove first found prefix
            if (hasPrefix(str, prefix)) {
                _removePrefix(str, prefix);
                return;
            }
        }
    }

    //

    std::string  removeSuffix(const std::string &str, const std::string &suffix) {
        std::string strn = str;
        _removeSuffix(strn, suffix);
        return strn;
    }

    void _removeSuffix(std::string &str, const std::string &suffix) {
        if (!hasSuffix(str, suffix)) {
            return;
        }
        if (str.length() == suffix.length()) {
            str = EMPTY_STRING;
            return;
        }
        str = str.substr(0, str.length() - suffix.length());
    }

     //

    std::string  removeSuffixes(const std::string &str, const std::vector<std::string> &suffixes) {
        std::string strn = str;
        _removeSuffixes(strn, suffixes);
        return strn;
    }

    void _removeSuffixes(std::string &str, const std::vector<std::string> &suffixes) {
        if (str.empty() || suffixes.empty()) {
            return;
        }
        std::string suffix;
        for (int i = 0; i < suffixes.size(); i++) {
            suffix = suffixes.at(i);
            if (suffix.empty()) {
                continue;
            }
            // Remove first found suffix
            if (hasSuffix(str, suffix)) {
                _removeSuffix(str, suffix);
                return;
            }
        }
    }

    //// 4.3

    // isQuoted by default: ', "
    bool isQuoted(const std::string &str) {
        if (str.empty()) {
            return false;
        }
        return isQuoted(str, "'", "'") || isQuoted(str, "\"", "\"");
    }

    // isQuoted
    bool isQuoted(const std::string &str, const std::string &startQuote, const std::string &endQuote) {
        if (str.empty()) {
            return false;
        }
        return startsWith(str, startQuote) && endsWith(str, endQuote);
    }

    // needQuote by default: ', "
    bool needQuote(const std::string &str) {
        return !isQuoted(str);
    }

    // needQuote
    bool needQuote(const std::string &str, const std::string &startQuote, const std::string &endQuote) {
        return !isQuoted(str, startQuote, endQuote);
    }

    // quote by default: "
    std::string quote(const std::string &str) {
        std::string strn = str;
        _quote(strn);
        return strn;
        //return quote(str, "\"", "\"");
    }

    void _quote(std::string &str) {
        _quote(str, "\"", "\"");
    }

    // quote
    std::string quote(const std::string &str, const std::string &startQuote, const std::string &endQuote) {
        std::string strn = str;
        _quote(strn, startQuote, endQuote);
        return strn;
        //return startQuote + str + endQuote;
    }

    void _quote(std::string &str, const std::string &startQuote, const std::string &endQuote) {
        str = startQuote + str + endQuote;
    }

    // unquote by default: ', "
    std::string unquote(const std::string  &str) {
        std::string strn = str;
        _unquote(strn);
        return strn;
        //std::string strc = str;        
        //if (!isQuoted(str, "'", "'") && !isQuoted(str, "\"", "\"")) {
        //    return strc;
        //}
        //return strc.substr(1, strc.length() - 2);
    }

    // unquote by default: ', "
    void _unquote(std::string  &str) {
        if (!isQuoted(str, "'", "'") && !isQuoted(str, "\"", "\"")) {
            return;
        }
        str = str.substr(1, str.length() - 2);
    }

    // unquote
    std::string unquote(const std::string &str, const std::string &startQuote, const std::string &endQuote) {
        std::string strn = str;
        _unquote(strn, startQuote, endQuote);
        return strn;
        //std::string strc = str;
        //if (!isQuoted(str, startQuote, endQuote)) {
        //    return strc;
        //}
        //return strc.substr(startQuote.length(), strc.length() - startQuote.length() - endQuote.length());
    }

    // unquote
    void _unquote(std::string &str, const std::string &startQuote, const std::string &endQuote) {
        if (!isQuoted(str, startQuote, endQuote)) {
            return;
        }
        str = str.substr(startQuote.length(), str.length() - startQuote.length() - endQuote.length());
    }

    //// 4.4

    // isColumnSeparator

    /*
    * Return true if 'ch' is column separator by default
    */
    bool isColumnSeparator(const char ch) {
        return (ch == '\r' || ch == '\n' || ch == '\t');
    }

    // isColumnText

    bool isColumnText(const char* array, int len) {
        if (array == nullptr || len == 0) {
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

    bool isColumnText(const std::string  &str) {
        if (str.empty()) {
            return false; // by default inline (isColumnText = false)
        }
        return isColumnText(str.c_str(), str.length());
    }

    // isLineText

    bool isLineText(const char* array, int len) {
        if (array == nullptr || len == 0) {
            return true; // by default inline
        }
        return !isColumnText(array, len);
    }

    bool isLineText(const std::string  &str) {
        if (str.empty()) {
            return true; // by default inline
        }
        return isLineText(str.c_str(), str.length());
    }

    //// 5.1

    int countChar(const std::string  &str, char ch) {
        if (str.empty()) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str[i] == ch) {
                count++;
            }
        }
        return count;
    }

    int countString(const std::string &str, const std::string &findStr) {
        if (str.empty() || findStr.empty()) {
            return 0;
        }
        size_t pos = 0;
        int count = 0;
        while ((pos = str.find(findStr, pos)) != std::string::npos && pos > 0) {
            pos += findStr.length();
            count++;
        }
        return count;
    }

    int countWord(const std::string &str) {
        std::vector<std::string> words = splitWords(str);
        return words.size();
    }

    int countWord(const std::string &str, const std::string &separators) {
        std::vector<std::string> words = splitWords(str, separators);
        return words.size();
    }

    int countLine(const std::string &str) {
        std::vector<std::string> lines = splitLines(str);
        return lines.size();
    }

    //// 6.1

    std::string replaceAll(const std::string &str, const std::string &s1, const std::string &s2) {
        std::string strn = str;
        _replaceAll(strn, s1, s2);
        return strn;
    }

    void _replaceAll(std::string &str, const std::string &s1, const std::string &s2) {
        if (str.empty()) {
            return;
        }
        size_t start_pos = 0;
        while ((start_pos = str.find(s1, start_pos)) != std::string::npos) {
            str.replace(start_pos, s1.length(), s2);
            start_pos += s2.length(); 
        }
    }

    std::string replaceAll(const std::string &str, const std::vector<std::string> &oldValues, const std::vector<std::string> &newValues) {
        std::string  strn = str;
        _replaceAll(strn, oldValues, newValues);
        return strn;
    }

    void _replaceAll(std::string &str, const std::vector<std::string> &oldValues, const std::vector<std::string> &newValues) {
        if (str.empty()) {
            return;
        }
        int size = std::min(oldValues.size(), newValues.size());
        if (size == 0) {
            return;
        }
        std::string s1;
        std::string s2;
        for (int i = 0; i < size; i++) {
            s1 = oldValues.at(i);
            s2 = newValues.at(i);
            _replaceAll(str, s1, s2);
        }
    }

    //// 7.1

    // TODO: split(str, separator, trim)

    // split: std::string , char

    std::vector<std::string> split(const std::string &str, char separator) {
        std::vector<std::string> result;
        std::stringstream stream(str);
        std::string token;

        while (std::getline(stream, token, separator)) {
            //result.push_back(trim(token));
            result.push_back(token);
        }

        return result;
    }

    // split v2.0: std::string , std::string 
    std::vector<std::string> split2(const std::string &str, const std::string &separator) {
        size_t pos_start = 0;
        size_t pos_end = 0;
        size_t delim_len = separator.length();
        std::string  token;
        std::vector<std::string> res;

        while ((pos_end = str.find(separator, pos_start)) != std::string ::npos) {
            token = str.substr(pos_start, pos_end - pos_start);
            pos_start = pos_end + delim_len;
            res.push_back(token);
        }

        res.push_back(str.substr(pos_start));
        return res;
    }

    // https://stackoverflow.com/questions/49201654/splitting-a-string-with-multiple-delimiters-in-c
    // https://stackoverflow.com/questions/7621727/split-a-string-into-words-by-multiple-delimiters

    std::vector<std::string> split(const std::string &str, const std::string &separators) {
        std::vector<std::string> result;
        if (str.empty() || separators.empty()) {
            result.push_back(str);
            return result;
        }

        size_t beg = 0;
        size_t pos = 0;
        std::string token;

        while ((beg = str.find_first_not_of(separators, pos)) != std::string::npos) {
            pos = str.find_first_of(separators, beg + 1);
            token = str.substr(beg, pos - beg);
            result.push_back(token);
        }
        return result;
    }

    // split v3.0 (split and parse float!)
    // TODO: Rename to 'splitf' or 'splitFloat'
    std::vector<float> split3(const std::string &s, const std::string &separator) {
        size_t pos_start = 0;
        size_t pos_end = 0;
        size_t delim_len = separator.length();
        std::string  token;
        std::vector<float> res;

        while ((pos_end = s.find(separator, pos_start)) != std::string ::npos) {
            token = s.substr(pos_start, pos_end - pos_start);
            pos_start = pos_end + delim_len;
            res.push_back(strtof(token.c_str(), 0));           // PARSE
        }

        res.push_back(strtof(s.substr(pos_start).c_str(), 0)); // PARSE
        return res;
    }

    //

    std::vector<std::string> splitWords(const std::string &str) {
        return splitWords(str, DEFAULT_WORD_SEPARATORS);
    }

    std::vector<std::string> splitWords(const std::string &str, const std::string &separators) {
        std::vector<std::string> result = split(str, separators.empty() ? DEFAULT_WORD_SEPARATORS : separators);
        return result;
    }

    std::vector<std::string> splitLines(const std::string &str) {
        std::vector<std::string> result = split(str, "\r\n");
        return result;
    }

    //// 8.1

    //string toString(const char* array) {
    //    std::string  result(array);
    //    return result;
    //}

    std::string toString(const char array[]) {
        std::string result(array);
        return result;
    }

    std::string toString(const std::vector<std::string> values) {
        return toString(values, "");
    }

    std::string  toString(const std::vector<std::string> values, const std::string &separator) {
        std::string result = "";
        if (values.empty()) {
            return result;
        }
        bool hasSeparator = !separator.empty();
        for (int i = 0; i < values.size(); i++) {
            if (hasSeparator && i > 0) {
                result.append(separator);
            }
            result.append(values.at(i));
        }
        return result;
    }

    std::vector<std::string> toWordList(const std::string &str) {
        // we use 'vector' as 'list'
        return splitWords(str);
    }

    std::vector<std::string> toWordList(const std::string &str, const std::string &separators) {
        // we use 'vector' as 'list'
        return splitWords(str, separators);
    }

    std::vector<std::string> toLineList(const std::string &str) {
        // we use 'vector' as 'list'
        return splitLines(str);
    }

    //// 9.1

    bool isDigit(char ch) {
        return isdigit(ch);
    }

    bool isLetter(char ch) {
        // TODO
        return isalpha(ch);
    }

    bool isAlpha(char ch) {
        return isalpha(ch);
    }

    //

    bool isWhitespace(char ch) {
        return isspace(ch);
    }

    bool isDot(char ch) {
        return ch == '.';
    }

    bool isUnderline(char ch) {
        return ch == '_';
    }

    bool isIdentifier(const std::string &str) {
        if (str.empty()) {
            return false;
        }
        char ch = str[0];
        bool underline = isUnderline(ch);
        if (!isLetter(ch) && !underline) {
            return false;
        }
        int len = str.length();
        if (len == 1) {
            return !underline;
        }

        int underlineCount = 0;
        if (underline) {
            underlineCount++;
        }

        for (int i = 1; i < len; i++) {
            ch = str[i];
            underline = isUnderline(ch);
            if (!isLetter(ch) && !isDigit(ch) && !underline) {
                return false;
            }
            if (underline) {
                underlineCount++;
            }
        }
        return !(underlineCount == len);
    }


    //// 9.2

    bool isUpperCase(char ch) {
        return isupper(ch);
    }

    bool isLowerCase(char ch) {
        return islower(ch);
    }

    //// 11.1

    /**
     * Return max length of std::string  element in array
     */
    int maxLen(const std::string array[], int len) {
        if (len < 1) {
            return 0;
        }
        int result = 0;
        std::string str;
        for (int i = 0; i < len; i++) {
            str = array[i];
            if (str.length() > result) {
                result = str.length();
            }
        }
        return result;
    }

}