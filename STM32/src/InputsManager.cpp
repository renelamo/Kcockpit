//
// Created by rene on 18/10/2019.
//

#include "InputsManager.h"
#include "offsets.h"


int lissage(int data, int centre){
    int seuil=15;
    data*=511/centre;
    if(data<=seuil){
        data=0;
    }
    else if(data<=511-seuil){
        double m=551.0/491.0;
        data=(int) (m* (double)(data-10));
    } else if(data<=551+seuil){
        data=511;
    }
    else if(data<=1023-seuil){
        double m=552.0/492.0;
        data=(int) (m* (double)(data-10 -2*seuil));
    }
    else{
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

    //analog inputs
    pinMode(THROTTLE_PIN, INPUT);
    pinMode(PITCH_PIN, INPUT);
    pinMode(YAW_PIN, INPUT);
    pinMode(ROLL_PIN, INPUT);
    pinMode(X_PIN, INPUT);
    pinMode(Y_PIN, INPUT);
    pinMode(Z_PIN, INPUT);
    pinMode(T_PIN, INPUT);
}

InputsManager::InputsManager() {
    initPins();
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

}

void InputsManager::sendStage() {
    Serial.write(digitalRead(STAGE_BUTTON));
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
