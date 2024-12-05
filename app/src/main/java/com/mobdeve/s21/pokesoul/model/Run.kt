package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class Run(
    var runId: Int,
    var runName: String,
    val gameTitle: String,
    var players: List<User>,
    var team: List<OwnedPokemon>,
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
        updatedTime,
        logs
    )
}
