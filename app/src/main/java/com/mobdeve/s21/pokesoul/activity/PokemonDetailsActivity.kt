package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Run
import com.squareup.picasso.Picasso

class PokemonDetailsActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var playerActv: AutoCompleteTextView
    private lateinit var saveTv: AutoCompleteTextView
    private lateinit var db: DatabaseManager
    private lateinit var refPlayer: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)

        // Retrieve the OwnedPokemon instance and Run instance from the Intent
        val pokemon = intent.getSerializableExtra("POKEMON_INSTANCE") as? OwnedPokemon
        val run = intent.getSerializableExtra("RUN_INSTANCE") as? Run

        // Initialize UI elements
        val pokemonSiv = findViewById<ShapeableImageView>(R.id.pokemonSiv)
        val nicknameText = findViewById<EditText>(R.id.nicknameText)
        playerActv = findViewById(R.id.playerActv)
        val locationTv = findViewById<EditText>(R.id.locationTv)
        saveTv = findViewById(R.id.saveTv)

        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)

        db = DatabaseManager(this)
        val playerList = run?.let { db.getPlayersByRunId(it.runId) }

        // Set click listener for the delete button
        deleteBtn.setOnClickListener {

            if(db.deletePokemonById(pokemon!!.ownedPokemonId, pokemon.savedLocation)){
                Toast.makeText(this, "Pokemon deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Pokemon not deleted", Toast.LENGTH_SHORT).show()
            }
            setResult(RESULT_OK)
            finish()
        }

        //TODO: LET IT SAVE THE NEW INFORMATION IN THE DATABASE
        saveBtn.setOnClickListener {

            if (playerList != null) {
                refPlayer = playerList.firstOrNull { it.name == playerActv.text.toString() }!!
            }


            val updatedNickname = nicknameText.text.toString()
            val updatedCaughtLocation = locationTv.text.toString()
            val updatedSavedLocation = saveTv.text.toString()

            if(updatedNickname.isEmpty()|| updatedCaughtLocation.isEmpty() || updatedSavedLocation.isEmpty()){
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Adds the new pokemon with the updated information to the database
            val dbManager = DatabaseManager(this)
            val success = dbManager.insertOwnedPokemonEntry(
                runId = run!!.runId,
                ownerId =refPlayer.id,
                name = pokemon!!.name,
                nickname = updatedNickname,
                caughtLocation = updatedCaughtLocation,
                savedLocation = updatedSavedLocation,
                url = pokemon.url,
                sprite = pokemon.sprite
            )

            //Removes the old pokemon with the old information to the database
            if(db.deletePokemonById(pokemon.ownedPokemonId, pokemon.savedLocation)){
                Toast.makeText(this, "Pokemon Updated", Toast.LENGTH_SHORT).show()
            }

            setResult(RESULT_OK)
            finish()
        }

        // Populate the UI with the OwnedPokemon data
        pokemon?.let {
            Picasso.get()
                .load(it.pokemon.sprite)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.magikarp)
                .error(R.drawable.missing)
                .into(pokemonSiv)

            nicknameText.setText(it.nickname)
            locationTv.setText(it.caughtLocation)
            saveTv.setText(it.savedLocation)
            playerActv.setText(it.owner.name, false)
        }

        // Set up the AutoCompleteTextView with player names from the run data
        run?.let {
            val playerNames = it.players.map { player -> player.name }
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, playerNames)
            playerActv.setAdapter(adapter)

            // Set up the item click listener for the AutoCompleteTextView
            playerActv.setOnItemClickListener { _, _, position, _ ->
                val selectedPlayer = it.players[position]
                // Handle the selected player (e.g., display player details or update UI)
            }

            // Add the 4 locations to the saveTv AutoCompleteTextView
            val locations = listOf("Team", "Box", "Daycare", "Grave")
            val locationAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locations)
            saveTv.setAdapter(locationAdapter)
        }
    }
}
