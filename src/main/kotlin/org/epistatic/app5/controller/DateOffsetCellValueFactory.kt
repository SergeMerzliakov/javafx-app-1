package org.epistatic.app5.controller

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.scene.control.TableColumn
import javafx.util.Callback
import org.epistatic.app5.model.DateItem
import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * A Cell Value Factory which uses stateful data to alter the cell value
 */
class DateOffsetCellValueFactory(private val offsetSeconds: Long) : Callback<TableColumn.CellDataFeatures<DateItem, OffsetDateTime>, ObservableValue<OffsetDateTime>> {

   override fun call(p: TableColumn.CellDataFeatures<DateItem, OffsetDateTime>): ObservableValue<OffsetDateTime> {
      return if (p.value != null) {
         ReadOnlyObjectWrapper(p.value.date.plusSeconds(offsetSeconds))
      } else {
         // only objects have a modified attribute as far as I know now
         ReadOnlyObjectWrapper()
      }
   }
}