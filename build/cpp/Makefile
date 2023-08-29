CC=g++
CFLAGS=-c  -std=c++17 -Wall -I../../src/main/cpp -O2 -Os

#CFLAGS=-c -finput-charset=UTF-8

LDFLAGS=

#######################################################################

START=../../src/main/cpp/plazma/kernel/lib

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

all: $(SOURCES) $(EXECUTABLE)
	
$(EXECUTABLE): $(OBJECTS) 
	$(CC) $(LDFLAGS) $(OBJECTS) -o $@

.cpp.o:
	$(CC) $(CFLAGS) $< -o $@

lib-nix: $(OBJECTS)
	$(CC) $(LDFLAGS) -shared -fPIC -o lib-task.so $(OBJECTS_LIB)
		
lib-mac: $(OBJECTS_LIB)
	$(CC) $(LDFLAGS) -dynamiclib -fPIC -o lib-task.dylib $(OBJECTS_LIB)
	
lib-mac-xml: $(OBJECTS_LIB_XML)
	$(CC) $(LDFLAGS) -dynamiclib -fPIC -o lib-task.dylib $(OBJECTS_LIB_XML)

lib-win: $(OBJECTS)
	$(CC) $(LDFLAGS) -shared -o lib-task.dll -Wl,--out-implib,lib-task.a -W1,--export-all-symbols-Wl,—enable-auto-image-base $(OBJECTS_LIB)