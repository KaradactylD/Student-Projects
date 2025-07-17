// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.StoreItem

class StoreAdapter(
    private var items: List<StoreItem>,
    private val onBuyClick: (StoreItem) -> Unit
) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    // The stuff in the stores
    inner class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemDescription: TextView = itemView.findViewById(R.id.itemDescription)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
        val buyButton: Button = itemView.findViewById(R.id.buyButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val item = items[position]
        holder.itemName.text = item.name
        holder.itemDescription.text = item.description
        holder.itemPrice.text = "$${item.price}"

        holder.buyButton.setOnClickListener {
            onBuyClick(item)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<StoreItem>) {
        items = newItems.sortedBy { it.name.lowercase() }
        notifyDataSetChanged()
    }
}