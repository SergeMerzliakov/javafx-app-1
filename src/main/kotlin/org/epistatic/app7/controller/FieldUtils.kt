package org.epistatic.app7.controller

/*
 Clean dirty user input
 */
fun cleanText(s: String?): String {
	s?.apply {
		return s.trim()
	}
	return ""
}

/*
 Clean dirty user input
 */
fun cleanInteger(s: String?): Int {
	s?.apply {
		return try {
			s.trim().toInt()
		}
		catch (e: NumberFormatException) {
			0
		}
	}
	return 0
}
