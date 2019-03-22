//
// Created by rene on 07/03/19.
//

#ifndef STM32_JOYSTICK3_H
#define STM32_JOYSTICK3_H

#include <Arduino.h>
#include "Joystick.h"


class Joystick3: public Joystick {
protected:
public:
    Joystick3(byte pinX, byte pinY, byte pinZ, byte pinButton);
    void refresh();
};


#endif //STM32_JOYSTICK3_H
