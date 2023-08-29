#ifndef PLAZMA_KERNEL_LIB_FS_FSLIB_H
#define PLAZMA_KERNEL_LIB_FS_FSLIB_H

#include <string>

namespace fslib {

    // fs
    std::string getFileSeparator();

    // fs
    std::string getOptionalPath(const std::string& path1, const std::string& path2);

    // fs
    std::string generateFileName(const std::string& dir, const std::string& filePrefix, int number, const std::string& fileExt);

    // fs
    std::string generateFileName(const std::string& filePrefix, int number, const std::string& fileExt);

}
#endif // PLAZMA_KERNEL_LIB_FS_FSLIB_H