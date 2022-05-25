#!/bin/sh

echo "> Running rmiregisty"
rmiregistry &
echo "* rmiregisty up and running!"
cd ./classes/
echo "> Running server program"
java HRServer &
echo "* HRServer up and running!"
echo "> Running client program"
java HRClient
