/*
 * Copyright 2020 Serge Merzliakov
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

package org.epistatic.app6.controller

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.Stage

/**
 * Controller for app6 - Event Handlers
 */
class ApplicationController {

   @FXML lateinit var closeButton: Button
   @FXML private lateinit var textFieldExtension: TextField
   @FXML private lateinit var textFieldWrapper: TextField
   @FXML private lateinit var colourCombo: ComboBox<String>
   @FXML private lateinit var animalListView: ListView<String>
   @FXML private lateinit var animalLabel: Label
   
   private val comboModel = FXCollections.observableArrayList<String>()
   private val listModel = FXCollections.observableArrayList<String>()

   @FXML
   fun initialize() {
      initializeFields()
      initializeCombo()
      initializeListView()
   }

   private fun initializeFields() {
      // method 1 - call extension function
      textFieldExtension.onFocusLost { f->  showDialog("Focus Lost", "${textFieldExtension.id} Lost Focus.\n[Called extension function - onFocusLost]") }

      // method 2 - call simple wrapper function
      EventWrapper.onFocusLost(textFieldWrapper) { f -> showDialog("Focus Lost", "${textFieldWrapper.id} Lost Focus.\n[Called Wrapper - EventHandler.onFocusLost]") }
   }
   
   private fun initializeCombo() {
      comboModel.add("Blue")
      comboModel.add("Green")
      comboModel.add("Yellow")
      colourCombo.items = comboModel.sorted()
      colourCombo.selectionModel.select(0)

      EventWrapper.onSelectionChanged(colourCombo) { cb -> showDialog("Selection Changed", "Colour is now ${cb.selectionModel.selectedItem}") }
   }

   private fun initializeListView() {
      listModel.add("Tiger")
      listModel.add("Whale")
      listModel.add("Chicken")
      animalListView.items = listModel.sorted()

      EventWrapper.onSelectionChanged(animalListView) { view -> animalLabel.text = "Selected Animal is a ${view.selectionModel.selectedItem}" }
   }

   @FXML
   fun closeApplication() {
      val stage = closeButton.scene.window as Stage
      stage.close()
   }

   /**
    * Show a simple message when event handled
    */
   private fun showDialog(title: String, message: String) {
      val alert = Alert(Alert.AlertType.INFORMATION)
      alert.title = title
      alert.headerText = null
      alert.contentText = message
      alert.showAndWait()
   }
}