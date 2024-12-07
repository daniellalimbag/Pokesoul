package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.viewholder.SearchResultViewHolder
import java.io.Serializable
import java.util.ArrayList

class SearchResultAdapter(
    private var itemList: ArrayList<out Serializable>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<SearchResultViewHolder>() {

    interface OnItemClickListener {
        fun onUserClick(user: Player)
        fun onPokemonClick(pokemon: Pokemon)
        fun onOwnedPokemonClick(ownedPokemon: OwnedPokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item, listener) // Pass the item directly
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateData(newList: ArrayList<Serializable>) {
        itemList = newList
        notifyDataSetChanged()
    }
}
