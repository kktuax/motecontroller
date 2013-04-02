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

    sudo apt-get install bluez-utils libbluetooth-dev maven2

# Get the last version of master branch

    cd ~
    wget https://github.com/kktuax/motecontroller/archive/master.zip
    unzip master.zip
    
# Create the java package

    cd ~/motecontroller-master
    mvn package
    
    
