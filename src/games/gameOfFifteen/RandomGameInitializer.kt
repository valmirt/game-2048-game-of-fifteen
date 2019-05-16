package games.gameOfFifteen

object RandomGameInitializer : GameOfFifteenInitializer {

    override val initialPermutation by lazy {
        val temp= (1..15).shuffled()

        if (!isEven(temp)) temp.swapValues()

        temp
    }

    private fun <T> List<T>.swapValues(index1: Int = 0, index2: Int = 1): List<T> {
        val result = this.toMutableList()
        val aux = result[index1]
        result[index1] = result[index2]
        result[index2] = aux
        return result
    }
}