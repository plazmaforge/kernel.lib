#ifndef PLAZMA_KERNEL_LIB_TASK_JSON_JSON2XML_CONVERT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_JSON_JSON2XML_CONVERT_TASK_H

#include "plazma/lib/task/convert/ConvertTask.h"

const std::string TASK_CONVERT_JSON2XML  = "convert-json2xml";

namespace task {

    class Json2XmlConvertTask: public ConvertTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_JSON_NODE_EMPTY = "Can't execute task: JSON Node is empty";
            const std::string ERROR_JSON_TOKENS_EMPTY = "Can't execute task: JSON Tokens are empty";
            const std::string ERROR_XML_NODE_EMPTY = "Can't execute task: XML Node is empty";            

            Json2XmlConvertTask();

            ~Json2XmlConvertTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_JSON_JSON2XML_CONVERT_TASK_H