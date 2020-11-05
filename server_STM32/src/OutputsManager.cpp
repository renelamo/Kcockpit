//
// Created by rene on 14/06/19.
//
#include "OutputsManager.h"

#include "Arduino.h"

void initSPI() {
    pinMode(LED_PIN, OUTPUT);
    //auto* settings = new SPISettings(SPI_SPEED_CLOCK_DIV2_MHZ, MSBFIRST, SPI_MODE_0);
    //SPI.beginTransaction(*settings);
    SPI.setMISO(MISO_PIN);
    SPI.setMOSI(MOSI_PIN);
    SPI.setSCLK(CLOCK_PIN);
    SPI.setDataMode(SPI_MODE_0);
    SPI.setClockDivider(SPI_SPEED_CLOCK_DIV2_MHZ); // anyway, the default clock speed is 4 MHz, less than the max of 10 MHz required by the MAXs
    SPI.begin();
}


OutputsManager::OutputsManager() {
    initSPI();
    pinMode(BUZZ_PIN, OUTPUT);

    bargraphs = new MAX7221(BARGRAPH_PIN);
    bargraphs->setIntensity(6);//Pour pas se brûler la rétine
    elecGraph = new Bargraph(bargraphs, adElec);
    fuelGraph = new Bargraph(bargraphs, adFuel);
    oxidGraph = new Bargraph(bargraphs, adOxydizer);
    monoPGraph = new Bargraph(bargraphs, adProp);

    altitudeMUX = new MAX7221(ALT_PIN);
    altitudeSegments = new SevenSeg(altitudeMUX);

    //timeMUX = new MAX7221(TIME_PIN);
    timeMUX = new MAX7221(TIME_PIN, 1);
    METSegments = new SevenSeg(timeMUX);

    apoapsisMUX = new MAX7221(AP_PIN);
    apoSegments = new SevenSeg(apoapsisMUX);

    periapsisMUX = new MAX7221(PE_PIN);
    periSegments = new SevenSeg(periapsisMUX);

    tft = new Adafruit_ST7735(&SPI, TFT_CS, TFT_DC, TFT_RESET);
    tft -> initR(INITR_BLACKTAB);
    tft -> fillScreen(ST7735_BLACK);
}


void OutputsManager::setSASLeds(uint16_t data) {
    digitalWrite(LED_PIN, LOW);
    SPI.transfer16(data);
    digitalWrite(LED_PIN, HIGH);

}

void OutputsManager::setMET(int64_t seconds) {
    METSegments->printDate(seconds);
}

void OutputsManager::setElecCharge(int ratio) {
    elecGraph->display(ratio); //ratio a une valeur entre 0 et 20
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

void OutputsManager::buzz(int freq) {
    if (freq == 0) {
        noTone(BUZZ_PIN);
        return;
    }
    tone(BUZZ_PIN, freq);
}

void OutputsManager::wait(uint8_t state) {
    uint8_t toWrite = 0b01000000u >> (state % 6u);
    for (DigitsRegister r:allRegisters) {
        altitudeMUX->setRegister(r, toWrite);
        apoapsisMUX->setRegister(r, toWrite);
        periapsisMUX->setRegister(r, toWrite);
        timeMUX->setRegister(r, toWrite);
    }
    elecGraph->displayOne(state % 10 + 1);
    monoPGraph->displayOne(state % 10 + 1);
    fuelGraph->displayOne(state % 10 + 1);
    oxidGraph->displayOne(state % 10 + 1);
    setSASLeds(0b1u << (state % 16u));
    apoapsisMUX->flush();
    periapsisMUX->flush();
    altitudeMUX->flush();
    timeMUX->flush();
}

void OutputsManager::setWaitMode(bool waitMode) {
    altitudeMUX->setDecodeMode(waitMode ? NO_DECODE : FULL_DECODE);
    apoapsisMUX->setDecodeMode(waitMode ? NO_DECODE : FULL_DECODE);
    periapsisMUX->setDecodeMode(waitMode ? NO_DECODE : FULL_DECODE);
    timeMUX->setDecodeMode(waitMode ? NO_DECODE : FULL_DECODE);
    if (!waitMode) {
        oxidGraph->displayOne(0);
        monoPGraph->displayOne(0);
        elecGraph->displayOne(0);
        fuelGraph->displayOne(0);
        for (DigitsRegister r:allRegisters) {
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

void OutputsManager::drawCurve(uint8_t coords[], uint8_t length, uint16_t color) {
    for (int i = 2; i < 2 * length; i += 2){
        tft -> drawLine(coords[i-2], coords[i-1], coords[i], coords[i+1], color);
    }
}

void OutputsManager::fillPoly(uint8_t *coords, uint8_t length, uint16_t color) {
    for (int i = 4; i < 2*length; i+=2){
        tft -> fillTriangle(coords[i-4], coords[i-3], coords[i-2], coords[i-1], coords[i], coords[i+1], color);
    }
}
