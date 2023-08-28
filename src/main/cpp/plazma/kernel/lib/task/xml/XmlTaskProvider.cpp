

#include "plazma/kernel/lib/task/xml/XmlFormatTask.h"
//#include "plazma/kernel/lib/task/xml/Xml2JsonConvertTask.h"
//#include "plazma/kernel/lib/task/xml/Xml2YamlConvertTask.h"

#include "plazma/kernel/lib/str/strlib.h" 

#include "XmlTaskProvider.h"

const std::string TASK_LIST[] = {

   TASK_FORMAT_XML,

   // TASK_CONVERT_XML2JSON,
   // TASK_CONVERT_XML2YAML,

   };

const int TASK_COUNT = 1;

namespace task {

    XmlTaskProvider::XmlTaskProvider() {}

    XmlTaskProvider::~XmlTaskProvider() {
        //std::cout << "Destroy XmlTaskProvider" << std::endl;
        //taskNames.clear();
    }

    void XmlTaskProvider::init() {
        for (int i  = 0; i < TASK_COUNT; i++) {
            taskNames.push_back(TASK_LIST[i]);
        }
    }

    bool XmlTaskProvider::equals(const std::string &str1, const std::string &str2) {
        return strlib::equals(str1, str2);
    }

    bool XmlTaskProvider::hasTask(std::string& taskName)  {
        return std::find(taskNames.begin(), taskNames.end(), taskName) != taskNames.end();
    }

    std::vector<std::string> XmlTaskProvider::getTaskNames()  {
        return taskNames;
    }

    Task* XmlTaskProvider::getTask(std::string& taskName)  {
       Task* task = nullptr;
       if (equals(TASK_FORMAT_XML, taskName)) {
          task = new XmlFormatTask();
       }
       return task;
    }

}
