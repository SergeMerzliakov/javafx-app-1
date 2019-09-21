package org.epistatic.app5.controller

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ObservableValue
import javafx.scene.control.TableColumn
import javafx.util.Callback
import org.epistatic.app5.model.DateItem
import java.time.OffsetDateTime

/**
 * Explicitly sets the cell value to the objects date field. Same functionality as:
 *     PropertyValueFactory<DateItem, OffsetDateTime>("date")
 */
class DateCellValueFactory : Callback<TableColumn.CellDataFeatures<DateItem, OffsetDateTime>, ObservableValue<OffsetDateTime>> {

   override fun call(p: TableColumn.CellDataFeatures<DateItem, OffsetDateTime>): ObservableValue<OffsetDateTime> {
      return if (p.value != null) {
         ReadOnlyObjectWrapper(p.value.date)
      } else {
         ReadOnlyObjectWrapper()
      }
   }
}