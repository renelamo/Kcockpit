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
    static volatile bool stage;

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
    /**
     * @deprecated
     */
    static void sendStage();
    void sendSAS();
};

#endif //STM32_INPUTSMANAGER_H
