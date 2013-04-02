#!/bin/bash

cd /tmp
# we need to install motej in the local maven repository as it is not in the central repository
wget --trust-server-name http://downloads.sourceforge.net/project/motej/motej/motej%200.9/motej-library-0.9-bin.zip -O /tmp/motej-library.zip && unzip motej-library.zip
sudo mvn install:install-file -DgroupId=motej -DartifactId=motej-library -Dversion=0.9 -Dpackaging=jar -Dfile=/tmp/motej-library-0.9/motej-library-0.9.jar
# motecontroller package
wget https://github.com/kktuax/motecontroller/archive/master.zip
unzip master.zip
cd motecontroller-master
mvn package
# motecontroller install
sudo mkdir -p /opt/motecontroller
sudo mv target/motecontroller-0.0.1-SNAPSHOT.jar /opt/motecontroller/motecontroller.jar
echo "java -jar /opt/motecontroller/motecontroller.jar" | sudo tee /opt/motecontroller/motecontroller > /dev/null
sudo chmod +x /opt/motecontroller/motecontroller
sudo ln -s /opt/motecontroller/motecontroller /usr/local/bin/motecontroller
