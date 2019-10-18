//
// Created by rene on 16/12/18.
//

#include "Bargraph.h"

Bargraph::Bargraph(MAX7221 *newHost, DigitsRegister newAdress[2]) {
    host=newHost;
    adress = newAdress;
}

void Bargraph::display(uint8_t nb) {
    level = nb;
    host->setDecodeMode(NO_DECODE);
    if(nb>8){
        host->setRegister(adress[1], toStack(nb - 8));
        nb=8;
    } else{
        host->setRegister(adress[1], 0);
    }
    host->setRegister(adress[0], toStack(nb));
    host->flush();//TODO: ne flush qu'une fois quand les 4 bargraphs ont été actualisés en mémoire
}

//convertit 4 en 0b 0000 1111
// et 6 en 0b 0011 1111
uint8_t Bargraph::toStack(int data) {
    uint8_t out=0;
    for (int i=1; i<=data; ++i){
        out|=toOne(i);
    }
    return out;
}


//convertit 4 en 0b 0000 1000
// et 6 en 0b 0010 0000
uint8_t Bargraph::toOne(int data) {
    if(data == 0){
        return 0b0;
    }
    if(data == 8){
        return 0b10000000;
    }
    return pow(2,7-data);
}

void Bargraph::displayOne(int nb) {
    host->setDecodeMode(NO_DECODE);
    if(nb>8){
        host->setRegister(adress[1], toOne(nb-8));
        host->setRegister(adress[0], 0);
    } else{
        host->setRegister(adress[1], 0);
        host->setRegister(adress[0], toOne(nb));
    }
    host->flush();//TODO: ne flush qu'une fois quand les 4 bargraphs ont été actualisés en mémoire
}

void Bargraph::update() {
    if(level%2) {
        if (state) {
            display(level/2);
        } else {
            display(level/2 - 1);
        }
    } else{
        display(level/2);
    }
    state = ! state;
}

