CXX = clang++
CXXFLAGS = -c -Wall -g -O2 -std=c++11
LDFLAGS =

SOURCES = main.cpp USBDevice.cpp
OBJECTS = $(SOURCES:.cpp=.o)
EXECUTABLE = main

all: $(SOURCES) $(EXECUTABLE)

$(EXECUTABLE): $(OBJECTS) 
	$(CXX) $(LDFLAGS) $(OBJECTS) -o $@

.cpp.o: 
	$(CXX) $(CXXFLAGS) $< -o $@

clean:
	rm $(OBJECTS) $(EXECUTABLE)
