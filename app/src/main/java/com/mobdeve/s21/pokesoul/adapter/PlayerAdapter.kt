package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.viewholder.PlayerViewHolder

class PlayerAdapter(
    private val playerList: List<User>,
    private val showNames: Boolean
) : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        // Inflate the item layout for each player (item_player.xml)
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        // Bind each player data to the ViewHolder
        holder.bind(playerList[position], showNames)
    }

    override fun getItemCount(): Int {
        // Return the size of the player list
        return playerList.size
    }
}
