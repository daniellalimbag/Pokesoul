package com.mobdeve.s21.pokesoul.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.AddPokemonActivity
import com.mobdeve.s21.pokesoul.activity.PokemonDetailsActivity
import com.mobdeve.s21.pokesoul.model.OwnedPokemon
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.User
import com.squareup.picasso.Picasso

class PokemonFragment : Fragment() {
    private lateinit var run: Run

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon, container, false)

        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.playerActv)
        val teamTableLayout = view.findViewById<TableLayout>(R.id.teamTl)
        val boxTableLayout = view.findViewById<TableLayout>(R.id.boxTl)
        val daycareTableLayout = view.findViewById<TableLayout>(R.id.daycareTl)
        val graveTableLayout = view.findViewById<TableLayout>(R.id.graveTl)
        val addFab = view.findViewById<FloatingActionButton>(R.id.addFab)

        // Get the run instance from arguments
        run = arguments?.getSerializable("RUN_INSTANCE") as? Run ?: return view

        // Set up the player names in the AutoCompleteTextView
        val playerNames = run.players.map { player -> player.username }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, playerNames)
        autoCompleteTextView.setAdapter(adapter)

        // Display the first player's Pokémon if available
        if (playerNames.isNotEmpty()) {
            autoCompleteTextView.setText(playerNames[0], false)
            val firstPlayer = run.players[0]
            displayPokemon(firstPlayer, teamTableLayout, boxTableLayout, daycareTableLayout, graveTableLayout, run)
        }

        // Set up the item click listener for the AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            val selectedPlayer = run.players[position]
            displayPokemon(selectedPlayer, teamTableLayout, boxTableLayout, daycareTableLayout, graveTableLayout, run)
        }

        // Set up FloatingActionButton to open AddPokemonActivity
        addFab.setOnClickListener {
            val intent = Intent(requireContext(), AddPokemonActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun displayPokemon(
        selectedPlayer: User,
        teamTableLayout: TableLayout,
        boxTableLayout: TableLayout,
        daycareTableLayout: TableLayout,
        graveTableLayout: TableLayout,
        run: Run
    ) {
        teamTableLayout.removeAllViews()
        boxTableLayout.removeAllViews()
        daycareTableLayout.removeAllViews()
        graveTableLayout.removeAllViews()

        val team = run.team.filter { it.owner.username == selectedPlayer.username }
        val box = run.box.filter { it.owner.username == selectedPlayer.username }
        val daycare = run.daycare.filter { it.owner.username == selectedPlayer.username }
        val grave = run.grave.filter { it.owner.username == selectedPlayer.username }

        populatePokemonTable(team, teamTableLayout)
        populatePokemonTable(box, boxTableLayout)
        populatePokemonTable(daycare, daycareTableLayout)
        populatePokemonTable(grave, graveTableLayout)
    }

    private fun populatePokemonTable(pokemonList: List<OwnedPokemon>, tableLayout: TableLayout) {
        for (i in pokemonList.indices step 2) {
            val tableRow = TableRow(requireContext()).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
            }

            val pokemonView1 = createPokemonView(pokemonList[i])
            tableRow.addView(pokemonView1)

            if (i + 1 < pokemonList.size) {
                val pokemonView2 = createPokemonView(pokemonList[i + 1])
                tableRow.addView(pokemonView2)
            } else {
                val placeholderView = View(requireContext()).apply {
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1f)
                    minimumHeight = pokemonView1.height
                }
                tableRow.addView(placeholderView)
            }

            tableLayout.addView(tableRow)
        }
    }

    private fun createPokemonView(pokemon: OwnedPokemon): LinearLayout {
        val pokemonView = layoutInflater.inflate(R.layout.item_pokemon, null) as LinearLayout
        val pokemonImageView = pokemonView.findViewById<ShapeableImageView>(R.id.pokemonSiv)
        val nicknameTextView = pokemonView.findViewById<TextView>(R.id.nicknameTv)

        pokemonView.orientation = LinearLayout.HORIZONTAL
        pokemon?.let {
            Picasso.get()
                .load(it.pokemon.sprite)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.magikarp)
                .error(R.drawable.player1)
                .into(pokemonImageView)
        }
        nicknameTextView.text = pokemon.nickname

        val paddingInPx = dpToPx(8)
        nicknameTextView.setPadding(paddingInPx, 0, 0, 0)

        val params = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        pokemonView.layoutParams = params

        pokemonView.setOnClickListener {
            val intent = Intent(requireContext(), PokemonDetailsActivity::class.java).apply {
                putExtra("POKEMON_INSTANCE", pokemon)
            }
            startActivity(intent)
        }
        return pokemonView
    }


    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}

