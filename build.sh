#!/bin/sh

echo "* Build of Java Hotel Started"
cd ./src/
echo "* Moved into ./src/"
echo "> Compiling .java files...(0/4)"
javac ./javaHotel/client/*.java -d ./bin/
echo "* Compiled client .java into .class files to ./bin/! (1/4)"
javac ./javaHotel/server/*.java -d ./bin/
echo "* Compiled common .java files into .class files to ./bin/! (2/4)"
javac ./javaHotel/common/*.java -d ./bin/
echo "* Compiled helpers into .class files to ./bin/! (3/4)"
javac ./javaHotel/helpers/*.java -d ./bin/
echo "* Compiled server .java server files into .class files to ./bin/! (4/4)"
cd ../bin/
echo "* Moved into ../bin/"
echo "> Generating stub file with rmic...(0/1)"
rmic javaHotel.server.HRImpl
echo "* Generated stub file with rmic! (1/1)"
mv ./javaHotel/server/HRImpl_Stub.class ./javaHotel/client/
echo "* Moved stub file to ./bin/client/!"
echo "> Generating jar files...(0/2)"
jar cvfm JavaHotelServer.jar ../src/javaHotel/manifests/serverManifest.mf javaHotel/server/* javaHotel/common/* javaHotel/helpers/*
echo "* Generated Server jar file! (1/2)"
jar cvfm JavaHotelClient.jar ../src/javaHotel/manifests/clientManifest.mf javaHotel/client/* javaHotel/common/* javaHotel/helpers/*
echo "* Generated Client jar file! (2/2)"
mv *.jar ../jar/
echo "* Moved jar files in jar/!"
echo "* Build complete"
