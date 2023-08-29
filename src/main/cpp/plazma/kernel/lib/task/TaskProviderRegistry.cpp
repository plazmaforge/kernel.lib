#include "plazma/kernel/lib/sys/syslib.h"

#include "TaskProviderDescriptor.h"
#include "TaskProviderRegistry.h"

namespace task {

    TaskProviderRegistry::TaskProviderRegistry() {
    }

    TaskProviderRegistry::~TaskProviderRegistry() {
        destroy();
    }

    ////

    sys::LibraryLoader<TaskProvider>* createLibraryLoader(const std::string& path) {
        sys::LibraryLoader<TaskProvider>* loader = new sys::LibraryLoader<TaskProvider>(path);
        return loader;
    }

    TaskProvider* loadTaskProvider(sys::LibraryLoader<TaskProvider>* loader) {
        if (loader == nullptr) {
            return nullptr;
        }
        loader->openLibrary();
        return loader->getInstance();
    }

    ////

    void TaskProviderRegistry::addStaticProvider(const std::string& name, TaskProvider* provider) {
        TaskProviderDescriptor* descriptor = new TaskProviderDescriptor();
        descriptor->providerName = name;
        descriptor->provider = provider;
        descriptor->hasLibrary = false;
        list.push_back(descriptor);
    }

    void TaskProviderRegistry::addLibraryProvider(const std::string& name, const std::string& libraryName) {
        TaskProviderDescriptor* descriptor = new TaskProviderDescriptor();
        descriptor->providerName = name;
        descriptor->libraryName = libraryName;
        descriptor->libraryPath = syslib::getLibraryPath(libraryName);
        descriptor->hasLibrary = true;
        list.push_back(descriptor);
    }

    void TaskProviderRegistry::addLibraryProvider(const std::string& name, const std::string& libraryName, const std::string& libraryPath) {
        TaskProviderDescriptor* descriptor = new TaskProviderDescriptor();
        descriptor->providerName = name;
        descriptor->libraryName = libraryName;        
        if (libraryPath.empty())  {
            descriptor->libraryPath = syslib::getLibraryPath(libraryName);
        } else {
            descriptor->libraryPath = libraryPath;
        }
        descriptor->hasLibrary = true;
        list.push_back(descriptor);
    }

    TaskProviderDescriptor* TaskProviderRegistry::getTaskDescriptor(int index) {
        if (index < 0 || index > list.size() - 1) {
            return nullptr;
        }
        return list.at(index);        
    }

    TaskProvider* TaskProviderRegistry::getTaskProvider(int index) {
        TaskProviderDescriptor* descriptor = getTaskDescriptor(index);
        if (descriptor == nullptr || descriptor->hasError) {
            return nullptr;
        }
        return descriptor->provider;
    }

    void TaskProviderRegistry::init() {
        if (list.empty()) {
            return;
        }
        TaskProviderDescriptor* descriptor = nullptr;
        for (int i = 0; i < list.size(); i++) {
            descriptor = list.at(i);
            init(descriptor);
        }
    }

    void TaskProviderRegistry::init(TaskProviderDescriptor* descriptor) {
        if (descriptor == nullptr || descriptor->initialized) {
            return;
        }

        descriptor->initialized = true;
        descriptor->hasError = true;

        if (!descriptor->hasLibrary) {
            if (descriptor->provider != nullptr) {
                descriptor->provider->init();
                descriptor->hasError = false;
            }
            return;
        }

        descriptor->loader = createLibraryLoader(descriptor->libraryPath);
        if (descriptor->loader != nullptr) {
            descriptor->provider = loadTaskProvider(descriptor->loader);
            if (descriptor->provider != nullptr) {
                descriptor->provider->init();
                descriptor->hasError = false;
            }
        }

    }

    void TaskProviderRegistry::destroy() {
        if (list.empty()) {
            return;
        }
        TaskProviderDescriptor* descriptor = nullptr;
        for (int i = 0; i < list.size(); i++) {
            descriptor = list.at(i);
            destroy(descriptor);
        }

        list.clear();
    }

    void TaskProviderRegistry::destroy(TaskProviderDescriptor* descriptor) {
        if (descriptor == nullptr) {
            return;
        }

        if (!descriptor->hasLibrary) {
            if (descriptor->provider != nullptr) {
                delete descriptor->provider;
                descriptor->provider = nullptr;
            }
            return;
        }

        if (descriptor->loader != nullptr) {
            descriptor->loader->closeLibrary();
            descriptor->provider = nullptr;
        }
    }


}
