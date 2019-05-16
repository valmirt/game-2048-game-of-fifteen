package board

data class Cell(val i: Int, val j: Int) {
    override fun toString()= "($i, $j)"
}