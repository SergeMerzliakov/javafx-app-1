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

package org.epistatic.app1.controller

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import org.epistatic.app1.model.SomeProperty

/**
 * Initialized By JavaFX Loader when loading the FXML document.
 *
 * fx:controller attribute in the FXML document maps a "controller" class with an FXML document.
 *
 * A controller is a compiled class that implements the "code behind" the object hierarchy defined by the document.
 *
 * Note: This controller currently manages three Tab Panes, and ideally this should probably be split up
 * into a controller for each logical section of the application.
 */
class Controller {

	// List Tab Controls
	@FXML lateinit var itemListView: ListView<String>
	@FXML lateinit var newItemField: TextField

	// Table Tab Controls
	@FXML lateinit var tableView: TableView<SomeProperty>
	@FXML lateinit var propertyColumn: TableColumn<SomeProperty, String>
	@FXML lateinit var valueColumn: TableColumn<SomeProperty, String>

	// Field Tab Controls
	@FXML lateinit var schemeCombo: ComboBox<String>
	@FXML lateinit var hostField: TextField
	@FXML lateinit var portField: TextField
	@FXML lateinit var generateUrlButton: Button
	@FXML lateinit var urlLabel: Label


	val listModel = FXCollections.observableArrayList<String>()
	val tableModel = FXCollections.observableArrayList<SomeProperty>()

	/**
	 * Called after JavaFX initialized and document loaded
	 */
	@FXML
	fun initialize() {
		initializeListTab()
		initializeTableTab()
		initializeFieldTab()
	}

	/**
	 * Setup model for listView
	 */
	private fun initializeListTab() {
		// Preload some data into listModel, which is then loaded into list via observable property magic
		listModel.add("Lion")
		listModel.add("Bear")
		listModel.add("Giraffe")

		// items are sorted in view
		itemListView.items = listModel.sorted()
	}

	/**
	 * Setup model for tableview
	 */
	private fun initializeTableTab() {
		// Preload some data
		tableModel.add(SomeProperty("color", "blue"))
		tableModel.add(SomeProperty("gender", "female"))
		tableModel.add(SomeProperty("age", "40"))
		tableModel.add(SomeProperty("city", "Sydney"))

		// items are sorted on property name in view
		tableView.items = tableModel.sorted()

		// map column cell value by simple property lookup by name on SomeProperty type
		propertyColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("name")
		valueColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("value")
	}


	private fun initializeFieldTab(){
		// fixed list so just use Strings here
		schemeCombo.items.add("http")
		schemeCombo.items.add("https")
		schemeCombo.selectionModel.select(0)
	}


	@FXML
	fun closeApplication() {
		val stage = itemListView.scene.window as Stage
		stage.close()
	}

	/*
	 * Add item to listView
	 */
	@FXML
	fun addItem() {
		val newItem = newItemField.text.trim()

		if (newItemField.text.trim().isEmpty())
			return

		listModel.add(newItem)
	}

	/**
	 * Put the URL together in the urlLabel. No input validation
	 * here for the sake of simplicity
	 */
	@FXML
	fun generateUrlClicked() {
		val scheme = schemeCombo.selectionModel.selectedItem
		val host = hostField.text.trim()

		// Cheating here - no real integer validation -
		// just default to 80 if integer conversion fails
		val port = portField.text.trim().toIntOrNull() ?: 80

		urlLabel.text = "$scheme://$host:$port"
	}
}