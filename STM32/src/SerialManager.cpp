//
// Created by rene on 14/06/19.
//

#include "SerialManager.h"
#include "Arduino.h"
#include "utils.h"
#include "SerialManager.h"
#include "commTable.h"
#include "PinMap.h"
#include <queue>

SerialManager::SerialManager() {
    Serial.begin(115200);
}

void SerialManager::add(std::vector<int> data) {
    buffer.push(data);
}

void SerialManager::send() {
    if(buffer.empty()){
        Serial.write(NO_OP_CODE);
        Serial.flush();
        return;
    }
    std::vector<int> toSend=buffer.front();
    buffer.pop();
    for(int i:toSend){
        Serial.write(i);
    }
    Serial.flush();
}

void SerialManager::stageHandler(){
    std::vector<int> out={STAGE_CODE};
    add(out);
}

void SerialManager::SASchangedHandler(){
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
    std::vector<int> buf={SAS_CODE, out};
    add(buf);
    /*
    Serial.write(SAS_CODE);
    Serial.write(out);
    Serial.flush();
     //*/
}

void SerialManager::customChangedHandler(){
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

    std::vector<int> buf={ACTION_GROUP_CODE, out};
    add(buf);
    /*
    Serial.write(ACTION_GROUP_CODE);
    Serial.write(out);
    Serial.flush();
     //*/
}