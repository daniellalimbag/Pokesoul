package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.database.MyDatabaseHelper
import com.mobdeve.s21.pokesoul.fragment.RunFragment

class MainActivity : AppCompatActivity() {

    private lateinit var dbManager: DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize DatabaseManager
        dbManager = DatabaseManager(this)

        // This is for testing if the Database Connects properly
        val dbHelper = MyDatabaseHelper(this)
        val db = dbHelper.writableDatabase // or dbHelper.readableDatabase

        // Load RunFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RunFragment())
                .commit()
        }

        // Retrieve and log the data from the database
        retrieveAndLogData()
    }

    private fun retrieveAndLogData() {
        try {
            val runId = 1 // Assuming you're checking the first inserted run

            // Retrieve run data
            val run = dbManager.getRunById(runId)
            Log.d("DatabaseLog", "Retrieved Run: ${run?.runName}, ${run?.gameTitle}, ${run?.updatedTime}")

            // Retrieve owned Pokemon data
            val ownedPokemon = dbManager.getTeamByRunId(runId) // Pass the correct table name here
            for (pokemon in ownedPokemon) {
                Log.d("DatabaseLog", "Owned Pokemon: ${pokemon.nickname}, ${pokemon.caughtLocation}, ${pokemon.savedLocation}")
            }

            // Retrieve timeline log data
            val logs = dbManager.getTimelineLogsByRun(runId)
            for (log in logs) {
                Log.d("DatabaseLog", "Timeline Log: ${log.eventName}, ${log.location}, ${log.time}, ${log.notes}")
            }
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error retrieving data", e)
        }
    }
}
