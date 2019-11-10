//
// Created by rene on 14/12/18.
//

#ifndef STM32_PINMAP_H
#define STM32_PINMAP_H

#include "MAX/MAX7221.h"

/////////Analogiques//////////////
enum analogInputs {
    PITCH_PIN = PA0,
    YAW_PIN = PA1,
    ROLL_PIN = PA4,
    X_PIN = PB0,
    Y_PIN = PC1,
    Z_PIN = PC2,
    T_PIN = PC3,
    THROTTLE_PIN = PC0
};

////////SPI1///////////////////////
enum SPIPins{
    CLOCK_PIN = PB3,
    MOSI_PIN = PB5,
    MISO_PIN = PB4,
    BARGRAPH_PIN = PB13,
    ALT_PIN = PB14,
    PE_PIN = PB15,
    AP_PIN = PB1
};


///////DigitalIn/////////////////
enum digitalInputs {
    STAGE_BUTTON = PC13,
    JOYSTICK_BUTTON = PH1,
    RB_PIN = PH0,
    LB_PIN = PC15,
    AP_PE_SWITCH = PB2,
};


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

//Todo: assigner un pin au buzzer
#define BUZZ_PIN -1


#endif
