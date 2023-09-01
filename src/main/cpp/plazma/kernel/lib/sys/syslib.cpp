#include <memory>
#include <stdexcept>
#include <iostream>

#include <cstring>
#include <vector>
#include <array>
#include <chrono>

//#include <cstdio>

#include "plazma/kernel/lib/io/iolib.h"
#include "plazma/kernel/lib/str/strlib.h"
#include "plazma/kernel/lib/num/numlib.h"
#include "plazma/kernel/lib/collection/collectionlib.h"

#ifdef _WIN32
#include <io.h>
#include <windows.h>
#else
#include <unistd.h>
#include <dlfcn.h>

#include <sys/utsname.h>        /* For os_name and os_version */
//#include <langinfo.h>         /* For nl_langinfo */
#include <pwd.h>
//#include <locale.h>

#endif

#include "syslib.h"

CONST_STRING DEFAULT_ARG_PREFIX = "-";

//int initState = 0; // 0 - Not Init, 1 - Fail Init, 2 - Success Init

//std::string osName = "";
//std::string osVersion = "";
//std::string osBuild = "";

bool colorizedConsole = true;
bool redirectStdErr = false;
int stdoutMode = -1;
int stderrMode = -1;

// https://stackoverflow.com/questions/3709031/mapstring-string-how-to-insert-data-in-this-map
// https://stackoverflow.com/questions/478898/how-do-i-execute-a-command-and-get-the-output-of-the-command-within-c-using-po
// https://superuser.com/questions/75166/how-to-find-out-mac-os-x-version-from-terminal

/*
*/



namespace syslib {

void* loadLibrary(const std::string& path) {
    if (path.empty()) {
        return nullptr;
    }
    #ifdef _WIN32
    return LoadLibrary(path.c_str());
    #else
    return dlopen(path.c_str(), /*RTLD_NOW |*/ RTLD_LAZY);
    #endif
}

void* getSymbol(void* handle, const std::string& name) {
    if (handle == nullptr || name.empty()) {
        return nullptr;
    }
    #ifdef _WIN32
    return GetProcAddress((HMODULE) _handle, name.c_str());
    #else
    return dlsym(handle, name.c_str());
    #endif
}

bool closeLibrary(void* handle) {
    #ifdef _WIN32
    return FreeLibrary((HMODULE) _handle) != 0; // success
	  #else
    return dlclose(handle) == 0;      // success
    #endif
}

bool isSupportLibraryError() {
    #ifdef _WIN32
    return false;
	  #else
    return true;
    #endif
}

const std::string getLibraryError() {
    #ifdef _WIN32
    return "";
	  #else
    return dlerror();
    #endif
}

void resetLibraryError() {
    #ifndef _WIN32
    dlerror();
    #endif
}

std::string getLibraryExtension() {
    std::string ext;
    #ifdef _WIN32
      ext = ".dll";   // Windows 
    //#elif defined(__unix__) && !defined(__apple__)
    //  ext = ".so";  // Linux, BDS, Solaris and so on. 
    #elif __APPLE__ || __MACH__
      ext = ".dylib"; // MacOSX 
    #elif __unix__
      ext = ".so";    // Linux, BDS, Solaris and so on. 
    #endif 
    return ext;
}

std::string getLibraryPath(const std::string& name) {
  if (name.empty()) {
    return "";
  }
  return name + getLibraryExtension();
}

////

std::string exec(const char *cmd, bool safe) {
    std::string result = "";
    std::array<char, 128> buffer;
    std::unique_ptr<FILE, decltype(&pclose)> pipe(popen(cmd, "r"), pclose);

    if (!pipe) {
        if (safe) {
            return result;
        }
        throw std::runtime_error("popen() failed!");
    }

    while (fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
        result += buffer.data();
    }

    return result;
}

std::string exec2(const char* cmd, bool safe) {
    std::string result = "";
    char buffer[128];
    FILE* pipe = popen(cmd, "r");

    if (!pipe) {
      if (safe){
        return result;
      }
      throw std::runtime_error("popen() failed!");
    }
    try {
        while (fgets(buffer, sizeof buffer, pipe) != NULL) {
            result += buffer;
        }
    } catch (...) {
        pclose(pipe);
        throw;
    }
    pclose(pipe);
    return result;
}

/**
 * Parse application argument array and return parameter map
 */
std::map<std::string, std::string> parseArguments(int argc, char* argv[]) {
  
  std::map<std::string, std::string> parameters;
  std::string arg = "";
  std::string key = "";
  std::string value = "";
  int i = 0;
  int j = 0;
  bool clearPrefix = true;

  // parse argument array
  // format: -parameter1 value1 -parameter2 value2 ... -parameter<N> value<N>
  // 'value' without 'parameter' will skip 
  while (i < argc) {
    arg = argv[i];
    if (strlib::startsWith(arg, DEFAULT_ARG_PREFIX) && arg.length() > 1) {
      key = arg;
      if (clearPrefix) {
        key = key.substr(1);
      }
      value = "";
      j = i + 1;
      if (j < argc && !strlib::startsWith(argv[j], DEFAULT_ARG_PREFIX)) {
        value = argv[j];
        i = j;        
      }
      // insert or update
      parameters[key] = value; 
    }
    i += 1;
  }
  return parameters;
}

void printParameters(std::map<std::string, std::string> &parameters) {
  std::map<std::string, std::string>::iterator it = parameters.begin();
    while(it != parameters.end()) {
      std::cout << it->first << " : " << it->second << std::endl;
        it++;
    }
}

bool hasParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return collectionlib::containsKey(parameters, parameter);
}

std::string getParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return parameters[parameter];
}

// int

int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return numlib::toInt(getParameter(parameters, parameter));
}

int getIntParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, int def) {
    return numlib::toInt(getParameter(parameters, parameter), def);
}

// float

float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return numlib::toFloat(getParameter(parameters, parameter));
}

float getFloatParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, float def) {
    return numlib::toFloat(getParameter(parameters, parameter), def);
}

// double

float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter) {
    return numlib::toFloat(getParameter(parameters, parameter));
}

float getDoubleParameter(std::map<std::string, std::string> &parameters, const std::string& parameter, double def) {
    return numlib::toDouble(getParameter(parameters, parameter), def);
}

// https://ps-group.github.io/os/os_version
// https://medium.com/@sshambir/%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82-std-filesystem-4c7ed50d5634


// os
std::string getOsInternalName() {
    #ifdef _WIN64
    return "win64";
    #elif _WIN32
    return "win32";
    #elif __unix || __unix__
    return "unix";
    #elif __APPLE__ || __MACH__
    return "macOS";
    #elif __linux__
    return "linux";
    #elif __FreeBSD__
    return "freeBSD";
    #else
    return "other";
    #endif
}


// os
bool isWindows() {
    #ifdef _WIN32
    return true;
    #elif _WIN64
    return true;
    #else
    return false;
    #endif
}

// os
bool isLinux() {
    #ifdef __linux__
    return true;
    #else
    return false;
    #endif
}

// os
bool isMacOS() {
    #ifdef __APPLE__
    return true;
    #elif __MACH__
    return true;
    #else
    return false;
    #endif
}

bool isValidCmd(const std::string& cmd) {
  std::string result = "";
  std::string checkCmd;

  // TODO
  if (isMacOS()) {
    checkCmd = "which " + cmd;
    result = exec(checkCmd.c_str(), true);
    return !result.empty(); 
  }

  return false;
}

//void initOS() {}

//void init() {}

std::string getOsName() {
  SysInfo* sysInfo = getSysInfo();
  char* value = sysInfo ? sysInfo->os_name : nullptr;
  return value ? std::string(value) : "";
}

std::string getOsVersion() {
  SysInfo* sysInfo = getSysInfo();
  char* value = sysInfo ? sysInfo->os_version : nullptr;
  return value ? std::string(value) : "";
}

// console

// https://stackoverflow.com/questions/42139699/how-to-tell-if-stderr-has-been-redirected-in-windows
// https://stackoverflow.com/questions/3648711/detect-nul-file-descriptor-isatty-is-bogus

/*
CONSOLE_SCREEN_BUFFER_INFO sbi;
DWORD mode;
if (!GetConsoleMode(GetStdHandle(STD_INPUT_HANDLE), &mode))
   fprintf(stderr, "not console\n");
else
   fprintf(stderr, "console\n");
if (!GetConsoleScreenBufferInfo(GetStdHandle(STD_OUTPUT_HANDLE), &sbi))
   fprintf(stderr, "not console\n");
else
  fprintf(stderr, "console\n");
*/

bool isColorizedConsole() {
  return colorizedConsole;
}

void setColorizedConsole(bool flag) {
  colorizedConsole = flag;
}

#ifdef _WIN32
bool isStdOutEnabled() {   
  if (stdoutMode == -1) {
    stdoutMode = (isatty(STDOUT_FILENO));
  }
  return stdoutMode;
}

bool isStdErrEnabled() {
  if (stderrMode == -1) {
    stderrMode = (isatty(STDERR_FILENO));
  }
  return stderrMode;
}
#else
bool isStdOutEnabled() {
  if (stdoutMode == -1) {
    stdoutMode = (isatty(STDOUT_FILENO));
  }
  return stdoutMode;
}

bool isStdErrEnabled() {
  if (stderrMode == -1) {
    stderrMode = (isatty(STDERR_FILENO));
  }
  return stderrMode;
}
#endif

//

bool isColorizedStdOut() {
  return isColorizedConsole() && isStdOutEnabled();
}

bool isColorizedStdErr() {
  return isColorizedConsole() && isStdErrEnabled();
}

///////////////////////////////////////////////////////////////////////////////////////
//https://stackoverflow.com/questions/4053837/colorizing-text-in-the-console-with-c

//https://github.com/sharkdp/dbg-macro/
//https://github.com/sharkdp/dbg-macro/blob/57e71b8a2f29bd4832647518e21a613e3854d877/dbg.h#L79-L81

//static constexpr const char* const
//CONST_STRING ANSI_EMPTY = "";
//CONST_STRING ANSI_DEBUG = "\x1b[02m";
//CONST_STRING ANSI_ERROR = "\x1B[31m";
//CONST_STRING ANSI_WARN = "\x1b[33m";
//CONST_STRING ANSI_EXPRESSION = "\x1b[36m";
//CONST_STRING ANSI_VALUE = "\x1b[01m";
//CONST_STRING ANSI_TYPE = "\x1b[32m"; // info
//CONST_STRING ANSI_RESET = "\x1b[0m";

//////////////////////////////////////////////////////////////////////////////////////

/*
Here is my solution that is leightweight and works with both Windows and Linux:

#include <iostream>
#include <string>

#ifdef _WIN32
#define WIN32_LEAN_AND_MEAN
#define VC_EXTRALEAN
#include <Windows.h> // for displaying colors
#endif // Windows

using namespace std;

*/


/*
Name            FG  BG
Black           30  40
Red             31  41
Green           32  42
Yellow          33  43
Blue            34  44
Magenta         35  45
Cyan            36  46
White           37  47
Bright Black    90  100
Bright Red      91  101
Bright Green    92  102
Bright Yellow   93  103
Bright Blue     94  104
Bright Magenta  95  105
Bright Cyan     96  106
Bright White    97  107
*/

//////////////////////////////////////////////////////////////////////////////////////

//cout<<"\n1. \x1b[1mBOLD\x1b[0m";
//cout<<"\n3. \x1b[3mITALIC\x1b[0m";
//cout<<"\n4. \x1b[4mUNDERLINE\x1b[0m";
//cout<<"\n5. \x1b[5mBLINKING\x1b[0m";
//cout<<"\n7. \x1b[7mHIGHLIGHT\x1b[0m";
//cout<<"\n8. \x1b[8mPRINT NOTHING\x1b[0m";
//cout<<"\n30. \x1b[30mBLACK\x1b[0m";
//cout<<"\n31. \x1b[31mRED\x1b[0m";
//cout<<"\n32. \x1b[32mGREEN\x1b[0m";
//cout<<"\n33. \x1b[33mPURPLE\x1b[0m";
//cout<<"\n34. \x1b[34mYELLOW\x1b[0m";
//cout<<"\n35. \x1b[35mPINK\x1b[0m";
//cout<<"\n36. \x1b[36mLIGHTBLUE\x1b[0m";
//cout<<"\n37. \x1b[37mWHITE\x1b[0m";

//cout<<"\n40. \x1b[40m Black Background      \x1b[0m";
//cout<<"\n41. \x1b[41m Red Background        \x1b[0m";
//cout<<"\n42. \x1b[42m Green Background      \x1b[0m";
//cout<<"\n43. \x1b[43m Yellow Background     \x1b[0m";
//cout<<"\n44. \x1b[44m Blue Background       \x1b[0m";
//cout<<"\n45. \x1b[45m Pink Background       \x1b[0m";
//cout<<"\n46. \x1b[46m Light Blue Background \x1b[0m";

//////////////////////////////////////////////////////////////////////////////////////

std::string getForegroundCode(const int foreground) { // Linux only
   switch(foreground) {
      case  0: return "30"; // COLOR_BLACK      0
      case  1: return "34"; // COLOR_DARK_BLUE  1
      case  2: return "32"; // COLOR_DARK_GREEN 2
      case  3: return "36"; // COLOR_LIGHT_BLUE 3
      case  4: return "31"; // COLOR_DARK_RED   4
      case  5: return "35"; // COLOR_MAGENTA    5
      case  6: return "33"; // COLOR_ORANGE     6
      case  7: return "37"; // COLOR_LIGHT_GRAY 7
      case  8: return "90"; // COLOR_GRAY       8
      case  9: return "94"; // COLOR_BLUE       9
      case 10: return "92"; // COLOR_GREEN     10
      case 11: return "96"; // COLOR_CYAN      11
      case 12: return "91"; // COLOR_RED       12
      case 13: return "95"; // COLOR_PINK      13
      case 14: return "93"; // COLOR_YELLOW    14
      case 15: return "97"; // COLOR_WHITE     15
      default: return "37";
   }
}

std::string getBackgroundCode(const int background) { // Linux only
    switch(background) {
        case  0: return  "40"; // COLOR_BLACK      0
        case  1: return  "44"; // COLOR_DARK_BLUE  1
        case  2: return  "42"; // COLOR_DARK_GREEN 2
        case  3: return  "46"; // COLOR_LIGHT_BLUE 3
        case  4: return  "41"; // COLOR_DARK_RED   4
        case  5: return  "45"; // COLOR_MAGENTA    5
        case  6: return  "43"; // COLOR_ORANGE     6
        case  7: return  "47"; // COLOR_LIGHT_GRAY 7
        case  8: return "100"; // COLOR_GRAY       8
        case  9: return "104"; // COLOR_BLUE       9
        case 10: return "102"; // COLOR_GREEN     10
        case 11: return "106"; // COLOR_CYAN      11
        case 12: return "101"; // COLOR_RED       12
        case 13: return "105"; // COLOR_PINK      13
        case 14: return "103"; // COLOR_YELLOW    14
        case 15: return "107"; // COLOR_WHITE     15
        default: return  "40";
    }
}

//////////////////////////////////////////////////////////////////////////////////////

std::string getPrintColor(const int foreground) { // Linux only
    return "\033[" + getForegroundCode(foreground) + "m";
}

std::string getPrintColor(const int foreground, const int background) { // Linux only
    return "\033[" + getForegroundCode(foreground) + ";" + getBackgroundCode(background) + "m";
}

std::string getPrintResetColor() { // Linux only
    return "\033[0m"; // reset color
}

//////////////////////////////////////////////////////////////////////////////////////
// color your text in Windows console mode
// colors are 0=black 1=blue 2=green and so on to 15=white  
// colorattribute = foreground + background * 16
// to get red text on yellow use 4 + 14*16 = 228
// light red on yellow would be 12 + 14*16 = 236

#if defined(_WIN32)
void _setConsoleTextAttribute(const int value) {
    static const HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(handle, value);
}
#endif // Windows

//// stream

void setColor(std::ostream &os, const int foreground) {
  os << getPrintColor(foreground);
}

void setColor(std::ostream &os, const int foreground, const int background) {
  os << getPrintColor(foreground, background);
}

void resetColor(std::ostream &os) {
  os << getPrintResetColor();
}

//// std

void _setStdColor(std::ostream &os, const int foreground) {
  #if defined(_WIN32)
    //static const HANDLE handle = GetStdHandle(os.rdbuf() == std::cout.rdbuf() ? STD_OUTPUT_HANDLE : STD_ERROR_HANDLE);
    //SetConsoleTextAttribute(handle, foreground);
    _setConsoleTextAttribute(foreground);
  #else // #elif defined(__linux__)
    setColor(os, foreground);
  #endif // Windows/Linux
}

void _setStdColor(std::ostream &os, const int foreground, const int background) {
  #if defined(_WIN32)
    //static const HANDLE handle = GetStdHandle(os.rdbuf() == std::cout.rdbuf() ? STD_OUTPUT_HANDLE : STD_ERROR_HANDLE);
    //SetConsoleTextAttribute(handle, background<<4|foreground);
    _setConsoleTextAttribute(background<<4|foreground);
  #else // #elif defined(__linux__)
    setColor(os, foreground, background);
  #endif // Windows/Linux
}

void _resetStdColor(std::ostream &os) {
  #if defined(_WIN32)
    //static const HANDLE handle = GetStdHandle(STD_OUTPUT_HANDLE);
    //SetConsoleTextAttribute(handle, 7); // reset color
    _setConsoleTextAttribute(7); // reset color
  #else // #elif defined(__linux__)
    resetColor(os); // reset color
  #endif // Windows/Linux
}

////

void setStdOutColor(const int foreground) {
  _setStdColor(std::cout, foreground);
}

void setStdOutColor(const int foreground, const int background) {
  _setStdColor(std::cout, foreground, background);
}

void resetStdOutColor() {
  _resetStdColor(std::cout);
}

////

void setStdErrColor(const int foreground) {
  _setStdColor(std::cerr, foreground);
}

void setStdErrColor(const int foreground, const int background) {
  _setStdColor(std::cerr, foreground, background);
}

void resetStdErrColor() {
  _resetStdColor(std::cerr);
}

////

bool isSupportEscapeCode() {
  #if defined(_WIN32)
    return false;
  #else
    return true;
  #endif
}

void setConsoleColor(const int foreground) {
  setStdOutColor(foreground);
}

void setConsoleColor(const int foreground, const int background) {
  setStdOutColor(foreground, background);
}

void resetConsoleColor() {
  resetStdOutColor();
}

//////////////////////////////////////////////////////////////////////////////////////

void print(const std::string& str, const int foreground) {
    setConsoleColor(foreground);
    print(str);
    resetConsoleColor();
}

void print(const std::string& str, const int foreground, const int background) {
    setConsoleColor(foreground, background);
    print(str);
    resetConsoleColor();
}

void printNoReset(const std::string& str, const int foreground) { // print with color, but don't reset color afterwards (faster)
    setConsoleColor(foreground);
    print(str);
}

void printNoReset(const std::string& str, const int foreground, const int background) { // print with color, but don't reset color afterwards (faster)
    setConsoleColor(foreground, background);
    print(str);
}

//////////////////////////////////////////////////////////////////////////////////////

// Unix time (1970-01-01 00:00:00) in milliseconds
long getTimeInMilliseconds() {
  std::chrono::milliseconds ms = std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now().time_since_epoch());
  return ms.count();
}

// Unix time (1970-01-01 00:00:00) in seconds
long getTimeInSeconds() {
  return getTimeInMilliseconds() * 1000;
}

////

long startTime() {
    return getTimeInMilliseconds();
}

long stopTime(long time) {
    return getTimeInMilliseconds() - time;
}

// print

void print(const std::string &str) {
  iolib::_print(str);
}

void println(const std::string &str) {
  iolib::_println(str);
}

#ifdef _WIN32

void print(const std::wstring &str) {
  iolib::_print(str);
}

void println(const std::wstring &str) {
  iolib::_println(str);
}

#else

void print(const std::wstring &str) {
  iolib::_print(str);
}

void println(const std::wstring &str) {
  iolib::_println(str);
}


void print(const ext::ustring &str) {
  iolib::_print(str);
}

void println(const ext::ustring &str) {
  iolib::_println(str);
}

#endif

//

void print(const char* str) {
  iolib::_print(str);
}

void println(const char* str) {
  iolib::_println(str);
}

void println() {
  iolib::_println();
}

////

void println(const char* message1, const char* message2) {
  print(message1);
  println(message2);
}

void println(const char* message1, const char* message2, const char* message3) {
  print(message1);
  print(message2);
  println(message3);
}

void println(const char* message1, const char* message2, const char* message3, const char* message4) {
  print(message1);
  print(message2);
  print(message3);
  println(message4);
}

// printf

int _vprintf(const char *fmt, va_list va) {
  return iolib::_vprintf(fmt, va);
}

int _printf(const char *fmt, ...) {
    va_list va;
    va_start(va, fmt);
    int n = _vprintf(fmt, va);
    va_end(va);
    return n;
}

// print format message

void printfMessage(const std::string &title, const std::string &message) {
   _printf(title.c_str(), message.c_str());
   _printf("\n");
}

// print message

void printMessage(const std::string &title, const std::string &message) {
   println(title + " " + message);
}

void printMessage(const std::string &message) {
  println(message);
}

//

void printMessage(const std::string &title, const std::string &message, int color) {
  setConsoleColor(color);
  printMessage(title, message);
  resetConsoleColor();
}

////

void printError(const std::string &title, const std::string &message) {
   //fprintf(stderr, title.c_str(), message.c_str()); // TODO: Use other way
   //fprintf(stderr, "\n");

   //fprintf(stderr, title.c_str());
   //fprintf(stderr, message.c_str());
   //fprintf(stderr, "\n");

   std::fprintf(stderr, (title + " " + message + "\n").c_str());
}

/*
 * Check 'redirect error output' mode.
 * If it is true print a message to stdout. 
 */
void printRedirect(const std::string &title, const std::string &message) {
  if (isRedirectStdErr()) {
    printMessage(title, message);
  } else {
    printError(title, message);
  }
}

void printRedirect(const std::string &title, const std::string &message, int color) {  
  if (isRedirectStdErr()) {
    setStdOutColor(color);
    printMessage(title, message);
    resetStdOutColor();
  } else {
    setStdErrColor(color);
    printError(title, message);
    resetStdErrColor();
  }
}

//void printColorRedirect(const std::string &title, const std::string &message) {
//  printColorRedirect(MESSAGE_ERROR, message, COLOR_ERROR);
//}

//////////////////////////////////////////////////////////////////////////////////////

// log

const int INFO_LEVEL  = 1;
const int WARN_LEVEL  = 2;
const int ERROR_LEVEL = 3;

bool isColorizedLevel(int level) {
  if (isRedirectStdErr() || level != ERROR_LEVEL) {
    return isColorizedStdOut(); // Check stdout
  } else {
    return isColorizedStdErr(); // Check stderr
  }
}

bool isRedirectStdErr() {
  return redirectStdErr;
}

void setRedirectStdErr(bool flag) {
  redirectStdErr = flag;
}

////////////////////////////////////////////////////////////////


void info(const std::string &message) {
   info(MESSAGE_INFO, message);
}

void warn(const std::string &message) {
   warn(MESSAGE_WARN, message);
}

void error(const std::string &message) {
  error(MESSAGE_ERROR, message);
}

////

void info(const std::string &title, const std::string &message) {
  if (!isColorizedConsole()) {
    printMessage(title, message);
    return;
  }

  // Colorized
  #ifdef _WIN32
  printMessage(title, message, COLOR_INFO);
  #else
  if (isColorizedLevel(INFO_LEVEL)) {
    printMessage(title, message, COLOR_INFO);
  } else {
    printMessage(title, message);
  }
  #endif
}

void warn(const std::string &title, const std::string &message) {
  if (!isColorizedConsole()) {
    printMessage(title, message); // MESSAGE_WARN
    return;
  }

  // Colorized
  #ifdef _WIN32
  printMessage(title, message, COLOR_WARN);
  #else
  if (isColorizedLevel(WARN_LEVEL)) {
    printMessage(title, message, COLOR_WARN);
  } else {
    printMessage(title, message);
  }
  #endif
}

void error(const std::string &title, const std::string &message) {
  if (!isColorizedConsole()) {
    printRedirect(MESSAGE_ERROR, message);
    return;
  }

  // Colorized
  #ifdef _WIN32
  printRedirect(title, message, COLOR_ERROR);
  #else
  if (isColorizedLevel(ERROR_LEVEL)) {
    printRedirect(title, message, COLOR_ERROR);
  } else {
    printRedirect(title, message);
  }
 #endif
}

void initOsInfoMac(SysInfo& sysInfo) {
    
       std::string cmd = "sw_vers";
       //if (!isValidCmd(cmd)) {
       //  return;
       //}
       std::string info = exec(cmd.c_str(), true);
       if (info.empty()) {
         return;
       }

       std::vector<std::string> lines = strlib::split(info, '\n');
       int size = lines.size();

       const std::string PRODUCT_NAME = "ProductName:";
       const std::string PRODUCT_VERSION = "ProductVersion:";
       //const std::string BUILD_VERSION = "BuildVersion:";

       std::string osName = "";
       std::string osVersion = "";
       //std::string osBuild = "";

       std::string line = "";

       for (int i = 0; i < size; i++) {
         line = lines[i];
         if (strlib::startsWith(line, PRODUCT_NAME)) {
           osName = line.substr(PRODUCT_NAME.length());
           osName = strlib::trimAll(osName);
           sysInfo.os_name = strdup(osName.c_str());
         } else if (strlib::startsWith(line, PRODUCT_VERSION)) {
           osVersion = line.substr(PRODUCT_VERSION.length());
           osVersion = strlib::trimAll(osVersion);
           sysInfo.os_version = strdup(osVersion.c_str());
         }

         // else if (strlib::startsWith(line, BUILD_VERSION)) {
         //  osBuild = line.substr(BUILD_VERSION.length());
         //  osBuild = strlib::trimAll(osBuild);
         //}
       }

       //ProductName:	Mac OS X
       //ProductVersion:	10.14.6
       //BuildVersion:	18G95

}

void initSysInfoNix(SysInfo& sysInfo) {

   /* Endianness of platform */
   unsigned int endianTest = 0xff000000;
   if (((char*) (&endianTest))[0] != 0) {
      sysInfo.cpu_endian = "big";
   } else {
      sysInfo.cpu_endian = "little";
   }

   /* OS */
   #ifdef __APPLE__ || __MACH__
     initOsInfoMac(sysInfo);
   #else

    struct utsname name;
    uname(&name);
    sysInfo.os_name = strdup(name.sysname);

    #ifdef _AIX
        char os_version[strlen(name.version) + strlen(name.release) + 2];
        strcpy(os_version, name.version);
        strcat(os_version, ".");
        strcat(os_version, name.release);
        sysInfo.os_version = os_version;
    #else
        sysInfo.os_version = strdup(name.release);
    #endif

   #endif

  /* User */
  struct passwd *pwent = getpwuid(getuid());
  if (pwent) {
    sysInfo.user_name = strdup(pwent->pw_name);
    sysInfo.user_home = strdup(pwent->pw_dir);
  }

  sysInfo.file_separator = "/";
  sysInfo.line_separator = "\n";

   /* Current directory */
   int MAXPATHLEN = 512; // TODO
   char buf[MAXPATHLEN];
   errno = 0;
   if (getcwd(buf, sizeof(buf)) == NULL) {
       error("System Properties init: Can't get current working directory.");
   } else {
       sysInfo.user_dir = strdup(buf);
   }

   #ifdef __APPLE__ || __MACH__
    /* darwin has a per-user temp dir */
    static char tmp_path[PATH_MAX];
    int pathSize = confstr(_CS_DARWIN_USER_TEMP_DIR, tmp_path, PATH_MAX);
    if (pathSize > 0 && pathSize <= PATH_MAX) {
        sysInfo.tmp_dir = tmp_path;
    }
   #else 
     // TODO
   #endif

}

void initSysInfoWin(SysInfo& sysInfo) {
  // TODO
}

SysInfo* getSysInfo() {
   static SysInfo sysInfo;
   if (sysInfo.init) {
     return &sysInfo;
   }

   sysInfo.init = true;

   #ifdef _WIN32
   initSysInfoWin(sysInfo);
   #else
   initSysInfoNix(sysInfo);
   #endif

   return &sysInfo;
}

}