// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon

import android.util.Log
import com.kcrumptonslashertycoon.database.ChiliIngredient
import com.kcrumptonslashertycoon.database.Judge

data class ChiliResult(
    val feedback: String,
    val totalScore: Int,
    val rewardMoney: Int
)

object ChiliCookoffManager {
    fun scoreChili(
        selectedIngredients: List<ChiliIngredient>,
        judges: List<Judge>
    ): ChiliResult {
        // Score depends on the judge - each has different amounts they give, and they're used at random
        val totalFlavor = selectedIngredients.sumOf { it.flavorPoints }
        val totalScare = selectedIngredients.sumOf { it.scarePoints }

        var totalScore = 0
        val feedbackBuilder = StringBuilder()

        judges.forEach { judge ->
            val judgeLoves = judge.loves.map { it.lowercase() }
            val judgeHates = judge.hates.map { it.lowercase() }

            val lovesUsed = selectedIngredients.filter { it.name.lowercase() in judgeLoves }
            val hatesUsed = selectedIngredients.filter { it.name.lowercase() in judgeHates }

            var judgeScore = 0
            feedbackBuilder.append("${judge.name} says:\n")

            // Shows what each judge liked or didn't like - couldn't find any horror-y emojis that looked good, but it needed something.
            if (lovesUsed.isNotEmpty()) {
                feedbackBuilder.append("😍 Loved the ${lovesUsed.joinToString { it.name }}!\n")
                judgeScore += lovesUsed.size * 2
            }

            if (hatesUsed.isNotEmpty()) {
                feedbackBuilder.append("🤢 Hated the ${hatesUsed.joinToString { it.name }}!\n")
                judgeScore -= hatesUsed.size * 2
            }

            if (lovesUsed.isEmpty() && hatesUsed.isEmpty()) {
                feedbackBuilder.append("😐 It was... okay, I guess.\n")
                judgeScore += 1
            }

            totalScore += judgeScore
            feedbackBuilder.append("\n")
            Log.d("CookoffDebug", "Feedback text: >>>${feedbackBuilder}<<<")
        }

        // Bonus points for total flavor/scare
        totalScore += totalFlavor / 2
        totalScore += totalScare / 2

        // Get reward (anywhere from $10 - $100)
        val rewardMoney = totalScore.coerceIn(10, 100)

        return ChiliResult(
            feedback = feedbackBuilder.toString().trim(),
            totalScore = totalScore,
            rewardMoney = rewardMoney
        )
    }
}