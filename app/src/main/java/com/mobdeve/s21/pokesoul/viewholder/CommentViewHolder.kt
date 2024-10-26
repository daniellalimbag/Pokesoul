package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Comment

class CommentViewHolder (view:View): ViewHolder(view){
    private val userTextView : TextView = view.findViewById(R.id.userTv)
    private val timeTextView : TextView = view.findViewById(R.id.timeTv)
    private val contentTextView : TextView = view.findViewById(R.id.contentTv)

    fun bind(comment: Comment) {
        userTextView.text = comment.user // set the comment's user
        timeTextView.text = comment.time // set the comment's time
        contentTextView.text = comment.content // set the comment's content
    }
}