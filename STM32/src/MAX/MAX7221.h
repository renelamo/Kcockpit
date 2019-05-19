//
// Created by rene on 14/12/18.
//

#ifndef MAX7221_H
#define MAX7221_H

#include <SPI.h>
#include <cstdlib>
#include <cstdio>
#include <Arduino.h>

enum Register{
    NO_OP=0b0000, //ne fait rien, sert au branchement en cascade
    DECODE=0b1001,
    INTENSITY=0b1010,
    SCAN_LIMIT=0b1011,
    SHUTDOWN =0b1100,
    TEST=0b1111
};


enum DigitsRegister {
    d0=0b0001,
    d1=0b0010,
    d2=0b0011,
    d3=0b0100,
    d4=0b0101,
    d5=0b0110,
    d6=0b0111,
    d7=0b1000
};

const DigitsRegister allRegisters[8]={d0,d1,d2,d3,d4,d5,d6,d7};

typedef enum :byte{
    FULL_DECODE=0b11111111,
    NO_DECODE=0b00000000
}DecodeMode;


class MAX7221 {
protected:
    uint8_t selectPin;
    byte state[8];

    void write(Register r, byte data);

public:
    explicit MAX7221(uint8_t pinSelect);
    void sleep();
    void wake();
    void test();
    void nbDigitsDisplayed(int nbdigit);
    void setDecodeMode(byte mode);
    //TODO: je sais pas si c'est utile mais un decodeMode par digit?
    DecodeMode getDecodeMode();
    void setIntensity(int newIntensity);
    void setRegister(DigitsRegister digit, byte data);
    byte getRegister(DigitsRegister digit);
    void flushDigit(DigitsRegister digit);
    void flush();
};


#endif //MAX7221_H
