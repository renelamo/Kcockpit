#include "Arduino.h"
//*
#include "Comm.h"
#include "OutputsManager.h"
#include "SerialManager.h"
#include "SPI.h"
// */

OutputsManager* omgr;
InputsManager* imgr;
SerialManager* smgr;

void setup(){
    smgr = new SerialManager();
    omgr = new OutputsManager(smgr);
    imgr = new InputsManager();
    digitalWrite(LED_GREEN, HIGH);
    //Comm::handshake();
    //delay(500);
    //digitalWrite(LED_GREEN, LOW);
}

void loop(){
    Comm::capt(omgr, imgr);
    digitalToggle(LED_GREEN);
}


