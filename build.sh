#!/bin/sh

echo "> Compiling .java files..."
javac ./src/*.java
echo "* Compiled .java files!"
echo "> Moving generated .class files to ./classes/ ..."
mv ./src/*.class ./classes/
echo "* Moved generated .class files to ./classes/!"
cd ./classes/
echo "> Generating stub file with rmic..."
rmic HRImpl
echo "* Generated stub file with rmic!"
echo "* Build complete"
