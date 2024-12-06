package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddRunActivity.Companion
import com.mobdeve.s21.pokesoul.adapter.PlayerAdapter
import com.mobdeve.s21.pokesoul.adapter.PokemonAdapter
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.squareup.picasso.Picasso

class AddPokemonActivity : AppCompatActivity() {
    private lateinit var searchIbtn: ImageButton
    private lateinit var pokemonTv: TextView
    private lateinit var deleteBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var pokemonSiv: ShapeableImageView

    companion object {
        private const val REQUEST_CODE_SEARCH = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_pokemon)

        // Initialize views
        pokemonTv = findViewById(R.id.pokemonTv)
        searchIbtn = findViewById(R.id.searchIbtn)
        deleteBtn = findViewById(R.id.deleteBtn)
        saveBtn = findViewById(R.id.saveBtn)
        pokemonSiv = findViewById(R.id.pokemonSiv) // Initialize the sprite ImageView

        // Set OnClickListener for the searchIbtn to open SearchActivity
        searchIbtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("isFromAddPokemon", true)
            startActivityForResult(intent, REQUEST_CODE_SEARCH)
        }

        // Set click listener for the delete button
        deleteBtn.setOnClickListener {
            finish()
        }

        // Set click listener for the save button
        saveBtn.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            val selectedPokemon = data?.getSerializableExtra("selectedPokemon") as? Pokemon
            selectedPokemon?.let {
                pokemonTv.text = selectedPokemon.name
                Picasso.get()
                    .load(it.sprite)
                    .into(pokemonSiv)
            }
        }
    }
}
