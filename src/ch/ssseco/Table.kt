package ch.ssseco

class Table {

    private var table: Array<Array<Char>>

    constructor() {
        table = getEmptyTable()
    }

    fun getCell(y: Int, x: Int): Char {
        return table[y][x]
    }

    fun setCell(y: Int, x: Int, value: Char) {
        table[y][x] = value
    }

    fun printTable() {
        println("\tA\tB\tC\n")

        for (i in table.indices) {
            val row = table[i]
            println(i.toString() + "\t" + row[0] + "\t" + row[1] + "\t" + row[2])
        }
    }

    fun checkForWin(character: Char): Boolean {
        // Check for horizontal win
        if (horizontalWin(character))
            return true


        // Check for vertical win
        if (verticalWin(character))
            return true

        // Check for diagonal win
        if (diagonalWin(character, false))
            return true


        // Check for opposite diagonal win
        if (diagonalWin(character, true))
            return true

        return false
    }

    fun checkForEmptySposts(): Boolean {
        var emptySpots = false
        for (row in table) {
            for (cell in row) {
                if (cell == Table.EMPTY) {
                    emptySpots = true
                    break
                }
            }
        }
        return emptySpots
    }

    private fun horizontalWin(character: Char): Boolean {
        var allTheSame = true
        for (i in table.indices) {
            if (table[0][i] != character) {
                allTheSame = false
                break
            }
        }

        return allTheSame
    }

    private fun verticalWin(character: Char): Boolean {
        var allTheSame = true
        for (i in table.indices) {
            if (table[i][0] != character) {
                allTheSame = false
                break
            }
        }

        return allTheSame
    }

    private fun diagonalWin(character: Char, opposite: Boolean): Boolean {
        var allTheSame = true
        for (i in table.indices) {
            val cell = if (opposite) table[i][2 - i] else table[i][i]
            if (cell != character) {
                allTheSame = false
                break
            }
        }

        return allTheSame
    }

    companion object {

        const val X = 'X'
        const val O = 'O'
        const val EMPTY = '\u0000' // Null value

        fun getEmptyTable(): Array<Array<Char>> {
            var table = arrayOf<Array<Char>>()

            for (i in 0..2) {
                var row = arrayOf<Char>()
                for (j in 0..2) {
                    row += Table.EMPTY
                }
                table += row
            }

            return table
        }

        fun getXCoordinate(coordinate: String): Int {
            return when (coordinate[0]) {
                'A' -> 0
                'B' -> 1
                'C' -> 2
                else -> -1
            }
        }

        fun getYCoordinate(coordinate: String): Int {
            return Character.getNumericValue(coordinate[1])
        }

        fun indexToCoordinate(x: Int, y: Int): String {
            val coordinate: String = when (x) {
                0 -> "A"
                1 -> "B"
                2 -> "C"
                else -> "Z"
            }
            return coordinate + y.toString()
        }
    }
}