// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.SelectInventoryItemDialogFragment
import com.kcrumptonslashertycoon.database.RoomEffect
import com.kcrumptonslashertycoon.database.UserInventoryItem
import com.kcrumptonslashertycoon.database.UserTask
import com.kcrumptonslashertycoon.FreeplayManager
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import com.kcrumptonslashertycoon.models.StoreViewModel
import com.kcrumptonslashertycoon.models.StoreViewModelFactory
import com.kcrumptonslashertycoon.XpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Victor Crowley's stuff
// All the fragments for the Slashers are exactly the same. Copy/Pasted after I made the first one.
class VictorSwampFragment : Fragment() {

    private lateinit var visitorTextView: TextView
    private lateinit var killsTextView: TextView
    private lateinit var textMoneyDisplay: TextView

    companion object {
    }

    private lateinit var storeViewModel: StoreViewModel
    private lateinit var slasherViewModel: SlasherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_victor_swamp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addFireflies(view)

        // Same as Jason, "Honey Island Swamp" has an animated glow behind it
        val swampTitleShadow = view.findViewById<TextView>(R.id.swampTitleShadow)

        val glowAnimator = ValueAnimator.ofFloat(8f, 20f).apply {
            duration = 900
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE

            addUpdateListener {
                val radius = it.animatedValue as Float
                swampTitleShadow.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                swampTitleShadow.setShadowLayer(radius, 0f, 0f, Color.parseColor("#FFFF33"))
                swampTitleShadow.invalidate()
            }
        }

        glowAnimator.start()


        visitorTextView = view.findViewById(R.id.visitorTextView)
        killsTextView = view.findViewById(R.id.killsTextView)
        textMoneyDisplay = view.findViewById(R.id.textMoneyDisplay)

        val bannerTextView = view.findViewById<TextView>(R.id.taskBannerTextView)
        val useItemButton = view.findViewById<Button>(R.id.useItemButton)

        view.findViewById<Button>(R.id.buttonStore).setOnClickListener {
            val action = VictorSwampFragmentDirections
                .actionVictorSwampFragmentToStoreFragment("Swamp")
            findNavController().navigate(action)
        }

        slasherViewModel = ViewModelProvider(
            requireActivity(),
            SlasherViewModelFactory(requireActivity().application as Application)
        )[SlasherViewModel::class.java]

        slasherViewModel.logAllSlashers()

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            Toast.makeText(requireContext(), "User not signed in!", Toast.LENGTH_SHORT).show()
            return
        }

        var currentTask: UserTask? = null


        lifecycleScope.launch {
            slasherViewModel.getUserInfo(userId).collect { userInfo ->
                userInfo?.let {
                    textMoneyDisplay.text = "$${userInfo.money}"
                    val userLevel = it.level


                    storeViewModel = ViewModelProvider(
                        this@VictorSwampFragment,
                        StoreViewModelFactory(requireContext(), "Swamp", userLevel)
                    )[StoreViewModel::class.java]


                    useItemButton.setOnClickListener {
                        lifecycleScope.launch {
                            val inventoryList =
                                storeViewModel.getInventoryForUser(userId).firstOrNull()
                                    ?: emptyList()

                            withContext(Dispatchers.Main) {
                                val dialog =
                                    SelectInventoryItemDialogFragment(inventoryList) { selectedItem ->
                                        val task = currentTask
                                        if (task != null) {
                                            handleItemUsed(selectedItem, task.taskMessage)
                                        } else {
                                            lifecycleScope.launch {
                                                handleFreePlayItemUse(selectedItem)
                                            }
                                        }
                                    }
                                dialog.show(parentFragmentManager, "SelectItemDialog")
                            }
                        }
                    }
                }
            }
        }


        lifecycleScope.launch {
            slasherViewModel.getActiveTask(userId, slasherId = 4).collect { task ->
                currentTask = task
                if (task != null) {
                    bannerTextView.text = task.taskMessage
                    bannerTextView.setBackgroundColor(requireContext().getColor(R.color.task_active_victor))
                } else {
                    bannerTextView.text = "Try something else or go back and wait for more tasks."
                    bannerTextView.setBackgroundColor(requireContext().getColor(R.color.task_inactive_victor))
                }
            }
        }

        val roomName = "Swamp"

        lifecycleScope.launchWhenStarted {
            slasherViewModel.slashers.collect { slashers ->
                val victor = slashers.find { it.name == "Victor Crowley" }
                if (victor != null) {
                    visitorTextView.text = "Visitor Count: ${victor.visitorCount}"
                    killsTextView.text = "Kills Today: ${victor.killsToday}"

                    val prevCount = slasherViewModel.getLastKnownVisitorCount(roomName)
                    val gained = victor.visitorCount - prevCount

                    if (gained > 0) {
                        slasherViewModel.updateLastKnownVisitorCount(roomName, victor.visitorCount)

                        val user = slasherViewModel.userInfo.value
                        if (user != null) {
                            val updatedUser = user.copy(money = user.money + (gained * 2))
                            slasherViewModel.updateUserInfo(updatedUser)

                            // Had to look this up - creating floating popups for visitors
                            val floatingText = TextView(requireContext()).apply {
                                text = "+${gained}"
                                textSize = 52f
                                setTextColor(Color.parseColor("#C22E2E"))
                                // Had to play around to get it to show up where I wanted
                                setPadding(455, 105, 64, 32) // left, top, bottom, right
                                alpha = 0.9f
                                layoutParams = FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.WRAP_CONTENT,
                                    FrameLayout.LayoutParams.WRAP_CONTENT
                                )
                                typeface = ResourcesCompat.getFont(requireContext(), R.font.slash_font)
                                setShadowLayer(3f, 2f, 2f, Color.parseColor("#5A0000"))
                            }

                            // Add to the root FrameLayout
                            val rootFrameLayout =
                                visitorTextView.rootView.findViewById<FrameLayout>(android.R.id.content)
                                    ?: (visitorTextView.parent as? ViewGroup)?.rootView as? FrameLayout
                                    ?: visitorTextView.parent as ViewGroup
                            rootFrameLayout.addView(floatingText)

                            // Position the floating TextView above visitorTextView
                            floatingText.post {
                                val location = IntArray(2)
                                visitorTextView.getLocationOnScreen(location)
                                val parentLocation = IntArray(2)
                                rootFrameLayout.getLocationOnScreen(parentLocation)

                                // Calculate relative position
                                floatingText.translationX =
                                    location[0].toFloat() - parentLocation[0].toFloat()
                                floatingText.translationY =
                                    location[1].toFloat() - parentLocation[1].toFloat() - visitorTextView.height - 24f
                            }

                            // Animate the floating TextView
                            floatingText.animate()
                                .translationYBy(-70f) // Move up
                                .alpha(0f) // Fade out
                                .setDuration(5000)
                                .withEndAction {
                                    rootFrameLayout.removeView(floatingText)
                                }
                                .start()
                        }
                    }
                }
            }
        }
    }

    // What happens when you use an item to complete a task
    private fun handleItemUsed(item: UserInventoryItem, taskMessage: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        lifecycleScope.launch {
            val task = slasherViewModel.getActiveTask(userId, slasherId = 4).firstOrNull()

            if (task != null && !task.isCompleted) {
                val keyword = task.requiredKeyword.lowercase()
                val itemName = item.itemName.lowercase()

                if (keyword.isBlank() || itemName.contains(keyword)) {
                    // Different effects for the tasks in each room
                    val boostAmount = when (task.effectType) {
                        "scare" -> 3
                        "attraction" -> 5
                        "trap" -> 0
                        else -> 1
                    }

                    val roomEffect = RoomEffect(
                        room = "Swamp",
                        description = "Used ${item.itemName} (task)",
                        effectType = task.effectType,
                        visitorBoost = boostAmount
                    )
                    slasherViewModel.logRoomEffect(roomEffect)

                    // Calculate totals from whatever effect is on the task
                    val allEffects =
                        slasherViewModel.getRoomEffects("Swamp").firstOrNull() ?: listOf()
                    val taskStartTime = task.createdAt
                    val relevantEffects = allEffects.filter { it.timestamp >= taskStartTime }

                    var attractionBoost = 0
                    var scareBoost = 0
                    var trapActive = false

                    relevantEffects.forEach { effect ->
                        when (effect.effectType) {
                            "attraction" -> attractionBoost += effect.visitorBoost
                            "scare" -> scareBoost += effect.visitorBoost
                            "trap" -> trapActive = true
                        }
                    }

                    val totalBoost = attractionBoost + scareBoost
                    val killBoost = scareBoost / 2

                    // Smaller rewards if there's a trap :)
                    val rewardMoney = if (trapActive) task.rewardMoney / 2 else task.rewardMoney
                    val xpGained = if (trapActive) task.xpReward / 2 else task.xpReward
                    val killsFromTask = if (trapActive) task.killsEarned / 2 else task.killsEarned

                    val totalKills = killsFromTask + killBoost

                    // Mark the task complete
                    val completedTask = task.copy(isCompleted = true)
                    slasherViewModel.saveUserTask(completedTask)


                    // Update stuff for the user (XP, Level, Money)
                    val currentUser = slasherViewModel.getUserInfo(userId).firstOrNull()
                    if (currentUser != null) {
                        val xpGained = 130
                        val newXp = currentUser.xp + xpGained
                        val newLevel = XpUtils.calculateLevelFromXp(newXp)
                        val updatedUser = currentUser.copy(
                            money = currentUser.money + rewardMoney,
                            xp = newXp,
                            level = newLevel
                        )
                        slasherViewModel.updateUserInfo(updatedUser)
                        slasherViewModel.unlockEligibleSlashers(newLevel)

                        // Same thing here - floating popup for reward money. I had too many toasts and snackbars, it looked crazy.
                        withContext(Dispatchers.Main) {
                            val floatingText = TextView(requireContext()).apply {
                                text = "+$${rewardMoney}"
                                textSize = 52f
                                setTextColor(Color.parseColor("#C22E2E"))
                                setBackgroundResource(0) // Clear theme background
                                // Same - had to play around to get it where I needed it
                                setPadding(40, 1790, 190, 8) // Left, Top, Bottom, Right
                                alpha = 0.9f
                                layoutParams = FrameLayout.LayoutParams(
                                    FrameLayout.LayoutParams.WRAP_CONTENT,
                                    FrameLayout.LayoutParams.WRAP_CONTENT
                                )
                                typeface = ResourcesCompat.getFont(requireContext(), R.font.slash_font)
                                setShadowLayer(3f, 2f, 2f, Color.parseColor("#5A0000"))
                            }

                            // Add to the root FrameLayout
                            val rootFrameLayout = textMoneyDisplay.rootView.findViewById<FrameLayout>(android.R.id.content)
                                ?: (textMoneyDisplay.parent as? ViewGroup)?.rootView as? FrameLayout
                                ?: textMoneyDisplay.parent as ViewGroup
                            rootFrameLayout.addView(floatingText)

                            // Position the floating TextView above textMoneyDisplay
                            floatingText.post {
                                val location = IntArray(2)
                                textMoneyDisplay.getLocationOnScreen(location)
                                val parentLocation = IntArray(2)
                                rootFrameLayout.getLocationOnScreen(parentLocation)

                                // Calculate relative position
                                floatingText.translationX = location[0].toFloat() - parentLocation[0].toFloat()
                                floatingText.translationY = location[1].toFloat() - parentLocation[1].toFloat() - textMoneyDisplay.height - 24f
                            }

                            // Animate the floating TextView
                            floatingText.animate()
                                .translationYBy(-70f) // Move up
                                .alpha(0f) // Fade out
                                .setDuration(5000) // 5 seconds for readability
                                .withEndAction {
                                    rootFrameLayout.removeView(floatingText) // Remove after animation
                                }
                                .start()
                        }
                    }


                    // Get total kills - from the task and any boosters and remove item from user's inventory
                    slasherViewModel.addKillsToSlasher(slasherId = 4, killsToAdd = totalKills)
                    storeViewModel.removeItemFromInventory(item)


                    // Visitor and Kill counts applied to the slasher
                    val slasher =
                        slasherViewModel.slashers.value.find { it.locationType == "Swamp" }
                    if (slasher != null) {
                        val updatedSlasher = slasher.copy(
                            visitorCount = slasher.visitorCount + totalBoost,
                            killsToday = slasher.killsToday + totalKills
                        )
                        slasherViewModel.updateSlasher(updatedSlasher)
                    } else {
                        Log.e("VictorDebug", "Couldn't find Victor in slasher list by location.")
                    }

                } else {
                    withContext(Dispatchers.Main) {
                        view?.let { rootView ->
                            val snackbar = Snackbar.make(
                                rootView,
                                "That item won't help Victor with this task.",
                                Snackbar.LENGTH_SHORT
                            )
                            val snackbarView = snackbar.view
                            val textView =
                                snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                            textView.textSize = 24f
                            snackbar.show()
                        }
                        return@withContext
                    }
                }
            }
        }
    }

    private suspend fun handleFreePlayItemUse(item: UserInventoryItem) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // Get user and level
        val user = slasherViewModel.getUserInfo(userId).firstOrNull()
        val userLevel = user?.level ?: 1

        // Get the slasher for this room
        val slasher =
            slasherViewModel.slashers.value.find { it.locationType == "Swamp" } ?: return

        // Generate task
        val task = FreeplayManager.getTaskForItem(item.itemName, userId, slasherId = 4, userLevel)

        if (task == null) {
            view?.let { rootView ->
                val snackbar = Snackbar.make(
                    rootView,
                    "Victor yawns. That didn't help.",
                    Snackbar.LENGTH_SHORT
                )
                val snackbarView = snackbar.view
                val textView =
                    snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                textView.textSize = 24f
                snackbar.show()
            }
            return
        }

        // Insert the task
        slasherViewModel.insertOrUpdateUserTask(task)

        // Log room effect
        val effect = RoomEffect(
            room = "Swamp",
            description = task.taskMessage,
            effectType = task.effectType,
            visitorBoost = task.killsEarned * 2
        )
        slasherViewModel.logRoomEffect(effect)

        // Update slasher stats
        val effects = slasherViewModel.getRoomEffects("Swamp").firstOrNull() ?: listOf()
        val visitorBoost = effects.sumOf { it.visitorBoost }

        val updatedSlasher = slasher.copy(
            visitorCount = slasher.visitorCount + visitorBoost,
            killsToday = slasher.killsToday + (visitorBoost / 2)
        )
        slasherViewModel.updateSlasher(updatedSlasher)

        // Update user stats and rewards
        user?.let {
            val newXp = it.xp + task.xpReward
            val newLevel = XpUtils.calculateLevelFromXp(newXp)
            val updatedUser = it.copy(xp = newXp, level = newLevel)
            slasherViewModel.updateUserInfo(updatedUser)
            slasherViewModel.unlockEligibleSlashers(newLevel)

            task.collectibleReward?.let { name ->
                slasherViewModel.addCollectible(name)
                Toast.makeText(
                    requireContext(),
                    "You found a collectible: $name!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            slasherViewModel.addMoney(task.rewardMoney)
        }

        // Remove item
        storeViewModel.removeItemFromInventory(item)
    }

    // Had to look this up too - I've got animated "sparkles" in the swamp to look like Fireflies. Didn't turn out as good as I wanted, still super neat though!
    private fun addFireflies(view: View) {
        val sparkleContainer = view.findViewById<FrameLayout>(R.id.fireflyContainer)
        val sparkleCount = (80..115).random()


        sparkleContainer.post {
            val containerHeight = sparkleContainer.height
            val containerWidth = sparkleContainer.width

            repeat(sparkleCount) {
                val size = (25..50).random()
                val sparkle = ImageView(requireContext()).apply {
                    setImageResource(R.drawable.sparkle)
                    layoutParams = FrameLayout.LayoutParams(size, size).apply {
                        topMargin = (0..containerHeight).random()
                        marginStart = (0..containerWidth).random()
                    }
                    alpha = 0f
                }

                sparkleContainer.addView(sparkle)

                // Fade and glow loop
                val fadeDuration = (1500..3000).random().toLong()
                val delay = (0..2000).random().toLong()

                sparkle.animate()
                    .alpha(1f)
                    .scaleX(1.3f)
                    .scaleY(1.3f)
                    .setDuration(fadeDuration)
                    .setStartDelay(delay)
                    .withEndAction {
                        sparkle.animate()
                            .alpha(0f)
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(fadeDuration)
                            .withEndAction {
                                sparkle.animate()
                                    .alpha(1f)
                                    .scaleX(1.3f)
                                    .scaleY(1.3f)
                                    .setDuration(fadeDuration)
                                    .setStartDelay((1000..3000).random().toLong())
                                    .start()
                            }
                            .start()
                    }
                    .start()

                // Movement animation
                val translationX = (10..100).random().toFloat()
                val translationY = (10..100).random().toFloat()
                val moveDuration = (4000..8000).random().toLong()

                val animator =
                    ObjectAnimator.ofFloat(sparkle, "translationX", 0f, translationX).apply {
                        this.duration = moveDuration
                        repeatMode = ValueAnimator.REVERSE
                        repeatCount = ValueAnimator.INFINITE
                    }

                val animatorY =
                    ObjectAnimator.ofFloat(sparkle, "translationY", 0f, translationY).apply {
                        this.duration = moveDuration
                        repeatMode = ValueAnimator.REVERSE
                        repeatCount = ValueAnimator.INFINITE
                    }

                animator.start()
                animatorY.start()
            }
        }
    }
}