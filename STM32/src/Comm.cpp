//
// Created by rene on 19/05/19.
//

#include "Comm.h"
#include "PinMap.h"
#include "commTable.h"
#include "OutputsManager.h"
#include "InputsManager.h"

bool Comm::capt(OutputsManager* omgr, InputsManager* imgr) {
    while (!Serial.available());
    int code=Serial.read();
    int arg1, arg2;
    switch (code){
        case NO_OP_CODE:
            return true;
        case HANDSHAKE_CODE:
            Serial.write(HANDSHAKE_CODE);
            return true;
        case ALTITUDE_CODE:
            while(!Serial.available());
            arg1=Serial.read();
            while(!Serial.available());
            arg2=Serial.read();
            omgr->altitudeSegments->display(arg2*pow(10, arg1)); //TODO: passer en double
            return true;
        case SAS_CODE_SET:
            while(!Serial.available());
            arg1=Serial.read();
            omgr->setSASLEDs(arg1);
            return true;
        case MET_CODE:
            while(!Serial.available());
            arg1=Serial.read();
            while(!Serial.available());
            arg2=Serial.read();
            omgr->setMET(((unsigned long)arg1)<<32|arg2);
            return true;
        case ELEC_CODE:
            while(!Serial.available());
            arg1 = Serial.read();
            omgr->setElecCharge(arg1);
            return true;
        case FUEL_CODE:
            while(!Serial.available());
            arg1 = Serial.read();
            omgr->setFuelLevel(arg1);
            return true;
        case OXID_CODE:
            while(!Serial.available());
            arg1 = Serial.read();
            omgr->setOxidLevel(arg1);
            return true;
        case MONOP_CODE:
            while(!Serial.available());
            arg1 = Serial.read();
            omgr->setMonoPLevel(arg1);
            return true;
        case ACTION_GROUP_CODE_SET:
            while(!Serial.available());
            arg1 = Serial.read();
            omgr->setActionGroupLeds(arg1);
            return true;
        case THROTTLE_CODE:
            imgr->sendThrottle();
            return true;
        case PITCH_CODE:
            imgr->sendPitch();
            return true;
        case YAW_CODE:
            imgr->sendYaw();
            return true;
        case ROLL_CODE:
            imgr->sendRoll();
            return true;
        case X_CODE:
            imgr->sendX();
            return true;
        case Y_CODE:
            imgr->sendY();
            return true;
        case Z_CODE:
            imgr->sendZ();
            return true;
        case T_CODE:
            imgr->sendT();
            return true;
        case STAGE_CODE:
            imgr->sendStage();
            return true;
        case SAS_CODE_GET:
            imgr->sendSAS();
            return true;
        case ACTION_CODE_CODE_GET:
            imgr->sendActionGroup();
            return true;
        case BUZZ_CODE:
            while (!Serial.available());
            arg1 = Serial.read();
            omgr->buzz(arg1);
            return true;
        default:
            return false;
    }
}


void Comm::handshake(){
    do{
        Serial.write(HANDSHAKE_CODE);
        Serial.flush();
        while (!Serial.available());
    }while (Serial.read()!=HANDSHAKE_CODE);
}