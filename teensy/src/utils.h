//
// Created by rene on 05/01/19.
//
#include <Arduino.h>
#include <SPI.h>
#include "Bargraph.h"
#include "LED.h"
#include "SevenSeg.h"


#ifndef TEENSY_UTILS_H
#define TEENSY_UTILS_H

MAX7221* bargraphs= new MAX7221(BARGRAPH_PIN);

Bargraph* oxydizer=new Bargraph(bargraphs, adOxydizer);
Bargraph* fuel=new Bargraph(bargraphs, adFuel);
Bargraph* elec=new Bargraph(bargraphs, adElec);
Bargraph* sup=new Bargraph(bargraphs, adSup);

MAX7221* leds= new MAX7221(LEDS_PIN);

LED* rcs=new LED(leds, {0,0});

MAX7221* alt=new MAX7221(ALT_PIN);
MAX7221* apoapsis=new MAX7221(AP_PIN);
MAX7221* periapsis=new MAX7221(PE_PIN);

#endif //TEENSY_UTILS_H
