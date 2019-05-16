package ui.settings

import java.awt.Color

abstract class GameSettings(val name: String, val backgroundColor: Color) {
    abstract fun getBackgroundColor(value: Int): Color
    abstract fun getForegroundColor(value: Int): Color
}