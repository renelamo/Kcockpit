//
// Created by rene on 14/12/18.
//

#ifndef TEENSY_PINMAP_H
#define TEENSY_PINMAP_H

#include "MAX7221.h"


/////////Analogiques//////////////
#define PITCH_PIN A0
#define YAW_PIN A1
#define ROLL_PIN A2
#define X_PIN A3
#define Y_PIN A4
#define Z_PIN A5
#define T_PIN A6
#define THROTTLE_PIN A7

////////SPI///////////////////////
#define CLOCK_PIN 13
#define MOSI_PIN 11
#define MISO_PIN 12
#define BARGRAPH_PIN 24
#define ALT_PIN 25
#define PE_PIN 22
#define AP_PIN 23
#define LEDS_PIN 26

DigitsRegister adOxydizer[2] = {D0,D1};
DigitsRegister adFuel[2] = {D2,D3};
DigitsRegister adElec[2] = {D4,D5};
DigitsRegister adSup[2] = {D6, D7};


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
