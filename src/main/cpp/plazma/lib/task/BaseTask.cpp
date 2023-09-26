
#include "task_helper.h"
#include "BaseTask.h"

//#include <iostream>

namespace task {

    BaseTask::BaseTask() {
        initFlag = false;
        parameters = nullptr;
    }

    BaseTask::~BaseTask() {
        //std::cout << "Destroy BaseTask: Start" << std::endl;
        if (parameters != nullptr) {
            delete parameters;
        }
        //std::cout << "Destroy BaseTask: End" << std::endl;
    }

    void BaseTask::init() {
        if (initFlag) {
            return;
        }
        initParameters();
        initFlag = true;
    }

    void BaseTask::initParameters() {
        // by default
    }

    void BaseTask::validate(TaskContext* ctx) {
        if (ctx == nullptr) {
            return;
        }
        validateParameters(ctx->getParameters());
    }

    void BaseTask::validateParameters(Parameters* parameters) {
        if (parameters == nullptr) {
            return;
        }
        // by default
    }

    void BaseTask::dump(TaskContext* ctx) {
        if (ctx == nullptr) {
            return;
        }
        dumpParameters(ctx->getParameters());
    }

    void BaseTask::dumpParameters(Parameters* parameters) {
        if (parameters == nullptr) {
            return;
        }
                
        if (!parameters->hasParameters()) {
            return;
        }

        int parameterLen = maxParameterLen(parameters);
        int count = parameters->getParameterCount();
        Parameter* parameter = nullptr;

        for (int i = 0; i < count; i++) {
            parameter = parameters->getParameter(i);
            printParameter(parameter, parameterLen);
        }

   }

    Parameters* BaseTask::getParameters() {
        if (parameters == nullptr) {
            parameters = new Parameters();
        }
        return parameters;
    }

    //

    bool BaseTask::isDysplayProcessing() {
        return dysplayProcessing;
    }

    void BaseTask::setDysplayProcessing(bool dysplayProcessing) {
        this->dysplayProcessing = dysplayProcessing;
    }

    bool BaseTask::isDysplayTiming() {
        return dysplayTiming;
    }

    void BaseTask::setDysplayTiming(bool dysplayTiming) {
        this->dysplayTiming = dysplayTiming;
    }

}
