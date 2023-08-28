
#include <string>
#include <vector>

#include "TaskProvider1.h"
#include "BaseTaskProvider.h"

namespace task {

    TaskProvider1::TaskProvider1() {
    }

    TaskProvider1::~TaskProvider1() {
      if (handler != nullptr) {
        delete handler;
      }
    }

    #if defined(__linux__) || defined(__APPLE__) 
    
    extern "C" {

	  TaskProvider1* create() {
	  	return new TaskProvider1();
	  }

	  void destroy(TaskProvider1* ptr) {
		delete ptr;
	  }

   }

   #endif

   #ifdef WIN32

   extern "C" {
      __declspec (dllexport) TaskProvider1* create() 	{
        return new TaskProvider1();
	  }

	  __declspec (dllexport) void destroy(TaskProvider1 *ptr) {
        delete ptr;
	  }

   }

   #endif

   void TaskProvider1::init() {
     handler = new BaseTaskProvider();
     handler->init();
   }

   TaskProvider* TaskProvider1::getHandler() {
       return handler;
       //return nullptr;
   }

   bool TaskProvider1::hasTask(std::string& taskName) {
     return getHandler()->hasTask(taskName);
     //return false;
   }

   Task* TaskProvider1::getTask(std::string& taskName) {
     return getHandler()->getTask(taskName);
     //return nullptr;
   }

   std::vector<std::string> TaskProvider1::getTaskNames() {     
       return getHandler()->getTaskNames();

       //std::vector<std::string> v;
       //v.push_back("Task1");
       //return v;
   }

}
