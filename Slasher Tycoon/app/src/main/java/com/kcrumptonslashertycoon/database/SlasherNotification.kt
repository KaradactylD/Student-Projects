// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Notifications for Tasks
@Entity(tableName = "notifications")
data class SlasherNotification(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val slasherName: String,
    val message: String,
    val locationType: String
)