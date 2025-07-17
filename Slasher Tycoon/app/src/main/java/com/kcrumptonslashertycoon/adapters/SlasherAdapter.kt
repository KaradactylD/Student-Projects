// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.Slasher

class SlasherAdapter(private var slashers: List<Slasher>) :
    RecyclerView.Adapter<SlasherAdapter.SlasherViewHolder>() {

    class SlasherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val slasherName: TextView = itemView.findViewById(R.id.slasherNameTextView)
        val slasherKills: TextView = itemView.findViewById(R.id.slasherKillsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlasherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_slasher, parent, false)
        return SlasherViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlasherViewHolder, position: Int) {
        val slasher = slashers[position]
        holder.slasherName.text = slasher.name
        holder.slasherKills.text = "Kills: ${slasher.kills}"
    }

    override fun getItemCount(): Int = slashers.size
    fun updateData(newSlashers: List<Slasher>) {
        slashers = newSlashers
        notifyDataSetChanged()
    }
}