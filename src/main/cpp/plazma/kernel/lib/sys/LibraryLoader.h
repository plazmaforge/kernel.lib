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

namespace sys {

template <class T> class LibraryLoader {

  private:
    
    #ifdef _WIN32
    HMODULE	_handle;
    #else
    void *_handle;
    #endif

    std::string _pathToLib;
    std::string _allocClassSymbol;
    std::string _deleteClassSymbol;

  public:

    LibraryLoader(std::string const &pathToLib,
                  std::string const &allocClassSymbol = "allocator",
                  std::string const &deleteClassSymbol = "deleter") {

        _handle = nullptr;
        _pathToLib = pathToLib;
        _allocClassSymbol = allocClassSymbol;
        _deleteClassSymbol = deleteClassSymbol; 

    }

    ~LibraryLoader() {

    }

    void openLibrary() {
      #ifdef _WIN32
      if (!(_handle = LoadLibrary(_pathToLib.c_str()))) {
        std::cerr << "Can't open and load " << _pathToLib << std::endl;
	  }
      #else
	  if (!(_handle = dlopen(_pathToLib.c_str(), RTLD_NOW | RTLD_LAZY))) {
	    std::cerr << dlerror() << std::endl;
	  } 
      #endif
    }

    std::shared_ptr<T> getInstance() {

      using allocClass = T * (*)();
  	  using deleteClass = void(*)(T *);

      #ifdef _WIN32
      //using allocClass = T * (*)();
  	  //using deleteClass = void(*)(T *);

	  auto allocFunc = reinterpret_cast<allocClass>(GetProcAddress(_handle, _allocClassSymbol.c_str()));
	  auto deleteFunc = reinterpret_cast<deleteClass>(GetProcAddress(_handle, _deleteClassSymbol.c_str()));

	  if (!allocFunc || !deleteFunc) {
        closeLibrary();
		std::cerr << "Can't find allocator or deleter symbol in " << _pathToLib << std::endl;
	  }

	  //return std::shared_ptr<T>(allocFunc(), [deleteFunc](T *p) { deleteFunc(p); });    
      #else
      //using allocClass = T *(*)();
	  //using deleteClass = void (*)(T *);

	  auto allocFunc = reinterpret_cast<allocClass>(dlsym(_handle, _allocClassSymbol.c_str()));
      auto deleteFunc = reinterpret_cast<deleteClass>(dlsym(_handle, _deleteClassSymbol.c_str()));

	  if (!allocFunc || !deleteFunc) {
        closeLibrary();
        std::cerr << dlerror() << std::endl;
	  }

	  //return std::shared_ptr<T>(allocFunc(), [deleteFunc](T *p){ deleteFunc(p); });
      #endif

      return std::shared_ptr<T>(allocFunc(), [deleteFunc](T *p){ deleteFunc(p); });

    }

    /*
    ** Correctly delete the instance of the "dynamically loaded" class.
    */
    void closeLibrary() {
      #ifdef _WIN32
      if (FreeLibrary(_handle) == 0) {
	    std::cerr << "Can't close " << _pathToLib << std::endl;
	  }
      #else
      if (dlclose(_handle) != 0) {
	    std::cerr << dlerror() << std::endl;
	  }
      #endif
    }

};

}
#endif // PLAZMA_KERNEL_LIB_SYS_LIBRARY_LOADER_H