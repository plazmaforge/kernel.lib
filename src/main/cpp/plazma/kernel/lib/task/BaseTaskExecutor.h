#ifndef PLAZMA_KERNEL_LIB_TASK_BASE_TASK_EXECUTOR_H
#define PLAZMA_KERNEL_LIB_TASK_BASE_TASK_EXECUTOR_H

#include "TaskExecutor.h"
#include "TaskProvider.h"

namespace task {

    class BaseTaskExecutor: public TaskExecutor {

        public:

            BaseTaskExecutor();

            ~BaseTaskExecutor();

            void init();

            void executeTask(std::string& taskName, std::map<std::string, std::string>& parameters);

        protected:

            TaskContext* createTaskContext(Task* task, std::map<std::string, std::string>& parameters);

            bool hasTask(std::string& taskName);

            Task* getTask(std::string& taskName);

            void printTaskList();

            //

            bool initFlag = false;

            TaskProvider* provider = nullptr;


    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_BASE_TASK_EXECUTOR_H