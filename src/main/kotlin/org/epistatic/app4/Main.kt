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

package org.epistatic.app4

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.GridPane
import javafx.stage.Stage

/**
 * An Application which demonstrates cell/value factories
 **/
class Main : Application() {

   @Throws(Exception::class)
   override fun start(primaryStage: Stage) {
      val loader = FXMLLoader(javaClass.getResource("/app4/app4.fxml"))
      val root = loader.load<GridPane>()
      primaryStage.title = "TableView Cell Factories"
      primaryStage.scene = Scene(root, 750.0, 550.0)
      primaryStage.show()
   }

   companion object {
      @JvmStatic
      fun main(args: Array<String>) {
         launch(Main::class.java, *args)
      }
   }
}
