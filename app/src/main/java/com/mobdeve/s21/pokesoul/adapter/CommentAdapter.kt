package com.mobdeve.s21.pokesoul.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Comment
import com.mobdeve.s21.pokesoul.viewholder.CommentViewHolder

class CommentAdapter(private val commentList : ArrayList<Comment>): Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        Log.d("DEBUG", "Binding item at position: $position with content: ${commentList[position].content}")
        holder.bind(commentList[position])
    }
    fun addComment(newComment : Comment){
        commentList.add(0,newComment)
        notifyItemInserted(0)
    }
}