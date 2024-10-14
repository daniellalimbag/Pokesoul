package com.mobdeve.s21.pokesoul.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_run, container, false)
        runsRv = view.findViewById(R.id.runsRv)

        // Load runs
        loadRuns()

        // Set up the RecyclerView
        runsRv.layoutManager = LinearLayoutManager(requireContext())
        runAdapter = RunAdapter(runList)
        runsRv.adapter = runAdapter

        return view
    }

    private fun loadRuns() {
        runList = DataHelper.loadRunData()
    }
}