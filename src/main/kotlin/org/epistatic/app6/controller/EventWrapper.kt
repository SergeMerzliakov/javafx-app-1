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
package org.epistatic.app6.controller

import javafx.scene.control.ComboBox
import javafx.scene.control.Control
import javafx.scene.control.ListView

/**
 * Wrapper functions around event listeners
 */
object EventWrapper {

   /*
    Invoke given function on loss of focus
   */
   fun <T : Control> onFocusLost(f: T, handler: (f: T) -> Unit) {
      f.focusedProperty()
            .addListener { _, _, hasFocus ->
               if (!hasFocus)
                  handler(f)
            }
   }

   /**
    * Invoke given function on change of selection
    *
    * SelectionModel is not in any shared base class (ie. no class like SelectableControl). 
    * All relevant controls just implement it as a member. So we have a function 
    * here specifically for ComboBoxes
    */
   fun onSelectionChanged(f: ComboBox<*>, handler: (f: ComboBox<*>) -> Unit) {
      f.selectionModel.selectedIndexProperty()
            .addListener { _, old, new ->
               if (old != new)
                  handler(f)
            }
   }

   /**
    * Invoke given function on change of selection
    *
    * SelectionModel is not in any shared base class (ie. no class like SelectableControl).
    * All relevant controls just implement it as a member. So we have a function
    * here specifically for ListViews
    */
   fun onSelectionChanged(f: ListView<*>, handler: (f: ListView<*>) -> Unit) {
      f.selectionModel.selectedIndexProperty()
            .addListener { _, old, new ->
               if (old != new)
                  handler(f)
            }
   }
}