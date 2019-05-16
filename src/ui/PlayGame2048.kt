package ui

import games.game2048.Game2048
import ui.settings.Game2048Settings
import ui.settings.Window

fun main() {
    Window.playGame(Game2048.newGame2048(), Game2048Settings)
}