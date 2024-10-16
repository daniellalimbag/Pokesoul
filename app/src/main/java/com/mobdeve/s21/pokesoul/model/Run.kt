package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class Run(
    val runName: String,
    val gameTitle: String,
    val players: List<User>,
    val team: List<LinkedPokemon>,
    val creationDateTime: String
) : Serializable
