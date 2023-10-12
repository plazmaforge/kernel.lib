#ifndef PLAZMA_LIB_TEST_H
#define PLAZMA_LIB_TEST_H

#include <string>
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

//// __func__

#define ASSERT(expression) _ASSERT(expression) 

#define ASSERT_TRUE(expression) _ASSERT(expression)

#define ASSERT_FALSE(expression) _ASSERT(!expression)

////

#define ASSERT_EQ(a, b) _ASSERT_OP(a, ==, b)

#define ASSERT_NE(a, b) _ASSERT_OP(a, !=, b)

#define TEST(name)                                                     \
void test_##name()                                                     \

//#define TEST_ALL(name)                                               \
//void test_##name##_all()                                             \

//#define SET_ALL_(name)                                               \
//void set_##name##_all()                                              \

#define INIT(name)                                                     \
void test_##name##_all() {                                             \
  runTestCase(__FILE__, #name);                                        \
}                                                                      \
void set_##name##_case() {                                             \
  registerTestCase(__FILE__, #name, &test_##name##_all);               \
}                                                                      \
void set_##name##_tests();                                             \
void set_##name##_all() {                                              \
  set_##name##_case();                                                 \
  set_##name##_tests();                                                \
}                                                                      \
void set_##name##_tests()                                              \

//#define SET_CASE(name)                                               \
//registerTestCase(__FILE__, #name);                                   \

#define SET_TEST(name)                                                 \
registerTest(__FILE__, strcatnew("test_", #name), &test_##name);       \

#define SET_ALL(name)                                                  \
set_##name##_all();                                                    \

//#define RUN_ALL(name)                                                \
//test_##name##_all();                                                 \

//#define RUN(name)                                                    \
//test_##name();                                                       \

struct Error;
struct Test;
struct TestCase;

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
};

struct TestCase {
    char* file = NULL;
    char* name = NULL;
    void (*setall)() = NULL;
    void (*setup)() = NULL;
    void (*setdown)() = NULL;
    int started = 0;
    int failed = 0;
    std::vector<Test*> tests;
};

static std::vector<TestCase*> testCases;

TestCase* findTestCaseByFile(const char* file) {
    if (testCases.empty()) {
        return NULL;
    }
    TestCase* testCase = NULL;
    for (int i = 0; i < testCases.size(); i++) {
        testCase = testCases[i];
        if (strcmp(testCase->file, file) == 0) {
            return testCase;
        }
    }
    return NULL;
}

TestCase* findTestCaseByName(const char* name) {
    if (testCases.empty()) {
        fprintf(stderr, "[ERROR] No registered TestCases\n"); 
        return NULL;
    }
    TestCase* testCase = NULL;
    for (int i = 0; i < testCases.size(); i++) {
        testCase = testCases[i];
        if (strcmp(testCase->name, name) == 0) {
            return testCase;
        }
    }
    return NULL;
}

Test* findTestByName(TestCase* testCase, const char* name) {
    if (testCase == NULL) {
        return NULL;
    }
    Test* test = NULL;
    for (int i = 0; i < testCase->tests.size(); i++) {
        test = testCase->tests[i];
        if (strcmp(test->name, name) == 0) {
            return test;
        }
    }
    return NULL;
}

void registerTestCase(const char* file, const char* name, void (*setall)()) {
    TestCase* testCase = new TestCase();
    if (file) {
        testCase->file = (char*) malloc(strlen(file)); 
        strcpy(testCase->file, file);
    }
    if (name) {
        testCase->name = (char*) malloc(strlen(name)); 
        strcpy(testCase->name, name);
    }
    testCase->setall = setall;
    testCases.push_back(testCase);
}

char* strcatnew(const char* str1, const char* str2) {
    if (str1 == NULL || str2 == NULL) {
        return NULL;
    }
    char* str = (char*) malloc(strlen(str1) + strlen(str2) + 1);
    strcpy(str, str1);
    strcat(str, str2);
    return str;
}

void registerTest(const char* file, const char* name, void (*func)()) {
    TestCase* testCase = findTestCaseByFile(file);
    if (testCase == NULL) {
        return;
    }
    Test* test = new Test();
    test->parent = testCase;
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
    if (test->func == NULL) {
        return;
    }

    test->started = true;
    test->parent->started++;
    
    test->func();
}

void fill(char* array, int len, char value) {
    if (len == 0) {
        return;
    }    
    for (int i = 0; i < len - 1; i++) {
        array[i] = value;
    }
    array[len - 1] = '\0';
}

void fill(char* array, int len) {
    fill(array, len, ' ');
}

int getPadLen(int len) {
    int MAX_LEN = 80;
    return (len < MAX_LEN) ? (MAX_LEN - len) : 0;
}

void printTotalResult() {
    if (testCases.empty()) {
        fprintf(stdout, "No Tests\n"); 
        return;
    }
    TestCase* testCase = NULL;
    int total = 0;
    int started = 0;
    int failed = 0;
    int passed = 0;
    for (int i = 0; i < testCases.size(); i++) {
        testCase = testCases[i];
        total += testCase->tests.size();
        started += testCase->started;
        failed += testCase->failed;
    }
    passed = started - failed;

    fprintf(stdout, "\n");

    if (started == 0) {
        fprintf(stdout, "No started Tests\n"); 
        return;
    } 

    if (failed > 0) {
        //fprintf(stdout, "%d TEST FAILED\n", failed); 
        fprintf(stdout, "TEST FAILED: %d\n", failed); 
    }
    fprintf(stdout, "TEST PASSED: %d\n", passed);
    fprintf(stdout, "TEST TOTAL : %d\n", started);
    fprintf(stdout, "\n");
    fprintf(stdout, (failed > 0 ? "FAILED" : "OK"));
    fprintf(stdout, "\n");
}

void printTestResult(const char* file, const char* name, Test* test) {
    int padLen = getPadLen(strlen(file) + strlen(name) + strlen(test->name) + 4); // +18 = 7 + 4 + 1 + 6
    char pad[padLen];
    fill(pad, padLen);

    fprintf(stdout, "[TEST] %s: %s: %s %s" , file, name, test->name, pad);
    fprintf(stdout, (test->failed ? "- FAIL\n" : "- OK\n"));
}

void printTestCaseResult(const char* file, const char* name, TestCase* testCase, bool isPrintTest) {
    int padLen = getPadLen(strlen(file) + strlen(name) + 2);
    char pad[padLen];
    fill(pad, padLen);

    fprintf(stdout, (isPrintTest ? "[CASE] %s: %s %s" : "[TEST] %s: %s %s"), file, name, pad);
    fprintf(stdout, (testCase->failed > 0 ? "- FAIL\n" : "- OK\n"));

    //if (testCase->failed > 0) {
        //fprintf(stdout, "[FAILED ] '%s: %s'\n", file, name); 
    //} else {
        //fprintf(stdout, "[PASSED ] '%s: %s'\n", file, name); 
    //}
}

void runTestCase(TestCase* testCase) {

    std::vector<Test*> tests = testCase->tests;
    if (tests.empty()) {
        return;
    }

    //fprintf(stdout, "[RUNNNING] %s: %s\n", file, name);
    //fprintf(stdout, "[RUNNNING] %s: %s\t", file, name);

    int printMode = 3; // 1 - Test, 2 - TestCase, 3 - Test & TestCase
    bool isPrintTest = printMode == 1 || printMode == 3;
    bool isPrintCase = printMode == 2 || printMode == 3;;

    //testCase->started = true;

    Test* test = NULL;
    for (int i = 0; i < tests.size(); i++) {
        test = tests[i];
        runTest(test);
                
        if (isPrintTest) {
            printTestResult(testCase->file, testCase->name, test);
        }
    }

    if (isPrintCase) {
        printTestCaseResult(testCase->file, testCase->name, testCase, isPrintTest);
    }

}

void runTestCase(const char* file, const char* name) {
    TestCase* testCase = findTestCaseByName(name);
    if (testCase == NULL) {
        fprintf(stderr, "[ERROR] TestCase not found: %s: %s\n", file, name); 
        return;
    }

    runTestCase(testCase);    
}

void runAll() {
    if (testCases.empty()) {
        fprintf(stderr, "[ERROR] No registered TestCases\n"); 
        return;
    }
    TestCase* testCase = NULL;
    for (int i = 0; i < testCases.size(); i++) {
        testCase = testCases[i];
        runTestCase(testCase);
    }
}

void registerError(const char* file, const int line, const char* func) {
    TestCase* testCase = findTestCaseByFile(file);
    if (testCase) {
        testCase->failed++;
        Test* test = findTestByName(testCase, func);
        if (test) {
            test->failed = true;
        }
    }
}

void fail(const char* file, const int line, const char* func, const char* message) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s\n", file, line, message); 
    registerError(file, line, NULL);
}

// op
void fail(const char* file, const int line, const char* func, const char* a, const char* op, const char* b) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s %s %s\n", file, line, a, op, b); 
    registerError(file, line, NULL);
}

// op
void fail(const char* file, const int line, const char* func, const std::string& a, const char* op, const std::string& b) {
    fprintf(stderr, "Assertion failed in %s on line %d: %s %s %s\n", file, line, a.c_str(), op, b.c_str()); 
    registerError(file, line, func);
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