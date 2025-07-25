// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SlasherNotificationDao {
    @Query("SELECT * FROM notifications")
    fun getAll(): Flow<List<SlasherNotification>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: SlasherNotification)

    @Delete
    suspend fun delete(notification: SlasherNotification)
}