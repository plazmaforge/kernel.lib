
#include <iostream>
#include "sysinit.h" // importatnt before OS_<NAME> detection

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

#ifdef _ALLBSD_SOURCE
#ifndef P_tmpdir
#include <paths.h>
#define P_tmpdir _PATH_VARTMP
#endif
#endif

#ifndef P_tmpdir
#define P_tmpdir "/var/tmp"
#endif

#ifdef OS_MAC

#ifdef OS_MAC_FRAMEWORK
#include <CoreFoundation/CoreFoundation.h>
#include <CoreServices/CoreServices.h>
#endif

#endif


#include "plazma/kernel/lib/str/strlib.h"

namespace syslib {

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

////

void initLocalePosix(SysInfo& sysInfo) {

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

//// MACOS ////

#ifdef OS_MAC

#ifdef OS_MAC_FRAMEWORK
const char* getLocaleValue(CFLocaleRef locale, CFLocaleKey key) {
  CFStringRef value = (CFStringRef) CFLocaleGetValue(locale, key); 
  const char* ch = CFStringGetCStringPtr(value, kCFStringEncodingUTF8);
  if (ch == nullptr) {
    return nullptr;
  }
  return ch;
}

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
#endif // OS_MAC_FRAMEWORK

// ALTERNATIVE
void initLocaleMac_A(SysInfo& sysInfo) {
    initLocalePosix(sysInfo);
}

void initLocaleMac(SysInfo& sysInfo) {
  #ifdef OS_MAC_FRAMEWORK

  #ifdef OS_MAC_FRAMEWORK_LOCALE
  initLocaleMac_F(sysInfo);
  #else
  initLocaleMac_A(sysInfo);
  #endif

  #else
  initLocaleMac_A(sysInfo);
  #endif
}

#ifdef OS_MAC_FRAMEWORK
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
#endif // OS_MAC_FRAMEWORK

// UTILS: getSuffix(prefix)
std::string getLineValue(const std::string& name, const std::string& line) {
    if (name.empty() || line.empty()) {
        return "";
    }
    std::string value = line.substr(name.length());
    strlib::_trimAll(value);
    return value;
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

       std::string line;
       std::string value;

       for (int i = 0; i < size; i++) {
         line = lines[i];
         if (strlib::startsWith(line, PRODUCT_NAME)) {
           value = getLineValue(PRODUCT_NAME, line);
           sysInfo.os_name = strdup(value.c_str());
         } else if (strlib::startsWith(line, PRODUCT_VERSION)) {
           value = getLineValue(PRODUCT_VERSION, line);
           sysInfo.os_version = strdup(value.c_str());
         }
         // else if (strlib::startsWith(line, BUILD_VERSION)) {
         //  value = getLineValue(BUILD_VERSION, line);
         //  sysInfo.os_build = strdup(value.c_str());
         //}
       }

       //ProductName    :	Mac OS X
       //ProductVersion :	10.14.6
       //BuildVersion   :	18G95
}

void initOsMac(SysInfo& sysInfo) {
    #ifdef OS_MAC_FRAMEWORK

    #ifdef OS_MAC_FRAMEWORK_SYSTEM
    initOsMac_F(sysInfo);
    #else
    initOsMac_A(sysInfo);
    #endif

    #else
    initOsMac_A(sysInfo);
    #endif  
}

#endif // OS_MAC


//// WIN ////

#ifdef OS_WIN

/*
 * From msdn page on OSVERSIONINFOEX, current as of this
 * writing, decoding of dwMajorVersion and dwMinorVersion.
 *
 *  Operating system            dwMajorVersion  dwMinorVersion
 * ==================           ==============  ==============
 *
 * Windows 95                   4               0
 * Windows 98                   4               10
 * Windows ME                   4               90
 * Windows 3.51                 3               51
 * Windows NT 4.0               4               0
 * Windows 2000                 5               0
 * Windows XP 32 bit            5               1
 * Windows Server 2003 family   5               2
 * Windows XP 64 bit            5               2
 *       where ((&ver.wServicePackMinor) + 2) = 1
 *       and  si.wProcessorArchitecture = 9
 * Windows Vista family         6               0  (VER_NT_WORKSTATION)
 * Windows Server 2008          6               0  (!VER_NT_WORKSTATION)
 * Windows 7                    6               1  (VER_NT_WORKSTATION)
 * Windows Server 2008 R2       6               1  (!VER_NT_WORKSTATION)
 * Windows 8                    6               2  (VER_NT_WORKSTATION)
 * Windows Server 2012          6               2  (!VER_NT_WORKSTATION)
 * Windows Server 2012 R2       6               3  (!VER_NT_WORKSTATION)
 * Windows 10                   10              0  (VER_NT_WORKSTATION)
 * Windows 11                   10              0  (VER_NT_WORKSTATION)
 *       where (buildNumber >= 22000)
 * Windows Server 2016          10              0  (!VER_NT_WORKSTATION)
 * Windows Server 2019          10              0  (!VER_NT_WORKSTATION)
 *       where (buildNumber > 17762)
 * Windows Server 2022          10              0  (!VER_NT_WORKSTATION)
 *       where (buildNumber > 20347)
 *
 * This mapping will presumably be augmented as new Windows
 * versions are released.
 */

char* getWIN32_WINDOWS_Name(int majorVersion, int minorVersion) {
  if (majorVersion == 4) {
    switch (minorVersion) {
      case  0: return "Windows 95";
      case 10: return "Windows 98";
      case 90: return "Windows Me";
    }
    return "Windows 9X (unknown)";
   }
   return "Windows 9X (unknown)"; 
}

char* getWIN32_NT_5_Name(int minorVersion) {
  switch (minorVersion) {
    case  0: return "Windows 2000";
    case  1: return "Windows XP";
    case  2:
        /*
         * From MSDN OSVERSIONINFOEX and SYSTEM_INFO documentation:
         *
         * "Because the version numbers for Windows Server 2003
         * and Windows XP 6u4 bit are identical, you must also test
         * whether the wProductType member is VER_NT_WORKSTATION.
         * and si.wProcessorArchitecture is
         * PROCESSOR_ARCHITECTURE_AMD64 (which is 9)
         * If it is, the operating system is Windows XP 64 bit;
         * otherwise, it is Windows Server 2003."
         */
         
         // TODO: Rellace minorVersion to 21 or 22

         //if (is_workstation && is_64bit) {
            return "Windows XP"; /* 64 bit */
         // } else {
         //    return "Windows 2003";
         // }

  }
  return "Windows NT (unknown)";
}

char* getWIN32_NT_6_Name(int minorVersion) {
}

char* getWIN32_NT_10_Name(int minorVersion) {
}

char* getWIN32_NT_Name(int majorVersion, int minorVersion) {
    if (majorVersion <= 4) {
        return "Windows NT";
    }
    if (majorVersion == 5) {
        return getWIN32_NT_5_Name(minorVersion);
    }
    if (majorVersion == 6) {
        return getWIN32_NT_6_Name(minorVersion);
    }
    if (majorVersion == 10) {
        return getWIN32_NT_10_Name(minorVersion);
    }
    return "Windows NT (unknown)";
}

char* getOsName(DWORD platformId, int majorVersion, int minorVersion) {

    // WINDOWS
    if (platformId == VER_PLATFORM_WIN32_WINDOWS) {
        return getWIN32_WINDOWS_Name(majorVersion, minorVersion);
    }

    // NT
    if (platformId == VER_PLATFORM_WIN32_NT) {
        return getWIN32_NT_Name(majorVersion, minorVersion);
    }

    // UNKNOWN
    return  "Windows (unknown)";
}

//#ifdef OS_WIN
void initSysInfoWin(SysInfo& sysInfo) {

  int majorVersion = 0;
  int minorVersion = 0;
  int buildNumber = 0;

  /* tmp dir */
  WCHAR tmpdir[MAX_PATH + 1];

  GetTempPathW(MAX_PATH + 1, tmpdir);
  sysInfo.tmp_dir = _wcsdup(tmpdir);

  /* OS properties */
  char buf[100];
  boolean is_workstation;
  boolean is_64bit;
  DWORD platformId;

  ////

  OSVERSIONINFOEX ver;
  ver.dwOSVersionInfoSize = sizeof(ver);

  GetVersionEx((OSVERSIONINFO *) &ver);
  majorVersion = ver.dwMajorVersion;
  minorVersion = ver.dwMinorVersion;

   /* distinguish Windows Server 2016+ by build number */
   buildNumber = ver.dwBuildNumber;
   is_workstation = (ver.wProductType == VER_NT_WORKSTATION);
   platformId = ver.dwPlatformId;

   //sysInfo.patch_level = _strdup(ver.szCSDVersion);    

   ////

   SYSTEM_INFO si;
   ZeroMemory(&si, sizeof(SYSTEM_INFO));
   GetNativeSystemInfo(&si);

   //is_64bit = (si.wProcessorArchitecture == PROCESSOR_ARCHITECTURE_AMD64);

   sysInfo.os_name = getOsName(platformId, majorVersion, minorVersion);

}
#endif


//// UNIX ////

#ifdef OS_UNIX

void initLocaleUnix(SysInfo& sysInfo) {
  #ifdef OS_MAC
  initLocaleMac(sysInfo);
  #else
  initLocalePosix(sysInfo);
  #endif
}

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
     //sysInfo.os_arch = strdup(name.machine); 
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
       //error("System Properties init: Can't get current working directory.");
       std::cerr << "System Properties init: Can't get current working directory." << std::endl;
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
     sysInfo.tmp_dir = P_tmpdir;
   #endif

   sysInfo.file_separator = "/";
   sysInfo.line_separator = "\n";

}
#endif // OS_UNIX

void initLocale(SysInfo& sysInfo) {
  #ifdef OS_WIN
  initLocaleWin(sysInfo);
  #else
  initLocaleUnix(sysInfo);
  #endif
}

void initSysInfo(SysInfo& sysInfo) {
  #ifdef OS_WIN
  initSysInfoWin(sysInfo);
  #else
  initSysInfoUnix(sysInfo);
  #endif
}
}
