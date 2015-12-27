# Introduction

This is a Joystick project which provides an android application that connects with Wifly chip 
( https://www.sparkfun.com/products/10822?gclid=CMHCvNeH-ckCFYEYHwodpD4NJgO )

The Wifly chip hosts a TCP/IP server which listens to incomming connections. For information regarding how to setup the server and host a network on the Wifly chip checkout the Wifly/README.md. The android application can connect with the Wifly chip by connecting the boradcasting network and providing the server IP and port. The Wifly chip is configured as such that the IP address is provided by the chip and doesn't need to be hardcoded.

# Why is it useful?

This can be used to easily connect mobile devices with any embedded application over a wireless network. The Wifly chip can sit on a rover/quad. Data can easily be transfered for controlling your embedded devices or for monitoring sensor values etc.

# Higher Level Architecture

<img src="https://cloud.githubusercontent.com/assets/6227984/12008386/247444a8-ac05-11e5-9130-2af55692b979.png" width ="500">

## Wifly Chip

<img src="https://cloud.githubusercontent.com/assets/6227984/12005675/9d7b0d58-ab80-11e5-879d-0eef649bb30a.jpg" width="200">

## Android Code screen shots

<img src="https://cloud.githubusercontent.com/assets/6227984/12005630/db9bb98c-ab7d-11e5-9bf2-e3ab1ec04e66.jpg" width="300">
<img src="https://cloud.githubusercontent.com/assets/6227984/12005627/d710eb3a-ab7d-11e5-9918-30da5da1e377.jpg" width="300">

