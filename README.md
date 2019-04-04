### Overview

This repository contains a simple demonstration JavaFX (OpenJFX 12) application, written
in Kotlin. 

The goal is to provide a working Kotlin/OpenJFX project, with a working build that you 
can then modify to suite your needs.

The application can be run from the command line, as long as the required software
is installed.

### Requirements

1) Java 11 SDK
2) OpenJFX 12 (11 should work as well)
3) SceneBuilder for Java 11 (optional)

After you install all of the above, you will need to remember the path of the OpenJFX 
install. I will refer this as the **JFX_INSTALL** dir.


### Setup

##### Set JFX_INSTALL variable in build.gradle file

Find the section in the build.gradle:

    
    // BEGIN YOUR SETUP

    def JFX_INSTALL =  ** YOUR JFX_INSTALL DIR HERE ** 

    // END YOUR SETUP

and overwrite your OpenJFX install directory. On my mac I prefer to put in /Library/Java, 
right next to my Java SDKs.

### Start the Application


Build the application

    gradlew clean build

Run the command:

    gradlew runApp1
    

which should start up the application.
