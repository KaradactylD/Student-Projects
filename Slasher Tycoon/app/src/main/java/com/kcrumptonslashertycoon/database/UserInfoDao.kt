// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM user_info WHERE userId = :id")
    fun getUserInfo(id: String): Flow<UserInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(userInfo: UserInfo)

    @Update
    suspend fun update(userInfo: UserInfo)
}