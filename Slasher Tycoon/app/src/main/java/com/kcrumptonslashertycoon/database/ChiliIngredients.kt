// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

// Available ingredients for the Chili Cookoff
data class ChiliIngredient(
    val name: String,
    val flavorPoints: Int,
    val scarePoints: Int,
    val isMeat: Boolean = false
)
