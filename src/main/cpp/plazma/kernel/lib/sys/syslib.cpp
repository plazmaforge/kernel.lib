#include <memory>
#include <stdexcept>
#include <iostream>

#include <cstring>
#include <vector>
//#include <array>
#include <chrono>

//#include <cstdio>
#include "syslib.h"

#include "plazma/kernel/lib/io/iolib.h"
#include "plazma/kernel/lib/str/strlib.h"
#include "plazma/kernel/lib/num/numlib.h"

#ifdef OS_WIN
#include <io.h>
#include <windows.h>
#else
#include <unistd.h>
#include <dlfcn.h>

#include <sys/utsname.h>        /* For os_name and os_version */
//#include <langinfo.h>         /* For nl_langinfo */
//#include <locale.h>
#include <pwd.h>
#endif

#ifdef OS_MAC
#include <CoreFoundation/CoreFoundation.h>
#include <CoreServices/CoreServices.h>
#endif




bool colorizedConsole = true;
bool redirectStdErr = false;
int stdoutMode = -1;
int stderrMode = -1;

// https://stackoverflow.com/questions/3709031/mapstring-string-how-to-insert-data-in-this-map
// https://stackoverflow.com/questions/478898/how-do-i-execute-a-command-and-get-the-output-of-the-command-within-c-using-po
// https://superuser.com/questions/75166/how-to-find-out-mac-os-x-version-from-terminal


namespace syslib {

void* loadLibrary(const std::string& path) {
    if (path.empty()) {
        return nullptr;
    }
    #ifdef OS_WIN
    return LoadLibrary(path.c_str());
    #else
    return dlopen(path.c_str(), /*RTLD_NOW |*/ RTLD_LAZY);
    #endif
}

void* getSymbol(void* handle, const std::string& name) {
    if (handle == nullptr || name.empty()) {
        return nullptr;
    }
    #ifdef OS_WIN
    return GetProcAddress((HMODULE) _handle, name.c_str());
    #else
    return dlsym(handle, name.c_str());
    #endif
}

bool closeLibrary(void* handle) {
    #ifdef OS_WIN
    return FreeLibrary((HMODULE) _handle) != 0; // success
	  #else
    return dlclose(handle) == 0;      // success
    #endif
}

bool isSupportLibraryError() {
    #ifdef OS_WIN
    return false; // TODO: Use GetLastError
	  #else
    return true;
    #endif
}

const std::string getLibraryError() {
    #ifdef OS_WIN
    return ""; // TODO: Use GetLastError
	  #else
    return dlerror();
    #endif
}

void resetLibraryError() {
    #ifndef OS_WIN
    dlerror();
    #endif
}

std::string getLibraryExtension() {
    std::string ext;
    #ifdef OS_WIN
      ext = ".dll";   // Windows 
    //#elif defined(__unix__) && !defined(__apple__)
    //  ext = ".so";  // Linux, BDS, Solaris and so on. 
    #elif defined(OS_MAC)
      ext = ".dylib"; // MacOSX 
    #elif defined(OS_UNIX)
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

// https://ps-group.github.io/os/os_version
// https://medium.com/@sshambir/%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82-std-filesystem-4c7ed50d5634

// os
bool isWindows() {
    #ifdef OS_WIN
    return true;
    #else
    return false;
    #endif
}

// os
bool isLinux() {
    #ifdef OS_LINUX
    return true;
    #else
    return false;
    #endif
}

// os
bool isMacOS() {
    #ifdef OS_MAC
    return true;
    #else
    return false;
    #endif
}


//void initOS() {}

//void init() {}

std::string getSafeString(const char* value) {
  return value ? std::string(value) : "";
}

std::string getOsName() {
  SysInfo* sysInfo = getSysInfo();
  return getSafeString(sysInfo ? sysInfo->os_name : nullptr);
}

std::string getOsVersion() {
  SysInfo* sysInfo = getSysInfo();
  return getSafeString(sysInfo ? sysInfo->os_version : nullptr);
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

////////////////////////////////////////////////////////////////

void setLocale(SysInfo& sysInfo, char* lc) {
  if (lc == nullptr) {
    return;
  }
  sysInfo.locale = strdup(lc);

  Locale* locale = parseLocale(sysInfo.locale);
  if (locale != nullptr) {
    
    sysInfo.format_language = locale->language;
    sysInfo.format_country = locale->country;
    sysInfo.encoding = locale->encoding;

    delete locale;
  }

}

// "en_US.UTF-8"

void initDefaultLocale(SysInfo& sysInfo) {
  sysInfo.locale = "en_US.UTF-8";
  sysInfo.format_language = "en";
  sysInfo.format_country = "US";
  sysInfo.encoding = "UTF-8";

  //sysInfo.locale = "UTF-8";
  //sysInfo.locale = "en_US";
  //sysInfo.locale = "en_US.";

  //setLocale(sysInfo, sysInfo.locale);
}

#ifdef OS_MAC

// FRAMEWORK
void initOsMac_F(SysInfo& sysInfo) {

    SInt32 majorVersion = 0;
    SInt32 minorVersion = 0;
    SInt32 bugFixVersion = 0;

    Gestalt(gestaltSystemVersionMajor, &majorVersion);
    Gestalt(gestaltSystemVersionMinor, &minorVersion);
    Gestalt(gestaltSystemVersionBugFix, &bugFixVersion);

    std::string version = "" + std::to_string(majorVersion) + "." + std::to_string(minorVersion) + "." + std::to_string(bugFixVersion);

    sysInfo.os_name = "Mac OS X";
    sysInfo.os_version = strdup(version.c_str());   
}

// ALTERNATIVE
void initOsMac_A(SysInfo& sysInfo) {
    
       std::string cmd = "sw_vers";
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

       //ProductName    :	Mac OS X
       //ProductVersion :	10.14.6
       //BuildVersion   :	18G95
}

void initOsMac(SysInfo& sysInfo) {
  initOsMac_F(sysInfo);
}

#endif

#ifdef OS_WIN
void initSysInfoWin(SysInfo& sysInfo) {
  // TODO
}
#endif

#ifdef OS_UNIX
void initSysInfoUnix(SysInfo& sysInfo) {

   /* Endianness of platform */
   unsigned int endianTest = 0xff000000;
   if (((char*) (&endianTest))[0] != 0) {
      sysInfo.cpu_endian = "big";
   } else {
      sysInfo.cpu_endian = "little";
   }

   struct utsname name;
   uname(&name);

   /* OS */
   #ifdef OS_MAC
     initOsMac(sysInfo);
     sysInfo.os_arch = strdup(name.machine); 
   #else

    //struct utsname name;
    //uname(&name);
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
   sysInfo.os_arch = strdup(name.machine); 

  /* User */
  struct passwd *pwent = getpwuid(getuid());
  if (pwent) {
    sysInfo.user_name = strdup(pwent->pw_name);
    sysInfo.user_home = strdup(pwent->pw_dir);
  }

   /* Current directory */
   //int PATH_MAX_ = 1024;
   char buf[PATH_MAX];
   errno = 0;
   if (getcwd(buf, sizeof(buf)) == NULL) {
       error("System Properties init: Can't get current working directory.");
   } else {
       sysInfo.user_dir = strdup(buf);
   }
   //#endif

   #ifdef OS_MAC
    /* Darwin has a per-user temp dir */
    static char tmp_path[PATH_MAX]; // static - important
    int pathSize = confstr(_CS_DARWIN_USER_TEMP_DIR, tmp_path, PATH_MAX);
    if (pathSize > 0 && pathSize <= PATH_MAX) {
        sysInfo.tmp_dir = tmp_path;
    }
   #else 
     // TODO: /var/tmp or /tmp
   #endif


   sysInfo.file_separator = "/";
   sysInfo.line_separator = "\n";

}
#endif

/*
static int ParseLocale(int cat, char ** std_language, char ** std_script,
                       char ** std_country, char ** std_variant, char ** std_encoding) {
    char *temp = NULL;
    char *language = NULL, *country = NULL, *variant = NULL,  *encoding = NULL;
    char *p, *encoding_variant, *old_temp, *old_ev;
    char *lc;

    lc = setlocale(cat, NULL);
}
*/

#ifdef OS_MAC

const char* getLocaleValue(CFLocaleRef locale, CFLocaleKey key) {
  CFStringRef value = (CFStringRef) CFLocaleGetValue(locale, key); 
  const char* ch = CFStringGetCStringPtr(value, kCFStringEncodingUTF8);
  if (ch == nullptr) {
    return nullptr;
  }
  return ch;
}

// FRAMEWORK
void initLocaleMac_F(SysInfo& sysInfo) {

  CFLocaleRef cflocale = CFLocaleCopyCurrent();

  const char* locale = getLocaleValue(cflocale, kCFLocaleIdentifier);

  const char* language = getLocaleValue(cflocale, kCFLocaleLanguageCode);
  const char* country = getLocaleValue(cflocale, kCFLocaleCountryCode);
  const char* script = getLocaleValue(cflocale, kCFLocaleScriptCode);
  const char* variant = getLocaleValue(cflocale, kCFLocaleVariantCode);
  const char* encoding = nullptr; //getLocaleValue(cflocale, kCFLocaleExemplarCharacterSet);

  //CFStringRef identifier = CFLocaleGetIdentifier(cflocale);
  //const char* ch = CFStringGetCStringPtr(identifier, kCFStringEncodingUTF8);

  CFRelease(cflocale);

  if (locale) {
    sysInfo.locale = strdup(locale);
  } else {

    char* lc = getLocale(); 
    setLocale(sysInfo, lc);
    return;

  }

  if (language) {
    sysInfo.format_language = strdup(language);
  }
  if (country) {
    sysInfo.format_country = strdup(country);
  }
  if (script) {
    sysInfo.format_script = strdup(script);
  }
  if (variant) {
    sysInfo.format_variant = strdup(variant);
  }

  if (encoding) {
    sysInfo.encoding = strdup(encoding);
  } else {

    char* lc = getLocale(); 

    // Empty
    if (isEmptyLocale(lc)) {
      lc = "en_US.UTF-8"; // TODO
      //initDefaultLocale(sysInfo);
      //return;
    }

    Locale* ulocale = parseLocale(lc);
    if (!ulocale || !ulocale->encoding) {
      return;
    }
    sysInfo.encoding = strdup(ulocale->encoding);
    
  }
  

}

////===


// ALTERNATIVE
void initLocaleMac_A(SysInfo& sysInfo) {

  char* lc = nullptr;

  // Test locale
  lc = getLocale(LC_CTYPE);

  if (isEmptyLocale(lc)) {
    setDefaultLocale();
  }

  int cat = LC_CTYPE;
  //int cat = LC_MESSAGES;
  //int cat = LC_ALL;
  lc = getLocale(cat);

  // Empty
  if (isEmptyLocale(lc)) {
    initDefaultLocale(sysInfo);
    return;
  }

  // UTF-8
  //if (isLocale(lc, "UTF-8")) {
  //  initDefaultLocale(sysInfo);
  //  return;
  //}

  setLocale(sysInfo, lc);

}

void initLocaleMac(SysInfo& sysInfo) {
  initLocaleMac_F(sysInfo);
  //initLocaleMac_A(sysInfo);
}

#endif


void initLocale(SysInfo& sysInfo) {

  /*
  setlocale(LC_ALL, "");
  int cat = LC_CTYPE;
  //int cat = LC_MESSAGES;
  //int cat = LC_ALL;
  char *lc = setlocale(cat, "");
  if (lc == NULL) {
    return;
  }
  //sysInfo.format_country = strdup(lc);
  */

  #ifdef OS_MAC
  initLocaleMac(sysInfo);
  #endif

}

void initSysInfo(SysInfo& sysInfo) {
  #ifdef OS_WIN
  initSysInfoWin(sysInfo);
  #else
  initSysInfoUnix(sysInfo);
  #endif
}

SysInfo* getSysInfo() {
   static SysInfo sysInfo;
   if (sysInfo.init) {
     return &sysInfo;
   }

   sysInfo.init = true;

   initLocale(sysInfo);

   initSysInfo(sysInfo);

   //initLocale(sysInfo); // In this position we have problems with MacOSX when use Gestalt

   return &sysInfo;
}

}