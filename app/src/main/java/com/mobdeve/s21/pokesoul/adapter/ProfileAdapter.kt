package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.model.Post
import com.mobdeve.s21.pokesoul.viewholder.PlayerViewHolder
import com.mobdeve.s21.pokesoul.viewholder.PostViewHolder

class ProfileAdapter(
    private val items: List<Any>, // Can contain both User and Post objects
    private val showNames: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 0
        private const val VIEW_TYPE_POST = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is User -> VIEW_TYPE_USER
            is Post -> VIEW_TYPE_POST
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_player, parent, false)

                // Set the orientation of item_player to vertical programmatically
                (view as? LinearLayout)?.orientation = LinearLayout.VERTICAL

                PlayerViewHolder(view)
            }
            VIEW_TYPE_POST -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_post, parent, false)
                PostViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlayerViewHolder -> {
                val user = items[position] as User
                holder.bind(user, showNames)
            }
            is PostViewHolder -> {
                val post = items[position] as Post
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
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
