//
// Created by rene on 16/12/18.
//

#include "Bargraph.h"

Bargraph::Bargraph(MAX7221 *newHost, DigitsRegister newAdress[2]) {
    host=newHost;
    adress = newAdress;
}

void Bargraph::display(int nb) {
    host->setDecodeMode(NO_DECODE);
    if(nb>8){
        host->setRegister(adress[1], toByte(nb-8));
        nb=8;
    }
    host->setRegister(adress[0], toByte(nb));
}

//convertit 4 en 0b 0000 1111
// et 6 en 0b 0011 1111
byte Bargraph::toByte(int data) {
    int out=0;
    int add=1;
    for (int i=0; i<data; ++i){
        out+=add;
        add*=2;
    }
    return (byte)out;
}