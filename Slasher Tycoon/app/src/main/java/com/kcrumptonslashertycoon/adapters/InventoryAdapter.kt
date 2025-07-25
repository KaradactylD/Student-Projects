// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.UserInventoryItem

class InventoryAdapter(
    private var items: List<UserInventoryItem>
) : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    class InventoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.itemName)
        val quantityText: TextView = view.findViewById(R.id.itemQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_inventory, parent, false)
        return InventoryViewHolder(view)
    }

    // Just showing the items you have and how many
    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        val item = items[position]
        holder.nameText.text = item.itemName
        holder.quantityText.text = "Qty: ${item.quantity}"
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<UserInventoryItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}