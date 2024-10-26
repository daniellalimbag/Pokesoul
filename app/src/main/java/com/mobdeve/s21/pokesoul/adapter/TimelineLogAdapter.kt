package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.TimelineLog
import com.mobdeve.s21.pokesoul.viewholder.TimelineLogViewHolder

class TimelineLogAdapter(private var timelineLogs: List<TimelineLog>) : RecyclerView.Adapter<TimelineLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timelinelog, parent, false)
        return TimelineLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineLogViewHolder, position: Int) {
        val timelineLog = timelineLogs[position]
        holder.bind(timelineLog)
    }

    override fun getItemCount(): Int {
        return timelineLogs.size
    }

    // Method to update logs
    fun setLogs(newLogs: List<TimelineLog>) {
        timelineLogs = newLogs
        notifyDataSetChanged()
    }
}
