#ifndef PLAZMA_LIB_SYS_LIBRARY_LOADER_H
#define PLAZMA_LIB_SYS_LIBRARY_LOADER_H

#include <string>
#include <iostream>
//#include <memory>

#include "syslib.h"

namespace sys {

// https://tldp.org/HOWTO/C++-dlopen/
// https://tldp.org/HOWTO/C++-dlopen/thesolution.html#loadingclasses

template <class T> class LibraryLoader {

  using CreateType = T* (*)();
  using DestroyType = void(*)(T *);

  private:
    
    //#ifdef _WIN32
    //HMODULE	_handle;
    //#else
    //void* _handle;
    //#endif

    void* _handle;

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

    ~LibraryLoader() {}

    void error(const std::string& message, bool details) {
      //std::cerr << message;
      std::string result = message;
      if (details && syslib::isSupportLibraryError()) {
        std::string err = syslib::getLibraryError();
        if (!err.empty()) {
          //std::cerr << ": " << err;
          result.append(": ");
          result.append(err);
        }
      }
      //std::cerr << std::endl;
      result.append("\n");
      syslib::error(result);
    }

    void error(const std::string& message) {
      error(message, false);
    }

    void openLibrary() {
      _handle = syslib::loadLibrary(_path);
      if (!_handle) {
        error("Cannot load library '" + _path + "'", true);
      }
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
        error("Cannot find symbol 'create' or 'destroy' in library '" + _path + "'");
        return nullptr;
      }

      instance = create();
      return instance;

      //return std::shared_ptr<T>(create(), [destroy](T *p){ destroy(p); });
      //instance = create();
      //return instance;
      //return std::shared_ptr<T>(create(), [destroy](T *p){ destroy(p); });

    }

    /*
    ** Correctly delete the instance of the "dynamically loaded" class.
    */
    void closeLibrary() {
      if (instance != nullptr) {
            if (destroy == nullptr) {
                error("Cannot destroy object: Destroy function is not implemented");
            }
            destroy(instance);
      }

      if (!syslib::closeLibrary(_handle)) {
        error("Cannot close library '" + _path + "'", true);
      }

    }

};

}
#endif // PLAZMA_LIB_SYS_LIBRARY_LOADER_H