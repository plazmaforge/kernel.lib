#include <string>

#include "plazma/kernel/lib/sys/syslib.h"

#include "fslib.h"

namespace fslib {

  // fs
  std::string getFileSeparator() {
    return syslib::isWindows() ? "\\" : "/"; 
  }

  // fs
  std::string getOptionalPath(const std::string& path1, const std::string& path2) {
    if (path1.empty() && path2.empty()) {
      return "";
    }
    if (path1.empty()) {
      return path2;
    }
    if (path2.empty()) {
      return path1;
    }
    return path1 + getFileSeparator() + path2;
  }

  // fs
  std::string generateFileName(std::string& dir, std::string& filePrefix, int number, std::string& fileExt) {
    std::string path = getOptionalPath(dir, filePrefix);
    return generateFileName(path, number, fileExt);
  }

  // fs
  std::string generateFileName(std::string& filePrefix, int number, std::string& fileExt) {
    return filePrefix + std::to_string(number) + fileExt;
  }


}