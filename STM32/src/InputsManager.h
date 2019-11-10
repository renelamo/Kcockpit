//
// Created by rene on 18/10/2019.
//

#ifndef STM32_INPUTSMANAGER_H
#define STM32_INPUTSMANAGER_H

#include "Arduino.h"
#include "PinMap.h"


class InputsManager {
private:
    static void initPins();
public:
    InputsManager();

    static void sendThrottle();
    static void sendPitch();
    static void sendYaw();
    static void sendRoll();
    static void sendX();
    static void sendY();
    static void sendZ();
    static void sendT();
    static void sendActionGroup();
    static void sendStage();
    static void sendSAS();
};


#endif //STM32_INPUTSMANAGER_H
