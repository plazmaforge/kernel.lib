#ifndef PLAZMA_KERNEL_LIB_TASK_XML_XML_FORMAT_TASK_H
#define PLAZMA_KERNEL_LIB_TASK_XML_XML_FORMAT_TASK_H

#include "plazma/kernel/lib/task/FormatTask.h"

const std::string TASK_FORMAT_XML = "format-xml";

namespace task {

    class XmlFormatTask: public FormatTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_XML_NODE_EMPTY = "Can't execute task: XML Node is empty";
            const std::string ERROR_XML_TOKENS_EMPTY = "Can't execute task: XML Tokens are empty";

            XmlFormatTask();

            ~XmlFormatTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_KERNEL_LIB_TASK_XML_XML_FORMAT_TASK_H