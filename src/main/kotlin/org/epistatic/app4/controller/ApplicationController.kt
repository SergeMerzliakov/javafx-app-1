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

package org.epistatic.app4.controller

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import javafx.stage.Stage
import org.epistatic.app4.model.DateItem
import java.time.OffsetDateTime
import java.time.ZoneOffset


/**
 * Controller for app4/app4.fxml
 */
class ApplicationController {

   @FXML lateinit var exitButton: Button
   @FXML lateinit var dateView: TableView<DateItem>
   @FXML lateinit var labelColumn: TableColumn<DateItem, String>
   @FXML lateinit var dateStringColumn: TableColumn<DateItem, String>
   @FXML lateinit var dateObjectColumn: TableColumn<DateItem, OffsetDateTime>
   @FXML lateinit var dateCustomColumn: TableColumn<DateItem, OffsetDateTime>
   @FXML lateinit var dateCustomColumn2: TableColumn<DateItem, OffsetDateTime>
   @FXML lateinit var dateLambdaColumn: TableColumn<DateItem, OffsetDateTime>
   
   val model: ObservableList<DateItem> = FXCollections.observableArrayList<DateItem>()

   @FXML
   fun initialize() {
      dateView.items = model
      labelColumn.cellValueFactory = PropertyValueFactory<DateItem, String>("label")

      // cannot do much with this - string limits choices
      dateStringColumn.cellValueFactory = PropertyValueFactory<DateItem, String>("date")

      dateObjectColumn.cellValueFactory = PropertyValueFactory<DateItem, OffsetDateTime>("date")
      dateObjectColumn.cellFactory = ModifiedISODateCellFactory()

      dateCustomColumn.cellValueFactory = DateCellValueFactory()
      dateCustomColumn.cellFactory = ModifiedISODateCellFactory()
      
      // Offset date by 10 seconds
      dateCustomColumn2.cellValueFactory = DateOffsetCellValueFactory(40)
      dateCustomColumn2.cellFactory = CustomDateCellFactory()

      // Use Lambda to create factories
      dateLambdaColumn.setCellValueFactory { cell: TableColumn.CellDataFeatures<DateItem, OffsetDateTime> -> ReadOnlyObjectWrapper(cell.value.date) }
      dateLambdaColumn.setCellFactory {
         object : TableCell<DateItem, OffsetDateTime>() {
            public override fun updateItem(dt: OffsetDateTime?, empty: Boolean) {
               super.updateItem(dt, empty)
               if (!empty)
                  this.text = "Lambda - $dt"
            }
         }
      }
      
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
      var item = DateItem("Green Tree", createDate(2018, 2, 3))
      model.add(item)
      item = DateItem("Red Car", createDate(2017, 5, 19))
      model.add(item)
      item = DateItem("Blue Ship", createDate(2016, 12, 19))
      model.add(item)
      item = DateItem("Yellow Moon", createDate(1990, 4, 4))
      model.add(item)
   }


   private fun createDate(year: Int, month: Int, day: Int): OffsetDateTime {
      return OffsetDateTime.of(year, month, day, 14, 30, 0, 0, ZoneOffset.UTC)
   }
}