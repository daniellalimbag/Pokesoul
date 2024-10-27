package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.SearchResultAdapter
import com.mobdeve.s21.pokesoul.helper.DataHelper
import com.mobdeve.s21.pokesoul.model.User
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var resultsRv: RecyclerView
    private lateinit var searchView: SearchView
    private var allUsers = ArrayList<User>()
    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)

        // Initialize views
        resultsRv = findViewById(R.id.resultsRv)
        searchView = findViewById(R.id.SearchView)

        // Set up RecyclerView
        resultsRv.setHasFixedSize(true)
        resultsRv.layoutManager = LinearLayoutManager(this)

        // Initialize users list
        addUserDataToList() // Populate the user list
        val isFromAddRun = intent.getBooleanExtra("isFromAddRun", false)
        if (isFromAddRun) {

            // Initialize the adapter
            searchResultAdapter = SearchResultAdapter(allUsers, object : SearchResultAdapter.OnItemClickListener {
                override fun onItemClick(user: User) {
                    // Create an intent to return to AddRunActivity with the selected user
                    val resultIntent = Intent() // Create a result intent without a new Activity
                    resultIntent.putExtra("selectedPlayer", user) // Pass the user instance
                    setResult(RESULT_OK, resultIntent) // Set the result to pass data back
                    finish() // Close SearchActivity
                }
            })
            resultsRv.adapter = searchResultAdapter

        }

        // Set up SearchView
        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<User>()
            for (user in allUsers) {
                if (user.username.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filteredList.add(user)
                }
            }
            searchResultAdapter.updateData(filteredList)
        }
    }

    private fun addUserDataToList() {
        allUsers.add(DataHelper.user2)
        allUsers.add(DataHelper.user3)
    }
}
