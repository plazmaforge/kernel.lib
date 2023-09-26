#ifndef PLAZMA_KERNEL_LIB_SYS_EXPORT_H
#define PLAZMA_KERNEL_LIB_SYS_EXPORT_H

#if defined(__cplusplus)
  #define EXTERN_C extern "C"
#else 
  #define EXTERN_C 
#endif

#if defined(_WIN32)
  #define METHOD_EXPORT_C __declspec(dllexport) 
#else
  #define METHOD_EXPORT_C __attribute__ ((visibility ("default")))
#endif

#define LIBRARY_EXPORT_C EXTERN_C METHOD_EXPORT_C

//#if defined(_WIN32)
//  #define LIBRARY_EXPORT_C EXTERN_C __declspec(dllexport) 
//#else
//  #define LIBRARY_EXPORT_C EXTERN_C __attribute__ ((visibility ("default")))
//#endif

#endif // PLAZMA_KERNEL_LIB_SYS_EXPORT_H