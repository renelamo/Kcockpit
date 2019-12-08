//
// Created by rene on 17/12/18.
//

#ifndef TEENSY_SEVENSEG_H
#define TEENSY_SEVENSEG_H


#include "MAX7221.h"
#include "Arduino.h"

enum letter{
    MINUS=0b1010,
    E=0b1011,
    H=0b1100,
    L=0b1101,
    P=0b1110,
    BLANK=0b1111,
    DOT=0b10000000,
    m=0b0,
    h=0b0,
    y=0b0,
    s=0b0
};

class SevenSeg {
private:
    MAX7221* host;
    String toString(float data);
public:
    SevenSeg(MAX7221* newHost);
    void display(float data);
    void wait();
};


#endif //TEENSY_SEVENSEG_H
