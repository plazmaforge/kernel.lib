#ifndef PLAZMA_LIB_STR_STRLIB_H
#define PLAZMA_LIB_STR_STRLIB_H

#include <string>
#include <vector>

#include "plazma/lib/sys/define.h"

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
// 1.2 
//
// - normalize(const string& str)                                              - trim
// - normalizeBlank(const string& str, bool trimAll, bool trimBlank)
// - normalizeQuoted(const string& str)                                       - trim in quoted value
//
// - defautIfEmpty(const string& str, const string& defautStr)                 - str.empty() ? defautStr : str
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
// 1.4
//
// - findFirstNotOf(const string& str, char ch)
// - findFirstNotOf(const string& str, char ch, int pos)
//
// - findLastNotOf(const string& str, char ch)
// - findLastNotOf(const string& str, char ch, int pos)

/////////////////////////////////////////////////////////////////////////////////
// 2.1
//
// - replicate(const string& str, int n)                                       - replicate("abc", 3) = 'abcabcabc' : repeat (?)
// - replicate(char ch, int n)                                                 - replicate('a', 3) = "aaa"
//
// 2.2 lpad, rpad
//
// - lpad(const string& str, int len)                                          - lpad("abc", 5) = "  abc"
// - lpad(const string& str, int len, const string& pad)                       - lpad("abc", 5, "*") = "**abc"
// - lpad(const string& str, int len, char pad)                                - lpad("abc", 5, '*') = "**abc"
//
// - rpad(const string& str, int len)                                          - rpad("abc", 5) = "abc  "
// - rpad(const string& str, int len, const string& pad)                       - rpad("abc", 5, "*") = "abc**""
// - rpad(const string& str, int len, char pad)                                - rpad("abc", 5, '*') = "abc**""
//
// 2.3
//
// - fill(const string& str, int len)                                          - 
// - fill(const string& str, int len, const string& pad)                       -  
// - fill(const string& str, int len, char pad)                                - 
// 
// - ellipsis(const string& str, int len)                                      - 
//
// - trunc(const string& str, int len)                                         - 
// - trunc(const string& str, int len, bool trim, bool ellipsis)               - 
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
// - toCamelCase(const string& str, const string& separator)                   - 
// - toCamelCase(const string& str, bool capitalize)                           - 
// - toCamelCase(const string& str, const string& separator, bool capitalize)  - 
//
// - toSnakeCase(const string& str)		                                       - toSnakeCase("PropertyName") = "property_name"
// - toSnakeCase(const string& str, const string& separator)                   - 
// - toSnakeCase(const string& str, bool upper)                                -  
// - toSnakeCase(const string& str, const string& separator, bool upper)       -  
// - toSnakeCase(const string& str, const string& separator, bool upper, bool trim) -  
//
// - toKebabCase(const string& str)		                                       - toKebabCase("PropertyName") = "property-name"
// - toKebabCase(const string& str, const string& separator)                   - 
// - toKebabCase(const string& str, bool upper)                                -  
// - toKebabCase(const string& str, const string& separator, bool upper)       -  
// - toKebabCase(const string& str, const string& separator, bool upper, bool trim) -  
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
// - endsWith(const string& str, const string& suffix)                         - endsWith("myfile.txt", ".txt") = true
//
// - hasPrefix(const string& str, const string& prefix)                        - [alias]: hasPrefix("myfile.txt", "myfile") = true
// - hasSuffix(const string& str, const string& suffix)  [alias]               - [alias]: hasSuffix("myfile.txt", ".txt") = true
//
// - startsWithIgnoreCase(const string& str, const string& prefix)             - startsWithIgnoreCase("myfile.txt", "MyFile") = true
// - endsWithIgnoreCase(const string& str, const string& suffix)               - endsWithIgnoreCase("myfile.txt", ".TxT") = true
//
// - hasPrefixIgnoreCase(const string& str, const string& prefix)              - [alias]: hasPrefixIgnoreCase("myfile.txt", "MyFile") = true
// - hasSuffixIgnoreCase(const string& str, const string& suffix)              - [alias]: hasSuffixIgnoreCase("myfile.txt", ".TxT") = true
//
// 4.2
// 
// - removePrefix(const string& str, const string& prefix)                     -
// - removePrefixes(const string& str, const vector<string > &prefixes)        - 
//
// - removeSuffix(const string& str, const string& suffix)                     -
// - removeSuffixes(const string& str, const vector<string > &suffixes)        - 
//
// 4.3
// 
// - isQuoted(const string& str)                                               -
// - isQuoted(const string& str, const string& startQuote, const string& endQuote)
// - needQuote(const string& str)                                              -
// - needQuote(const string& str, const string& startQuote, const string& endQuote)
// - quote(const string& str)                                                  -
// - quote(const string& str, const string& startQuote, const string& endQuote)
// - unquote(const string& str)                                                -
// - unquote(const string& str, const string& startQuote, const string& endQuote)
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
// - split(const string& str, char separator)
// - split(const string& str, const string& separator)
// 
// - splitWords(const string& str)
// - splitWords(const string& str, const string& separators)
// - splitLines(const string& str)

/////////////////////////////////////////////////////////////////////////////////
// 8.1
//      
// - toString(const char[] array)
// - toString(const vector<string > values)
// - toString(const vector<string > values, const string& separator)
//
// - toWordArray(const string& str)                                            - N/A ???
// - toWordArray(const string& str, const string& separators)                  - N/A ???
// - toLineArray(const string& str)                                            - N/A ???
//
// 8.2
//
// - toWordList(const string& str)                                             - 
// - toWordList(const string& str, const string& separators)                   - 
// - toLineList(const string& str)                                             - 
// - toList(array)                                                             - N/A ???    

/////////////////////////////////////////////////////////////////////////////////
// 9.1
//  
// - isDigit(char ch)                                                          -
// - isLetter(char ch)                                                         -
// - isAlpha(char ch)                                                          -
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
// - maxLen(const string array[], int len)                                     - May be rename to 'maxLength' ?
//

namespace strlib {

    CONST_STRING EMPTY_STRING = "";

    const char SPACE_CHAR = ' ';

    CONST_STRING  ELLIPSIS = "...";

    const int ELLIPSIS_LEN = 3;

    CONST_STRING DEFAULT_PAD = " ";

    CONST_STRING DEFAULT_SEPARATORS = " \t\n\r\f\v";

    CONST_STRING DEFAULT_TRIM = " \t\n\r\f\v";

    CONST_STRING DEFAULT_WORD_SEPARATORS = DEFAULT_SEPARATORS + ".,;'(){}[]!?+/=<>*&^%$#@`~|\\";

    CONST_STRING DEFAULT_CASE_SEPARATOR = "_";

    CONST_STRING DEFAULT_CASE_SEPARATORS_ = "-_";

    CONST_STRING DEFAULT_CASE_SEPARATORS_A = "-_A";

    CONST_STRING DEFAULT_ALTER_CASE_SEPARATOR = "-"; // XML    

    CONST_STRING DEFAULT_SNAKE_CASE_SEPARATOR = "_"; // SNAKE

    CONST_STRING DEFAULT_KEBAB_CASE_SEPARATOR = "-"; // KEBAB


    const bool DEFAULT_FORCE_CAPITALIZE = false;

    const bool DEFAULT_CAMEL_CASE_CAPITALIZE = true; // 'first_name' -> 'FirstName'

    // const int INDEX_NOT_FOUND = -1;

    ///////////////////////////////////
    // Case Operations
    ///////////////////////////////////
    // caseOp =  1: 'myname': lowercase 
    // caseOp =  2: 'MYNAME': UPPERCASE 
    // caseOp =  3: 'myName': camelCase
    // caseOp =  4: 'MyName': CamelCase
    
    const int CO_NONE       =  0;
    const int CO_lowercase  =  1;
    const int CO_UPPERCASE  =  2;
    const int CO_camelCase  =  3;
    const int CO_PascalCase =  4;


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

    const int CT_lowercase  =  1;
    const int CT_UPPERCASE  =  2;
    const int CT_camelCase  =  3;
    const int CT_PascalCase =  4;
    const int CT_snake_case =  5;
    const int CT_SNAKE_CASE =  6;
    const int CT_Snake_Case =  7;
    const int CT_kebab_case =  8;
    const int CT_KEBAB_CASE =  9;
    const int CT_Kebab_Case = 10;

    CONST_STRING SNAKE_CONNECTOR = "_"; // shake_case
    CONST_STRING KEBAB_CONNECTOR = "-"; // kebab-case

    //// 1.1

    bool isEmpty(const char* str);

    bool isEmpty(const std::string& str);

    bool isBlank(const std::string& str);

    //

    int size(const char* str);

    int size(const std::string& str);

    ////

    bool equals(const char* str1, const char* str2);

    bool equals(const std::string& str1, const std::string& str2);

    bool equalsContent(const std::string& str1, const char* str2);

    //// 1.2

    std::string normalize(const std::string& str);

    void _normalize(std::string& str);

    std::string normalizeBlank(const std::string& str, bool trimAll, bool trimBlank);

    void _normalizeBlank(std::string& str, bool trimAll, bool trimBlank);

    std::string normalizeQuoted(const std::string& str);

    void _normalizeQuoted(std::string& str);

    std::string defaultIfEmpty(const std::string& str, const std::string& defaultStr);

    //// 1.3 trim (left, right)

    // trim

    std::string trim(const std::string& str);

    std::string trim(const std::string& str, char ch);

    std::string trim(const std::string& str, const char* terms);

    void _trim(std::string& str);

    void _trim(std::string& str, char ch);

    void _trim(std::string& str, const char* ch);

    // trimAll

    std::string trimAll(const std::string& str);

    void _trimAll(std::string& str);

    // ltrim

    std::string ltrim(const std::string& str);

    std::string ltrim(const std::string& str, char ch);

    std::string ltrim(const std::string& str, const char* terms);

    void _ltrim(std::string& str);

    void _ltrim(std::string& str, char ch);

    void _ltrim(std::string& str, const char* terms);

    // rtrim

    std::string rtrim(const std::string& str);

    std::string rtrim(const std::string& str, char ch);

    std::string rtrim(const std::string& str, const char* terms);

    void _rtrim(std::string& str);

    void _rtrim(std::string& str, char ch);

    void _rtrim(std::string& str, const char* terms);

// find

    int find(const std::string& str, char ch);

    int find(const std::string& str, char ch, int pos);

    // findFirst

    int findFirst(const std::string& str, char ch);

    int findFirst(const std::string& str, char ch, int pos);

    // findLast

    int findLast(const std::string& str, char ch);

    int findLast(const std::string& str, char ch, int pos);

    // findFirstOf

    int findFirstOf(const std::string& str, char ch);

    int findFirstOf(const std::string& str, char ch, int pos);

    // findLastOf

    int findLastOf(const std::string& str, char ch);

    int findLastOf(const std::string& str, char ch, int pos);

    // findFirstNotOf

    int findFirstNotOf(const std::string& str, char ch);

    int findFirstNotOf(const std::string& str, char ch, int pos);

    // findLastNotOf

    int findLastNotOf(const std::string& str, char ch);

    int findLastNotOf(const std::string& str, char ch, int pos);

    //// 2.1

    std::string replicate(const std::string& str, int n);

    std::string replicate(char ch, int n);

    //// 2.1

    std::string lpad(const std::string& str, int len);

    std::string lpad(const std::string& str, int len, const std::string& pad);

    std::string lpad(const std::string& str, int len, char pad);

    //

    std::string rpad(const std::string& str, int len);

    std::string rpad(const std::string& str, int len, const std::string& pad);

    std::string rpad(const std::string& str, int len, char pad);

    //// 2.3

    std::string fill(const std::string& str, int len);

    std::string fill(const std::string& str, int len, const std::string& pad);

    std::string fill(const std::string& str, int len, char pad);

    //

    std::string ellipsis(const std::string& str, int len);

    std::string trunc(const std::string& str, int len);

    std::string trunc(const std::string& str, int len, bool trim, bool ellipsis);

    void _trunc(std::string& str, int len, bool trim, bool ellipsis);
    
    std::string left(const std::string& str, int len);
    
    std::string right(const std::string& str, int len);

    //// 3.1

    // capitalze

    std::string capitalize(const std::string& str);

    std::string capitalize(const std::string& str, bool force);

    void _capitalize(std::string& str);

    void _capitalize(std::string& str, bool force);

    // decapitalze

    std::string decapitalize(const std::string& str);

    std::string decapitalize(const std::string& str, bool force);

    void _decapitalize(std::string& str);

    void _decapitalize(std::string& str, bool force);

    // capitalze/decapitalze

    void _toCapitalize(std::string& str, bool upper, bool force);

    ////

    // upper/lower

    std::string upper(const std::string& str);

    void _upper(std::string& str);

    std::string lower(const std::string& str);

    void _lower(std::string& str);

    // toUpper/LowerCase

    std::string toUpperCase(const std::string& str);

    void _toUpperCase(std::string& str);

    std::string toLowerCase(const std::string& str);

    void _toLowerCase(std::string& str);

    std::string toCase(const std::string& str, bool upper);

    void _toCase(std::string& str, bool upper);

    char toCase(char ch, bool upper);

    // toCamelCase: CamelCase, camelCase

    std::string toCamelCase(const std::string& str);

    std::string toCamelCase(const std::string& str, const std::string& separator);

    std::string toCamelCase(const std::string& str, bool capitalize);

    std::string toCamelCase(const std::string& str, const std::string& separator, bool capitalize);

    void _toCamelCase(std::string& str, const std::string& separator, bool capitalize);

    // toSnakeCase: snake_case

    std::string toSnakeCase(const std::string& str);

    std::string toSnakeCase(const std::string& str, const std::string& separator);

    std::string toSnakeCase(const std::string& str, bool upper);

    std::string toSnakeCase(const std::string& str, const std::string& separator, bool upper, bool trim);

    void _toSnakeCase(std::string& str, const std::string& separator, bool upper, bool trim);

    // toKebabCase: kebab-case

    std::string toKebabCase(const std::string& str);

    std::string toKebabCase(const std::string& str, const std::string& separator);

    std::string toKebabCase(const std::string& str, bool upper);

    std::string toKebabCase(const std::string& str, const std::string& separator, bool upper);

    std::string toKebabCase(const std::string& str, const std::string& separator, bool upper, bool trim);

    void _toKebabCase(std::string& str, const std::string& separator, bool upper, bool trim);

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

    // Return case code by case type
    int toCaseCode(const std::string& type);

    std::string toTypeCase(const std::string& str, const std::string& type);

    std::string toTypeCase(const std::string& str, const std::string& type, const std::string& separators, const std::string& connector);

    void _toTypeCase(std::string& str, const std::string& type);

    void _toTypeCase(std::string& str, const std::string& type, const std::string& separators, const std::string& connector);

    // reverse

    std::string reverse(const std::string& str);

    void _reverse(std::string& str);

    //// 4.1

 
    // startsWith, endsWith(

    bool startsWith(const std::string& str, const std::string& prefix);

    bool startsWith2(const std::string& str, const std::string& prefix);

    bool endsWith(const std::string& str, const std::string& suffix);

    // hasPrefix, hasSuffix

    bool hasPrefix(const std::string& str, const std::string& prefix);

    bool hasSuffix (const std::string& str, const std::string& suffix);

    // startsWithIgnoreCase, endsWithIgnoreCase

    bool startsWithIgnoreCase(const std::string& str, const std::string& prefix);

    bool endsWithIgnoreCase(const std::string& str, const std::string& suffix);

    // hasPrefixIgnoreCase, hasSuffixIgnoreCase

    bool hasPrefixIgnoreCase(const std::string& str, const std::string& prefix);

    bool hasSuffixIgnoreCase(const std::string& str, const std::string& suffix);

    //// 4.2

    std::string removePrefix(const std::string& str, const std::string& prefix);

    void _removePrefix(std::string& str, const std::string& prefix);

    //

    std::string removePrefixes(const std::string& str, const std::vector<std::string > &prefixes);

    void _removePrefixes(std::string& str, const std::vector<std::string > &prefixes);

    //

    std::string removeSuffix(const std::string& str, const std::string& suffix);

    void _removeSuffix(std::string& str, const std::string& suffix);

     //

    std::string removeSuffixes(const std::string& str, const std::vector<std::string > &suffixes);

    void _removeSuffixes(std::string& str, const std::vector<std::string > &suffixes);
   
    //// 4.3

    // isQuoted by default: ', "
    bool isQuoted(const std::string& str);

    // isQuoted
    bool isQuoted(const std::string& str, const std::string& startQuote, const std::string& endQuote);

    // needQuote by default: ', "
    bool needQuote(const std::string& str);

    // needQuote
    bool needQuote(const std::string& str);

    // quote by default: "
    std::string quote(const std::string& str);

    // quote by default: "
    void _quote(std::string& str);

    // quote
    std::string quote(const std::string& str, const std::string& startQuote, const std::string& endQuote);

    // quote
    void _quote(std::string& str, const std::string& startQuote, const std::string& endQuote);

    // unquote by default: ', "
    std::string unquote(const std::string& str);

    // unquote by default: ', "
    void _unquote(std::string& str);

    // unquote
    std::string unquote(const std::string& str, const std::string& startQuote, const std::string& endQuote);

    // unquote
    void _unquote(std::string& str, const std::string& startQuote, const std::string& endQuote);

    //// 4.4

    // isColumnSeparator

    /*
    * Return true if 'ch' is column separator by default
    */
    bool isColumnSeparator(char ch);

    // isColumnText

    bool isColumnText(const char* array, int len);

    bool isColumnText(const std::string& str);

    // isLineText

    bool isLineText(const char* array, int len);

    bool isLineText(const std::string& str);

    //// 5.1

    int countChars(const std::string& str, char ch);

    int countStrings(const std::string& str, const std::string& substr);

    int countWords(const std::string& str);

    int countWords(const std::string& str, const std::string& separators);

    int countLines(const std::string& str);

    //// 6.1

    std::string replaceAll(const std::string& str, const std::string& s1, const std::string& s2);

    void _replaceAll(std::string& str, const std::string& s1, const std::string& s2);

    std::string replaceAll(const std::string& str, const std::vector<std::string>& oldValues, const std::vector<std::string>& newValues);

    void _replaceAll(std::string& str, const std::vector<std::string>& oldValues, const std::vector<std::string>& newValues);

    //// 7.1

    std::vector<std::string> split(const std::string& str, char separator);

    std::vector<std::string> split(const std::string& str, const std::string& separator);

    // splitBySeparator

    std::vector<std::string> splitBySeparator(const std::string& str, char separator);

    std::vector<std::string> splitBySeparator(const std::string& str, char separator, bool preserveAll);

    // splitBySeparator

    std::vector<std::string> splitBySeparator(const std::string& str, const std::string& separator);

    std::vector<std::string> splitBySeparator(const std::string& str, const std::string& separator, bool preserveAll);

    // splitBySeparators

    std::vector<std::string> splitBySeparators(const std::string& str, const std::string& separators);

    std::vector<std::string> splitBySeparators(const std::string& str, const std::string& separators, bool preserveAll);

    //

    std::vector<std::string> splitWords(const std::string& str);

    std::vector<std::string> splitWords(const std::string& str, const std::string& separators);

    std::vector<std::string> splitLines(const std::string& str);

    // tokenizeBySeparator

    std::vector<std::string> tokenizeBySeparator(const std::string& str, char separator);

    std::vector<std::string> tokenizeBySeparator(const std::string& str, char separator, bool includeAll, bool preserveAll);

    // tokenizeBySeparator

    std::vector<std::string> tokenizeBySeparator(const std::string& str, const std::string& separator);

    std::vector<std::string> tokenizeBySeparator(const std::string& str, const std::string& separator, bool includeAll, bool preserveAll);

    // tokenizeBySeparators

    std::vector<std::string> tokenizeBySeparators(const std::string& str, const std::string& separators);

    std::vector<std::string> tokenizeBySeparators(const std::string& str, const std::string& separators, bool includeAll, bool preserveAll);

    //// 8.1

    std::string toString(const char* array);

    std::string toString(const std::vector<std::string>& values);

    std::string toString(const std::vector<std::string>& values, const std::string& separator);

    std::vector<std::string > toWordList(const std::string& str);

    std::vector<std::string > toWordList(const std::string& str, const std::string& separators);

    std::vector<std::string > toLineList(const std::string& str);

    //// 9.1

    bool isDigit(char ch);

    bool isLetter(char ch);

    bool isAlpha(char ch);

    //

    bool isWhitespace(char ch);

    bool isDot(char ch);

    bool isUnderline(char ch);

    bool isIdentifier(const std::string& str);

    //// 9.2

    bool isUpperCase(char ch);

    bool isLowerCase(char ch);

    //// 11.1

    int maxLen(const std::string array[], int len);

}

#endif // PLAZMA_LIB_STR_STRLIB_H