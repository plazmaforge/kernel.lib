//#include <iostream>

#include <algorithm>

#include "plazma/kernel/lib/task/sys/SysTask.h"
#include "plazma/kernel/lib/task/sys/CalendarTask.h"

#include "plazma/kernel/lib/task/xml/XmlFormatTask.h"
#include "plazma/kernel/lib/task/xml/Xml2JsonConvertTask.h"
#include "plazma/kernel/lib/task/xml/Xml2YamlConvertTask.h"

#include "plazma/kernel/lib/task/json/JsonFormatTask.h"
#include "plazma/kernel/lib/task/json/Json2XmlConvertTask.h"
#include "plazma/kernel/lib/task/json/Json2YamlConvertTask.h"

#include "plazma/kernel/lib/task/yaml/YamlFormatTask.h"
#include "plazma/kernel/lib/task/yaml/Yaml2XmlConvertTask.h"
#include "plazma/kernel/lib/task/yaml/Yaml2JsonConvertTask.h"

#include "plazma/kernel/lib/task/array/ArrayReadTask.h"
#include "plazma/kernel/lib/task/array/ArrayGenTask.h"

#include "plazma/kernel/lib/task/test/TestTask.h"

#include "plazma/kernel/lib/str/strlib.h" 

#include "BaseTaskProvider.h"

const std::string TASK_LIST[] = {
   TASK_PRINT_SYSTEM_INFO,
   TASK_PRINT_DATE,
   TASK_PRINT_TIME,
   TASK_PRINT_DATE_TIME,

   TASK_PRINT_FACT,
   TASK_PRINT_FIB,

   TASK_PRINT_CALENDAR,
   TASK_PRINT_YEAR_CALENDAR,
   TASK_PRINT_MONTH_CALENDAR,

   TASK_FORMAT_XML,
   TASK_FORMAT_JSON,
   TASK_FORMAT_YAML,

   TASK_CONVERT_XML2JSON,
   TASK_CONVERT_XML2YAML,

   TASK_CONVERT_JSON2XML,
   TASK_CONVERT_JSON2YAML,

   TASK_CONVERT_YAML2XML,
   TASK_CONVERT_YAML2JSON,

   TASK_READ_FLOAT_ARRAY,
   TASK_GENERATE_FLOAT_ARRAY,

   TASK_TEST
   };

const int TASK_COUNT = 21;

namespace task {

    BaseTaskProvider::BaseTaskProvider() {
        for (int i  = 0; i < TASK_COUNT; i++) {
            taskNames.push_back(TASK_LIST[i]);
        }
    }

    BaseTaskProvider::~BaseTaskProvider() {
        //std::cout << "Destroy BaseTaskProvider" << std::endl;
        //taskNames.clear();
    }

    bool equals(const std::string &str1, const std::string &str2) {
        return strlib::equals(str1, str2);
    }

    bool BaseTaskProvider::hasTask(std::string& taskName)  {
        return std::find(taskNames.begin(), taskNames.end(), taskName) != taskNames.end();
    }

    std::vector<std::string> BaseTaskProvider::getTaskNames()  {
        return taskNames;
    }

    Task* BaseTaskProvider::getTask(std::string& taskName)  {
        Task* task = nullptr;

     // sys
     if (equals(TASK_PRINT_SYSTEM_INFO, taskName)
      || equals(TASK_PRINT_DATE, taskName)
      || equals(TASK_PRINT_TIME, taskName)
      || equals(TASK_PRINT_DATE_TIME, taskName)

      || equals(TASK_PRINT_FACT, taskName)
      || equals(TASK_PRINT_FIB, taskName)) {

         task = new SysTask();

      // calendar
      } else if (equals(TASK_PRINT_CALENDAR, taskName)
       || equals(TASK_PRINT_YEAR_CALENDAR, taskName)
       || equals(TASK_PRINT_MONTH_CALENDAR, taskName)) {

         task = new CalendarTask();

     // xml-format
     } else if (equals(TASK_FORMAT_XML, taskName)) {
        task = new XmlFormatTask();

     // json-format
     } else if (equals(TASK_FORMAT_JSON, taskName)) {
        task = new JsonFormatTask();

     // yaml-format
     } else if (equals(TASK_FORMAT_YAML, taskName)) {
        task = new YamlFormatTask();

     // xml-json
     } else if (equals(TASK_CONVERT_XML2JSON, taskName)) {
        task = new Xml2JsonConvertTask();

     // xml-yaml
     } else if (equals(TASK_CONVERT_XML2YAML, taskName)) {
        task = new Xml2YamlConvertTask();

     // json-xml
     } else if (equals(TASK_CONVERT_JSON2XML, taskName)) {
        task = new Json2XmlConvertTask();

     // json-yaml
     } else if (equals(TASK_CONVERT_JSON2YAML, taskName)) {
        task = new Json2YamlConvertTask();

     // yaml-xml 
     } else if (equals(TASK_CONVERT_YAML2XML, taskName)) {
        task = new Yaml2XmlConvertTask();

     // yaml-json
     } else if (equals(TASK_CONVERT_YAML2JSON, taskName)) {
        task = new Yaml2JsonConvertTask();

     // test
     } else if (equals(TASK_TEST, taskName)) {
        task = new TestTask();

     // array-read
     } else if (equals(TASK_READ_FLOAT_ARRAY, taskName)) {
        task = new ArrayReadTask();

     // array-gen
     } else if (equals(TASK_GENERATE_FLOAT_ARRAY, taskName)) {
        task = new ArrayGenTask();
     }


      return task;

    }

}
