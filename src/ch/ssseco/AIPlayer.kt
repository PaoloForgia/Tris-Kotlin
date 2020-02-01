package ch.ssseco

import kotlin.random.Random

class AIPlayer(private val myCharacter: Char, private val opponentCharacter: Char) {

    private lateinit var tableValue: Array<Array<Int>>

    fun getCoordinate(table: Table, turn: Int): String {

        val coordinate: String = if (turn == 1) {
            // Choose random position
            Table.indexToCoordinate(random(), random())
        } else {
            // Choose position based on table
            analizeTable(table)

            val coordinate = getHighestValueCoordinate()
            println(coordinate)
            coordinate
        }

        // Since the coordinate is not manually entered, print it
        println(coordinate)

        return coordinate
    }

    private fun getHighestValueCoordinate(): String {
        // TODO "Random" spot (not always first) -> center >> corner >> side

        var row = 0
        var column = 0
        var highestValue = 0

        for (rowIndex in tableValue.indices) {
            for (columnIndex in tableValue[rowIndex].indices) {
                if (tableValue[rowIndex][columnIndex] > highestValue) {
                    highestValue = tableValue[rowIndex][columnIndex]
                    row = rowIndex
                    column = columnIndex
                }
            }
        }

        return Table.indexToCoordinate(column, row)
    }

    private fun analizeTable(table: Table) {
        val trisTable = table.getTable()
        tableValue = arrayOf<Array<Int>>()


        // TODO Can I win?

        // TODO Can prevent opponent to win?

        for (rowIndex in trisTable.indices) {
            val row = trisTable[rowIndex]
            var rowScore = arrayOf<Int>()
            for (columnIndex in row.indices) {
                val cell = row[columnIndex]

                if (cell != Table.EMPTY) {
                    // Space used
                    rowScore += 0
                    continue
                }

                // If is center +50
                rowScore += if (rowIndex == 1 && columnIndex == 1) {
                    20
                }
                // If is side +10
                else if ((columnIndex == 1 && (rowIndex == 0 || rowIndex == 2)) ||
                    (rowIndex == 1 && (columnIndex == 0 || columnIndex == 2))) {
                    10
                }
                // Is corner +25
                else {
                    15
                }
            }
            tableValue += rowScore
        }

        // Check cells
        for (rowIndex in trisTable.indices) {
            val row = trisTable[rowIndex]
            for (columnIndex in row.indices) {
                val cell = trisTable[rowIndex][columnIndex]
                if (cell == Table.EMPTY) {
                    // Do nothing
                    continue
                }

                val isOpponentCell = cell == this.opponentCharacter
                if (rowIndex > 0) {
                    increaseCellValue(table, rowIndex - 1, columnIndex, isOpponentCell)
                }

                if (rowIndex < 2) {
                    increaseCellValue(table, rowIndex + 1, columnIndex, isOpponentCell)
                }

                if (columnIndex > 0) {
                    increaseCellValue(table, rowIndex, columnIndex - 1, isOpponentCell)
                }

                if (columnIndex < 2) {
                    increaseCellValue(table, rowIndex, columnIndex + 1, isOpponentCell)
                }

                if  (rowIndex > 0 && columnIndex > 0) {
                    increaseCellValue(table, rowIndex - 1, columnIndex - 1, isOpponentCell)
                }

                if  (rowIndex < 2 && columnIndex < 2) {
                    increaseCellValue(table, rowIndex + 1, columnIndex + 1, isOpponentCell)
                }

                if  (rowIndex > 0 && columnIndex < 2) {
                    increaseCellValue(table, rowIndex - 1, columnIndex + 1, isOpponentCell)
                }

                if  (rowIndex < 2 && columnIndex > 0) {
                    increaseCellValue(table, rowIndex + 1, columnIndex - 1, isOpponentCell)
                }
            }
        }


        println("\tA\tB\tC\n")
        for (i in tableValue.indices) {
            val row = tableValue[i]
            println(i.toString() + "\t" + row[0] + "\t" + row[1] + "\t" + row[2])
        }
    }

    private fun increaseCellValue(table: Table, rowIndex: Int, columnIndex: Int, isOpponentCell: Boolean) {
        val nearCell = table.getCell(rowIndex, columnIndex)
        if (nearCell == Table.EMPTY) {
            tableValue[rowIndex][columnIndex] += if (isOpponentCell) 5 else 10
        }
    }

    private fun random(): Int {
        return Random.nextInt(0,2)
    }
}