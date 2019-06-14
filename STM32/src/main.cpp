#include "Arduino.h"
//*
#include "MAX/SevenSeg.h"
#include "MAX/Bargraph.h"
#include "MAX/LED.h"
#include "utils.h"
#include "commTable.h"
#include "Comm.h"
#include "OutputsManager.h"
// */
//TODO: refaire un utils.h

OutputsManager* omgr;

void setup(){
    Serial.begin(115200);
    omgr = new OutputsManager();
    digitalWrite(LED_GREEN, HIGH);
    Comm::handshake();
    delay(500);
    digitalWrite(LED_GREEN, LOW);
}

void loop(){
    Comm::capt(omgr);
    Comm::sendThrottle();
    Comm::sendPitch();
    Comm::sendYaw();
    Comm::sendRoll();
    delay(10);
    Comm::handshake();
    digitalToggle(LED_GREEN);
}


