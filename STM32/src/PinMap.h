//
// Created by rene on 14/12/18.
//

#ifndef TEENSY_PINMAP_H
#define TEENSY_PINMAP_H

#include "MAX/MAX7221.h"

//TODO: adapter a la STM32

/////////Analogiques//////////////
#define PITCH_PIN PA_0
#define YAW_PIN PA_1
#define ROLL_PIN PA_4
#define X_PIN PB_0
#define Y_PIN PC_1
#define Z_PIN PC_2
#define T_PIN PC_3
#define THROTTLE_PIN PC_0

////////SPI1///////////////////////
#define CLOCK_PIN PB_3
#define MOSI_PIN PB_5
#define MISO_PIN PB_4
#define BARGRAPH_PIN 24
#define ALT_PIN 25
#define PE_PIN 22
#define AP_PIN 23

DigitsRegister adOxydizer[2] = {d0,d1};
DigitsRegister adFuel[2] = {d2,d3};
DigitsRegister adElec[2] = {d4,d5};


///////DigitalIn/////////////////
#define JOYSTICK_BUTTON 0
#define RB_PIN 1
#define LB_PIN 2
#define SAS_PIN 3
#define RCS_PIN 4
#define LIGHTS_PIN 5
#define GEARS_PIN 6
#define BRAKES_PIN 7
#define STAGE_BUTTON 8
#define AP_PE_SWITCH 9

#endif //TEENSY_PINMAP_H
