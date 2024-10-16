package com.mobdeve.s21.pokesoul.model

import java.io.Serializable

class Pokemon(name: String, nickname: String, dexId: Int, imageId: Int) : Serializable {
    var name = name
        private set

    var nickname = nickname
        private set

    var dexId = dexId
        private set

    var imageId = imageId
        private set
}
