#ifndef PLAZMA_KERNEL_LIB_TASK_JSON_JSON_FORMAT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_JSON_JSON_FORMAT_TASK_H

#include "plazma/kernel/lib/task/format/FormatTask.h"

const std::string TASK_FORMAT_JSON = "format-json";

namespace task {

    class JsonFormatTask: public FormatTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_JSON_NODE_EMPTY = "Can't execute task: JSON Node is empty";
            const std::string ERROR_JSON_TOKENS_EMPTY = "Can't execute task: JSON Tokens are empty";

            JsonFormatTask();

            ~JsonFormatTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_JSON_JSON_FORMAT_TASK_H