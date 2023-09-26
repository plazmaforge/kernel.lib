#ifndef PLAZMA_KERNEL_LIB_TASK_SYS_SYS_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_SYS_SYS_TASK_H

#include <string>

#include "plazma/lib/task/BaseTask.h"

// sys
const std::string TASK_PRINT_SYSTEM_INFO = "print-system-info";

// date/time
const std::string TASK_PRINT_DATE = "print-date";
const std::string TASK_PRINT_TIME = "print-time";
const std::string TASK_PRINT_DATE_TIME = "print-date-time";

// math
const std::string TASK_PRINT_FACT = "print-fact";
const std::string TASK_PRINT_FIB = "print-fib";

namespace task {

    class SysTask: public BaseTask {

        public:

            ///////////////////////////////////////////////////////////
            // parameters:
            ///////////////////////////////////////////////////////////

            const std::string PARAMETER_FORMAT = "format";
            const std::string PARAMETER_VALUE = "value";

            //const std::string PARAMETER_OUTPUT_FILE = "output-file";              // Output file name
            //const std::string PARAMETER_DISPLAY = "display";                      // Display result
            //const std::string PARAMETER_COLOR = "color";                          // Color flag
            //const std::string PARAMETER_STDERR = "stderr";                        // StdErr mode

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            //const std::string ERROR_INPUT = "Can't execute task";

            SysTask();

            ~SysTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

        private:

            void executePrintSystemInfo(TaskContext* ctx);

            //

            void executePrintDate(TaskContext* ctx);

            void executePrintTime(TaskContext* ctx);

            void executePrintDateTime(TaskContext* ctx);

            //

            void executePrintFact(TaskContext* ctx);

            void executePrintFib(TaskContext* ctx);


            ////

            std::string formatCurrentDate(TaskContext* ctx);

            std::string formatCurrentTime(TaskContext* ctx);

            std::string formatCurrentDateTime(TaskContext* ctx);

            ////

            std::string getFormat(TaskContext* ctx);

            std::string getValue(TaskContext* ctx);

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_SYS_SYS_TASK_H