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
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.assertj.core.api.Assertions.assertThat
import org.epistatic.app1.controller.Controller
import org.epistatic.test.utils.BetterApplicationTest
import org.junit.Test

// Node Ids defined in the FXML file. Is a CSS Id selector
// Use these to uniquely identify JavaFX controls for testing
private const val FIELD_DEMO_TAB = "#fieldTab"
private const val SCHEME_COMBO = "#schemeCombo"
private const val HOST_FIELD = "#hostField"
private const val PORT_FIELD = "#portField"
private const val GENERATE_URL_BUTTON = "#generateUrlButton"
private const val URL_LABEL = "#urlLabel"

/**
 * Use Junit TestFX to integration test the application UI, so
 * not unit tests per se.
 */
class FieldTabIntegrationTest : BetterApplicationTest() {

   private lateinit var controller: Controller

   override fun start(stage: Stage?) {
      // We instantiate a loader directly so we can access the controller for testing
      val loader = FXMLLoader(javaClass.getResource("/app1/app1.fxml"))
      val root = loader.load<VBox>()
      controller = loader.getController()
      stage?.scene = Scene(root)
      stage?.show()
   }

   @Test
   fun shouldSelectHttpsSchemeCorrectly() {
      val scheme = "https"
      clickOn(FIELD_DEMO_TAB)
      //ensure "List Demo" tab has focus
      clickOn(SCHEME_COMBO)
      clickOn(scheme)
      type(KeyCode.TAB)

      clickOn(GENERATE_URL_BUTTON)

      val urlLabel = lookup(URL_LABEL).query<Label>()
      assertThat(urlLabel.text).startsWith(scheme)
   }


   @Test
   fun shouldSetHostCorrectly() {
      val scheme = "http"
      val host = "www.fubar.com"

      clickOn(FIELD_DEMO_TAB)
      //ensure "List Demo" tab has focus
      clickOn(SCHEME_COMBO)
      clickOn(scheme)
      type(KeyCode.TAB)

      clickOn(HOST_FIELD)
      write(host)
      type(KeyCode.TAB)

      clickOn(GENERATE_URL_BUTTON)

      val urlLabel = lookup(URL_LABEL).query<Label>()
      assertThat(urlLabel.text).contains(host)
   }


   @Test
   fun shouldSetPortCorrectly() {
      val scheme = "https"
      val host = "www.cheapwebsites.com"
      val port = "8081"
      clickOn(FIELD_DEMO_TAB)
      //ensure "List Demo" tab has focus
      clickOn(SCHEME_COMBO)
      clickOn(scheme)
      type(KeyCode.TAB)

      clickOn(HOST_FIELD)
      write(host)
      type(KeyCode.TAB)

      clickOn(PORT_FIELD)
      write(port)
      type(KeyCode.TAB)

      clickOn(GENERATE_URL_BUTTON)

      val urlLabel = lookup(URL_LABEL).query<Label>()

      assertThat(urlLabel.text).isEqualTo("$scheme://$host:$port")
      assertThat(urlLabel.text).endsWith(port)
   }
}