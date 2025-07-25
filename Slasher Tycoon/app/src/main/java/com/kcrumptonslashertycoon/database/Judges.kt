// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

// The chili Judges - Texas Chainsaw Massacre Characters :)
data class Judge(
    val name: String,
    val loves: List<String>,
    val hates: List<String>,
    val flavorThreshold: Int,
    val scareThreshold: Int
)
