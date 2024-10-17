package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class OwnedPokemon(
    val pokemon: Pokemon,
    val nickname: String,
    val owner: User,
    val links: MutableList<OwnedPokemon> = mutableListOf()
) : Serializable {

    // Method to link another LinkedPokemon
    fun link(pokemonToLink: OwnedPokemon) {
        if (!links.contains(pokemonToLink)) {
            links.add(pokemonToLink)
            pokemonToLink.links.add(this) // Ensure bidirectional linking
        }
    }
}
