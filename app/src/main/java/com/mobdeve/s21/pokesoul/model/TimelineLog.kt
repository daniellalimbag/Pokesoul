package com.mobdeve.s21.pokesoul.model
import java.io.Serializable

class TimelineLog(
    var eventName: String,
    var location: String,
    var time: String,
    var team: List<OwnedPokemon>,
    var deaths: List<OwnedPokemon>? = null,
    var captures: List<OwnedPokemon>? = null,
    var notes: String? = null,
    var displayTeam: Boolean = true
) : Serializable