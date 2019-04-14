package org.epistatic.app3.controller

import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.layout.Pane
import org.epistatic.app3.event.FileAddedEvent
import org.epistatic.app3.event.FileSelectedEvent
import java.io.File
import java.nio.charset.Charset

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
 * Controller for app3/fileData.fxml
 */
class FileDataController(eventBus: EventBus) : EventAwareController(eventBus) {

	@FXML lateinit var fileContentsArea: TextArea

	@FXML lateinit var fileContentsLabel: Label

	fun load(): Pane {
		val loader = FXMLLoader(javaClass.getResource("/app3/fileData.fxml"))
		loader.setController(this)
		return loader.load<Pane>()
	}

	@Subscribe
	fun handleFileAdded(e: FileAddedEvent) {
		println("FileDataController processing FileAddedEvent")

		loadAndDisplayContents(e.file)
	}

	@Subscribe
	fun handleFileSelectionChanged(e: FileSelectedEvent) {
		println("FileDataController processing FileSelectedEvent")

		loadAndDisplayContents(e.file)
	}

	private fun loadAndDisplayContents(file: File) {
		// manage large files
		if (file.length() < 8192) {
			fileContentsArea.text = String(file.readBytes(), Charset.defaultCharset())
			fileContentsLabel.text = "Full File Contents"
		}
		else {
			// preview first 8K only - could be multiple gigabytess
			val stream = file.inputStream()
			val bytes = stream.readNBytes(8192)
			fileContentsArea.text = String(bytes, Charset.defaultCharset())
			fileContentsLabel.text = "File Content Preview - First 8K Only"
		}
	}
}