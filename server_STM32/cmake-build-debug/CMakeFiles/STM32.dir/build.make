# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.15

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
CMAKE_COMMAND = /snap/clion/97/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /snap/clion/97/bin/cmake/linux/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/rene/ksp/STM32

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/rene/ksp/STM32/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/STM32.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/STM32.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/STM32.dir/flags.make

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.o: ../lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.o -c /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp > CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.i

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.s

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.o: ../lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.o -c /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp > CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.i

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.s

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.o: ../lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building C object CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-gcc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.o   -c /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-gcc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c > CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.i

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-gcc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.s

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.o: ../lib/Adafruit-GFX-Library-master/glcdfont.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building C object CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-gcc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.o   -c /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/glcdfont.c

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-gcc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/glcdfont.c > CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.i

CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-gcc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-GFX-Library-master/glcdfont.c -o CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.s

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.o: ../lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.o -c /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp > CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.i

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp -o CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.s

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.o: ../lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.o -c /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp > CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.i

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp -o CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.s

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.o: ../lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Building CXX object CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.o -c /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp > CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.i

CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp -o CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.s

CMakeFiles/STM32.dir/src/Comm.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/Comm.cpp.o: ../src/Comm.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_8) "Building CXX object CMakeFiles/STM32.dir/src/Comm.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/Comm.cpp.o -c /home/rene/ksp/STM32/src/Comm.cpp

CMakeFiles/STM32.dir/src/Comm.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/Comm.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/Comm.cpp > CMakeFiles/STM32.dir/src/Comm.cpp.i

CMakeFiles/STM32.dir/src/Comm.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/Comm.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/Comm.cpp -o CMakeFiles/STM32.dir/src/Comm.cpp.s

CMakeFiles/STM32.dir/src/InputsManager.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/InputsManager.cpp.o: ../src/InputsManager.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_9) "Building CXX object CMakeFiles/STM32.dir/src/InputsManager.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/InputsManager.cpp.o -c /home/rene/ksp/STM32/src/InputsManager.cpp

CMakeFiles/STM32.dir/src/InputsManager.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/InputsManager.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/InputsManager.cpp > CMakeFiles/STM32.dir/src/InputsManager.cpp.i

CMakeFiles/STM32.dir/src/InputsManager.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/InputsManager.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/InputsManager.cpp -o CMakeFiles/STM32.dir/src/InputsManager.cpp.s

CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.o: ../src/MAX/Bargraph.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_10) "Building CXX object CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.o -c /home/rene/ksp/STM32/src/MAX/Bargraph.cpp

CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/MAX/Bargraph.cpp > CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.i

CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/MAX/Bargraph.cpp -o CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.s

CMakeFiles/STM32.dir/src/MAX/LED.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/MAX/LED.cpp.o: ../src/MAX/LED.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_11) "Building CXX object CMakeFiles/STM32.dir/src/MAX/LED.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/MAX/LED.cpp.o -c /home/rene/ksp/STM32/src/MAX/LED.cpp

CMakeFiles/STM32.dir/src/MAX/LED.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/MAX/LED.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/MAX/LED.cpp > CMakeFiles/STM32.dir/src/MAX/LED.cpp.i

CMakeFiles/STM32.dir/src/MAX/LED.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/MAX/LED.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/MAX/LED.cpp -o CMakeFiles/STM32.dir/src/MAX/LED.cpp.s

CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.o: ../src/MAX/MAX7221.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_12) "Building CXX object CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.o -c /home/rene/ksp/STM32/src/MAX/MAX7221.cpp

CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/MAX/MAX7221.cpp > CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.i

CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/MAX/MAX7221.cpp -o CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.s

CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.o: ../src/MAX/SevenSeg.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_13) "Building CXX object CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.o -c /home/rene/ksp/STM32/src/MAX/SevenSeg.cpp

CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/MAX/SevenSeg.cpp > CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.i

CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/MAX/SevenSeg.cpp -o CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.s

CMakeFiles/STM32.dir/src/OutputsManager.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/OutputsManager.cpp.o: ../src/OutputsManager.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_14) "Building CXX object CMakeFiles/STM32.dir/src/OutputsManager.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/OutputsManager.cpp.o -c /home/rene/ksp/STM32/src/OutputsManager.cpp

CMakeFiles/STM32.dir/src/OutputsManager.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/OutputsManager.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/OutputsManager.cpp > CMakeFiles/STM32.dir/src/OutputsManager.cpp.i

CMakeFiles/STM32.dir/src/OutputsManager.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/OutputsManager.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/OutputsManager.cpp -o CMakeFiles/STM32.dir/src/OutputsManager.cpp.s

CMakeFiles/STM32.dir/src/main.cpp.o: CMakeFiles/STM32.dir/flags.make
CMakeFiles/STM32.dir/src/main.cpp.o: ../src/main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_15) "Building CXX object CMakeFiles/STM32.dir/src/main.cpp.o"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/STM32.dir/src/main.cpp.o -c /home/rene/ksp/STM32/src/main.cpp

CMakeFiles/STM32.dir/src/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/STM32.dir/src/main.cpp.i"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/rene/ksp/STM32/src/main.cpp > CMakeFiles/STM32.dir/src/main.cpp.i

CMakeFiles/STM32.dir/src/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/STM32.dir/src/main.cpp.s"
	/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/rene/ksp/STM32/src/main.cpp -o CMakeFiles/STM32.dir/src/main.cpp.s

# Object files for target STM32
STM32_OBJECTS = \
"CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.o" \
"CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.o" \
"CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.o" \
"CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.o" \
"CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.o" \
"CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.o" \
"CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.o" \
"CMakeFiles/STM32.dir/src/Comm.cpp.o" \
"CMakeFiles/STM32.dir/src/InputsManager.cpp.o" \
"CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.o" \
"CMakeFiles/STM32.dir/src/MAX/LED.cpp.o" \
"CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.o" \
"CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.o" \
"CMakeFiles/STM32.dir/src/OutputsManager.cpp.o" \
"CMakeFiles/STM32.dir/src/main.cpp.o"

# External object files for target STM32
STM32_EXTERNAL_OBJECTS =

STM32: CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_GFX.cpp.o
STM32: CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/Adafruit_SPITFT.cpp.o
STM32: CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/fontconvert/fontconvert.c.o
STM32: CMakeFiles/STM32.dir/lib/Adafruit-GFX-Library-master/glcdfont.c.o
STM32: CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7735.cpp.o
STM32: CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST7789.cpp.o
STM32: CMakeFiles/STM32.dir/lib/Adafruit-ST7735-Library-master/Adafruit_ST77xx.cpp.o
STM32: CMakeFiles/STM32.dir/src/Comm.cpp.o
STM32: CMakeFiles/STM32.dir/src/InputsManager.cpp.o
STM32: CMakeFiles/STM32.dir/src/MAX/Bargraph.cpp.o
STM32: CMakeFiles/STM32.dir/src/MAX/LED.cpp.o
STM32: CMakeFiles/STM32.dir/src/MAX/MAX7221.cpp.o
STM32: CMakeFiles/STM32.dir/src/MAX/SevenSeg.cpp.o
STM32: CMakeFiles/STM32.dir/src/OutputsManager.cpp.o
STM32: CMakeFiles/STM32.dir/src/main.cpp.o
STM32: CMakeFiles/STM32.dir/build.make
STM32: CMakeFiles/STM32.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/rene/ksp/STM32/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_16) "Linking CXX executable STM32"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/STM32.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/STM32.dir/build: STM32

.PHONY : CMakeFiles/STM32.dir/build

CMakeFiles/STM32.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/STM32.dir/cmake_clean.cmake
.PHONY : CMakeFiles/STM32.dir/clean

CMakeFiles/STM32.dir/depend:
	cd /home/rene/ksp/STM32/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/rene/ksp/STM32 /home/rene/ksp/STM32 /home/rene/ksp/STM32/cmake-build-debug /home/rene/ksp/STM32/cmake-build-debug /home/rene/ksp/STM32/cmake-build-debug/CMakeFiles/STM32.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/STM32.dir/depend

