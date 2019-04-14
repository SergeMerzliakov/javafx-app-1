package org.epistatic.app3.controller

import com.google.common.eventbus.EventBus
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
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
 * Controller for app3/app3.fxml
 */
class ApplicationController {

	@FXML lateinit var exitButton: Button
	@FXML lateinit var leftPane: AnchorPane
	@FXML lateinit var topRightPane: AnchorPane
	@FXML lateinit var bottomRightPane: AnchorPane

	private var eventBus = EventBus()
	private val fileListController = FileListController(eventBus)
	private val fileDataController = FileDataController(eventBus)
	private val filePropertiesController = FilePropertiesController(eventBus)

	@FXML
	fun initialize(){
		val fileListPane = fileListController.load()
		AnchorPane.setTopAnchor(fileListPane, 0.0)
		AnchorPane.setLeftAnchor(fileListPane, 0.0)
		AnchorPane.setBottomAnchor(fileListPane, 2.0)
		AnchorPane.setRightAnchor(fileListPane, 0.0)
		leftPane.children.add(fileListPane)

		val filePropertiesPane = filePropertiesController.load()
		AnchorPane.setTopAnchor(filePropertiesPane, 0.0)
		AnchorPane.setLeftAnchor(filePropertiesPane, 0.0)
		AnchorPane.setBottomAnchor(filePropertiesPane, 2.0)
		AnchorPane.setRightAnchor(filePropertiesPane, 5.0)
		topRightPane.children.add(filePropertiesPane)

		val fileDataPane = fileDataController.load()
		AnchorPane.setTopAnchor(fileDataPane, 0.0)
		AnchorPane.setLeftAnchor(fileDataPane, 0.0)
		AnchorPane.setBottomAnchor(fileDataPane, 2.0)
		AnchorPane.setRightAnchor(fileDataPane, 5.0)
		bottomRightPane.children.add(fileDataPane)
	}

	@FXML
	fun closeApplication() {
		val stage = exitButton.scene.window as Stage
		stage.close()
	}
}