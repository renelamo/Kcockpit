//
// Created by rene on 14/06/19.
//

#ifndef STM32_SERIALMANAGER_H
#define STM32_SERIALMANAGER_H

#include <queue>

class SerialManager {
private:
    std::queue< std::vector<int> > buffer;
public:
    SerialManager();
    void add(std::vector<int> data);
    void send();
    void SASchangedHandler();
    void customChangedHandler();
    void stageHandler();
};


#endif //STM32_SERIALMANAGER_H