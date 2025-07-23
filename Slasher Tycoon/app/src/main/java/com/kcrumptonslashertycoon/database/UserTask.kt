// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.ColumnInfo
import androidx.room.Entity

// Keeping track of the User's tasks, what they get for it, etc
@Entity(
    tableName = "user_tasks",
    primaryKeys = ["userId", "slasherId", "taskMessage"]
)
data class UserTask(
    val slasherId: Int,
    val userId: String,
    val taskMessage: String,
    val isCompleted: Boolean = false,
    val rewardMoney: Int = 0,
    val killsEarned: Int = 0,
    val xpReward: Int = 0,
    @ColumnInfo(name = "requiredKeyword")
    val requiredKeyword: String = "",
    val effectType: String = "attraction",
    val createdAt: Long = System.currentTimeMillis(),
    val requiredLevel: Int = 1,
    val taskType: String = "regular",
    val collectibleReward: String? = null

)