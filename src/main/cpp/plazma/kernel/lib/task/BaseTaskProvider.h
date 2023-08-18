#ifndef PLAZMA_KERNEL_LIB_TASK_BASE_TASK_PROVIDER_H
#define PLAZMA_KERNEL_LIB_TASK_BASE_TASK_PROVIDER_H

#include <string>
#include <vector>

#include "TaskProvider.h"

namespace task {

    class BaseTaskProvider: public TaskProvider {

        
        public:

            BaseTaskProvider();

            ~BaseTaskProvider();

            bool hasTask(std::string& taskName);

            Task* getTask(std::string& taskName);

            std::vector<std::string> getTaskNames();

        protected:

            std::vector<std::string> taskNames;

        private:


    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_BASE_TASK_PROVIDER_H