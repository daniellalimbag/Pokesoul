package com.mobdeve.s21.pokesoul.helper

import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.LinkedPokemon
import com.mobdeve.s21.pokesoul.model.Notification
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.Post
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.User
import kotlinx.datetime.Instant
import java.text.SimpleDateFormat
import java.util.Locale

object DataHelper {

    val user1 = User("Player 1",  R.drawable.player1, "I like men and women",mutableListOf(), mutableListOf())
    val user2 = User("Player 2", R.drawable.player2, "I like men and women",mutableListOf(), mutableListOf())
    val user3 = User("Player 3", R.drawable.player3, "I like men and women",mutableListOf(), mutableListOf())

    private fun formatInstant(instant: Instant): String {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = java.util.Date(instant.toEpochMilliseconds())
        return dateFormatter.format(date)
    }

    fun loadRunData(): ArrayList<Run> {
        val data = ArrayList<Run>()

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
            updatedTime = formatInstant(Instant.parse("2024-10-14T17:15:23.000Z"))
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
            updatedTime = formatInstant(Instant.parse("2024-10-17T22:19:44.000Z"))
        )
        data.add(run2)

        user1.runs.add(run2)
        user3.runs.add(run2)

        return data
    }

    fun loadPostData(): ArrayList<Post> {
        val posts = ArrayList<Post>()

        posts.add(
            Post(
                title = "How to beat Voltorb Flip?",
                creator = user3,
                time = "30 min ago",
                content = "I want to get 2000 coins from the game corner to trade for a Dratini but I suck at Voltorb Flip. Any tips on how to beat it?",
                commentCount = 5,
                likeCount = 20,
                dislikeCount = 1
            )
        )

        posts.add(
            Post(
                title = "I Died to a Stantler",
                creator = user2,
                time = "5 hrs ago",
                content = "I hate this game",
                commentCount = 8,
                likeCount = 35,
                dislikeCount = 3
            )
        )

        posts.add(
            Post(
                title = "HeartGold Run Success!",
                creator = user1,
                time = "12 hrs ago",
                content = "Finally finished my HeartGold run. I love this game.",
                commentCount = 3,
                likeCount = 15,
                dislikeCount = 0
            )
        )

        return posts
    }

    fun loadNotificationData(): ArrayList<Notification>{
        val Notifications = ArrayList<Notification>()

        Notifications.add(
            Notification(
                title = "Who wants to Join?",
                time = "30 Mins ago",
                content = "I'm a new player and would love to learn how to play a Soulink"
            )
        )

        Notifications.add(
            Notification(
                title = "A player died to a Stantler and made us lose...",
                time = "2 Hrs ago",
                content = "Title ^^^"
            )
        )

        Notifications.add(
            Notification(
                title = "Looking for one more person for soulink",
                time = "10 Hrs ago",
                content = "We're a group of 3 and looking for one more person to join this runthrough. Comment if interested!"
            )
        )

        return Notifications
    }
}
