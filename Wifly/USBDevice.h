//
//  serialDevice.h
//  TestXbee
//
//  Created by Arjun Passi on 5/5/15.
//  Copyright (c) 2015 Arjun Passi. All rights reserved.
//

#ifndef WIFLY_USBDevice_h
#define WIFLY_USBDevice_h

#include <string>


class USBDevice {
public:
    static const int DEFAULT_BAUD = 115200;
    
    USBDevice(std::string newPort, int newBaud = USBDevice::DEFAULT_BAUD);
    ~USBDevice();
    
    int readByte(char*);
    int writeByte(char*);
    int readBytes(char*, int);
    int writeBytes(char*, int);
    
    int readString(char*, char EOL = '\0', int MAX = 255);
    
private:
    USBDevice();
    void setupUSB(const char*, int);
    int fd;
    int baud;
    std::string port;
};


#endif
