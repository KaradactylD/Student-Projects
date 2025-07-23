// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// User's inventory - item and qty
@Entity(tableName = "user_inventory")
data class UserInventoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val itemId: UUID,
    val itemName: String,
    val quantity: Int = 1
)