#ifndef PLAZMA_KERNEL_LIB_TASK_ROOT_TASK_PROVIDER_H
#define PLAZMA_KERNEL_LIB_TASK_ROOT_TASK_PROVIDER_H

#include <string>
#include <vector>
#include <iostream>

#include "TaskProvider.h"

namespace task {

    class RootTaskProvider: public TaskProvider {

        
        public:

            RootTaskProvider();

            ~RootTaskProvider();

            bool hasTask(std::string& taskName);

            Task* getTask(std::string& taskName);

            std::vector<std::string> getTaskNames();

        protected:

            std::vector<std::string> taskNames;

            TaskProvider* getHandler();

        private:

            TaskProvider* handler;

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_ROOT_TASK_PROVIDER_H