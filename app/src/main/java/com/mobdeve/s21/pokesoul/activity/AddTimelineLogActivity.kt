package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddTimelineLogActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var addDeathIbtn: ImageButton
    private lateinit var addCaptureIbtn: ImageButton
    private lateinit var playerActv: AutoCompleteTextView

    private lateinit var eventnameEt: EditText
    private lateinit var locationEt: EditText
    private lateinit var notesEt: EditText

    private lateinit var deathsRv: RecyclerView
    private lateinit var capturesRv: RecyclerView

    private lateinit var dbManager: DatabaseManager

    companion object {
        private const val REQUEST_CODE_SEARCH_DEATH = 2
        private const val REQUEST_CODE_ADD_CAPTURE = 3
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_timelinelog)

        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)
        addDeathIbtn = findViewById(R.id.addDeathIbtn)
        addCaptureIbtn = findViewById(R.id.addCaptureIbtn)
        playerActv = findViewById(R.id.playerActv)
        eventnameEt = findViewById(R.id.eventnameEt)
        locationEt = findViewById(R.id.locationEt)
        notesEt = findViewById(R.id.notesEt)
        deathsRv = findViewById(R.id.deathsRv)
        capturesRv = findViewById(R.id.capturesRv)

        dbManager = DatabaseManager(this)
        val runId = intent.getIntExtra("RUN_ID", -1)
        val playerList = intent.getStringArrayListExtra("playerList")

        // Get the Run
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
                team = team
            )
            if (success != -1L) {
                Log.d("AddTimelineLogActivity", "Log added successfully: " +
                        "EventName=$eventName, " +
                        "location=$location, " +
                        "notes=$notes, " +
                        "time=$currentTime, ")
                setResult(RESULT_OK)
            } else {
                Toast.makeText(this, "Error adding Log", Toast.LENGTH_SHORT).show()
            }

            finish()
        }

        // Set up RecyclerViews for deaths and captures
        deathsRv.layoutManager = LinearLayoutManager(this)
        capturesRv.layoutManager = LinearLayoutManager(this)

        // Set click listener for the addDeathIbtn button
        addDeathIbtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("isFromAddDeath", true)
            intent.putExtra("RUN_ID", runId)
            startActivityForResult(intent, REQUEST_CODE_SEARCH_DEATH)
        }

        // Set click listener for the addCaptureIbtn button
        addCaptureIbtn.setOnClickListener {
            val intent = Intent(this, AddPokemonActivity::class.java)
            intent.putExtra("RUN_ID", runId)
            intent.putExtra("playerList", playerList)
            startActivityForResult(intent, REQUEST_CODE_ADD_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_SEARCH_DEATH -> {
                    val selectedPokemon = data?.getSerializableExtra("selectedPokemon") as? OwnedPokemon
                    selectedPokemon?.let {
                        // Add selectedPokemon to deaths list and update the RecyclerView
                        // Add logic to handle adding to deaths
                        Toast.makeText(this, "Selected death: ${it.name}", Toast.LENGTH_SHORT).show()
                    }
                }
                REQUEST_CODE_ADD_CAPTURE -> {
                    val newPokemon = data?.getSerializableExtra("new_pokemon") as? OwnedPokemon
                    newPokemon?.let {
                        // Add newPokemon to captures list and update the RecyclerView
                        // Add logic to handle adding to captures
                        Toast.makeText(this, "Added capture: ${it.name}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
