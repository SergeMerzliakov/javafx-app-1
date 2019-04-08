package org.epistatic.app2

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

import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.assertj.core.api.Assertions.assertThat
import org.epistatic.app2.controller.Controller
import org.epistatic.app2.model.Person
import org.junit.Ignore
import org.junit.Test
import org.testfx.framework.junit.ApplicationTest

/**
 * Use Junit TestFX to integration test the application UI, so
 * not unit tests per se.
 */
class App2IntegrationTest : ApplicationTest() {

	// Node Ids defined in the FXML file. Is a CSS Id selector
	// Use these to uniquely identify JavaFX controls for testing
	companion object {
		const val FRIEND_LIST_VIEW = "#friendListView"
		const val PARTY_LIST_VIEW = "#partyListView"
		const val FILE_LIST_VIEW = "#fileListView"
		const val CANCEL_BUTTON = "#cancelButton"

	}

	private lateinit var controller: Controller


	override fun start(stage: Stage?) {
		// We instantiate a loader directly so we can access the controller for testing
		val loader = FXMLLoader(javaClass.getResource("/app2/app2.fxml"))
		val root = loader.load<VBox>()
		controller = loader.getController()
		stage?.scene = Scene(root)
		stage?.show()
	}


	@Test
	fun shouldDragSingleFriendToPartyList() {
		// drag a single friend to party list
		dragFriendToParty("Andrea")

		val partyList = lookup(PARTY_LIST_VIEW).query<ListView<Person>>()
		assertThat(partyList.items).hasSize(1)
		assertThat(partyList.items[0]).hasFieldOrPropertyWithValue("firstName", "Andrea")
	}


	@Test
	fun shouldDragAllFriendsToPartyList() {
		// drag everyone to party
		val friendCount = controller.friends.size

		// avoid ConcurrentModificationException - copy names into separate list
		val firstNames = controller.friends.map { it.firstName }
		firstNames.map { dragFriendToParty(it) }

		//check model
		assertThat(controller.friends).isEmpty()
		assertThat(controller.partyGuests).hasSize(friendCount)

		//check view
		val friendView = lookup(FRIEND_LIST_VIEW).query<ListView<Person>>()
		assertThat(friendView.items).isEmpty()
		val partyView = lookup(PARTY_LIST_VIEW).query<ListView<Person>>()
		assertThat(partyView.items).hasSize(friendCount)
	}


	private fun dragFriendToParty(firstName: String) {
		val friend = getListViewRowByFirstName(FRIEND_LIST_VIEW, firstName)
		val d = drag(friend)
		d.dropTo(PARTY_LIST_VIEW)
	}

	/**
	 * Helper function to get a row from a ListView
	 *
	 * Type T is the type of the ListView data model.
	 *
	 */
	private fun <T> getListViewRow(viewId: String, row: Int): ListCell<T> {
		val listView = lookup(viewId).query<ListView<T>>()
		return from(listView).lookup(".list-cell").nth(row).query()
	}

	/**
	 * Helper function to get a row containing the given text from a ListView
	 * Type T is the type of the ListView data model.
	 *
	 * TODO Not ideal, but I am all out of ideas right now
	 */
	private fun getListViewRowByFirstName(viewId: String, textToFind: String): ListCell<Person>? {
		val listView = lookup(viewId).query<ListView<Person>>()
		val cells = from(listView).lookup(".list-cell").queryAll<ListCell<Person>>()
		return cells.find { it.item.firstName.contains(textToFind) }
	}

}