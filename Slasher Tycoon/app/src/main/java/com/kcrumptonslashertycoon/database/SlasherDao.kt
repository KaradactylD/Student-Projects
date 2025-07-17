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
interface SlasherDao {

    @Query("SELECT * FROM slasher")
    fun getSlashers(): Flow<List<Slasher>>

    @Query("SELECT * FROM Slasher")
    fun getAllSlashers(): Flow<List<Slasher>>

    @Query("SELECT * FROM slasher WHERE id = :id")
    suspend fun getSlasher(id: UUID): Slasher

    @Query("SELECT * FROM slasher WHERE name = :name LIMIT 1")
    suspend fun getSlasherByName(name: String): Slasher?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(slasher: Slasher)

    @Update
    suspend fun update(slasher: Slasher)

    @Delete
    suspend fun delete(slasher: Slasher)
}
