#ifndef PLAZMA_KERNEL_LIB_SYSINIT_NIX_H
#define PLAZMA_KERNEL_LIB_SYSINIT_NIX_H

#include "os.h"
#ifdef OS_UNIX

//// UNIX ////

#include <iostream>
#include <unistd.h>
#include <dlfcn.h>

#include <sys/utsname.h>        /* For os_name and os_version */
//#include <langinfo.h>         /* For nl_langinfo */
#include <pwd.h>

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
#include "sysinit_mac.h"
#endif

namespace syslib {

void initLocaleNix(SysInfo& sysInfo) {
  #ifdef OS_MAC
  initLocaleMac(sysInfo);
  #else
  initLocalePosix(sysInfo);
  #endif
}

void initSysInfoNix(SysInfo& sysInfo) {

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
   if (sysInfo.os_arch != nullptr) {
       if (strcmp(sysInfo.os_arch, "x86_64") == 0 || strcmp(sysInfo.os_arch, "amd64") == 0) {
           sysInfo.os_arch_data = "64";
       }       
   } 

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


}

#endif
#endif // PLAZMA_KERNEL_LIB_SYSINIT_NIX_H
