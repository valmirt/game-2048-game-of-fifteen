package games.game2048

import board.Cell
import board.GameBoard

interface Game2048Initializer<T> {
    //Specifies the cell and the value that should be added to this cell.
    fun nextValue(board: GameBoard<T?>): Pair<Cell, T>?
}