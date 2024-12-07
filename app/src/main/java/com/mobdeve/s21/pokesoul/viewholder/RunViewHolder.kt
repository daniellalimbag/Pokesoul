package com.mobdeve.s21.pokesoul.viewholder

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PlayerAdapter
import com.mobdeve.s21.pokesoul.adapter.PokemonAdapter
import com.mobdeve.s21.pokesoul.model.Run

class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val runNameTv: TextView = itemView.findViewById(R.id.runnameTv)
    private val gameTv: TextView = itemView.findViewById(R.id.gameTv)
    private val playersRv: RecyclerView = itemView.findViewById(R.id.playersRv)
    private val teamRv: RecyclerView = itemView.findViewById(R.id.teamRv)

    fun bind(run: Run) {
        runNameTv.text = run.runName
        gameTv.text = run.gameTitle

        val activity = itemView.context as? Activity
        activity?.let {
            val playerAdapter = PlayerAdapter(run.players as ArrayList, false, it)
            playersRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            playersRv.adapter = playerAdapter
        } ?: run {
            Log.e("RunViewHolder", "Could not determine the activity context.")
        }

        // Check if players list is not empty
        if (run.players.isNotEmpty()) {
            val firstPlayerName = run.players[0].name
            Log.d("RunViewHolder", "First Player: $firstPlayerName")

            val userPokemon = run.team.filter { it.owner.name.equals(firstPlayerName, ignoreCase = true) }

            Log.d("RunViewHolder", "User Pokémon: ${userPokemon.size}")

            val pokemonAdapter = PokemonAdapter(userPokemon)
            teamRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            teamRv.adapter = pokemonAdapter
        } else {
            Log.d("RunViewHolder", "No players found in run.")
        }
    }
}
