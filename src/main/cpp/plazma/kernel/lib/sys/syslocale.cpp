
#include "syslocale.h"

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

bool isEmptyLocale(char* lc) {
  return lc == nullptr || (strcmp(lc, "C") == 0);
}

bool isLocale(char* lc1, char* lc2) {
  if (lc1 == nullptr || lc2 == nullptr) {
    return false;
  }
  return strcmp(lc1, lc2) == 0;
}

Locale* parseLocale(char* lc) {
  if (lc == nullptr) {
    return nullptr;
  }

  char* p = nullptr;
  char* temp = strdup(lc);
  
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
  result->language = language;
  result->country = country;
  result->encoding = encoding;

  return result;

}



}
