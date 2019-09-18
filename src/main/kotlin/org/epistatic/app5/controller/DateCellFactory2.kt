package org.epistatic.app5.controller

import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.util.Callback
import org.epistatic.app5.model.DateItem
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class DateCellFactory2 : Callback<TableColumn<DateItem, OffsetDateTime>, TableCell<DateItem, OffsetDateTime>> {

   companion object {
      private val customFormat = DateTimeFormatter.ofPattern("yy-MMM-dd *** HH:mm:ss")
   }

   override fun call(col: TableColumn<DateItem, OffsetDateTime>?): TableCell<DateItem, OffsetDateTime> {
      return object : TableCell<DateItem, OffsetDateTime>() {
         public override fun updateItem(dt: OffsetDateTime?, empty: Boolean) {
            super.updateItem(dt, empty)
            if (!empty)
               this.text = customFormat.format(dt).replace(".", "") // hack - remove unexpected period in month names
         }
      }
   }
}