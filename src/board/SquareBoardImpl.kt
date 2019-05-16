package board

import board.Direction.*
import java.lang.IllegalArgumentException

class SquareBoardImpl private constructor(private val w: Int): SquareBoard {

    companion object {
        fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
    }

    override val width: Int
        get() = w

    private val board = createBoard()

    private fun createBoard(): List<Cell> {
        val response = mutableListOf<Cell>()

        repeat(width) {i->
            repeat(width) {j->
                response.add(Cell(i+1, j+1))
            }
        }

        return response
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? =
        board.find { it.i == i && it.j == j }

    override fun getCell(i: Int, j: Int): Cell =
        board.find { it.i == i && it.j == j }
            ?: throw IllegalArgumentException("Incorrect values to i and/or j")

    override fun getAllCells(): Collection<Cell> = board

    override fun getRow(i: Int, jRange: IntProgression): List<Cell>  {
        return if (jRange.first < jRange.last) board.filter { it.i == i && it.j in jRange }
        else board.filter { it.i == i && it.j in jRange }.sortedByDescending { it.j }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell>  {
        return if (iRange.first < iRange.last) board.filter { it.i in iRange && it.j == j }
        else board.filter { it.i in iRange && it.j == j }.sortedByDescending { it.i }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            UP -> board.find { it.i == i-1 && it.j == j }
            DOWN -> board.find { it.i == i+1 && it.j == j }
            LEFT -> board.find { it.i == i && it.j == j-1 }
            RIGHT -> board.find { it.i == i && it.j == j+1 }
        }
    }
}