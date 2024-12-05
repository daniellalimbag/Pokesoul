package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class OwnedPokemon(
    val pokemon: Pokemon,
    val nickname: String,
    val owner: User,
    val caughtLocation: String,
    val savedLocation: String,
    val links: MutableList<OwnedPokemon> = mutableListOf(),
    val url: String,
    val sprite: String
) : Serializable {

    // Method to link another OwnedPokemon
    fun link(pokemonToLink: OwnedPokemon) {
        if (!links.contains(pokemonToLink)) {
            links.add(pokemonToLink)
            pokemonToLink.links.add(this) // Ensure bidirectional linking
        }
    }
}
