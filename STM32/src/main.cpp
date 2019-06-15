#include "Arduino.h"
//*
#include "MAX/SevenSeg.h"
#include "MAX/Bargraph.h"
#include "MAX/LED.h"
#include "utils.h"
#include "commTable.h"
#include "Comm.h"
#include "OutputsManager.h"
#include "SerialManager.h"
// */
//TODO: refaire un utils.h

OutputsManager* omgr;
SerialManager* smgr;

void setup(){
    smgr = new SerialManager();
    omgr = new OutputsManager();
    digitalWrite(LED_GREEN, HIGH);
    Comm::handshake();
    delay(500);
    digitalWrite(LED_GREEN, LOW);
}

void loop(){
    Comm::capt(omgr);
    Comm::sendThrottle(SerialManager());
    Comm::sendPitch(SerialManager());
    Comm::sendYaw(SerialManager());
    Comm::sendRoll(SerialManager());
    delay(10);
    Comm::handshake();
    digitalToggle(LED_GREEN);
}


