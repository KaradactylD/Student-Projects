// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.Slasher

class SlasherAdapter(
    private var slashers: List<Slasher>,
    private var activeTaskLocations: List<String>,
    private val onSlasherClick: (Slasher) -> Unit
) : RecyclerView.Adapter<SlasherAdapter.SlasherViewHolder>() {

    class SlasherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val slasherButton: ImageButton = itemView.findViewById(R.id.slasherButton)
        val alertIcon: ImageView = itemView.findViewById(R.id.alertIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlasherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_slasher, parent, false)
        return SlasherViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlasherViewHolder, position: Int) {
        val slasher = slashers[position]

        // Set the button image based on location
        val buttonDrawable = when (slasher.locationType) {
            "Boiler Room" -> R.drawable.freddy_btn
            "Cabin" -> R.drawable.jason_btn
            "Suburbs" -> R.drawable.ghostface_btn
            "Swamp" -> R.drawable.victor_btn
            "Warehouse" -> R.drawable.jigsaw_btn
            "Hive" -> R.drawable.candyman_btn
            "High School" -> R.drawable.carrie_btn
            "Lair" -> R.drawable.leprechaun_btn
            else -> R.drawable.freddy_btn // just picked him as like a default if they dont' load or whatever
        }

        holder.slasherButton.setImageResource(buttonDrawable)
        holder.slasherButton.contentDescription = slasher.name

        //Unlocked vs Locked -  Locked buttons are grayed out
        holder.slasherButton.isEnabled = slasher.isUnlocked
        holder.slasherButton.alpha = if (slasher.isUnlocked) 1.0f else 0.5f

        // Exclamation mark to show if there's a task
        val hasTask = activeTaskLocations.contains(slasher.locationType)
        holder.alertIcon.visibility = if (hasTask) View.VISIBLE else View.GONE

        holder.slasherButton.setOnClickListener {
            if (slasher.isUnlocked) {
                onSlasherClick(slasher)

            }
        }
    }

    override fun getItemCount(): Int = slashers.size

    fun updateData(newSlashers: List<Slasher>, newActiveTaskLocations: List<String>) {
        slashers = newSlashers
        activeTaskLocations = newActiveTaskLocations
        notifyDataSetChanged()
    }
}