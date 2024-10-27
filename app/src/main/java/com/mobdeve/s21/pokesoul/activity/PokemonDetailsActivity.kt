package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.OwnedPokemon

class PokemonDetailsActivity : AppCompatActivity() {
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_details)

        // Retrieve the OwnedPokemon instance from the Intent
        val pokemon = intent.getSerializableExtra("POKEMON_INSTANCE") as? OwnedPokemon

        // Initialize UI elements
        val pokemonSiv = findViewById<ShapeableImageView>(R.id.pokemonSiv)
        val nicknameText = findViewById<EditText>(R.id.nicknameText)
        val playerActv = findViewById<AutoCompleteTextView>(R.id.playerActv)
        val locationTv = findViewById<AutoCompleteTextView>(R.id.locationTv)
        val saveTv = findViewById<AutoCompleteTextView>(R.id.saveTv)

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
            pokemonSiv.setImageResource(it.pokemon.imageId) // Assumes imageId is a drawable resource ID
            nicknameText.setText(it.nickname)
            locationTv.setText(it.caughtLocation)
            saveTv.setText(it.savedLocation)
        }

    }
}
