
#include "plazma/lib/num/numlib.h"
#include "Parameters.h"
#include <algorithm> // WIN32: std::transform

//#include <iostream>

namespace sys {

    Parameters::Parameters() {
        parameters = nullptr;
    }

    Parameters::~Parameters() {
        //std::cout << "Destroy Parameters: Start" << std::endl;
        if (parameters != nullptr) {
            //std::cout << "Destroy Parameters: Point-1" << std::endl;
            int count = parameters->size();
            Parameter* parameter = nullptr;
            //std::cout << "Destroy Parameters: Point-2" << std::endl;
            for (int i = 0 ; i < count; i++) {
                parameter = parameters->at(i);
                //std::cout << "Destroy Parameters: Point-3-" << std::to_string(i) << std::endl;
                if (parameter == nullptr) {
                    continue;
                }
                delete parameter;
            }
        }
        //std::cout << "Destroy Parameters: End" << std::endl;
    }

    ///

    void Parameters::addParameter(Parameter* parameter) {
        if (parameter == nullptr) {
            return;
        }

        //(*getParameters())[parameter->getName()] = parameter;
        getParameters()->push_back(parameter);

    }

    void Parameters::addParameter(const std::string& name) {
        addParameter(new Parameter(name));
    }

    void Parameters::addParameter(const std::string& name, const std::string& description) {
        addParameter(new Parameter(name, description));
    }

    void Parameters::addParameter(const std::string& name, const std::string& description, const std::string& datatType) {
        addParameter(new Parameter(name, description, datatType));
    }

    void Parameters::addParameter(const std::string& name, const std::string& description, const std::string& datatType, bool required) {
        addParameter(new Parameter(name, description, datatType, required));
    }

    void Parameters::addParameter(const std::string& name, const std::string& description, bool required) {
        addParameter(new Parameter(name, description, required));
    }

    void Parameters::addParameter(const std::string& name, bool required) {
        addParameter(new Parameter(name, required));
    }

    ////

    std::vector<Parameter*>* Parameters::getParameters() {
        if (parameters == nullptr) {
            parameters = new std::vector<Parameter*>();
        }
        return parameters;
    }

    bool Parameters::hasParameters() {
        return getParameterCount() > 0;
    }

    int Parameters::getParameterCount() {
        return parameters == nullptr ? 0 : parameters->size();
    }


    // byIndex
    Parameter* Parameters::getParameter(const int index) {
        if (parameters == nullptr) {
            return nullptr;
        }
        if (index < 0 && index >= parameters->size()) {
            // Not found
            return nullptr;
        }
        return parameters->at(index);
    }

    // byName
    Parameter* Parameters::getParameter(const std::string& name) {
        if (parameters == nullptr) {
            return nullptr;
        }
        if (name.empty()) {
            // Not found
            return nullptr;
        }
        int count = getParameterCount();
        Parameter* parameter = nullptr;
        for (int i = 0; i < count; i++) {
            parameter = getParameter(i);
            if (equalsName(parameter, name)) {
                return parameter;
            }
        }
        // Not found
        return nullptr;
    }

    bool Parameters::hasParameter(const std::string& name) {
        Parameter* parameter = getParameter(name);
        return parameter != nullptr;
    }

    bool Parameters::equalsName(Parameter* parameter, const std::string& name) {
       if (parameter == nullptr || name.empty()) {
           return false;
        }
        return name == parameter->getName(); // TODO: Check 'longName'
    }

    ////

    // string

    std::string Parameters::getStringParameter(const std::string& name) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return "";
        }
        return parameter->getValue();
    }

    std::string Parameters::getStringParameter(const std::string& name, std::string defValue) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return defValue;
        }
        return parameter->getValue();
    }

    // int

    int Parameters::getIntParameter(const std::string& name) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return 0;
        }
        return numlib::toInt(parameter->getValue());
    }

    int Parameters::getIntParameter(const std::string& name, int defValue) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return defValue;
        }
        return numlib::toInt(parameter->getValue(), defValue);
    }

    // float

    float Parameters::getFloatParameter(const std::string& name) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return 0;
        }
        return numlib::toFloat(parameter->getValue());
    }

    float Parameters::getFloatParameter(const std::string& name, float defValue) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return defValue;
        }
        return numlib::toFloat(parameter->getValue(), defValue);
    }

    // boolean


    // [UTIL]
    bool toBoolean(const std::string& value, bool defValue) {
        if (value.empty()) {
            return defValue;
        }

        std::string str = value;
        std::transform(str.begin(), str.end(), str.begin(), ::tolower);

        // true
        if (str == "true" || str == "yes" || str == "t" || str == "y") {
            return true;
        }

        // false
        if (str == "false" || str == "no" || str == "f" || str == "n") {
            return false;
        }

        // default
        return defValue;

    }

    bool toBoolean(const std::string& value) {
        return toBoolean(value, false);
    }

    bool Parameters::getBooleanParameter(const std::string& name) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return false;
        }
        return toBoolean(parameter->getValue());
    }

    bool Parameters::getBooleanParameter(const std::string& name, bool defValue) {
        Parameter* parameter = getParameter(name);
        if (parameter == nullptr) {
            return defValue;
        }
        return toBoolean(parameter->getValue(), defValue);
    }

}
