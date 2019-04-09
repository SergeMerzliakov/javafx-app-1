package org.epistatic.test.utils

import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.TableCell
import javafx.scene.control.TableView
import org.epistatic.app1.model.SomeProperty
import org.testfx.framework.junit.ApplicationTest

/**
 * Add a few utility methods for accessing table and list view elements
 */
open class BetterApplicationTest : ApplicationTest() {

   /**
    * Helper function to get a row from a TableView
    *
    *  Creating a generic version of this function ==> fun <T:Any> getTableViewRow(viewId: String, row: Int): T
    *
    *  is very tricky and I did not quite succeed. Ah, so close...
    *
    *  see: https://stackoverflow.com/questions/43477903/instantiating-a-generic-type-in-kotlin
    */
   fun getTableViewRow(viewId: String, row: Int): SomeProperty {
      val tableView = lookup(viewId).query<TableView<SomeProperty>>()
      val cell1: TableCell<SomeProperty, String> = from(tableView).lookup(".table-cell").nth(row * 2).query()
      val cell2: TableCell<SomeProperty, String> = from(tableView).lookup(".table-cell").nth(row * 2 + 1).query()

      return SomeProperty(cell1.text, cell2.text)
   }

   /**
    * Helper function to get a row from a ListView
    *
    * Type T is the type of the ListView data model.
    */
   fun getListViewRow(viewId: String, row: Int): ListCell<String> {
      val listView = lookup(viewId).query<ListView<String>>()
      return from(listView).lookup(".list-cell").nth(row).query()
   }

   /**
    * Helper function to get a row starting the given text from a ListView
    * Type T is the type of the ListView data model.
    * 
    *  This method is a bit dodgy, but illustrates how to access listView row data
    *
    */
   fun <T> getListViewRowByFirstName(viewId: String, textToFind: String): ListCell<T>? {
      val listView = lookup(viewId).query<ListView<T>>()
      val cells = from(listView).lookup(".list-cell").queryAll<ListCell<T>>()
      // assumes type T has a toString method starting with first name!
      return cells.find { it.item.toString().toLowerCase().startsWith(textToFind.toLowerCase()) }
   }
}