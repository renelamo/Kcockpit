package krpc.main;

public interface CommTable {
    byte HANDSHAKE_CODE     = 0x00;
    byte STAGE_CODE         = 0x01;
    byte THROTTLE_CODE      = 0x02;
    byte PITCH_CODE         = 0x03;
    byte YAW_CODE           = 0x04;
    byte ROLL_CODE          = 0x05;
    byte X_CODE             = 0x06;
    byte Y_CODE             = 0x07;
    byte Z_CODE             = 0x08;
    byte T_CODE = 0x09;
    byte LEDS_CODE_SET = 0x0A;
    byte SAS_CODE_GET = 0x0B;
    byte ACTIONS_CODE_GET   = 0x0D;

    int ALTITUDE_CODE       = 0x81;
    int MET_CODE            = 0x82;
    int AP_ALT_CODE         = 0x83;
    int AP_TIME_CODE        = 0x84;
    int PE_ALT_CODE         = 0x85;
    int PE_TIME_CODE        = 0x86;

    int ELEC_CODE           = 0x90;
    int FUEL_CODE           = 0x91;
    int OXID_CODE           = 0x92;
    int MONOP_CODE          = 0x93;

    int BUZZ_CODE           = 0xA0;

    int NO_OP_CODE          = 0xFF;
}
