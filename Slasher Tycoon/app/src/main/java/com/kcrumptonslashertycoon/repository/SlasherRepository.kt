// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.repository

import android.content.Context
import androidx.room.Room
import com.kcrumptonslashertycoon.database.Collectible
import com.kcrumptonslashertycoon.database.CollectibleDao
import com.kcrumptonslashertycoon.database.RoomEffect
import com.kcrumptonslashertycoon.database.RoomEffectDao
import com.kcrumptonslashertycoon.database.Slasher
import com.kcrumptonslashertycoon.database.SlasherDao
import com.kcrumptonslashertycoon.database.SlasherDatabase
import com.kcrumptonslashertycoon.database.StoreItem
import com.kcrumptonslashertycoon.database.StoreItemDao
import com.kcrumptonslashertycoon.database.UserInfo
import com.kcrumptonslashertycoon.database.UserInfoDao
import com.kcrumptonslashertycoon.database.UserInventoryDao
import com.kcrumptonslashertycoon.database.UserInventoryItem
import com.kcrumptonslashertycoon.database.UserTask
import com.kcrumptonslashertycoon.database.UserTaskDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class SlasherRepository private constructor(
    private val slasherDao: SlasherDao,
    private val storeItemDao: StoreItemDao,
    private val userInfoDao: UserInfoDao,
    private val userInventoryDao: UserInventoryDao,
    internal val userTaskDao: UserTaskDao,
    private val roomEffectDao: RoomEffectDao,
    private val collectibleDao: CollectibleDao
) {

    // Slasher Stuff
    fun getSlashers(): Flow<List<Slasher>> = slasherDao.getSlashers()


    fun getAllSlashers(): Flow<List<Slasher>> {
        return slasherDao.getAllSlashers()
    }

    suspend fun getSlasherByName(name: String): Slasher? {
        return slasherDao.getSlasherByName(name)
    }

    suspend fun insert(slasher: Slasher) = slasherDao.insert(slasher)

    suspend fun update(slasher: Slasher) = slasherDao.update(slasher)

    suspend fun delete(slasher: Slasher) = slasherDao.delete(slasher)


    // Store Stuff
    fun getStoreItems(): Flow<List<StoreItem>> = storeItemDao.getItems()

    fun getItemsForRoom(roomName: String): Flow<List<StoreItem>> {
        return storeItemDao.getStoreItemsForRoom(roomName)
    }

    fun getItemsForExactLevel(location: String, userLevel: Int): Flow<List<StoreItem>> {
        return storeItemDao.getItemsForExactLevel(location, userLevel)
            .onEach { items ->

            }
    }
    suspend fun insertStoreItem(item: StoreItem) = storeItemDao.insertItem(item)
    suspend fun deleteStoreItem(item: StoreItem) = storeItemDao.deleteItem(item)


    // User Stuff
    fun getUserInfo(userId: String): Flow<UserInfo> {
        return userInfoDao.getUserInfo(userId)
    }

    suspend fun updateUserInfo(userInfo: UserInfo) = userInfoDao.update(userInfo)

    suspend fun insertUserInfo(userInfo: UserInfo) = userInfoDao.insertOrUpdate(userInfo)


    // Inventory Stuff
    fun getInventory(userId: String): Flow<List<UserInventoryItem>> =
        userInventoryDao.getInventoryForUser(userId)

    suspend fun addToInventory(newItem: UserInventoryItem) {
        val existing = userInventoryDao.getInventoryItemByUserAndItem(newItem.userId, newItem.itemId)
        if (existing != null) {
            val updated = existing.copy(quantity = existing.quantity + 1)
            userInventoryDao.update(updated)
        } else {
            userInventoryDao.insert(newItem)
        }
    }
    suspend fun updateItem(item: UserInventoryItem) {
        userInventoryDao.update(item)
    }

    suspend fun deleteItem(item: UserInventoryItem) =
        userInventoryDao.delete(item)


    // Task Stuff
    fun getActiveTask(userId: String, slasherId: Int): Flow<UserTask?> {
        return userTaskDao.getActiveTask(userId, slasherId)
    }
    suspend fun insertOrUpdateUserTask(task: UserTask) {
        userTaskDao.insertTask(task)
    }

    fun getCompletedTasks(userId: String): Flow<List<UserTask>> {
        return userTaskDao.getCompletedTasks(userId)
    }

    suspend fun insertCollectible(collectible: Collectible) {
        collectibleDao.insertCollectible(collectible)
    }

    fun getCollectiblesForUser(userId: String): Flow<List<Collectible>> {
        return collectibleDao.getCollectiblesForUser(userId)
    }

    // Room Stuff
    suspend fun logRoomEffect(effect: RoomEffect) = roomEffectDao.insert(effect)

    fun getRoomEffects(room: String): Flow<List<RoomEffect>> = roomEffectDao.getEffectsForRoom(room)

    fun getAttractionScore(room: String): Flow<Int> = roomEffectDao.getTotalAttraction(room).map { it ?: 0 }

    companion object {
        @Volatile
        private var INSTANCE: SlasherRepository? = null

        fun getInstance(context: Context): SlasherRepository {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    SlasherDatabase::class.java,
                    "slasher-database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                val instance = SlasherRepository(
                    database.slasherDao(),
                    database.storeItemDao(),
                    database.userInfoDao(),
                    database.userInventoryDao(),
                    database.userTaskDao(),
                    database.roomEffectDao(),
                    database.collectibleDao()

                )
                INSTANCE = instance
                instance
            }
        }
    }
}