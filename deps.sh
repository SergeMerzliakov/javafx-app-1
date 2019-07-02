#!/usr/bin/env bash
jdeps -s --module-path $JAVA_HOME/jmods:/Library/Java/javafx/12.0.1/jmods --no-recursive --class-path lib/* javafx-app-1-1.0.0.jar
