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

package org.epistatic.app1

import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.assertj.core.api.Assertions.assertThat
import org.epistatic.app1.controller.Controller
import org.epistatic.test.utils.BetterApplicationTest
import org.junit.Test

// Node Ids defined in the FXML file. Is a CSS Id selector
// Use these to uniquely identify JavaFX controls for testing
private const val TABLE_DEMO_TAB = "#basicTableTab"
private const val PROPERTY_TABLE_VIEW = "#propertyTableView"

/**
 * Use Junit TestFX to integration test the application UI, so
 * not unit tests per se.
 *
 * This tests does not really do anything. Just showing how to access
 * table views.
 */
class TableTabIntegrationTest : BetterApplicationTest() {

   private lateinit var controller: Controller


   override fun start(stage: Stage?) {
      // we instantiate a loader directly so we can access the controller for testing
      val loader = FXMLLoader(javaClass.getResource("/app1/app1.fxml"))
      val root = loader.load<VBox>()
      controller = loader.getController()

      stage?.scene = Scene(root)
      stage?.show()
   }


   /**
    * Not really a good test - just shows how to access table views
    */
   @Test
   fun shouldCheckTableDemoPropertyExists() {
      //ensure "List Demo" tab has focus
      clickOn(TABLE_DEMO_TAB)

      val ageProperty = getTableViewRow(PROPERTY_TABLE_VIEW, 0)

      // Check that the property exists and has the correct values
      assertThat(ageProperty.name).isEqualTo("age")
      assertThat(ageProperty.value).isEqualTo("40")
   }

}