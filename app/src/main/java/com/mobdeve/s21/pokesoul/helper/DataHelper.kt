package com.mobdeve.s21.pokesoul.helper

import com.mobdeve.s21.pokesoul.R
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

        data.add(
            Run(
                runName = "Run 1",
                gameTitle = "Game A",
                players = listOf(user1, user2)
            )
        )
        data.add(
            Run(
                runName = "Run 2",
                gameTitle = "Game B",
                players = listOf(user3, user4)
            )
        )
        // Add more runs
        return data
    }
}