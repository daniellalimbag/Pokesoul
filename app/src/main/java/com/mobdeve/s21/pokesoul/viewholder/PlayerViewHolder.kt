package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Player
import com.squareup.picasso.Picasso

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val playerImageView: ShapeableImageView = view.findViewById(R.id.playerSiv)
    private val playerNameTextView: TextView = view.findViewById(R.id.usernameTv)

    fun bind(player: Player, showNames: Boolean) {
        // Load the player's image using Picasso
        Picasso.get()
            .load(player.image)
            .into(playerImageView)

        // Set the player's name
        playerNameTextView.text = player.name

    }
}
