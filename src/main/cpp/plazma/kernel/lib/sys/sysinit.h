#ifndef PLAZMA_KERNEL_LIB_SYSINIT_H
#define PLAZMA_KERNEL_LIB_SYSINIT_H

#include <string>
#include <locale>

#include "os.h"
#include "syslocale.h"
#include "sysexec.h"

#ifdef OS_WIN
#include <tchar.h>
typedef WCHAR nchar;
#else
typedef char nchar;
#endif

namespace syslib {

  typedef struct {

    char *os_name;
    char *os_version;
    char *os_release; // *
    char *os_arch;

    nchar *user_name;
    nchar *user_home;
    nchar *user_dir;
    nchar *tmp_dir;

    char *file_separator;
    char *path_separator; // ?
    char *line_separator;

    char *locale;

    char *format_language;
    char *format_script;
    char *format_country;
    char *format_variant;

    char *display_language;    
    char *display_script;    
    char *display_country;    
    char *display_variant;
    char *encoding;

    char *stdout_encoding;
    char *stderr_encoding;

    char *unicode_encoding;     // The default endianness of unicode i.e. UnicodeBig or UnicodeLittle
    const char *cpu_isalist;    // list of supported instruction sets
    char *cpu_endian;           // endianness of platform

    bool init = false;

  } SysInfo;


   void initLocale(SysInfo& sysInfo);

   void initSysInfo(SysInfo& sysInfo);

}

#endif // PLAZMA_KERNEL_LIB_SYSINIT_H