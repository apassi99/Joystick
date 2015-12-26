//
//  USBDevice.cpp
//  TestXbee
//
//  Created by Arjun Passi on 5/5/15.
//  Copyright (c) 2015 Arjun Passi. All rights reserved.
//

#include "USBDevice.h"

#include <iostream>
#include <fcntl.h>
#include <termios.h>
#include <errno.h>
#include <string>


USBDevice::USBDevice() { }

USBDevice::USBDevice(std::string newPort, int newBaud) {
    baud = newBaud;
    port = std::string(newPort);
    setupUSB(newPort.c_str(), newBaud); 
}

void USBDevice::setupUSB(const char* newPort, int newBaud) {
    int USB = open( newPort, O_RDWR | O_NONBLOCK | O_NOCTTY );
    struct termios tty;
    struct termios tty_old;
    memset (&tty, 0, sizeof tty);
    
    /* Error Handling */
    if ( tcgetattr ( USB, &tty ) != 0 )
    {
        std::cout << "Error " << errno << " from tcgetattr: " << strerror(errno) << std::endl;
        return;
    }
    
    /* Save old tty parameters */
    tty_old = tty;
    
    /* Set Baud Rate */
    cfsetospeed (&tty, (speed_t)B115200);
    cfsetispeed (&tty, (speed_t)B115200); 
    
    /* Setting other Port Stuff */
    tty.c_cflag     &=  ~PARENB;
    tty.c_cflag     &=  ~CSTOPB;
    tty.c_cflag     &=  ~CSIZE;
    tty.c_cflag     |=  CS8;
    
    tty.c_cflag     &=  ~CRTSCTS;
    tty.c_cc[VMIN]      =   1;
    tty.c_cc[VTIME]     =   5;
    tty.c_cflag     |=  CREAD | CLOCAL;
    
    /* Make raw */
    cfmakeraw(&tty);
    
    /* Flush Port, then applies attributes */
    tcflush( USB, TCIFLUSH );
    if ( tcsetattr ( USB, TCSANOW, &tty ) != 0)
    {
        std::cout << "Error " << errno << " from tcsetattr" << std::endl;
    }
    
    fd = USB;
}

USBDevice::~USBDevice() {
    close(fd);
}

int USBDevice::readByte(char* buff) {
    return readBytes(buff, 1);
}

int USBDevice::writeByte(char* buff) {
    return writeBytes(buff, 1);
}

int USBDevice::readBytes(char* buff, int numBytes) {
    return read(fd, buff, numBytes);
}

int USBDevice::writeBytes(char* buff, int numBytes) {
    return write(fd, buff, numBytes);
}

int USBDevice::readString(char* buff, char EOL, int MAX) {
    int buffPtr = 0;
    bool done = false;
    while(!done) {
        if ( readByte(buff + buffPtr) < 0 )
            continue;

	if(buff[buffPtr] == EOL || buffPtr == MAX-1) {
            buff[buffPtr] = '\0';
            done = true;
        }
        else
            buffPtr++;
    }
    
    return buffPtr;
}
