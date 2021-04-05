//
// Created by rene on 18/10/2019.
//

#include "InputsManager.h"

volatile bool InputsManager::stage;

void InputsManager::initPins(){
    //digital inputs
    pinMode(STAGE_BUTTON, INPUT_PULLUP);

    pinMode(SAS_PIN, INPUT_PULLUP);
    pinMode(RCS_PIN, INPUT_PULLUP);
    pinMode(LIGHTS_PIN, INPUT_PULLUP);
    pinMode(GEARS_PIN, INPUT_PULLUP);
    pinMode(BRAKES_PIN, INPUT_PULLUP);

    pinMode(CUSTOM_1, INPUT_PULLUP);
    pinMode(CUSTOM_2, INPUT_PULLUP);
    pinMode(CUSTOM_3, INPUT_PULLUP);
    pinMode(CUSTOM_4, INPUT_PULLUP);
    pinMode(CUSTOM_5, INPUT_PULLUP);

    //analog inputs
    pinMode(THROTTLE_PIN, INPUT_ANALOG);
    pinMode(PITCH_PIN, INPUT_ANALOG);
    pinMode(YAW_PIN, INPUT_ANALOG);
    pinMode(ROLL_PIN, INPUT_ANALOG);
    pinMode(X_PIN, INPUT_ANALOG);
    pinMode(Y_PIN, INPUT_ANALOG);
    pinMode(Z_PIN, INPUT_ANALOG);
    pinMode(T_PIN, INPUT_ANALOG);

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

void stageISR(){
    InputsManager::stage = true;
}

InputsManager::InputsManager() {
    initPins();
    InputsManager::stage = false;
    attachInterrupt(digitalPinToInterrupt(STAGE_BUTTON), stageISR, FALLING);
}

void InputsManager::sendThrottle() {
    int val=analogRead(THROTTLE_PIN);
    Serial.write(val/4);
}

void InputsManager::sendPitch() {
    int val=analogRead(PITCH_PIN);
    Serial.write(val/4);
}

void InputsManager::sendYaw() {
    int val=analogRead(YAW_PIN);
    Serial.write(val/4);
}

void InputsManager::sendRoll() {
    int val=analogRead(ROLL_PIN);
    Serial.write(val/4);
}

void InputsManager::sendX() {
    int val=analogRead(X_PIN);
    Serial.write(val/4);
}

void InputsManager::sendY() {
    int val=analogRead(Y_PIN);
    Serial.write(val/4);
}

void InputsManager::sendZ() {
    int val=analogRead(Z_PIN);
    Serial.write(val/4);
}

void InputsManager::sendT() {
    int val=analogRead(T_PIN);
    Serial.write(val/4);
}

void InputsManager::sendActionGroup() {
    uint8_t out = 0;
    if(digitalRead(CUSTOM_1)==LOW){
        out|=0b0001u;
    }
    if(digitalRead(CUSTOM_2)==LOW){
        out|=0b0010u;
    }
    if(digitalRead(CUSTOM_3)==LOW){
        out|=0b0100u;
    }
    if(digitalRead(CUSTOM_4)==LOW){
        out|=0b1000u;
    }
    if(digitalRead(CUSTOM_5)==LOW){
        out|=0b10000u;
    }
    if(digitalRead(AP_PE_SWITCH)==LOW){
        out|=0b100000u;
    }
    Serial.write(out);
}

void InputsManager::sendStage() {
    Serial.write((int)InputsManager::stage);
    InputsManager::stage = false;
}

void InputsManager::sendSAS() {
    uint8_t out = 0;
    if(digitalRead(SAS_PIN)==LOW){
        out|=0b0001u;
    }
    if(digitalRead(RCS_PIN)==LOW){
        out|=0b0010u;
    }
    if(digitalRead(LIGHTS_PIN)==LOW){
        out|=0b0100u;
    }
    if(digitalRead(GEARS_PIN) ==LOW){
        out|=0b1000u;
    }
    if(digitalRead(BRAKES_PIN)==LOW){
        out|=0b10000u;
    }
    if(InputsManager::stage){
        InputsManager::stage= false;
        out|=0b100000u;
    }
    Serial.write(out);
}
