// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Collectibles you can get from doing special tasks
@Entity(tableName = "collectibles")
data class Collectible(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val name: String,
    val collectedAt: Long,
    val count: Int = 1
)
