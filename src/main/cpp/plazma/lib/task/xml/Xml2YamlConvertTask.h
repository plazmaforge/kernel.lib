#ifndef PLAZMA_LIB_TASK_XML_XML2YAML_CONVERT_TASK_H
#define PLAZMA_LIB_TASK_XML_XML2YAML_CONVERT_TASK_H

#include "plazma/lib/task/convert/ConvertTask.h"

const std::string TASK_CONVERT_XML2YAML = "convert-xml2yaml";

namespace task {

    class Xml2YamlConvertTask: public ConvertTask {

        public:

            ///////////////////////////////////////////////////////////
            // errors:
            ///////////////////////////////////////////////////////////

            const std::string ERROR_XML_NODE_EMPTY = "Can't execute task: XML Node is empty";
            const std::string ERROR_XML_TOKENS_EMPTY = "Can't execute task: XML Tokens are empty";
            const std::string ERROR_YAML_NODE_EMPTY = "Can't execute task: YAML Node is empty";

            Xml2YamlConvertTask();

            ~Xml2YamlConvertTask();

            virtual void execute(TaskContext* ctx);

            virtual void initParameters();

    };

}
#endif // PLAZMA_LIB_TASK_XML_XML2YAML_CONVERT_TASK_H