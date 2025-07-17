// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomEffectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(effect: RoomEffect)

    @Query("SELECT * FROM room_effects WHERE room = :room ORDER BY timestamp DESC")
    fun getEffectsForRoom(room: String): Flow<List<RoomEffect>>

    @Query("SELECT SUM(visitorBoost) FROM room_effects WHERE room = :room AND effectType = 'attraction'")
    fun getTotalAttraction(room: String): Flow<Int?>
}