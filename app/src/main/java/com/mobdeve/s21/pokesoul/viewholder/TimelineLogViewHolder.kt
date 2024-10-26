package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PokemonAdapter
import com.mobdeve.s21.pokesoul.model.TimelineLog

class TimelineLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val eventTv: TextView = itemView.findViewById(R.id.eventTv)
    private val locationTv: TextView = itemView.findViewById(R.id.locationTv)
    private val timeTv: TextView = itemView.findViewById(R.id.timeTv)

    private val teamLl: View = itemView.findViewById(R.id.teamLl)
    private val teamRv: RecyclerView = itemView.findViewById(R.id.teamRv)

    private val deathsLl: View = itemView.findViewById(R.id.deathsLl)
    private val deathsRv: RecyclerView = itemView.findViewById(R.id.deathsRv)

    private val capturesLl: View = itemView.findViewById(R.id.capturesLl)
    private val capturesRv: RecyclerView = itemView.findViewById(R.id.capturesRv)

    private val notesLl: View = itemView.findViewById(R.id.notesLl)
    private val notesTv: TextView = itemView.findViewById(R.id.notesTv)

    fun bind(timelineLog: TimelineLog) {
        eventTv.text = timelineLog.eventName
        locationTv.text = timelineLog.location
        timeTv.text = timelineLog.time

        // Setting up the team RecyclerView if displayTeam is true
        if (timelineLog.displayTeam) {
            teamLl.visibility = View.VISIBLE
            teamRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            teamRv.adapter = PokemonAdapter(timelineLog.team)
        } else {
            teamLl.visibility = View.GONE
        }

        if (!timelineLog.deaths.isNullOrEmpty()) {
            deathsLl.visibility = View.VISIBLE
            deathsRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            deathsRv.adapter = PokemonAdapter(timelineLog.deaths!!)
        } else {
            deathsLl.visibility = View.GONE
        }

        if (!timelineLog.captures.isNullOrEmpty()) {
            capturesLl.visibility = View.VISIBLE
            capturesRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            capturesRv.adapter = PokemonAdapter(timelineLog.captures!!)
        } else {
            capturesLl.visibility = View.GONE
        }
        if (!timelineLog.notes.isNullOrEmpty()) {
            notesLl.visibility = View.VISIBLE
            notesTv.visibility = View.VISIBLE
            notesTv.text = timelineLog.notes ?: ""
        } else {
            notesLl.visibility = View.GONE
            notesTv.visibility = View.GONE
        }
    }
}
