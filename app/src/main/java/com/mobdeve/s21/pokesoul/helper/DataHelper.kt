package com.mobdeve.s21.pokesoul.helper

import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.LinkedPokemon
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.User
import kotlinx.datetime.*

object DataHelper {
    fun loadRunData(): ArrayList<Run> {
        val data = ArrayList<Run>()

        val user1 = User("Player 1", R.drawable.player1, mutableListOf())
        val user2 = User("Player 2", R.drawable.player2, mutableListOf())
        val user3 = User("Player 3", R.drawable.player3, mutableListOf())

        val pokemon1 = Pokemon("Quilava", 156, R.drawable.quilava)
        val pokemon2 = Pokemon("Ampharos", 181, R.drawable.ampharos)
        val pokemon3 = Pokemon("Drowzee", 96, R.drawable.drowzee)
        val pokemon4 = Pokemon("Noctowl", 164, R.drawable.noctowl)
        val pokemon5 = Pokemon("Bellsprout", 69, R.drawable.bellsprout)
        val pokemon6 = Pokemon("Croconaw", 159, R.drawable.croconaw)
        val pokemon7 = Pokemon("Snubbull", 209, R.drawable.snubbull)
        val pokemon8 = Pokemon("Eevee", 133, R.drawable.eevee)

        val linkedPokemonList = listOf(
            LinkedPokemon(pokemon1, "Ron", user1),
            LinkedPokemon(pokemon2, "Rov", user1),
            LinkedPokemon(pokemon3, "Izzy", user2),
            LinkedPokemon(pokemon4, "Julian", user2),
            LinkedPokemon(pokemon5, "Hiro", user1),
            LinkedPokemon(pokemon6, "Ilan", user2),
            LinkedPokemon(pokemon7, "Marc", user3),
            LinkedPokemon(pokemon8, "Fish", user3),
            LinkedPokemon(pokemon1, "Weaver", user3)
        )

        linkedPokemonList[0].link(linkedPokemonList[1])
        linkedPokemonList[2].link(linkedPokemonList[3])
        linkedPokemonList[4].link(linkedPokemonList[5])
        linkedPokemonList[1].link(linkedPokemonList[2])
        linkedPokemonList[3].link(linkedPokemonList[4])
        linkedPokemonList[5].link(linkedPokemonList[6])
        linkedPokemonList[6].link(linkedPokemonList[7])
        linkedPokemonList[7].link(linkedPokemonList[8])
        linkedPokemonList[8].link(linkedPokemonList[0])

        val linkedTeam1 = linkedPokemonList
        val run1 = Run(
            runName = "Best Run Ever",
            gameTitle = "SoulSilver",
            players = listOf(user1, user2, user3),
            team = linkedTeam1,
            creationDateTime = LocalDateTime.parse("2024-10-14T17:15:23.000Z").toString()
        )
        data.add(run1)

        user1.runs.add(run1)
        user2.runs.add(run1)
        user3.runs.add(run1)

        val linkedTeam2 = listOf(
            LinkedPokemon(pokemon4, "Drew", user1),
            LinkedPokemon(pokemon2, "Tin", user3)
        )

        linkedTeam2[0].link(linkedTeam2[1])

        val run2 = Run(
            runName = "Another Run",
            gameTitle = "HeartGold",
            players = listOf(user1, user3),
            team = linkedTeam2,
            creationDateTime = LocalDateTime.parse("2024-10-17T22:19:44.000Z").toString()
        )
        data.add(run2)

        user1.runs.add(run2)
        user3.runs.add(run2)

        return data
    }
}
