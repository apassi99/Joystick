Setting up TCP/IP Server on Wifly 
For more information checkout: 
http://ww1.microchip.com/downloads/en/DeviceDoc/50002230A.pdf

set wlan ssid <string>

This command sets the SSID with which the module associates, where <string> is 1 to
32 characters (32 bytes).i

set ip address <address>

This command sets a secondary host IP address, where <address> is an IP address
in the form <value>.<value>.<value>.<value> with <value> being a number between 0
and 255. If the primary host IP is unreachable, the module attempts to reach the secondary
IP address (if set).

Example: set ip a 10.20.20.1
