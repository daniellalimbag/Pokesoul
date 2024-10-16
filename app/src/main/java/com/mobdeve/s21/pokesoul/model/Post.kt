package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

data class Post(
    val title: String,
    val username: String,
    val time: String,
    val content: String,
    val commentCount: Int,
    val likeCount: Int,
    val dislikeCount: Int
) : Serializable