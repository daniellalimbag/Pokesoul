package com.mobdeve.s21.pokesoul.viewholder

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

        // Set up the Player adapter
        val playerAdapter = PlayerAdapter(run.players, false)
        playersRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        playersRv.adapter = playerAdapter

        // Set up the Pokémon adapter for the team
        val pokemonAdapter = PokemonAdapter(run.team)
        teamRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        teamRv.adapter = pokemonAdapter
    }
}