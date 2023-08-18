#include <iostream>

#include "task_helper.h"

#include "TestTask.h"

//
// Intel Core i5, 1.4 GHz, 8GB, Mac OS X
// ========================================
// Java:         386 ms
// C++:          930 ms
// C++ (-O2):    365 ms
// Swift:      3 497 ms
// Python:     8 450 ms
// 

//
// Intel Core i5, 2.6 GHz, 16GB, Windows 10
// ========================================
// Java:         477 ms
// C++:          639 ms
// C++ (-O2)     316 ms
// Swift:            -
// Python:    13 418 ms
// 

// Java vs C++ vs PHP vs Python vs Perl vs Ruby
// https://habr.com/ru/post/66562/

// gcc -O2
// https://codeforces.com/blog/entry/11450?locale=ru

namespace task {

    TestTask::TestTask() {}

    TestTask::~TestTask() {
        //std::cout << "Destroy TestTask" << std::endl;
    }

    void TestTask::execute(TaskContext* ctx) {

        int r = 0;
        int COUNT = 10000;
        for (int i = 0; i < COUNT; i++) {
            for (int j = 0; j < COUNT; j++) {
                r = (r + (i * j) % 100) % 47;
            }
        }

        std::cout << "Answer: " << r << std::endl;
        
    }

}
