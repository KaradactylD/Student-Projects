// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTaskDao {
    @Query("SELECT * FROM user_tasks WHERE userId = :userId AND slasherId = :slasherId AND isCompleted = 0 LIMIT 1")
    fun getActiveTask(userId: String, slasherId: Int): Flow<UserTask?>

    @Query("SELECT * FROM user_tasks WHERE userId = :userId AND slasherId = :slasherId AND isCompleted = 0 LIMIT 1")
    suspend fun getActiveTaskOnce(userId: String, slasherId: Int): UserTask?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: UserTask)

    @Query("UPDATE user_tasks SET isCompleted = 1 WHERE userId = :userId AND slasherId = :slasherId")
    suspend fun markTaskComplete(userId: String, slasherId: Int)

    @Query("SELECT * FROM user_tasks WHERE userId = :userId AND slasherId = :slasherId LIMIT 1")
    fun getTaskEvenIfCompleted(userId: String, slasherId: Int): Flow<UserTask?>

    @Query("SELECT * FROM user_tasks WHERE userId = :userId AND isCompleted = 1")
    fun getCompletedTasks(userId: String): Flow<List<UserTask>>

    @Query("SELECT * FROM user_tasks WHERE userId = :userId AND isCompleted = 1")
    suspend fun getCompletedTasksOnce(userId: String): List<UserTask>

    @Query("SELECT COUNT(*) FROM user_tasks WHERE userId = :userId AND slasherId = :slasherId AND isCompleted = 1")
    suspend fun getCompletedTaskCount(userId: String, slasherId: Int): Int

    @Query("SELECT taskMessage FROM user_tasks WHERE userId = :userId AND slasherId = :slasherId")
    suspend fun getAllTaskMessagesForSlasher(userId: String, slasherId: Int): List<String>

    @Query("SELECT * FROM user_tasks WHERE userId = :userId")
    suspend fun getAllCompletedTasksOnce(userId: String): List<UserTask>

    @Query("""
    SELECT * FROM user_tasks 
    WHERE userId = :userId 
    AND slasherId = :slasherId 
    AND isCompleted = 0 
    AND requiredLevel <= :userLevel 
    ORDER BY createdAt ASC 
    LIMIT 1
""")
    suspend fun getNextEligibleTask(userId: String, slasherId: Int, userLevel: Int): UserTask?

    @Query("""
    SELECT * FROM user_tasks
    WHERE userId = :userId AND slasherId = :slasherId AND isCompleted = 0
    LIMIT 1
""")
    suspend fun getActiveTaskForSlasher(userId: String, slasherId: Int): UserTask?

    @Query("""
    SELECT * FROM user_tasks
    WHERE userId = '' AND slasherId = :slasherId AND requiredLevel <= :userLevel
    ORDER BY requiredLevel ASC
    LIMIT 1
""")
    suspend fun getNextUnassignedTask(slasherId: Int, userLevel: Int): UserTask?

    @Query("SELECT * FROM user_tasks WHERE userId = '' AND slasherId = :slasherId")
    suspend fun getAllUnassignedTasks(slasherId: Int): List<UserTask>

    @Query("SELECT * FROM user_tasks WHERE taskType = 'freeplay'")
    fun getAllFreeplayTasks(): Flow<List<UserTask>>
}