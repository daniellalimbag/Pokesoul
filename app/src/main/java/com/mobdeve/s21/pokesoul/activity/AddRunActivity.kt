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
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Run

class AddRunActivity : AppCompatActivity() {
    private lateinit var backBtn: ImageButton
    private lateinit var saveBtn: Button
    private lateinit var titleEt: EditText
    private lateinit var gameEt: EditText
    private lateinit var playersRv: RecyclerView
    private lateinit var addIbtn: ImageButton
    private lateinit var playerAdapter: PlayerAdapter
    private val playersList = mutableListOf<Player>()

    companion object {
        private const val REQUEST_CODE_ADD_PLAYER = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_run)

        // Initialize views
        backBtn = findViewById(R.id.backIbtn)
        saveBtn = findViewById(R.id.saveBtn)
        titleEt = findViewById(R.id.titleEt)
        gameEt = findViewById(R.id.gameEt)
        playersRv = findViewById(R.id.playersRv)
        addIbtn = findViewById(R.id.addIbtn)

        // Initialize the PlayerAdapter with playersList
        playerAdapter = PlayerAdapter(playersList, showNames = true)

        // Set up RecyclerView
        playersRv.adapter = playerAdapter
        playersRv.layoutManager = LinearLayoutManager(this)

        // Back button closes the activity
        backBtn.setOnClickListener {
            finish()
        }

        // Save button logic
        saveBtn.setOnClickListener {
            saveRun()
        }

        // Set OnClickListener for the addIbtn to open addPlayerActivity
        addIbtn.setOnClickListener {
            val intent = Intent(this, AddPlayerActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_PLAYER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_PLAYER && resultCode == RESULT_OK) {
            val player = data?.getSerializableExtra("selectedPlayer") as? Player
            player?.let {
                playersList.add(it)
                playerAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun saveRun() {
        // Get text input values
        val title = titleEt.text.toString()
        val game = gameEt.text.toString()
        val runId = 1 // Replace with your logic to generate a unique ID

        // Create new Run object
        val newRun = Run(
            runId = runId,
            runName = title,
            gameTitle = game,
            players = playersList,
            updatedTime = System.currentTimeMillis().toString()
        )

        // Pass the new Run back to RunFragment
        val resultIntent = Intent()
        resultIntent.putExtra("new_run", newRun)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
