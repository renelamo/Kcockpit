#include "Arduino.h"
#include "Comm.h"
#include "OutputsManager.h"
#include <InputsManager.h>

OutputsManager* omgr;
InputsManager* imgr;
HardwareTimer* timer;

void timerISR(HardwareTimer*){
    digitalToggle(LED_BUILTIN);
}

void setup(){
    Serial.begin(115200, SERIAL_8N1);
    omgr = new OutputsManager();
    imgr = new InputsManager();
    digitalWrite(LED_GREEN, HIGH);
/*
    timer = new HardwareTimer(TIM1);
    timer->attachInterrupt(timerISR);
    timer->setOverflow(2, HERTZ_FORMAT);
    timer->resume();
    */
}

void loop(){
    Comm::capt(omgr);
}


