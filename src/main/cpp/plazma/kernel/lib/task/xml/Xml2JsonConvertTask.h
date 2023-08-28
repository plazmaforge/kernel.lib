#ifndef PLAZMA_KERNEL_LIB_TASK_XML_XML2JSON_CONVERT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_XML_XML2JSON_CONVERT_TASK_H

#include "plazma/kernel/lib/task/convert/ConvertTask.h"

const std::string TASK_CONVERT_XML2JSON  = "convert-xml2json";

namespace task {

    class Xml2JsonConvertTask: public ConvertTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_XML_NODE_EMPTY = "Can't execute task: XML Node is empty";
            const std::string ERROR_XML_TOKENS_EMPTY = "Can't execute task: XML Tokens are empty";
            const std::string ERROR_JSON_NODE_EMPTY = "Can't execute task: JSON Node is empty";

            Xml2JsonConvertTask();

            ~Xml2JsonConvertTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_XML_XML2JSON_CONVERT_TASK_H