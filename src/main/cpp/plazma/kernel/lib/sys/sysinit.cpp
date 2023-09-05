
#include "sysinit.h" // importatnt before OS_<NAME> detection
#include "sysexec.h"

#ifdef OS_WIN
#include "sysinit_win.h"
#else
#include "sysinit_nix.h"
#endif

#include "plazma/kernel/lib/str/strlib.h"

namespace syslib {

void initLocale(SysInfo& sysInfo) {
  #ifdef OS_WIN
  initLocaleWin(sysInfo);
  #else
  initLocaleNix(sysInfo);
  #endif
}

void initSysInfo(SysInfo& sysInfo) {
  #ifdef OS_WIN
  initSysInfoWin(sysInfo);
  #else
  initSysInfoNix(sysInfo);
  #endif
}

}
