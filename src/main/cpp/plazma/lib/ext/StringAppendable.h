#ifndef PLAZMA_LIB_EXT_STRING_APPENDABLE_H
#define PLAZMA_LIB_EXT_STRING_APPENDABLE_H

#include <string>
#include "Appendable.h"

namespace ext {

    class StringAppendable : public Appendable {

        public:

          StringAppendable();

          ~StringAppendable();

          virtual void append(const std::string &str);

          virtual std::string toString();

        private:

          std::string buf;

    };

}
#endif // PLAZMA_LIB_EXT_STRING_APPENDABLE_H