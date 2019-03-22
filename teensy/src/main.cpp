#include <Arduino.h>
#include <SPI.h>
#include "PinMap.h"
#include "MAX7221.h"
#include "Bargraph.h"
#include "utils.h"
#include <cmath>


void setup() {
    Serial.begin(9600);
    pinMode(13, OUTPUT);
    SPI.beginTransaction(SPISettings(10000000, MSBFIRST, SPI_MODE0));
    Serial.println("start");
}

void loop(){
    bargraphs->test();
    delay(100);
    for(int i=0; i<10;++i){
        oxydizer->display(i);
        elec->display(i);
        fuel->display(i);
        delay(100);
    }
}