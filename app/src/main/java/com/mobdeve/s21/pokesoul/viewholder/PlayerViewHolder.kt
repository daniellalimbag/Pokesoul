package com.mobdeve.s21.pokesoul.viewholder

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Player

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val playerImageView: ShapeableImageView = view.findViewById(R.id.playerSiv)
    private val playerNameTextView: TextView = view.findViewById(R.id.usernameTv)

    fun bind(user: Player, showNames: Boolean) {
        playerImageView.setImageResource(user.image) // Set the player's image
        playerNameTextView.text = user.username // Set the player's username
        playerNameTextView.visibility = if (showNames) View.VISIBLE else View.GONE // Toggle visibility

        playerImageView.setOnClickListener {
            val context = itemView.context
            //val intent = Intent(context, ProfileDetailsActivity::class.java)
            //intent.putExtra("USER_INSTANCE", user) // Pass the user instance to ProfileDetailsActivity
            //context.startActivity(intent)
        }
    }
}
