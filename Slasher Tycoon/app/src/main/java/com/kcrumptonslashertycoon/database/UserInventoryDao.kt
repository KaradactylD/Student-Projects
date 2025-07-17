// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserInventoryDao {
    @Query("SELECT * FROM user_inventory WHERE userId = :userId")
    fun getInventoryForUser(userId: String): Flow<List<UserInventoryItem>>

    @Query("SELECT * FROM user_inventory WHERE userId = :userId AND itemId = :itemId LIMIT 1")
    suspend fun getInventoryItemByUserAndItem(userId: String, itemId: UUID): UserInventoryItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserInventoryItem)

    @Update
    suspend fun update(item: UserInventoryItem)

    @Delete
    suspend fun delete(item: UserInventoryItem)
}