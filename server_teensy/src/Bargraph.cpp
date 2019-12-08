//
// Created by rene on 16/12/18.
//

#include "Bargraph.h"

Bargraph::Bargraph(MAX7221 *newHost, DigitsRegister *newAdress) {
    host=newHost;
    adress=newAdress;
    host->decode(NO_DECODE);
}

void Bargraph::display(int nb) {
    if(nb>8){
        byte mask=0b11111100;
        byte current=host->getState(adress[1]);
        current^=mask;//TODO: AND bit à bit
        current|=toByte(nb-8);
        host->setState(adress[1], current);
        nb=8;
    }
    host->setState(adress[0], toByte(nb));
}


byte Bargraph::toByte(int data) {
    int out=0;
    int add=1;
    for (int i=0; i<data; ++i){
        out+=add;
        add*=2;
    }
    return (byte)out;
}
