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

import javafx.scene.control.Control

/**
 * Add onFocusLost method to all JavaFx Controls
 */
fun Control.onFocusLost(handler: (f: Control) -> Unit) {
   focusedProperty()
         .addListener { _, _, hasFocus ->
            if (!hasFocus)
               handler(this)
         }
}
