//
// Created by rene on 16/12/18.
//

#ifndef TEENSY_BARGRAPH_H
#define TEENSY_BARGRAPH_H


#include "MAX7221.h"


class Bargraph {
private:
    MAX7221* host;
    DigitsRegister* adress;
    uint8_t toStack(int data);
    uint8_t toOne(int data);

public:
    Bargraph(MAX7221 *newHost, DigitsRegister newAdress[2]);
    void display(int nb);
    void displayOne(int nb);
};


#endif //TEENSY_BARGRAPH_H
