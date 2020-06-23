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

package org.epistatic.app3.controller

import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import org.epistatic.app3.event.FileAddedEvent
import org.epistatic.app3.event.FileSelectedEvent
import java.io.File
import java.nio.charset.Charset

private const val PREVIEW_BYTES = 8192

/**
 * Controller for app3/fileData.fxml - which displays the contents of file in a TextArea
 */
class FileDataController(eventBus: EventBus) : EventAwareController(eventBus) {

   // view
   @FXML lateinit var fileContentsArea: TextArea
   @FXML lateinit var fileContentsLabel: Label


   @Subscribe
   fun handleFileAdded(e: FileAddedEvent) {
      println("FileDataController processing FileAddedEvent")
      loadAndDisplayContents(e.file)
   }


   @Subscribe
   fun handleFileSelectionChanged(e: FileSelectedEvent) {
      println("FileDataController processing FileSelectedEvent")
      loadAndDisplayContents(e.file)
   }

   /**
    * Either load the whole file or a preview of it.
    */
   private fun loadAndDisplayContents(file: File) {
      if (file.length() < PREVIEW_BYTES) {
         fileContentsArea.text = String(file.readBytes(), Charset.defaultCharset())
         fileContentsLabel.text = "Full File Contents"
      } else {
         // preview first PREVIEW_BYTES only - could be a very large file!
         val stream = file.inputStream()
         val bytes = stream.readNBytes(PREVIEW_BYTES)
         fileContentsArea.text = String(bytes, Charset.defaultCharset())
         fileContentsLabel.text = "File Content Preview - First $PREVIEW_BYTES Bytes Only"
      }
   }
}