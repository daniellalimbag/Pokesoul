package com.mobdeve.s21.pokesoul.viewholder


import android.view.View
import com.google.android.material.imageview.ShapeableImageView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.User

class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val playerImageView: ShapeableImageView = itemView.findViewById(R.id.playerSiv)

    fun bind(player: User) {
        playerImageView.setImageResource(player.image)
    }
}