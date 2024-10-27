package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.adapter.SearchResultAdapter

class SearchResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val resultTv: TextView = itemView.findViewById(R.id.resultTv)

    fun bind(user: User, listener: SearchResultAdapter.OnItemClickListener) {
        resultTv.text = user.username
        itemView.setOnClickListener {
            listener.onItemClick(user)
        }
    }
}
