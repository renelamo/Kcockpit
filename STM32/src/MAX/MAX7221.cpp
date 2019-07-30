//
// Created by rene on 14/12/18.
//

#include "MAX7221.h"

#define DAYS_PER_YEAR 426
#define HOURS_PER_DAY 6
#define MINUTES_PER_HOUR 60
#define SECONDS_PER_MINUTE 60

MAX7221::MAX7221(uint8_t pinSelect) {
    selectPin=pinSelect;
    pinMode(selectPin, OUTPUT);
    for(unsigned char & i : state){
        i=(byte)0;
    }
    wake();
    setIntensity(15);
    write(SCAN_LIMIT, 0x07);
}

void MAX7221::sleep() {
    write(SHUTDOWN, 0b0000);
}

void MAX7221::wake() {
    write(SHUTDOWN, 0b0001);
}

void MAX7221::test() {
    write(TEST, 0b0001);
    delay(1000);
    write(TEST, 0b0000);
}

void MAX7221::nbDigitsDisplayed(int nbdigit) {
    if(nbdigit>7 or nbdigit<1){return;}
    write(SCAN_LIMIT, (byte)nbdigit);
}

void MAX7221::setDecodeMode(byte mode) {
    write(DECODE, mode);
}

void MAX7221::setRegister(DigitsRegister digit, byte data) {
    state[digit-1]=data;
}

byte MAX7221::getRegister(DigitsRegister digit) {
    return state[digit-1];
}

void MAX7221::flushDigit(DigitsRegister digit) {
    digitalWrite(selectPin, LOW);
    SPI.transfer16(digit << 8u | state[digit - 1]);
    digitalWrite(selectPin, HIGH);
}

void MAX7221::flush() {
    for(auto digit:allRegisters){
        flushDigit(digit);
    }
}

void MAX7221::setIntensity(int newIntensity) {
    if(newIntensity>16 or newIntensity<1){return;}
    write(INTENSITY, (byte)newIntensity);
}

void MAX7221::write(Register r, byte data) {
    digitalWrite(selectPin, LOW);
    SPI.transfer16( r << 8u | data);
    digitalWrite(selectPin, HIGH);
}

void smartTime(int y, int d,int h, int m, int s, std::vector<char>* buff){
    bool noYear = true;
    if(y>0){
        buff->push_back('y');
        noYear = false;
    }
    while (y>0){
        buff->insert(buff->begin(), y%10);
        y/=10;
    }
    bool noDay = (d == 0) & noYear;
    if(!noYear || d / 100 > 0){
        buff->push_back(d/100);
    }
    if( !noYear || d/10 > 0){
        buff->push_back(d/10%10);
    }
    if(! noDay){
        buff->push_back(d%10);
        buff->push_back('d');
    }
    bool noHour = (h==0) & noDay;
    if(!noHour){
        buff->push_back(h%10);
        buff->push_back('h');
    }
    bool noMinute = (m==0) & noHour;
    if( !noHour || m/10 > 0){
        buff->push_back(m/10);
    }
    if(!noMinute ){
        buff->push_back(m%10);
        buff->push_back('\'');
    }
    if( !noMinute || s/10 > 0){
        buff->push_back(s/10);
    }
    buff->push_back(s%10);
    buff->push_back('"');
}

void MAX7221::printDate(unsigned long time) {
    int s = time % SECONDS_PER_MINUTE;
    time /= SECONDS_PER_MINUTE;
    int min = time % MINUTES_PER_HOUR;
    time /= MINUTES_PER_HOUR;
    int h = time %HOURS_PER_DAY;
    time /= HOURS_PER_DAY;
    int d = time % DAYS_PER_YEAR;
    int y = time /DAYS_PER_YEAR;

    std::vector<char> buff;
    smartTime(y,d,h,min,s, &buff);
    while (buff.size() < 8){
        buff.insert(buff.begin(), 'e');
    }
    uint8_t decMode = NO_DECODE;
    for (unsigned int i=0; i<8; i++){
        char c = buff[7-i];
        switch (c){
            case '"':
                setRegister(allRegisters[i], 0b00100010);
                break;
            case '\'':
                setRegister(allRegisters[i], 0b00000010);
                break;
            case 'h':
                setRegister(allRegisters[i], 0b00010111);
                break;
            case 'd':
                setRegister(allRegisters[i], 0b00111101);
                break;
            case 'y':
                setRegister(allRegisters[i], 0b00100111);
                break;
            case 'e':
                setRegister(allRegisters[i], 0x00);
                break;
            default:
                setRegister(allRegisters[i], c);
                decMode|= 1u << (i);
        }
    }
    setDecodeMode(decMode);

    flush();
}