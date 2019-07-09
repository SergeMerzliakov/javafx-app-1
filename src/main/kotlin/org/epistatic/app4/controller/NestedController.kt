package org.epistatic.app4.controller

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.stage.Stage

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 **/

/**
 * Controller for app3/app3.fxml. Manages the main UI and all the other controllers
 */
class NestedController {

   @FXML lateinit var exitButton: Button
   @FXML lateinit var child1Controller: Child1Controller
   
   @FXML
   fun initialize() {
   }
   

   @FXML
   fun closeApplication() {
      val stage = exitButton.scene.window as Stage
      stage.close()
      println("App 4 shutdown ${child1Controller.name}")

   }
}