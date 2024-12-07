package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.TimelineLog

class TimelineLogDetailsActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var dbManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timelinelog_details)

        // Retrieve the log passed from the TimelineFragment
        val logEntry = intent.getSerializableExtra("TIMELINE_LOG") as? TimelineLog
        if (logEntry != null) {
            val eventNameTextView = findViewById<TextView>(R.id.eventnameEt)
            val locationTextView = findViewById<TextView>(R.id.locationEt)
            val notesTextView = findViewById<TextView>(R.id.notesEt)

            eventNameTextView.text = logEntry.eventName
            locationTextView.text = logEntry.location
            notesTextView.text = logEntry.notes
        }
        else{
            Log.d("TimelineLogDetailsActivity", "logEntry is null")
        }

        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)

        dbManager = DatabaseManager(this)

        // Set click listener for the delete button
        deleteBtn.setOnClickListener {
            finish()
        }
        // Set click listener for the save button
        saveBtn.setOnClickListener {
            finish()
        }
    }
}
