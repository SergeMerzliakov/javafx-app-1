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

package org.epistatic.app5.controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.stage.Stage

/**
 * Controller for app5 - Custom Dialog Demo
 */
class ApplicationController {

   @FXML lateinit var exitButton: Button
   @FXML lateinit var nameLabel: Label

   @FXML
   fun initialize() {
   }

   @FXML
   fun rename() {
      val stage = exitButton.scene.window as Stage
      val dialog = SingleValueDialog(stage, "New Name", "Change Name", nameLabel.text)
      val result = dialog.showAndWait()
      if (result.ok) {
         nameLabel.text = result.data
      }
   }

   @FXML
   fun closeApplication() {
      val stage = exitButton.scene.window as Stage
      stage.close()
   }
}