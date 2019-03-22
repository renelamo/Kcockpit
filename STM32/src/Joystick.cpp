//
// Created by rene on 07/03/19.
//

#include "Joystick.h"

byte Joystick::centreReduit(byte data) {
    uint8_t centre=128;
    uint8_t reduit=10;
    data-=centre;
    data*=(centre-reduit)/centre;
    data+=centre;
    return data;
}

Joystick::Joystick() {
    preciseControls=false;
}