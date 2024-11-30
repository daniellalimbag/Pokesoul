package com.mobdeve.s21.pokesoul.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.PokemonDetailsActivity
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.viewholder.PokemonViewHolder

class PokemonAdapter(private val ownedPokemonList: List<OwnedPokemon>) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val linkedPokemon = ownedPokemonList[position]
        holder.bind(linkedPokemon)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, PokemonDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = ownedPokemonList.size
}