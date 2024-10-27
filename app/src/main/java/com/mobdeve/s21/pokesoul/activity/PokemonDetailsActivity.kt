package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.OwnedPokemon

class PokemonDetailsActivity : AppCompatActivity() {

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
        val saveBtn = findViewById<Button>(R.id.saveBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)

        // Populate the UI with the OwnedPokemon data
        pokemon?.let {
            pokemonSiv.setImageResource(it.pokemon.imageId) // Assumes imageId is a drawable resource ID
            nicknameText.setText(it.nickname)
            locationTv.setText(it.caughtLocation)
            saveTv.setText(it.savedLocation)
        }

        // Button click listeners can be added here as needed
    }
}
