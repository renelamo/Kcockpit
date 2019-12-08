//
// Created by rene on 23/12/18.
//

#include "LED.h"

LED::LED(MAX7221 *newHost, address newAdr) {
    host=newHost;
    adr=newAdr;
    host->decode(toByte(adr.registre));
}

void LED::off() {
    host->setBitOff(adr.registre,adr.bit);
}

void LED::on() {
    host->setBitOn(adr.registre, adr.bit);
}

byte LED::toByte(int data) {
    int out=(int)pow(2,data);
    return (byte)out;
}