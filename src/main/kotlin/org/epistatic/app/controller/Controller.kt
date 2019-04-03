package org.epistatic.app.controller

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.stage.Stage

class Controller {

	@FXML lateinit var itemListView: ListView<String>
	@FXML lateinit var newItemField: TextField

	val model = FXCollections.observableArrayList<String>()

	@FXML
	fun initialize() {
		// Preload some data into model, which is then loaded into list via observable property magic
		model.add("Lion")
		model.add("Bear")
		model.add("Giraffe")
		itemListView.items = model
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

		model.add(newItem)

	}
}