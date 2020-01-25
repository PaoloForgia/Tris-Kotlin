package ch.ssseco

import kotlin.random.Random

class AIPlayer(private val myCharacter: Char, private val opponentCharacter: Char) {

    fun getCoordinate(table: Table, turn: Int): String {

        val coordinate: String = if (turn == 1) {
            // Choose random position
            Table.indexToCoordinate(random(), random())
        } else {
            // Choose position based on table

            "A0"
        }

        // Since the coordinate is not manually entered, print it
        println(coordinate)

        return coordinate
    }

    private fun random(): Int {
        return Random.nextInt(0,2)
    }
}