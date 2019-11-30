#include "Arduino.h"
//*
#include "Comm.h"
#include "OutputsManager.h"
// */

OutputsManager* omgr;
unsigned int lastTimeChanged;

void setup(){
    omgr = new OutputsManager();
    digitalWrite(LED_GREEN, HIGH);
    lastTimeChanged = millis();
}

void loop(){
    Comm::capt(omgr);
    if(millis()>lastTimeChanged + 500) {
        digitalToggle(LED_GREEN);
        lastTimeChanged = millis();
    }
}


