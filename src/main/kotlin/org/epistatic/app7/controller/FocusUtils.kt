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

import javafx.event.EventHandler
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Control
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent


/*
  Invoke given function on loss of focus
 */
fun <T : Control> focusLostHandler(f: T, handler: (f: T) -> Unit) {
	f.focusedProperty()
			.addListener { _, _, hasFocus ->
				if (!hasFocus)
					handler(f)
			}
}


/**
 * SelectionModel is not in any shared base class - all relevant controls just
 * have it as a member. Simpler to have a function for each control rather than a generic
 * function.
 */
fun selectionChangedHandler(f: ChoiceBox<*>, handler: (f: ChoiceBox<*>) -> Unit) {
	f.selectionModel.selectedItemProperty()
			.addListener { _, old, new ->
				if (old != new)
					handler(f)
			}
}

/**
 * Force a focus lost event on enter key as well as tab
 */
class FocusHandler : EventHandler<KeyEvent> {
	override fun handle(e: KeyEvent?) {
		if (e?.code === KeyCode.ENTER) {
			val ctrl = e.source as Control
			// Tab order is determined by order of the children inside the parent.
			// On hitting enter we shift focus to next child in order
			val kids = ctrl.parent.childrenUnmodifiable
			val nextFocus = kids.indexOf(ctrl) + 1
			if (nextFocus < kids.size) {
				kids[nextFocus].requestFocus()
			}
		}
	}
}
