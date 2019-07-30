#include "Arduino.h"
//*
#include "Comm.h"
#include "OutputsManager.h"
#include "SerialManager.h"
#include "SPI.h"
// */
//TODO: refaire un utils.h

OutputsManager* omgr;
SerialManager* smgr;
MAX7221* tester;

void setup(){
    SPI.setMISO(MISO_PIN);
    SPI.setMOSI(MOSI_PIN);
    SPI.setSCLK(CLOCK_PIN);
    SPI.begin();
    tester = new MAX7221(AP_PIN);
    smgr = new SerialManager();
    omgr = new OutputsManager(smgr);
    digitalWrite(LED_GREEN, HIGH);
    //Comm::handshake();
    delay(500);
    digitalWrite(LED_GREEN, LOW);
    tester->setDecodeMode(0b10110111);
    tester->test();
}

void loop(){
    /*
    Comm::capt(omgr);
    Comm::sendThrottle(SerialManager());
    Comm::sendPitch(SerialManager());
    Comm::sendYaw(SerialManager());
    Comm::sendRoll(SerialManager());
     */
    delay(100);
    //Comm::handshake();
    digitalToggle(LED_GREEN);
    tester->printDate(millis()/1000);
}


