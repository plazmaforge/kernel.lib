#ifndef PLAZMA_KERNEL_LIB_TASK_SYS_CALENDAR_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_SYS_CALENDAR_TASK_H

#include <string>

#include "plazma/kernel/lib/task/BaseTask.h"

// calendar
const std::string TASK_PRINT_CALENDAR = "print-calendar";
const std::string TASK_PRINT_YEAR_CALENDAR = "print-year-calendar";
const std::string TASK_PRINT_MONTH_CALENDAR = "print-month-calendar";

namespace task {

    class CalendarTask: public BaseTask {

        public:

            ///////////////////////////////////////////////////////////
            // parameters:
            ///////////////////////////////////////////////////////////

            const std::string PARAMETER_TYPE = "type";
            const std::string PARAMETER_YEAR = "year";
            const std::string PARAMETER_MONTH = "month";

            //const std::string PARAMETER_OUTPUT_FILE = "output-file";              // Output file name
            //const std::string PARAMETER_DISPLAY = "display";                      // Display result
            //const std::string PARAMETER_COLOR = "color";                          // Color flag
            //const std::string PARAMETER_STDERR = "stderr";                        // StdErr mode

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            //const std::string ERROR_INPUT = "Can't execute task";

            CalendarTask();

            ~CalendarTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

        private:

            void executePrintCalendar(TaskContext* ctx);

            void executePrintYearCalendar(TaskContext* ctx);

            void executePrintMonthCalendar(TaskContext* ctx);

            ////

            int getYear(TaskContext* ctx);

            int getMonth(TaskContext* ctx);

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_SYS_CALENDAR_TASK_H