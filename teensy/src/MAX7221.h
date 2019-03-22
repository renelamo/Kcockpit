//
// Created by rene on 14/12/18.
//

#ifndef MAX7221_H
#define MAX7221_H

#include <SPI.h>
#include <cstdlib>
#include <cstdio>
#include "Arduino.h"

enum Register{
    NO_OP=0b0000,
    DECODE=0b1001,
    INTENSITY=0b1010,
    SCAN_LIMIT=0b1011,
    SHUTDOWN =0b1100,
    TEST=0b1111
};

enum DigitsRegister {
    D0=0b0001,
    D1=0b0010,
    D2=0b0011,
    D3=0b0100,
    D4=0b0101,
    D5=0b0110,
    D6=0b0111,
    D7=0b1000
};

const DigitsRegister allRegisters[8]={D0,D1,D2,D3,D4,D5,D6,D7};

typedef enum DecodeMode{
    FULL_DECODE=0b11111111,
    NO_DECODE=0b00000000
};


class MAX7221 {
protected:
    uint8_t selectPin;
    byte state[8];


    void write(Register r, byte data);
    byte toByte(int data);

public:
    MAX7221(uint8_t pinSelect);
    void sleep();
    void wake();
    void test();
    void nbDigitsDisplayed(int nbdigit);
    void decode(byte mode);
    void intensity(int newIntensity);
    bool display(unsigned int digit);
    void display();
    void setState(unsigned int digit, byte data);
    byte getState(unsigned int digit);
    bool setBitOn(unsigned int digit, unsigned int bit);
    bool setBitOff(unsigned int digit, unsigned int bit);

};


#endif //MAX7221_H
