#!/bin/bash

export JAVA9_HOME=/home/taner/Development/jdk-9/
export JAVA9_BIN=/home/taner/Development/jdk-9/bin/

rm -rf modules
mkdir modules

rm -rf libs
mkdir libs

rm -rf bin
mkdir -p bin/com.level2
mkdir -p bin/com.level1
mkdir -p bin/com.app

function compileAndJar {
    $JAVA9_BIN/javac -p modules --add-modules ALL-MODULE-PATH  -cp libs/com.level2.jar:libs/com.level1.jar:libs/com.app.jar -d bin/$1 -s src $(find src/$1 -name "*.java")
    MAIN_CLASS=""
    if [ $# -eq 2 ]
    then
        MAIN_CLASS="--main-class=$2"

    fi
    PACKAGE="$JAVA9_BIN/jar  --create --file=libs/$1.jar $MAIN_CLASS -C bin/$1 ."
    eval $PACKAGE
    echo "$1 jar created..."
}

function compileAndModule {
    $JAVA9_BIN/javac -p modules  -cp libs/com.level2.jar:libs/com.level1.jar:libs/com.app.jar  -d bin/$1 -s src $(find src/$1 -name "*.java")
    MAIN_CLASS=""
    if [ $# -eq 2 ]
    then
        MAIN_CLASS="--main-class=$2"

    fi
    PACKAGE="$JAVA9_BIN/jar  --create --file=modules/$1@1.0.jar $MAIN_CLASS --module-version=1.0 -C bin/$1 ."
    eval $PACKAGE
    echo "$1 module created..."
}

compileAndModule "com.level2"
compileAndModule "com.level1"
compileAndJar "com.app" "com.app.Main"

$JAVA9_BIN/java -p modules --add-modules ALL-MODULE-PATH   -cp libs/com.level2.jar:libs/com.level1.jar:libs/com.app.jar   com.app.Main  #-m com.app.Main


