#include <map>
//#include <iostream>

#include "plazma/kernel/lib/sys/syslib.h"
#include "plazma/kernel/lib/io/iolib.h"

#include "run.h"
#include "task_helper.h"

#include "BaseTaskExecutor.h"
#include "RootTaskProvider.h"

using namespace syslib;
using namespace task;

const std::string MESSAGE_TASK_NOT_SETTING = "-task is not setting";
const std::string MESSAGE_TASK_EMPTY = "Task is empty";

const std::string PARAMETER_VERSION = "version";
const std::string PARAMETER_HELP = "help";
const std::string PARAMETER_TASK = "task";
const std::string PARAMETER_TASK_LIST = "task-list";
const std::string PARAMETER_COLOR = "color";          // Color flag
const std::string PARAMETER_STDERR = "stderr";        // StdErr mode

//https://github.com/fffaraz/awesome-cppf
//https://github.com/sumeetchhetri/ffead-cpp
//https://github.com/davisking/dlib/tree/master/dlib
//https://thispointer.com/stdmap-tutorial-part-1-usage-detail-with-examples/

// Locale
// =========
// https://en.cppreference.com/w/cpp/locale/setlocale
// https://www.linux.org.ru/forum/development/3469000
// https://code-live.ru/post/cpp-russian-locale-for-windows-cmd/
// https://stackoverflow.com/questions/42769095/how-to-handle-utf-8-encoded-source-when-compiling-on-windows

TaskExecutor* executor;

TaskProvider* createTaskProvider() {
  return new RootTaskProvider();
}

TaskExecutor* getExecutor() {
  if (executor == nullptr) {

    executor = new BaseTaskExecutor();
    
    TaskProvider* provider = createTaskProvider();

    if (provider == nullptr) {
      error("Provider is not implemented");
      return executor;
    }

    provider->init();

    executor->setTaskProvider(provider);
  }
  return executor;
}

void printVersion() {
  syslib::println("Run [C++] v1.0.8");
  syslib::println();
}

void printUsage() {
  syslib::println("Usage: run [options]");
  syslib::println("Options:");

  syslib::println(" -version");
  syslib::println(" -help");
  syslib::println(" -task <name> [-<param> <value>]");
  syslib::println(" -task-list");
}

void printTaskList() {
  getExecutor()->printTaskList();
}

////

void execute(std::map<std::string, std::string> &parameters) {

    // COLORIZED: CONSOLE
    bool colorized = false;
    if (hasParameter(parameters, PARAMETER_COLOR)) {
      std::string value = getParameter(parameters, PARAMETER_COLOR);
      if (value.empty() || value == "true") {
        colorized = true;
      }      
    }
    syslib::setColorizedConsole(colorized);


    // REDIRECT: STDERR
    bool redirectStdErr = false;
    if (hasParameter(parameters, PARAMETER_STDERR)) {
      std::string value = getParameter(parameters, PARAMETER_STDERR);
      if (value == "stdout") {
        redirectStdErr = true;
      }      
    }
    syslib::setRedirectStdErr(redirectStdErr);

  
   // VERSION
   if (hasParameter(parameters, PARAMETER_VERSION)) {
     printVersion();
     return;
   }

   // HELP
   if (hasParameter(parameters, PARAMETER_HELP)) {
     printUsage();
     return;
   }

   // TASK
   if (hasParameter(parameters, PARAMETER_TASK)) {
     executeTask(parameters);
     return;
   } 
   
   // TASK LIST
   if (hasParameter(parameters, PARAMETER_TASK_LIST)) {
     printTaskList();
     return;
   }

   // TASK NO
   syslib::println(MESSAGE_TASK_NOT_SETTING);
   syslib::println();
   
   printUsage();

}

void executeTask(std::map<std::string, std::string> &parameters) {
  std::string taskName = getParameter(parameters, PARAMETER_TASK);
  if (taskName.empty()) {
    error(MESSAGE_TASK_EMPTY);
    printUsage();
    return;
  }
  getExecutor()->executeTask(taskName, parameters);
}

////

int main(int argc, char* argv[]) {

  iolib::init_utf8_console();

  // Parse command line arguments
  std::map<std::string, std::string> parameters = parseArguments(argc, argv);

  // Execute task
  execute(parameters);

  if (executor == nullptr) {
    delete executor;
  }

  return 0;

}