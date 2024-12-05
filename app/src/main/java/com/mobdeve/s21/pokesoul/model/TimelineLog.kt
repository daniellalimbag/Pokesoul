package com.mobdeve.s21.pokesoul.model
import java.io.Serializable

class TimelineLog(
    var eventName: String,
    var location: String,
    var time: String,
    var team: Boolean,
    var deaths: Long = null,
    var captures: Long = null,
    var notes: String? = null,
    var displayTeam: Boolean = true
) : Serializable