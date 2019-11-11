//
// Created by rene on 19/05/19.
//

#include "Comm.h"
#include "PinMap.h"
#include "commTable.h"
#include "OutputsManager.h"
#include "InputsManager.h"

bool Comm::capt(OutputsManager* omgr) {
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
            omgr->altitudeSegments->display((float)arg2*(float)pow(10, arg1));
            return true;
        case SAS_CODE_SET:
            while(!Serial.available());
            arg1=Serial.read();
            OutputsManager::setSASLEDs(arg1);
            return true;
        case MET_CODE:
            while(!Serial.available());
            arg1=Serial.read();
            while(!Serial.available());
            arg2=Serial.read();
            omgr->setMET(((unsigned long)arg1)<<16u|(unsigned)arg2);//Fixme: c'est peut-être 32 bits de décallage?
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
            OutputsManager::setActionGroupLeds(arg1);
            return true;
        case THROTTLE_CODE:
            InputsManager::sendThrottle();
            return true;
        case PITCH_CODE:
            InputsManager::sendPitch();
            return true;
        case YAW_CODE:
            InputsManager::sendYaw();
            return true;
        case ROLL_CODE:
            InputsManager::sendRoll();
            return true;
        case X_CODE:
            InputsManager::sendX();
            return true;
        case Y_CODE:
            InputsManager::sendY();
            return true;
        case Z_CODE:
            InputsManager::sendZ();
            return true;
        case T_CODE:
            InputsManager::sendT();
            return true;
        case STAGE_CODE:
            InputsManager::sendStage();
            return true;
        case SAS_CODE_GET:
            InputsManager::sendSAS();
            return true;
        case ACTION_CODE_CODE_GET:
            InputsManager::sendActionGroup();
            return true;
        case BUZZ_CODE:
            while (!Serial.available());
            arg1 = Serial.read();
            OutputsManager::buzz(arg1);
            return true;
        default:
            return false;
    }
}

/*
void Comm::handshake(){
    do{
        Serial.write(HANDSHAKE_CODE);
        Serial.flush();
        while (!Serial.available());
    }while (Serial.read()!=HANDSHAKE_CODE);
}
*/