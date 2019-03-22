//
// Created by rene on 07/03/19.
//

#include "Joystick3.h"

void Joystick3::refresh() {
    for(int i=0; i<3; ++i){
        state[i]=analogRead(analogPins[i]); //TODO: vérifier le format des données
    }
}