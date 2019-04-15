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

### What's Next ?

Some of things I am thinking of, in no particular order:
* More advanced CSS Styling
* TableColumn CellValueFactories for complex objects
* Using asynchronous events to store application UI state (if you are sick of constantly opening and resizing dialogs)
* TornadoFX
  

