CC=g++
CFLAGS=-c  -std=c++17 -Wall -I../../src/main/cpp -O2 -Os

#CFLAGS=-c -finput-charset=UTF-8

LDFLAGS=

#######################################################################

START=../../src/main/cpp/plazma/kernel/lib

# V-ALL
SOURCES:=$(wildcard $(START)/**/*.cpp) $(wildcard $(START)/**/**/*.cpp)

# V-LIB
#SOURCES:=$(wildcard $(START)/**/*.cpp) 
#SOURCES:=$(filter-out $(START)/task/BaseTaskProvider.cpp, $(SOURCES))
#SOURCES:=$(filter-out $(START)/task/TaskProvider1.cpp, $(SOURCES))
#SOURCES:=$(filter-out $(START)/task/FormatTask.cpp, $(SOURCES))
#SOURCES:=$(filter-out $(START)/task/ConvertTask.cpp, $(SOURCES))

#SOURCES:=$(filter-out $(START)/node/*.cpp, $(SOURCES))
#SOURCES:=$(filter-out $(START)/node/**/*.cpp, $(SOURCES))

# S-TEST
SOURCES:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES))

# S-LIB
SOURCES_LIB:=$(wildcard $(START)/**/*.cpp) $(wildcard $(START)/task/**/*.cpp) $(wildcard $(START)/data/**/*.cpp)
SOURCES_LIB:=$(filter-out $(START)/task/run.cpp, $(SOURCES_LIB))
SOURCES_LIB:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES_LIB))

OBJECTS=$(SOURCES:.cpp=.o)
OBJECTS_LIB=$(SOURCES_LIB:.cpp=.o)

EXECUTABLE=run

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS) 
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@

lib-nix: $(OBJECTS)
	$(CC) $(LDFLAGS) -shared -fPIC -o lib-task.so $(OBJECTS_LIB)
		
lib-mac: $(OBJECTS_LIB)
	$(CC) $(LDFLAGS) -dynamiclib -fPIC -o lib-task.dylib $(OBJECTS_LIB)

lib-win: $(OBJECTS)
	$(CC) $(LDFLAGS) -shared -o lib-task.dll -Wl,--out-implib,lib-task.a -W1,--export-all-symbols-Wl,—enable-auto-image-base $(OBJECTS_LIB)