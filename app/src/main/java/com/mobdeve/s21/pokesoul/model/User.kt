package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class User(
    val username: String,
    val image: Int,
    val bio: String,
    val friends: MutableList<User> = mutableListOf(),
    val runs: MutableList<Run> = mutableListOf()
) : Serializable
