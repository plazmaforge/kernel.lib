#include <array>

#include "os.h"
#include "sysexec.h"

namespace syslib {

std::string exec(const char* cmd, bool safe) {
    std::string result = "";
    std::array<char, 128> buffer;
    std::unique_ptr<FILE, decltype(&pclose)> pipe(popen(cmd, "r"), pclose);

    if (!pipe) {
        if (safe) {
            return result;
        }
        throw std::runtime_error("popen() failed!");
    }

    while (fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
        result += buffer.data();
    }

    return result;
}

std::string exec2(const char* cmd, bool safe) {
    std::string result = "";
    char buffer[128];
    FILE* pipe = popen(cmd, "r");

    if (!pipe) {
      if (safe){
        return result;
      }
      throw std::runtime_error("popen() failed!");
    }
    try {
        while (fgets(buffer, sizeof buffer, pipe) != NULL) {
            result += buffer;
        }
    } catch (...) {
        pclose(pipe);
        throw;
    }
    pclose(pipe);
    return result;
}

bool isValidCmd(const std::string& cmd) {
  std::string result = "";
  std::string checkCmd;

  #ifdef OS_MAC
    checkCmd = "which " + cmd;
    result = exec(checkCmd.c_str(), true);
    return !result.empty(); 
  #else
    return false;
  #endif

  // TODO
//   if (isMacOS()) {
//     checkCmd = "which " + cmd;
//     result = exec(checkCmd.c_str(), true);
//     return !result.empty(); 
//   }

//   return false;

}



}

