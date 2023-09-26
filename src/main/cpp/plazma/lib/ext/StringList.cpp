#include <stdio.h>

#include "StringList.h"

namespace ext {

    //template<class T>
    StringList::StringList() {
        this->data = nullptr;
        this->capacity_ = 0;
        this->size_ = 0;
    }

    //template<class T>
    StringList::StringList(char** data, int size) {
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

        this->data = new char*[this->size_];
        fill(data, this->data, this->size_);

    }

    //template<class T>
    StringList::~StringList() {
        if (this->data == nullptr) {
            return;
        }
        //cout << "DELETE[] data" << endl;
        delete[] this->data;
    }

    ////

    void StringList::fill(char** from, char** to, int size) {
        if (from == nullptr || to == nullptr || size < 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            to[i] = from[i];
            
            //int str_size = _strlen(from[i]); // TODO
            //to[i] = new char[str_size]; // ? '\0'
            //fill(from[i], to[i], str_size);
        }
    }

    void StringList::fill(char* from, char* to, int size) {
        if (from == nullptr || to == nullptr || size < 1) {
            return;
        }
        for (int i = 0; i < size; i++) {
            to[i] = from[i];
        }
    }

    int StringList::expandCapacity(int capacity) {
        return capacity + STRING_LIST_EXPAND_SIZE;
    }

    ////

    void StringList::setData(char** data, int size) {

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
            this->data = new char*[this->capacity_];
        } else if (this->capacity_ < size) {

            // set new capacity
            this->capacity_ = size;

            // expand capacity
            this->capacity_ = expandCapacity(this->capacity_);

            // delete old data (we don't use old data)
            delete[] this->data; 

            // create new data
            this->data = new char*[this->capacity_];
        }

        // set new size
        this->size_ = size;

        // fill new data
        fill(data, this->data, this->size_);

    }

    char** StringList::getData() {
        return this->data;
    }

    ////

    void StringList::add(char* element) {

        if (this->data == nullptr) {

            // set new capacity
            this->capacity_ = STRING_LIST_EXPAND_SIZE;

            // reset size
            this->size_ = 0;

            // create new data
            this->data = new char*[this->capacity_];
        } else if (this->size_ == this->capacity_) {

            // expand capacity
            this->capacity_ = expandCapacity(this->capacity_);
             
            // cteate tmp data
            char** tmp = new char*[this->capacity_];

            // fill tmp data
            fill(this->data, tmp, this->size_);

            //delete[] this->data;

            if (this->data != nullptr) {
                delete[] this->data;
            }
            
            this->data = tmp;
            
        }

        // add new element
        // TODO
        this->data[this->size_] = element;

        // increment size
        this->size_++;
    }

    ////

    void StringList::add(const char* element, int offset, int count) {

        if (this->data == nullptr) {

            // set new capacity
            this->capacity_ = STRING_LIST_EXPAND_SIZE;

            // reset size
            this->size_ = 0;

            // create new data
            this->data = new char*[this->capacity_];
        } else if (this->size_ == this->capacity_) {

            // expand capacity
            this->capacity_ = expandCapacity(this->capacity_);
             
            // cteate tmp data
            char** tmp = new char*[this->capacity_];

            // fill tmp data
            fill(this->data, tmp, this->size_);

            //delete[] this->data;

            if (this->data != nullptr) {
                delete[] this->data;
            }
            
            this->data = tmp;
            
        }

        // add new element
        if (offset < 0 || count < 1) {
            this->data[this->size_] = nullptr;
        } else {
            char* curr = new char[count + 1];
            //char* curr = new char[count];
            for (int i = 0; i < count; i++) {
                curr[i] = element[i + offset];
            }
            curr[count] = '\0';
            this->data[this->size_] = curr;
        }

        // increment size
        this->size_++;
    }

    ////

    char* StringList::get(int index) {
        // TODO: check index
        if (this->data == nullptr) {
            return nullptr;
        }
        return this->data[index];
    }

    bool StringList::set(int index, char* element) {
        // TODO: check index
        if (this->data == nullptr) {
            return false;
        }

        // TODO: Copy chars like in add(...)
        this->data[index] = element;
        return true;
    }

    ////

    int StringList::capacity() {
        return this->capacity_;
    }

    int StringList::size() {
        return this->size_;
    }


}