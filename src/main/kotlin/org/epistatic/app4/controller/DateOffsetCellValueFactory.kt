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

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.scene.control.TableColumn
import javafx.util.Callback
import org.epistatic.app4.model.DateItem
import java.time.OffsetDateTime

/**
 * A Cell Value Factory which uses stateful data to alter the cells date value by a number of days
 */
class DateOffsetCellValueFactory(private val offsetDays: Long) : Callback<TableColumn.CellDataFeatures<DateItem, OffsetDateTime>, ObservableValue<OffsetDateTime>> {

   override fun call(p: TableColumn.CellDataFeatures<DateItem, OffsetDateTime>): ObservableValue<OffsetDateTime> {
      return if (p.value != null) {
         ReadOnlyObjectWrapper(p.value.date.plusDays(offsetDays))
      } else {
         ReadOnlyObjectWrapper()
      }
   }
}