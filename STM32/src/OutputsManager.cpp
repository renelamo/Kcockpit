//
// Created by rene on 14/06/19.
//
#include "OutputsManager.h"

#include "Arduino.h"
#include "utils.h"
#include "commTable.h"
#include "SerialManager.h"

void OutputsManager::initPins(){
    pinMode(LED_GREEN, OUTPUT);
}

void initSPI(){
    SPI.setMISO(MISO_PIN);
    SPI.setMOSI(MOSI_PIN);
    SPI.setSCLK(CLOCK_PIN);
    SPI.begin();
}


OutputsManager::OutputsManager(SerialManager* smgr) {
    //initPins();
    initSPI();
    serialManager=smgr;
    timeMUX = new MAX7221(AP_PIN);
    bargraphs = new MAX7221(BARGRAPH_PIN);
    bargraphs->setIntensity(6);
    elecGraph = new Bargraph(bargraphs, adElec);
    fuelGraph = new Bargraph(bargraphs, adFuel);
    oxidGraph = new Bargraph(bargraphs, adOxydizer);
    monoPGraph = new Bargraph(bargraphs, adProp);
    altitudeMUX = new MAX7221(ALT_PIN);
    altitudeSegments = new SevenSeg(altitudeMUX);
    METSegments = new SevenSeg(timeMUX);
}


void OutputsManager::setSASLEDs(int data) {
    bool sas= data%2;
    data/=2;
    bool rcs= data%2;
    data/=2;
    bool lights= data%2;
    data/=2;
    bool gears= data%2;
    data/=2;
    bool brakes= data%2;
}

void OutputsManager::setMET(double seconds) {
    METSegments->printDate((long)seconds);
}

void OutputsManager::setElecCharge(int ratio) {
    elecGraph->display(ratio);
}

void OutputsManager::customChangedHandler() {

}

void OutputsManager::setFuelLevel(int ratio) {
    fuelGraph->display(ratio);

}

void OutputsManager::setOxidLevel(int ratio) {
    oxidGraph->display(ratio);
}

void OutputsManager::setMonoPLevel(int ratio) {
    monoPGraph->display(ratio);
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
}

void OutputsManager::buzz(int freq) {
    if(freq == 0){
        noTone(BUZZ_PIN);
        return;
    }
    tone(BUZZ_PIN, freq);
}
