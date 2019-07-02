#!/usr/bin/env bash
jdeps -s --module-path $JAVA_HOME/lib:/Library/Java/javafx/12.0.1/lib --no-recursive --class-path installBuild/lib/* installBuild/javafx-app-1-1.0.0.jar
