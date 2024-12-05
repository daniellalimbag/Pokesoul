package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class Run(
    var runId: Int,
    var runName: String,
    val gameTitle: String,
    var players: List<User>,
    var team: List<OwnedPokemon>,
    //TODO: Remove box, daycare, grave, and updated time
    var box: List<OwnedPokemon>,
    var daycare: List<OwnedPokemon>,
    var grave: List<OwnedPokemon>,
    var updatedTime: String,
    var logs: List<TimelineLog> = mutableListOf()
) : Serializable {
    constructor(
        runId: Int,
        runName: String,
        gameTitle: String,
        players: List<User>,
        updatedTime: String,
        logs: List<TimelineLog> = mutableListOf()
    ) : this(
        runId,
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
