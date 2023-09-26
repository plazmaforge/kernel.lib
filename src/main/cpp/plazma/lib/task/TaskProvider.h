#ifndef PLAZMA_KERNEL_LIB_TASK_TASK_PROVIDER_H
#define PLAZMA_KERNEL_LIB_TASK_TASK_PROVIDER_H

#include <string>
#include <vector>

#include "Task.h"

using namespace sys;

namespace task {

    class TaskProvider {

        public:

            TaskProvider();

            virtual ~TaskProvider();

            virtual void init() = 0;

            virtual bool hasTask(std::string& taskName) = 0;

            virtual Task* getTask(std::string& taskName) = 0;

            virtual std::vector<std::string> getTaskNames() = 0;

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_TASK_PROVIDER_H