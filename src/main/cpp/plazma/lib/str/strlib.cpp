
#include <cstring>
#include <algorithm>

#include <sstream>
#include <cmath>

#include <regex>
#include <iterator>

#include "strlib.h"

// Functions

/////////////////////////////////////////////////////////////////////////////////
//
// 1.1 empty, size
//
// - isEmpty(const string& str)                                                - check empty
// - isBlank(const string& str)                                                - check blank
// - size(const string& str)                                                   - length
//
// - equals(const char* str1, const char* str2)
// - equals(const string& str1, const string& str2)
//
// - equalsContent(const string& str, ???)                                     - N/A
//
// 1.2 
//
// - normalize(const string& str)                                              - trim
// - normalizeBlank(const string& str, bool trimAll, bool trimBlank)
// - normalizeQuoted(const string& str)                                        - trim in quoted value
//
// - defaultIfEmpty(const string& str, const string& defaultStr)               - str.empty() ? defaultStr : str
//
// 1.3 trim (left, right)
// 
// - trim(const string& str)                                                   - all trim (left, right)
// - trim(const string& str, char ch)           
//
// - ltrim(const string& str)                                                  - left trim
// - ltrim(const string& str, char ch)
//
// - rtrim(const string& str)                                                  - right trim
// - rtrim(const string& str, char ch)
//
// - void trimElements(std::vector<std::string>& elements)
//
// 1.4
//
// - findFirstNotOf(const string& str, char ch)
// - findFirstNotOf(const string& str, char ch, int pos)
//
// - findLastNotOf(const string& str, ch)
// - findLastNotOf(const string& str, char ch, int pos)

/////////////////////////////////////////////////////////////////////////////////
// 2.1
//
// - replicate(char ch, int n)                                                 - replicate('a', 3) = "aaa"
// - replicate(const string& str, int n)                                       - replicate("abc", 3) = 'abcabcabc' : repeat (?)
//
// 2.2 lpad, rpad
//
// - lpad(const string& str, int len)                                          - lpad("abc", 5) = "  abc"
// - lpad(const string& str, int len, char pad)                                - lpad("abc", 5, '*') = "**abc"
// - lpad(const string& str, int len, const string& pad)                       - lpad("abc", 5, "*") = "**abc"
//
// - rpad(const string& str, int len)                                          - rpad("abc", 5) = "abc  "
// - rpad(const string& str, int len, char pad)                                - rpad("abc", 5, '*') = "abc**""
// - rpad(const string& str, int len, const string& pad)                       - rpad("abc", 5, "*") = "abc**""
//
// 2.3
//
// - fill(const string& str, int len)                                          - 
// - fill(const string& str, int len, char pad)                                -  
// - fill(const string& str, int len, const string& pad)                       -  
//
// - ellipsis(const string& str, int len)                                      - 
//
// - trunc(const string& str, int len)                                         - 
// - trunc(const string& str, int len, bool ellipsis)                          - 
//
// - left(const string& str, int len)                                          - 
// - right(const string& str, int len)                                         - 
//

/////////////////////////////////////////////////////////////////////////////////
// 3.1
//
// - capitalize(const string& str)		                                       - capitalize("abc") = "Abc"
// - capitalize(const string& str, bool force)		                           - capitalize(("abC") = "Abc"
// - capitalize(const string& str, const string& locale)                       - N/A
// - capitalize(const string& str, bool force, const string& locale)           - N/A
//
// - decapitalize(const string& str)		                                   - decapitalize("Abc") = "abc"
// - decapitalize(const string& str, bool force)	                           - decapitalize("AbC") = "abc"
// - decapitalize(const string& str, const string& locale)                     - N/A
// - decapitalize(const string& str, bool force, const string& locale)         - N/A
//
// - upper(const string& str)			                                       - upper("aBc") = "ABC" 
// - upper(const string& str, const string& locale)                            - N/A
//
// - lower(const string& str)			                                       - lower("Abc") = "abc" 
// - lower(const string& str, const string& locale)                            - N/A
//
// - toUpperCase(const string& str)
// - toUpperCase(const string& str, const string& locale)                      - N/A
//
// - toLowerCase(const string& str)
// - toLowerCase(const string& str, const string& locale)                      - N/A
//
// - toCase(const string& str, bool upper)
// - toCase(const string& str, bool upper, const string& locale)               - N/A
// - toCase(char ch, bool upper);
//
// - toCamelCase(const string& str)		                                       - toCamelCase("property_name") = "PropertyName"
// - toCamelCase(const string& str, const string& separators)                  - 
// - toCamelCase(const string& str, bool capitalize)                           - 
// - toCamelCase(const string& str, const string& separators, bool capitalize) - 
//
// - toSnakeCase(const string& str)		                                       - toSnakeCase("PropertyName") = "property_name"
// - toSnakeCase(const string& str, const string& separators)                  - 
// - toSnakeCase(const string& str, bool upper)                                -  
// - toSnakeCase(const string& str, const string& separators, bool upper)      -  
//
// - toKebabCase(const string& str)		                                       - toKebabCase("PropertyName") = "property-name"
// - toKebabCase(const string& str, const string& separators)                  - 
// - toKebabCase(const string& str, bool upper)                                - 
// - toKebabCase(const string& str, const string& separators, bool upper)      -  
//
// - toTypeCase(const string& str, const string& type)
// - toTypeCase(const string& str, const string& type, const string& separators, const string& connector)
//
// - reverse(const string& str)			                                       - reverse("abc") = "cba"
//

/////////////////////////////////////////////////////////////////////////////////
// 4.1
//
// - startsWith(const string& str, const string& prefix)                       - startsWith("myfile.txt", "myfile") = true
// - startsWithIgnoreCase(const string& str, const string& prefix)             - startsWithIgnoreCase("myfile.txt", "MyFile") = true
//
// - endsWith(const string& str, const string& suffix)                         - endsWith("myfile.txt", ".txt") = true
// - endsWithIgnoreCase(const string& str, const string& suffix)               - endsWithIgnoreCase("myfile.txt", ".TxT") = true
//
// - hasPrefix(const string& str, const string& prefix)                        - [alias]: hasPrefix("myfile.txt", "myfile") = true
// - hasPrefixIgnoreCase(const string& str, const string& prefix)              - [alias]: hasPrefixIgnoreCase("myfile.txt", "MyFile") = true
//
// - hasSuffix(const string& str, const string& suffix)                        - [alias]: hasSuffix("myfile.txt", ".txt") = true
// - hasSuffixIgnoreCase(const string& str, const string& suffix)              - [alias]: hasSuffixIgnoreCase("myfile.txt", ".TxT") = true
//
// 4.2
// 
// - removePrefix(const string& str, const string& prefix)                     -
// - removePrefixes(const string& str, const vector<string> &prefixes)         - 
//
// - removeSuffix(const string& str, const string& suffix)                     -
// - removeSuffixes(const string& str, const vector<string> &suffixes)         - 
//
// 4.3
// 
// - isQuoted(const string& str)                                               -
// - isQuoted(const string& str, const string& start, const string& end)
//
// - needQuote(const string& str)                                              -
// - needQuote(const string& str, const string& start, const string& end)
//
// - quote(const string& str)                                                  -
// - quote(const string& str, const string& start, const string& end)
//
// - unquote(const string& str)                                                -
// - unquote(const string& str, const string& start, const string& end)
//
// 4.4
// 
// - isColumnSeparator(char ch)                                                -
// - isColumnText(const char* array, int len)                                  -
// - isColumnText(const string& str)                                           -
// - isLineText(const char* array, int len)                                    -
// - isLineText(const string& str)                                             -

/////////////////////////////////////////////////////////////////////////////////
// 5.1
//
// - countChars(const string& str, char ch)
// - countStrings(const string& str, const string& substr)
// - countWords(const string& str)
// - countWords(const string& str, const string& separators)
// - countLines(const string& str)

/////////////////////////////////////////////////////////////////////////////////
// 6.1
//  
// - replaceAll(const string& str, const string& s1, const string& s2)
// - replaceAll(const string& str, const vector<string>& oldValues, const vector<string>& newValues)
    
/////////////////////////////////////////////////////////////////////////////////
// 7.1
//      
// - split(const string& str)
// - split(const string& str, char separator)
// - split(const string& str, const string& separator)
// 
// - splitBySeparator(const std::string& str, char separator)
// - splitBySeparator(const std::string& str, char separator, bool preserveAll)
//
// - splitBySeparator(const std::string& str, const std::string& separator)
// - splitBySeparator(const std::string& str, const std::string& separator, bool preserveAll)
//
// - splitBySeparators(const std::string& str, const std::string& separators)
// - splitBySeparators(const std::string& str, const std::string& separators, bool preserveAll)
//
// - splitWords(const string& str)
// - splitWords(const string& str, const string& separators)
// - splitLines(const string& str)
//
// - tokenizeBySeparator(const std::string& str, char separator)
// - tokenizeBySeparator(const std::string& str, char separator, bool includeAll, bool preserveAll)
//
// - tokenizeBySeparator(const std::string& str, const std::string& separator);
// - tokenizeBySeparator(const std::string& str, const std::string& separator, bool includeAll, bool preserveAll)
//
// - tokenizeBySeparators(const std::string& str, const std::string& separators)
// - tokenizeBySeparators(const std::string& str, const std::string& separators, bool includeAll, bool preserveAll)

/////////////////////////////////////////////////////////////////////////////////
// 8.1
//      
// - toString(const char* array)
// - toString(const vector<string> values)
// - toString(const vector<string> values, const string& separator)
//
// - toWordArray(const string& str)                                            - N/A ???
// - toWordArray(const string& str, const string& separators)                  - N/A ???
//
// - toLineArray(const string& str)                                            - N/A ???
//
// 8.2
//
// - toWordList(const string& str)                                             - 
// - toWordList(const string& str, const string& separators)                   - 
//
// - toLineList(const string& str)                                             - 
// - toList(array)                                                             - N/A ???    

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
// - isIdentifier(const string& str)
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

    /*
     isEmpty(null) =  true
     isEmpty("")    = true     
     isEmpty("   ") = false
    */
    bool isEmpty(const char* str) {
        return str == nullptr || str[0] == '\0';
    }

    /*
     isEmpty("")    = true
     isEmpty("   ") = false     
     isEmpty("abc") = false
    */
    bool isEmpty(const std::string& str) {
        return str.empty();
    }

    /*
    bool isBlank(const char* str) {
        if (isEmpty(str)) {
            return true;
        }
        // TODO: Optimize
        int len = strlen(str);
        int len2 = DEFAULT_TRIM.length();
        const char* terms = DEFAULT_TRIM.c_str();
        char ch = 0;
        for (int i = 0; i < len; i++) {
            ch = str[i];
            for (int j = 0; j < len2; j++) {
                if (ch != terms[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    */

    /*
     isBlank("abc")    = false
     isBlank(" abc ")  = false
     isBlank("     ")  = true
     isBlank("\t")     = true
     isBlank("\t ")    = true
     isBlank(" \r\n")  = true
    */
    bool isBlank(const std::string& str) {
        if (str.empty()) {
            return true;
        }
        return str.find_first_not_of(TRIM_CHARS) == std::string::npos; // " \t\n\r\f\v"
    }

    //

    // [char]
    int size(const char* str) {
        return str == nullptr ? 0 : strlen(str); // str.length()
    }

    int size(const std::string& str) {
        return str.size(); // str.length()
    }

    ////

    // [char]
    bool equals(const char* str1, const char* str2) {
        if (str1 == str2) {
            return true;
        }
        if (str1 == nullptr || str2 == nullptr) {
            return false;
        }
        return strcmp(str1, str2) == 0;
    }

    bool equals(const std::string& str1, const std::string& str2) {
        return str1 == str2;
    }

    bool equalsContent(const std::string& str1, const char* str2) {
        if (str2 == nullptr) {
            return false;
        }
        if (str1.empty()) {
            return isEmpty(str2);
        }
        //if (str2 == nullptr) {
        //    return false;
        //}
        int len1 = str1.length();
        int len2 = strlen(str2);
        if (len1 != len2) {
            return false;
        }
        for (int i = 0; i < len1; i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
        //return str1.compare(str2);
    }

    //// 1.2

    /*
     Return normalized string

     normalize("abc")       = "abc" 
     normalize(" abc ")     = "abc"
     normalize(" abc \r\n") = "abc"
     normalize("     ")     = ""
     normalize("")          = ""
    */
    std::string normalize(const std::string& str) {
        return trim(str);
    }

    void _normalize(std::string& str) {
        _trim(str);
    }

    /*
     Return normalized string with additional options: trimAll and trimBlank

     normalizeBlank("abc", false, false)   = "abc" 
     normalizeBlank(" abc ", false, false) = " abc "
     normalizeBlank(" abc ", true, false)  = "abc"
     normalizeBlank(" abc ", true, true)   = "abc"
     normalizeBlank(" abc ", false, true)  = " abc "

     normalizeBlank("     ", false, false) = "     "
     normalizeBlank("     ", true, false)  = ""
     normalizeBlank("     ", true, true)   = ""
     normalizeBlank("     ", false, true)  = ""


     Different cases (trimAll=false, trimBlank=true):
     normalizeBlank(" abc ", false, true)  = " abc "
     normalizeBlank("     ", false, true)  = ""

     In this cases:
     " abc " - as is
     ""      - "" - trim blank

    */
    std::string normalizeBlank(const std::string& str, bool trimAll, bool trimBlank) {
        std::string strn = str;
        _normalizeBlank(strn, trimAll, trimBlank);
        return strn;
    }

    void _normalizeBlank(std::string& str, bool trimAll, bool trimBlank) {
        if (str.empty()) {
            return;
        }
        
        // 'trimAll' flag overrides 'trimBlank' flag for each type
        // If 'trimAll' is true we will trim all text
        // 'trimBlank' for non normalize text only

        if (!trimAll && !trimBlank) {
            return;
        }

        if (!trimAll) {

            // trimBlank=true, because we have condition (!trimAll && !trimBlank) before
            // Analyze blank
            if (isBlank(str)) {
                str.clear();
            }
            return;
        }

        _normalize(str);
    }

    /*
     Return normalized string in quoted value

     normalizeQuoted("abc")      = "abc" 
     normalizeQuoted(" abc ")    = "abc" 

     normalizeQuoted("\"abc\"")  = "\"abc\"" 
     normalizeQuoted("\" abc \"") = "\"abc\"" 

     normalizeQuoted("\'abc\'")  = "\'abc\'" 
     normalizeQuoted("\' abc \'") = "\'abc\'" 

    */
    std::string normalizeQuoted(const std::string& str) {
        std::string strn = str;
        _normalizeQuoted(strn);
        return strn;
    }

    void _normalizeQuoted(std::string& str) {
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
     Return 'defaultStr' if 'str' is empty
     defaultIfEmpty("", "")     = ""
     defaultIfEmpty("", "-")    = "-"
     defaultIfEmpty("abc", "-") = "abc"
    */
    std::string defaultIfEmpty(const std::string& str, const std::string& defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

    //// 1.3

    // trim (left, right)

    /*
     Trim a std::string  by ' '
     trim("  abc  ") = "abc"
     trim("  abc")   = "abc"
     trim("abc  ")   = "abc"
    */
    std::string trim(const std::string& str) {
        std::string strn = str;
        _trim(strn);
        return strn;
    }

    /*
     Trim a std::string  by 'ch'
     trim("--abc--", '-') = "abc"
     trim("--abc", '-')   = "abc"
     trim("abc--", '-')   = "abc"
    */
    std::string trim(const std::string& str, char ch) {
        std::string strn = str;
        _trim(strn, ch);
        return strn;
    }

    std::string trim(const std::string& str, const char* terms) {
        std::string strn = str;
        if (isEmpty(terms)) {
            return strn;
        }
        _trim(strn, terms);
        return strn;
    }

    void _trim(std::string& str) {
        _trim(str, TRIM_CHARS); // // " \t\n\r\f\v"
    }

    void _trim(std::string& str, char ch) {
        _ltrim(str, ch);
        _rtrim(str, ch);
    }

    void _trim(std::string& str, const char* terms) {
        if (isEmpty(terms)) {
            return;
        }
        _ltrim(str, terms);
        _rtrim(str, terms);
    }

    // trimSpace

    std::string trimSpace(const std::string& str) {
        std::string strn = str;
        _trimSpace(strn);
        return strn;
    }

    void _trimSpace(std::string& str) {
        _trim(str, SPACE_CHAR); // ' '
    }

    // trimAll

    std::string trimAll(const std::string& str) {
        std::string strn = str;
        _trimAll(strn);
        return strn;
    }

    void _trimAll(std::string& str) {
        _trim(str, TRIM_CHARS); // " \t\n\r\f\v"
    }

    // ltrim

    std::string ltrim(const std::string& str) {
        std::string strn = str;
        _ltrim(strn);
        return strn;
    }

    std::string ltrim(const std::string& str, char ch) {
        std::string strn = str;
        _ltrim(strn, ch);
        return strn;
    }

    std::string ltrim(const std::string& str, const char* terms) {
        std::string strn = str;
        if (isEmpty(terms)) {
            return strn;
        }
        _ltrim(strn, terms);
        return strn;
    }

    void _ltrim(std::string& str) {
        _ltrim(str, TRIM_CHARS); // " \t\n\r\f\v"
    }

    void _ltrim(std::string& str, char ch) {
        str.erase(0, str.find_first_not_of(ch)); // prefixing spaces
    }

    void _ltrim(std::string& str, const char* terms) {
        if (isEmpty(terms)) {
            return;
        }
        str.erase(0, str.find_first_not_of(terms)); // prefixing spaces
    }


    // rtrim

    std::string rtrim(const std::string& str) {
        std::string strn = str;
        _rtrim(strn);
        return strn;
    }

    std::string rtrim(const std::string& str, char ch) {
        std::string strn = str;
        _rtrim(strn, ch);
        return strn;
    }

    std::string rtrim(const std::string& str, const char* terms) {
        std::string strn = str;
        if (isEmpty(terms)) {
            return strn;
        }
        _rtrim(strn, terms);
        return strn;
    }

    void _rtrim(std::string& str) {
        _rtrim(str, TRIM_CHARS); // " \t\n\r\f\v"
    }

    void _rtrim(std::string& str, char ch) {
        str.erase(str.find_last_not_of(ch) + 1); // surfixing spaces
    }

    void _rtrim(std::string& str, const char* terms) {
        if (isEmpty(terms)) {
            return;
        }
        str.erase(str.find_last_not_of(terms) + 1); // surfixing spaces
    }


    //// 1.4 

    // contains

    bool contains(const std::string& str, char ch) {
        if (str.empty()) {
            return false;
        }
        return str.find(ch) != std::string::npos;
    }

    bool contains(const std::string& str, const std::string& substr) {
        if (str.empty()) {
            return false;
        }
        return (str.find(substr) != std::string::npos);
    }

    // find

    int find(const std::string& str, char ch) {
        return findFirstOf(str, ch);
    }

    int find(const std::string& str, char ch, int pos) {
        return findFirstOf(str, ch, pos);
    }

    // findFirst

    int findFirst(const std::string& str, char ch) {
        return findFirstOf(str, ch);
    }

    int findFirst(const std::string& str, char ch, int pos) {
        return findFirstOf(str, ch, pos);
    }

    // findLast

    int findLast(const std::string& str, char ch) {
        return findLastOf(str, ch);
    }

    int findLast(const std::string& str, char ch, int pos) {
        return findLastOf(str, ch, pos);
    }

    // findFirstOf

    int findFirstOf(const std::string& str, char ch) {
        if (str.empty()) {
            return -1;
        }
        return str.find_first_of(ch);
    }

    int findFirstOf(const std::string& str, char ch, int pos) {
        if (str.empty() || pos < 0 || pos >= str.length()) { // find_first_of - OK
            return -1;
        }
        return str.find_first_of(ch, pos);
    }

    // findLastOf

    int findLastOf(const std::string& str, char ch) {
        if (str.empty()) {
            return -1;
        }
        return str.find_last_of(ch);
    }

    int findLastOf(const std::string& str, char ch, int pos) {
        if (str.empty() || pos < 0 || pos >= str.length()) { // find_last_of - FAIL
            return -1;
        }
        return str.find_last_of(ch, pos);
    }

    // findFirstNotOf

    int findFirstNotOf(const std::string& str, char ch) {
        if (str.empty()) {
            return -1;
        }
        return str.find_first_not_of(ch);
    }

    int findFirstNotOf(const std::string& str, char ch, int pos) {
        if (str.empty() || pos < 0 || pos >= str.length()) { // find_first_not_of - OK
            return -1;
        }
        return str.find_first_not_of(ch, pos);
    }

    // findLastNotOf

    int findLastNotOf(const std::string& str, char ch) {
        if (str.empty()) {
            return -1;
        }
        return str.find_last_not_of(ch);
    }

    int findLastNotOf(const std::string& str, char ch, int pos) {
        if (str.empty() || pos < 0 || pos >= str.length()) { // find_last_not_of - FAIL
            return -1;
        }
        return str.find_last_not_of(ch, pos);
    }

    //// 2.1

    std::string replicate(char ch, int n) {
        if (n < 1) {
            return EMPTY_STRING;
        }
        std::string result(n, ch);
        return result;
    }

    std::string replicate(const std::string& str, int n) {
        if (str.empty() || n < 1) {
            return EMPTY_STRING;
        }

        std::string result;
        result.reserve(str.length() * n); // avoid repeated reallocation
        for (int i = 0; i < n; i++) {
            result += str;
        }
        return result;
    }

    //// 2.2 lpad, rpad

    // lpad

    std::string lpad(const std::string& str, int len) {
        return lpad(str, len, DEFAULT_PAD);
    }

    std::string lpad(const std::string& str, int len, char pad) {
        if (len < 1 || pad == 0) { // isEmpty(pad): no padding
            return str;
        }

        int strLen = str.length();
        if (len <= strLen) {
            return str;
        }

        int padCount = len - strLen;
        return replicate(pad, padCount) + str;
    }

    //

    std::string lpad(const std::string& str, int len, const std::string& pad) {
        if (len < 1 || pad.empty()) { // isEmpty(pad): no padding
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

        std::string fill = replicate(pad, padCount);
        if (fill.length() > fillLen) {
            return fill.substr(0, fillLen) + str;            
        } else {
            return fill + str;
        }
    }

    // rpad

    std::string rpad(const std::string& str, int len) {
        return rpad(str, len, DEFAULT_PAD);
    }

    std::string rpad(const std::string& str, int len, char pad) {
        if (len < 1 || pad == 0) {
            return str;
        }

        int strLen = str.length();
        if (len <= strLen) {
            return str;
        }

        int padCount = len - strLen;
        return str + replicate(pad, padCount);
    }

    //

    std::string rpad(const std::string& str, int len, const std::string& pad) {
        if (len < 1 || pad.empty()) {
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
        std::string fill = replicate(pad, padCount);
        
        if (fill.length() > fillLen) {
            return str + fill.substr(0, fillLen);            
        } else {
            return str + fill;
        }        
    }

    //// 2.3

    std::string fill(const std::string& str, int len) {
        return fill(str, len, DEFAULT_PAD);
    }

    std::string fill(const std::string& str, int len, char pad) {
        std::string strpad(1, pad); // TODO: Optimize
        return fill(str, len, strpad);
    }

    std::string fill(const std::string& str, int len, const std::string& pad) {
        std::string strn;

        // hard format: <=len or empty
        if (len < 1) {
            return strn;
        }
        int strLen = str.length();
        if (strLen == len) {
            strn = str;
            return strn;
        }

        // hard format: pad or trunc
        if (strLen < len) {
            // add <pad> to right side
            strn = rpad(str, len, pad);
            
        } else {
            // remove chars from right side
            strn = trunc(str, len, true); // ellipsis
        }
        return strn;
    }

    /*
    Ellipsis string
    */

    std::string ellipsis(const std::string& str, int len) {
        std::string strn = str;
        _trunc(strn, len, true); // ellipsis
        return strn;
    }

    std::string trunc(const std::string& str, int len) {
        std::string strn = str;
        _trunc(strn, len, false);
        return strn;
    }

    std::string trunc(const std::string& str, int len, bool ellipsis) {
        std::string strn = str;
        _trunc(strn, len, ellipsis);
        return strn;
    }

    void _trunc(std::string& str, int len, bool ellipsis) {
        if (str.empty()) {
            return;
        }

        // soft format
        if (len < 1) {
            return;
        }

        if (str.length() <= len) {
            return;
        }

        if (ellipsis) {
            if (len <= ELLIPSIS_LEN) {
                str = str.substr(0, len);                       // modify
                return;
            }
            str = str.substr(0, len - ELLIPSIS_LEN) + ELLIPSIS; // modify
            return;
        }
        str = str.substr(0, len);                               // modify
    }

    std::string left(const std::string& str, int len) {
        if (str.empty()) {
            return str;
        }

        // hard format: <=len or empty
        if (len < 1) {
            return EMPTY_STRING;
        }

        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substr(0, len);
    }

    std::string right(const std::string& str, int len) {
        if (str.empty()) {
            return str;
        }

        // hard format: <=len or empty
        if (len < 1) {
            return EMPTY_STRING;
        }

        int strLen = str.length();
        if (strLen <= len) {
            return str;
        }
        return str.substr(strLen - len);
    }

    //// 3.1

    // capitalze

    std::string capitalize(const std::string& str) {
        std::string strn = str;
        _toCapitalize(strn, true, DEFAULT_FORCE_CAPITALIZE); // upper = true
        return strn;
    }

    std::string capitalize(const std::string& str, bool forceRest) {
        std::string strn = str;
        _toCapitalize(strn, true, forceRest); // upper = true
        return strn;
    }

    void _capitalize(std::string& str) {
        _toCapitalize(str, true, DEFAULT_FORCE_CAPITALIZE);  // upper = true
    }

    void _capitalize(std::string& str, bool forceRest) {
        _toCapitalize(str, true, forceRest);  // upper = true
    }

    // decapitalze

    std::string decapitalize(const std::string& str) {
        std::string strn = str;
        _toCapitalize(strn, false, DEFAULT_FORCE_CAPITALIZE); // upper = false
        return strn;
    }

    std::string decapitalize(const std::string& str, bool forceRest) {
        std::string strn = str;
        _toCapitalize(strn, false, forceRest); // upper = false
        return strn;
    }

    void _decapitalize(std::string& str) {
        _toCapitalize(str, false, DEFAULT_FORCE_CAPITALIZE); // upper = false
    }

    void _decapitalize(std::string& str, bool forceRest) {
        _toCapitalize(str, false, forceRest); // upper = false
    }

    // General capitalize/decapitalize: _toCapitalize
    // internal
    void _toCapitalize(std::string& str, bool upper, bool forceRest) {
        if (str.empty()) {
            return;
        }
        if (str.length() > 1 && forceRest) {
            _toCase(str, !upper);
        }
        str[0] = upper ? toupper(str[0]) : tolower(str[0]);
    }

    ////

    // upper/lower

    std::string upper(const std::string& str) {
        return toUpperCase(str);
    }

    void _upper(std::string& str) {
        _toUpperCase(str);
    }

    std::string lower(const std::string& str) {
        return toLowerCase(str);
    }

    void _lower(std::string& str) {
        _toLowerCase(str);
    }

    // toUpper/LowerCase

    std::string toUpperCase(const std::string& str) {
        std::string strn = str;
        _toCase(strn, true);
        return strn;
    }

    void _toUpperCase(std::string& str) {
        _toCase(str, true);
    }

    std::string toLowerCase(const std::string& str) {
        std::string strn = str;
        _toCase(strn, false);
        return strn;
    }

    void _toLowerCase(std::string& str) {
        _toCase(str, false);
    }

    // toUpper/LowerCase: std::string 
    std::string toCase(const std::string& str, bool upper) {
        std::string strn = str;
        _toCase(strn, upper);
        return strn;
    }
    
    // toUpper/LowerCase: std::string 
    void _toCase(std::string& str, bool upper) {
        std::transform(str.begin(), str.end(), str.begin(), upper ? ::toupper : ::tolower);
    }
    
    // toUpper/LowerCase: char
    char toCase(char ch, bool upper) {
        return upper ? toupper(ch) : tolower(ch);
    }

    ////

    std::string toIgnoreCase(const std::string& str) {
        return toLowerCase(str);
    }

    ////

    // toCamelCase
    std::string toCamelCase(const std::string& str) {
        std::string strn = str;
        _toCamelCase(strn, "", DEFAULT_CAMEL_CASE_CAPITALIZE);
        return strn;
    }

    std::string toCamelCase(const std::string& str, const std::string& separators) {
        std::string strn = str;
        _toCamelCase(strn, separators, DEFAULT_CAMEL_CASE_CAPITALIZE);
        return strn;
    }

    std::string toCamelCase(const std::string& str, bool capitalize) {
        std::string strn = str;
        _toCamelCase(strn, "", capitalize);
        return strn;
    }

    std::string toCamelCase(const std::string& str, const std::string& separators, bool capitalize) {
        std::string strn = str;
        _toCamelCase(strn, separators, capitalize);
        return strn;
    }

    void _toCamelCase(std::string& str, const std::string& separators, bool capitalize) {
        _toTypeCase(str, (capitalize ? "Camel" : "camel"), separators, "");
    }

    ////

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string& str) {
        std::string strn = str;
        _toSnakeCase(strn, "", false);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string& str, const std::string& separators) {
        std::string strn = str;
        _toSnakeCase(strn, separators, false);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string& str, bool upper) {
        std::string strn = str;
        _toSnakeCase(strn, "", upper);
        return strn;
    }

    // toSnakeCase: snake_case
    std::string toSnakeCase(const std::string& str, const std::string& separators, bool upper) {
        std::string strn = str;
        _toSnakeCase(strn, separators, upper);
        return strn;
    }

    // toSnakeCase: snake_case
    void _toSnakeCase(std::string& str, const std::string& separators, bool upper) {
        _toTypeCase(str, (upper ? "SNAKE" : "snake"), separators, SNAKE_CONNECTOR);
    }

    ////

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string& str) {
        std::string strn = str;
        _toKebabCase(strn, "", false);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string& str, const std::string& separators) {
        std::string strn = str;
        _toKebabCase(strn, separators, false);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string& str, bool upper) {
        std::string strn = str;
        _toKebabCase(strn, "", upper);
        return strn;
    }

    // toKebabCase: kebab-case
    std::string toKebabCase(const std::string& str, const std::string& separators, bool upper) {
        std::string strn = str;
        _toKebabCase(strn, separators, upper);
        return strn;
    }

    // toKebabCase: kebab-case
    void _toKebabCase(std::string& str, const std::string& separators, bool upper) {
        _toTypeCase(str, (upper ? "KEBAB" : "kebab"), separators, KEBAB_CONNECTOR);
    }

    //////////////////////////////////////////////////////////////////
    // Internal
    //////////////////////////////////////////////////////////////////

    // Transform token
    void _transformToken(std::string& token, int caseOp, bool first) {
        if (token.empty()) {
            return;
        }
        if (caseOp == CO_LOWER_CHAR) {          // camelCase
            if (first) {
                token[0] = tolower(token[0]);   // lower char (first)
            } else {
                token[0] = toupper(token[0]);   // upper char (first)
            }
        } else if (caseOp == CO_UPPER_CHAR) {   // CamelCase, PascalCase
            token[0] = toupper(token[0]);       // UPPER char (first)
        } else if (caseOp == CO_LOWER) {  
            std::transform(token.begin(), token.end(), token.begin(), ::tolower); // lower case
        } else if (caseOp == CO_UPPER) {
            std::transform(token.begin(), token.end(), token.begin(), ::toupper); // UPPER case
        }
    }    

    // Transformation tokens by caseOp
    void _transformOp(std::vector<std::string>& tokens, int caseOp) {
        if (tokens.empty()) {
            return;
        }

        // No transformation
        if (caseOp == CO_NONE){
            return;
        }

        for (int i = 0; i < tokens.size(); i++) {
            _transformToken(tokens[i], caseOp, i == 0);
        }

    }        

    // splitOp: separators and A (Upper Char)
    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR
    std::vector<std::string> _splitOp(const std::string& str, const std::string& separators, const int caseOp) {

        std::vector<std::string> result;
        if (str.empty() || separators.empty()) {
            result.push_back(str);
            return result;
        }

        int strLen = str.length();
        int sepLen = separators.length();

        char ch = 0;
        char separator = 0;
        bool found = false;

        int pos = 0;
        int end = 0;
        int i = 0;
        int j = 0;

        while (i < strLen) {

            ch = str[i];
            found = false;
            j = 0;

            // Find a separator
            while (j < sepLen) {
                separator = separators[j];
                if (separator == 'A') { // TODO: 'A' is special marker for check 'Upper Char'
                    if (isupper(ch)) {
                        found = true;
                        break;
                    }
                } else if (ch == separator) {
                    found = true;
                    break;
                }
                j++;
            }

            if (found) {

                end = i;

                if (pos < end) {
                    result.push_back(str.substr(pos, end - pos));
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
            result.push_back(str.substr(pos));
        }           

        return result;

    }

    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR
    void _toCaseOp(std::string& str, const std::string& separators, const std::string& connector, const int caseOp) {
        if (isBlank(str)) {
            return;
        }
        std::string strn = str;
        std::vector<std::string> tokens = _splitOp(strn, separators, caseOp);
        _transformOp(tokens, caseOp);

        bool hasConnector = !connector.empty();
        str.clear();
        for (int i = 0; i < tokens.size(); i++) {
            if (i > 0 && hasConnector) {
                str.append(connector);
            }
            str.append(tokens.at(i));
        }
    }

    void _toTypeCase(std::string& str, const std::string& type) {
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

    // caseOp =  1: 'myname': LOWER
    // caseOp =  2: 'MYNAME': UPPER
    // caseOp =  3: 'myName': LOWER_CHAR
    // caseOp =  4: 'MyName': UPPER_CHAR
    void _toTypeCase(std::string& str, const std::string& type, const std::string& separators, const std::string& connector) {

        if (str.empty()) {
            return;
        }

        int code = getCaseCode(type); 
        if (code == 0) {
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
        std::string _connector = connector;
        if (code == CT_kebab_case || code == CT_KEBAB_CASE || code == CT_Kebab_Case) {
            _connector = (connector.empty() ? KEBAB_CONNECTOR :connector);
        } else if (code == CT_snake_case || code  == CT_SNAKE_CASE || code == CT_Snake_Case) {
            _connector = (connector.empty() ? SNAKE_CONNECTOR : connector);
        }
            
        int _caseOp = getCaseOp(code);

        return _toCaseOp(str, _separators, _connector, _caseOp);

        // UNKNOWN CASE: use 'separators', 'connector'
        //_toCaseOp(str, separators, connector, CO_NONE);

    }

    //

    // Return case op by case code
    int getCaseOp(int code) {
        
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
    int getCaseCode(const std::string& type) {

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

        } else if (type == "Kebab"
            || type == "Dash"
            || type == "Train") {
            return CT_Kebab_Case;        // Kebab_Case
        }

        return CT_NONE;        
    }

    std::string toTypeCase(const std::string& str, const std::string& type) {
        std::string strn = str;
        _toTypeCase(strn, type, DEFAULT_CASE_SEPARATORS_A, "");
        return strn;
    }

    std::string toTypeCase(const std::string& str, const std::string& type, const std::string& separators, const std::string& connector) {
        std::string strn = str;        
        _toTypeCase(strn, type, separators, connector);
        return strn;
    }

    // reverse

    std::string reverse(const std::string& str) {
        std::string strn = str;
        _reverse(strn);
        return strn;
    }

    void _reverse(std::string& str) {
        //TODO: WIN: #include <algorithm>
        std::reverse(str.begin(), str.end());
    }

    //// 4.1

    // startsWith, startsWithIgnoreCase

    bool startsWith(const std::string& str, const std::string& prefix) {
        if (str.empty() || prefix.empty()) {
            return false;
        }
        return str.size() >= prefix.size() && str.compare(0, prefix.size(), prefix) == 0;
    }

    bool startsWithIgnoreCase(const std::string& str, const std::string& prefix) {
        if (str.empty() || prefix.empty()) {
            return false;
        }
        std::string strn = toIgnoreCase(str);
        std::string prefixn = toIgnoreCase(prefix);
        return startsWith(strn, prefixn);
    }

    //bool startsWith2(const std::string& str, const std::string& prefix) {
    //    if (str.empty() || prefix.empty()) {
    //        return false;
    //    }
    //    return str.size() >= prefix.size() && str.substr(0, prefix.size()) == prefix; // Myabe 'compare()' is better
    //}

    // endsWith, endsWithIgnoreCase

    bool endsWith(const std::string& str, const std::string& suffix) {
        if (str.empty() || suffix.empty()) {
            return false;
        }
        return str.size() >= suffix.size() && str.compare(str.size() - suffix.size(), str.size(), suffix) == 0;
    }

    bool endsWithIgnoreCase(const std::string& str, const std::string& suffix) {
        if (str.empty() || suffix.empty()) {
            return false;
        }
        std::string strn = toIgnoreCase(str);
        std::string suffixn = toIgnoreCase(suffix);
        return endsWith(strn, suffixn);
    }

    // hasPrefix, hasPrefixIgnoreCase

    bool hasPrefix(const std::string& str, const std::string& prefix) {
        return startsWith(str, prefix);
    }

    bool hasPrefixIgnoreCase(const std::string& str, const std::string& prefix) {
        return startsWithIgnoreCase(str, prefix);
    }

    // hasSuffix, hasSuffixIgnoreCase

    bool hasSuffix (const std::string& str, const std::string& suffix) {
        return endsWith(str, suffix);
    }

    bool hasSuffixIgnoreCase(const std::string& str, const std::string& suffix) {
        return endsWithIgnoreCase(str, suffix);
    }

    //// 4.2

    std::string removePrefix(const std::string& str, const std::string& prefix) {
        std::string strn = str;
        _removePrefix(strn, prefix);
        return strn;
    }

    void _removePrefix(std::string& str, const std::string& prefix) {
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

    std::string removePrefixes(const std::string& str, const std::vector<std::string> &prefixes) {
        std::string strn = str;
        _removePrefixes(strn, prefixes);
        return strn;
    }

    void _removePrefixes(std::string& str, const std::vector<std::string> &prefixes) {
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

    std::string removeSuffix(const std::string& str, const std::string& suffix) {
        std::string strn = str;
        _removeSuffix(strn, suffix);
        return strn;
    }

    void _removeSuffix(std::string& str, const std::string& suffix) {
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

    std::string removeSuffixes(const std::string& str, const std::vector<std::string> &suffixes) {
        std::string strn = str;
        _removeSuffixes(strn, suffixes);
        return strn;
    }

    void _removeSuffixes(std::string& str, const std::vector<std::string> &suffixes) {
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
    bool isQuoted(const std::string& str) {
        if (str.empty()) {
            return false;
        }
        // by default: '', ""
        return isQuoted(str, "'", "'") || isQuoted(str, "\"", "\"");
    }

    // isQuoted
    bool isQuoted(const std::string& str, const std::string& start, const std::string& end) {
        if (str.empty()) {
            return false;
        }
        if (start.empty() || end.empty()) {
            return false; // hard condition         
        }
        return startsWith(str, start) && endsWith(str, end) && str.length() >= start.length() + end.length();
    }

    // needQuote by default: '', ""
    bool needQuote(const std::string& str) {
        return !isQuoted(str);
    }

    // needQuote
    bool needQuote(const std::string& str, const std::string& start, const std::string& end) {
        return !isQuoted(str, start, end);
    }

    // quote by default: ""
    std::string quote(const std::string& str) {
        std::string strn = str;
        _quote(strn);
        return strn;
    }

    void _quote(std::string& str) {
        // quote by default: ""
        _quote(str, "\"", "\"");
    }

    // quote
    std::string quote(const std::string& str, const std::string& start, const std::string& end) {
        std::string strn = str;
        _quote(strn, start, end);
        return strn;
    }

    void _quote(std::string& str, const std::string& start, const std::string& end) {
        if (start.empty() || end.empty()) {
            return;// hard condition
        }
        str = start + str + end;
    }

    // unquote by default: '', ""
    std::string unquote(const std::string& str) {
        std::string strn = str;
        _unquote(strn);
        return strn;
    }

    // unquote by default: '', ""
    void _unquote(std::string& str) {
        if (str.empty()) {
            return;
        }
        if (!isQuoted(str)) {
            return;
        }
        str = str.substr(1, str.length() - 2);
    }

    // unquote
    std::string unquote(const std::string& str, const std::string& start, const std::string& end) {
        std::string strn = str;
        _unquote(strn, start, end);
        return strn;
    }

    // unquote
    void _unquote(std::string& str, const std::string& start, const std::string& end) {
        if (str.empty()) {
            return;
        }
        if (start.empty() || end.empty()) {
            return; // hard condition
        }
        if (!isQuoted(str, start, end)) {
            return;
        }
        str = str.substr(start.length(), str.length() - start.length() - end.length());
    }

    //// 4.4

    // isColumnSeparator

    /*
    * Return true if 'ch' is column separator by default
    */
    bool isColumnSeparator(char ch) {
        return (ch == '\r' || ch == '\n' || ch == '\t');
    }

    // isColumnText

    bool isColumnText(const char* array, int len) {
        if (isEmpty(array) || len <= 0) {
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

    bool isColumnText(const std::string& str) {
        if (str.empty()) {
            return false; // by default inline (isColumnText = false)
        }
        return isColumnText(str.c_str(), str.length());
    }

    // isLineText

    bool isLineText(const char* array, int len) {
        if (isEmpty(array)  || len <= 0) {
            return true; // by default inline
        }
        return !isColumnText(array, len);
    }

    bool isLineText(const std::string& str) {
        if (str.empty()) {
            return true; // by default inline
        }
        return isLineText(str.c_str(), str.length());
    }

    //// 5.1

    int countChars(const std::string& str, char ch) {
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

    int countStrings(const std::string& str, const std::string& substr) {
        if (str.empty() || substr.empty()) {
            return 0;
        }
        size_t pos = 0;
        int count = 0;
        while ((pos = str.find(substr, pos)) != std::string::npos /*&& pos > 0*/) {
            pos += substr.length();
            count++;
        }
        return count;
    }

    int countWords(const std::string& str) {
        if (str.empty()) {
            return 0;
        }
        std::vector<std::string> words = splitWords(str);
        return words.size();
    }

    int countWords(const std::string& str, const std::string& separators) {
        std::vector<std::string> words = splitWords(str, separators);
        return words.size();
    }

    int countLines(const std::string& str) {
        std::vector<std::string> lines = splitLines(str);
        return lines.size();
    }

    //// 6.1

    // [string]

    std::string replaceAll(const std::string& str, const std::string& s1, const std::string& s2) {
        std::string strn = str;
        _replaceAll(strn, s1, s2);
        return strn;
    }

    void _replaceAll(std::string& str, const std::string& s1, const std::string& s2) {
        if (str.empty() || s1.empty()) {
            // empty 's2' is correct case, we will remove 's1' from 'str'
            return;
        }
        if (s1 == s2) {
            return;
        }
        size_t start_pos = 0;
        while ((start_pos = str.find(s1, start_pos)) != std::string::npos) {
            str.replace(start_pos, s1.length(), s2);
            start_pos += s2.length(); 
        }
    }

    // [vector]

    std::string replaceAll(const std::string& str, const std::vector<std::string>& oldValues, const std::vector<std::string>& newValues) {
        std::string  strn = str;
        _replaceAll(strn, oldValues, newValues);
        return strn;
    }

    void _replaceAll(std::string& str, const std::vector<std::string>& oldValues, const std::vector<std::string>& newValues) {
        if (str.empty()) {
            return;
        }
        int size = std::min(oldValues.size(), newValues.size());
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            _replaceAll(str, oldValues.at(i), newValues.at(i));
        }
    }

    // [map]

    std::string replaceAll(const std::string& str, const std::map<std::string, std::string>& map) {
        std::string strn = str;
        _replaceAll(strn, map);
        return strn;
    }

    void _replaceAll(std::string& str, const std::map<std::string, std::string>& map) {
        if (str.empty()) {
            return;
        }
        if (map.empty()) {
            return;
        }
        for (std::map<std::string, std::string>::const_iterator it = map.begin(); it != map.end(); ++it) {
            _replaceAll(str, it->first, it->second);
        }
    }

    //// 7.1

    // https://stackoverflow.com/questions/49201654/splitting-a-string-with-multiple-delimiters-in-c
    // https://stackoverflow.com/questions/7621727/split-a-string-into-words-by-multiple-delimiters

    // ALT-BEGIN
    // splitBySeparator: preserve solution: split("a,b,,c", ',') = ["a", "b", "", "c"]

    std::vector<std::string> split_(const std::string& str, char separator) {        
        std::vector<std::string> res;
        if (str.empty()) {
            return res;
        }

        std::stringstream stream(str);
        std::string token;

        while (std::getline(stream, token, separator)) {
            res.push_back(token);
        }

        return res;
    }

    // splitBySeparator: preserve solution: split("a,b,,c", ",") = ["a", "b", "", "c"]

    std::vector<std::string> split_(const std::string& str, const std::string& separator) {
        size_t start = 0;
        size_t end = 0;
        size_t sep_len = separator.length();
        std::string  token;
        std::vector<std::string> res;

        while ((end = str.find(separator, start)) != std::string ::npos) {
            token = str.substr(start, end - start);
            start = end + sep_len;
            res.push_back(token);
        }

        res.push_back(str.substr(start));
        return res;
    }

    // splitBySeparator: preserve solution: split("10,20,,30", ",") = [10.0, 20.0, 0.0, 30.0]

    std::vector<float> splitf_(const std::string& str, const std::string& separator) {
        size_t start = 0;
        size_t end = 0;
        size_t sep_len = separator.length();
        std::string  token;
        std::vector<float> res;

        while ((end = str.find(separator, start)) != std::string ::npos) {
            token = str.substr(start, end - start);
            start = end + sep_len;
            res.push_back(strtof(token.c_str(), 0));         // PARSE
        }

        res.push_back(strtof(str.substr(start).c_str(), 0)); // PARSE
        return res;
    }
    // ALT-END

    // splitBySeparators: no preserve solution: split("a,b,,c", ",") = ["a", "b", "c"]

    std::vector<std::string> split__(const std::string& str, const std::string& separators) {
        std::vector<std::string> res;
        if (str.empty()) {
            return res;
        }

        if (separators.empty()) {
            res.push_back(str);
            return res;
        }

        size_t start = 0;
        size_t pos = 0;
        std::string token;

        while ((start = str.find_first_not_of(separators, pos)) != std::string::npos) {
            pos = str.find_first_of(separators, start + 1);
            token = str.substr(start, pos - start);
            res.push_back(token);
        }
        return res;
    }

    ////

    std::vector<std::string> split(const std::string& str) {
        return splitBySeparators(str, DEFAULT_SEPARATORS, false); // by default preserveAll=false
    }

    //

    std::vector<std::string> split(const std::string& str, char separator) {
        return splitBySeparator(str, separator);
    }

    std::vector<std::string> split(const std::string& str, char separator, bool preserveAll) {
        return splitBySeparator(str, separator, preserveAll);
    }

    //

    std::vector<std::string> split(const std::string& str, const std::string& separator) {
        return splitBySeparator(str, separator);
    }

    std::vector<std::string> split(const std::string& str, const std::string& separator, bool preserveAll) {
        return splitBySeparator(str, separator, preserveAll);
    }

    // splitBySeparator

    std::vector<std::string> splitBySeparator(const std::string& str, char separator) {
        return tokenizeBySeparator(str, separator, false, true);
    }

    std::vector<std::string> splitBySeparator(const std::string& str, char separator, bool preserveAll) {
        return tokenizeBySeparator(str, separator, false, preserveAll);
    }

    // splitBySeparator

    std::vector<std::string> splitBySeparator(const std::string& str, const std::string& separator) {
        return tokenizeBySeparator(str, separator, false, true);
    }

    std::vector<std::string> splitBySeparator(const std::string& str, const std::string& separator, bool preserveAll) {
        return tokenizeBySeparator(str, separator, false, preserveAll);
    }

    // splitBySeparators

    std::vector<std::string> splitBySeparators(const std::string& str, const std::string& separators) {
        return tokenizeBySeparators(str, separators, false, true);
    }

    std::vector<std::string> splitBySeparators(const std::string& str, const std::string& separators, bool preserveAll) {
        return tokenizeBySeparators(str, separators, false, preserveAll);
    }

    // trimElements

    void trimElements(std::vector<std::string>& elements) {
        if (elements.empty()) {
            return;            
        }
        for (int i = 0; i < elements.size(); i++) {
            _trim(elements[i]);
        }
    }

    // splitTrim

    std::vector<std::string> splitTrim(const std::string& str, char separator) {
        return splitTrimBySeparator(str, separator);
    }

    std::vector<std::string> splitTrim(const std::string& str, const std::string& separator) {
        return splitTrimBySeparator(str, separator);
    }
    
    // splitTrimBySeparartor
    
    std::vector<std::string> splitTrimBySeparator(const std::string& str, char separator) {
        std::vector<std::string> elements = splitBySeparator(str, separator);
        trimElements(elements);
        return elements;
    }    

    std::vector<std::string> splitTrimBySeparator(const std::string& str, const std::string& separator) {
        std::vector<std::string> elements = splitBySeparator(str, separator);
        trimElements(elements);
        return elements;
    }
    
    // splitTrimBySeparartors

    std::vector<std::string> splitTrimBySeparators(const std::string& str, const std::string& separators) {
        std::vector<std::string> elements = splitBySeparators(str, separators);
        trimElements(elements);
        return elements;
    }

    ////

    // splitWords

    std::vector<std::string> splitWords(const std::string& str) {
        return splitWords(str, DEFAULT_WORD_SEPARATORS);
    }

    std::vector<std::string> splitWords(const std::string& str, const std::string& separators) {
        std::vector<std::string> result = splitBySeparators(str, /*separators.empty() ? DEFAULT_WORD_SEPARATORS :*/ separators, false); // no preserve
        return result;
    }

    // splitRegex

    // https://stackoverflow.com/questions/13172158/c-split-string-by-line#comment56336259_13172514
    //
    // splitRegex(content, R"(\r\n|\r|\n)");
    //

    std::vector<std::string> splitRegex(const std::string& str, const std::string in_pattern) {
        std::vector<std::string> result;
        if (isEmpty(str) || isEmpty(in_pattern)) {
            return result;
        }
    
        std::regex pattern(in_pattern);
        std::copy( std::sregex_token_iterator(str.begin(), str.end(), pattern, -1), std::sregex_token_iterator(), std::back_inserter(result));
        return result;
    }

    // splitLines

    // https://en.wikipedia.org/wiki/Newline

    std::vector<std::string> splitLines1(const std::string& str) {
        return splitRegex(str, R"(\r\n|\r|\n)");
    }

    std::vector<std::string> splitLines2(const std::string& str) {
        return splitRegex(str, R"(\r\n|\r|\n|\f|\v)");
    }

    std::vector<std::string> splitLines(const std::string& str) {

        //std::vector<std::string> result = splitBySeparators(str, "\r\n", false); // no preserve
        //return result;

        return splitLines2(str);
    }

    // tokenizeBySeparator

    std::vector<std::string> tokenizeBySeparator(const std::string& str, char separator) {
        return tokenizeBySeparator(str, separator, true, false);
    }

    std::vector<std::string> tokenizeBySeparator(const std::string& str, char separator, bool includeAll, bool preserveAll) {
        
        std::vector<std::string> res;
        if (str.empty()) {
            return res;
        }

        size_t start = 0;
        size_t end = 0;
        size_t sep_len = 1;
        std::string token;
        
        while ((end = str.find(separator, start)) != std::string ::npos) {
            if (end - start == 0) {
                if (preserveAll && !includeAll) {
                    res.push_back("");
                }
            } else {
                token = str.substr(start, end - start);
                res.push_back(token);
            }            

            if (includeAll) {
                token = str.substr(end, sep_len);
                res.push_back(token);
            }

            start = end + sep_len;

        }
        
        if (start == str.length()) {
            if (preserveAll && !includeAll) {
                res.push_back("");
            }
        } else {
            res.push_back(str.substr(start));
        }
        
        return res;
    }

    // tokenizeBySeparator

    std::vector<std::string> tokenizeBySeparator(const std::string& str, const std::string& separator) {
        return tokenizeBySeparator(str, separator, true, false);
    }

    std::vector<std::string> tokenizeBySeparator(const std::string& str, const std::string& separator, bool includeAll, bool preserveAll) {
        
        std::vector<std::string> res;
        if (str.empty()) {
            return res;
        }

        if (separator.empty()) {
            res.push_back(str);
            return res;
        }

        size_t start = 0;
        size_t end = 0;
        size_t sep_len = separator.length();
        std::string token;
        
        while ((end = str.find(separator, start)) != std::string ::npos) {
            if (end - start == 0) {
                if (preserveAll && !includeAll) {
                    res.push_back("");
                }
            } else {
                token = str.substr(start, end - start);
                res.push_back(token);
            }            

            if (includeAll) {
                token = str.substr(end, sep_len);
                res.push_back(token);
            }

            start = end + sep_len;

        }
        
        if (start == str.length()) {
            if (preserveAll && !includeAll) {
                res.push_back("");
            }
        } else {
            res.push_back(str.substr(start));
        }
        
        return res;
    }

    // tokenizeBySeparators

    std::vector<std::string> tokenizeBySeparators(const std::string& str, const std::string& separators) {
        return tokenizeBySeparators(str, separators, true, false);
    }

    std::vector<std::string> tokenizeBySeparators(const std::string& str, const std::string& separators, bool includeAll, bool preserveAll) {
        
        std::vector<std::string> res;
        if (str.empty()) {
            return res;
        }

        if (separators.empty()) {
            res.push_back(str);
            return res;
        }

        size_t start = 0;
        size_t end = 0;
        size_t sep_len = 1;
        std::string token;
        
        while ((end = str.find_first_of(separators, start)) != std::string ::npos) {
            if (end - start == 0) {
                if (preserveAll && !includeAll) {
                    res.push_back("");
                }
            } else {
                token = str.substr(start, end - start);
                res.push_back(token);
            }            

            if (includeAll) {
                token = str.substr(end, sep_len);
                res.push_back(token);
            }

            start = end + sep_len;

        }
        
        if (start == str.length()) {
            if (preserveAll && !includeAll) {
                res.push_back("");
            }
        } else {
            res.push_back(str.substr(start));
        }
        
        return res;
    }

    //// 8.1

    std::string toString(const char* array) {
        if (array == nullptr) {
            return EMPTY_STRING;
        }
        std::string result(array);
        return result;
    }

    std::string toString(const std::vector<std::string>& values) {
        return toString(values, EMPTY_STRING);
    }

    std::string toString(const std::vector<std::string>& values, const std::string& separator) {
        std::string result;
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

    std::vector<std::string> toWordList(const std::string& str) {
        // we use 'vector' as 'list'
        return splitWords(str);
    }

    std::vector<std::string> toWordList(const std::string& str, const std::string& separators) {
        // we use 'vector' as 'list'
        return splitWords(str, separators);
    }

    std::vector<std::string> toLineList(const std::string& str) {
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

    bool isIdentifier(const std::string& str) {
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