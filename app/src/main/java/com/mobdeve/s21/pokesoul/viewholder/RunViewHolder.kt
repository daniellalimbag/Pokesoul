package com.mobdeve.s21.pokesoul.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PlayerAdapter
import com.mobdeve.s21.pokesoul.model.Run

class RunViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val runNameTv: TextView = itemView.findViewById(R.id.runnameTv)
    private val gameTv: TextView = itemView.findViewById(R.id.gameTv)
    private val playersRv: RecyclerView = itemView.findViewById(R.id.playersRv)

    fun bind(model: Run) {
        runNameTv.text = model.runName
        gameTv.text = model.gameTitle

        val playerAdapter = PlayerAdapter(model.players)
        playersRv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        playersRv.adapter = playerAdapter
    }
}