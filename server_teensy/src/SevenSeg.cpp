//
// Created by rene on 17/12/18.
//

#include "SevenSeg.h"

SevenSeg::SevenSeg(MAX7221 *newHost) {
    host=newHost;
    host->decode(FULL_DECODE);
}

String SevenSeg::toString(float data){
    int neg=(int)(data<0);
    int dec=(int)log10(abs(data));
    return String(data, (unsigned char)(7-dec-neg));
}

void SevenSeg::display(float data) {
    String str= toString(data);
    int i=0;
    for(int s=0; s<8; ++s){
        byte disp;
        switch(str[s]){
            case '-':
                disp=MINUS;
                host->setState(i, disp);
                break;
            case '.':
                disp=DOT;
                --i;
                break;
            default:
                disp=(byte)str[s];
                if(s<=str.length()-2) {
                    if (str[s + 1] == '.') {
                        disp |= DOT;
                    }
                }
                host->setState(i, disp);
                break;
        }
        ++i;
    }

}

void SevenSeg::wait() {
    host->decode(NO_DECODE);
    for(int i=0; i<=256;){
        i*=2;
        for(int j=0; j<8; ++j){
            host->setState(j,(byte)i);
            host->display();
        }
        delay(100);
    }
    host->decode(FULL_DECODE);
}