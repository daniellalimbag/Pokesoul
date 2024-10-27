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
            if(!post.disliked){
                if(post.liked){
                    post.liked = false
                    holder.likeImageButton.setImageResource(R.drawable.like_inactive)
                }
                else{
                    post.liked = true
                    holder.likeImageButton.setImageResource(R.drawable.like_active)
                }
            }
        }

        holder.dislikeImageButton.setOnClickListener {
            if(!post.liked){
                if(post.disliked){
                    post.disliked = false
                    holder.dislikeImageButton.setImageResource(R.drawable.dislike_inactive)
                }
                else{
                    post.disliked = true
                    holder.dislikeImageButton.setImageResource(R.drawable.dislike_active)
                }
            }

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
