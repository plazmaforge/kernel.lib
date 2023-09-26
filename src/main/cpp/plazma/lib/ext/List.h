#ifndef PLAZMA_LIB_EXT_LIST_H
#define PLAZMA_LIB_EXT_LIST_H

#include <stdio.h>

namespace ext {

    const int EXPAND_SIZE = 10;

    template<class T>
    class List {

        public:
            List();
            List(T data[], int size);
            ~List();

            void setData(T* data, int size);
            T* getData();
            void add(T element);

            int capacity();
            int size();

            //T get(int index);
            //bool set(int index, T element);

        protected:
            int expandCapacity(int capacity);
            void fill(T from[], T to[], int size);

        private:
            T* data;
            int capacity_;
            int size_;

    };

    template<class T>
    List<T>::List() {
        this->data = nullptr;
        this->capacity_ = 0;
        this->size_ = 0;
    }

    template<class T>
    List<T>::List(T data[], int size) {
        this->data = nullptr;
        this->capacity_ = 0;
        this->size_ = 0;

        if (data == nullptr) {
            return;
        }
        if (size < 0) {
            return;
        }
        this->size_ = size;
        this->capacity_ = size;

        this->data = new T[this->size_];
        fill(data, this->data, this->size_);
    }

    template<class T>
    List<T>::~List() {
        if (this->data == nullptr) {
            return;
        }
        //cout << "DELETE[] data" << endl;
        delete[] this->data;
    }

    ////

    template<class T>
    void List<T>::fill(T from[], T to[], int size) {
        if (from == nullptr || to == nullptr || size < 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            to[i] = from[i];
        }
    }


    template<class T>
    int List<T>::expandCapacity(int capacity) {
        return capacity + EXPAND_SIZE;
    }

    ////

    template<class T>
    void List<T>::setData(T* data, int size) {

        // reset size
        this->size_ = 0;

        if (data == nullptr) {
            return;
        }
        if (size < 0) {
            return;
        }

        if (this->data == nullptr) {

            // set new capacity
            this->capacity_ = size;

            // create new data
            this->data = new T[this->capacity_];
        } else if (this->capacity_ < size) {

            // set new capacity
            this->capacity_ = size;

            // expand capacity
            this->capacity_ = expandCapacity(this->capacity_);

            // delete old data (we don't use old data)
            delete[] this->data; 

            // create new data
            this->data = new T[this->capacity_];
        }

        // set new size
        this->size = size;

        // fill new data
        fill(data, this->data, this->size_);

    }

    template<class T>
    T* List<T>::getData() {
        return this->data;
    }

    ////

    template<class T>
    void List<T>::add(T element) {

        if (this->data == nullptr) {
            // set new capacity
            this->capacity_ = EXPAND_SIZE;

            // reset size
            this->size_ = 0;

            // create new data
            this->data = new T[this->capacity_];
        } else if (this->size_ == this->capacity_) {

            // expand capacity
            this->capacity_ = expandCapacity(this->capacity_);
             
            // cteate tmp data
            T* tmp = new T[this->capacity_];

            // fill tmp data
            fill(this->data, tmp, this->size_);

            if (this->data != nullptr) {
                delete[] this->data;
                //cout << "SIZE: " << size;
            }
            
            this->data = tmp;
            
        }

        // add new element
        this->data[this->size_] = element;

        // increment size
        this->size_++;
    }

    ////

    template<class T>
    int List<T>::capacity() {
        return this->capacity_;
    }


    template<class T>
    int List<T>::size() {
        return this->size_;
    }


}

#endif // PLAZMA_LIB_EXT_LIST_H