#ifndef PLAZMA_LIB_SYS_PARAMETER_H
#define PLAZMA_LIB_SYS_PARAMETER_H

#include <string>

namespace sys {

    class Parameter {

        public:

            Parameter();

            Parameter(const std::string& name);

            Parameter(const std::string& name, const std::string& description);

            Parameter(const std::string& name, const std::string& description, const std::string& dataType);

            Parameter(const std::string& name, const std::string& description, const std::string& dataType, bool required);

            //

            Parameter(const std::string& name, const std::string& description, bool required);

            Parameter(const std::string& name, bool required);

            ~Parameter();

            std::string getName();

            void setName(const std::string& name);

            std::string getDescription();

            void setDescription(const std::string& description);

            std::string getDataType();

            void setDataType(const std::string& dataType);

            bool isRequired();

            void setRequired(bool required);

            std::string getValue();

            void setValue(const std::string& value);

            bool hasValue();

            std::string getDefaultValue();

            void setDefaultValue(const std::string& defaultValue);

            void resetValue();

            void reset();

            bool isFound();

            void setFound(bool found);

            Parameter* clone();

        protected:

            bool hasFlag = false; // has value

            bool found = false;   // found in parameter list

        private:

            std::string name;

            std::string description;

            std::string dataType;

            bool required = false;

            std::string value;

            std::string defaultValue;

    };

}
#endif // PLAZMA_LIB_SYS_PARAMETER_H