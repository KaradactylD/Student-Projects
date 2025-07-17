// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon

import com.kcrumptonslashertycoon.database.UserTask

// Secret tasks, completed by using certain items without a prompt
object FreeplayManager {

    fun getTaskForItem(
        itemName: String,
        userId: String,
        slasherId: Int,
        userLevel: Int
    ): UserTask? {
        val lowerName = itemName.lowercase()

        return when {
            // Freddy (Boiler Room)
            (lowerName.contains("detergent")) && userLevel >= 1 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You washed Freddy's sweater!",
                isCompleted = true,
                rewardMoney = 25,
                xpReward = 20,
                killsEarned = 0,
                requiredKeyword = "detergent",
                effectType = "attraction",
                requiredLevel = 1,
                taskType = "freeplay",
                collectibleReward = "Hell's Fabric Softener"
            )

            lowerName.contains("dj") && userLevel >= 1 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You successfully bribed the DJ!",
                isCompleted = true,
                rewardMoney = 350,
                xpReward = 30,
                killsEarned = 0,
                requiredKeyword = "dj",
                effectType = "attraction",
                requiredLevel = 1,
                taskType = "freeplay",
                collectibleReward = "Thriller cassette tape"
            )

            lowerName.contains("pills") && userLevel >= 1 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You spiked the punch!",
                isCompleted = true,
                rewardMoney = 680,
                xpReward = 50,
                killsEarned = 2,
                requiredKeyword = "pills",
                effectType = "scare",
                requiredLevel = 1,
                taskType = "freeplay",
                collectibleReward = "Nightcap Cocktail"
            )

            // Jason (Cabin)
            lowerName.contains("party") && userLevel >= 2 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You posted fake party flyers! Let's see how many suckers show up.",
                isCompleted = true,
                rewardMoney = 150,
                xpReward = 30,
                killsEarned = 1,
                requiredKeyword = "party",
                effectType = "attraction",
                requiredLevel = 2,
                taskType = "freeplay",
                collectibleReward = "Crystal Lake Event Permit"
            )

            lowerName.contains("bear") && userLevel >= 2 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You set bear traps!",
                isCompleted = true,
                rewardMoney = 315,
                xpReward = 25,
                killsEarned = 1,
                requiredKeyword = "bear",
                effectType = "attraction",
                requiredLevel = 2,
                taskType = "freeplay",
                collectibleReward = "Trail Cam Footage"
            )

            lowerName.contains("kegs") && userLevel >= 2 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "Frat boys can't resist free beer.",
                isCompleted = true,
                rewardMoney = 400,
                xpReward = 60,
                killsEarned = 3,
                requiredKeyword = "kegs",
                effectType = "attraction",
                requiredLevel = 2,
                taskType = "freeplay",
                collectibleReward = "Empty Keg"
            )

            // Ghostface (Suburbs)
            lowerName.contains("polish") && userLevel >= 3 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You shined Ghostface's mask!",
                isCompleted = true,
                rewardMoney = 230,
                xpReward = 15,
                killsEarned = 0,
                requiredKeyword = "polish",
                effectType = "attraction",
                requiredLevel = 3,
                taskType = "freeplay",
                collectibleReward = "Mask Wax"
            )

            lowerName.contains("floor") && userLevel >= 3 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "Used floor plan (freeplay)",
                isCompleted = true,
                rewardMoney = 850,
                xpReward = 25,
                killsEarned = 1,
                requiredKeyword = "floor",
                effectType = "attraction",
                requiredLevel = 3,
                taskType = "freeplay",
                collectibleReward = "Contractor's License"
            )

            lowerName.contains("letter") && userLevel >= 3 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "They're doing parody's of Ghostface on Tik Tok. We gotta shut that shit down.",
                isCompleted = true,
                rewardMoney = 710,
                xpReward = 55,
                killsEarned = 2,
                requiredKeyword = "letter",
                effectType = "attraction",
                requiredLevel = 3,
                taskType = "freeplay",
                collectibleReward = "Ghostface Signature"
            )

            // Victor Crowley (Swamp)
            lowerName.contains("harris") && userLevel >= 4 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You just wasted $2,999. I can't believe you'd pay money for her.",
                isCompleted = true,
                rewardMoney = 1,
                xpReward = 5,
                killsEarned = 0,
                requiredKeyword = "harris",
                effectType = "attraction",
                requiredLevel = 4,
                taskType = "freeplay",
                collectibleReward = "Roseanne DVD - Season 5"
            )

            lowerName.contains("beavers") && userLevel >= 4 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "Did you just buy porn? Weirdo.",
                isCompleted = true,
                rewardMoney = 1445,
                xpReward = 35,
                killsEarned = 1,
                requiredKeyword = "beavers",
                effectType = "attraction",
                requiredLevel = 4,
                taskType = "freeplay",
                collectibleReward = "Bootleg DVD"
            )

            lowerName.contains("crab") && userLevel >= 4 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "Crab Killer? I won't even ask...",
                isCompleted = true,
                rewardMoney = 1100,
                xpReward = 70,
                killsEarned = 3,
                requiredKeyword = "crab",
                effectType = "attraction",
                requiredLevel = 4,
                taskType = "freeplay",
                collectibleReward = "Crab Shell"
            )

            // Jigsaw (Warehouse)
            lowerName.contains("syringes") && userLevel >= 5 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You filled the needle pit!",
                isCompleted = true,
                rewardMoney = 1750,
                xpReward = 40,
                killsEarned = 1,
                requiredKeyword = "syringes",
                effectType = "attraction",
                requiredLevel = 5,
                taskType = "freeplay",
                collectibleReward = "Bloody Needle"
            )

            lowerName.contains("grinder") && userLevel >= 5 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You started the meat grinder.",
                isCompleted = true,
                rewardMoney = 2150,
                xpReward = 80,
                killsEarned = 4,
                requiredKeyword = "grinder",
                effectType = "attraction",
                requiredLevel = 5,
                taskType = "freeplay",
                collectibleReward = "Rusty Gear"
            )

            lowerName.contains("animal") && userLevel >= 5 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "This was his favorite toy. He'll never play with it again.",
                isCompleted = true,
                rewardMoney = 2230,
                xpReward = 85,
                killsEarned = 4,
                requiredKeyword = "animal",
                effectType = "attraction",
                requiredLevel = 5,
                taskType = "freeplay",
                collectibleReward = "Worn Plushie"
            )

            // Candyman (Hive)
            lowerName.contains("mural") && userLevel >= 6 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You restored the mural!",
                isCompleted = true,
                rewardMoney = 1750,
                xpReward = 40,
                killsEarned = 1,
                requiredKeyword = "mural",
                effectType = "attraction",
                requiredLevel = 6,
                taskType = "freeplay",
                collectibleReward = "Paintbrush Set"
            )

            lowerName.contains("bees") && userLevel >= 6 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "He can't see without his glasses!",
                isCompleted = true,
                rewardMoney = 2150,
                xpReward = 80,
                killsEarned = 4,
                requiredKeyword = "bees",
                effectType = "attraction",
                requiredLevel = 6,
                taskType = "freeplay",
                collectibleReward = "Thomas J's Glasses"
            )

            lowerName.contains("hook") && userLevel >= 6 -> UserTask(
                userId = userId,
                slasherId = slasherId,
                taskMessage = "You polished Candyman's hook!",
                isCompleted = true,
                rewardMoney = 700,
                xpReward = 85,
                killsEarned = 4,
                requiredKeyword = "hook",
                effectType = "attraction",
                requiredLevel = 6,
                taskType = "freeplay",
                collectibleReward = "Bee shaped Soap"
            )

            else -> null
        }
    }
}