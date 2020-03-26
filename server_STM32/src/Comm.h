//
// Created by rene on 19/05/19.
//

#ifndef STM32_COMM_H
#define STM32_COMM_H

#include "OutputsManager.h"
#include "InputsManager.h"

class Comm {
public:
    static bool capt(OutputsManager* outputsManager, InputsManager* inputsManager);
};


#endif //STM32_COMM_H
