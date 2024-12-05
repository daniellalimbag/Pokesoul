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

        val emptyList: MutableList<OwnedPokemon> = mutableListOf()
        val ownedPokemonLists = listOf(
            OwnedPokemon(1,pokemon1, "Ron", user1, "New Bark Town", "Team", pokemon1.url, pokemon1.sprite),
            OwnedPokemon(2,pokemon2, "Rov", user1, "Route 32", "Team", pokemon2.url, pokemon2.sprite),
            OwnedPokemon(3,pokemon3, "Izzy", user2, "Ilex Forest", "Team",pokemon3.url, pokemon3.sprite),
            OwnedPokemon(4,pokemon4, "Julian", user2, "Mt. Mortar", "Team",pokemon4.url, pokemon4.sprite),
            OwnedPokemon(5,pokemon5, "Hiro", user1, "National Park", "Team",pokemon5.url, pokemon5.sprite),
            OwnedPokemon(6,pokemon6, "Ilan", user2, "Lake of Rage", "Team",pokemon6.url, pokemon6.sprite),
            OwnedPokemon(7,pokemon7, "Marc", user3, "Ice Path", "Team",pokemon7.url, pokemon7.sprite),
            OwnedPokemon(8,pokemon8, "Fish", user3, "Route 42", "Team",pokemon8.url, pokemon8.sprite),
            OwnedPokemon(9,pokemon1, "Weaver", user3, "Sprout Tower", "Team",pokemon1.url, pokemon1.sprite)
        )

        val linkedTeam1 = listOf(
            OwnedPokemon(10,pokemon1, "Ron", user1, "New Bark Town", "Team", pokemon1.url, pokemon1.sprite),
            OwnedPokemon(11,pokemon2, "Rov", user1, "Route 32", "Team", pokemon2.url, pokemon2.sprite),
            OwnedPokemon(12,pokemon3, "Izzy", user2, "Ilex Forest", "Team", pokemon3.url, pokemon3.sprite),
            OwnedPokemon(13, pokemon4, "Julian", user2, "Mt. Mortar", "Team", pokemon4.url, pokemon4.sprite),
            OwnedPokemon(14, pokemon5, "Hiro", user1, "National Park", "Team", pokemon5.url, pokemon5.sprite),
            OwnedPokemon(15, pokemon6, "Ilan", user2, "Lake of Rage", "Team", pokemon6.url, pokemon6.sprite),
            OwnedPokemon(16, pokemon7, "Marc", user3, "Ice Path", "Team", pokemon7.url, pokemon7.sprite),
            OwnedPokemon(17, pokemon8, "Fish", user3, "Route 42", "Team", pokemon8.url, pokemon8.sprite),
            OwnedPokemon(18, pokemon5, "Test", user2, "Sprout Tower", "Team", pokemon1.url, pokemon1.sprite)

        )

        val box1 = listOf(
            OwnedPokemon(19, pokemon5, "Test", user1, "National Park", "Box", pokemon1.url, pokemon1.sprite),
            OwnedPokemon(20, pokemon5, "Test", user2, "Route 35", "Box", pokemon2.url, pokemon2.sprite),
            OwnedPokemon(21, pokemon5, "Test", user3, "Route 12", "Box", pokemon3.url, pokemon3.sprite)
        )

        val logTeam1 = listOf(
            OwnedPokemon(22, pokemon1, "Ron", user1, "New Bark Town", "Team", pokemon1.url, pokemon1.sprite),
            OwnedPokemon(23, pokemon3, "Izzy", user2, "Ilex Forest", "Team", pokemon2.url, pokemon2.sprite),
            OwnedPokemon(24, pokemon8, "Fish", user3, "Route 42", "Team", pokemon3.url, pokemon3.sprite)

        )

        // Create TimelineLog instances for run1
        val run1Logs = listOf(
            TimelineLog(1,"Run started", "New Bark Town", formatInstant(Instant.parse("2024-10-14T17:15:23.000Z")), logTeam1),
            TimelineLog(2,"Caught Quilava", "New Bark Town", formatInstant(Instant.parse("2024-10-14T18:00:00.000Z")), logTeam1),
            TimelineLog(3,"Team evolved to Croconaw", "New Bark Town", formatInstant(Instant.parse("2024-10-14T19:30:00.000Z")), logTeam1),
            TimelineLog(4,"Player 1 defeated Gym Leader", "New Bark Town", formatInstant(Instant.parse("2024-10-14T20:00:00.000Z")), logTeam1)
        )

        val run1 = Run(
            runId = 1,
            runName = "Best Run Ever",
            gameTitle = "SoulSilver",
            players = listOf(user1, user2, user3),
            updatedTime = formatInstant(Instant.parse("2024-10-14T17:15:23.000Z")),
            logs = run1Logs
        ).apply {
            team = linkedTeam1
        }

        data.add(run1)

        user1.runs.add(run1)
        user2.runs.add(run1)
        user3.runs.add(run1)

        val linkedTeam2 = listOf(
            OwnedPokemon(25, pokemon4, "Drew", user1, "Lake of Rage", "Team", pokemon1.url, pokemon1.sprite),
            OwnedPokemon(26, pokemon2, "Tin", user3, "Route 39", "Team", pokemon2.url, pokemon2.sprite)
        )

        val run2 = Run(
            runId = 1,
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
