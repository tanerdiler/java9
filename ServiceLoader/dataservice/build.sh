#!/bin/bash

export JAVA9_HOME=/home/taner/Development/jdk-9/
export JAVA9_BIN=/home/taner/Development/jdk-9/bin/

rm -rf modules
mkdir modules

rm -rf bin
mkdir -p bin/data.service
mkdir -p bin/data.plc
mkdir -p bin/data.app

function compileAndPackage {
    $JAVA9_BIN/javac -mp modules -d bin/$1 -s src $(find src/$1 -name "*.java")
    MAIN_CLASS=""
    if [ $# -eq 2 ]
    then
        MAIN_CLASS="--main-class=$2"
        
    fi
    PACKAGE="$JAVA9_BIN/jar  --create --file=modules/$1@1.0.jar $MAIN_CLASS --module-version=1.0 -C bin/$1 ."
    eval $PACKAGE 
    echo "$1 module created..."
}

compileAndPackage "data.service"
compileAndPackage "data.plc"
compileAndPackage "data.app" "com.data.app.Main"

$JAVA9_BIN/java -mp modules -m data.app
