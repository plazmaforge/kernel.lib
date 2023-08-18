#ifndef PLAZMA_KERNEL_LIB_TASK_ARRAY_ARRAY_READ_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_ARRAY_ARRAY_READ_TASK_H

#include <string>

#include "plazma/kernel/lib/task/BaseTask.h"

// array
const std::string TASK_READ_FLOAT_ARRAY = "read-float-array";

namespace task {

    class ArrayReadTask: public BaseTask {

        public:

            const std::string DEFAULT_FILE_EXT = ".csv";
            const std::string DEFAULT_SEPRATOR = ",";
            const int DEFAULT_FILE_COUNT = 1;

            ///////////////////////////////////////////////////////////
            // parameters:
            ///////////////////////////////////////////////////////////

            const std::string PARAMETER_DIR = "dir";
            const std::string PARAMETER_FILE =  "file";
            const std::string PARAMETER_FILE_PREFIX =  "file-prefix";
            const std::string PARAMETER_FILE_COUNT =  "file-count";

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_REQUIREMENT_ARGS = "Requirement arguments are not setting.";
            const std::string ERROR_SET_FILE = "Set -file or (-dir and -file-prefix). By default file extension is '.csv'";

            ArrayReadTask();

            ~ArrayReadTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_ARRAY_ARRAY_READ_TASK_H