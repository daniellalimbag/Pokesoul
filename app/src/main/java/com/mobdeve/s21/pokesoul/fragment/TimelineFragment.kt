package com.mobdeve.s21.pokesoul.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddTimelineLogActivity
import com.mobdeve.s21.pokesoul.adapter.TimelineLogAdapter
import com.mobdeve.s21.pokesoul.model.Run

class TimelineFragment : Fragment() {
    private lateinit var run: Run
    private lateinit var logRv: RecyclerView
    private lateinit var timelineLogAdapter: TimelineLogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_timeline, container, false)
        val addFab = view.findViewById<FloatingActionButton>(R.id.addFab)

        run = arguments?.getSerializable("RUN_INSTANCE") as? Run ?: return view

        logRv = view.findViewById(R.id.logRv)
        val noLogsText: TextView = view.findViewById(R.id.noLogsTv)

        logRv.layoutManager = LinearLayoutManager(requireContext())
        timelineLogAdapter = TimelineLogAdapter(run.logs)
        logRv.adapter = timelineLogAdapter

        if (run.logs.isEmpty()) {
            logRv.visibility = View.GONE
            noLogsText.visibility = View.VISIBLE
            Log.d("TimelineFragment", "No logs available")
        } else {
            logRv.visibility = View.VISIBLE
            noLogsText.visibility = View.GONE
            Log.d("TimelineFragment", "Logs loaded: ${run.logs.size}")
        }
        // Set up FloatingActionButton to open AddTimelineLogActivity
        addFab.setOnClickListener {
            val intent = Intent(requireContext(), AddTimelineLogActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    companion object {
        fun newInstance(run: Run): TimelineFragment {
            return TimelineFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("RUN_INSTANCE", run)
                }
            }
        }
    }
}