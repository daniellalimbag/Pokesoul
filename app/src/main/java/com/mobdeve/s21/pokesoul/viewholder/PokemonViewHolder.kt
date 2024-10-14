package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Pokemon

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val pokemonImageView: ShapeableImageView = itemView.findViewById(R.id.pokemonSiv)
    private val pokemonNicknameTextView: TextView = itemView.findViewById(R.id.nicknameTv)

    fun bind(pokemon: Pokemon) {
        pokemonImageView.setImageResource(pokemon.imageId)
        pokemonNicknameTextView.text = pokemon.nickname
    }
}
