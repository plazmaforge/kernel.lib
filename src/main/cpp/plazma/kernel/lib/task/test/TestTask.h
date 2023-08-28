#ifndef PLAZMA_KERNEL_LIB_TASK_TEST_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_TEST_TASK_H

#include <string>

#include "plazma/kernel/lib/task/TaskContext.h"
#include "plazma/kernel/lib/task/BaseTask.h"

const std::string TASK_TEST = "test";

namespace task {

    class TestTask: public BaseTask {

        public:

            TestTask();

            ~TestTask();

            virtual void execute(TaskContext* ctx);

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_TEST_TASK_H