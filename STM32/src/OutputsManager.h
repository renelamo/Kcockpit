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
    MAX7221* bargraphs=new MAX7221(BARGRAPH_PIN);
    Bargraph* elecGraph=new Bargraph(bargraphs, adElec);
    Bargraph* fuelGraph=new Bargraph(bargraphs, adFuel);

    MAX7221* altitudeMUX=new MAX7221(ALT_PIN);
    SevenSeg* altitudeSegments=new SevenSeg(altitudeMUX);
public:
    OutputsManager(SerialManager* smgr);
    void setSASLEDs(int data);
    void customChangedHandler();
};


#endif //STM32_OUTPUTSMANAGER_H
