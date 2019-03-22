//
// Created by rene on 07/03/19.
//

#ifndef STM32_JOYSTICK_H
#define STM32_JOYSTICK_H

#include <Arduino.h>

class Joystick {
protected:
    uint8_t buttonPin;
    byte* analogPins;
    byte* state;
    bool preciseControls;
    Joystick();
    byte centreReduit(byte data);

public:
    virtual void refresh();

};


#endif //STM32_JOYSTICK_H
