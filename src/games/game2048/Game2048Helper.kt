package games.game2048

import board.Cell
import board.Direction
import board.Direction.*
import board.GameBoard

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> =
    filterNotNull().fold(mutableListOf()) {acc, e ->
        if (acc.isNotEmpty() && e == acc.last()) {
            acc.remove(e)
            acc.add(merge(e))
        } else acc.add(e)

        acc
    }

// Add a new value produced by 'initializer' to a specified cell in a board.
fun GameBoard<Int?>.addNewValue(initializer: Game2048Initializer<Int>) {
    val pair = initializer.nextValue(this)

    pair?.let { (cell, value) ->
        this[cell] = value
    }
}


/*
 * Update the values stored in a board.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValuesInRowOrColumn(rowOrColumn: List<Cell>): Boolean {
    val oldValues = rowOrColumn.map { this[it] }
    val newValues = oldValues.moveAndMergeEqual { it * 2 }

    //Updating list of values
    for (i in rowOrColumn.indices) {
        if (i < newValues.size)
            this[rowOrColumn[i]] = newValues[i]
        else this[rowOrColumn[i]] = null
    }

    /*
     * Return false because the oldValues are null and
     * the newValues are empty they are different but
     * content the same logic
     */
    if (newValues.isEmpty()) return false

    return oldValues != newValues
}


/*
 * Update the values stored in a board,
 * Use the 'moveValuesInRowOrColumn' function above.
 * Return 'true' if the values were moved and 'false' otherwise.
 */
fun GameBoard<Int?>.moveValues(direction: Direction): Boolean {
    val rm = mutableListOf<Boolean>()
    repeat(width) {
        when (direction) {
            UP -> rm.add(moveValuesInRowOrColumn(this.getColumn(1..width, it+1)))
            DOWN -> rm.add(moveValuesInRowOrColumn(this.getColumn(width downTo 1, it+1)))
            RIGHT -> rm.add(moveValuesInRowOrColumn(this.getRow(it+1, width downTo 1)))
            LEFT -> rm.add(moveValuesInRowOrColumn(this.getRow(it+1, 1..width)))
        }
    }

    return rm.any { it }
}