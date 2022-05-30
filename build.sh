#!/bin/sh

echo "> Compiling .java files..."
javac ./javaHotel/client/*.java -d ./bin/
echo "* Compiled client .java into .class files to ./bin/!"
javac ./javaHotel/server/*.java -d ./bin/
echo "* Compiled common .java files into .class files to ./bin/!"
javac ./javaHotel/common/*.java -d ./bin/
echo "* Compiled helpers into .class files to ./bin/!"
javac ./javaHotel/helpers/*.java -d ./bin/
echo "* Compiled server .java server files into .class files to ./bin/!"
cd ./bin/
echo "> Generating stub file with rmic..."
rmic javaHotel.server.HRImpl
echo "* Generated stub file with rmic!"
mv ./javaHotel/server/HRImpl_Stub.class ./javaHotel/client/
echo "* Moved stub file to ./bin/client/!"
#echo "> Generating jar files..."
#cd ../jar/
#jar cfm JavaHotelServer.jar ../javaHotel/manifests/serverManifest.mf ../bin/javaHotel/server/*
#jar cfm JavaHotelClient.jar ../javaHotel/manifests/clientManifest.mf ../bin/javaHotel/client/*
#echo "* Generated jar files in jar/!"
echo "* Build complete"
