package com.mobdeve.s21.pokesoul.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddRunActivity
import com.mobdeve.s21.pokesoul.adapter.RunAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.Run

class RunFragment : Fragment() {

    private lateinit var runsRv: RecyclerView
    private lateinit var runAdapter: RunAdapter
    private lateinit var runList: List<Run>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_run, container, false)

        // Initialize RecyclerView
        runsRv = view.findViewById(R.id.runsRv)
        loadRuns()

        runsRv.layoutManager = LinearLayoutManager(requireContext())
        runAdapter = RunAdapter(runList, "Player 1") // Note: Hardcoded username
        runsRv.adapter = runAdapter

        // Initialize add button and set click listener
        val addIbtn = view.findViewById<ImageButton>(R.id.addIbtn)
        addIbtn.setOnClickListener {
            val intent = Intent(requireContext(), AddRunActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun loadRuns() {
        runList = DataHelper.loadRunData()
    }
}
