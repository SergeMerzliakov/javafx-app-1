#!/usr/bin/env bash
export JAVA_HOME=$1
export JFX_INSTALL=$2
export JAR_FILE=$3
$JAVA_HOME/bin/jdeps -s --module-path $JAVA_HOME/lib:$JFX_INSTALL/lib --no-recursive --class-path installBuild/lib/* $JAR_FILE
