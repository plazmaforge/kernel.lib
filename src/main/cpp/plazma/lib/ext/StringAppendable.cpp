#include <string>
#include "StringAppendable.h"

namespace ext {

    StringAppendable::StringAppendable() {

    }

    StringAppendable::~StringAppendable() {

    }

    void StringAppendable::append(const std::string &str) {
        buf.append(str);
    }

    std::string StringAppendable::toString() {
        return buf;
    }

}
