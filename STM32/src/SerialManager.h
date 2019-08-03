//
// Created by rene on 14/06/19.
//

#ifndef STM32_SERIALMANAGER_H
#define STM32_SERIALMANAGER_H

#include <queue>

class SerialManager {
private:
    std::queue<const std::vector<int> *> buffer;
public:
    SerialManager();
    void add(const std::vector<int> *data);
    void send();
};


#endif //STM32_SERIALMANAGER_H
