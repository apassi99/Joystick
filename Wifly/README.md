Setting up TCP/IP Server on Wifly
For more information checkout: 
http://ww1.microchip.com/downloads/en/DeviceDoc/50002230A.pdf

### set wlan ssid <string>

This command sets the SSID with which the module associates, where <string> is 1 to
32 characters (32 bytes).

### set ip address <address>

This command sets a secondary host IP address, where <address> is an IP address
in the form <value>.<value>.<value>.<value> with <value> being a number between 0
and 255. If the primary host IP is unreachable, the module attempts to reach the secondary
IP address (if set).

Example: set ip a 10.20.20.1

### set ip localport <value>

This command sets the local port number, where <value> is a decimal number representing
the port.

Default: 2000
Example: set ip localport 1025 // Sets the local port to 1025


### set ip dhcp <value>

This command enables/disables DHCP mode, where <value> is a decimal number. If you set this parameter, the module requests and sets the IP address, gateway, netmask, and DNS server upon association with an access point. Any previously set IP information is overwritten.

0 	Turns DHCP off. The module uses its stored static IP address.
1 	Turns DHCP on. The module attempts to obtain an IP address and gateway from the access point.
2	 Enables automatic IP, which is generally used on networks that do not have a DHCP server.
3 	Turns on DHCP cache mode. The module uses a previously set IP address if the lease is not expired (or the lease survives reboot).
4 	Enables DHCP server in soft AP mode. 
