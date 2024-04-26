
#include "syslocale.h"

#include <string>
#include <cstring> // WIN32: strcmp, strchr, strdup, strlen

namespace syslib {

void setDefaultLocale() {
  setlocale(LC_ALL, "");
}

char* getLocale(int cat) {
  return setlocale(cat, NULL);
}

char* getLocale() {
  return getLocale(LC_CTYPE);
}

bool isEmptyLocale(char* locale) {
  return locale == nullptr || (strcmp(locale, "C") == 0);
}

bool equalsLocale(char* locale1, char* locale2) {
  if (locale1 == nullptr || locale2 == nullptr) {
    return false;
  }
  return strcmp(locale1, locale2) == 0;
}

Locale* parseLocale(char* name) {
  if (name == nullptr) {
    return nullptr;
  }

  char* p = nullptr;
  char* temp = strdup(name);
  
  char* language = nullptr;
  char* country = nullptr;
  //char* encoding_variant = nullptr;
  char* encoding = nullptr;

  if ((encoding = strchr(temp, '.')) != NULL) {
    *encoding++ = '\0';
  } else if ((encoding = strchr(temp, '@')) != NULL) {
    *encoding++ = '\0';
  }

  if ((country = strchr(temp, '_')) != NULL) {
    *country++ = '\0';
    language = temp;
  }

  if (language == NULL && country == NULL && encoding == NULL) {
    int len = strlen(temp);
    if (len == 2) {
      if (islower(temp[0]) && islower(temp[1])) {
        language = temp;
      } else {
        country = temp;
      }      
    } else {
      encoding = temp;
    }
    
  }
  
  //std::cout << "Language : " << (language ? language : "?") << std::endl;
  //std::cout << "Country  : " << (country ? country : "?") << std::endl;
  //std::cout << "Encoding : " << (encoding ? encoding : "?") << std::endl;

  Locale* result = new Locale();
  result->name = strdup(name);
  result->language = language;
  result->country = country;
  result->encoding = encoding;

  return result;

}

Locale* loadLocale(int cat) {
  char* name = getLocale(cat /*LC_CTYPE*/); 
  if (name == nullptr) {
    return nullptr;
  }
  return parseLocale(name);;
}

}
