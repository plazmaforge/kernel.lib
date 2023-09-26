#ifndef PLAZMA_KERNEL_LIB_SYS_PARAMETERS_H
#define PLAZMA_KERNEL_LIB_SYS_PARAMETERS_H

#include <string>
//#include <map>
#include <vector>

#include "Parameter.h"

namespace sys {

    class Parameters {

        public:

            Parameters();

            ~Parameters();

            ////

            void addParameter(Parameter* parameter);

            void addParameter(const std::string& name);

            void addParameter(const std::string& name, const std::string& description);

            void addParameter(const std::string& name, const std::string& description, const std::string& datatType);

            void addParameter(const std::string& name, const std::string& description, const std::string& datatType, bool required);

            void addParameter(const std::string& name, const std::string& description, bool required);

            void addParameter(const std::string& name, bool required);

            ////

            bool hasParameters();

            int getParameterCount();

            Parameter* getParameter(const int index);

            Parameter* getParameter(const std::string& name);

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

            ////

        protected:

            //std::map<std::string, Parameter*>* getParameters();
            std::vector<Parameter*>* getParameters();

            bool equalsName(Parameter* parameter, const std::string& name);

        private:

            //std::map<std::string, Parameter*>* parameters = nullptr;
            std::vector<Parameter*>* parameters = nullptr;

    };

}
#endif // PLAZMA_KERNEL_LIB_SYS_PARAMETERS_H