#ifndef PLAZMA_KERNEL_LIB_STRING_STRINGLIB2_H
#define PLAZMA_KERNEL_LIB_STRING_STRINGLIB2_H

#include <string>
#include <cstring>

#include "plazma/lib/io/iolib.h"

namespace strlib2 {

//https://github.com/ltcmelo/string_utils/blob/master/detail.hpp

// ltrim

template <class char_t, class traits_t, class allocator_t>
void _ltrim(std::basic_string<char_t, traits_t, allocator_t> &str, const char_t ch) {
  str.erase(0, str.find_first_not_of(ch)); // prefixing spaces
}

template <class char_t, class traits_t, class allocator_t>
void _ltrim(std::basic_string<char_t, traits_t, allocator_t> &str, const char ch) {
  _ltrim(str, (char_t) ch);
}

template <class char_t, class traits_t, class allocator_t>
void _ltrim(std::basic_string<char_t, traits_t, allocator_t> &str) {
  _ltrim(str, ' ');
  _ltrim(str, '\t');
}

// rtrim

template <class char_t, class traits_t, class allocator_t>
void _rtrim(std::basic_string<char_t, traits_t, allocator_t> &str, const char_t ch) {
  str.erase(str.find_last_not_of(ch) + 1); // surfixing spaces
}

template <class char_t, class traits_t, class allocator_t>
void _rtrim(std::basic_string<char_t, traits_t, allocator_t> &str, const char ch) {
  _rtrim(str, (char_t) ch);
}

template <class char_t, class traits_t, class allocator_t>
void _rtrim(std::basic_string<char_t, traits_t, allocator_t> &str) {
  _rtrim(str, ' ');
  _rtrim(str, '\t');
}

// trim

template <class char_t, class traits_t, class allocator_t>
void _trim(std::basic_string<char_t, traits_t, allocator_t> &str, const char_t ch) {
  _ltrim(str, ch);
  _rtrim(str, ch);
}

template <class char_t, class traits_t, class allocator_t>
void _trim(std::basic_string<char_t, traits_t, allocator_t> &str, const char ch) {
  _ltrim(str, (char_t) ch);
  _rtrim(str, (char_t) ch);
}

template <class char_t, class traits_t, class allocator_t>
void _trim(std::basic_string<char_t, traits_t, allocator_t> &str/*, std::locale const& loc*/) {
  _trim(str, ' ');
  _trim(str, '\t');

  //_trim(str, (char_t)' ');
  //_trim(str, (char_t)'\t');
  //_trim(str, (char_t)"\x20\x09\x0A\x0B\x0C\x0D");
  //_trim(str, "\x20\x09\x0A\x0B\x0C\x0D");
}


/*
    string _trim2(string &str, const char ch) {
        str.erase(0, str.find_first_not_of(ch)); // prefixing spaces
        str.erase(str.find_last_not_of(ch) + 1); // surfixing spaces
        return str;
    }
  */

//     typedef std::u16string::value_type char_t7;
    
// char_t7 upcase7( char_t7 ch )
// {
//  return std::use_facet< std::ctype< char_t7 > >( std::locale() ).toupper( ch );
// }


// https://stackoverflow.com/questions/17991431/convert-a-unicode-string-in-c-to-upper-case
//template <class char_t, class traits_t, class allocator_t>
//void _toCase(std::basic_string<char_t, traits_t, allocator_t> &str, bool upper) {

template <class char_t>
void _toCase(std::basic_string<char_t> &str, bool upper) {

    // transform(str.begin(), str.end(), str.begin(), upper ? ::toupper :
    // ::tolower);

    int len = str.length();
    if (len == 0) {
        return;
    }

    if (upper) {
        for (int i = 0; i < len; i++)
            str[i] = toupper(str[i]);
    } else {
        for (int i = 0; i < len; i++)
            str[i] = tolower(str[i]);
    }
}


    /*

    void _toCaseW(std::wstring &str, bool upper) {

        int len = str.length();
        if (len == 0) {
            return;
        }

        std::locale loc;

        // std::locale loc(locale(), new codecvt_byname<wchar_t, char,
        // mbstate_t> ("en_US.UTF-8"));

        auto &f = std::use_facet<std::ctype<wchar_t>>(loc);

        if (upper) {
            f.toupper(&str[0], &str[0] + str.size());
        } else {
            f.tolower(&str[0], &str[0] + str.size());
        }
    }

    */


}

#endif // PLAZMA_KERNEL_LIB_STRING_STRINGLIB2_H