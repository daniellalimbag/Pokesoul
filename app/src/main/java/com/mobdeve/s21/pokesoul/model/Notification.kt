package com.mobdeve.s21.pokesoul.model

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class Notification(val title: String? = null, val time: String? = null, val content: String? = null) : Serializable

