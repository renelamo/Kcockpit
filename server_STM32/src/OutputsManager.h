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
#include <Adafruit_GFX.h>
#include <Adafruit_ST7735.h>


class OutputsManager {
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

    Adafruit_ST7735* tft;

    explicit OutputsManager();
    void setMET(int64_t seconds);
    void setElecCharge(int ratio);
    void setFuelLevel(int ratio);
    void setOxidLevel(int ratio);
    void setMonoPLevel(int ratio);
    void wait(uint8_t state);
    void setWaitMode(bool waitMode);
    static void setSASLeds(uint16_t data);
    static void buzz(int freq);

    /// Draws a curve as segments given some points
    /// \param coords the list of x and y coordinates
    /// \param length the number of points (half the number of coordinates)
    /// \param color
    void drawCurve(uint8_t coords[], uint8_t length, uint16_t color);
    void fillPoly(uint8_t* coords, uint8_t length, uint16_t color);
};


#endif //STM32_OUTPUTSMANAGER_H
