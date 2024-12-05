package com.mobdeve.s21.pokesoul.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.TimelineLogDetailsActivity
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.TimelineLog
import com.mobdeve.s21.pokesoul.viewholder.TimelineLogViewHolder

class TimelineLogAdapter(
    private var timelineLogs: List<TimelineLog>,
    private var run: Run // Pass the run instance here
) : RecyclerView.Adapter<TimelineLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timelinelog, parent, false)
        return TimelineLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineLogViewHolder, position: Int) {
        val timelineLog = timelineLogs[position]
        holder.bind(timelineLog, run)
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, TimelineLogDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
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
