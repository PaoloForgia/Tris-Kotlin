package ch.ssseco

import kotlin.random.Random

class AIPlayer(myCharacter: Char, opponentCharacter: Char) {

    private val minmax = Minmax(myCharacter, opponentCharacter)

    fun getCoordinate(table: Table, turn: Int): String {

        val coordinate: String = if (turn == 1) {
            // Choose random position
            Table.indexToCoordinate(random(), random())
        } else {
            // Choose position based on table

            val move = minmax.findBestMove(table)

            Table.indexToCoordinate(move.col, move.row)
        }

        // Since the coordinate is not manually entered, print it
        println(coordinate)

        return coordinate
    }

    private fun random(): Int {
        return Random.nextInt(0,2)
    }
}