// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.Collectible
import android.text.format.DateFormat

class CollectibleAdapter : ListAdapter<Collectible, CollectibleAdapter.CollectibleViewHolder>(
    DiffCallback()
) {

    class CollectibleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameText: TextView = itemView.findViewById(R.id.collectibleNameText)
        private val dateText: TextView = itemView.findViewById(R.id.collectibleDateText)

        fun bind(collectible: Collectible) {
            nameText.text = collectible.name
            val formattedDate = DateFormat.format("MMM d, yyyy h:mm a", collectible.collectedAt)
            dateText.text = "Found on: $formattedDate"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectibleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_collectible, parent, false)
        return CollectibleViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectibleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Had to look this up. Wanted it to show "Empty Keg x2" instead of "Empty Keg" twice...
    class DiffCallback : DiffUtil.ItemCallback<Collectible>() {
        override fun areItemsTheSame(old: Collectible, new: Collectible) = old.name == new.name
        override fun areContentsTheSame(old: Collectible, new: Collectible) = old == new
    }
}