//
// Created by rene on 14/06/19.
//

#ifndef STM32_OUTPUTSMANAGER_H
#define STM32_OUTPUTSMANAGER_H

#include <MAX7221.h>
#include <SevenSeg.h>
#include "Arduino.h"
#include <Bargraph.h>
#include "PinMap.h"


class OutputsManager {
private:
    static void initPins();
public:
    DigitsRegister adOxydizer[2] = {d4,d5};
    DigitsRegister adFuel[2] = {d2,d3};
    DigitsRegister adElec[2] = {d0,d1};
    DigitsRegister adProp[2] = {d6, d7};
    MAX7221* bargraphs;
    Bargraph* elecGraph;
    Bargraph* fuelGraph;
    Bargraph* oxidGraph;
    Bargraph* monoPGraph;

    MAX7221* altitudeMUX;
    MAX7221* timeMUX;
    MAX7221* apoapsisMUX;
    MAX7221* periapsisMUX;
    SevenSeg* altitudeSegments;
    SevenSeg* METSegments;
    SevenSeg* apoSegments;
    SevenSeg* periSegments;
public:
    explicit OutputsManager();
    void setMET(int64_t seconds);
    void setElecCharge(int ratio);
    void setFuelLevel(int ratio);
    void setOxidLevel(int ratio);
    void setMonoPLevel(int ratio);
    void wait(uint8_t state);
    void setWaitMode(bool waitMode);
    static void setSASLeds(int data);
    static void setActionGroupLeds(int data);
    static void buzz(int freq);
};


#endif //STM32_OUTPUTSMANAGER_H
