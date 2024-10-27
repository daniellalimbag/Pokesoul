package com.mobdeve.s21.pokesoul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.TimelineLog
import com.mobdeve.s21.pokesoul.model.User
import com.mobdeve.s21.pokesoul.viewholder.TimelineLogViewHolder

class TimelineLogAdapter(
    private var timelineLogs: List<TimelineLog>,
    private var selectedPlayer: User, // Pass the selected player here
    private var run: Run // Pass the run instance here
) : RecyclerView.Adapter<TimelineLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timelinelog, parent, false)
        return TimelineLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineLogViewHolder, position: Int) {
        val timelineLog = timelineLogs[position]
        holder.bind(timelineLog, run)
    }

    override fun getItemCount(): Int {
        return timelineLogs.size
    }

    // Method to update logs
    fun setLogs(newLogs: List<TimelineLog>) {
        timelineLogs = newLogs
        notifyDataSetChanged()
    }

    // Method to update the selected player
    fun setSelectedPlayer(player: User) {
        selectedPlayer = player
        notifyDataSetChanged() // Refresh the adapter with the new player selection
    }
}
