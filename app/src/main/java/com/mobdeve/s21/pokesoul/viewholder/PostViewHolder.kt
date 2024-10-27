package com.mobdeve.s21.pokesoul.viewholder


import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddCommentActivity
import com.mobdeve.s21.pokesoul.activity.ViewPostActivity

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    val usernameTv: TextView = itemView.findViewById(R.id.usernameTv)
    val userSiv: ShapeableImageView = itemView.findViewById(R.id.userSiv)
    val timeTv: TextView = itemView.findViewById(R.id.timeTv)
    val contentTv: TextView = itemView.findViewById(R.id.contentTv)
    val commentCountTv: TextView = itemView.findViewById(R.id.commentCountTv)
    val likeCountTv: TextView = itemView.findViewById(R.id.likeCountTv)
    val dislikeCountTv: TextView = itemView.findViewById(R.id.dislikeCountTv)
    val likeImageButton: ImageButton = itemView.findViewById(R.id.likeImageButton)
    val dislikeImageButton: ImageButton = itemView.findViewById(R.id.dislikeImageButton)
    val savedImageButton : ImageButton = itemView.findViewById(R.id.saveImageButton)
    val postContainer : ConstraintLayout = itemView.findViewById(R.id.postContainer)
    val commentIv : ImageView = itemView.findViewById(R.id.commentIv)

    private var isSaved = false
    private var isLiked = false
    private var isDisliked = false

    fun bind(onClick: () -> Unit) {
        commentIv.setOnClickListener{
            val intent = Intent(itemView.context, AddCommentActivity::class.java)
            itemView.context.startActivity(intent)
        }
        savedImageButton.setOnClickListener {
            if (isSaved) {
                isSaved = false
                savedImageButton.setImageResource(R.drawable.bookmark_inactive)
            } else {
                isSaved = true
                savedImageButton.setImageResource(R.drawable.bookmark_active)
            }
        }
        likeImageButton.setOnClickListener {

            if (isLiked) {
                isLiked = false
                likeImageButton.setImageResource(R.drawable.like_inactive)
            } else {
                isLiked = true
                likeImageButton.setImageResource(R.drawable.like_active)
            }
            dislikeImageButton.setOnClickListener {
                if (isDisliked) {
                    isDisliked = false
                    dislikeImageButton.setImageResource(R.drawable.dislike_inactive)
                } else {
                    isDisliked = true
                    dislikeImageButton.setImageResource(R.drawable.dislike_active)
                }
            }
            postContainer.setOnClickListener{
                val intent = Intent(itemView.context, ViewPostActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }

    }


}
