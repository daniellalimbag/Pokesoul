package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R

class AddTimelineLogActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var playerActv: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_timelinelog)

        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)
        playerActv = findViewById(R.id.playerActv)

        val playerList = intent.getStringArrayListExtra("playerList")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, playerList!!.toMutableList())
        playerActv.setAdapter(adapter)
        playerActv.threshold = 1

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