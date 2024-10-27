package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.adapter.SearchResultAdapter

class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val resultTv: TextView = itemView.findViewById(R.id.resultTv)

    fun bind(item: Any, listener: SearchResultAdapter.OnItemClickListener) {
        when (item) {
            is User -> {
                resultTv.text = item.username
                itemView.setOnClickListener {
                    listener.onUserClick(item)
                }
            }
            is Pokemon -> {
                resultTv.text = item.name // Assuming Pokemon has a name property
                itemView.setOnClickListener {
                    listener.onPokemonClick(item)
                }
            }
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }
}
