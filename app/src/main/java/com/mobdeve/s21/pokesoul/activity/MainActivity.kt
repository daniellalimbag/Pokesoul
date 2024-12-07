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

        // Insert some sample data for testing
        addSampleData()

        // Retrieve and log the data from the database
        retrieveAndLogData()
    }

    private fun addSampleData() {
        try {
            // Example: Insert a new run entry
            val runId = dbManager.insertRunEntry("My Nuzlocke Run", "Pokemon Emerald", "2024-12-07T10:00:00Z")
            Log.d("DatabaseLog", "Inserted Run ID: $runId")

            // Example: Insert a new owned Pokemon
            val pokemonId = dbManager.insertOwnedPokemonEntry(
                "Bulbasaur",
                0,
                "Route 1",
                "PC",
                "https://pokeapi.co/api/v2/pokemon/bulbasaur",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                runId.toInt() // Pass the runId here
            )
            Log.d("DatabaseLog", "Inserted Pokemon ID: $pokemonId")

            // Example: Insert a new timeline log entry
            if (runId != null) {
                val logId = dbManager.insertTimelineLogEntry(
                    "Caught Bulbasaur",
                    "Route 1",
                    "2024-12-07T10:30:00Z",
                    "First capture!",
                    true,
                    runId.toInt(), // Ensure runId is passed as an integer
                    1
                )
                Log.d("DatabaseLog", "Inserted Timeline Log ID: $logId")
            }
        } catch (e: Exception) {
            Log.e("DatabaseLog", "Error inserting sample data", e)
        }
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
