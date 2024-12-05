package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class Run(
    var runId: Int,
    var runName: String,
    val gameTitle: String,
    var players: List<Player>,
    var team: List<OwnedPokemon>,
    var updatedTime: String,
    var logs: List<TimelineLog> = mutableListOf()
) : Serializable {
    constructor(
        runId: Int,
        runName: String,
        gameTitle: String,
        players: List<Player>,
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
