package games.game2048

import board.Cell
import board.GameBoard
import kotlin.random.Random

object Game2048InitializerImpl: Game2048Initializer<Int> {
    private fun generateRandomStartValue(): Int =
        if (Random.nextInt(10) == 9) 4 else 2

    /*
     * Generate a random value and a random cell among free cells
     * that given value should be added to.
     * The value should be 2 for 90% cases, and 4 for the rest of the cases.
     */
    override fun nextValue(board: GameBoard<Int?>): Pair<Cell, Int>? {
        val emptyCells = board.filter { it == null }.toList()

        return if (emptyCells.isEmpty()) null
        else emptyCells[Random.nextInt(emptyCells.size)] to generateRandomStartValue()
    }
}