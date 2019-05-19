//
// Created by rene on 23/12/18.
//

#include "LED.h"

LED::LED(MAX7221 *newHost, address newAdr) {
    host=newHost;
    adr=newAdr;
    host->setDecodeMode(host->getDecodeMode() - toByte(adr.registre));
}

void LED::turnOff() {
    host->setRegister(adr.registre, host->getRegister(adr.registre) & ~toByte(adr.bit));
}

void LED::turnOn() {
    host->setRegister(adr.registre, host->getRegister(adr.registre) | toByte(adr.bit));
}

//convertit 5 en 0b 0001 0000
//et 3 en 0b 0000 0100
byte LED::toByte(int data) {
    if(data==0)
        return 0b00000000;
    int out=(int)pow(2,data-1);
    return (byte)out;
}