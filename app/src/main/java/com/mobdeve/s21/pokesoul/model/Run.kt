package com.mobdeve.s21.pokesoul.model

import java.io.Serializable
import java.util.UUID

data class Run(
    val id: String = UUID.randomUUID().toString(),
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
        id: String,
        runName: String,
        gameTitle: String,
        players: List<User>,
        updatedTime: String,
        logs: List<TimelineLog> = mutableListOf()
    ) : this(
        id,
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
