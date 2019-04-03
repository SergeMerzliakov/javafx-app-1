package org.epistatic.app1.controller

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import org.epistatic.app1.model.SomeProperty

class Controller {

	@FXML lateinit var itemListView: ListView<String>
	@FXML lateinit var newItemField: TextField
	@FXML lateinit var tableView: TableView<SomeProperty>
	@FXML lateinit var propertyColumn: TableColumn<SomeProperty, String>
	@FXML lateinit var valueColumn: TableColumn<SomeProperty, String>

	private val listModel = FXCollections.observableArrayList<String>()
	private val tableModel = FXCollections.observableArrayList<SomeProperty>()

	@FXML
	fun initialize() {
		initializeListTab()
		initializeTableTab()
	}

	private fun initializeListTab() {
		// Preload some data into listModel, which is then loaded into list via observable property magic
		listModel.add("Lion")
		listModel.add("Bear")
		listModel.add("Giraffe")
		itemListView.items = listModel
	}

	private fun initializeTableTab() {
		// Preload some data
		tableModel.add(SomeProperty("color", "blue"))
		tableModel.add(SomeProperty("gender", "female"))
		tableModel.add(SomeProperty("age", "40"))
		tableModel.add(SomeProperty("city", "Sydney"))

		tableView.items = tableModel
		// map column cell value by simple property lookup by name on SomeProperty type
		propertyColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("name")
		valueColumn.cellValueFactory = PropertyValueFactory<SomeProperty, String>("value")
	}

	@FXML
	fun closeApplication() {
		val stage = itemListView.scene.window as Stage
		stage.close()
	}

	@FXML
	fun addItem() {
		val newItem = newItemField.text.trim()

		if (newItemField.text.trim().isEmpty())
			return

		listModel.add(newItem)

	}
}