#include "TaskProviderDescriptor.h"

namespace task {

    TaskProviderDescriptor::TaskProviderDescriptor() {
        provider = nullptr;
        initialized = false;
        hasError = false;
        hasLibrary = false;
    }

    TaskProviderDescriptor::~TaskProviderDescriptor() {
        destroy();
    }

    void TaskProviderDescriptor::destroy() {
        if (provider != nullptr) {
            // TODO
        }
    }

}
