package com.mobdeve.s21.pokesoul.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import java.text.SimpleDateFormat
import java.util.Date
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
        eventnameEt = findViewById(R.id.eventnameEt)
        locationEt = findViewById(R.id.locationEt)
        notesEt = findViewById(R.id.notesEt)

        dbManager = DatabaseManager(this)
        val runId = intent.getIntExtra("RUN_ID",-1)
        val playerList = intent.getStringArrayListExtra("playerList")

        //get the Run
        val run = dbManager.getRunById(runId)


        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, playerList!!.toMutableList())
        playerActv.setAdapter(adapter)
        playerActv.threshold = 1

        // Set click listener for the delete button
        deleteBtn.setOnClickListener {
            finish()
        }
        // Set click listener for the save button
        saveBtn.setOnClickListener {
            val dbManager = DatabaseManager(this)
            val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val formattedTime = currentTime.format(Date())
            val eventName = eventnameEt.text.toString()
            val location = locationEt.text.toString()
            val notes = notesEt.text.toString()
            val displayTeam = 1
            val team = run!!.team

            val success = dbManager.insertTimelineLogEntry(
                eventName = eventName,
                location = location,
                time = formattedTime,
                notes = notes,
                displayTeam = displayTeam,
                runId = runId,
                team= team
            )
            if (success != -1L) {
                Log.d("AddTimelineLogActivity", "Pokemon added successfully: " +
                        "EventName=$eventName, " +
                        "location=$location, " +
                        "notes=$notes, " +
                        "time=$currentTime, ")
                setResult(RESULT_OK)
            } else {
                Log.e("AddPokemonActivity", "Error adding Pokemon")
                Toast.makeText(this, "Error adding Pokemon", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }
}