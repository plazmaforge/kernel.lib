//#define PLAZMA_RUN_TASK_LIBRARY

#include <string>
#include <vector>

#include "RootTaskProvider.h"

#ifndef PLAZMA_RUN_TASK_LIBRARY
#include "plazma/kernel/lib/task/base/BaseTaskProvider.h"
#endif

namespace task {

    RootTaskProvider::RootTaskProvider() {}

    RootTaskProvider::~RootTaskProvider() {
      if (registry == nullptr) {
        return;
      }
      registry->destroy();

      delete registry;

    }

    void RootTaskProvider::init() {
        registry = new TaskProviderRegistry();

        #ifdef PLAZMA_RUN_TASK_LIBRARY
        registry->addLibraryProvider("LibTaskProvider", "lib-task" /*, "lib-task.dylib"*/);
        #else
        registry->addStaticProvider("BaseTaskProvider", new BaseTaskProvider());        
        #endif

        registry->init();
        handler = registry->getTaskProvider(0);
        
    }

    bool RootTaskProvider::hasTask(std::string& taskName) {
      if (handler == nullptr) {
        return false;
      }
      return handler->hasTask(taskName);
    }

    Task* RootTaskProvider::getTask(std::string& taskName) {
      if (handler == nullptr) {
        return nullptr;
      }
      return handler->getTask(taskName);
   }

   std::vector<std::string> RootTaskProvider::getTaskNames() {
      if (handler == nullptr) {
        std::vector<std::string> names;
        return names;
      }
      return handler->getTaskNames();
   }

}
