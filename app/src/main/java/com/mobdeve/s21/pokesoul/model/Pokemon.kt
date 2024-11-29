package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class Pokemon(
    val name: String,
    val url: String,
    val sprite: String
) : Serializable

data class PokemonListResponse(
    val results: List<Pokemon>
) : Serializable
