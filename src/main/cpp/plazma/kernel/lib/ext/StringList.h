//#pragma once
#ifndef PLAZMA_KERNEL_LIB_EXT_STRING_LIST_H
#define PLAZMA_KERNEL_LIB_EXT_STRING_LIST_H

namespace ext {

    const int STRING_LIST_EXPAND_SIZE = 1024; // 512; // 1024; //512;

    //template<class T>
    class StringList {

        public:
            StringList();
            StringList(char** data, int size);
            ~StringList();

            void setData(char** data, int size);
            char** getData();

            void add(char* element);
            void add(const char* element, int offset, int count);

            char* get(int index);
            bool set(int index, char* element);

            int capacity();
            int size();

        protected:
            int expandCapacity(int capacity);
            void fill(char** from, char** to, int size);
            void fill(char* from, char* to, int size);

        private:
            char** data;
            int capacity_;
            int size_;

    };

}

#endif // PLAZMA_KERNEL_LIB_EXT_STRING_LIST_H