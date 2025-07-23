// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// Stuff about the users
@Entity(tableName = "user_info")
data class UserInfo(
    @PrimaryKey val userId: String,
    val money: Int = 0,
    val level: Int = 1,
    val xp: Int = 0,
    val lastCookoffTime: Long = 0L, // Set it so you can only do the Chili Cookoff once every 5 minutes
    val lastPhotoTime: Long = 0L // Same for photos, once every 5 minutes
)