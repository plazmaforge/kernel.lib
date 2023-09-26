#ifndef PLAZMA_LIB_TASK_TASK_CONTEXT_H
#define PLAZMA_LIB_TASK_TASK_CONTEXT_H

#include "plazma/lib/sys/Parameters.h" 

using namespace sys;

namespace task {

    class TaskContext {

        public:

            TaskContext();

            ~TaskContext();

            std::string getTaskName();

            void setTaskName(std::string& taskName);

            Parameters* getParameters();

            void setParameters(Parameters* parameters);

            bool hasParameter(const std::string& name);            

            ////

            std::string getStringParameter(const std::string& name);

            std::string getStringParameter(const std::string& name, std::string defValue);

            int getIntParameter(const std::string& name);

            int getIntParameter(const std::string& name, int defValue);

            float getFloatParameter(const std::string& name);

            float getFloatParameter(const std::string& name, float defValue);

            bool getBooleanParameter(const std::string& name);

            bool getBooleanParameter(const std::string& name, bool defValue);

            //

            bool getFlagParameter(const std::string& name);

            bool getFlagParameter(const std::string& name, bool defValue);

        protected: 

            Parameter* getFoundParameter(const std::string& name);

        private:

           std::string taskName;

           Parameters* parameters = nullptr;

    };

}
#endif // PLAZMA_LIB_TASK_TASK_CONTEXT_H