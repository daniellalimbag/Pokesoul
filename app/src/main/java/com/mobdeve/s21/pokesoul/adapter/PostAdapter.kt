package com.mobdeve.s21.pokesoul.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.ViewPostActivity
import com.mobdeve.s21.pokesoul.model.Post
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.viewholder.PostViewHolder

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
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

        holder.postContainer.setOnClickListener{
            val intent = Intent(holder.itemView.context, ViewPostActivity::class.java)

            intent.putExtra("post_title", post.title)
            intent.putExtra("post_creator", post.creator.username)
            intent.putExtra("post_pfp",post.creator.image)
            intent.putExtra("post_time", post.time)
            intent.putExtra("post_content", post.content)
            intent.putExtra("post_commentCount", post.commentCount)
            intent.putExtra("post_likeCount", post.likeCount)
            intent.putExtra("post_dislikeCount", post.dislikeCount)

            holder.itemView.context.startActivity(intent)
        }
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
