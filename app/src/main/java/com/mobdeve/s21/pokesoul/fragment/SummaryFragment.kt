package com.mobdeve.s21.pokesoul.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.imageview.ShapeableImageView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.EditRunActivity
import com.mobdeve.s21.pokesoul.model.Player
import com.mobdeve.s21.pokesoul.model.Run
import com.squareup.picasso.Picasso

class SummaryFragment : Fragment() {
    private lateinit var editBtn: Button
    private lateinit var runTitleTv: TextView
    private lateinit var gameTitleTv: TextView
    private lateinit var playersTableLayout: TableLayout
    private var run: Run? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        runTitleTv = view.findViewById(R.id.runTitleTv)
        gameTitleTv = view.findViewById(R.id.gameTitleTv)
        playersTableLayout = view.findViewById(R.id.playersTl)
        editBtn = view.findViewById(R.id.editBtn) // Initialize edit button

        run = arguments?.getSerializable("RUN_INSTANCE") as? Run

        run?.let {
            runTitleTv.text = it.runName
            gameTitleTv.text = it.gameTitle
            populatePlayers(it.players)
        }

        editBtn.setOnClickListener {
            val intent = Intent(requireContext(), EditRunActivity::class.java)
            intent.putExtra("RUN_INSTANCE", run) // Pass the current Run instance to EditRunActivity
            startActivity(intent)
        }

        return view
    }

    private fun populatePlayers(players: List<Player>) {
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

            Picasso.get().load(players[i].image).into(player1ImageView)
            player1NameTextView.text = players[i].name

            val params1 = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f) // Use weight
            player1View.layoutParams = params1

            tableRow.addView(player1View)

            if (i + 1 < players.size) {
                val player2View = layoutInflater.inflate(R.layout.item_player, tableRow, false) as LinearLayout
                val player2ImageView = player2View.findViewById<ShapeableImageView>(R.id.playerSiv)
                val player2NameTextView = player2View.findViewById<TextView>(R.id.usernameTv)

                Picasso.get().load(players[i + 1].image).into(player2ImageView)
                player2NameTextView.text = players[i + 1].name

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
