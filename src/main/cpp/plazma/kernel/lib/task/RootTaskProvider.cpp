//#define PLAZMA_RUN_TASK_LIBRARY

#include <string>
#include <vector>

#include "RootTaskProvider.h"

#ifdef PLAZMA_RUN_TASK_LIBRARY
#include "plazma/kernel/lib/sys/LibraryLoader.h"
#else
#include "plazma/kernel/lib/task/base/BaseTaskProvider.h"
#endif

namespace task {

    #ifdef PLAZMA_RUN_TASK_LIBRARY
    TaskProvider* loadTaskProvider() {
        sys::LibraryLoader<TaskProvider>* loader = new sys::LibraryLoader<TaskProvider>("lib-task.dylib");
        loader->openLibrary();
        return loader->getInstance();
    }
    #endif

    RootTaskProvider::RootTaskProvider() {}

    RootTaskProvider::~RootTaskProvider() {
        #ifdef PLAZMA_RUN_TASK_LIBRARY
        // TODO
        #else
        if (handler != nullptr) {
            delete handler;
        }
        #endif
    }

    void RootTaskProvider::init() {
        #ifdef PLAZMA_RUN_TASK_LIBRARY
        handler = loadTaskProvider();
        #else
        handler = new BaseTaskProvider();
        #endif
        handler->init();
    }

    TaskProvider* RootTaskProvider::getHandler() {
       return handler;
       //return nullptr;
    }

    bool RootTaskProvider::hasTask(std::string& taskName) {
      return getHandler()->hasTask(taskName);
      //return false;
    }

    Task* RootTaskProvider::getTask(std::string& taskName) {
      return getHandler()->getTask(taskName);
     //return nullptr;
   }

   std::vector<std::string> RootTaskProvider::getTaskNames() {     
     return getHandler()->getTaskNames();

     //std::vector<std::string> v;
     //v.push_back("Task1");
     //return v;
   }

}
