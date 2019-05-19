//
// Created by rene on 17/12/18.
//

#include "SevenSeg.h"

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
        if(s==str.length()){ //si la chaine str fait moins de 8 carractères
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