#ifndef PLAZMA_LIB_TEST_H
#define PLAZMA_LIB_TEST_H

#include <string>
#include <vector>
#include <iostream>

#define _ASSERT(expression)                                  \
if (!expression) {                                           \
    fail(__FILE__, __LINE__, #expression);                   \
}                     

#define _ASSERT_OP(a, op, b)                                 \
if (!(a op b)) {                                             \    
    fail(__FILE__, __LINE__, toString(a), #op, toString(b)); \
}

////

#define ASSERT(expression) _ASSERT(expression) 

#define ASSERT_TRUE(expression) _ASSERT(expression)

#define ASSERT_FALSE(expression) _ASSERT(!expression)

////

#define ASSERT_EQ(a, b) _ASSERT_OP(a, ==, b)

#define ASSERT_NE(a, b) _ASSERT_OP(a, !=, b)


void fail(const char* file, const int line, const char* message) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s\n", file, line, message); 
}

// op
void fail(const char* file, const int line, const char* a, const char* op, const char* b) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s %s %s\n", file, line, a, op, b); 
}

// op
void fail(const char* file, const int line, const std::string& a, const char* op, const std::string& b) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s %s %s\n", file, line, a.c_str(), op, b.c_str()); 
}

std::string toString(const std::string& value) {
    return "\"" + value + "\"";
} 

std::string toString(const short value) {
    return std::to_string(value);
}

std::string toString(const int value) {
    return std::to_string(value);
} 

std::string toString(const long value) {
    return std::to_string(value);
}

std::string toString(const float value) {
    return std::to_string(value);
}

std::string toString(const double value) {
    return std::to_string(value);
}

#endif //PLAZMA_LIB_TEST_H