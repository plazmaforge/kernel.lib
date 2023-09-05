
#include "sysinfo.h"

#include <locale>

namespace syslib {

void initLocale(SysInfo& sysInfo, int cat, Locale* locale) {
  if (locale == nullptr) {
    return;
  }
  //if (cat != LC_CTYPE && cat != LC_MESSAGES) {
  //  return;
  //}

  if (cat == LC_CTYPE) {
    sysInfo.format_locale = locale->name;
    sysInfo.format_language = locale->language;
    sysInfo.format_script = locale->script;
    sysInfo.format_country = locale->country;
    sysInfo.format_variant= locale->variant;
    sysInfo.encoding = locale->encoding; // For format only
    
  } else /*if (cat == LC_MESSAGES)*/ {

    sysInfo.display_locale = locale->name;
    sysInfo.display_language = locale->language;
    sysInfo.display_script = locale->script;
    sysInfo.display_country = locale->country;
    sysInfo.display_variant= locale->variant;

  }

  return;
}

void initLocale(SysInfo& sysInfo, int cat, char* name) {
  if (name == nullptr) {
    return;
  }
  //if (cat != LC_CTYPE && cat != LC_MESSAGES) {
  //  return;
  //}

  Locale* locale = parseLocale(name);
  if (locale == nullptr) {
    return;
  }

  initLocale(sysInfo, cat, locale);

  delete locale;
  return;
}

//void initLocale(SysInfo& sysInfo, char* name) {
//  initLocale(sysInfo, LC_CTYPE, name);
//}

// "en_US.UTF-8"

void initDefaultLocale(SysInfo& sysInfo) {
  sysInfo.format_locale = "en_US.UTF-8";
  sysInfo.format_language = "en";
  sysInfo.format_country = "US";
  sysInfo.encoding = "UTF-8";

  sysInfo.display_locale = "en_US.UTF-8";
  sysInfo.display_language = "en";
  sysInfo.display_country = "US";

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

  initLocale(sysInfo, LC_CTYPE, lc);

}

}
