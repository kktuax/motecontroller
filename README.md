motecontroller
==============

Control your applications with a WiiMote
---------------------------------------------------

If you have a Wii Remote (a "wiimote"), you can use it to control your favourite linux applications. This application maps a clicked button  on your wiimote to a command on the operating system. 

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
    wget --trust-server-name http://downloads.sourceforge.net/project/motej/motej/motej%200.9/motej-library-0.9-bin.zip -O /tmp/motej-library.zip && cd /tmp/ && unzip motej-library.zip
    sudo mvn install:install-file -DgroupId=motej -DartifactId=motej-library -Dversion=0.9 -Dpackaging=jar -Dfile=/tmp/motej-library-0.9/motej-library-0.9.jar

# Install last version of motecontroller

    wget https://raw.github.com/kktuax/motecontroller/master/install.sh -O /tmp/motecontroller-install.sh
    chmod +x /tmp/motecontroller-install.sh
    sudo /tmp/motecontroller-install.sh

# Define the controlled applications

Every controlled application must be defined in a text file in the directory
    
    ~/.motecontroller/applications
    
For instance, if we want to control the [clementine](http://www.clementine-player.org/) audio player, we would define a file in ~/.motecontroller/applications/clementine.xml with this content:

    <applicationController>
      <applicationName>clementine</applicationName>
      <applicationCommands>
        <init>clementine</init>
        <one>clementine --play-pause</one>
        <two>clementine --stop</two>
        <up>clementine --volume-up</up>
        <down>clementine --volume-down</down>
        <right>clementine --next</right>
        <left>clementine --previous</left>
      </applicationCommands>
    </applicationController>

We can define as much applications as we want. For example we could create a second file ~/.motecontroller/applications/smplayer.xml to control the [smplayer](http://smplayer.sourceforge.net/) video player with this content:

    <applicationController>
      <applicationName>smplayer</applicationName>
      <applicationCommands>
        <init>smplayer</init>
        <one>smplayer -send-action play_or_pause</one>
        <two>smplayer -send-action fullscreen</two>
        <up>smplayer -send-action pl_prev</up>
        <down>smplayer -send-action pl_next</down>
        <right>smplayer -send-action forward2</right>
        <left>smplayer -send-action rewind2</left>
      </applicationCommands>
    </applicationController>

In a simillar way, we can define a response to a combination of buttons. So, let's say we want to shutdown the machine when we hit A + B + A. We would define a file in ~/.motecontroller/combos/shutdown.xml looking like:

    <comboController>
      <name>shutdown</name>
      <comboElements>A, B, A</comboElements>
      <command>sudo shutdown -h now</command>
    </comboController>

# Using motecontroller

After the installation, we can launch the motecontroller by running:

    motecontroller
    
When the application starts it scans the configuration directory searching for controlled applications. If you change something in this directory, you will need to restart motecontroller for it to refresh the changes.

We must synchronize our wiimote with the motecontroller. Yo can perform this opperation by:

 * Clicking on the Synchronize button
 * Push buttons 1 & 2 in your wiimote.
 * If synchronization was successful the syncronize button will now have the text "Synchronized OK" and the first led in your wiimote will be on.
 
Now you can select with your mouse what application you want to control and start playing with your wiimote :)

