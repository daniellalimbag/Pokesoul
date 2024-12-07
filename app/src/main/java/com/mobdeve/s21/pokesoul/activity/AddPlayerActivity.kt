package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Player

class AddPlayerActivity : AppCompatActivity() {
    private lateinit var selectBtn: Button
    private lateinit var nicknameText: EditText
    private lateinit var confirmBtn: Button
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)

        selectBtn = findViewById(R.id.selectBtn)
        confirmBtn = findViewById(R.id.confirmBtn)
        nicknameText = findViewById(R.id.nicknameText)  // Initialize nicknameText

        // Set click listener for the confirm button
        confirmBtn.setOnClickListener {
            val playerName = nicknameText.text.toString()
            if (playerName.isNotEmpty()) {
                val player = Player(id = 0, name = playerName, image = "") // Generate an ID or handle it later
                val resultIntent = Intent().apply {
                    putExtra("selectedPlayer", player)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Handle empty player name case
                nicknameText.error = "Name cannot be empty"
            }
        }

        // Set click listener for the select button
        selectBtn.setOnClickListener {
            // Handle image selection logic
        }
    }
}
