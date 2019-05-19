#include "Arduino.h"
//*
#include "MAX/SevenSeg.h"
#include "MAX/Bargraph.h"
#include "MAX/LED.h"
#include "utils.h"
#include "commTable.h"
#include "Comm.h"
// */
//TODO: refaire un utils.h

void stageHandler(){
    Serial.write(STAGE_CODE);
    Serial.flush();
}

void SASchangedHandler(){
    byte out=0;
    if(digitalRead(SAS_PIN)==LOW){
        out+=0b00000001;
    }
    if(digitalRead(RCS_PIN)==LOW){
        out+=0b00000010;
    }
    if(digitalRead(LIGHTS_PIN)==LOW){
        out+=0b00000100;
    }
    if(digitalRead(GEARS_PIN)==LOW){
        out+=0b00001000;
    }
    if(digitalRead(BRAKES_PIN)==LOW){
        out+=0b00010000;
    }
    Serial.write(SAS_CODE);
    Serial.write(out);
    Serial.flush();
}

void customChangedHandler(){
    byte out=0;
    if(digitalRead(CUSTOM_1)==LOW){
        out+=0b00000001;
    }
    if(digitalRead(CUSTOM_2)==LOW){
        out+=0b00000010;
    }
    if(digitalRead(CUSTOM_3)==LOW){
        out+=0b00000100;
    }
    if(digitalRead(CUSTOM_4)==LOW){
        out+=0b00001000;
    }
    if(digitalRead(CUSTOM_5)==LOW){
        out+=0b00010000;
    }
    Serial.write(ACTION_GROUP_CODE);
    Serial.write(out);
    Serial.flush();
}

void initPins(){
    pinMode(LED_GREEN, OUTPUT);
    pinMode(USER_BTN, INPUT);
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
}

void setup(){
    Serial.begin(115200);
    initPins();
    digitalWrite(LED_GREEN, HIGH);
    Comm::handshake();
    delay(500);
    digitalWrite(LED_GREEN, LOW);
}

void loop(){
    Comm::sendThrottle();
    Comm::sendPitch();
    Comm::sendYaw();
    Comm::sendRoll();
    delay(10);
    Comm::handshake();
    digitalToggle(LED_GREEN);
}


