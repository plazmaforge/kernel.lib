
#include <string>
#include <vector>

#include "TaskProvider1.h"

namespace task {

    TaskProvider1::TaskProvider1() {

    }

    TaskProvider1::~TaskProvider1() {

    }

    #if defined(__linux__) || defined(__APPLE__) 
    
    extern "C" {

	  TaskProvider1* allocator() {
	  	return new TaskProvider1();
	  }

	  void deleter(TaskProvider1 *ptr) {
		delete ptr;
	  }

   }

   #endif

   #ifdef WIN32

   extern "C" {
      __declspec (dllexport) TaskProvider1* allocator() 	{
        return new TaskProvider1();
	  }

	  __declspec (dllexport) void deleter(TaskProvider1 *ptr) {
        delete ptr;
	  }

   }

   #endif

   bool TaskProvider1::hasTask(std::string& taskName) {
     return false;
   }

   Task* TaskProvider1::getTask(std::string& taskName) {
     return nullptr;
   }

   std::vector<std::string> TaskProvider1::getTaskNames() {
    std::vector<std::string> v;
    v.push_back("WAY++");
    return v;
   }

}
