//
// Created by rene on 17/12/18.
//

#include "SevenSeg.h"

#define DAYS_PER_YEAR 426
#define HOURS_PER_DAY 6
#define MINUTES_PER_HOUR 60
#define SECONDS_PER_MINUTE 60

SevenSeg::SevenSeg(MAX7221 *newHost) {
    host=newHost;
    host->setDecodeMode(FULL_DECODE);
}

String SevenSeg::toString(float data){
    int neg=(int)(data<0);
    int dec=(int)log10(data);
    int dot=(int)(data-(float)(int)data!=0);
    return String(data, (unsigned char)(7+dot-dec-neg));
    //FIXME verifier si le dot fonctionne, sinon juste mettre 7 ou 8
}

void SevenSeg::display(float data) {
    String str= toString(data);
    int i=0; //digit que l'on est en train de modifier
    for(int s=0; s<8; ++s){
        if(s==str.length()){ //au cas où la chaine str fait moins de 8 carractères
            return;
        }
        switch(str[s]){ //pour chaque carractère de la chaine
            case '-':
                host->setRegister(allRegisters[i], MINUS); //on affiche moins
                break;
            case '.': //si c'est un point, on ne passe pas au digit suivant à la fin
                --i;  //de cette boucle de for (le point se met sur l'afficheur précédent)
                      //ce --i compense le ++i de la ligne 43
                break;
            default:
                byte disp=(byte)str[s];
                if(s<=str.length()-2) { //si le carractère suivant existe
                    if (str[s + 1] == '.') { //et si c'est un point
                        disp |= DOT; //alors on ajoute un point au digit actuel
                    }
                }
                host->setRegister(allRegisters[i], disp);
                break;
        }
        ++i; //on passe au digit suivant
    }

}


void smartTime(int y, int d,int h, int m, int s, bool negative, std::vector<char>* buff){
    bool noYear = true;
    if(y>0){
        buff->push_back('y');
        noYear = false;
    }
    while (y>0){
        buff->insert(buff->begin(), y%10);
        y/=10;
    }
    if(negative){
        buff->insert(buff->begin(), '-');
    }
    bool noDay = (d == 0) & noYear;
    if(buff->size() >= 6)
        return;
    if(!noYear || d / 100 > 0){
        buff->push_back(d/100);
    }
    if( !noYear || d/10 > 0){
        buff->push_back(d/10%10);
    }
    if(! noDay){
        buff->push_back(d%10);
        if(buff->size() == 8)
            return;
        buff->push_back('d');
        if(buff->size() == 8)
            return;
    }
    if(buff->size() >= 8)
        return;
    bool noHour = (h==0) & noDay;
    if(!noHour){
        buff->push_back(h%10);
        if(buff->size() == 8)
            return;
        buff->push_back('h');
        if(buff->size() == 8)
            return;
    }
    bool noMinute = (m==0) & noHour;
    if(buff->size() >= 7)
        return;
    if( !noHour || m/10 > 0){
        buff->push_back(m/10);
    }
    if(!noMinute ){
        buff->push_back(m%10);
        if(buff->size() == 8)
            return;
        buff->push_back('\'');
        if(buff->size() == 8)
            return;
    }
    if(buff->size() >= 7)
        return;
    if( !noMinute || s/10 > 0){
        buff->push_back(s/10);
    }
    buff->push_back(s%10);
    if(buff->size() == 8)
        return;
    buff->push_back('"');

}

void SevenSeg::printDate(long time) {
    int s = time % SECONDS_PER_MINUTE;
    time /= SECONDS_PER_MINUTE;
    int min = time % MINUTES_PER_HOUR;
    time /= MINUTES_PER_HOUR;
    int h = time %HOURS_PER_DAY;
    time /= HOURS_PER_DAY;
    int d = time % DAYS_PER_YEAR;
    int y = time /DAYS_PER_YEAR;

    std::vector<char> buff;
    smartTime(y,d,h,min,s, time<0, &buff);
    while (buff.size() < 8){
        buff.insert(buff.begin(), 'e');
    }
    uint8_t decMode = NO_DECODE;
    for (unsigned int i=0; i<8; i++){
        char c = buff[7-i];
        switch (c){
            case '"':
                host->setRegister(allRegisters[i], 0b00100010);
                break;
            case '\'':
                host->setRegister(allRegisters[i], 0b00000010);
                break;
            case 'h':
                host->setRegister(allRegisters[i], 0b00010111);
                break;
            case 'd':
                host->setRegister(allRegisters[i], 0b00111101);
                break;
            case 'y':
                host->setRegister(allRegisters[i], 0b00100111);
                break;
            case 'e':
                host->setRegister(allRegisters[i], 0x00);
                break;
            case '-':
                host->setRegister(allRegisters[i], 0b00000001);
                break;
            default:
                host->setRegister(allRegisters[i], c);
                decMode|= 1u << (i);
        }
    }
    host->setDecodeMode(decMode);
//TODO: ne pas forcément flush ici
    host->flush();
}