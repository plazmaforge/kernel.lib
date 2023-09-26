#ifndef PLAZMA_LIB_TASK_TASK_EXECUTOR_H
#define PLAZMA_LIB_TASK_TASK_EXECUTOR_H

#include <string>
#include <map>
#include "TaskProvider.h"

namespace task {

    class TaskExecutor {

        public:

            TaskExecutor();

            virtual ~TaskExecutor();

            virtual void init() = 0;

            virtual void setTaskProvider(TaskProvider* provider) = 0;

            virtual void executeTask(std::string &taskName, std::map<std::string, std::string> &parameters) = 0;

            virtual void printTaskList() = 0;

    };

}
#endif // PLAZMA_LIB_TASK_TASK_EXECUTOR_H