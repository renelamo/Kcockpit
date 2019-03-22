#include <Arduino.h>
/*
#include "MAX/SevenSeg.h"
#include "MAX/Bargraph.h"
#include "MAX/LED.h"
#include "utils.h"
 */
//TODO: refaire un utils.h

void interruptHandler(){
    Serial.write(0x01);
    Serial.flush();
}

void setup(){
    Serial.begin(115200);
    pinMode(LED_GREEN, OUTPUT);
    pinMode(USER_BTN, INPUT);
    attachInterrupt(digitalPinToInterrupt(USER_BTN),interruptHandler, FALLING);
}

void loop(){

}