package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class OwnedPokemon(
    val ownedPokemonId: Int,
    val pokemon: Pokemon,
    val name: String,
    val nickname: String,
    val owner: Player,
    val caughtLocation: String,
    val savedLocation: String,
    val url: String,
    val sprite: String
) : Serializable