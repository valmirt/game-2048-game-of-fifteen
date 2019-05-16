package games.game2048

import board.Direction
import board.GameBoardImpl
import games.Game

/*
 * The goal is implement the game 2048 https://en.wikipedia.org/wiki/2048_(video_game).
 * Implement the utility methods below.
 *
 * After implementing it you can try to play the game running 'PlayGame2048'.
 */
class Game2048 private constructor(private val initializer: Game2048Initializer<Int>): Game {

    companion object {
        fun newGame2048(initializer: Game2048Initializer<Int> = Game2048InitializerImpl): Game =
                Game2048(initializer)
    }

    private val board = GameBoardImpl.createGameBoard<Int?>(4)

    override fun initialize() {
        repeat(2) {
            board.addNewValue(initializer)
        }
    }

    override fun canMove() = board.any { it == null }

    override fun hasWon() = board.any { it == 2048 }

    override fun processMove(direction: Direction) {
        if (board.moveValues(direction)) {
            board.addNewValue(initializer)
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}