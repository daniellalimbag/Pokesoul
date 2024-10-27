package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R

class AddTimelineLogActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_timelinelog)

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