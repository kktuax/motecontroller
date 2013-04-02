#!/bin/bash

cd /tmp
wget https://github.com/kktuax/motecontroller/archive/master.zip
unzip master.zip
cd motecontroller-master
mvn package
sudo mkdir -p /opt/motecontroller
sudo mv target/motecontroller-0.0.1-SNAPSHOT.jar /opt/motecontroller/motecontroller.jar
echo "java -jar /opt/motecontroller/motecontroller.jar" | sudo tee /opt/motecontroller/motecontroller > /dev/null
sudo chmod +x /opt/motecontroller/motecontroller
sudo ln -s /opt/motecontroller/motecontroller /usr/local/bin/motecontroller
