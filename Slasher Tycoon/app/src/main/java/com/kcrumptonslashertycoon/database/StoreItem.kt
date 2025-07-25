// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// The stuff in my stores
@Entity(tableName = "store_items")
data class StoreItem(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val price: Int,
    val isPurchased: Boolean = false,
    val locationType: String = "",
    val levelRequired: Int = 1

)