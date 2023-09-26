#ifndef PLAZMA_LIB_TASK_YAML_YAML2JSON_CONVERT_TASK_H
#define PLAZMA_LIB_TASK_YAML_YAML2JSON_CONVERT_TASK_H

#include "plazma/lib/task/convert/ConvertTask.h"

const std::string TASK_CONVERT_YAML2JSON = "convert-yaml2json";

namespace task {

    class Yaml2JsonConvertTask: public ConvertTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_YAML_NODE_EMPTY = "Can't execute task: YAML Node is empty";
            const std::string ERROR_YAML_TOKENS_EMPTY = "Can't execute task: YAML Tokens are empty";
            const std::string ERROR_JSON_NODE_EMPTY = "Can't execute task: JSON Node is empty";

            Yaml2JsonConvertTask();

            ~Yaml2JsonConvertTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_LIB_TASK_YAML_YAML2JSON_CONVERT_TASK_H