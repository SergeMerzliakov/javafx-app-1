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
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.stage.Stage

/**
 * Controller for app6 - Event Handler
 */
class ApplicationController {

   @FXML lateinit var closeButton: Button
   @FXML private lateinit var textFieldExtension: TextField
   @FXML private lateinit var textFieldWrapper: TextField
   @FXML private lateinit var colourCombo: ComboBox<String>

   val comboModel = FXCollections.observableArrayList<String>()

   @FXML
   fun initialize() {
      // method 1 - call extension function
      textFieldExtension.onFocusLost { showDialog("Focus Lost", "textField1 Lost Focus.\n[Called extension function - onFocusLost]") }

      // method 2 - call simple wrapper function
      EventWrapper.onFocusLost(textFieldWrapper) { showDialog("Focus Lost", "textField2 Lost Focus.\n[Called Wrapper - EventHandler.onFocusLost]") }

      comboModel.add("Blue")
      comboModel.add("Green")
      comboModel.add("Yellow")
      colourCombo.items = comboModel
      colourCombo.selectionModel.select(0)

      EventWrapper.onComboSelectionChanged(colourCombo) { cb -> showDialog("Selection Changed", "Colour is now ${cb.selectionModel.selectedItem}") }
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