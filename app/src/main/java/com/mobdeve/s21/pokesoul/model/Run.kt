package com.mobdeve.s21.pokesoul.model

class Run(runName: String, gameTitle: String, players: List<User>, team: List<Pokemon>) {

    var runName = runName
        private set

    var gameTitle = gameTitle
        private set

    var players = players
        private set

    var team = team
        private set
}