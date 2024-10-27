package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class Comment (
    val user: String,
    var content: String?,
    val time: String
) : Serializable