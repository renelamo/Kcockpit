#include "Arduino.h"
//*
#include "MAX/SevenSeg.h"
#include "MAX/Bargraph.h"
#include "MAX/LED.h"
#include "utils.h"
#include "commTable.h"
// */
//TODO: refaire un utils.h

void interruptHandler(){
    Serial.write(STAGE_CODE);
    Serial.flush();
}

void initPins(){
    pinMode(LED_GREEN, OUTPUT);
    pinMode(USER_BTN, INPUT);
}

void sendThrottle(){
    int val=analogRead(THROTTLE_PIN);
    Serial.write(THROTTLE_CODE);
    Serial.write(val);//entre 0 et 1023
    Serial.flush();
}

void handshake(){
    Serial.write(HANDSHAKE_CODE);
    Serial.flush();
    do{
        while (!Serial.available())
    }while (Serial.read()!=HANDSHAKE_CODE);
}
void setup(){
    Serial.begin(115200);
    initPins();
    attachInterrupt(digitalPinToInterrupt(USER_BTN),interruptHandler, FALLING);
    handshake();
}

void loop(){
    sendThrottle();
    delay(100);
    handshake();
}

