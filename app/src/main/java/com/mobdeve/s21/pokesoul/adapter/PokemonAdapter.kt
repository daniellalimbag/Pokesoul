package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.LinkedPokemon
import com.mobdeve.s21.pokesoul.viewholder.PokemonViewHolder

class PokemonAdapter(private val linkedPokemonList: List<LinkedPokemon>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val linkedPokemon = linkedPokemonList[position]
        holder.bind(linkedPokemon)
    }

    override fun getItemCount(): Int = linkedPokemonList.size
}