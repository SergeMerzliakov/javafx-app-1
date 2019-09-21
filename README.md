### Overview

This repository contains simple demo JavaFX 11+ applications, written
in Kotlin. 

The goal is to provide Kotlin/OpenJFX demo applications, which work out of the box. 
If they don't work - let me know! I hate demos that don't work.

All applications can be run from the command line, as long as the required software
is installed (see Install section).

The list of demo apps includes:

  * **Application 1** -  Basic JavaFX/Kotlin Application
  * **Application 2** -  Drag and Drop
  * **Application 3** -  Nested Controllers, Event Based using Google EventBus
  * **Application 4** -  TableView Cell Value Factories vs Cell Factories
  
  
My blog on JavaFx with Kotlin at [https://thickclient.blog/](https://thickclient.blog/)

#### Philosophy
All the code samples are not meant to be production quality and I use the following guidelines:

* Enough unit tests to illustrate a point or technique
* Logging, exception and error handling is avoided, unless it’s specifically a focus of the sample
* Longer variable names to assist in clarity
* Shorter classes and methods to minimise TL;DR syndrome.
---
### Install
You will need to install:

* Java 11 or 12 SDK (Oracle or Open JDK)
* [OpenJFX 11,12 or 13](https://gluonhq.com/products/javafx/)

If you want to edit FXML and keep your sanity:

* [SceneBuilder for Java 11](https://gluonhq.com/products/scene-builder/)


#### Dependency Management and Build - Gradle

Gradle is bundled as part of the project - no need to install anything.

**Note:** Remember the path of the OpenJFX install. I will refer to this as the **JFX_INSTALL** dir.

---
### Configuration

*The projects does NOT need Gradle to be installed as this is included inside the project via a gradle wrapper*

##### Set JFX_VERSION and JFX_INSTALL variable Values in build.gradle file

Find the section in the build.gradle:

    
    // BEGIN YOUR SETUP
        def JFX_VERSION = "13" // or "12" or "11"
        def JFX_INSTALL =  ** YOUR JFX_INSTALL DIR HERE ** 
    // END YOUR SETUP

and update the JFX_INSTALL value with your OpenJFX install directory.

---
### Build The Applications

Build the applications with gradle:

    gradlew clean build


Run all the unit and integration tests

    gradlew test
    
---
#### Application 1 - Basic JavaFx + Kotlin Application

* Text Fields, Combo Boxes
* ListView
* TableView
* Tabs
* CSS Stylesheets
* FXML based UI
* TestFX tests

Start the application with:

    gradlew runApp1
    

---
#### Application 2 - Drag and Drop

* Drag and Drop between listView controls
* Drag files from Desktop
* Custom Drag images 
* CSS Stylesheets
* FXML based UI
* Drag and Drop TestFX tests

Start the application with:

    gradlew runApp2
    
    
#### Application 3 - Multiple Controller + FXML files. Event Based Communication

* Application with nested controllers/FXML
* Controllers assigned dynamically, allowing dependency injection and better testing
* Google EventBus used for publish-subscribe event communication between controllers
* FXML based UI

Start the application with:

    gradlew runApp3    


#### Application 4 - TableView Cell Factories vs Cell Value Factories

* Examples of different Cell Value and Cell Factories in Kotlin
* FXML based UI

Start the application with:

    gradlew runApp4

