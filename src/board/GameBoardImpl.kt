package board

class GameBoardImpl<T> private constructor(width: Int) :
    GameBoard<T>, SquareBoard by SquareBoardImpl.createSquareBoard(width) {

    companion object {
        fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl<T>(width)
    }

    private val mapValue: MutableMap<Cell, T?> = initGameBoard()

    private fun initGameBoard(): MutableMap<Cell, T?> {
        val result = mutableMapOf<Cell, T?>()
        repeat(width) { i ->
            repeat(width) { j ->
                result[getCell(i + 1, j + 1)] = null
            }
        }
        return result
    }

    override fun get(cell: Cell): T? = mapValue[cell]

    override fun set(cell: Cell, value: T?) = mapValue.set(cell, value)

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        val destination = mutableListOf<Cell>()
        for ((key, value) in mapValue) {
            if (predicate(value))
                destination.add(key)
        }
        return destination
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        for ((key, value) in mapValue)
            if (predicate(value)) return key
        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        if (mapValue.isEmpty()) return false
        for ((_, value) in mapValue)
            if (predicate(value)) return true
        return false
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        if (mapValue.isEmpty()) return true
        for ((_, value) in mapValue)
            if (!predicate(value)) return false
        return true
    }
}