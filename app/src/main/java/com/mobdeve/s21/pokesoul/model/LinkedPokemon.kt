package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class LinkedPokemon(
    val pokemon: Pokemon,
    val nickname: String,
    val owner: User,
    val links: MutableList<LinkedPokemon> = mutableListOf()
) : Serializable {

    // Method to link another LinkedPokemon
    fun link(pokemonToLink: LinkedPokemon) {
        if (!links.contains(pokemonToLink)) {
            links.add(pokemonToLink)
            pokemonToLink.links.add(this) // Ensure bidirectional linking
        }
    }
}
