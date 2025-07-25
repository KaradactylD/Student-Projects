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
                    taskMessage = "Tina's room could use a makeover...",
                    rewardMoney = 250,
                    killsEarned = 2,
                    xpReward = 35,
                    requiredKeyword = "paint",
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
                    taskMessage = "NEVER wake a sleepwalker!",
                    rewardMoney = 850,
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
                    taskMessage = "Rod’s been having nightmares too.",
                    rewardMoney = 730,
                    killsEarned = 6,
                    xpReward = 60,
                    requiredKeyword = "dirty",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 1,
                    taskMessage = "Spruce up the TV room.",
                    rewardMoney = 855,
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
                    rewardMoney = 510,
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
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "Welcome to Camp Crystal Lake... kind of...",
                    rewardMoney = 930,
                    killsEarned = 4,
                    xpReward = 100,
                    requiredKeyword = "vr",
                    effectType = "attraction",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "She wants space. Give it to her.",
                    rewardMoney = 950,
                    killsEarned = 4,
                    xpReward = 100,
                    requiredKeyword = "airlock",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 2,
                    taskMessage = "\"Hey, I just met you... and this is crazy...\"",
                    rewardMoney = 1150,
                    killsEarned = 4,
                    xpReward = 100,
                    requiredKeyword = "nitrogen",
                    effectType = "scare",
                    requiredLevel = 7
                ),

            )

            3 -> listOf( // Ghostface
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "Extra butter, extra blood!",
                    rewardMoney = 150,
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
                    rewardMoney = 350,
                    killsEarned = 1,
                    xpReward = 35,
                    requiredKeyword = "rule",
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
                    taskMessage = "Don't believe everything you hear.",
                    rewardMoney = 750,
                    killsEarned = 1,
                    xpReward = 75,
                    requiredKeyword = "voice",
                    effectType = "scare",
                    requiredLevel = 3
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "Gale caught you on camera.",
                    rewardMoney = 820,
                    killsEarned = 1,
                    xpReward = 75,
                    requiredKeyword = "erase",
                    effectType = "scare",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "Owww!",
                    rewardMoney = 740,
                    killsEarned = 1,
                    xpReward = 75,
                    requiredKeyword = "cordless",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 3,
                    taskMessage = "She thought those bangs were OK?",
                    rewardMoney = 630,
                    killsEarned = 1,
                    xpReward = 75,
                    requiredKeyword = "kitchen",
                    effectType = "attraction",
                    requiredLevel = 6
                ),

            )

            4 -> listOf( // Victor Crowley
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Victor's hatchet is getting dull",
                    rewardMoney = 155,
                    killsEarned = 1,
                    xpReward = 35,
                    requiredKeyword = "sharpener",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Ben and Marybeth have an idea...",
                    rewardMoney = 300,
                    killsEarned = 2,
                    xpReward = 55,
                    requiredKeyword = "gas",
                    effectType = "scare",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Victor doesn't collect beads... he collects bodies.",
                    rewardMoney = 420,
                    killsEarned = 12,
                    xpReward = 75,
                    requiredKeyword = "beads",
                    effectType = "attraction",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Boat Tours are illegal in this part of the swamp...",
                    rewardMoney = 1030,
                    killsEarned = 15,
                    xpReward = 95,
                    requiredKeyword = "tour",
                    effectType = "attraction",
                    requiredLevel = 4
                ),
                UserTask(
                    userId = userId,
                    slasherId = 4,
                    taskMessage = "Victor hates liars.",
                    rewardMoney = 900,
                    killsEarned = 8,
                    xpReward = 105,
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
                    rewardMoney = 280,
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
                    rewardMoney = 240,
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
                    rewardMoney = 400,
                    killsEarned = 0,
                    xpReward = 75,
                    requiredKeyword = "penlight",
                    effectType = "scare",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "X Marks the spot",
                    rewardMoney = 550,
                    killsEarned = 1,
                    xpReward = 85,
                    requiredKeyword = "blacklight",
                    effectType = "attraction",
                    requiredLevel = 5
                ),
                UserTask(
                    userId = userId,
                    slasherId = 5,
                    taskMessage = "Live or Die, Dr. Gordon. Make your choice.",
                    rewardMoney = 1600,
                    killsEarned = 8,
                    xpReward = 115,
                    requiredKeyword = "saw",
                    effectType = "scare",
                    requiredLevel = 5
                )
            )
            6 -> listOf( // Candyman
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "If you say it 5 times, that's when he comes.",
                    rewardMoney = 810,
                    killsEarned = 8,
                    xpReward = 75,
                    requiredKeyword = "mirror",
                    effectType = "scare",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "He led the boy away so easily.",
                    rewardMoney = 700,
                    killsEarned = 6,
                    xpReward = 55,
                    requiredKeyword = "candy",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "The walls pulsed. The hive had woken. Cabrini-Green would bleed.",
                    rewardMoney = 910,
                    killsEarned = 9,
                    xpReward = 75,
                    requiredKeyword = "swarm",
                    effectType = "scare",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "She mocked his story. He painted hers... in blood.",
                    rewardMoney = 780,
                    killsEarned = 7,
                    xpReward = 125,
                    requiredKeyword = "art",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
                UserTask(
                    userId = userId,
                    slasherId = 6,
                    taskMessage = "They found the child asleep in the ashes. The buzzing never stopped.",
                    rewardMoney = 1330,
                    killsEarned = 7,
                    xpReward = 105,
                    requiredKeyword = "ash",
                    effectType = "attraction",
                    requiredLevel = 6
                ),
            )
            7 -> listOf( // Carrie
                UserTask(
                    userId = userId,
                    slasherId = 7,
                    taskMessage = "Brush Carrie's hair.",
                    rewardMoney = 600,
                    killsEarned = 1,
                    xpReward = 35,
                    requiredKeyword = "brush",
                    effectType = "attraction",
                    requiredLevel = 7
                ),
                UserTask(
                    userId = userId,
                    slasherId = 7,
                    taskMessage = "Mama said it was a sin. She locked me in here to pray.",
                    rewardMoney = 820,
                    killsEarned = 4,
                    xpReward = 65,
                    requiredKeyword = "closet",
                    effectType = "scare",
                    requiredLevel = 7
                ),
                UserTask(
                    userId = userId,
                    slasherId = 7,
                    taskMessage = "\"They're all gonna laugh at you!\"",
                    rewardMoney = 880,
                    killsEarned = 0,
                    xpReward = 95,
                    requiredKeyword = "prom",
                    effectType = "attraction",
                    requiredLevel = 7
                ),
                UserTask(
                    userId = userId,
                    slasherId = 7,
                    taskMessage = "Chris and Billy have a surprise for me...",
                    rewardMoney = 1020,
                    killsEarned = 5,
                    xpReward = 115,
                    requiredKeyword = "bucket",
                    effectType = "scare",
                    requiredLevel = 7
                ),
                UserTask(
                    userId = userId,
                    slasherId = 7,
                    taskMessage = "Mama was right. They're all laughing at me...",
                    rewardMoney = 1370,
                    killsEarned = 15,
                    xpReward = 135,
                    requiredKeyword = "rampage",
                    effectType = "scare",
                    requiredLevel = 7
                ),
            )

            8 -> listOf( // Leprechaun
                UserTask(
                    userId = userId,
                    slasherId = 8,
                    taskMessage = "This old Lep, he played one...",
                    rewardMoney = 520,
                    killsEarned = 1,
                    xpReward = 75,
                    requiredKeyword = "pogo",
                    effectType = "scare",
                    requiredLevel = 8
                ),
                UserTask(
                    userId = userId,
                    slasherId = 8,
                    taskMessage = "Lep's tricycle wheels are getting squeaky.",
                    rewardMoney = 440,
                    killsEarned = 1,
                    xpReward = 95,
                    requiredKeyword = "WD40",
                    effectType = "attraction",
                    requiredLevel = 8
                ),
                UserTask(
                    userId = userId,
                    slasherId = 8,
                    taskMessage = "\"I'll bite it off and make boots out of it!\"",
                    rewardMoney = 630,
                    killsEarned = 3,
                    xpReward = 105,
                    requiredKeyword = "ear",
                    effectType = "scare",
                    requiredLevel = 8
                ),
                UserTask(
                    userId = userId,
                    slasherId = 8,
                    taskMessage = "He picked the wrong time to talk trash.",
                    rewardMoney = 650,
                    killsEarned = 5,
                    xpReward = 105,
                    requiredKeyword = "pick",
                    effectType = "scare",
                    requiredLevel = 8
                ),
                UserTask(
                    userId = userId,
                    slasherId = 8,
                    taskMessage = "\"Try as they will and try as they might...\"",
                    rewardMoney = 800,
                    killsEarned = 5,
                    xpReward = 115,
                    requiredKeyword = "gold",
                    effectType = "scare",
                    requiredLevel = 8
                ),
            )

            9 -> listOf( // Leslie Vernon
                UserTask(
                    userId = userId,
                    slasherId = 9,
                    taskMessage = "No one fears a slasher who's winded.",
                    rewardMoney = 530,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "treadmill",
                    effectType = "attraction",
                    requiredLevel = 9
                ),
                UserTask(
                    userId = userId,
                    slasherId = 9,
                    taskMessage = "Eliminate the exits.",
                    rewardMoney = 605,
                    killsEarned = 3,
                    xpReward = 175,
                    requiredKeyword = "hammer",
                    effectType = "scare",
                    requiredLevel = 9
                ),
                UserTask(
                    userId = userId,
                    slasherId = 9,
                    taskMessage = "Misdirection is key to building tension.",
                    rewardMoney = 700,
                    killsEarned = 3,
                    xpReward = 275,
                    requiredKeyword = "librarian",
                    effectType = "scare",
                    requiredLevel = 9
                ),
                UserTask(
                    userId = userId,
                    slasherId = 9,
                    taskMessage = "Heavy enough to feel real, useless when it matters.",
                    rewardMoney = 820,
                    killsEarned = 3,
                    xpReward = 175,
                    requiredKeyword = "fake",
                    effectType = "scare",
                    requiredLevel = 9
                ),
                UserTask(
                    userId = userId,
                    slasherId = 9,
                    taskMessage = "It's not clutter, it's strategy.",
                    rewardMoney = 875,
                    killsEarned = 3,
                    xpReward = 175,
                    requiredKeyword = "junk",
                    effectType = "attraction",
                    requiredLevel = 9
                ),
            )

            10 -> listOf( // Bughuul
                UserTask(
                    userId = userId,
                    slasherId = 10,
                    taskMessage = "Pool Party '66",
                    rewardMoney = 800,
                    killsEarned = 5,
                    xpReward = 275,
                    requiredKeyword = "chair",
                    effectType = "scare",
                    requiredLevel = 10
                ),
                UserTask(
                    userId = userId,
                    slasherId = 10,
                    taskMessage = "\"Destroying the films doesn't help.\"",
                    rewardMoney = 830,
                    killsEarned = 5,
                    xpReward = 185,
                    requiredKeyword = "film",
                    effectType = "attraction",
                    requiredLevel = 10
                ),
                UserTask(
                    userId = userId,
                    slasherId = 10,
                    taskMessage = "\"Don't let your children see it.\"",
                    rewardMoney = 1400,
                    killsEarned = 5,
                    xpReward = 225,
                    requiredKeyword = "slide",
                    effectType = "attraction",
                    requiredLevel = 10
                ),
                UserTask(
                    userId = userId,
                    slasherId = 10,
                    taskMessage = "Lawn Work '86",
                    rewardMoney = 1790,
                    killsEarned = 5,
                    xpReward = 325,
                    requiredKeyword = "lawnmower",
                    effectType = "scare",
                    requiredLevel = 10
                ),
                UserTask(
                    userId = userId,
                    slasherId = 10,
                    taskMessage = "House Painting '12",
                    rewardMoney = 1970,
                    killsEarned = 5,
                    xpReward = 365,
                    requiredKeyword = "axe",
                    effectType = "scare",
                    requiredLevel = 10
                ),
            )

            11 -> listOf( // The Collector
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "\"For the collection.\"",
                    rewardMoney = 550,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "trunk",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "Anything for love...",
                    rewardMoney = 750,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "lisa",
                    effectType = "attraction",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "Get the jewel. Pay the Sharks.",
                    rewardMoney = 800,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "safe",
                    effectType = "attraction",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "Screams echo through the vents.",
                    rewardMoney = 830,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "call",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "You need to leave!",
                    rewardMoney = 1050,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "window",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "You hear screams from the attic...",
                    rewardMoney = 1200,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "back",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "This is your only shot.",
                    rewardMoney = 1000,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "shotgun",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "It Worked! Paramedics arrive. You and Hannah are safe.",
                    rewardMoney = 1700,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "twist",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "The ambulance is ambushed. He's coming toward you.",
                    rewardMoney = 2070,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "fight",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "You're too weak. You can't fight him off.",
                    rewardMoney = 2100,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "game",
                    effectType = "scare",
                    requiredLevel = 11
                ),
                UserTask(
                    userId = userId,
                    slasherId = 11,
                    taskMessage = "YOU HAVE BEEN COLLECTED.",
                    rewardMoney = 2500,
                    killsEarned = 1,
                    xpReward = 175,
                    requiredKeyword = "worry",
                    effectType = "scare",
                    requiredLevel = 11
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
