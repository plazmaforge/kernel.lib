#ifndef PLAZMA_LIB_TASK_TASK_PROVIDER_REGISTRY_H
#define PLAZMA_LIB_TASK_TASK_PROVIDER_REGISTRY_H

#include <string>
#include <vector>

//#include "plazma/lib/sys/LibraryLoader.h"
#include "TaskProvider.h"
#include "TaskProviderDescriptor.h"

namespace task {

    class TaskProviderRegistry {

        public:

          std::vector<TaskProviderDescriptor*> list;

        public:

            TaskProviderRegistry();

            ~TaskProviderRegistry();

            void addStaticProvider(const std::string& name, TaskProvider* provider);

            void addLibraryProvider(const std::string& name, const std::string& libraryName);

            void addLibraryProvider(const std::string& name, const std::string& libraryName, const std::string& libraryPath);

            TaskProviderDescriptor* getTaskDescriptor(int index);

            TaskProvider* getTaskProvider(int index);

            void init();

            void init(TaskProviderDescriptor* descriptor);

            void destroy();

            void destroy(TaskProviderDescriptor* descriptor);

    };

}
#endif // PLAZMA_LIB_TASK_TASK_PROVIDER_REGISTRY_H