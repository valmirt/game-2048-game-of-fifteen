package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 *
 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */

fun isEven(permutation: List<Int>): Boolean {
    var count = 0

    var i = 0
    var j: Int
    while (i < permutation.size) {
        j = i+1
        while(j < permutation.size) {
            if (permutation[i] > permutation[j]) count++
            j++
        }
        i++
    }

    return count % 2 == 0
}