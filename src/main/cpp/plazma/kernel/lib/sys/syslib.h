#ifndef PLAZMA_KERNEL_LIB_SYS_SYSLIB_H
#define PLAZMA_KERNEL_LIB_SYS_SYSLIB_H

#include <string>
#include <map>

//#include <cstdarg>
//#include <iostream>

#include "plazma/kernel/lib/ext/ustring.h"

namespace syslib {

    #define COLOR_BLACK      0
    #define COLOR_DARK_BLUE  1
    #define COLOR_DARK_GREEN 2
    #define COLOR_LIGHT_BLUE 3
    #define COLOR_DARK_RED   4
    #define COLOR_MAGENTA    5
    #define COLOR_ORANGE     6
    #define COLOR_LIGHT_GRAY 7
    #define COLOR_GRAY       8
    #define COLOR_BLUE       9
    #define COLOR_GREEN     10
    #define COLOR_CYAN      11
    #define COLOR_RED       12
    #define COLOR_PINK      13
    #define COLOR_YELLOW    14
    #define COLOR_WHITE     15

    const int COLOR_INFO = COLOR_GREEN;
    const int COLOR_WARN = COLOR_YELLOW;
    const int COLOR_ERROR = COLOR_RED;

    const std::string MESSAGE_INFO  = "[INFO]";
    const std::string MESSAGE_WARN  = "[WARNING]";
    const std::string MESSAGE_ERROR = "[ERROR]";

    //const std::string MESSAGE_INFO  = "[INFO   ]";
    //const std::string MESSAGE_WARN  = "[WARNING]";
    //const std::string MESSAGE_ERROR = "[ERROR  ]";

    /**
     * Parse application argument array and return parameter map
     */
    std::map<std::string, std::string> parseArguments(int argc, char *argv[]);

    void printParameters(std::map<std::string, std::string> &parameters);

    bool hasParameter(std::map<std::string, std::string> &parameters, const std::string &parameter);

    std::string getParameter(std::map<std::string, std::string> &parameters, const std::string &parameter);

    // int

    int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

    int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, int def);

    // float

    float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

    float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, float def);

    // double

    float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter);

    float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, double def);

    // os
    std::string getOsInternalName();

    // os
    std::string getOsName();

    // os
    std::string getOsVersion();

    // os: Windows
    bool isWindows();

    // os: Linux
    bool isLinux();

    // os: MacOS
    bool isMacOS();

    // console
    bool isColorizedConsole();

    void setColorizedConsole(bool flag);

    //

    bool isColorizedStdOut();

    bool isColorizedStdErr();

    //

    bool isStdOutEnabled();

    bool isStdErrEnabled();

    ////

    std::string getPrintColor(const int foreground); // Linux only

    std::string getPrintColor(const int foreground, const int background); // Linux only

    std::string getPrintResetColor(); // Linux only

    //// stream

    //void setColor(std::ostream &os, const int foreground);

    //void setColor(std::ostream &os, const int foreground, const int background);

    //void resetColor(std::ostream &os);

    ////

    bool isSupportEscapeCode();

    void setConsoleColor(const int foreground);

    void setConsoleColor(const int foreground, const int background);

    void resetConsoleColor();

    //////////////////////////////////////////////////////////////////////////////////////

    void print(const std::string& str, const int foreground);

    void print(const std::string& str, const int foreground, const int background);

    void printNoReset(const std::string& str, const int foreground);

    void printNoReset(const std::string& str, const int foreground, const int background);

    //////////////////////////////////////////////////////////////////////////////////////
    
    // Unix time (1970-01-01 00:00:00) in milliseconds
    long getTimeInMilliseconds();

    // Unix time (1970-01-01 00:00:00) in seconds
    long getTimeInSeconds();

    ////

    long startTime();

    long stopTime(long time);

    // printf

    int _vprintf(const char *fmt, va_list va);
    
    int _printf(const char *fmt, ...);

    // print

    void println();

    void print(const std::string &str);

    void println(const std::string &str);

    //

    #ifdef _WIN32

    void print(const std::wstring &str);

    void println(const std::wstring &str);

    #else

    void print(const std::wstring &str);

    void println(const std::wstring &str);

    void print(const ext::ustring &str);

    void println(const ext::ustring &str);

    #endif

    //

    void print(const char* str);

    void println(const char* str);

    ////

    void println(const char* message1, char* message2);

    void println(const char* message1, const char* message2, const char* message3);

    void println(const char* message1, const char* message2, const char* message3, const char* message4);

    // print format message

    void printfMessage(const std::string &title, const std::string &message);

    // print message

    void printMessage(const std::string &title, const std::string &message);

    void printMessage(const std::string &message);

    // log

    bool isRedirectStdErr();

    void setRedirectStdErr(bool flag);

    //

    void info(const std::string &message);

    void warn(const std::string &message);

    void error(const std::string &message);

    //

    void info(const std::string &title, const std::string &message);

    void warn(const std::string &title, const std::string &message);

    void error(const std::string &title, const std::string &message);

}
#endif // PLAZMA_KERNEL_LIB_SYS_SYSLIB_H