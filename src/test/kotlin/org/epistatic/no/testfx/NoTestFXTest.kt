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
package org.epistatic.no.testfx

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import org.assertj.core.api.Assertions.assertThat
import org.junit.BeforeClass
import org.junit.Test


/**
 * Runs JavaFX unit tests without TestFx
 */
class NoTestFXTest {

	companion object {
		@BeforeClass
		@JvmStatic
		fun startJavaFXRuntime() {
			Platform.startup {}
		}
	}


	/**
	 * More common case, and generally safer, as it's often hard to tell
	 * what can or cannot be run on calling thread (and not JavaFX
	 * thread)
	 */
	@Test
	fun fxThreadTest() {
		// when
		val ap = AnchorPane()

		FXBlock(Runnable {
			val stage = Stage()
			ap.prefHeight = 200.0
			ap.prefWidth = 240.0

			val label = Label("Empty")
			ap.children.add(label)

			val button = Button("OK")
			button.onAction = EventHandler { label.text = "button clicked" }
			ap.children.add(button)

			stage.scene = Scene(ap)
			stage.show()
		}).run()

		// then - some basic tests
		FXBlock(Runnable {
			val button = ap.children.find { it is Button } as Button
			button.fire()

			val label = ap.children.find { it is Label } as Label
			assertThat(label.text).isEqualTo("button clicked")
		}).run()
	}

	/**
	 * Does not required to be run from JavaFX application thread
	 */
	@Test
	fun basicTestOnMainThread() {
		// when
		val ap = AnchorPane()
		val label = Label("Empty")
		ap.children.add(label)
		val button = Button("OK")
		ap.children.add(button)
		AnchorPane.setTopAnchor(label, 10.0)

		// then
		val button2 = ap.children.find { it is Button } as Button
		assertThat(button2.text).isEqualTo("OK")

		val label2 = ap.children.find { it is Label } as Label
		assertThat(label2.text).isEqualTo("Empty")
	}
}
