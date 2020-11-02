/*
 * Copyright 2020 Serge Merzliakov
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

package org.epistatic.app7.controller

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.Stage
import org.epistatic.app7.model.*


private const val MY_SQL = "MySQL"
private const val ORACLE = "Oracle"
private const val POSTGRES = "Postgres"
private const val SQL_SERVER = "SQL Server"

/**
 * Controller for app7 - Focus Demo
 */
class ApplicationController {

   @FXML private lateinit var vendorChoice: ChoiceBox<String>
   @FXML private lateinit var hostText: TextField
   @FXML private lateinit var portText: TextField
   @FXML private lateinit var databaseText: TextField
   @FXML private lateinit var jdbcUrlText: TextField

   @FXML
   fun initialize() {
      vendorChoice.items.add(MY_SQL)
      vendorChoice.items.add(ORACLE)
      vendorChoice.items.add(POSTGRES)
      vendorChoice.items.add(SQL_SERVER)
      vendorChoice.selectionModel.select(2)

      // ensure enter key also shifts focus to next control in tab order
      val enterKeyHandler = FocusHandler()

      hostText.onKeyPressed = enterKeyHandler
      portText.onKeyPressed = enterKeyHandler
      databaseText.onKeyPressed = enterKeyHandler

      // any time focus is lost, rebuild JDBC URL
      focusLostHandler(hostText) { generateJDBCUrl() }
      focusLostHandler(portText) { generateJDBCUrl() }
      focusLostHandler(databaseText) { generateJDBCUrl() }
      selectionChangedHandler(vendorChoice) { generateJDBCUrl() }

      //generate a URL from initial value of fields
      generateJDBCUrl()
   }

   /**
    * Update JDBC URL on screen
    */
   private fun generateJDBCUrl() {
      val jdbcUrl = url(vendorChoice.value)
      jdbcUrl?.apply {
         jdbcUrlText.text = jdbcUrl.url
      }
   }

   private fun url(name: String?): JdbcUrl? {
      val db = DatabaseParameters(cleanText(hostText.text), cleanInteger(portText.text), "", cleanText(databaseText.text))
      return when (name) {
         MY_SQL -> MySQLJdbcUrl(db)
         ORACLE -> OracleJdbcUrl(db)
         POSTGRES -> PostgresJdbcUrl(db)
         SQL_SERVER -> SQLServerJdbcUrl(db)
         else       -> null
      }
   }

   @FXML
   fun closeApplication(){
      val stage = hostText.scene.window as Stage
      stage.close()
   }
}
