#include <iostream>
#include "task_helper.h"

#include "plazma/kernel/lib/sys/syslib.h" 
#include "plazma/kernel/lib/str/strlib.h" 
#include "plazma/kernel/lib/fmt/fmtlib.h" 

using namespace syslib;

#include "BaseTaskExecutor.h"
//#include "BaseTaskProvider.h"

const std::string MESSAGE_TASK_LIST = "Task List:";
const std::string MESSAGE_NO_TASKS = "No tasks";

const std::string ERROR_TASK_NAME_EMPTY = "Task name is empty";
const std::string ERROR_TASK_NOT_FOUND = "Task not found: '%s'";
const std::string ERROR_TASK_NOT_IMPLEMENTED = "Task not implemented: '%s'";
const std::string ERROR_TASK_PROVIDER_NOT_IMPLEMENTED = "Task provider not implemented";

namespace task {

    BaseTaskExecutor::BaseTaskExecutor() {
        initFlag = false;
        provider = nullptr;
    }

    BaseTaskExecutor::~BaseTaskExecutor() {
        //std::cout << "Destroy BaseTaskExecutor" << std::endl;
        if (provider != nullptr) {
            delete provider;
        }
    }

    void BaseTaskExecutor::init() {
        if (initFlag) {
            return;
        }
        //provider = new BaseTaskProvider();
        initFlag = true;
    }

    void BaseTaskExecutor::setTaskProvider(TaskProvider* provider) {
        this->provider = provider;
    }

    void BaseTaskExecutor::executeTask(std::string& taskName, std::map<std::string, std::string>& parameters)  {

       // CHECK EMPTY NAME
       if (taskName.empty()) {
           error(ERROR_TASK_NAME_EMPTY);
           return;
       }

       // CHECK NAME
       if (!hasTask(taskName)) {
           error(fmtlib::format(ERROR_TASK_NOT_FOUND, taskName));
           return;
       }

       Task* task = getTask(taskName);

       // CHECK TASK
       if (task == nullptr) {
           error(fmtlib::format(ERROR_TASK_NOT_IMPLEMENTED, taskName));
           return;
       }

       // Init task (register parameters)
       task->init();

       // Prepare Context
       TaskContext* ctx = createTaskContext(task, parameters);
       ctx->setTaskName(taskName);

       bool dysplayProcessing = task->isDysplayProcessing();
       bool dysplayTiming = dysplayProcessing && task->isDysplayTiming();

       // Processing
       if (dysplayProcessing) {
           printStartTask(taskName);
           printProcessing();
           println();
       }

       // Validate
       task->validate(ctx);

       // Dumping
       if (dysplayProcessing) {
           task->dump(ctx);
       }

       long time = startTime();

       /////////////////////////

       task->execute(ctx);

       /////////////////////////

       time = stopTime(time);

       // Timing
       if (dysplayTiming) {
           println();
           printTotalTime(time);
       }

       // sleep_for(nanoseconds(1000));

       if (ctx != nullptr) {
           delete ctx;
       }

       if (task != nullptr) {
           delete task;
       }

   }

   ////

   TaskContext* BaseTaskExecutor::createTaskContext(Task* task, std::map<std::string, std::string>& parameters) {

       TaskContext* ctx = new TaskContext();

       if (parameters.empty()) {
           return ctx;
       }

       Parameters* taskParameters = task->getParameters();
       if (taskParameters == nullptr || !taskParameters->hasParameters()) {
           return ctx;
       }

       int count = taskParameters->getParameterCount();
       Parameter* parameter = nullptr;
       std::string name;
       std::string value;

       for (int i = 0; i < count; i++) {
           parameter = taskParameters->getParameter(i);

           // Transfer Parameter
           parameter->reset(); // why?

           // Clone because we transfer parameters from Task to Context
           parameter = parameter->clone();

           ctx->getParameters()->addParameter(parameter);

           name = parameter->getName();
           if (!hasParameter(parameters, name)) {
               // Not Found
               continue;
           }

           parameter->setFound(true); // Mark as found: IMPORTANT!
           value = getParameter(parameters, name);
           parameter->setValue(value);
       }

       return ctx;
   }

   ////

   bool BaseTaskExecutor::hasTask(std::string& taskName) { 
       return provider == nullptr ? false : provider->hasTask(taskName); 
   }

   Task* BaseTaskExecutor::getTask(std::string& taskName) { 
       return provider == nullptr ? nullptr :provider->getTask(taskName); 
   }

   void BaseTaskExecutor::printTaskList() {
       if (provider == nullptr) {
           error(ERROR_TASK_PROVIDER_NOT_IMPLEMENTED);
           return;
       }       
       
       std::vector<std::string> taskNames = provider->getTaskNames();
       if (taskNames.empty()) {
           std::cout << MESSAGE_NO_TASKS << std::endl;
           return;
       }

       std::cout << MESSAGE_TASK_LIST << std::endl;
       for (int i = 0; i < taskNames.size(); i++) {
           std::cout << " " << taskNames.at(i) << std::endl;
       }

   }


}
