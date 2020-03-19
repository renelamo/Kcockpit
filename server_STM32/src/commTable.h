//
// Created by rene on 19/04/19.
//

#ifndef STM32_COMMTABLE_H
#define STM32_COMMTABLE_H

#define HANDSHAKE_CODE          0X00
#define STAGE_CODE              0x01
#define THROTTLE_CODE           0x02
#define PITCH_CODE              0x03
#define YAW_CODE                0x04
#define ROLL_CODE               0x05
#define X_CODE                  0x06
#define Y_CODE                  0x07
#define Z_CODE                  0x08
#define T_CODE                  0x09

#define SAS_CODE_SET            0x0A
#define SAS_CODE_GET            0x0B
#define ACTION_GROUP_CODE_SET   0x0C
#define ACTION_CODE_CODE_GET    0x0D

#define ALTITUDE_CODE           0x81
#define MET_CODE                0x82
#define AP_ALT_CODE             0x83
#define AP_TIME_CODE            0x84
#define PE_ALT_CODE             0x85
#define PE_TIME_CODE            0x86

#define ELEC_CODE               0x90
#define FUEL_CODE               0x91
#define OXID_CODE               0x92
#define MONOP_CODE              0x93

#define BUZZ_CODE               0xA0

#define NO_OP_CODE              0xFF

#endif //STM32_COMMTABLE_H
