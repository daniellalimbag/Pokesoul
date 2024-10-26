package com.mobdeve.s21.pokesoul.model

class TimelineLog(
    var eventName: String,
    var location: String,
    var time: String,
    var team: List<Pokemon>,
    var deaths: List<Pokemon>? = null,
    var captures: List<Pokemon>? = null,
    var notes: String? = null,

    var displayTeam: Boolean = true
)