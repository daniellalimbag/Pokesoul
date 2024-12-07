package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PokemonAdapter
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.TimelineLog
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Pokemon

class TimelineLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val eventTv: TextView = itemView.findViewById(R.id.eventTv)
    private val locationTv: TextView = itemView.findViewById(R.id.locationTv)
    private val timeTv: TextView = itemView.findViewById(R.id.timeTv)
    private val teamLl: View = itemView.findViewById(R.id.teamLl)
    private val teamRv: RecyclerView = itemView.findViewById(R.id.teamRv)
    private val playerActv: AutoCompleteTextView = itemView.findViewById(R.id.playerActv)

    private val deathsLl: View = itemView.findViewById(R.id.deathsLl)
    private val deathsRv: RecyclerView = itemView.findViewById(R.id.deathsRv)
    private val capturesLl: View = itemView.findViewById(R.id.capturesLl)
    private val capturesRv: RecyclerView = itemView.findViewById(R.id.capturesRv)
    private val notesLl: View = itemView.findViewById(R.id.notesLl)
    private val notesTv: TextView = itemView.findViewById(R.id.notesTv)

    // Bind the data for each timeline log entry
    fun bind(timelineLog: TimelineLog, run: Run) {
        eventTv.text = timelineLog.eventName
        locationTv.text = timelineLog.location
        timeTv.text = timelineLog.time

        // Set up the player dropdown
        val playerNames = run.players.map { it.name }
        val adapter = ArrayAdapter(itemView.context, android.R.layout.simple_dropdown_item_1line, playerNames)
        playerActv.setAdapter(adapter)

        // Set the first player as default, if available
        if (playerNames.isNotEmpty()) {
            playerActv.setText(playerNames[0], false)
            updateTeamUI(run.players[0], timelineLog)
        }

        // Handle player selection from AutoCompleteTextView
        playerActv.setOnItemClickListener { _, _, position, _ ->
            val selectedPlayer = run.players[position]
            updateTeamUI(selectedPlayer, timelineLog)
        }

        // Setup other lists
        setupRecyclerView(deathsLl, deathsRv, timelineLog.deaths)
        setupRecyclerView(capturesLl, capturesRv, timelineLog.captures)

        // Handle notes
        if (!timelineLog.notes.isNullOrBlank()) {
            notesLl.visibility = View.VISIBLE
            notesTv.text = timelineLog.notes
        } else {
            notesLl.visibility = View.GONE
        }
    }

    // Function to update team UI based on selected player
    private fun updateTeamUI(selectedPlayer: Player, timelineLog: TimelineLog) {
        val team = timelineLog.team.filter { it.owner.name == selectedPlayer.name }
        teamRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        teamRv.adapter = PokemonAdapter(team)
        teamLl.visibility = if (team.isNotEmpty()) View.VISIBLE else View.GONE
    }

    // Utility function to set up RecyclerView based on list visibility
    private fun setupRecyclerView(container: View, recyclerView: RecyclerView, items: List<OwnedPokemon>?) {
        if (!items.isNullOrEmpty()) {
            container.visibility = View.VISIBLE
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = PokemonAdapter(items)
        } else {
            container.visibility = View.GONE
        }
    }
}