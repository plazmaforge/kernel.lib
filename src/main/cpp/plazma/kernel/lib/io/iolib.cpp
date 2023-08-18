#include <iostream>
#include <fstream>
#include <sstream>

#include <string>
#include <vector>

#include <iostream>

#include <locale>
#include <codecvt>

//#include <stdlib.h>

#include "iolib.h"
#include "plazma/kernel/lib/sys/syslib.h"

#ifdef _WIN32
#include "iolib_win32.h"
#endif

namespace iolib {

    //// init ////

    void init_utf8_console_base() {
        setlocale(LC_ALL, "en_US.UTF-8");

        std::locale utf8locale(std::locale(), new std::codecvt_byname<wchar_t, char, mbstate_t> ("en_US.UTF-8"));
        std::wcout.imbue(utf8locale);
    }


    #ifdef _WIN32
    void init_utf8_console_win32() {
        iolib_win32::FixStdStream(STD_INPUT_HANDLE, true, std::cin);
        iolib_win32::FixStdStream(STD_OUTPUT_HANDLE, false, std::cout);
        iolib_win32::FixStdStream(STD_ERROR_HANDLE, false, std::cerr);
    }
    #endif

    void init_utf8_console() {
        #ifdef _WIN32
        init_utf8_console_win32();
        #else
        init_utf8_console_base();
        #endif
    }

    //// convert ////

    // convert UTF-8 string to wstring
    std::wstring utf8_to_wstring_base(const std::string &str) {
        std::wstring_convert<std::codecvt_utf8<wchar_t>> converter;
        //std::wstring_convert<std::codecvt_utf8<wchar_t>, wchar_t> converter;
        return converter.from_bytes(str);
    }
   
    // convert wstring to UTF-8 string
    std::string wstring_to_utf8_base(const std::wstring &str) {
        std::wstring_convert<std::codecvt_utf8<wchar_t>> converter;
        return converter.to_bytes(str);
    }

    ////

    // convert UTF-8 string to wstring
    std::wstring utf8_to_wstring(const std::string &str) {
        #ifdef _WIN32
        return iolib_win32::utf8_to_wstring(str);
        #else
        return utf8_to_wstring_base(str);
        #endif
    }
   
    // convert wstring to UTF-8 string
    std::string wstring_to_utf8(const std::wstring &str) {
        #ifdef _WIN32
        return iolib_win32::wstring_to_utf8(str);
        #else
        return wstring_to_utf8_base(str);
        #endif
    }

    std::wstring arg_to_wstring(const std::string &str) {
        #ifdef _WIN32
        return iolib_win32::ansi_to_wstring(str);
        #else
        return utf8_to_wstring_base(str);
        #endif
    }

    ////

    // convert UTF-8 string to ustring
    ext::ustring utf8_to_ustring(const std::string &str) {
        #ifdef _WIN32
        return iolib_win32::utf8_to_wstring(str);
        #else

        // 16
        std::wstring_convert<std::codecvt_utf8<ext::uchar>, ext::uchar> converter;
        return converter.from_bytes(str);

        // 32
        //return utf8_to_wstring_base(str);
        #endif
    }
   
    // convert ustring to UTF-8 string
    std::string ustring_to_utf8(const ext::ustring &str) {
        #ifdef _WIN32
        return iolib_win32::wstring_to_utf8(str);
        #else

        // 16
        std::wstring_convert<std::codecvt_utf8<ext::uchar>, ext::uchar> converter;
        return converter.to_bytes(str);

        // 32
        //std::wstring wstr = str;
        //return wstring_to_utf8_base(wstr);
        #endif
    }

    ext::ustring arg_to_ustring(const std::string &str) {
        #ifdef _WIN32
        return iolib_win32::ansi_to_wstring(str);
        #else

        // 16
        std::wstring_convert<std::codecvt_utf8<ext::uchar>, ext::uchar> converter;
        return converter.from_bytes(str);

        // 32
        //return utf8_to_wstring_base(str);
        #endif
    }

    ////

    // convert UTF-8 string to ustring
    ext::ustring wstring_to_ustring(const std::wstring &str) {
        #ifdef _WIN32
        std::wstring wstr = str;
        return wstr;
        #else

        // 16
        // TODO: Bad solution: wchar_t -> u16char_t
        std::string s = wstring_to_utf8_base(str);
        return utf8_to_ustring(s);

        // 32
        //stringlib::ustring ustr;
        //return ustr;
        
        //std::wstring_convert<std::codecvt_utf8<stringlib::uchar>, stringlib::uchar> converter;
        //converter.from_bytes(str.c_str());

        //std::wstring_convert<std::codecvt_utf8<std::wchar_t>, std::wchar_t> converter;
        //std::wstring_convert<std::codecvt_utf8<wchar_t>, wchar_t> converter;
        //return converter.from_bytes(str);
        //wstring stW_2 = converter1.from_bytes(st8_2);
 
        //return converter.from_bytes(str.c_str());
        //return utf8_to_wstring_base(str);
        

        #endif
    }
   
    // convert ustring to UTF-8 string
    std::wstring ustring_to_wstring(const ext::ustring &str) {

        // TODO: Bad solution: wchar_t -> u16char_t
        std::string s = ustring_to_utf8(str);
        return utf8_to_wstring(s);

        //#ifdef _WIN32
        //return iolib_win32::wstring_to_utf8(str);
        //#else

        // 16
        //std::wstring_convert<std::codecvt_utf8<ext::uchar>, ext::uchar> converter;
        //return converter.to_bytes(str);

        // 32
        //std::wstring wstr = str;
        //return wstring_to_utf8_base(wstr);
        //#endif
    }


    bool isNativeConsole() {
        #ifdef _WIN32
        return systemlib::isColorizedConsole() && systemlib::isStdOutEnabled();
        #else
        return false;
        #endif
    }

    ////

    void _wout(const std::wstring &str) {
        _out(str);
    }

    #ifdef _WIN32

    void _out(const std::wstring &str) {
        iolib_win32::_WriteConsoleOutput(str);
    }

    #else

    void _out(const std::wstring &str) {
        std::wcout << str;
    }

    void _out(const ext::ustring &str) {
        std::cout << ustring_to_utf8(str);
    }    

    #endif


    /*
    void _out(const ext::ustring &str) {
        #ifdef _WIN32
        iolib_win32::_WriteConsoleOutput(str);
        #else
        cout << ustring_to_utf8(str);
        #endif
    }
    */


    void _out(const std::string &str) {
        #ifdef _WIN32
        if (isNativeConsole()) {
            iolib_win32::_WriteConsoleOutput(str);
        } else {
            std::cout << str;
        }
        #else
        std::cout << str;
        #endif
    }

    void _werr(const std::wstring &str) {
        _err(str);
    }

    void _err(const std::wstring &str) {
        #ifdef _WIN32
        iolib_win32::_WriteConsoleError(str);
        #else
        std::wcout << str; // TODO: werr
        #endif
    }

    void _err(const std::string &str) {
        #ifdef _WIN32
        iolib_win32::_WriteConsoleError(str);
        #else
        std::cout << str; // TODO: cerr
        #endif
    }

    ////

    std::string _in() {
        std::string input;
        std::cin >> input;
        return input;
        //return olib_win32::_ReadConsoleInput();
    }

    //// print ////

    int _vprintf(/*FILE *fp,*/ const char *fmt, va_list va) {

        // https://stackoverflow.com/questions/1657883/variable-number-of-arguments-in-c
        // https://stackoverflow.com/questions/7315936/which-of-sprintf-snprintf-is-more-secure
        // https://en.cppreference.com/w/cpp/utility/variadic/va_arg

        va_list va2;
        va_copy(va2, va);

        size_t n = std::vsnprintf(NULL, 0, fmt, va2) + 1; // +1 for the '\0'
 
        char str[n]; // 50
        //int n = sizeof str;

        //const char* v1 = va_arg(va, const char*);
        std::vsnprintf(str, n, fmt, va);

        _print(str);

        return n;
    }

    ////

    //int _vprintf(const char *fmt, va_list va) {
    //    return _vfprintf(stdout, fmt, va);
    //}

    int _printf(const char *fmt, ...) {
        va_list va;
        va_start(va, fmt);
        int n = _vprintf(fmt, va);
        va_end(va);
        return n;

        // va_list args;
        // va_start(args, fmt);
    
        // #ifdef _WIN32
        // iolib_win32::printf(fmt, args);
        // #else
        // std::printf(fmt, args);
        // #endif

        // va_end(args);
    }

    //int _printf(const std::string &str) {
    //    #ifdef _WIN32
    //    iolib_win32::_printf(str.c_str());
    //    #else
    //    std::printf(str.c_str());
    //    #endif
    //}

    void _print(const std::string &str) {
        _out(str);
    }

    void _println(const std::string &str) {
        _print(str);
        _println();
    }

    #ifdef _WIN32

    void _print(const std::wstring &str) {
        _out(str);
    }

    void _println(const std::wstring &str) {
        _print(str);
        _println();
    }

    #else

    void _print(const std::wstring &str) {
        _out(str);
    }

    void _println(const std::wstring &str) {
        _print(str);
        _println();
    }

    void _print(const ext::ustring &str) {
        _out(str);
    }

    void _println(const ext::ustring &str) {
        _print(str);
        _println();
    }    

    #endif


    void _print(const char* str) {
        _out(str);
        //cout << str;
    }

    void _println(const char* str) {
        _print(str);
        _println();
    }

    void _println() {
        _out("\n");
        //std::cout << endl;
    }

    //// read ////

    // readLines

    std::vector<std::string> readLines(const std::string &fileName) {
        std::ifstream file;
        std::vector<std::string> lines;
        file.open(fileName);
        if (file.is_open()) {
            lines = readLines(file);
        } else {
            syslib::error("Cannot open file: " + fileName);
        }
        file.close();
        return lines;
    }

    std::vector<std::string> readLines(std::ifstream &file) {
        std::string line;
        std::vector<std::string> lines;
        while (getline(file, line)) {
            lines.push_back(line);
        }
        return lines;
    }


    ////

    // readText

    std::string readText(const std::string &fileName) {
        char *data = readChars(fileName);
        if (data == nullptr) {
            return "";
        }
        return std::string(data);

        //return _readText(fileName);

        /*
        std::ifstream file;
        std::string text;

        //std::locale utf8locale(locale(), new std::codecvt_byname<wchar_t, char, mbstate_t> (".UTF-8"));
        //std::wcout.pubimbue(utf8locale);
        //file.imbue(utf8locale);

        file.open(fileName);
        if (file.is_open()) {
            text = readText(file);
        } else {
            std::cout << "Cannot open file: " + fileName << std::endl;
        }
        file.close();
        return text;
        */
    }

    std::string readText(std::ifstream &file) {
        char *data = readChars(file);
        if (data == nullptr) {
            return "";
        }
        return std::string(data);

        /*
        // TODO: Temp solution: read text by lines
        std::string text;
        std::vector<std::string> lines = readLines(file);
        if (lines.empty()) {
            return text;
        }
        std::string line;
        int size = lines.size();
        for (int i = 0; i < size; i++) {
            line = lines.at(i);
            //text.append(line + "\n");
            text.append(line);
            if (i != size - 1) {
                text.append("\n");
            }
        }
        return text;
        */
    }

    // readChars

    char* readChars(const std::string &fileName) {
        char *data = nullptr;
        std::ios_base::openmode openmode = std::ios::ate | std::ios::in | std::ios::binary;
        std::ifstream file(fileName, openmode); // open file

        if (file.is_open()) {
            data = readChars(file);
        } else {
            syslib::error("Cannot open file: " + fileName);
        }
        file.close();
        return data;
    }

    char* readChars(std::ifstream &file) {
        char *data = nullptr;
        if (file.is_open()) {
            std::streampos pos = file.tellg();
            size_t size = pos;
            data = new char[size + 1];
            file.seekg(0, std::ios::beg);
            file.read(data, pos);
            file.close();
            // Terminate C string at the end
            data[size] = 0;
        }
        return data;
     }

    //// write ////

    // writeLines

    void writeLines(const std::string &fileName, std::vector<std::string> lines) {
        std::ofstream file;

        file.open(fileName);
        if (file.is_open()) {
            writeLines(file, lines);
        } else {
            syslib::error("Cannot open file: " + fileName);
        }
        file.close();
    }

    void writeLines(std::ofstream &file, std::vector<std::string> lines) {
        int size = lines.size();
        std::string line;

        for (int i = 0; i < size; i++) {
            line = lines[i];

            // add lilnes to file
            file << line; // << "\n";
            if (i != size - 1) {
                file << "\n";
            }

        }
    }

    // writeText

    void writeText(std::ofstream &file, std::string &text) {
        // add text to file
        file << text;
    }

    void writeText(const std::string &fileName, std::string &text) {
        std::ofstream file;

        file.open(fileName);
        if (file.is_open()) {
            writeText(file, text);
        } else {
            syslib::error("Cannot open file: " + fileName);
        }
        file.close();
    }

    /*
    char* readCharArray(const std::string &fileName) {

        std::ifstream file;
        file.open(fileName);
        if (file.is_open()) {

            int arraysize = 512; 
            char* array = new char[arraysize];
            int i = 0;

            while(!file.eof() && i < arraysize - 1) {
                file.get(array[i]); //reading single character from file to array
                    i++;

            }
            //i++;
            //array[i] = '\0';
            return array;

        } else {
            std::cout << "Cant open file !!!!: '" << fileName << "'" << std::endl; 
            return nullptr;
        }

    }
    */


}

