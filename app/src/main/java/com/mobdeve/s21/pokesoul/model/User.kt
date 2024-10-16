package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class User(username: String, image: Int) : Serializable {
    var username = username
        private set

    var image = image
        private set
}
