#ifndef PLAZMA_LIB_DATA_JSON_JSON_STRING_APPENDABLE_H
#define PLAZMA_LIB_DATA_JSON_JSON_STRING_APPENDABLE_H

#include <string>
#include "JsonAppendable.h"

namespace json {

    class JsonStringAppendable : public JsonAppendable {

        public:

          JsonStringAppendable();

          ~JsonStringAppendable();

          ////

          void setColorized(bool colorized);

          void append(const std::string &str);

          void appendObject(const std::string &str);

          void appendArray(const std::string &str);

          void appendAttributeName(const std::string &str);

          void appendAttributeValue(const std::string &str);

          void appendText(const std::string &str);

          ////

          void appendWrap(const std::string &str, int color);

          std::string toString();

        public:

          bool colorized = false;

        private:

          std::string buf;

    };

}
#endif // PLAZMA_LIB_DATA_JSON_JSON_STRING_APPENDABLE_H