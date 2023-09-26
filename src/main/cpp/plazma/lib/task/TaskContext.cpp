#include <string>
#include "TaskContext.h"

namespace task {

    TaskContext::TaskContext() {
        parameters = nullptr;
    }

    TaskContext::~TaskContext() {
        if (parameters != nullptr) {
            delete parameters;
        }
    }

    std::string TaskContext::getTaskName() {
        return taskName;
    }

    void TaskContext::setTaskName(std::string& taskName) {
        this->taskName = taskName;
    }

    Parameters* TaskContext::getParameters() {
        if (parameters == nullptr) {
            parameters = new Parameters();
        }
        return parameters;
    }

    void TaskContext::setParameters(Parameters* parameters) {
        this->parameters = parameters;
    }

    ////

    Parameter* TaskContext::getFoundParameter(const std::string& name) {

        ///////////////////////////////////////////
        Parameter* parameter = getParameters()->getParameter(name);
        if (parameter == nullptr) {
            return nullptr;
        }

        // We can have a parameter in the list but this parameter can be not found
        if (!parameter->isFound()) {
            return nullptr;
        }

        return parameter;
        ////////////////////////////////////////////
    }

    ////

    bool TaskContext::hasParameter(const std::string& name) {

        ////////////////////////////////////////////
        Parameter* parameter = getFoundParameter(name);
        return parameter != nullptr;
        ////////////////////////////////////////////

        //return getParameters()->hasParameter(name);
    }

    std::string TaskContext::getStringParameter(const std::string& name) {
        return getParameters()->getStringParameter(name);
    }

    std::string TaskContext::getStringParameter(const std::string& name, std::string defValue) {
        return getParameters()->getStringParameter(name, defValue);
    }

    int TaskContext::getIntParameter(const std::string& name) {
        return getParameters()->getIntParameter(name);
    }

    int TaskContext::getIntParameter(const std::string& name, int defValue) {
        return getParameters()->getIntParameter(name, defValue);
    }

    float TaskContext::getFloatParameter(const std::string& name) {
        return getParameters()->getFloatParameter(name);
    }

    float TaskContext::getFloatParameter(const std::string& name, float defValue) {
        return getParameters()->getFloatParameter(name, defValue);
    }

    bool TaskContext::getBooleanParameter(const std::string& name) {
        return getParameters()->getBooleanParameter(name);
    }

    bool TaskContext::getBooleanParameter(const std::string& name, bool defValue) {
        return getParameters()->getBooleanParameter(name, defValue);
    }

    bool TaskContext::getFlagParameter(const std::string& name) {

        ////////////////////////////////////////////
        Parameter* parameter = getFoundParameter(name);
        if (parameter == nullptr) {
            return false;
        }

        if (!parameter->hasValue() || parameter->getValue().empty()) {
            return true; // option - only name
        }
        ////////////////////////////////////////////

        return getParameters()->getBooleanParameter(name);
    }

    bool TaskContext::getFlagParameter(const std::string& name, bool defValue) {

        ////////////////////////////////////////////
        Parameter* parameter = getFoundParameter(name);
        if (parameter == nullptr) {
            return defValue;
        }

        if (!parameter->hasValue() || parameter->getValue().empty()) {
            return true; // option - only name
        }
        ////////////////////////////////////////////

        return getParameters()->getBooleanParameter(name, defValue);
    }

}
