#include "Arduino.h"
#include "Comm.h"
#include "OutputsManager.h"
#include <InputsManager.h>

OutputsManager* omgr;
InputsManager* imgr;
HardwareTimer* timer;

void print_sizes(){
    Serial.print("long ");Serial.println(sizeof(long));
    Serial.print("long long ");Serial.println(sizeof(long long));
    Serial.print("float "); Serial.println(sizeof(float));
    Serial.print("double "); Serial.println(sizeof(double));
    Serial.print("int ");Serial.println(sizeof(int));
    Serial.print("short ");Serial.println(sizeof(short));
    Serial.print("char ");Serial.println(sizeof(char));
    Serial.print("byte "); Serial.println(sizeof(byte));
}

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
    omgr->setWaitMode(true);
    int i = 0;
    while (!Serial.available()){
        omgr->wait(i++);
        delay(100);
    }
    omgr->setWaitMode(false);
}

void loop(){
    Comm::capt(omgr, imgr);
    Serial.flush();//Pour pas surcharger la série, on s'assure qu'elle ait fini d'écrire avant de continuer
}


