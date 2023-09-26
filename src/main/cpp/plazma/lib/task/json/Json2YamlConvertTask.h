#ifndef PLAZMA_KERNEL_LIB_TASK_JSON_JSON2YAML_CONVERT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_JSON_JSON2YAML_CONVERT_TASK_H

#include "plazma/lib/task/convert/ConvertTask.h"

const std::string TASK_CONVERT_JSON2YAML = "convert-json2yaml";

namespace task {

    class Json2YamlConvertTask: public ConvertTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_JSON_NODE_EMPTY = "Can't execute task: JSON Node is empty";
            const std::string ERROR_JSON_TOKENS_EMPTY = "Can't execute task: JSON Tokens are empty";
            const std::string ERROR_YAML_NODE_EMPTY = "Can't execute task: YAML Node is empty";

            Json2YamlConvertTask();

            ~Json2YamlConvertTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_JSON_JSON2YAML_CONVERT_TASK_H