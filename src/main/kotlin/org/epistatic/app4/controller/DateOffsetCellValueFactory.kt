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