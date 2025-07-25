// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Different scores depending on the room and the type of task
@Entity(tableName = "room_effects")
data class RoomEffect(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val room: String,
    val description: String,
    val effectType: String,
    val visitorBoost: Int,
    val timestamp: Long = System.currentTimeMillis()
)