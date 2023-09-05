#ifndef PLAZMA_KERNEL_LIB_SYSINIT_H
#define PLAZMA_KERNEL_LIB_SYSINIT_H

#include "os.h"
#include "sysinfo.h"

namespace syslib {

   void initLocale(SysInfo& sysInfo);

   void initSysInfo(SysInfo& sysInfo);

}

#endif // PLAZMA_KERNEL_LIB_SYSINIT_H