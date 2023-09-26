#ifndef PLAZMA_LIB_EXT_APPENDABLE_H
#define PLAZMA_LIB_EXT_APPENDABLE_H

#include <string>

namespace ext {

    class Appendable {

        public:

          Appendable();

          virtual ~Appendable();

          virtual void append(const std::string &str) = 0;
    };

}
#endif // PLAZMA_LIB_EXT_APPENDABLE_H