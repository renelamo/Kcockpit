//
// Created by rene on 16/12/18.
//

#ifndef TEENSY_BARGRAPH_H
#define TEENSY_BARGRAPH_H


#include <MAX7221.h>


class Bargraph {
private:
    uint8_t level;
    bool state;
    MAX7221* host;
    DigitsRegister* adress;
    static uint8_t toStack(int data);
    static uint8_t toOne(int data);

public:
    Bargraph(MAX7221 *newHost, DigitsRegister newAdress[2]);
    void update();
    void display(uint8_t nb);
    void displayOne(int nb);
};


#endif //TEENSY_BARGRAPH_H
