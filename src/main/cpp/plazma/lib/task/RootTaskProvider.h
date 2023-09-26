#ifndef PLAZMA_KERNEL_LIB_TASK_ROOT_TASK_PROVIDER_H
#define PLAZMA_KERNEL_LIB_TASK_ROOT_TASK_PROVIDER_H

#include <string>
#include <vector>
#include <iostream>

#include "TaskProvider.h"
#include "TaskProviderRegistry.h"

namespace task {

    class RootTaskProvider: public TaskProvider {

        
        public:

            RootTaskProvider();

            ~RootTaskProvider();

            void init();

            bool hasTask(std::string& taskName);

            Task* getTask(std::string& taskName);

            std::vector<std::string> getTaskNames();

        private:

            TaskProviderRegistry* registry;

            TaskProvider* handler;

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_ROOT_TASK_PROVIDER_H