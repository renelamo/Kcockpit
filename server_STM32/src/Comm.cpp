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
    uint8_t buffer8[8];
    switch (code){
        case NO_OP_CODE:
            return true;
        case HANDSHAKE_CODE:
            Serial.write(HANDSHAKE_CODE);
            return true;
        case STAGE_CODE: //deprecated
            InputsManager::sendStage();
            return true;
        case BUZZ_CODE:
            while (!Serial.available());
            arg1 = Serial.read();
            OutputsManager::buzz(arg1);
            return true;

////////////////////////////////// 7 SEGMENTS ///////////////////////////////////////////////
//region 7_seg
        case ALTITUDE_CODE:
            while(!Serial.available());
            Serial.readBytes(buffer8, 8);
            //buff[0] poids fort
            omgr->altitudeSegments->printLong(*((int64_t*)buffer8));
            return true;
        case AP_ALT_CODE:
            while(!Serial.available());
            Serial.readBytes(buffer8, 8);
            if(buffer8[0] < 0){
                omgr->apoSegments->printErr();
                return true;
            }
            omgr->apoSegments->printLong(*((int64_t*)buffer8));
            return true;
        case PE_ALT_CODE:
            while(!Serial.available());
            Serial.readBytes(buffer8, 8);
            if(buffer8[0] < 0){
                omgr->periSegments->printErr();
                return true;
            }
            omgr->periSegments->printLong(*((int64_t*)buffer8));
            return true;
        case AP_TIME_CODE:
            while(!Serial.available());
            Serial.readBytes(buffer8, 8);
            omgr->apoSegments->printDate(*((int64_t*)buffer8));
            return true;
        case PE_TIME_CODE:
            while(!Serial.available());
            Serial.readBytes(buffer8, 8);
            omgr->periSegments->printDate(*((int64_t*)buffer8));
            return true;
        case MET_CODE:
            uint8_t buff[8];
            Serial.readBytes(buff, 8);
            omgr->setMET(*(int64_t*)(buff));
            return true;
//endregion
///////////////////////////////// ACTION GROUPS /////////////////////////////////////////////
//region action_groups
        case SAS_CODE_SET:
            while(!Serial.available());
            arg1=Serial.read();
            OutputsManager::setSASLeds(arg1);
            return true;
        case ACTION_GROUP_CODE_SET:
            while(!Serial.available());
            arg1 = Serial.read();
            OutputsManager::setActionGroupLeds(arg1);
            return true;
        case SAS_CODE_GET:
            imgr->sendSAS();
            return true;
        case ACTION_CODE_CODE_GET:
            InputsManager::sendActionGroup();
            return true;
//endregion
/////////////////////////////////// BARGRAPHS ///////////////////////////////////////////////
//region bragraphs
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
//endregion
///////////////////////////////// ANALOG INPUTS /////////////////////////////////////////////
//region analog_inputs
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
            //endregion

        default:
            digitalWrite(LED_BUILTIN, LOW);
            return false;
    }
}