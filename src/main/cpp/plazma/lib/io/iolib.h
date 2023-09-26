//#pragma once
#ifndef PLAZMA_KERNEL_LIB_IO_IOLIB_H
#define PLAZMA_KERNEL_LIB_IO_IOLIB_H


#include <string>
#include <vector>

#include "plazma/lib/ext/ustring.h" 

namespace iolib {


    //// init ////

    void init_utf8_console();

    // convert UTF-8 string to wstring
    std::wstring utf8_to_wstring(const std::string &str);
   
    // convert wstring to UTF-8 string
    std::string wstring_to_utf8(const std::wstring &str);

    std::wstring arg_to_wstring(const std::string &str);

    ////

    // convert UTF-8 string to ustring
    ext::ustring utf8_to_ustring(const std::string &str);
   
    // convert ustring to UTF-8 string
    std::string ustring_to_utf8(const ext::ustring &str);

    ext::ustring arg_to_ustring(const std::string &str);

    ////

    // convert UTF-8 string to ustring
    ext::ustring wstring_to_ustring(const std::wstring &str);

    // convert ustring to UTF-8 string
    std::wstring ustring_to_wstring(const ext::ustring &str);


    ////

    void _wout(const std::wstring &str);

    #ifdef _WIN32

    void _out(const std::wstring &str);

    #else

    void _out(const std::wstring &str);

    void _out(const ext::ustring &str);

    #endif

    void _out(const std::string &str);

    void _werr(const std::wstring &str);

    void _err(const std::wstring &str);

    void _err(const std::string &str);

    ////

    std::string _in();


    //// print ////

    ////

    //int _vfprintf(FILE *fp, const char *fmt, va_list va);

    int _vprintf(const char *fmt, va_list va);

    int _printf(const char *fmt, ...);

    //int _printf(const std::string &str);

    void _print(const std::string &str);

    void _println(const std::string &str);

    void _print(const std::wstring &str);

    void _println(const std::wstring &str);

    #ifndef _WIN32

    // void _print(const std::wstring &str);

    // void _println(const std::wstring &str);

    // #else

    // void _print(const std::wstring &str);

    // void _println(const std::wstring &str);

    void _print(const ext::ustring &str);

    void _println(const ext::ustring &str);

    #endif

    void _print(const char* str);

    void _println(const char* str);

    void _print(const wchar_t* str);

    void _println(const wchar_t* str);

    void _println();

    void _wprintln();

    //// read ////

    // readLines

    std::vector<std::string> readLines(const std::string &fileName);

    std::vector<std::string> readLines(std::ifstream &file);

    // readText

    std::string readText(const std::string &fileName);

    std::string readText(std::ifstream &file);

    // readChars

    char* readChars(const std::string &fileName);

    char* readChars(std::ifstream &file);

    //// write ////

    // writeLines

    void writeLines(const std::string &fileName, std::vector<std::string> lines);

    void writeLines(std::ofstream &file, std::vector<std::string> lines);

    // writeText

    void writeText(std::ofstream &file, std::string &text);

    void writeText(const std::string &fileName, std::string &text);


    //char* readCharArray(const std::string &fileName);

}

#endif // PLAZMA_KERNEL_LIB_IO_IOLIB_H