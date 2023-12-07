#ifndef PLAZMA_LIB_TEST_H
#define PLAZMA_LIB_TEST_H

#include <string>
#include <cstring>
#include <vector>
#include <iostream>

#define _ASSERT(expression)                                            \
if (!expression) {                                                     \
    fail(__FILE__, __LINE__, __func__, #expression);                   \
}                     

#define _ASSERT_OP(a, op, b)                                           \
if (!(a op b)) {                                                       \
    fail(__FILE__, __LINE__, __func__, toString(a), #op, toString(b)); \
}

#define ASSERT(expression) _ASSERT(expression) 

#define ASSERT_TRUE(expression) _ASSERT(expression)

#define ASSERT_FALSE(expression) _ASSERT(!expression)

////

#define ASSERT_EQ(a, b) _ASSERT_OP(a, ==, b)

#define ASSERT_NE(a, b) _ASSERT_OP(a, !=, b)

#define TEST(name)                                                     \
void test_##name()                                                     \

//#define TEST(case, name)
//void test_##case##_##name()

//#define TEST_ALL(name)
//void test_##name##_all()

//#define SET_ALL_(name)
//void set_##name##_all()

//void set_##name##_all();                                             

#define INIT(name)                                                     \
void set_##name##_case();                                              \
void set_##name##_tests();                                             \
void test_##name##_all();                                              \
                                                                       \
void test_##name##_all() {                                             \
  runTestCase(__FILE__, #name);                                        \
}                                                                      \
void set_##name##_case() {                                             \
  registerTestCase(__FILE__, #name, &test_##name##_all);               \
}                                                                      \
void set_##name##_all() {                                              \
  set_##name##_case();                                                 \
  set_##name##_tests();                                                \
}                                                                      \
void set_##name##_tests()                                              \

//#define SET_CASE(name)
//registerTestCase(__FILE__, #name);

#define SET_TEST(name)                                                 \
registerTest(__FILE__, strcatnew("test_", #name), &test_##name);       \

//#define SET_ALL(name)
//set_##name##_all();

//#define RUN_ALL(name)
//test_##name##_all();

//#define RUN(name)
//test_##name();


struct Error;
struct Test;
struct TestCase;

static std::vector<TestCase*> testCases;

////

struct Error {
    char* file = NULL;
    char* func = NULL;
    int line = 0;
};

struct Test {
    TestCase* parent = NULL;
    char* name = NULL;
    void (*func)() = NULL;
    bool started = false;
    bool failed = false;
    int asserted = 0;
};

struct TestCase {
    char* file = NULL;
    char* name = NULL;
    void (*setall)() = NULL;
    void (*setup)() = NULL;
    void (*setdown)() = NULL;
    int started = 0;
    int failed = 0;
    int asserted = 0;
    std::vector<Test*> tests;
};


char* strnew(const int len);

// strdup, strcpynew
char* strnew(const char* str);

char* strcatnew(const char* str1, const char* str2);


//////////////

TestCase* findTestCaseByFile(const char* file);

TestCase* findTestCaseByName(const char* name);

Test* findTestByName(TestCase* testCase, const char* name);

void registerTestCase(const char* file, const char* name, void (*setall)());

void registerTest(const char* file, const char* name, void (*func)());

void runTest(Test* test);

void fill(char* array, int len, char value);

void fill(char* array, int len);

int getPadLen(int len);

void printTotalResult();

void printTestResult(const char* file, const char* name, Test* test);

void printTestCaseResult(const char* file, const char* name, TestCase* testCase, bool isPrintTest);


void runTestCase(TestCase* testCase);

void runTestCase(const char* file, const char* name);

void runAll();

void registerError(const char* file, const int line, const char* func);

void fail(const char* file, const int line, const char* func, const char* message);

// op
void fail(const char* file, const int line, const char* func, const char* a, const char* op, const char* b);

// op
void fail(const char* file, const int line, const char* func, const std::string& a, const char* op, const std::string& b);

////

std::string toString(const std::string& value);

std::string toString(const std::vector<std::string>& values);

std::string toString(const short value);

std::string toString(const int value);

std::string toString(const long value);

std::string toString(const float value);

std::string toString(const double value);

///////////////

#endif //PLAZMA_LIB_TEST_H