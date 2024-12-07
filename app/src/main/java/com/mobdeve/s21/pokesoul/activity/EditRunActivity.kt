package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.PlayerAdapter
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Run

class EditRunActivity : AppCompatActivity() {

    private lateinit var backBtn: ImageButton
    private lateinit var saveBtn: Button
    private lateinit var titleEt: EditText
    private lateinit var gameEt: EditText
    private lateinit var playersRv: RecyclerView
    private lateinit var addIbtn: ImageButton
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var deleteBtn:Button
    private lateinit var dbManager: DatabaseManager
    private val playersList = mutableListOf<Player>()

    companion object {
        private const val REQUEST_CODE_SEARCH = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_run)

        // Initialize DatabaseManager
        dbManager = DatabaseManager(this)

        // Initialize views
        backBtn = findViewById(R.id.backIbtn)
        saveBtn = findViewById(R.id.saveBtn)
        titleEt = findViewById(R.id.titleEt)
        gameEt = findViewById(R.id.gameEt)
        playersRv = findViewById(R.id.playersRv)
        addIbtn = findViewById(R.id.addIbtn)
        deleteBtn = findViewById(R.id.deleteBtn)

        // Initialize PlayerAdapter
        playerAdapter = PlayerAdapter(playersList, showNames = true)

        // Set up RecyclerView with PlayerAdapter and LayoutManager
        playersRv.adapter = playerAdapter
        playersRv.layoutManager = LinearLayoutManager(this)

        // Retrieve Run instance from Intent and initialize values
        val run = intent.getSerializableExtra("RUN_INSTANCE") as? Run
        run?.let {
            titleEt.setText(it.runName)
            gameEt.setText(it.gameTitle)
            playersList.addAll(it.players)
            playerAdapter.notifyDataSetChanged()
        }

        // Back button logic
        backBtn.setOnClickListener { finish() }

        deleteBtn.setOnClickListener{
            if(dbManager.deleteRunById(run!!.runId)){
                Toast.makeText(this, "Run deleted", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Run not deleted", Toast.LENGTH_SHORT).show()
            }
            setResult(RESULT_OK)
            finish()
        }

        // Save button logic with basic validation
        saveBtn.setOnClickListener {
            if (titleEt.text.isBlank() || gameEt.text.isBlank()) {
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
            } else {
                saveRun(run?.runId ?: 0)
            }
        }

        // Add player through SearchActivity
        addIbtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("isFromAddRun", true)
            startActivityForResult(intent, REQUEST_CODE_SEARCH)
        }
    }

    // Handle result from SearchActivity to add a player to the list
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SEARCH && resultCode == RESULT_OK) {
            val selectedPlayer = data?.getSerializableExtra("selectedPlayer") as? Player
            selectedPlayer?.let {
                playersList.add(it)
                playerAdapter.notifyItemInserted(playersList.size - 1)
            }
        }
    }

    // Save Run data and return it to the calling activity
    private fun saveRun(runId: Int) {
        val title = titleEt.text.toString()
        val game = gameEt.text.toString()
        val updatedTime = System.currentTimeMillis().toString()

        val editedRun = Run(
            runId = runId,
            runName = title,
            gameTitle = game,
            players = playersList,
            updatedTime = updatedTime
        )

        // Update run details in the database
        val success = dbManager.updateRunDetails(editedRun)
        if (success) {
            val resultIntent = Intent().apply {
                putExtra("edited_run", editedRun)
                putExtra("update_successful", true)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "Error updating run details.", Toast.LENGTH_SHORT).show()
        }
    }
}

