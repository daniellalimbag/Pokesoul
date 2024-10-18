package com.mobdeve.s21.pokesoul.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.helper.DataHelper

class PokemonFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val autoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)

        val runs = DataHelper.loadRunData()
        val run = runs[0]

        val playerNames = run.players.map { it.username }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            playerNames
        )

        autoCompleteTextView.setAdapter(adapter)

        if (playerNames.isNotEmpty()) {
            autoCompleteTextView.setText(playerNames[0], false)
        }

        autoCompleteTextView.setOnClickListener {
            autoCompleteTextView.showDropDown()
        }
    }
}
