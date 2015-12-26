//
//  main.cpp
//  TestXbee
//
//  Created by Arjun Passi on 5/4/15.
//  Copyright (c) 2015 Arjun Passi. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
#include <thread>
#include "USBDevice.h"

#define USB_PORT "/dev/tty.usbserial-A5025UHW"


void readStringFromXbee() {
  USBDevice usb(USB_PORT);
  char buf[255];

  while (true) {
    if ( usb.readString(&buf[0]) )
      std::cout << buf << std::endl;
  }
}

int main(int argc, const char * argv[]) {

  std::thread first (readStringFromXbee);

  while (true) { }

  return 0;
}
