package com.mobdeve.s21.pokesoul.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class AddTimelineLogActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var playerActv: AutoCompleteTextView

    private lateinit var eventnameEt: EditText
    private lateinit var locationEt: EditText
    private lateinit var notesEt: EditText

    private lateinit var dbManager: DatabaseManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_timelinelog)

        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)
        playerActv = findViewById(R.id.playerActv)

        dbManager = DatabaseManager(this)
        val runId = intent.getIntExtra("RUN_ID",-1)
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


            val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val eventName = eventnameEt.text.toString()
            val location = locationEt.text.toString()
            val notes = notesEt.text.toString()
            val displayTeam = 1
            val teamId = dbManager.getTeamByRunId(runId)

            finish()
        }
    }
}