#ifndef PLAZMA_KERNEL_LIB_TASK_YAML_YAML2XML_CONVERT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_YAML_YAML2XML_CONVERT_TASK_H

#include "plazma/lib/task/convert/ConvertTask.h"

const std::string TASK_CONVERT_YAML2XML  = "convert-yaml2xml";

namespace task {

    class Yaml2XmlConvertTask: public ConvertTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_YAML_NODE_EMPTY = "Can't execute task: YAML Node is empty";
            const std::string ERROR_YAML_TOKENS_EMPTY = "Can't execute task: YAML Tokens are empty";
            const std::string ERROR_XML_NODE_EMPTY = "Can't execute task: XML Node is empty";            

            Yaml2XmlConvertTask();

            ~Yaml2XmlConvertTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_YAML_YAML2XML_CONVERT_TASK_H