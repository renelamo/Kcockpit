#include "Arduino.h"
#include "Comm.h"
#include "OutputsManager.h"

OutputsManager* omgr;
HardwareTimer* timer;

void timerISR(HardwareTimer*){
    digitalToggle(LED_BUILTIN);
}

void setup(){
    omgr = new OutputsManager();
    digitalWrite(LED_GREEN, HIGH);
    timer = new HardwareTimer(TIM1);
    timer->attachInterrupt(timerISR);
    timer->setOverflow(2, HERTZ_FORMAT);
    timer->resume();
}

void loop(){
    Comm::capt(omgr);
}


