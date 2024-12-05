package com.mobdeve.s21.pokesoul.model
import java.io.Serializable

class TimelineLog(
    var timelineLogId: Int,
    var eventName: String,
    var location: String,
    var time: String? = null,
    var team: List<OwnedPokemon>,
    var deaths: List<OwnedPokemon>? = null,
    var captures: List<OwnedPokemon>? = null,
    var notes: String? = null,
    var displayTeam: Boolean = true
) : Serializable