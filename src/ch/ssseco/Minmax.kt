package ch.ssseco

// myCharacter, opponentCharacter
class Minmax(private val player: Char, private val opponent: Char) {

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
    private fun minimax(table: Table, depth: Int, isMax: Boolean): Int {
        val score = table.evaluateWin() * -1 // Multiply for -1 because is seen from the "opposite" view
        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10) {
            return score
        }
        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10) {
            return score
        }
        // If there are no more moves and
        // no winner then it is a tie
        if (!table.isTableFull()) {
            return 0
        }
        // If this maximizer's move
        return if (isMax) {
            var best = -1000
            // Traverse all cells
            for (i in 0..2) {
                for (j in 0..2) { // Check if cell is empty
                    if (table.getCell(i, j) == Table.EMPTY) { // Make the move
                        table.setCell(i, j, player)
                        // Call minimax recursively and choose the maximum value
                        best = best.coerceAtLeast(minimax(table,depth + 1, !isMax))
                        // Undo the move
                        table.setCell(i, j, Table.EMPTY)
                    }
                }
            }
            best
        } // If this minimizer's move
        else {
            var best = 1000
            // Traverse all cells
            for (i in 0..2) {
                for (j in 0..2) { // Check if cell is empty
                    if (table.getCell(i, j) == Table.EMPTY) { // Make the move
                        table.setCell(i, j, opponent)
                        // Call minimax recursively and choose the minimum value
                        best = best.coerceAtMost(minimax(table,depth + 1, !isMax))
                        // Undo the move
                        table.setCell(i, j, Table.EMPTY)
                    }
                }
            }
            best
        }
    }

    // This will return the best possible
    // move for the player
    fun findBestMove(table: Table): Move {
        var bestVal = -1000
        val bestMove = Move()
        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (i in 0..2) {
            for (j in 0..2) { // Check if cell is empty
                if (table.getCell(i, j) == Table.EMPTY) { // Make the move
                    table.setCell(i, j, player)
                    // compute evaluation function for this move.
                    val moveVal = minimax(table, 0, false)
                    // Undo the move
                    table.setCell(i, j, Table.EMPTY)
                    // If the value of the current move is
                    // more than the best value, then update best
                    if (moveVal > bestVal) {
                        bestMove.row = i
                        bestMove.col = j
                        bestVal = moveVal
                    }
                }
            }
        }
        return bestMove
    }
}