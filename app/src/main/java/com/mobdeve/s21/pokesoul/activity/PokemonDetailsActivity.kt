package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Run
import com.squareup.picasso.Picasso

class PokemonDetailsActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var playerActv: AutoCompleteTextView
    private lateinit var saveTv: AutoCompleteTextView

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

        // Set click listener for the delete button
        deleteBtn.setOnClickListener {
            finish()
        }

        // Set click listener for the save button
        saveBtn.setOnClickListener {
            finish()
        }

        // Populate the UI with the OwnedPokemon data
        pokemon?.let {
            Picasso.get()
                .load(it.pokemon.sprite)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.magikarp)
                .error(R.drawable.player1)
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
