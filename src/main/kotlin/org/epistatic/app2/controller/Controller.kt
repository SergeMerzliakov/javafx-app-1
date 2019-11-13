/*
 * Copyright 2019 Serge Merzliakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.epistatic.app2.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.input.ClipboardContent
import javafx.scene.input.Dragboard
import javafx.scene.input.TransferMode
import javafx.stage.Stage
import org.epistatic.app2.model.Person
import java.io.File

/**
 * Initialized By JavaFX Loader when loading the FXML document.
 *
 * fx:controller attribute in the FXML document maps a "controller" class with an FXML document.
 *
 * A controller is a compiled class that implements the "code behind" the object hierarchy defined by the document.
 */
class Controller {

   companion object {
      val happy = Image(Controller::class.java.getResourceAsStream("/app2/happy.png"))
      val sad = Image(Controller::class.java.getResourceAsStream("/app2/sad.png"))
   }

   @FXML lateinit var friendListView: ListView<Person>
   @FXML lateinit var partyListView: ListView<Person>
   @FXML lateinit var fileListView: ListView<File>

   // model
   val friends = FXCollections.observableArrayList<Person>()
   val partyGuests = FXCollections.observableArrayList<Person>()
   val fileList = FXCollections.observableArrayList<File>()

   @FXML
   fun closeApp() {
      val stage = friendListView.scene.window as Stage
      stage.close()
   }

   /**
    * Called after JavaFX initialized and document loaded
    */
   @FXML
   fun initialize() {

      friends.add(Person("John", "Smith"))
      friends.add(Person("Van", "Nuyen"))
      friends.add(Person("Jill", "Vandalay"))
      friends.add(Person("Andrea", "Mueller"))
      friends.add(Person("Boris", "Prokopenko"))
      friends.add(Person("Anna", "Tam"))

      // Ensure the lists are always sorted
      friendListView.items = friends.sorted()
      partyListView.items = partyGuests.sorted()
      fileListView.items = fileList.sorted()

      setupDragAndDrop()
      initPlaceHolders()
   }

   /**
    * Shown when lists are empty
    */
   private fun initPlaceHolders() {
      val filePlaceHolder = Label("Drag Files Here")
      filePlaceHolder.styleClass.add("file-placeholder")
      fileListView.placeholder = filePlaceHolder

      val partyPlaceHolder = Label("Drag Guests Here")
      partyPlaceHolder.styleClass.add("general-placeholder")
      partyListView.placeholder = partyPlaceHolder

      val friendPlaceholder = Label("Everyone is at the Party!")
      friendPlaceholder.styleClass.add("general-placeholder")
      friendListView.placeholder = friendPlaceholder
   }

   /**
    * Register all the Drag and Drop event handlers
    */
   private fun setupDragAndDrop() {

      friendListView.onDragDetected = EventHandler { event ->
         val db = friendListView.startDragAndDrop(TransferMode.MOVE)
         addSelectedPersonToDragboard(friendListView, db)
         db.dragView = happy
         event.consume()
      }

      partyListView.onDragDetected = EventHandler { event ->
         val db = partyListView.startDragAndDrop(TransferMode.MOVE)
         addSelectedPersonToDragboard(partyListView, db)
         db.dragView = sad
         event.consume()
      }

      // DragHelper.registerAsDragSource()
      friendListView.onDragOver = EventHandler { event ->
         event.acceptTransferModes(TransferMode.MOVE)
         event.consume()
      }

      // accept references
      partyListView.onDragOver = EventHandler { event ->
         event.acceptTransferModes(TransferMode.MOVE)
         event.consume()
      }

      friendListView.onDragDropped = EventHandler { event ->
         val ejectedGuest = getSelectedPersonFromDragboard(partyGuests, event.dragboard)
         System.out.println("Kicked ${ejectedGuest} out of the party!")
         friends.add(ejectedGuest)
         partyGuests.remove(ejectedGuest)
         event.isDropCompleted = true
         event.consume()
      }

      partyListView.onDragDropped = EventHandler { event ->
         val newGuest = getSelectedPersonFromDragboard(friends, event.dragboard)
         System.out.println("Invited ${newGuest} to the party")
         partyGuests.add(newGuest)
         friends.remove(newGuest)
         event.isDropCompleted = true
         event.consume()
      }

      // accept references
      fileListView.onDragOver = EventHandler { event ->
         event.acceptTransferModes(TransferMode.LINK)
         event.consume()
      }

      fileListView.onDragDropped = EventHandler { event ->
         if (event.gestureSource == null && event.acceptedTransferMode == TransferMode.LINK) {
            // FROM OUTSIDE THIS APP
            val files = event.dragboard.files
            System.out.println("Got ${files.size} files")
            files.map { fileList.add(File(it.canonicalPath)) }
         }
         event.isDropCompleted = true
         event.consume()
      }
   }

   private fun getSelectedPersonFromDragboard(sourceList: ObservableList<Person>, db: Dragboard): Person {
      val personID = db.string.toInt()
      return sourceList.find { it.id == personID } ?: throw RuntimeException("Person Not Found in Source List After Drag")
   }

   /**
    * Helper method to put ID of selected person into Dragboard content
    */
   private fun addSelectedPersonToDragboard(listView: ListView<Person>, db: Dragboard) {
      val content = ClipboardContent()
      content.putString(getSelectedPersonId(listView))
      db.setContent(content)
   }

   private fun getSelectedPersonId(listView: ListView<Person>): String {
      if (listView.selectionModel.isEmpty)
         throw RuntimeException("No Person selected")
      val item = listView.selectionModel.selectedItem
      return item.id.toString()
   }
}