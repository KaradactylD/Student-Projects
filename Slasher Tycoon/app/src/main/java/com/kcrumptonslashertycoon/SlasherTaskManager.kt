// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon

import android.util.Log
import com.kcrumptonslashertycoon.database.UserTask
import com.kcrumptonslashertycoon.database.UserTaskDao

object SlasherTaskManager {

    // Tasks for the slashers, rewards, required levels, etc
    fun getTaskQueueForSlasher(userId: String, slasherId: Int): List<UserTask> {
        return when (slasherId) {
            1 -> listOf( // Freddy
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Fuel the furnace",
                    rewardMoney = 80,
                    killsEarned = 1,
                    xpReward = 15,
                    requiredKeyword = "fuel",
                    effectType = "attraction",
                    requiredLevel = 1
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Sharpen Freddy's glove",
                    rewardMoney = 175,
                    killsEarned = 1,
                    xpReward = 25,
                    requiredKeyword = "blade",
                    effectType = "scare",
                    requiredLevel = 1
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Whatever you do... Don't. Fall. Asleep.",
                    rewardMoney = 250,
                    killsEarned = 2,
                    xpReward = 35,
                    requiredKeyword = "coffee",
                    effectType = "attraction",
                    requiredLevel = 1
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Let's paint the ceiling... red is a nice color...",
                    rewardMoney = 250,
                    killsEarned = 2,
                    xpReward = 35,
                    requiredKeyword = "tina",
                    effectType = "scare",
                    requiredLevel = 1
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "\"It'll help you sleep.\"",
                    rewardMoney = 200,
                    killsEarned = 5,
                    xpReward = 55,
                    requiredKeyword = "milk",
                    effectType = "scare",
                    requiredLevel = 1
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Not the worst thing to happen in Johnny Depp's bed.",
                    rewardMoney = 500,
                    killsEarned = 8,
                    xpReward = 75,
                    requiredKeyword = "sheets",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "\"I'm your boyfriend now, Nancy!\"",
                    rewardMoney = 800,
                    killsEarned = 8,
                    xpReward = 95,
                    requiredKeyword = "phone",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Shhhh... someone's sleepwalking...",
                    rewardMoney = 950,
                    killsEarned = 8,
                    xpReward = 95,
                    requiredKeyword = "puppet",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "You can check in...but you can't check out.",
                    rewardMoney = 1000,
                    killsEarned = 8,
                    xpReward = 95,
                    requiredKeyword = "roach",
                    effectType = "attraction",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Rod’s having trouble sleeping.",
                    rewardMoney = 800,
                    killsEarned = 6,
                    xpReward = 60,
                    requiredKeyword = "rope",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Spruce up the TV room.",
                    rewardMoney = 1050,
                    killsEarned = 5,
                    xpReward = 75,
                    requiredKeyword = "television",
                    effectType = "scare",
                    requiredLevel = 5
                ),

            )

            2 -> listOf( // Jason
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Clean Jason's machete",
                    rewardMoney = 75,
                    killsEarned = 2,
                    xpReward = 15,
                    requiredKeyword = "cleaner",
                    effectType = "scare",
                    requiredLevel = 2
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Bitches be trippin'.",
                    rewardMoney = 150,
                    killsEarned = 5,
                    xpReward = 25,
                    requiredKeyword = "wire",
                    effectType = "scare",
                    requiredLevel = 2
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "It's too dark in here...",
                    rewardMoney = 200,
                    killsEarned = 2,
                    xpReward = 35,
                    requiredKeyword = "lantern",
                    effectType = "attraction",
                    requiredLevel = 2
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Kevin Bacon sucks anyway",
                    rewardMoney = 350,
                    killsEarned = 1,
                    xpReward = 55,
                    requiredKeyword = "arrow",
                    effectType = "scare",
                    requiredLevel = 2
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Sack 'em and Smack 'em!",
                    rewardMoney = 730,
                    killsEarned = 2,
                    xpReward = 75,
                    requiredKeyword = "bag",
                    effectType = "scare",
                    requiredLevel = 2
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Aim for the eyes.",
                    rewardMoney = 400,
                    killsEarned = 2,
                    xpReward = 75,
                    requiredKeyword = "harpoon",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Not the rod she planned on taking tonight.",
                    rewardMoney = 700,
                    killsEarned = 2,
                    xpReward = 100,
                    requiredKeyword = "tent",
                    effectType = "scare",
                    requiredLevel = 5
                ),

            )

            3 -> listOf( // Ghostface
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "Extra butter, extra blood!",
                    rewardMoney = 75,
                    killsEarned = 1,
                    xpReward = 15,
                    requiredKeyword = "popcorn",
                    effectType = "attraction",
                    requiredLevel = 3
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "\"What's your favorite scary movie?\"",
                    rewardMoney = 150,
                    killsEarned = 1,
                    xpReward = 25,
                    requiredKeyword = "phone",
                    effectType = "attraction",
                    requiredLevel = 3
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "He said he'd be right back. Make sure he isn't.",
                    rewardMoney = 250,
                    killsEarned = 1,
                    xpReward = 35,
                    requiredKeyword = "beer",
                    effectType = "scare",
                    requiredLevel = 3
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "Don't Fuck with the original!",
                    rewardMoney = 500,
                    killsEarned = 3,
                    xpReward = 55,
                    requiredKeyword = "dvd",
                    effectType = "attraction",
                    requiredLevel = 3
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "There's always two killers...",
                    rewardMoney = 750,
                    killsEarned = 1,
                    xpReward = 75,
                    requiredKeyword = "fake",
                    effectType = "attraction",
                    requiredLevel = 3
                ),

            )

            4 -> listOf( // Victor Crowley
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Victor's hatchet is getting dull",
                    rewardMoney = 75,
                    killsEarned = 1,
                    xpReward = 15,
                    requiredKeyword = "sharpener",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Someone's in the shed... ",
                    rewardMoney = 250,
                    killsEarned = 2,
                    xpReward = 25,
                    requiredKeyword = "gas",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Victor doesn't collect beads... he collects bodies.",
                    rewardMoney = 400,
                    killsEarned = 12,
                    xpReward = 35,
                    requiredKeyword = "beads",
                    effectType = "attraction",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Boat Tours are illegal in this part of the swamp...",
                    rewardMoney = 650,
                    killsEarned = 15,
                    xpReward = 55,
                    requiredKeyword = "tour",
                    effectType = "attraction",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Victor hates liars.",
                    rewardMoney = 600,
                    killsEarned = 8,
                    xpReward = 75,
                    requiredKeyword = "belt",
                    effectType = "scare",
                    requiredLevel = 4
                ),

            )

            5 -> listOf( // Jigsaw
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "\"I want to play a game...\"",
                    rewardMoney = 100,
                    killsEarned = 1,
                    xpReward = 15,
                    requiredKeyword = "tape",
                    effectType = "attraction",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "Fix Billy's Bike",
                    rewardMoney = 100,
                    killsEarned = 2,
                    xpReward = 25,
                    requiredKeyword = "bike",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "Frame Dr. Gordon",
                    rewardMoney = 300,
                    killsEarned = 0,
                    xpReward = 35,
                    requiredKeyword = "penlight",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "X Marks the spot",
                    rewardMoney = 350,
                    killsEarned = 1,
                    xpReward = 55,
                    requiredKeyword = "blacklight",
                    effectType = "attraction",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "Live or Die, Dr. Gordon. Make your choice.",
                    rewardMoney = 800,
                    killsEarned = 8,
                    xpReward = 75,
                    requiredKeyword = "saw",
                    effectType = "scare",
                    requiredLevel = 5
                )
            )
            6 -> listOf( // Candyman
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "Candyman, Candyman, Candyman, Candyman, Candyman",
                    rewardMoney = 980,
                    killsEarned = 8,
                    xpReward = 75,
                    requiredKeyword = "mirror",
                    effectType = "scare",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "There was nothing sweet about the crime scene...",
                    rewardMoney = 1100,
                    killsEarned = 6,
                    xpReward = 55,
                    requiredKeyword = "candy",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "The walls of Cabrini-Green dripped with my swarm’s curse.",
                    rewardMoney = 1250,
                    killsEarned = 9,
                    xpReward = 75,
                    requiredKeyword = "Honeycomb",
                    effectType = "scare",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "She mocked his story. He painted hers... in blood.",
                    rewardMoney = 780,
                    killsEarned = 7,
                    xpReward = 65,
                    requiredKeyword = "art",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "Blood soaked the child’s blanket. Bees circled her skull.",
                    rewardMoney = 1700,
                    killsEarned = 7,
                    xpReward = 105,
                    requiredKeyword = "ash",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
            )

            else -> emptyList()
        }
    }

    // Getting the tasks, showing what's active, etc for the users
    suspend fun loadOrInsertNextTask(
        userId: String,
        slasherId: Int,
        userLevel: Int,
        dao: UserTaskDao
    ) {
        val active = dao.getActiveTaskOnce(userId, slasherId)

        val allMessages = dao.getAllTaskMessagesForSlasher(userId, slasherId)
        val completedTasks = dao.getAllCompletedTasksOnce(userId)
            .filter { it.slasherId == slasherId && it.isCompleted }

        val queue = getTaskQueueForSlasher(userId, slasherId)

        val completedMessages = completedTasks.map { it.taskMessage }


        val nextTask = queue.firstOrNull { task ->
            val notCompleted = task.taskMessage !in completedMessages
            val notActive = task.taskMessage != active?.taskMessage
            val levelOk = (task.requiredLevel ?: 1) <= userLevel


            notCompleted && notActive && levelOk
        }


        if (nextTask != null && active == null) {
            dao.insertTask(nextTask)

        }
    }
}
