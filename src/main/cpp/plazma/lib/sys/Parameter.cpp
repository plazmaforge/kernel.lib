#include "Parameter.h"

namespace sys {

    Parameter::Parameter() {}

    Parameter::Parameter(const std::string& name) {
        this->name = name;
    }

    Parameter::Parameter(const std::string& name, const std::string& description) {
        this->name = name;
        this->description = description;
    }

    Parameter::Parameter(const std::string& name, const std::string& description, const std::string& dataType) {
        this->name = name;
        this->description = description;
        this->dataType = dataType;
    }

    Parameter::Parameter(const std::string& name, const std::string& description, const std::string& dataType, bool required) {
        this->name = name;
        this->description = description;
        this->dataType = dataType;
        this->required = required;
    }

    //

    Parameter::Parameter(const std::string& name, const std::string& description, bool required) {
        this->name = name;
        this->description = description;
        this->required = required;
    }

    Parameter::Parameter(const std::string& name, bool required) {
        this->name = name;
        this->required = required;
    }

    Parameter::~Parameter() {}

    std::string Parameter::getName() {
        return this->name;
    }

    void Parameter::setName(const std::string& name) {
        this->name = name;
    }

    std::string Parameter::getDescription() {
        return this->description;
    }

    void Parameter::setDescription(const std::string& description) {
        this->description = description;
    }

    std::string Parameter::getDataType() {
        return this->dataType;
    }

    void Parameter::setDataType(const std::string& dataType) {
        this->dataType = dataType;
    }

    bool Parameter::isRequired() {
        return this->required;
    }

    void Parameter::setRequired(bool required) {
        this->required = required;
    }

    std::string Parameter::getValue() {
        return this->value;

    }

    void Parameter::setValue(const std::string& value) {
        this->value = value;
        this->hasFlag = true;
    }

    bool Parameter::hasValue() {
        return this->hasFlag;
    }

    std::string Parameter::getDefaultValue() {
        return this->defaultValue;
    }

    void Parameter::setDefaultValue(const std::string& defaultValue) {
        this->defaultValue = defaultValue;
    }

    void Parameter::resetValue() {
        this->value = "";
        this->hasFlag = false;
    }

    void Parameter::reset() {
        resetValue();
        this->found = false;
    }

    bool Parameter::isFound() {
        return this->found;
    }

    void Parameter::setFound(bool found) {
        this->found = found;
    }

    Parameter* Parameter::clone() {
        Parameter* result  = new Parameter();

        result->name = this->name;
        result->description = this->description;
        result->dataType = this->dataType;
        result->required = this->required;
        result->value = this->value;
        result->defaultValue = this->defaultValue;

        result->hasFlag = this->hasFlag;
        result->found = this->found;

        return result;
    }

}
