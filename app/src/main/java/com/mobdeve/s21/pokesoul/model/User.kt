package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class User(
    val username: String,
    val image: Int,
    val runs: MutableList<Run> = mutableListOf()
) : Serializable
