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

InputsManager::InputsManager() {

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

}
