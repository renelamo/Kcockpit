//
// Created by rene on 19/05/19.
//

#include "Comm.h"
#include "PinMap.h"
#include "commTable.h"
#include "utils.h"
#include "offsets.h"
#include "OutputsManager.h"

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

bool Comm::capt(OutputsManager* omgr) {
    if( ! Serial.available()){
        return false;
    }
    int code=Serial.read();
    int arg1;
    int arg2;
    switch (code){
        case HANDSHAKE_CODE:
            Serial.write(HANDSHAKE_CODE);
            return true;
        case ALTITUDE_CODE:
            if( ! Serial.available()){
                return false;
            }
            arg1=Serial.read();
            if( ! Serial.available()){
                return false;
            }
            arg2=Serial.read();
            altitudeSegments->display(arg2*pow(10, arg1));
            return true;
        case SAS_CODE:
            if(Serial.available()){
                return false;
            }
            arg1=Serial.read();
            omgr->setSASLEDs(arg1);
        default:
            return false;
    }
}

void Comm::sendThrottle(){
    int val=analogRead(THROTTLE_PIN);
    Serial.write(THROTTLE_CODE);//entre 0 et 1023 (10 bits)
    Serial.write(val/4);//entre 0 et 255 (8 bits)
    Serial.flush();
}
void Comm::sendPitch(){
    int val=analogRead(PITCH_PIN);
    val= lissage(val, PITCH_CENTER);
    Serial.write(PITCH_CODE);
    Serial.write(val/4);
    Serial.flush();
}
void Comm::sendYaw(){
    int val=analogRead(YAW_PIN);
    val= lissage(val, YAW_CENTER);
    Serial.write(YAW_CODE);
    Serial.write(val/4);
    Serial.flush();
}
void Comm::sendRoll(){
    int val=analogRead(ROLL_PIN);
    val= lissage(val, ROLL_CENTER);
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