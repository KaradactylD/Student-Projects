// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectibleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCollectible(collectible: Collectible)

    @Query("SELECT * FROM collectibles WHERE userId = :userId ORDER BY collectedAt DESC")
    fun getCollectiblesForUser(userId: String): Flow<List<Collectible>>
}