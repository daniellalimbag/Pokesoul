package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Player
import com.squareup.picasso.Picasso

class AddPlayerActivity : AppCompatActivity() {
    private lateinit var selectBtn: Button
    private lateinit var nicknameText: EditText
    private lateinit var confirmBtn: Button
    private lateinit var playerImageView: ImageView
    private var imageUri: Uri? = null

    private val imageResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            try {
                result.data?.data?.let { uri ->
                    imageUri = uri
                    Picasso.get().load(imageUri).into(playerImageView)
                }
            } catch (exception: Exception) {
                Log.e("AddPlayerActivity", "Error loading image", exception)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_player)

        selectBtn = findViewById(R.id.selectBtn)
        confirmBtn = findViewById(R.id.confirmBtn)
        nicknameText = findViewById(R.id.nicknameText)
        playerImageView = findViewById(R.id.newPlayerSiv)
        // Set click listener for the confirm button
        confirmBtn.setOnClickListener {
            val playerName = nicknameText.text.toString()
            if (playerName.isNotEmpty() && imageUri != null) {
                val player = Player(id = 0, name = playerName, image = imageUri.toString()) // Generate an ID or handle it later
                val resultIntent = Intent().apply {
                    putExtra("selectedPlayer", player)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                // Handle empty player name or image case
                Toast.makeText(this, "Name and image cannot be empty", Toast.LENGTH_SHORT).show()
                if (playerName.isEmpty()) {
                    nicknameText.error = "Name cannot be empty"
                }
            }
        }

        // Set click listener for the select button
        selectBtn.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_OPEN_DOCUMENT
            }
            imageResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
        }
    }
}
