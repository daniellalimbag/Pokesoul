package com.mobdeve.s21.pokesoul.fragment

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddRunActivity
import com.mobdeve.s21.pokesoul.adapter.RunAdapter
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.Run

class RunFragment : Fragment() {
    private lateinit var runsRv: RecyclerView
    private lateinit var runAdapter: RunAdapter
    private var runList: MutableList<Run> = mutableListOf()
    private lateinit var dbManager: DatabaseManager

    // Result launcher for adding a new run
    private val addRunResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK && result.data != null) {
            val newRun = result.data?.getSerializableExtra("new_run") as? Run
            newRun?.let {
                runList.add(0, it) // Add new run to the top of the list
                runAdapter.notifyItemInserted(0)
                runsRv.scrollToPosition(0)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_run, container, false)

        // Initialize DatabaseManager
        dbManager = DatabaseManager(requireContext())

        // Initialize RecyclerView
        runsRv = view.findViewById(R.id.runsRv)
        runList = loadRunDataFromDatabase().toMutableList()
        runAdapter = RunAdapter(runList)
        runsRv.layoutManager = LinearLayoutManager(requireContext())
        runsRv.adapter = runAdapter

        // Initialize add button and set click listener
        val addIbtn = view.findViewById<ImageButton>(R.id.addIbtn)
        addIbtn.setOnClickListener {
            val intent = Intent(requireContext(), AddRunActivity::class.java)
            addRunResultLauncher.launch(intent)

        }

        return view
    }

    private fun loadRunDataFromDatabase(): List<Run> {
        return try {
            dbManager.getAllRuns().runs
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Return an empty list if an error occurs
        }
    }
}
