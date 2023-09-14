#ifndef PLAZMA_KERNEL_LIB_IO_IOLIB_NIX_H
#define PLAZMA_KERNEL_LIB_IO_IOLIB_NIX_H

#include "plazma/kernel/lib/sys/os.h"

#ifdef OS_UNIX

namespace iolib_nix {

   void init_utf8_console_nix() {
        setlocale(LC_ALL, "en_US.UTF-8");

        std::locale utf8locale(std::locale(), new std::codecvt_byname<wchar_t, char, mbstate_t> ("en_US.UTF-8"));
        std::wcout.imbue(utf8locale);
    }
}

#endif

#endif // PLAZMA_KERNEL_LIB_IO_IOLIB_NIX_H
