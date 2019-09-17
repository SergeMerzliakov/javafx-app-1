package org.epistatic.app5.controller

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import org.epistatic.app5.model.DateItem
import java.time.OffsetDateTime
import java.time.ZoneOffset

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
 * Controller for app5/app5.fxml. Manages the main UI and all the other controllers
 */
class ApplicationController {

   @FXML lateinit var exitButton: Button
   @FXML lateinit var dateView: TableView<DateItem>
   @FXML lateinit var labelColumn: TableColumn<DateItem, String>
   @FXML lateinit var dateStringColumn: TableColumn<DateItem, String>
   @FXML lateinit var date2Column: TableColumn<DateItem, OffsetDateTime>
   @FXML lateinit var date3Column: TableColumn<DateItem, OffsetDateTime>

   val model: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()

   @FXML
   fun initialize() {
      dateView.items = model
      labelColumn.cellValueFactory = PropertyValueFactory<DateItem, String>("label")
      dateStringColumn.cellValueFactory = PropertyValueFactory<DateItem, String>("date1")
      date2Column.cellValueFactory = PropertyValueFactory<DateItem, OffsetDateTime>("date2")
      date3Column.cellValueFactory = PropertyValueFactory<DateItem, OffsetDateTime>("date3")

      addItems()
   }

   @FXML
   fun closeApplication() {
      val stage = exitButton.scene.window as Stage
      stage.close()
   }

   /**
    * Add items to table
    */
   private fun addItems() {
      var item = DateItem("Green Tree", createDate(2018, 2, 3), createDate(2018, 4, 12), createDate(2018, 8, 21))
      model.add(item)
      item = DateItem("Red Car", createDate(2017, 5, 19), createDate(2017, 3, 15), createDate(2017, 12, 21))
      model.add(item)
      item = DateItem("Blue Ship", createDate(2016, 12, 19), createDate(2016, 6, 2), createDate(2017, 7, 28))
      model.add(item)
      item = DateItem("Yellow Moon", createDate(1990, 4, 4), createDate(1990, 1, 2), createDate(1990, 3, 11))
      model.add(item)
   }


   private fun createDate(year: Int, month: Int, day: Int): OffsetDateTime {
      return OffsetDateTime.of(year, month, day, 14, 30, 0, 0, ZoneOffset.UTC)
   }
}