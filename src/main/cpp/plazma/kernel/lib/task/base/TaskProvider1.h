#ifndef PLAZMA_KERNEL_LIB_TASK_BASE_TASK_PROVIDER1_H
#define PLAZMA_KERNEL_LIB_TASK_BASE_TASK_PROVIDER1_H

#include <string>
#include <vector>
#include <iostream>


#include "plazma/kernel/lib/task/TaskProvider.h"

namespace task {

    class TaskProvider1: public TaskProvider {

        
        public:

            TaskProvider1();

            ~TaskProvider1();

            void init();

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
#endif // PLAZMA_KERNEL_LIB_TASK_BASE_TASK_PROVIDER1_H