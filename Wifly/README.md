Setting up TCP/IP Server on Wifly
For more information checkout: 
http://ww1.microchip.com/downloads/en/DeviceDoc/50002230A.pdf

# Example Wifly Setting
<img src="https://cloud.githubusercontent.com/assets/6227984/12005720/7ba106ea-ab82-11e5-8fb6-8e6f8d376dd1.png" width=250>
<img src="https://cloud.githubusercontent.com/assets/6227984/12005723/8c17e9e4-ab82-11e5-958e-7e34c6440ed6.png" width=200>

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

| Mode          |   Protocol    |
| ------------- | ------------- |
| 0 	          | Turns DHCP off. The module uses its stored static IP address. |
| 1 	          | Turns DHCP on. The module attempts to obtain an IP address and gateway from the access point. |
| 2	            | Enables automatic IP, which is generally used on networks that do not have a DHCP server. |
| 3 | Turns on DHCP cache mode. The module uses a previously set IP address if the lease is not expired (or the lease survives reboot).|
| 4 	          | Enables DHCP server in soft AP mode. |

### set sys autoconn <value>
 
This command sets the auto-connect timer in TCP mode, where <value> is a decimal number from 0 to 255 as shown in Table 2-5. Setting this parameter causes the module to connect to the stored remote host periodically as specified by <value>. 

| Value     |   Description |
| ----------| ------------- |
| 0 	      | Disable the auto-connect timer (default). |
| 1 	      | Connect to the stored remote host IMMEDIATELY upon power up or when waking from sleep. |
| 2 - 254	  | Connect to a stored remote host every <value> seconds. |
| 255       | Connect to a stored host IMMEDIATELY upon power up or when waking from sleep and go back to sleep IMMEDIATELY as soon as the TCP connection closes.|
