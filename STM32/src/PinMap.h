//
// Created by rene on 14/12/18.
//

#ifndef STM32_PINMAP_H
#define STM32_PINMAP_H

#include "MAX/MAX7221.h"

/////////Analogiques//////////////
#define PITCH_PIN PA0
#define YAW_PIN PA1
#define ROLL_PIN PA4
#define X_PIN PB0
#define Y_PIN PC1
#define Z_PIN PC2
#define T_PIN PC3
#define THROTTLE_PIN PC0

////////SPI1///////////////////////
#define CLOCK_PIN PB3
#define MOSI_PIN PB5
#define MISO_PIN PB4
#define BARGRAPH_PIN PB13
#define ALT_PIN PB14
#define PE_PIN PB15
#define AP_PIN PB1

DigitsRegister adOxydizer[2] = {d0,d1};
DigitsRegister adFuel[2] = {d2,d3};
DigitsRegister adElec[2] = {d4,d5};
DigitsRegister adProp[2] = {d6, d7};


///////DigitalIn/////////////////
#define JOYSTICK_BUTTON PH1
#define RB_PIN PH0
#define LB_PIN PC15
#define STAGE_BUTTON PC13
#define AP_PE_SWITCH PB2

enum ActionGroupsPins{
    CUSTOM_1 = PB6,
    CUSTOM_2 = PC7,
    CUSTOM_3 = PA9,
    CUSTOM_4 = PA8,
    CUSTOM_5 = PB10,
    SAS_PIN = PC9,
    RCS_PIN = PB8,
    LIGHTS_PIN = PC8,
    GEARS_PIN = PC6,
    BRAKES_PIN = PC5
};


#endif
