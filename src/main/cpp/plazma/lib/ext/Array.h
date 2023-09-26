//#pragma once
#ifndef PLAZMA_KERNEL_LIB_EXT_ARRAY_H
#define PLAZMA_KERNEL_LIB_EXT_ARRAY_H

#include <stdio.h>

namespace ext {

    template<class T>
    class Array {

        public:
            Array(T* data, int size);
            ~Array();

            void setData(T* data, int size);
            int size();
            
            //T get(int index);
            //bool set(int index, T element);

        protected:
            void fill(T from[], T to[], int size);

        private:
            T* data;
            int size_;

    };

    template<class T>
    Array<T>::Array(T* data, int size) {
        this->data = nullptr;
        this->size = 0;
        if (data == nullptr) {
            return;
        }
        if (size < 0) {
            return;
        }
        this->size_ = size;
        this->data = new T[this->size_];

        fill(data, this->data, this->size_);
    }

    template<class T>
    Array<T>::~Array() {
        if (this->data == nullptr) {
            return;
        }
        delete[] this->data;
    }

    ////

    template<class T>
    void Array<T>::fill(T from[], T to[], int size) {
        if (from == nullptr || to == nullptr || size < 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            to[i] = from[i];
        }
    }

    ////

    template<class T>
    void Array<T>::setData(T* data, int size) {
        this->size_ = 0;
        if (data == nullptr) {
            return;
        }
        if (size < 0) {
            return;
        }
        this->size_ = size;
        this->data = new T[this->size_];
        fill(data, this->data, this->size_);

    }

    ////

    template<class T>
    int Array<T>::size() {
        return this->size_;
    }

}
#endif // PLAZMA_KERNEL_LIB_EXT_ARRAY_H