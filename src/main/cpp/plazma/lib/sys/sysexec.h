#ifndef PLAZMA_KERNEL_LIB_SYSEXEC_H
#define PLAZMA_KERNEL_LIB_SYSEXEC_H

#include <string>

namespace syslib {

std::string exec(const char* cmd, bool safe);

std::string exec2(const char* cmd, bool safe);

bool isValidCmd(const std::string& cmd);

}
#endif // PLAZMA_KERNEL_LIB_SYSEXEC_H