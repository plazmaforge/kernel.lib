#ifndef PLAZMA_KERNEL_LIB_TASK_TASK_PROVIDER_DESCRIPTOR_H
#define PLAZMA_KERNEL_LIB_TASK_TASK_PROVIDER_DESCRIPTOR_H

#include <string>
#include <vector>

#include "TaskProvider.h"

using namespace sys;

namespace task {

    class TaskProviderDescriptor {

        public:

          TaskProvider* provider;
          std::string providerName;
          std::string packageName;
          std::string libraryName;
          std::string libraryPath; // depends on OS
          //TODO: loader
          bool initialized;
          bool hasError;
          bool hasLibrary;

        public:

            TaskProviderDescriptor();

            ~TaskProviderDescriptor();

            void destroy();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_TASK_PROVIDER_DESCRIPTOR_H