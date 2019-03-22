//
// Created by rene on 07/03/19.
//

#ifndef STM32_UTILS_H
#define STM32_UTILS_H

#include "MAX/MAX7221.h"
#include "PinMap.h"
#include "MAX/Bargraph.h"

MAX7221* bargraphs=new MAX7221(BARGRAPH_PIN);
Bargraph* elecGraph=new Bargraph(bargraphs, (DigitsRegister[2]){d0,d1});

#endif //STM32_UTILS_H
