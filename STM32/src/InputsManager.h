//
// Created by rene on 18/10/2019.
//

#ifndef STM32_INPUTSMANAGER_H
#define STM32_INPUTSMANAGER_H

#include "Arduino.h"
#include "PinMap.h"


class InputsManager {
private:

public:
    InputsManager();

    void sendThrottle();
    void sendPitch();
    void sendYaw();
    void sendRoll();
    void sendX();
    void sendY();
    void sendZ();
    void sendT();
    void sendActionGroup();
    void sendStage();
    void sendSAS();
};


#endif //STM32_INPUTSMANAGER_H
