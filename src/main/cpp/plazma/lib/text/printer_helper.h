#ifndef PLAZMA_KERNEL_LIB_TEXT_PRINTER_HELPER_H
#define PLAZMA_KERNEL_LIB_TEXT_PRINTER_HELPER_H

#include <string>

#include "plazma/lib/ext/ustring.h"

namespace text {

    bool eq(const char* str1, const char* str2);

    ////

    void println(const std::string &message);

    void println(const ext::ustring &message);


    ////

    void println(const std::string &message1, const std::string &message2);

    void println(const std::string &message1, const std::string &message2, const std::string &message3);


    ////

    void println(bool flag, const std::string &message);

    void println(bool flag, const std::string &message1, const std::string &message2);

    void println(bool flag, const std::string &message1, const std::string &message2, const std::string &message3);

    void println(bool flag, const std::string &message1, const std::string &message2, const std::string &message3, const std::string &message4);

    ////

    void printEvent(bool flag, const std::string &message);

    void printEvent2(bool flag, const std::string &message1, const ext::ustring &message2);

    void printEvent(bool flag, const std::string &message1, const std::string &message2);

    void printEventItem(bool flag, const std::string &message);

    void printEventItem(bool flag, const std::string &message1, const std::string &message2);

    void printEventItem(bool flag, const std::string &message1, const std::string &message2, const std::string &message3, const std::string &message4);

    ////

    void info(const std::string &message);

    ////

    void warn(const std::string &message);

    ////

    void error(const std::string &message);

    void error(const std::string &message1, const std::string &message2);

    void error(const std::string &message1, const std::string &message2, const std::string &message3);

    ////

    std::string toSafeString(char* array);

    
}

#endif // PLAZMA_KERNEL_LIB_TEXT_PRINTER_HELPER_H