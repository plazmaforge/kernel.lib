#ifndef PLAZMA_KERNEL_LIB_SYS_OS_H
#define PLAZMA_KERNEL_LIB_SYS_OS_H


#if defined __linux__
        #define OS_UNIX
        #define OS_LINUX
        #ifdef ANDROID
                #define OS_ANDROID
        #else
                #define OS_LINUX_MAINLINE // obsolete (use `!defined OS_ANDROID`)
        #endif

//#ifdef __APPLE__ || __MACH__
#elif defined __APPLE__ && defined __MACH__
        #define OS_UNIX
        #define OS_APPLE
        #define OS_MAC
        #define OS_MAC_FRAMEWORK // CoreFoundation ...
        #define OS_MAC_FRAMEWORK_SYSTEM
        #define OS_MAC_FRAMEWORK_LOCALE

#elif defined __unix__
        #define OS_UNIX
        //#include <sys/param.h>
        #if defined BSD
                #define OS_BSD
        #endif

#elif defined _WIN32 || defined _WIN64 || defined __CYGWIN__
        #define OS_WIN
        //#ifndef OS_WIN_APIVER
        //        #define OS_WIN_APIVER 0x0600
        //#endif
        //#define OS_WIN  OS_WIN_APIVER

#else
        #error "This OS is not supported"
#endif

#endif