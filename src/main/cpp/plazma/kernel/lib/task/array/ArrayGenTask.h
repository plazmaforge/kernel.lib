#ifndef PLAZMA_KERNEL_LIB_TASK_ARRAY_ARRAY_GEN_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_ARRAY_ARRAY_GEN_TASK_H

#include <string>

#include "plazma/kernel/lib/task/BaseTask.h"

// array
const std::string TASK_GENERATE_FLOAT_ARRAY = "generate-float-array";

namespace task {

    class ArrayGenTask: public BaseTask {

        public:

            const std::string DEFAULT_FILE_EXT = ".csv";
            const std::string DEFAULT_SEPRATOR = ",";
            const int DEFAULT_FILE_COUNT = 1;
            const int DEFAULT_COLS = 100;
            const int DEFAULT_ROWS = 1000;
            const int DEFAULT_CELL_MAX = 100000000;	// 100 millions
            const float DEFAULT_MIN = -100.0;
            const float DEFAULT_MAX = 100.0;

            ///////////////////////////////////////////////////////////
            // parameters:
            ///////////////////////////////////////////////////////////

            const std::string PARAMETER_DIR = "dir";
            const std::string PARAMETER_FILE =  "file";
            const std::string PARAMETER_FILE_PREFIX =  "file-prefix";
            const std::string PARAMETER_FILE_COUNT =  "file-count";
            const std::string PARAMETER_COLS =  "cols";
            const std::string PARAMETER_ROWS =  "rows";

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_REQUIREMENT_ARGS = "Requirement arguments are not setting.";
            const std::string ERROR_SET_FILE = "Set -file or (-dir and -file-prefix). By default file extension is '.csv'";

            const std::string WARN_CANT_WRITE_FILE = "Can't write file(s) (generation only).";
            const std::string WARN_CELL_LIMIT = "Cell limit is %s. But real cell count is %s";


            ArrayGenTask();

            ~ArrayGenTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_ARRAY_ARRAY_GEN_TASK_H