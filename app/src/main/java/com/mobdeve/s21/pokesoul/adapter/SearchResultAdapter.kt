package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.viewholder.SearchResultViewHolder

class SearchResultAdapter(
    private var userList: List<User>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<SearchResultViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user, listener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateData(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()
    }
}
