# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.13

# compile CXX with /home/rene/.platformio/packages/toolchain-gccarmnoneeabi/bin/arm-none-eabi-g++
CXX_FLAGS = -g   -D'PLATFORMIO=30604' -D'__MK20DX256__' -D'TEENSY31' -D'USB_SERIAL' -D'ARDUINO=10805' -D'TEENSYDUINO=145' -D'F_CPU=72000000L' -D'LAYOUT_US_ENGLISH' -std=gnu++11

CXX_DEFINES = 

CXX_INCLUDES = -I/home/rene/.platformio/packages/framework-arduinoteensy/cores/teensy3 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SPI -I/home/rene/ksp/teensy/lib/kerbalsimpit -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ADC -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/AccelStepper/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_CC3000 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_CC3000/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_GFX -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_ILI9340 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_ILI9341 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_NeoPixel -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_RA8875 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_SSD1306 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_ST7735 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_STMPE610 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_VS1053 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_nRF8001 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Adafruit_nRF8001/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/AltSoftSerial -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Artnet -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Audio -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Audio/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Bounce -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Bounce2 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/CapacitiveSensor -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/CryptoAccel/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/DS1307RTC -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/DmxSimple -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/DogLcd -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/EEPROM -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/EasyTransfer -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/EasyTransferI2C -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Encoder -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Encoder/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Entropy -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Ethernet/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FastCRC -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FastLED -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FlexCAN -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FlexiTimer2 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FreqCount -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FreqMeasure -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FreqMeasureMulti -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/FrequencyTimer2 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ILI9341_t3 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/IRremote -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Keypad/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/LedControl/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/LedDisplay -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/LiquidCrystal/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/LiquidCrystalFast -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/LowPower -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/MFRC522/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/MIDI/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Metro -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/MsTimer2 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/NXPMotionSense -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/NXPMotionSense/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/OSC -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/OctoWS2811 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/OneWire -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/PS2Keyboard -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/PS2Keyboard/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/PWMServo -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Ping -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/PulsePosition -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/RA8875 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/RadioHead -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ResponsiveAnalogRead/src -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SD -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SD/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SPIFlash -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ST7565 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ST7735_t3 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SerialFlash -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Servo -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ShiftPWM -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Snooze -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Snooze/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SoftPWM -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/SoftwareSerial -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TFT_ILI9163C -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Talkie -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TeensyThreads -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Time -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TimeAlarms -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TimerOne -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TimerThree -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TinyGPS -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Tlc5940 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/TouchScreen -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/USBHost_t36 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/UTFT -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/VirtualWire -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/WS2812Serial -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Wire -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/Wire/utility -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/XBee -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/XPT2046_Touchscreen -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/i2c_t3 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ks0108 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/openGLCD -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/ssd1351 -I/home/rene/.platformio/packages/framework-arduinoteensy/libraries/x10 -I/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/arm-none-eabi/include -I/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/arm-none-eabi/include/c++/5.4.1 -I/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/arm-none-eabi/include/c++/5.4.1/arm-none-eabi -I/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/lib/gcc/arm-none-eabi/5.4.1/include-fixed -I/home/rene/.platformio/packages/toolchain-gccarmnoneeabi/lib/gcc/arm-none-eabi/5.4.1/include -I/home/rene/.platformio/packages/tool-unity -I/home/rene/ksp/teensy/include -I/home/rene/ksp/teensy/src 

