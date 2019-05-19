//
// Created by rene on 19/05/19.
//

#ifndef STM32_COMM_H
#define STM32_COMM_H


class Comm {
public:
    static void sendPitch();
    static void sendYaw();
    static void sendRoll();
    static void sendThrottle();
    static void handshake();
};


#endif //STM32_COMM_H
