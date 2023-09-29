CC=g++
#CC=clang++

CFLAGS=-c -std=c++17 -Wall -I../../src/main/cpp -O2 -Os
LDFLAGS=

#clang and MacOS (libc++): Resolved "Undefined symbols for architecture x86_64"
#LDFLAGS=-lc++

#######################################################################

#https://stackoverflow.com/questions/714100/os-detecting-makefile

#ifeq ($(OS),Windows_NT)
#    CCFLAGS += -D WIN32
#    ifeq ($(PROCESSOR_ARCHITEW6432),AMD64)
#        CCFLAGS += -D AMD64
#    else
#        ifeq ($(PROCESSOR_ARCHITECTURE),AMD64)
#            CCFLAGS += -D AMD64
#        endif
#        ifeq ($(PROCESSOR_ARCHITECTURE),x86)
#            CCFLAGS += -D IA32
#        endif
#    endif
#else
#    UNAME_S := $(shell uname -s)
#    ifeq ($(UNAME_S),Linux)
#        CCFLAGS += -D LINUX
#    endif
#    ifeq ($(UNAME_S),Darwin)
#        CCFLAGS += -D OSX
#    endif
#    UNAME_P := $(shell uname -p)
#    ifeq ($(UNAME_P),x86_64)
#        CCFLAGS += -D AMD64
#    endif
#    ifneq ($(filter %86,$(UNAME_P)),)
#        CCFLAGS += -D IA32
#    endif
#    ifneq ($(filter arm%,$(UNAME_P)),)
#        CCFLAGS += -D ARM
#    endif
#endif


#######################################################################

START=../../src/main/cpp/plazma/lib
START_W=..\..\src\main\cpp\plazma\lib

SOURCES_ALL:=$(wildcard $(START)/**/*.cpp) $(wildcard $(START)/**/**/*.cpp)

SOURCES_MIN:=$(wildcard $(START)/sys/*.cpp) \
 $(wildcard $(START)/io/*.cpp) \
 $(wildcard $(START)/fs/*.cpp) \
 $(wildcard $(START)/str/*.cpp) \
 $(wildcard $(START)/collection/*.cpp) \
 $(wildcard $(START)/fmt/*.cpp) \
 $(wildcard $(START)/task/*.cpp) \
 $(wildcard $(START)/num/*.cpp)

SOURCES_EXT:=$(SOURCES_MIN) \
 $(wildcard $(START)/ext/*.cpp) \
 $(wildcard $(START)/text/*.cpp) \
 $(wildcard $(START)/math/*.cpp) \
 $(wildcard $(START)/time/*.cpp) \
 $(wildcard $(START)/data/node/*.cpp)

# V-ALL
SOURCES:=$(SOURCES_ALL)

# V-MIN
#SOURCES:=$(SOURCES_MIN)
  
# V-AVG
#SOURCES:=$(wildcard $(START)/**/*.cpp)
 
#SOURCES:=$(filter-out $(START)/node/*.cpp, $(SOURCES))
#SOURCES:=$(filter-out $(START)/node/**/*.cpp, $(SOURCES))

# S-TEST
SOURCES:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES))
SOURCES_MIN:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES_MIN))

# S-LIB
SOURCES_LIB:=$(wildcard $(START)/**/*.cpp) $(wildcard $(START)/task/**/*.cpp) $(wildcard $(START)/data/**/*.cpp)
SOURCES_LIB:=$(filter-out $(START)/task/run.cpp, $(SOURCES_LIB))
SOURCES_LIB:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES_LIB))

# S-LIB-XML
SOURCES_LIB_XML:=$(SOURCES_EXT) \
 $(wildcard $(START)/data/xml/*.cpp) \
 $(wildcard $(START)/task/base/*.cpp) \
 $(wildcard $(START)/task/xml/*.cpp) \
 $(wildcard $(START)/task/format/*.cpp)
 
SOURCES_LIB_XML:=$(filter-out $(START)/task/run.cpp, $(SOURCES_LIB_XML))
SOURCES_LIB_XML:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES_LIB_XML))
SOURCES_LIB_XML:=$(filter-out $(START)/task/base/BaseTaskProvider.cpp, $(SOURCES_LIB_XML))
SOURCES_LIB_XML:=$(filter-out $(START)/task/xml/Xml2JsonConvertTask.cpp, $(SOURCES_LIB_XML))
SOURCES_LIB_XML:=$(filter-out $(START)/task/xml/Xml2YamlConvertTask.cpp, $(SOURCES_LIB_XML))

OBJECTS=$(SOURCES:.cpp=.o)
OBJECTS_MIN=$(SOURCES_MIN:.cpp=.o)
OBJECTS_LIB=$(SOURCES_LIB:.cpp=.o)
OBJECTS_LIB_XML=$(SOURCES_LIB_XML:.cpp=.o)

EXECUTABLE=run
#EXECUTABLE_MIN=runm

#######################################################################

ifeq ($(OS),Windows_NT)
    #LIB_CMD=$(CC) $(LDFLAGS) -shared -o task-base.dll -Wl,--out-implib,task-base.a -W1,--export-all-symbols-Wl,--enable-auto-image-base $(OBJECTS_LIB)
    LIB_CMD=$(CC) $(LDFLAGS) -shared -o task-base.dll -Wl,--out-implib,task-base.a $(OBJECTS_LIB)
    DEL_OBJ_CMD=del /s $(START_W)\*.o
    DEL_EXE_CMD=del run.exe run_test.exe lib-*.dll task-*.dll task-*.a  
else
    UNAME_S := $(shell uname -s)
    
    #ifeq ($(UNAME_S),Linux)
    #
    #endif
        
    DEL_OBJ_CMD=rm -f $(START)/**/*.o $(START)/**/**/*.o
    DEL_EXE_CMD=rm -f run run_test
    
    ifeq ($(UNAME_S),Darwin)
        LDFLAGS += -framework CoreFoundation -framework CoreServices
        LIB_CMD=$(CC) $(LDFLAGS) -dynamiclib -fPIC -o task-base.dylib $(OBJECTS_LIB)
        DEL_EXE_CMD += lib-*.dylib task-*.dylib
    else
        LIB_CMD=$(CC) $(LDFLAGS) -shared -fPIC -o task-base.so $(OBJECTS_LIB)
        DEL_EXE_CMD += lib-*.so task-*.so
    endif
    
endif

######################################################################

all: $(SOURCES) $(EXECUTABLE)
	
#min: $(SOURCES_MIN) $(EXECUTABLE_MIN)
	
$(EXECUTABLE): $(OBJECTS) 
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

#$(EXECUTABLE_MIN): $(OBJECTS_MIN) 
#	$(CC) $(LDFLAGS) $(OBJECTS_MIN) -o $@

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@

lib: $(OBJECTS_LIB)
	$(LIB_CMD)

clean:
	$(DEL_OBJ_CMD)
	$(DEL_EXE_CMD)

######################################################################

#lib-nix: $(OBJECTS)
#	$(CC) $(LDFLAGS) -shared -fPIC -o lib-task.so $(OBJECTS_LIB)
		
#lib-mac: $(OBJECTS_LIB)
#	$(CC) $(LDFLAGS) -dynamiclib -fPIC -o lib-task.dylib $(OBJECTS_LIB)
	
#lib-mac-xml: $(OBJECTS_LIB_XML)
#	$(CC) $(LDFLAGS) -dynamiclib -fPIC -o lib-task.dylib $(OBJECTS_LIB_XML)

#lib-win: $(OBJECTS)
#	$(CC) $(LDFLAGS) -shared -o lib-task.dll -Wl,--out-implib,lib-task.a -W1,--export-all-symbols-Wl,—enable-auto-image-base $(OBJECTS_LIB)
