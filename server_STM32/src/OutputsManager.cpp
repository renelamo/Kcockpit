//
// Created by rene on 14/06/19.
//
#include "OutputsManager.h"

#include "Arduino.h"

void OutputsManager::initPins(){
    pinMode(STAGE_LED, OUTPUT);
    pinMode(PRECISE_CONTROLS, OUTPUT);
    pinMode(BUZZ_PIN, OUTPUT);

    pinMode(SAS_LED, OUTPUT);
    pinMode(RCS_LED, OUTPUT);
    pinMode(LIGHTS_LED, OUTPUT);
    pinMode(GEARS_LED, OUTPUT);
    pinMode(BRAKES_LED, OUTPUT);

    pinMode(CUSTOM_LED_1, OUTPUT);
    pinMode(CUSTOM_LED_2, OUTPUT);
    pinMode(CUSTOM_LED_3, OUTPUT);
    pinMode(CUSTOM_LED_4, OUTPUT);
    pinMode(CUSTOM_LED_5, OUTPUT);
}

void initSPI(){
    SPI.setMISO(MISO_PIN);
    SPI.setMOSI(MOSI_PIN);
    SPI.setSCLK(CLOCK_PIN);
    SPI.setDataMode(SPI_MODE_0);
    SPI.begin();
}


OutputsManager::OutputsManager() {
    initPins();
    initSPI();

    bargraphs = new MAX7221(BARGRAPH_PIN);
    bargraphs->setIntensity(6);//Pour pas se brûler la rétine
    elecGraph = new Bargraph(bargraphs, adElec);
    fuelGraph = new Bargraph(bargraphs, adFuel);
    oxidGraph = new Bargraph(bargraphs, adOxydizer);
    monoPGraph = new Bargraph(bargraphs, adProp);

    altitudeMUX = new MAX7221(ALT_PIN);
    altitudeSegments = new SevenSeg(altitudeMUX);

    timeMUX = new MAX7221(TIME_PIN);
    METSegments = new SevenSeg(timeMUX);

    apoapsisMUX = new MAX7221(AP_PIN);
    apoSegments = new SevenSeg(apoapsisMUX);

    periapsisMUX = new MAX7221(PE_PIN);
    periSegments = new SevenSeg(periapsisMUX);
}


void OutputsManager::setSASLeds(int data) {
    bool sas= data%2;
    data/=2;
    bool rcs= data%2;
    data/=2;
    bool lights= data%2;
    data/=2;
    bool gears= data%2;
    data/=2;
    bool brakes= data%2;
    data/=2;
    bool stage = data%2;

    digitalWrite(SAS_LED, (sas?HIGH:LOW));
    digitalWrite(RCS_LED, (rcs?HIGH:LOW));
    digitalWrite(LIGHTS_LED, (lights?HIGH:LOW));
    digitalWrite(GEARS_LED, (gears?HIGH:LOW));
    digitalWrite(BRAKES_LED, (brakes?HIGH:LOW));
    digitalWrite(STAGE_LED, (stage?HIGH:LOW));
}

void OutputsManager::setMET(int64_t seconds) {
    METSegments->printDate(seconds);
}

void OutputsManager::setElecCharge(int ratio) {
    elecGraph->display(ceil(ratio/2.0)); //ratio a une valeur entre 0 et 20
}

void OutputsManager::setFuelLevel(int ratio) {
    fuelGraph->display(ceil(ratio/2.0));
}

void OutputsManager::setOxidLevel(int ratio) {
    oxidGraph->display(ceil(ratio/2.0));
}

void OutputsManager::setMonoPLevel(int ratio) {
    monoPGraph->display(ceil(ratio/2.0));
}

void OutputsManager::setActionGroupLeds(int data) {
    bool sas= data%2;
    data/=2;
    bool rcs= data%2;
    data/=2;
    bool lights= data%2;
    data/=2;
    bool gears= data%2;
    data/=2;
    bool brakes= data%2;

    digitalWrite(CUSTOM_LED_1, (sas?HIGH:LOW));
    digitalWrite(CUSTOM_LED_2, (rcs?HIGH:LOW));
    digitalWrite(CUSTOM_LED_3, (lights?HIGH:LOW));
    digitalWrite(CUSTOM_LED_4, (gears?HIGH:LOW));
    digitalWrite(CUSTOM_LED_5, (brakes?HIGH:LOW));
}

void OutputsManager::buzz(int freq) {
    if(freq == 0){
        noTone(BUZZ_PIN);
        return;
    }
    tone(BUZZ_PIN, freq);
}

void OutputsManager::wait(uint8_t state) {
    uint8_t toWrite = 0b01000000u >> (state%6u);
    for(DigitsRegister r:allRegisters){
        altitudeMUX->setRegister(r, toWrite);
        apoapsisMUX->setRegister(r, toWrite);
        periapsisMUX->setRegister(r, toWrite);
        timeMUX->setRegister(r, toWrite);
        elecGraph->displayOne(state%10+1);
        monoPGraph->displayOne(state%10+1);
        fuelGraph->displayOne(state%10+1);
        oxidGraph->displayOne(state%10+1);
    }
    altitudeMUX->flush();
    apoapsisMUX->flush();
    periapsisMUX->flush();
    timeMUX->flush();
}

void OutputsManager::setWaitMode(bool waitMode) {
    altitudeMUX->setDecodeMode(waitMode?NO_DECODE:FULL_DECODE);
    apoapsisMUX->setDecodeMode(waitMode?NO_DECODE:FULL_DECODE);
    periapsisMUX->setDecodeMode(waitMode?NO_DECODE:FULL_DECODE);
    timeMUX->setDecodeMode(waitMode?NO_DECODE:FULL_DECODE);
    if(!waitMode) {
        oxidGraph->displayOne(0);
        monoPGraph->displayOne(0);
        elecGraph->displayOne(0);
        fuelGraph->displayOne(0);
        for(DigitsRegister r:allRegisters){
            altitudeMUX->setRegister(r, BLANK);
            apoapsisMUX->setRegister(r, BLANK);
            periapsisMUX->setRegister(r, BLANK);
            timeMUX->setRegister(r, BLANK);
        }
        timeMUX->flush();
        apoapsisMUX->flush();
        periapsisMUX->flush();
        altitudeMUX->flush();
    }
}

