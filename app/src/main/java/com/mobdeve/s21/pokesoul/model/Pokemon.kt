package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class Pokemon(
    val name: String,
    val dexId: Int,
    val imageId: Int,
) : Serializable
