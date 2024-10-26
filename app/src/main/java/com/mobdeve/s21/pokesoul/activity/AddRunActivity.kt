package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PlayerAdapter
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.User

class AddRunActivity : AppCompatActivity() {

    private lateinit var backBtn: ImageButton
    private lateinit var saveBtn: Button
    private lateinit var titleEt: EditText
    private lateinit var gameEt: EditText
    private lateinit var playersRv: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter
    private val playersList = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_run)

        // Initialize views
        this.backBtn = findViewById(R.id.backIbtn)
        this.saveBtn = findViewById(R.id.saveBtn)
        this.titleEt = findViewById(R.id.titleEt)
        this.gameEt = findViewById(R.id.gameEt)
        this.playersRv = findViewById(R.id.playersRv)

        // Static data
        val user1 = User("Player 1", R.drawable.player1, "I like men and women", mutableListOf(), mutableListOf())
        playersList.add(user1)

        // Initialize the PlayerAdapter with playersList and showNames = true
        playerAdapter = PlayerAdapter(playersList, showNames = true)

        // Set up RecyclerView with PlayerAdapter and LayoutManager
        playersRv.adapter = playerAdapter
        playersRv.layoutManager = LinearLayoutManager(this)

        // Back button closes the activity
        this.backBtn.setOnClickListener {
            finish()
        }

        // Save button logic
        this.saveBtn.setOnClickListener {
            saveRun()
        }
    }

    private fun saveRun() {
        // Get text input values
        val title = titleEt.text.toString()
        val game = gameEt.text.toString()

        // Initialize updatedTime with current time or any default value
        val updatedTime = System.currentTimeMillis().toString() // Placeholder

        // Create new Run object
        val newRun = Run(runName = title, gameTitle = game, players = playersList, updatedTime = updatedTime)

        // Pass the new Run back to RunFragment
        val resultIntent = Intent()
        resultIntent.putExtra("new_run", newRun)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}