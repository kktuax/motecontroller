motecontroller
==============

Control your applications with a WiiMote
---------------------------------------------------

If you have a Wii Remote (a "wiimote"), you can use it to control your 
favourite linux applications. This application maps a clicked button 
on your wiimote to a command on the operating system. 

Technologies used
-----------------

 * [motej](http://motej.sourceforge.net/) a java wiimote wrapper
 * [bluecove](http://bluecove.org/) for Bluetooth support  

Hardware checklist
-----------------

 * A Wii Remote
 * A computer with Bluetooth (or a Bluetooth dongle)

How-To
----------

# Install the dependencies

    sudo apt-get install openjdk-7-jdk maven2 bluez-utils libbluetooth-dev

# Install last version of motecontroller

    wget https://raw.github.com/kktuax/motecontroller/master/install.sh -O /tmp/motecontroller-install.sh
    chmod +x /tmp/motecontroller-install.sh
    sudo /tmp/motecontroller-install.sh
