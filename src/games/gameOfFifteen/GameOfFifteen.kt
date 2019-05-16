package games.gameOfFifteen

import board.Direction
import board.GameBoardImpl
import games.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
class GameOfFifteen private constructor(
    private val initializer: GameOfFifteenInitializer): Game {

    companion object {
        fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer): Game =
                GameOfFifteen(initializer)
    }

    private val board = GameBoardImpl.createGameBoard<Int?>(4)

    override fun initialize() {
        val shuffled = initializer.initialPermutation
        var k = 0
        repeat(board.width) {i->
            repeat(board.width) { j ->
                if (k < shuffled.size)
                    board[board.getCell(i+1, j+1)] = shuffled[k]
                k++
            }
        }
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean {
        val result = mutableListOf<Int>()
        repeat(board.width) {i->
            repeat(board.width) { j ->
                result.add(board[board.getCell(i+1, j+1)] ?: 16)
            }
        }
        return result == (1..16).toList()
    }

    override fun processMove(direction: Direction) {
        board.run {
            val blank = find { it == null }
            blank?.let {cell->
                cell.getNeighbour(direction.reversed())?.let {
                    board[cell] = board[it]
                    board[it] = null
                }
            }
        }
    }

    override fun get(i: Int, j: Int): Int? = board.run { get(getCell(i, j)) }
}