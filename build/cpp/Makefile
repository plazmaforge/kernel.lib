CC=g++
CFLAGS=-c  -std=c++17 -Wall -I../../src/main/cpp -O2 -Os

#CFLAGS=-c -finput-charset=UTF-8

LDFLAGS=

#######################################################################

#SOURCES1=$(wildcard $(START)/**/*.cpp)
#SOURCES2=$(wildcard $(START)/**/**/*.cpp)

START=../../src/main/cpp/plazma/kernel/lib
SOURCES:=$(wildcard $(START)/**/*.cpp) $(wildcard $(START)/**/**/*.cpp)
SOURCES:=$(filter-out $(START)/task/run_test.cpp, $(SOURCES))

OBJECTS=$(SOURCES:.cpp=.o)
EXECUTABLE=run

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS) 
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@

lib-nix: $(OBJECTS)
	$(CC) $(LDFLAGS) -shared -fPIC -o lib-task.so $(OBJECTS)
		
lib-mac: $(OBJECTS)
	$(CC) $(LDFLAGS) -dynamiclib -fPIC -o lib-task.dylib $(OBJECTS)

lib-win: $(OBJECTS)
	$(CC) $(LDFLAGS) -shared -o lib-task.dll -Wl,--out-implib,lib-task.a -W1,--export-all-symbols-Wl,—enable-auto-image-base $(OBJECTS)