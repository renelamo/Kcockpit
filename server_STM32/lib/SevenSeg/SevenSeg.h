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

class SevenSeg {
private:
    MAX7221* host;
    static String toString(float data);
public:
    explicit SevenSeg(MAX7221* newHost);
    void display(float data);
    void printLong(int64_t data);
    void printDate(int64_t seconds);
};


#endif //TEENSY_SEVENSEG_H
