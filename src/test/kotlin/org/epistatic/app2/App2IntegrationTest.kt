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
import org.epistatic.test.utils.BetterApplicationTest
import org.junit.Ignore
import org.junit.Test
import org.testfx.framework.junit.ApplicationTest

/**
 * Use Junit TestFX to integration test the application UI, so
 * not unit tests per se.
 */
class App2IntegrationTest : BetterApplicationTest() {

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
      val friendCount = controller.friends.size
      
		// drag a single friend to party list
		dragFriendToParty("Andrea")

		val partyList = lookup(PARTY_LIST_VIEW).query<ListView<Person>>()

      // check model
      assertThat(controller.friends).hasSize(friendCount - 1)
      assertThat(controller.partyGuests).hasSize(1)
      
      // check view
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

		// check model
		assertThat(controller.friends).isEmpty()
		assertThat(controller.partyGuests).hasSize(friendCount)

		// check view
		val friendView = lookup(FRIEND_LIST_VIEW).query<ListView<Person>>()
		assertThat(friendView.items).isEmpty()
		val partyView = lookup(PARTY_LIST_VIEW).query<ListView<Person>>()
		assertThat(partyView.items).hasSize(friendCount)
	}


   @Test
   fun shouldDragSingleFriendsOutOfParty() {
      val friend = "Andrea"
      val friendCount = controller.friends.size
      val friendView = lookup(FRIEND_LIST_VIEW).query<ListView<Person>>()
      val partyView = lookup(PARTY_LIST_VIEW).query<ListView<Person>>()
      
      dragFriendToParty(friend)
  
      // check model and view after dragging from friendView -> partyView
      assertThat(controller.friends).hasSize(friendCount - 1)
      assertThat(controller.partyGuests).hasSize(1)

      assertThat(friendView.items).hasSize(friendCount - 1)
      assertThat(partyView.items).hasSize(1)

      dragFriendOutOfParty(friend)

      // check model and view after dragging from partyView -> friendView
      assertThat(controller.friends).hasSize(friendCount)
      assertThat(controller.partyGuests).isEmpty()

      assertThat(friendView.items).hasSize(friendCount)
      assertThat(partyView.items).isEmpty()
   }
   
   
	private fun dragFriendToParty(firstName: String) {
		val friend = getListViewRowByFirstName<Person>(FRIEND_LIST_VIEW, firstName)
		val d = drag(friend)
		d.dropTo(PARTY_LIST_VIEW)
	}

   private fun dragFriendOutOfParty(firstName: String) {
      val friend = getListViewRowByFirstName<Person>(PARTY_LIST_VIEW, firstName)
      val d = drag(friend)
      d.dropTo(FRIEND_LIST_VIEW)
   }
}