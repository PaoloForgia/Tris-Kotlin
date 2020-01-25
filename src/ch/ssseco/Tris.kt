package ch.ssseco

import java.util.*

fun main() {
    Tris().start()
}

class Tris {

    companion object {
        const val PC = "PC"
    }

    private val table: Table = Table()
    private var player1: String = ""
    private var player2: String = ""
    private var turn: Int = 0
    private val aiPlayer = AIPlayer(Table.O, Table.X)

    fun start() {
        val scanner = Scanner(System.`in`)

        println("Insert name of Player 1 (X): ")
        player1 = scanner.next()
        println("Insert name of Player 2 or 'PC' for AI (O): ")
        player2 = scanner.next()

        do {
            turn++

            var character: Char
            var player: String
            val aiTurn: Boolean
            if (turn % 2 != 0) {
                character = Table.X
                player = player1
                aiTurn = false
            } else {
                character = Table.O
                player = player2
                aiTurn = player.equals(PC, true)
            }

            println("\n\nTurno di $player ($character)")
            table.printTable()

            println("\n\n\nInserire coordinata: ")
            val coordinate = if (aiTurn) aiPlayer.getCoordinate(table, turn) else scanner.next().toUpperCase()

            val x = Table.getXCoordinate(coordinate)
            val y = Table.getYCoordinate(coordinate)

            val cell = table.getCell(y, x)
            if (cell == Table.EMPTY) {
                table.setCell(y, x, character)
            } else {
                println("La cella è occupata, perdi il turno... stronzo")
            }
        } while (canContinue())
        println("Completato in $turn turni")
    }

    private fun canContinue(): Boolean {
        // Check if someone won
        if (table.checkForWin(Table.X)) {
            table.printTable()
            println("$player1 ha vinto")
            return false
        }

        if (table.checkForWin(Table.O)) {
            table.printTable()
            println("$player2 ha vinto")
            return false
        }

        // Check if there are empty spots in the table
        if (!table.checkForEmptySposts()) {
            table.printTable()
            println("Il campo è pieno, pareggio!")
            return false
        }

        return true
    }
}