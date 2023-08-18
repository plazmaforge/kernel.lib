#include <string>

#include "printer_helper.h"

#include "plazma/kernel/lib/str/strlib.h"
#include "plazma/kernel/lib/sys/syslib.h"

namespace text {

    static const char* EVENT_PREFIX   = "=> ";
    static const char* EVENT_ITEM_PREFIX   = "   ";

    bool eq(const char* str1, const char* str2) {
        return strlib::equals(str1, str2);
    }

    void println(const std::string &message) {
        syslib::println(message);
    }

    void println(const ext::ustring &message) {
        syslib::println(message);
    }

    void println(const std::string &message1, const std::string &message2) {
        syslib::print(message1);
        syslib::println(message2);
    }

    void println(const std::string &message1, const std::string &message2, const std::string &message3) {
        syslib::print(message1);
        syslib::print(message2);
        syslib::println(message3);
    }

    void println(const std::string &message1, const std::string &message2, const std::string &message3, const std::string &message4) {
        syslib::print(message1);
        syslib::print(message2);
        syslib::print(message3);
        syslib::println(message4);
    }

    ////

    void println(bool flag, const std::string &message) {
        if (!flag) {
            return;
        }
        println(message);
    }

    void println(bool flag, const std::string &message1, const std::string &message2) {
        if (!flag) {
            return;
        }
        println(message1, message2);
    }

    void println(bool flag, const std::string &message1, const std::string &message2, const std::string &message3) {
        if (!flag) {
            return;
        }
        println(message1, message2, message3);
    }

    ////

    void printEvent(bool flag, const std::string &message) {
        if (!flag) {
            return;
        }
        syslib::print(EVENT_PREFIX);
        syslib::print(message);
        syslib::println();
    }

    void printEvent(bool flag, const std::string &message1, const std::string &message2) {
        if (!flag) {
            return;
        }
        syslib::print(EVENT_PREFIX);
        syslib::print(message1);
        syslib::print(message2);
        syslib::println();
    }

    void printEvent2(bool flag, const std::string &message1, const ext::ustring &message2) {
        if (!flag) {
            return;
        }
        syslib::print(EVENT_PREFIX);
        syslib::print(message1);
        syslib::print(message2);
        syslib::println();
    }

    void printEventItem(bool flag, const std::string &message) {
        if (!flag) {
            return;
        }
        println(EVENT_ITEM_PREFIX + message);
    }

    void printEventItem(bool flag, const std::string &message1, const std::string &message2) {
        if (!flag) {
            return;
        }
        println(EVENT_ITEM_PREFIX + message1, message2);
    }

    void printEventItem(bool flag, const std::string &message1, const std::string &message2, const std::string &message3, const std::string &message4) {
        if (!flag) {
            return;
        }
        println(EVENT_ITEM_PREFIX + message1, message2, message3, message4);
    }

    ////

    void info(const std::string &message) {
        syslib::info(message);
    }

    ////

    void warn(const std::string &message) {
        syslib::warn(message);
    }

    ////

    void error(const std::string &message) {
        syslib::error(message);
    }

    void error(const std::string &message1, const std::string &message2) {
        syslib::error(message1 + message2);
    }

    void error(const std::string &message1, const std::string &message2, const std::string &message3) {
        syslib::error(message1 + message2 + message3);
    }

    ////

    std::string toSafeString(char* array) {
        if (array == nullptr) {
            return "";
        }
        return array;
    }


    
}