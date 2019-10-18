#include "Arduino.h"
//*
#include "Comm.h"
#include "OutputsManager.h"
#include "SerialManager.h"
#include "SPI.h"
// */

OutputsManager* omgr;
SerialManager* smgr;

void setup(){
    smgr = new SerialManager();
    omgr = new OutputsManager(smgr);
    while(1) {
        for (int i = 0; i <= 10; ++i) {
            omgr->fuelGraph->displayOne(i);
            Serial.println(i);
            delay(1000);
        }
    }
    digitalWrite(LED_GREEN, HIGH);
    Comm::handshake();
    delay(500);
    digitalWrite(LED_GREEN, LOW);
}

void loop(){
    Comm::capt(omgr);
    delay(5);
    digitalToggle(LED_GREEN);
    smgr->send();
    delay(5);
}


