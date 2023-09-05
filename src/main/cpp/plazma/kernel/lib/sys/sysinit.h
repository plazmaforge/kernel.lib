#ifndef PLAZMA_KERNEL_LIB_SYSINIT_H
#define PLAZMA_KERNEL_LIB_SYSINIT_H

//#include <string>
//#include <locale>

#include "os.h"
//#include "syslocale.h"
#include "sysinfo.h"
//#include "sysexec.h"

namespace syslib {


   void initLocale(SysInfo& sysInfo);

   void initSysInfo(SysInfo& sysInfo);

}

#endif // PLAZMA_KERNEL_LIB_SYSINIT_H