package com.mobdeve.s21.pokesoul.helper

import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.User

object DataHelper {
    fun loadRunData(): ArrayList<Run> {
        val data = ArrayList<Run>()

        // Create User objects
        val user1 = User("Player 1", R.drawable.player1)
        val user2 = User("Player 2", R.drawable.player2)
        val user3 = User("Player 3", R.drawable.player3)
        val user4 = User("Player 4", R.drawable.player4)

        // Create Pokemon objects for Run 1
        val teamRun1 = listOf(
            Pokemon("Quilava", "Ron", 156, R.drawable.quilava),
            Pokemon("Ampharos", "Rov", 181, R.drawable.ampharos),
            Pokemon("Drowzee", "Andrew", 96, R.drawable.drowzee),
            Pokemon("Noctowl", "Kiel", 164, R.drawable.noctowl),
            Pokemon("Bellsprout", "Plant", 69, R.drawable.bellsprout)
        )

        // Create Pokemon objects for Run 2
        val teamRun2 = listOf(
            Pokemon("Croconaw", "Ilan", 159, R.drawable.croconaw),
            Pokemon("Snubbull", "Izzy", 209, R.drawable.snubbull),
            Pokemon("Eevee", "Tin", 133, R.drawable.eevee),
        )

        // Add runs with players and Pokemon teams
        data.add(
            Run(
                runName = "BiRep",
                gameTitle = "SoulSilver",
                players = listOf(user1, user2, user4),
                team = teamRun1
            )
        )
        data.add(
            Run(
                runName = "Super Funny Run",
                gameTitle = "HeartGold",
                players = listOf(user1, user3),
                team = teamRun2
            )
        )
        return data
    }
}
