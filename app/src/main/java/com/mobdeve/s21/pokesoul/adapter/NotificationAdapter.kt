package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Notification
import com.mobdeve.s21.pokesoul.viewholder.NotificationViewHolder
import com.mobdeve.s21.pokesoul.viewholder.PlayerViewHolder

class NotificationAdapter(
    private val notificationList : List<Notification>
): RecyclerView.Adapter<NotificationViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }

}