//
// Created by rene on 19/05/19.
//

#include "Comm.h"
#include "PinMap.h"
#include "commTable.h"
#include "utils.h"
#include "offsets.h"
#include "OutputsManager.h"
#include "SerialManager.h"

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
    while (!Serial.available());
    int code=Serial.read();
    int arg1, arg2, arg3, arg4, arg5, arg6;
    int buff[8];
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
            omgr->altitudeSegments->display(arg2*pow(10, arg1)); //TODO: passer en double
            return true;
        case SAS_CODE:
            if( ! Serial.available()){
                return false;
            }
            arg1=Serial.read();
            omgr->setSASLEDs(arg1);
            return true;
        case MET_CODE:
            if( ! Serial.available()){
                return false;
            }
            arg1=Serial.read();
            if( ! Serial.available()){
                return false;
            }
            arg2=Serial.read();
            omgr->setMET(((unsigned long)arg1)<<32|arg2);
            return true;
        case ELEC_CODE:
            if(!Serial.available()){
                return false;
            }
            arg1 = Serial.read();
            omgr->setElecCharge(arg1);
            return true;
        case FUEL_CODE:
            if(!Serial.available()){
                return false;
            }
            arg1 = Serial.read();
            omgr->setFuelLevel(arg1);
            return true;
        default:
            return false;
    }
}

void Comm::sendThrottle(SerialManager smgr) {
    int val=analogRead(THROTTLE_PIN);
    std::vector<int> buff= {THROTTLE_CODE, val/4};
    smgr.add(&buff);
}
void Comm::sendPitch(SerialManager smgr) {
    int val=analogRead(PITCH_PIN);
    val= lissage(val, PITCH_CENTER);
    std::vector<int> buff = {THROTTLE_CODE, val/4};
    smgr.add(&buff);
}
void Comm::sendYaw(SerialManager smgr) {
    int val=analogRead(YAW_PIN);
    val= lissage(val, YAW_CENTER);
    std::vector<int> buff= {THROTTLE_CODE, val/4};
    smgr.add(&buff);
}
void Comm::sendRoll(SerialManager smgr) {
    int val=analogRead(ROLL_PIN);
    val= lissage(val, ROLL_CENTER);
    std::vector<int> buff = {THROTTLE_CODE, val/4};
    smgr.add(&buff);
}

void Comm::handshake(){
    do{
        Serial.write(HANDSHAKE_CODE);
        Serial.flush();
        while (!Serial.available());
    }while (Serial.read()!=HANDSHAKE_CODE);
}