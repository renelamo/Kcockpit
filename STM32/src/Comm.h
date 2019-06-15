//
// Created by rene on 19/05/19.
//

#ifndef STM32_COMM_H
#define STM32_COMM_H


#include "OutputsManager.h"
#include "SerialManager.h"

class Comm {
public:
    static void sendPitch(SerialManager smgr);
    static void sendYaw(SerialManager smgr);
    static void sendRoll(SerialManager smgr);
    static void sendThrottle(SerialManager smgr);
    static void handshake();
    static bool capt(OutputsManager* outputsManager);
};


#endif //STM32_COMM_H
