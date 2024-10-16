package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    val usernameTv: TextView = itemView.findViewById(R.id.usernameTv)
    val timeTv: TextView = itemView.findViewById(R.id.timeTv)
    val contentTv: TextView = itemView.findViewById(R.id.contentTv)
    val commentCountTv: TextView = itemView.findViewById(R.id.commentCountTv)
    val likeCountTv: TextView = itemView.findViewById(R.id.likeCountTv)
    val dislikeCountTv: TextView = itemView.findViewById(R.id.dislikeCountTv)
    val likeImageButton: ImageButton = itemView.findViewById(R.id.likeImageButton)
    val dislikeImageButton: ImageButton = itemView.findViewById(R.id.dislikeImageButton)
}
