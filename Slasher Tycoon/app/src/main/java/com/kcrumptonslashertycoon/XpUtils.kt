// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon

// Changed this a million times... still not super happy with it
object XpUtils {
    fun getXpForNextLevel(level: Int): Int {
        // XP needed for next level
        return (125 * level)
    }

    fun calculateLevelFromXp(xp: Int): Int {
        var level = 1
        var totalXp = 0

        while (xp >= totalXp + getXpForNextLevel(level)) {
            totalXp += getXpForNextLevel(level)
            level++
        }

        return level
    }

    fun getTotalXpToReachLevel(level: Int): Int {
        var total = 0
        for (i in 1 until level) {
            total += getXpForNextLevel(i)
        }
        return total
    }
}