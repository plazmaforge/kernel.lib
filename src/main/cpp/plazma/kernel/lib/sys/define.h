#ifndef PLAZMA_KERNEL_LIB_SYS_DEFINE_H
#define PLAZMA_KERNEL_LIB_SYS_DEFINE_H

#ifndef CONST_STRING
#define CONST_STRING const std::string
//#define CONST_STRING static constexpr const char* const
#endif

#ifndef STATIC_CONST_STRING
#define STATIC_CONST_STRING static constexpr const char* const
#endif

#endif // PLAZMA_KERNEL_LIB_SYS_DEFINE_H