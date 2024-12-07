package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.adapter.SearchResultAdapter
import com.mobdeve.s21.pokesoul.model.OwnedPokemon

class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val resultTv: TextView = itemView.findViewById(R.id.resultTv)

    fun bind(item: Any, listener: SearchResultAdapter.OnItemClickListener) {
        when (item) {
            is Pokemon -> {
                resultTv.text = item.name // Assuming Pokemon has a name property
                itemView.setOnClickListener {
                    listener.onPokemonClick(item)
                }
            }
            is OwnedPokemon -> {
                resultTv.text = item.nickname
                itemView.setOnClickListener { listener.onOwnedPokemonClick(item) }
            }
        }
    }
}
