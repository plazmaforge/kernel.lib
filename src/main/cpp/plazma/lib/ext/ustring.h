#ifndef PLAZMA_LIB_EXT_USTRING_H
#define PLAZMA_LIB_EXT_USTRING_H

#include <string>

namespace ext {

    #ifdef _WIN32

    // 32 bit char (Windows only)
    typedef std::wstring ustring;
    typedef wchar_t uchar;
    
    #else

    // 16 bit char
    typedef std::u16string ustring;
    typedef char16_t uchar;

    #endif

}

#endif // PLAZMA_LIB_EXT_USTRING_H
