package ch.ssseco

class Table {

    private var table: Array<Array<Char>>

    init {
        table = getEmptyTable()
    }

    fun getCell(move: Move): Char {
        return getCell(move.row, move.col)
    }

    fun getCell(row: Int, col: Int): Char {
        return table[row][col]
    }

    fun setCell(move: Move, value: Char) {
        setCell(move.row, move.col, value)
    }

    fun setCell(row: Int, col: Int, value: Char) {
        table[row][col] = value
    }

    fun printTable() {
        println("\tA\tB\tC\n")

        for (i in table.indices) {
            val row = table[i]
            println(i.toString() + "\t" + row[0] + "\t" + row[1] + "\t" + row[2])
        }
    }

    fun evaluateWin(): Int {
        val player = X
        val opponent = O

        // Checking for Rows for X or O victory.
        for (row in 0..2) {
            if (table[row][0] == table[row][1]
                && table[row][1] == table[row][2]
            ) {
                if (table[row][0] == player) {
                    return +10
                } else if (table[row][0] == opponent) {
                    return -10
                }
            }
        }
        // Checking for Columns for X or O victory.
        for (col in 0..2) {
            if (table[0][col] == table[1][col]
                && table[1][col] == table[2][col]
            ) {
                if (table[0][col] == player) {
                    return +10
                } else if (table[0][col] == opponent) {
                    return -10
                }
            }
        }
        // Checking for Diagonals for X or O victory.
        if (table[0][0] == table[1][1] && table[1][1] == table[2][2]) {
            if (table[0][0] == player) {
                return +10
            } else if (table[0][0] == opponent) {
                return -10
            }
        }
        if (table[0][2] == table[1][1] && table[1][1] == table[2][0]) {
            if (table[0][2] == player) {
                return +10
            } else if (table[0][2] == opponent) {
                return -10
            }
        }
        // Else if none of them have won then return 0
        return 0
    }

    fun isTableFull(): Boolean {
        for (row in table) {
            for (cell in row) {
                if (cell == EMPTY) {
                    return true
                }
            }
        }
        return false
    }

    private fun getEmptyTable(): Array<Array<Char>> {
        var table = arrayOf<Array<Char>>()

        for (i in 0..2) {
            var row = arrayOf<Char>()
            for (j in 0..2) {
                row += EMPTY
            }
            table += row
        }

        return table
    }

    companion object {

        const val X = 'X'
        const val O = 'O'
        const val EMPTY = '\u0000' // Null value

        fun getColumnCoordinate(coordinate: String): Int {
            return when (coordinate[0]) {
                'A' -> 0
                'B' -> 1
                'C' -> 2
                else -> -1
            }
        }

        fun getRowCoordinate(coordinate: String): Int {
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