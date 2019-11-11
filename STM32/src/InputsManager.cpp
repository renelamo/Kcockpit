//
// Created by rene on 18/10/2019.
//

#include "InputsManager.h"
#include "offsets.h"


int lissage(int data, int centre){
    int seuil=15;
    data*=511/centre; //La multiplication est effectuée avant la division d'entiers
    if(data<=seuil){ //Premier palier
        data=0;
    }
    else if(data<=511-seuil){ //Première pente
        double m=511.0/(511.0-2*(double)seuil);
        data=(int) (m* (double)(data-seuil));
    }
    else if(data<=511+seuil){ //Deuxième palier
        data=511;
    }
    else if(data<=1023-seuil){ //Seconde pente
        double m=512.0/(512.0-2*(double)seuil);
        data=(int) (m* (double)(data-3*seuil));
    }
    else{ //Dernier palier
        data=1023;
    }
    return data;
}

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
    stage = false;
    attachInterrupt(digitalPinToInterrupt(STAGE_BUTTON), stageISR, FALLING);
}

void InputsManager::sendThrottle() {
    int val=analogRead(THROTTLE_PIN);
    Serial.write(val/4);
}

void InputsManager::sendPitch() {
    int val=analogRead(PITCH_PIN);
    val= lissage(val, PITCH_CENTER);
    Serial.write(val/4);
}

void InputsManager::sendYaw() {
    int val=analogRead(YAW_PIN);
    val= lissage(val, YAW_CENTER);
    Serial.write(val/4);
}

void InputsManager::sendRoll() {
    int val=analogRead(ROLL_PIN);
    val= lissage(val, ROLL_CENTER);
    Serial.write(val/4);
}

void InputsManager::sendX() {
    int val=analogRead(X_PIN);
    val= lissage(val, X_CENTER);
    Serial.write(val/4);
}

void InputsManager::sendY() {
    int val=analogRead(Y_PIN);
    val= lissage(val, Y_CENTER);
    Serial.write(val/4);
}

void InputsManager::sendZ() {
    int val=analogRead(Z_PIN);
    val= lissage(val, Z_CENTER);
    Serial.write(val/4);
}

void InputsManager::sendT() {
    int val=analogRead(T_PIN);
    val= lissage(val, T_CENTER);
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
    if(digitalRead(CUSTOM_4) ==LOW){
        out|=0b1000u;
    }
    if(digitalRead(CUSTOM_5)==LOW){
        out|=0b10000u;
    }
    Serial.write(out);
}

void InputsManager::sendStage() {
    Serial.write((int)stage);
    stage = false;
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
    Serial.write(out);
}
