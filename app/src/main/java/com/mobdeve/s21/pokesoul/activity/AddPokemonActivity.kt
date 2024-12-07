package com.mobdeve.s21.pokesoul.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.Player
import com.squareup.picasso.Picasso

class AddPokemonActivity : AppCompatActivity() {
    private lateinit var searchIbtn: ImageButton
    private lateinit var pokemonTv: TextView
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var pokemonSiv: ShapeableImageView
    private lateinit var pokemonNickname: EditText
    private lateinit var locationTv: EditText
    private lateinit var saveTv: AutoCompleteTextView
    private var selectedPokemon: Pokemon? = null
    private lateinit var savedPlayer: AutoCompleteTextView

    private lateinit var refPlayer: Player
    private lateinit var db: DatabaseManager

    private val savedLocations = listOf("Team", "Box", "Daycare", "Grave")

    companion object {
        private const val REQUEST_CODE_SEARCH = 1
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_pokemon)

        // Initialize views
        pokemonTv = findViewById(R.id.pokemonTv)
        searchIbtn = findViewById(R.id.searchIbtn)
        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)
        pokemonSiv = findViewById(R.id.pokemonSiv)
        pokemonNickname = findViewById(R.id.nicknameText)
        locationTv = findViewById(R.id.caughtLocation)
        saveTv = findViewById(R.id.saveTv)
        savedPlayer = findViewById(R.id.playerActv)

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, savedLocations)
        saveTv.setAdapter(adapter)
        saveTv.threshold = 1

        val playerList = intent.getStringArrayListExtra("playerList")
        if (playerList != null) {
            savedPlayer.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, playerList.toMutableList()))
        }

        // Retrieve runId from the intent
        val runId = intent.getIntExtra("RUN_ID", -1)
        if (runId == -1) {
            Toast.makeText(this, "Invalid run ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        db = DatabaseManager(this)
        val players = db.getPlayersByRunId(runId)

        searchIbtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("isFromAddPokemon", true)
            startActivityForResult(intent, REQUEST_CODE_SEARCH)
        }

        deleteBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            // Log the selected player name and available players
            Log.d("AddPokemonActivity", "Selected Player Name: ${savedPlayer.text}")
            Log.d("AddPokemonActivity", "Available Players: ${players.map { it.name }}")

            // Validate player selection
            if (savedPlayer.text.toString().isEmpty()) {
                Toast.makeText(this, "Please select a Player", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Use firstOrNull instead of manual loop
            refPlayer = players.firstOrNull { it.name == savedPlayer.text.toString() }!!

            // Check if player was found
            if (refPlayer == null) {
                Toast.makeText(this, "Selected player not found", Toast.LENGTH_SHORT).show()
                Log.e("AddPokemonActivity", "No player found matching name: ${savedPlayer.text}")
                return@setOnClickListener
            }

            // Rest of the existing validation and save logic...
            if (selectedPokemon == null) {
                Toast.makeText(this, "Please select a Pokemon", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val name = pokemonTv.text.toString()
            val nickname = pokemonNickname.text.toString()
            val caughtLocation = locationTv.text.toString()
            val savedLocation = saveTv.text.toString()

            if (nickname.isEmpty() || caughtLocation.isEmpty() || savedLocation.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Log player details before saving
            Log.d("AddPokemonActivity", "Saving Pokemon for Player: " +
                    "ID=${refPlayer.id}, " +
                    "Name=${refPlayer.name}, " +
                    "RunID=$runId")

            val dbManager = DatabaseManager(this)
            val success = dbManager.insertOwnedPokemonEntry(
                runId = runId,
                ownerId = refPlayer.id,
                name = name,
                nickname = nickname,
                caughtLocation = caughtLocation,
                savedLocation = savedLocation,
                url = selectedPokemon!!.url,
                sprite = selectedPokemon!!.sprite
            )

            if (success != -1L) {
                Log.d("AddPokemonActivity", "Pokemon added successfully: " +
                        "Name=$name, " +
                        "Nickname=$nickname, " +
                        "CaughtLocation=$caughtLocation, " +
                        "SavedLocation=$savedLocation, " +
                        "URL=${selectedPokemon!!.url}, " +
                        "Sprite=${selectedPokemon!!.sprite}")
                setResult(RESULT_OK)
            } else {
                Log.e("AddPokemonActivity", "Error adding Pokemon")
                Toast.makeText(this, "Error adding Pokemon", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            selectedPokemon = data?.getSerializableExtra("selectedPokemon") as? Pokemon
            selectedPokemon?.let {
                pokemonTv.text = it.name
                Picasso.get()
                    .load(it.sprite)
                    .into(pokemonSiv)
            }
        }
    }
}
