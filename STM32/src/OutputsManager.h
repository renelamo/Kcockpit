//
// Created by rene on 14/06/19.
//

#ifndef STM32_OUTPUTSMANAGER_H
#define STM32_OUTPUTSMANAGER_H

#include <MAX/MAX7221.h>
#include <MAX/SevenSeg.h>
#include "Arduino.h"
#include "MAX/Bargraph.h"
#include "PinMap.h"
#include "SerialManager.h"


class OutputsManager {
private:
    SerialManager* serialManager;
public:
    DigitsRegister adOxydizer[2] = {d0,d1};
    DigitsRegister adFuel[2] = {d2,d3};
    DigitsRegister adElec[2] = {d4,d5};
    DigitsRegister adProp[2] = {d6, d7};
    MAX7221* bargraphs;
    Bargraph* elecGraph;
    Bargraph* fuelGraph;

    MAX7221* altitudeMUX;
    MAX7221* timeMUX;
    SevenSeg* altitudeSegments;
    SevenSeg* METSegments;
public:
    explicit OutputsManager(SerialManager* smgr);
    void setSASLEDs(int data);
    void setMET(double seconds);
    void customChangedHandler();
    void setElecCharge(int ratio);
    void setFuelLevel(int ratio);
};


#endif //STM32_OUTPUTSMANAGER_H
