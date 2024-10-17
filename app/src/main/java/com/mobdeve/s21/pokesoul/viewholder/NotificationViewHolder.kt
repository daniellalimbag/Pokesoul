package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Notification
import com.mobdeve.s21.pokesoul.model.User

class NotificationViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val titleTextView : TextView = view.findViewById(R.id.titleTv)
    private val timeTextView : TextView = view.findViewById(R.id.timeTv)
    private val contentTextView : TextView = view.findViewById(R.id.contentTv)

    fun bind(notification: Notification) {
        titleTextView.text = notification.title // set the notification's title
        timeTextView.text = notification.time // set the notification's time
        contentTextView.text = notification.content // set the notification's content
    }
}