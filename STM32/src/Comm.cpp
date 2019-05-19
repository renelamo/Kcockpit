//
// Created by rene on 19/05/19.
//

#include "Comm.h"
#include "PinMap.h"
#include "commTable.h"

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

void Comm::sendThrottle(){
    int val=analogRead(THROTTLE_PIN);
    Serial.write(THROTTLE_CODE);//entre 0 et 1023 (10 bits)
    Serial.write(val/4);//entre 0 et 255 (8 bits)
    Serial.flush();
}
void Comm::sendPitch(){
    int val=analogRead(PITCH_PIN);
    val= lissage(val, 507);
    Serial.write(PITCH_CODE);
    Serial.write(val/4);
    Serial.flush();
}
void Comm::sendYaw(){
    int val=analogRead(YAW_PIN);
    val= lissage(val, 511);
    Serial.write(YAW_CODE);
    Serial.write(val/4);
    Serial.flush();
}
void Comm::sendRoll(){
    int val=analogRead(ROLL_PIN);
    val= lissage(val, 545);
    Serial.write(ROLL_CODE);
    Serial.write(val/4);
    Serial.flush();
}

void Comm::handshake(){
    do{
        Serial.write(HANDSHAKE_CODE);
        Serial.flush();
        while (!Serial.available());
    }while (Serial.read()!=HANDSHAKE_CODE);
}