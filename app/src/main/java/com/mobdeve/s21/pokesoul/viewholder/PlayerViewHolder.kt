package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.User

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val playerImageView: ShapeableImageView = view.findViewById(R.id.playerSiv)
    private val playerNameTextView: TextView = view.findViewById(R.id.nameTv)

    fun bind(user: User, showNames: Boolean) {
        playerImageView.setImageResource(user.image) // Set the player's image
        playerNameTextView.text = user.username // Set the player's username
        playerNameTextView.visibility = if (showNames) View.VISIBLE else View.GONE // Toggle visibility
    }
}