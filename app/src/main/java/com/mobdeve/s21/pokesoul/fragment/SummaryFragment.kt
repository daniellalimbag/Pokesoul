package com.mobdeve.s21.pokesoul.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.model.User
import com.google.android.material.imageview.ShapeableImageView

class SummaryFragment : Fragment() {

    private lateinit var runTitleTv: TextView
    private lateinit var gameTitleTv: TextView
    private lateinit var playersTableLayout: TableLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        runTitleTv = view.findViewById(R.id.runTitleTv)
        gameTitleTv = view.findViewById(R.id.gameTitleTv)
        playersTableLayout = view.findViewById(R.id.playersTl)

        val run = arguments?.getSerializable("RUN_INSTANCE") as? Run

        run?.let {
            runTitleTv.text = it.runName
            gameTitleTv.text = it.gameTitle
            populatePlayers(it.players)
        }

        return view
    }

    private fun populatePlayers(players: List<User>) {
        playersTableLayout.removeAllViews()

        for (i in players.indices step 2) {
            val tableRow = TableRow(requireContext()).apply {
                layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
            }
            val player1View = layoutInflater.inflate(R.layout.item_player, tableRow, false) as LinearLayout
            val player1ImageView = player1View.findViewById<ShapeableImageView>(R.id.playerSiv)
            val player1NameTextView = player1View.findViewById<TextView>(R.id.usernameTv)

            player1ImageView.setImageResource(players[i].image)
            player1NameTextView.text = players[i].username

            val params1 = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f) // Use weight
            player1View.layoutParams = params1

            tableRow.addView(player1View)

            if (i + 1 < players.size) {
                val player2View = layoutInflater.inflate(R.layout.item_player, tableRow, false) as LinearLayout
                val player2ImageView = player2View.findViewById<ShapeableImageView>(R.id.playerSiv)
                val player2NameTextView = player2View.findViewById<TextView>(R.id.usernameTv)

                player2ImageView.setImageResource(players[i + 1].image)
                player2NameTextView.text = players[i + 1].username

                val params2 = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f) // Use weight
                player2View.layoutParams = params2

                tableRow.addView(player2View)
            } else {
                val placeholderView = LinearLayout(requireContext()).apply {
                    layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
                    visibility = View.INVISIBLE
                }
                tableRow.addView(placeholderView)
            }
            playersTableLayout.addView(tableRow)
        }
    }
}
