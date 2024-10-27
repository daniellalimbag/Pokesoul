package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Post
import com.mobdeve.s21.pokesoul.viewholder.PostViewHolder

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.titleTv.text = post.title
        holder.usernameTv.text = post.creator.username
        holder.userSiv.setImageResource(post.creator.image)
        holder.timeTv.text = post.time
        holder.contentTv.text = post.content
        holder.commentCountTv.text = post.commentCount.toString()
        holder.likeCountTv.text = post.likeCount.toString()
        holder.dislikeCountTv.text = post.dislikeCount.toString()

        holder.likeImageButton.setOnClickListener {
            // Handle like action
        }

        holder.dislikeImageButton.setOnClickListener {
            // Handle dislike action
        }
        holder.savedImageButton.setOnClickListener{
            if(post.saved){
                post.saved = false
                holder.savedImageButton.setImageResource(R.drawable.bookmark_inactive)
            }
            else{
                post.saved = true
                holder.savedImageButton.setImageResource(R.drawable.bookmark_active)
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
