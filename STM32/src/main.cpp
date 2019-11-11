#include "Arduino.h"
//*
#include "Comm.h"
#include "OutputsManager.h"
// */

OutputsManager* omgr;

void setup(){
    omgr = new OutputsManager();
    digitalWrite(LED_GREEN, HIGH);
    //Comm::handshake();
    //delay(500);
    //digitalWrite(LED_GREEN, LOW);
}

void loop(){
    Comm::capt(omgr);
    digitalToggle(LED_GREEN);
}


