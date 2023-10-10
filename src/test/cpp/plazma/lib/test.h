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

//// __func__

#define ASSERT(expression) _ASSERT(expression) 

#define ASSERT_TRUE(expression) _ASSERT(expression)

#define ASSERT_FALSE(expression) _ASSERT(!expression)

////

#define ASSERT_EQ(a, b) _ASSERT_OP(a, ==, b)

#define ASSERT_NE(a, b) _ASSERT_OP(a, !=, b)

#define TEST(name)                                           \
void test_##name()                                           \

#define TEST_ALL(name)                                       \
void test_##name_all()                                       \

#define SET_ALL_(name)                                       \
void set_##name_all()                                        \

#define INIT(name)                                        \
void test_##name_all() {                                     \
  runTestCase(#name);                                        \
}                                                            \
void set_##name_all()                                        \

#define SET_ALL(name)                                        \
set_##name_all();                                           \

#define RUN_ALL(name)                                        \
test_##name_all();                                           \

#define RUN(name)                                            \
test_##name();                                               \

#define SET_CASE(name)                                       \
registerTestCase(__FILE__, #name);                           \

#define SET_TEST(name)                                       \
registerTest(__FILE__, #name, &test_##name);                 \

struct Error {
    char* file;
    char* func;
    int line;
};

struct Test {
    char* name;
    void (*func)();
};

struct TestCase {
    char* file;
    char* name;
    int failed = 0;
    std::vector<Test*> tests;
};

std::vector<TestCase*> registeredTestCases;

TestCase* findTestCaseByFile(const char* file) {
    if (registeredTestCases.empty()) {
        return NULL;
    }
    TestCase* testCase = NULL;
    for (int i = 0; i < registeredTestCases.size(); i++) {
        testCase = registeredTestCases[i];
        if (strcmp(testCase->file, file) == 0) {
            return testCase;
        }
    }
    return NULL;
}

TestCase* findTestCaseByName(const char* name) {
    if (registeredTestCases.empty()) {
        fprintf(stderr, "[ERROR] Registered TestCases is empty\n"); 
        return NULL;
    }
    TestCase* testCase = NULL;
    for (int i = 0; i < registeredTestCases.size(); i++) {
        testCase = registeredTestCases[i];
        if (strcmp(testCase->name, name) == 0) {
            return testCase;
        }
    }
    return NULL;
}

void registerTestCase(const char* file, const char* name) {
    TestCase* testCase = new TestCase();
    if (file) {
        testCase->file = (char*) malloc(strlen(file)); 
        strcpy(testCase->file, file);
    }
    if (name) {
        testCase->name = (char*) malloc(strlen(name)); 
        strcpy(testCase->name, name);
    }
    registeredTestCases.push_back(testCase);
}

void registerTest(const char* file, const char* name, void (*func)()) {
    TestCase* testCase = findTestCaseByFile(file);
    if (testCase == NULL) {
        return;
    }
    Test* test = new Test();
    if (name) {
        test->name = (char*) malloc(strlen(name)); 
        strcpy(test->name, name);
    }
    test->func = func;
    testCase->tests.push_back(test);
}

void runTest(Test* test) {
    if (test == NULL) {
        return;
    }
    //if (test->func == NULL) {
    //    return;
    //}
    test->func();
}

void runTestCase(const char* name) {
    TestCase* testCase = findTestCaseByName(name);
    if (testCase == NULL) {
        fprintf(stderr, "[ERROR] TestCase not found: %s\n", name); 
        return;
    }
    fprintf(stdout, "[RUN   ] '%s'\n", name); 
    std::vector<Test*> tests = testCase->tests;
    if (tests.empty()) {
        return;
    }
    Test* test = NULL;
    for (int i = 0; i < tests.size(); i++) {
        test = tests[i];
        runTest(test);
    }
    if (testCase->failed > 0) {
        fprintf(stdout, "[FAILED] '%s'\n", name); 
    } else {
        fprintf(stdout, "[PASSED] '%s'\n", name); 
    }
}

void printResult() {
    if (registeredTestCases.empty()) {
        fprintf(stdout, "No Tests\n"); 
        return;
    }
    TestCase* testCase = NULL;
    int total = 0;
    int failed = 0;
    for (int i = 0; i < registeredTestCases.size(); i++) {
        testCase = registeredTestCases[i];
        total += testCase->tests.size();
        failed += testCase->failed;
    }

    if (failed > 0) {
        fprintf(stdout, "%d TEST FAILED\n", failed); 
    } else {
        fprintf(stdout, "%d TEST PASSED\n", total); 
    }
}

void registerError(const char* file, const char* func, const int line) {
    TestCase* testCase = findTestCaseByFile(file);
    if (testCase) {
        testCase->failed++;
    }
}

void fail(const char* file, const int line, const char* message) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s\n", file, line, message); 
    registerError(file, NULL, line);
}

// op
void fail(const char* file, const int line, const char* a, const char* op, const char* b) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s %s %s\n", file, line, a, op, b); 
    registerError(file, NULL, line);
}

// op
void fail(const char* file, const int line, const std::string& a, const char* op, const std::string& b) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s %s %s\n", file, line, a.c_str(), op, b.c_str()); 
    registerError(file, NULL, line);
}

////

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