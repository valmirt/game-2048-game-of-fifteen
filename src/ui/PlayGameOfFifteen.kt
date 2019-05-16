package ui

import games.gameOfFifteen.GameOfFifteen
import ui.settings.GameOfFifteenSettings
import ui.settings.Window

fun main () {
    Window.playGame(GameOfFifteen.newGameOfFifteen(), GameOfFifteenSettings)
}