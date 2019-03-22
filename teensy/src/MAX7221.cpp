//
// Created by rene on 14/12/18.
//

#include "Bargraph.h"
#include "MAX7221.h"

MAX7221::MAX7221(uint8_t pinSelect) {
    selectPin=pinSelect;
    pinMode(selectPin, OUTPUT);
    for(int i=0; i<8;++i){
        state[i]=(byte)0;
    }
    wake();
}

void MAX7221::sleep() {
    write(SHUTDOWN, 0b0000);
}

void MAX7221::wake() {
    write(SHUTDOWN, 0b0001);
}

void MAX7221::test() {
    write(TEST, 0b0001);
    delay(1000);
    write(TEST, 0b0000);
}

void MAX7221::nbDigitsDisplayed(int nbdigit) {
    if(nbdigit>7 or nbdigit<1){return;}
    write(SCAN_LIMIT, (byte)nbdigit);
}

void MAX7221::decode(byte mode) {
    write(DECODE, mode);
}

bool MAX7221::display(unsigned int digit) {
    if(digit>7){return false;}

    byte registerAdress=allRegisters[digit];
    byte data=state[digit];
    digitalWrite(selectPin, LOW);
    SPI.transfer16((registerAdress<<8)|data);
    digitalWrite(selectPin, HIGH);
    return true;
}

void MAX7221::display() {
    for(unsigned int i=0; i<8; ++i){
        display(i);
    }
}

void MAX7221::intensity(int newIntensity) {
    if(newIntensity>16 or newIntensity<1){return;}
    write(INTENSITY, (byte)newIntensity);
}

void MAX7221::write(Register r, byte data) {
    digitalWrite(selectPin, LOW);
    SPI.transfer16((r<<8)|data);
    digitalWrite(selectPin, HIGH);
}

void MAX7221::setState(unsigned int digit, byte data) {
    if(digit>7){return;}
    state[digit]=data;
}

bool MAX7221::setBitOff(unsigned int digit, unsigned int bit) {
    if(digit>7){return false;}
    if(bit>7){return false;}
    byte tmp=state[digit];
    state[digit]=tmp|toByte(bit);
    return true;
}

bool MAX7221::setBitOn(unsigned int digit, unsigned int bit) {
    if(digit>7){return false;}
    if(bit>7){return false;}
    byte tmp=state[digit];
    state[digit]=tmp;
    return true;
}

byte MAX7221::toByte(int data) {
    int out=(int)pow(2,data);
    return (byte)out;
}

byte MAX7221::getState(unsigned int digit) {
    return state[digit];
}