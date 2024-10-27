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
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.User
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var resultsRv: RecyclerView
    private lateinit var searchView: SearchView
    private var allUsers = ArrayList<User>()
    private var allPokemon = ArrayList<Pokemon>()
    private var isSearchingPokemon: Boolean = false
    private lateinit var searchResultAdapter: SearchResultAdapter

    companion object {
        private const val REQUEST_CODE_SEARCH = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)

        // Initialize views
        resultsRv = findViewById(R.id.resultsRv)
        searchView = findViewById(R.id.SearchView)

        // Set up RecyclerView
        resultsRv.setHasFixedSize(true)
        resultsRv.layoutManager = LinearLayoutManager(this)

        // Initialize lists
        addUserDataToList()
        addPokemonDataToList()

        // Determine the context of the search
        isSearchingPokemon = intent.getBooleanExtra("isFromAddPokemon", false)

        // Initialize the adapter based on context
        searchResultAdapter = SearchResultAdapter(if (isSearchingPokemon) allPokemon else allUsers, object : SearchResultAdapter.OnItemClickListener {
            override fun onUserClick(user: User) {
                val resultIntent = Intent()
                resultIntent.putExtra("selectedPlayer", user)
                setResult(RESULT_OK, resultIntent)
                finish()
            }

            override fun onPokemonClick(pokemon: Pokemon) {
                val resultIntent = Intent()
                resultIntent.putExtra("selectedPokemon", pokemon)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        })
        resultsRv.adapter = searchResultAdapter

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
            val filteredList = if (isSearchingPokemon) {
                allPokemon.filter { it.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) }
            } else {
                allUsers.filter { it.username.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) }
            }
            searchResultAdapter.updateData(ArrayList(filteredList)) // Ensure the type matches ArrayList<Serializable>
        }
    }

    private fun addUserDataToList() {
        allUsers.add(DataHelper.user2)
        allUsers.add(DataHelper.user3)
    }

    private fun addPokemonDataToList() {
        allPokemon.add(DataHelper.pokemon1)
        allPokemon.add(DataHelper.pokemon2)
        allPokemon.add(DataHelper.pokemon3)
        allPokemon.add(DataHelper.pokemon4)
        allPokemon.add(DataHelper.pokemon5)
        allPokemon.add(DataHelper.pokemon6)
        allPokemon.add(DataHelper.pokemon7)
        allPokemon.add(DataHelper.pokemon8)
    }
}
