/*
 * Copyright 2019 Serge Merzliakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.epistatic.app4.controller

import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.util.Callback
import org.epistatic.app4.model.DateItem
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Sets cell's text to be a formatted date
 */
class ModifiedISODateCellFactory : Callback<TableColumn<DateItem, OffsetDateTime>, TableCell<DateItem, OffsetDateTime>> {

   companion object {
      private val customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd => HH:mm:ss")
   }

   override fun call(col: TableColumn<DateItem, OffsetDateTime>?): TableCell<DateItem, OffsetDateTime> {
      return object : TableCell<DateItem, OffsetDateTime>() {
         public override fun updateItem(dt: OffsetDateTime?, empty: Boolean) {
            super.updateItem(dt, empty)
            if (!empty)
               this.text = customFormat.format(dt)
         }
      }
   }
}