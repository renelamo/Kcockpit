# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /snap/clion/61/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /snap/clion/61/bin/cmake/linux/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/rene/ksp/teensy

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/rene/ksp/teensy/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/teensy.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/teensy.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/teensy.dir/flags.make

CMakeFiles/teensy.dir/src/Bargraph.cpp.o: CMakeFiles/teensy.dir/flags.make
CMakeFiles/teensy.dir/src/Bargraph.cpp.o: ../src/Bargraph.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/teensy/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/teensy.dir/src/Bargraph.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/teensy.dir/src/Bargraph.cpp.o -c /home/rene/ksp/teensy/src/Bargraph.cpp

CMakeFiles/teensy.dir/src/Bargraph.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/teensy.dir/src/Bargraph.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/teensy/src/Bargraph.cpp > CMakeFiles/teensy.dir/src/Bargraph.cpp.i

CMakeFiles/teensy.dir/src/Bargraph.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/teensy.dir/src/Bargraph.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/teensy/src/Bargraph.cpp -o CMakeFiles/teensy.dir/src/Bargraph.cpp.s

CMakeFiles/teensy.dir/src/LED.cpp.o: CMakeFiles/teensy.dir/flags.make
CMakeFiles/teensy.dir/src/LED.cpp.o: ../src/LED.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/teensy/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/teensy.dir/src/LED.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/teensy.dir/src/LED.cpp.o -c /home/rene/ksp/teensy/src/LED.cpp

CMakeFiles/teensy.dir/src/LED.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/teensy.dir/src/LED.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/teensy/src/LED.cpp > CMakeFiles/teensy.dir/src/LED.cpp.i

CMakeFiles/teensy.dir/src/LED.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/teensy.dir/src/LED.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/teensy/src/LED.cpp -o CMakeFiles/teensy.dir/src/LED.cpp.s

CMakeFiles/teensy.dir/src/MAX7221.cpp.o: CMakeFiles/teensy.dir/flags.make
CMakeFiles/teensy.dir/src/MAX7221.cpp.o: ../src/MAX7221.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/teensy/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/teensy.dir/src/MAX7221.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/teensy.dir/src/MAX7221.cpp.o -c /home/rene/ksp/teensy/src/MAX7221.cpp

CMakeFiles/teensy.dir/src/MAX7221.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/teensy.dir/src/MAX7221.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/teensy/src/MAX7221.cpp > CMakeFiles/teensy.dir/src/MAX7221.cpp.i

CMakeFiles/teensy.dir/src/MAX7221.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/teensy.dir/src/MAX7221.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/teensy/src/MAX7221.cpp -o CMakeFiles/teensy.dir/src/MAX7221.cpp.s

CMakeFiles/teensy.dir/src/SevenSeg.cpp.o: CMakeFiles/teensy.dir/flags.make
CMakeFiles/teensy.dir/src/SevenSeg.cpp.o: ../src/SevenSeg.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/teensy/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/teensy.dir/src/SevenSeg.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/teensy.dir/src/SevenSeg.cpp.o -c /home/rene/ksp/teensy/src/SevenSeg.cpp

CMakeFiles/teensy.dir/src/SevenSeg.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/teensy.dir/src/SevenSeg.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/teensy/src/SevenSeg.cpp > CMakeFiles/teensy.dir/src/SevenSeg.cpp.i

CMakeFiles/teensy.dir/src/SevenSeg.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/teensy.dir/src/SevenSeg.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/teensy/src/SevenSeg.cpp -o CMakeFiles/teensy.dir/src/SevenSeg.cpp.s

CMakeFiles/teensy.dir/src/main.cpp.o: CMakeFiles/teensy.dir/flags.make
CMakeFiles/teensy.dir/src/main.cpp.o: ../src/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/teensy/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/teensy.dir/src/main.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/teensy.dir/src/main.cpp.o -c /home/rene/ksp/teensy/src/main.cpp

CMakeFiles/teensy.dir/src/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/teensy.dir/src/main.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/teensy/src/main.cpp > CMakeFiles/teensy.dir/src/main.cpp.i

CMakeFiles/teensy.dir/src/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/teensy.dir/src/main.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/teensy/src/main.cpp -o CMakeFiles/teensy.dir/src/main.cpp.s

# Object files for target teensy
teensy_OBJECTS = \
"CMakeFiles/teensy.dir/src/Bargraph.cpp.o" \
"CMakeFiles/teensy.dir/src/LED.cpp.o" \
"CMakeFiles/teensy.dir/src/MAX7221.cpp.o" \
"CMakeFiles/teensy.dir/src/SevenSeg.cpp.o" \
"CMakeFiles/teensy.dir/src/main.cpp.o"

# External object files for target teensy
teensy_EXTERNAL_OBJECTS =

teensy: CMakeFiles/teensy.dir/src/Bargraph.cpp.o
teensy: CMakeFiles/teensy.dir/src/LED.cpp.o
teensy: CMakeFiles/teensy.dir/src/MAX7221.cpp.o
teensy: CMakeFiles/teensy.dir/src/SevenSeg.cpp.o
teensy: CMakeFiles/teensy.dir/src/main.cpp.o
teensy: CMakeFiles/teensy.dir/build.make
teensy: CMakeFiles/teensy.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/rene/ksp/teensy/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Linking CXX executable teensy"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/teensy.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/teensy.dir/build: teensy

.PHONY : CMakeFiles/teensy.dir/build

CMakeFiles/teensy.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/teensy.dir/cmake_clean.cmake
.PHONY : CMakeFiles/teensy.dir/clean

CMakeFiles/teensy.dir/depend:
	cd /home/rene/ksp/teensy/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/rene/ksp/teensy /home/rene/ksp/teensy /home/rene/ksp/teensy/cmake-build-debug /home/rene/ksp/teensy/cmake-build-debug /home/rene/ksp/teensy/cmake-build-debug/CMakeFiles/teensy.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/teensy.dir/depend

