package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.TimelineLog

class TimelineLogDetailsActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button

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

        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)

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
