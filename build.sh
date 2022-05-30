#!/bin/sh

echo "> Compiling .java files..."
javac ./javaHotel/client/*.java -d ./classes/
echo "* Compiled client .java into .class files to ./classes/!"
javac ./javaHotel/server/*.java -d ./classes/
echo "* Compiled server .java server files into .class files to ./classes/!"
cd ./classes/
echo "> Generating stub file with rmic..."
rmic javaHotel.server.HRImpl
echo "* Generated stub file with rmic!"
mv ./javaHotel/server/HRImpl_Stub.class ./javaHotel/client/
echo "* Moved stub file to ./classes/client/!"
echo "* Build complete"
