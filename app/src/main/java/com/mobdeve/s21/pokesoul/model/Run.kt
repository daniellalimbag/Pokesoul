package com.mobdeve.s21.pokesoul.model

import java.io.Serializable
import java.util.UUID

data class Run(
    var runName: String,
    val gameTitle: String,
    var players: List<User>,
    var team: List<OwnedPokemon>,
    var box: List<OwnedPokemon>,
    var daycare: List<OwnedPokemon>,
    var grave: List<OwnedPokemon>,
    var updatedTime: String,
    var logs: List<TimelineLog> = mutableListOf()
) : Serializable {
    constructor(
        runName: String,
        gameTitle: String,
        players: List<User>,
        updatedTime: String,
        logs: List<TimelineLog> = mutableListOf()
    ) : this(
        runName,
        gameTitle,
        players,
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        mutableListOf(),
        updatedTime,
        logs
    )
}
