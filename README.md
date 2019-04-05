### Overview

This repository contains a simple demonstration JavaFX (OpenJFX 12) application, written
in Kotlin. 

The goal is to provide a working Kotlin/OpenJFX projects, with working builds that you 
can then modify to suite your needs.

All applications can be run from the command line, as long as the required software
is installed (see Install section).

The list of demo apps includes:

  * **Application 1** -  Basic JavaFX/Kotlin Application
  * **Application 2** -  Drag and Drop 
  
My blog on JavaFx with Kotlin at [https://thickclient.blog/](https://thickclient.blog/)

---
### Install
You will need to install:

* Java 11 SDK (Oracle or Open JDK)
* [OpenJFX 12](https://gluonhq.com/products/javafx/)  (OpenJFX 11 should work as well)

If you want to edit FXML and keep your sanity:

* [SceneBuilder for Java 11](https://gluonhq.com/products/scene-builder/)


#### Dependency Management and Build - Gradle

Gradle is bundled as part of the project - no need to install anything.

**Note:** Remember the path of the OpenJFX install. I will refer to this as the **JFX_INSTALL** dir.

---
### Configuration

*The projects does NOT need Gradle to be installed as this is included inside the project via a gradle wrapper*

##### Set JFX_INSTALL variable in build.gradle file

Find the section in the build.gradle:

    
    // BEGIN YOUR SETUP

    def JFX_INSTALL =  ** YOUR JFX_INSTALL DIR HERE ** 

    // END YOUR SETUP

and update the JFX_INSTALL value with your OpenJFX install directory.

---
### Build The Applications

Build the application with gradle:

    gradlew clean build


---
#### Application 1 - Basic JavaFx + Kotlin Application

* ListView
* TableView
* Tab Panes
* Sorted ObservableList Model
* Use of Boostrap.css stylesheet
* FXML based UI

Start the application with:

    gradlew runApp1
    

---
#### Application 2 - Drag and Drop

* Drag and Drop between listView controls
* Drag files from Desktop
* Custom Drag images 
* Sorted ObservableList Model
* Use of Stylesheet in code and in FXML
* FXML based UI

Start the application with:

    gradlew runApp2
    
