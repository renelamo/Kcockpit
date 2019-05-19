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
    byte toByte(int data);

public:
    Bargraph(MAX7221 *newHost, DigitsRegister newAdress[2]);
    void display(int nb);

};


#endif //TEENSY_BARGRAPH_H
