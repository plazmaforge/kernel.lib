#ifndef PLAZMA_LIB_DATA_NODE_ATTRBUTE_LIST_H
#define PLAZMA_LIB_DATA_NODE_ATTRBUTE_LIST_H

#include <string>
#include <vector>

namespace node {

    class AttributeList {

        public:

            AttributeList();

            ~AttributeList();

            void addAttribute(const std::string &name, const std::string &value);

            void removeAttribute(const std::string &name);

            std::string getAttribute(const std::string &name);

            std::string getAttributeName(const int index);

            std::string getAttributeValue(const int index);

            std::string getAttributeValue(const std::string &name);

            bool hasAttributes();

            int getAttributeCount();

            std::string toString();

        protected:

            void destroy();

        private:

            std::vector<std::string>* names;
            std::vector<std::string>* values;


    };

}
#endif // PLAZMA_LIB_DATA_NODE_ATTRBUTE_LIST_H