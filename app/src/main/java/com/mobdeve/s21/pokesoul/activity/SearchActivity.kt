package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.adapter.SearchResultAdapter
import com.mobdeve.s21.pokesoul.api.PokemonAPIClient
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Pokemon
import com.mobdeve.s21.pokesoul.model.PokemonListResponse
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

class SearchActivity : AppCompatActivity() {

    private lateinit var resultsRv: RecyclerView
    private lateinit var searchView: SearchView
    private var allUsers = ArrayList<Player>()
    private var allPokemon = ArrayList<Pokemon>()
    private var allOwnedPokemon = ArrayList<OwnedPokemon>()
    private var isSearchingPokemon: Boolean = false
    private var isSearchingOwnedPokemon: Boolean = false
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

        // Determine the context of the search
        isSearchingPokemon = intent.getBooleanExtra("isFromAddPokemon", false)
        isSearchingOwnedPokemon = intent.getBooleanExtra("isFromAddDeath", false) || intent.getBooleanExtra("isFromAddCapture", false)

        // Initialize the adapter based on context
        searchResultAdapter = SearchResultAdapter(
            when {
                isSearchingPokemon -> allPokemon
                isSearchingOwnedPokemon -> allOwnedPokemon
                else -> allUsers
            },
            object : SearchResultAdapter.OnItemClickListener {
                override fun onUserClick(user: Player) {
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

                override fun onOwnedPokemonClick(ownedPokemon: OwnedPokemon) {
                    val resultIntent = Intent()
                    resultIntent.putExtra("selectedPokemon", ownedPokemon)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        )
        resultsRv.adapter = searchResultAdapter

        // Fetch data based on context
        if (isSearchingPokemon) {
            fetchPokemonData()
        } else if (isSearchingOwnedPokemon) {
            val runId = intent.getIntExtra("RUN_ID", -1)
            fetchAllOwnedPokemonData(runId)
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
            val filteredList = when {
                isSearchingPokemon -> allPokemon.filter { it.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) }
                isSearchingOwnedPokemon -> allOwnedPokemon.filter { it.nickname.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) }
                else -> allUsers.filter { it.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) }
            }
            searchResultAdapter.updateData(ArrayList(filteredList))
        }
    }

    private fun fetchPokemonData() {
        // Make API call to fetch Pokémon list
        PokemonAPIClient.api.getPokemonList(1000).enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                if (response.isSuccessful) {
                    val pokemonList = response.body()?.results
                    if (pokemonList != null) {
                        allPokemon.clear()
                        pokemonList.forEach { result ->
                            // Create a Pokemon object for each result
                            val pokemon = Pokemon(
                                name = result.name,
                                url = result.url,
                                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${result.url.split("/")[6]}.png"
                            )
                            allPokemon.add(pokemon)
                        }
                        // Update the adapter with the new list
                        searchResultAdapter.updateData(allPokemon as ArrayList<Serializable>)
                    }
                } else {
                    Log.e("SearchActivity", "Failed to load Pokémon data")
                    Toast.makeText(this@SearchActivity, "Failed to load Pokémon data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                Log.e("SearchActivity", "Error fetching Pokémon data", t)
                Toast.makeText(this@SearchActivity, "Error fetching Pokémon data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchAllOwnedPokemonData(runId: Int) {
        val dbManager = DatabaseManager(this)
        val allOwnedPokemonList = dbManager.getAllOwnedPokemonByRunId(runId)
        allOwnedPokemon.clear()
        allOwnedPokemon.addAll(allOwnedPokemonList)
        searchResultAdapter.updateData(allOwnedPokemon as ArrayList<Serializable>)
    }
}
