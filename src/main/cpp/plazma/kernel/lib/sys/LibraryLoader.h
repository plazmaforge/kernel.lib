#ifndef PLAZMA_KERNEL_LIB_SYS_LIBRARY_LOADER_H
#define PLAZMA_KERNEL_LIB_SYS_LIBRARY_LOADER_H

#include <memory>
#include <string>
#include <iostream>

#ifdef _WIN32
#include <windows.h>
#else
#include <dlfcn.h>
#endif

#include "plazma/kernel/lib/sys/syslib.h"

namespace sys {

// https://tldp.org/HOWTO/C++-dlopen/
// https://tldp.org/HOWTO/C++-dlopen/thesolution.html#loadingclasses

template <class T> class LibraryLoader {

  using CreateType = T* (*)();
  using DestroyType = void(*)(T *);

  private:
    
    #ifdef _WIN32
    HMODULE	_handle;
    #else
    void *_handle;
    #endif

    std::string _path;
    std::string _createName;
    std::string _destroyName;

    CreateType create;
    DestroyType destroy;

    T* instance;

  public:

    LibraryLoader(std::string const &path,
                  std::string const &createName = "create",
                  std::string const &destroyName = "destroy") {

        _handle = nullptr;
        _path = path;
        _createName = createName;
        _destroyName = destroyName;

    }

    ~LibraryLoader() {

    }

    void error(const std::string& message, bool details) {
      std::cerr << message;
      if (details && syslib::isSupportLibraryError()) {
        std::string err = syslib::getLibraryError();
        if (!err.empty()) {
          std::cerr << ": " << err;
        }
      }
      std::cerr << std::endl;
    }

    void error(const std::string& message) {
      error(message, false);
    }

    void openLibrary() {
      _handle = syslib::loadLibrary(_path);
      if (!_handle) {
        error("Can't load library " + _path, true);
      }

      // #ifdef _WIN32
      // if (!(_handle = LoadLibrary(_path.c_str()))) {
      //   std::cerr << "Can't load library " << _path << std::endl;
	    // }
      // #else
      // if (!(_handle = dlopen(_path.c_str(), RTLD_NOW | RTLD_LAZY))) {
      //   std::cerr << "Can't load library " << _path << std::endl;
	    //   std::cerr << dlerror() << std::endl;
	    // } 
      // #endif

    }


    //std::shared_ptr<T> getInstance() {

    T* getInstance() {    

      if (_handle == nullptr) {
        return nullptr;
      }

      /*auto*/ create = reinterpret_cast<CreateType>(syslib::getSymbol(_handle, _createName));
      /*auto*/ destroy = reinterpret_cast<DestroyType>(syslib::getSymbol(_handle, _destroyName));

      if (!create || !destroy) {
        closeLibrary();
        error("Can't find symbol 'create' or 'destroy' in " + _path);
        return nullptr;
      }

      instance = create();
      return instance;
      //return std::shared_ptr<T>(create(), [destroy](T *p){ destroy(p); });

      // #ifdef _WIN32

      // /*auto*/ create = reinterpret_cast<CreateType>(GetProcAddress(_handle, _createName.c_str()));
      // /*auto*/ destroy = reinterpret_cast<DestroyType>(GetProcAddress(_handle, _destroyName.c_str()));

      // if (!create || !destroy) {
      //   closeLibrary();
      //   std::cerr << "Can't find symbol 'create' or 'destroy' in " << _path << std::endl;
      // }

      // //return std::shared_ptr<T>(create(), [destroy](T *p) { destroy(p); });    
      // #else

      // /*auto*/ create = reinterpret_cast<CreateType>(dlsym(_handle, _createName.c_str()));
      // /*auto*/ destroy = reinterpret_cast<DestroyType>(dlsym(_handle, _destroyName.c_str()));

      // if (!create || !destroy) {
      //   closeLibrary();
      //   std::cerr << "Can't find symbol 'create' or 'destroy' in " << _path << std::endl;
      //   std::cerr << dlerror() << std::endl;
      // }

      // #endif

      // instance = create();
      // return instance;

      //return std::shared_ptr<T>(create(), [destroy](T *p){ destroy(p); });

    }

    /*
    ** Correctly delete the instance of the "dynamically loaded" class.
    */
    void closeLibrary() {
      if (instance != nullptr) {
            if (destroy == nullptr) {
                error("Can't destroy object: Destroy function is not implemented");
            }
            destroy(instance);
      }

      if (!syslib::closeLibrary(_handle)) {
        error("Can't close library " + _path, true);
      }

      // #ifdef _WIN32
      // if (FreeLibrary(_handle) == 0) {
      //   std::cerr << "Can't close library " << _path << std::endl;
      // }
	    // #else
      // if (dlclose(_handle) != 0) {
      //   std::cerr << "Can't close library " << _path << std::endl;
      //   std::cerr << dlerror() << std::endl;
      // }

      // #endif

    }

};

}
#endif // PLAZMA_KERNEL_LIB_SYS_LIBRARY_LOADER_H