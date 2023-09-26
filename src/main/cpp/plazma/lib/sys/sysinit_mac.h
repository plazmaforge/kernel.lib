#ifndef PLAZMA_LIB_SYSINIT_MAC_H
#define PLAZMA_LIB_SYSINIT_MAC_H

#include "os.h"
#ifdef OS_MAC

//// MACOS ////

#ifdef OS_MAC_FRAMEWORK
#include <CoreFoundation/CoreFoundation.h>
#include <CoreServices/CoreServices.h>
#endif

#include "sysinfo.h"
#include "sysexec.h"
#include "plazma/lib/str/strlib.h"

namespace syslib {

#ifdef OS_MAC_FRAMEWORK
const char* getLocaleValue(CFLocaleRef locale, CFLocaleKey key) {
  CFStringRef value = (CFStringRef) CFLocaleGetValue(locale, key); 
  const char* ch = CFStringGetCStringPtr(value, kCFStringEncodingUTF8);
  if (ch == nullptr) {
    return nullptr;
  }
  return ch;
}

Locale* loadLocaleMac() {

  CFLocaleRef cflocale = CFLocaleCopyCurrent();

  const char* name = getLocaleValue(cflocale, kCFLocaleIdentifier);

  const char* language = getLocaleValue(cflocale, kCFLocaleLanguageCode);
  const char* script = getLocaleValue(cflocale, kCFLocaleScriptCode);
  const char* country = getLocaleValue(cflocale, kCFLocaleCountryCode);  
  const char* variant = getLocaleValue(cflocale, kCFLocaleVariantCode);

  //CFStringRef identifier = CFLocaleGetIdentifier(cflocale);
  //const char* ch = CFStringGetCStringPtr(identifier, kCFStringEncodingUTF8);

  CFRelease(cflocale);

  if (name == nullptr) {
    return nullptr;
  }

  Locale* result = new Locale();
  result->name = strdup(name);
  
  if (language != nullptr) {
    result->language = strdup(language);
  }
  if (script != nullptr) {
    result->script = strdup(script);
  }
  if (country != nullptr) {
    result->country = strdup(country);
  }
  if (variant != nullptr) {
    result->variant = strdup(variant);
  }

  return result;


}

void initLocaleMac_F(SysInfo& sysInfo) {

  Locale* posixLocale = nullptr; // For loading encoding
  Locale* formatLocale = loadLocaleMac(); // TODO: LC_CTYPE
  if (formatLocale == nullptr) {
    formatLocale = loadLocale(LC_CTYPE);
    posixLocale = formatLocale;
  } 
  initLocale(sysInfo, LC_CTYPE, formatLocale);

  Locale* displayLocale = loadLocaleMac(); // TODO: LC_MESSAGES
  if (displayLocale == nullptr) {
    displayLocale = loadLocale(LC_MESSAGES);
  } 
  initLocale(sysInfo, LC_MESSAGES, displayLocale);

  // Check encoding
  if (sysInfo.encoding == nullptr) {

    // Use posix locale to get encoding
    if (posixLocale == nullptr) {
        posixLocale = loadLocale(LC_CTYPE);    
    }
    char* encoding = nullptr;
    if (posixLocale != nullptr) {
        encoding = posixLocale->encoding;
    }

    if (encoding != nullptr) {
        sysInfo.encoding = strdup(encoding);
    }
  }

  if (formatLocale != nullptr) {
    delete formatLocale;

  }

  if (displayLocale != nullptr) {
    delete displayLocale;
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


}

#endif
#endif // PLAZMA_LIB_SYSINIT_MAC_H