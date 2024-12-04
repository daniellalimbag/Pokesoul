package com.mobdeve.s21.pokesoul.helper

import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.TimelineLog
import com.mobdeve.s21.pokesoul.model.User
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Locale

object DataHelper {

    val user1 = User("Player 1", R.drawable.player1, mutableListOf())
    val user2 = User("Player 2", R.drawable.player2, mutableListOf())
    val user3 = User("Player 3", R.drawable.player3, mutableListOf())
    val pokemon1 = Pokemon("Quilava", "https://pokeapi.co/api/v2/pokemon/Quilava", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/156.png")
    val pokemon2 = Pokemon("Ampharos", "https://pokeapi.co/api/v2/pokemon/Ampharos","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/181.png")
    val pokemon3 = Pokemon("Drowzee", "https://pokeapi.co/api/v2/pokemon/Drowzee","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/96.png")
    val pokemon4 = Pokemon("Noctowl", "https://pokeapi.co/api/v2/pokemon/Noctowl","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/164.png")
    val pokemon5 = Pokemon("Bellsprout", "https://pokeapi.co/api/v2/pokemon/Bellsprout","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/69.png")
    val pokemon6 = Pokemon("Croconaw", "https://pokeapi.co/api/v2/pokemon/Croconaw","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/159.png")
    val pokemon7 = Pokemon("Snubbull", "https://pokeapi.co/api/v2/pokemon/Snubbull","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/209.png")
    val pokemon8 = Pokemon("Eevee", "https://pokeapi.co/api/v2/pokemon/Eevee","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/133.png")

    private fun formatInstant(instant: Instant): String {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = java.util.Date(instant.toEpochMilliseconds())
        return dateFormatter.format(date)
    }

    fun loadRunData(): ArrayList<Run> {
        val data = ArrayList<Run>()


        val ownedPokemonLists = listOf(
            OwnedPokemon(pokemon1, "Ron", user1, "New Bark Town", "Team"),
            OwnedPokemon(pokemon2, "Rov", user1, "Route 32", "Team"),
            OwnedPokemon(pokemon3, "Izzy", user2, "Ilex Forest", "Team"),
            OwnedPokemon(pokemon4, "Julian", user2, "Mt. Mortar", "Team"),
            OwnedPokemon(pokemon5, "Hiro", user1, "National Park", "Team"),
            OwnedPokemon(pokemon6, "Ilan", user2, "Lake of Rage", "Team"),
            OwnedPokemon(pokemon7, "Marc", user3, "Ice Path", "Team"),
            OwnedPokemon(pokemon8, "Fish", user3, "Route 42", "Team"),
            OwnedPokemon(pokemon1, "Weaver", user3, "Sprout Tower", "Team")
        )

        // Link Pokémon together
        ownedPokemonLists[0].link(ownedPokemonLists[1])
        ownedPokemonLists[2].link(ownedPokemonLists[3])
        ownedPokemonLists[4].link(ownedPokemonLists[5])
        ownedPokemonLists[1].link(ownedPokemonLists[2])
        ownedPokemonLists[3].link(ownedPokemonLists[4])
        ownedPokemonLists[5].link(ownedPokemonLists[6])
        ownedPokemonLists[6].link(ownedPokemonLists[7])
        ownedPokemonLists[7].link(ownedPokemonLists[8])
        ownedPokemonLists[8].link(ownedPokemonLists[0])

        val linkedTeam1 = listOf(
            OwnedPokemon(pokemon1, "Ron", user1, "New Bark Town", "Team"),
            OwnedPokemon(pokemon2, "Rov", user1, "Route 32", "Team"),
            OwnedPokemon(pokemon3, "Izzy", user2, "Ilex Forest", "Team"),
            OwnedPokemon(pokemon4, "Julian", user2, "Mt. Mortar", "Team"),
            OwnedPokemon(pokemon5, "Hiro", user1, "National Park", "Team"),
            OwnedPokemon(pokemon6, "Ilan", user2, "Lake of Rage", "Team"),
            OwnedPokemon(pokemon7, "Marc", user3, "Ice Path", "Team"),
            OwnedPokemon(pokemon8, "Fish", user3, "Route 42", "Team"),
            OwnedPokemon(pokemon5, "Test", user2, "Sprout Tower", "Team")
        )

        val box1 = listOf(
            OwnedPokemon(pokemon5, "Test", user1, "National Park", "Box"),
            OwnedPokemon(pokemon5, "Test", user2, "Route 35", "Box"),
            OwnedPokemon(pokemon5, "Test", user3, "Route 12", "Box")
        )

        val logTeam1 = listOf(
            OwnedPokemon(pokemon1, "Ron", user1, "New Bark Town", "Team"),
            OwnedPokemon(pokemon3, "Izzy", user2, "Ilex Forest", "Team"),
            OwnedPokemon(pokemon8, "Fish", user3, "Route 42", "Team")
        )

        // Create TimelineLog instances for run1
        val run1Logs = listOf(
            TimelineLog("Run started", "New Bark Town", formatInstant(Instant.parse("2024-10-14T17:15:23.000Z")), logTeam1),
            TimelineLog("Caught Quilava", "New Bark Town", formatInstant(Instant.parse("2024-10-14T18:00:00.000Z")), logTeam1),
            TimelineLog("Team evolved to Croconaw", "New Bark Town", formatInstant(Instant.parse("2024-10-14T19:30:00.000Z")), logTeam1),
            TimelineLog("Player 1 defeated Gym Leader", "New Bark Town", formatInstant(Instant.parse("2024-10-14T20:00:00.000Z")), logTeam1)
        )

        val run1 = Run(
            runName = "Best Run Ever",
            gameTitle = "SoulSilver",
            players = listOf(user1, user2, user3),
            updatedTime = formatInstant(Instant.parse("2024-10-14T17:15:23.000Z")),
            logs = run1Logs
        ).apply {
            team = linkedTeam1
            box = box1
        }

        data.add(run1)

        user1.runs.add(run1)
        user2.runs.add(run1)
        user3.runs.add(run1)

        val linkedTeam2 = listOf(
            OwnedPokemon(pokemon4, "Drew", user1, "Lake of Rage", "Team"),
            OwnedPokemon(pokemon2, "Tin", user3, "Route 39", "Team")
        )

        linkedTeam2[0].link(linkedTeam2[1])

        val run2 = Run(
            runName = "Another Run",
            gameTitle = "HeartGold",
            players = listOf(user1, user3),
            updatedTime = formatInstant(Instant.parse("2024-10-17T22:19:44.000Z"))
        ).apply {
            team = linkedTeam2
        }

        data.add(run2)

        user1.runs.add(run2)
        user3.runs.add(run2)

        return data
    }
}
