
#include <string>
#include <vector>

#include "TaskProvider1.h"

#include "BaseTaskProvider.h"
//#include "plazma/kernel/lib/task/xml/XmlTaskProvider.h"

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
     //handler = new XmlTaskProvider();
     handler->init();
   }

    bool TaskProvider1::hasTask(std::string& taskName) {
      if (handler == nullptr) {
        return false;
      }
      return handler->hasTask(taskName);
    }

    Task* TaskProvider1::getTask(std::string& taskName) {
      if (handler == nullptr) {
        return nullptr;
      }
      return handler->getTask(taskName);
   }

   std::vector<std::string> TaskProvider1::getTaskNames() {
      if (handler == nullptr) {
        std::vector<std::string> names;
        return names;
      }
      return handler->getTaskNames();

      //std::vector<std::string> v;
      //v.push_back("Task1");
      //return v;
   }

}
