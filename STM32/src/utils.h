//
// Created by rene on 07/03/19.
//

#ifndef STM32_UTILS_H
#define STM32_UTILS_H

#include "MAX/MAX7221.h"
#include "PinMap.h"
#include "MAX/Bargraph.h"
#include "MAX/SevenSeg.h"
#include "commTable.h"

DigitsRegister adOxydizer[2] = {d0,d1};
DigitsRegister adFuel[2] = {d2,d3};
DigitsRegister adElec[2] = {d4,d5};
DigitsRegister adProp[2] = {d6, d7};

MAX7221* bargraphs=new MAX7221(BARGRAPH_PIN);
Bargraph* elecGraph=new Bargraph(bargraphs, adElec);
Bargraph* fuelGraph=new Bargraph(bargraphs, adFuel);

MAX7221* altitudeMUX=new MAX7221(ALT_PIN);
SevenSeg* altitudeSegments=new SevenSeg(altitudeMUX);

#endif //STM32_UTILS_H
