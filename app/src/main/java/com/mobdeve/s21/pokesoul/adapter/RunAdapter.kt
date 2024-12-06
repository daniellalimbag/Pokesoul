package com.mobdeve.s21.pokesoul.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.activity.RunDetailsActivity
import com.mobdeve.s21.pokesoul.model.Run
import com.mobdeve.s21.pokesoul.viewholder.RunViewHolder

class RunAdapter(private val runs: List<Run>) : RecyclerView.Adapter<RunViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_run, parent, false)
        return RunViewHolder(view)
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val run = runs[position]
        holder.bind(run)

        // Set an onClickListener to pass the Run instance to RunDetailsActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, RunDetailsActivity::class.java)

            intent.putExtra("RUN_INSTANCE", run)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return runs.size
    }
}
