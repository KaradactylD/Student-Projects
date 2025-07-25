// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.ChiliIngredient

class IngredientAdapter(
    private val ingredients: List<ChiliIngredient>,
    private val selectedIngredients: Set<ChiliIngredient>,
    private val onClick: (ChiliIngredient) -> Unit
) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameText: TextView = view.findViewById(R.id.ingredientTextView)
        private val imageView: ImageView = view.findViewById(R.id.ingredientImage)

        // Check marks aren't slashery... gonna use a Skull and Crossbones!
        fun bind(ingredient: ChiliIngredient) {
            val checkmark = if (selectedIngredients.contains(ingredient)) " ☠️" else ""
            nameText.text = ingredient.name + checkmark

            // Load the picture that goes with the ingredient, I named 'em the same to make it easier
            val context = itemView.context
            val resourceName = ingredient.name
                .lowercase()
                .replace(" ", "_")
                .replace("'", "")
                .replace("-", "")
                .replace("’", "")
            val imageResId =
                context.resources.getIdentifier(resourceName, "drawable", context.packageName)
            imageView.setImageResource(
                if (imageResId != 0) imageResId else R.drawable.default_ingredient_image
            )

            // Background color for the one you pick
            itemView.setBackgroundColor(
                if (selectedIngredients.contains(ingredient)) Color.parseColor("#66FF3300")
                else Color.TRANSPARENT
            )

            itemView.setOnClickListener { onClick(ingredient) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_ingredient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size
}