//
// Created by rene on 14/06/19.
//
#include "OutputsManager.h"

#include "Arduino.h"
#include "utils.h"
#include "commTable.h"
#include "SerialManager.h"

void initPins(){
    pinMode(LED_GREEN, OUTPUT);
    pinMode(USER_BTN, INPUT);

    pinMode(STAGE_BUTTON, INPUT);
    pinMode(SAS_PIN, INPUT);
    pinMode(RCS_PIN, INPUT);
    pinMode(LIGHTS_PIN, INPUT);
    pinMode(GEARS_PIN, INPUT);
    pinMode(BRAKES_PIN, INPUT);
    pinMode(CUSTOM_1, INPUT);
    pinMode(CUSTOM_2, INPUT);
    pinMode(CUSTOM_3, INPUT);
    pinMode(CUSTOM_4, INPUT);
    pinMode(CUSTOM_5, INPUT);

    /*
    attachInterrupt(digitalPinToInterrupt(STAGE_BUTTON), stageHandler, FALLING);
    attachInterrupt(digitalPinToInterrupt(SAS_PIN), SASchangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(RCS_PIN), SASchangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(LIGHTS_PIN), SASchangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(GEARS_PIN), SASchangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(BRAKES_PIN), SASchangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(CUSTOM_1), customChangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(CUSTOM_2), customChangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(CUSTOM_3), customChangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(CUSTOM_4), customChangedHandler, CHANGE);
    attachInterrupt(digitalPinToInterrupt(CUSTOM_5), customChangedHandler, CHANGE);
    //*/
}


OutputsManager::OutputsManager(SerialManager* smgr) {
    initPins();
    serialManager=smgr;
}


void OutputsManager::setSASLEDs(int data) {
    bool sas= data%2;
    data/=2;
    bool rcs= data%2;
    data/=2;
    bool lights= data%2;
    data/=2;
    bool gears= data%2;
    data/=2;
    bool brakes= data%2;
}