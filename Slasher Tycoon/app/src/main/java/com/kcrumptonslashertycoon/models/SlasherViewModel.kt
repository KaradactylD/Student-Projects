// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.models

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.SlasherTaskManager
import com.kcrumptonslashertycoon.database.Collectible
import com.kcrumptonslashertycoon.database.RoomEffect
import com.kcrumptonslashertycoon.database.Slasher
import com.kcrumptonslashertycoon.database.UserInfo
import com.kcrumptonslashertycoon.database.UserTask
import com.kcrumptonslashertycoon.repository.SlasherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SlasherViewModel(application: Application, private val repository: SlasherRepository) : AndroidViewModel(application) {
    val userTaskDao = repository.userTaskDao

    private val lastKnownVisitors = mutableMapOf<String, Int>()

    fun getLastKnownVisitorCount(room: String): Int {
        return lastKnownVisitors[room] ?: 0
    }

    fun updateLastKnownVisitorCount(room: String, count: Int) {
        lastKnownVisitors[room] = count
    }

    private val _slashers = MutableStateFlow<List<Slasher>>(emptyList())
    val slashers: StateFlow<List<Slasher>> = _slashers

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo


    init {
        loadSlashers()
        initializeUserIfNeeded()
    }

    fun initializeUserIfNeeded() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        viewModelScope.launch {
            repository.getUserInfo(userId).collect { user ->
                if (user == null) {
                    val defaultUser = UserInfo(userId = userId, money = 1200)
                    repository.insertUserInfo(defaultUser)
                    _userInfo.value = defaultUser
                } else {
                    _userInfo.value = user
                }
            }
        }
    }
    fun getUserInfo(userId: String): Flow<UserInfo> {
        return repository.getUserInfo(userId)
    }

    fun updateUserInfo(userInfo: UserInfo) {
        viewModelScope.launch {
            repository.updateUserInfo(userInfo)
            _userInfo.value = userInfo

            // Check for unlockable slashers
            val currentSlashers = _slashers.value
            val newlyUnlocked = mutableListOf<Slasher>()

            currentSlashers.forEach { slasher ->
                if (!slasher.isUnlocked && userInfo.level >= slasher.requiredLevel) {
                    val updated = slasher.copy(isUnlocked = true)
                    repository.update(updated)
                    newlyUnlocked.add(updated)
                }
            }

            // Show Toasts for each unlock
            withContext(Dispatchers.Main) {
                newlyUnlocked.forEach {
                    Toast.makeText(
                        getApplication<Application>().applicationContext,
                        "🎉 You've unlocked ${it.name}!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    private fun loadSlashers() {
        viewModelScope.launch {
            repository.getSlashers().collect { incomingList ->
                val currentList = _slashers.value.toMutableList()

                incomingList.forEach { incoming ->
                    val index = currentList.indexOfFirst { it.name == incoming.name }
                    if (index != -1) {
                        val current = currentList[index]

                        // Only replace if incoming is newer - Had to use this because my stats were getting jacked up
                        if (incoming.visitorCount >= current.visitorCount) {
                            currentList[index] = incoming

                        } else {
                            Log.d("SlasherUI", "Ignored stale: ${incoming.name}, visitors=${incoming.visitorCount} < ${current.visitorCount}")
                        }
                    } else {
                        // Add/Create New Slasher - probably won't use
                        currentList.add(incoming)

                    }
                }

                _slashers.value = currentList
            }
        }
    }

    // Slashers only unlock on specific levels
    private fun getRequiredLevelForSlasher(name: String): Int {
        return when (name) {
            "Freddy Krueger" -> 1
            "Jason Voorhees" -> 2
            "Ghostface" -> 3
            "Victor Crowley" -> 4
            "Jigsaw" -> 5
            "Candyman" -> 6
            "Carrie" -> 7
            "Leprechaun" -> 8
            else -> Int.MAX_VALUE
        }
    }

    // Unlock if it's time
    fun unlockEligibleSlashers(userLevel: Int): List<String> {
        val newlyUnlocked = mutableListOf<String>()

        val currentSlashers = _slashers.value
        val updated = currentSlashers.map { slasher ->
            if (!slasher.isUnlocked && userLevel >= getRequiredLevelForSlasher(slasher.name)) {
                newlyUnlocked.add(slasher.name)
                slasher.copy(isUnlocked = true)
            } else {
                slasher
            }
        }

        updated.forEach { updateSlasher(it) }

        return newlyUnlocked
    }

    fun logAllSlashers() {
        viewModelScope.launch {
            repository.getAllSlashers().collect { list ->
                list.forEach {

                }
            }
        }
    }

    fun updateSlasher(updated: Slasher) {
        viewModelScope.launch {
            repository.update(updated)

            // More Debuggy stuff - kept overwriting my stuff
            val currentList = _slashers.value.toMutableList()
            val index = currentList.indexOfFirst { it.name == updated.name }

            if (index != -1) {
                val current = currentList[index]

                // Check for visitor count, make sure it's not resetting
                if (updated.visitorCount >= current.visitorCount) {
                    currentList[index] = updated
                    _slashers.value = currentList

                } else {
                    Log.d("SlasherUI", "Skipped stale update: ${updated.name}, visitors=${updated.visitorCount} < ${current.visitorCount}")
                }
            }
        }
    }

    fun addKillsToSlasher(slasherId: Int, killsToAdd: Int) {
        viewModelScope.launch {
            val slasher = _slashers.value.getOrNull(slasherId - 1)
            slasher?.let {
                val updated = it.copy(killsToday = it.killsToday + killsToAdd)
                repository.update(it.copy(killsToday = it.killsToday + killsToAdd))
            }
        }
    }

    fun insertOrUpdateSlasherIfNeeded(slasher: Slasher) {
        viewModelScope.launch {
            val existing = repository.getSlasherByName(slasher.name)

            if (existing == null) {
                repository.insert(slasher)
            } else {
                // Only update if something is different
                val updated = existing.copy(
                    kills = slasher.kills,
                    weapon = slasher.weapon,
                    locationType = slasher.locationType,
                    requiredLevel = slasher.requiredLevel
                )
                repository.update(updated)
            }
        }
    }

    fun getActiveTask(userId: String, slasherId: Int): Flow<UserTask?> {
        return repository.getActiveTask(userId, slasherId)
    }

    fun saveUserTask(task: UserTask) {
        viewModelScope.launch {
            repository.insertOrUpdateUserTask(task)
        }
    }
    fun insertOrUpdateUserTask(task: UserTask) {
        viewModelScope.launch {
            repository.insertOrUpdateUserTask(task)
        }
    }

    fun getCompletedTasks(userId: String): Flow<List<UserTask>> {
        return repository.getCompletedTasks(userId)
    }

    fun loadOrInsertNextTask(userId: String, slasherId: Int) {
        viewModelScope.launch {
            val user = getUserInfo(userId).firstOrNull() ?: return@launch
            val userLevel = user.level
            SlasherTaskManager.loadOrInsertNextTask(userId, slasherId, userLevel, userTaskDao)
        }
    }

    fun logRoomEffect(effect: RoomEffect) {
        viewModelScope.launch {
            repository.logRoomEffect(effect)
        }
    }

    fun getRoomEffects(locationType: String): Flow<List<RoomEffect>> {
        return repository.getRoomEffects(locationType)
    }

    fun addCollectible(name: String) {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val collectible = Collectible(
                userId = userId,
                name = name,
                collectedAt = System.currentTimeMillis()
            )
            repository.insertCollectible(collectible)
        }
    }

    fun getCollectiblesForUser(userId: String): Flow<List<Collectible>> {
        return repository.getCollectiblesForUser(userId)
    }

    fun addMoney(amount: Int) {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val user = getUserInfo(userId).firstOrNull() ?: return@launch
            val updatedUser = user.copy(money = user.money + amount)
            updateUserInfo(updatedUser)
        }
    }

}