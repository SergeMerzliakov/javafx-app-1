package org.epistatic.app4.controller

import javafx.scene.control.TableCell
import javafx.scene.control.TableColumn
import javafx.util.Callback
import org.epistatic.app4.model.DateItem
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Sets cell's text to be another formatted date
 */
class CustomDateCellFactory : Callback<TableColumn<DateItem, OffsetDateTime>, TableCell<DateItem, OffsetDateTime>> {

   companion object {
      private val customFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy *** HH:mm:ss")
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