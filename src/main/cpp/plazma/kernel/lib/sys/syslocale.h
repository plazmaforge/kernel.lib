#ifndef PLAZMA_KERNEL_LIB_SYSLOCALE_H
#define PLAZMA_KERNEL_LIB_SYSLOCALE_H

#include <string>
#include <locale>

namespace syslib {

typedef struct {

    char* locale;

    char* language;
    char* script;
    char* country;
    char* variant;
    char* encoding;

} Locale;

  void setDefaultLocale();

  char* getLocale(int cat);

  char* getLocale();

  bool isEmptyLocale(char* lc);

  bool isLocale(char* lc1, char* lc2);

  Locale* parseLocale(char* lc);


}

#endif // PLAZMA_KERNEL_LIB_SYSLOCALE_H