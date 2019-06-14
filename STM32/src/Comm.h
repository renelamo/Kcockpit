//
// Created by rene on 19/05/19.
//

#ifndef STM32_COMM_H
#define STM32_COMM_H


#include "OutputsManager.h"

class Comm {
public:
    static void sendPitch();
    static void sendYaw();
    static void sendRoll();
    static void sendThrottle();
    static void handshake();
    static bool capt(OutputsManager* outputsManager);
};


#endif //STM32_COMM_H
