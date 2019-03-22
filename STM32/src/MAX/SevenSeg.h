//
// Created by rene on 17/12/18.
//

#ifndef TEENSY_SEVENSEG_H
#define TEENSY_SEVENSEG_H


#include "MAX7221.h"
#include "Arduino.h"

enum letter{
    MINUS=0b1010,
    E=0b1011,
    H=0b1100,
    L=0b1101,
    P=0b1110,
    BLANK=0b1111,
    DOT=0b10000000,
};

/********************************************
 *         (A)
 *    +-----------+
 *    |           |
 * (F)|           |(B)
 *    |    (G)    |
 *    +-----------+
 *    |           |
 * (E)|           |(C)
 *    |    (D)    |
 *    +-----------+
 *                  *(DP)
 *
 *  Trames non codées de la forme:
 *  0b(DP)(A)(B)(C)(D)(E)(F)(G)
 *
 *  Trames codées de la forme:
 *  0b(DP)XXX[code B du digit sur 4 bits]
 *
 ******************************************/

enum decodedLetter{
    sec=0b00100010, //''
    mn=0b00000010, //'
    hours=0b00010111, //h
    days=0b00111101, //d
    months=0b00010101, //n
    years=0b00100111, //y
};

class SevenSeg {
private:
    MAX7221* host;
    String toString(float data);
public:
    SevenSeg(MAX7221* newHost);
    void display(float data);
};


#endif //TEENSY_SEVENSEG_H
