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

package org.epistatic.app2

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * A Basic Drag and Drop Application demonstrating
 *
 *   1) Dragging between Controls
 *   2) Dragging Items from the Desktop
 *   3) Automatic Sorting of Items in Lists
 *   4) Observable Lists for Managing Model-View updates
 */
class Main : Application() {

	@Throws(Exception::class)
	override fun start(primaryStage: Stage) {
		val root = FXMLLoader.load<Parent>(javaClass.getResource("/app2/app2.fxml"))
		primaryStage.title = "Drag and Drop"
		primaryStage.scene = Scene(root, 650.0, 550.0)
		primaryStage.show()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			launch(Main::class.java, *args)
		}
	}
}
