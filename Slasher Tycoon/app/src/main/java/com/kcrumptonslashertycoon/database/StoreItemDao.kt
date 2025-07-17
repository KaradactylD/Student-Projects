// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreItemDao {
    @Query("SELECT * FROM store_items")
    fun getItems(): Flow<List<StoreItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: StoreItem)

    @Update
    suspend fun updateItem(item: StoreItem)

    @Delete
    suspend fun deleteItem(item: StoreItem)

    @Query("SELECT * FROM store_items WHERE locationType = :room")
    fun getStoreItemsForRoom(room: String): Flow<List<StoreItem>>

    @Query("SELECT * FROM store_items WHERE locationType = :location AND levelRequired <= :userLevel")
    fun getItemsForExactLevel(location: String, userLevel: Int): Flow<List<StoreItem>>
}