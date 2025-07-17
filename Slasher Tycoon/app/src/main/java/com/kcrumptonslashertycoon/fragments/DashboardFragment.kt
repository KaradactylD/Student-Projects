// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.app.Application
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.AbsoluteSizeSpan
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.database.Slasher
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import com.kcrumptonslashertycoon.XpUtils
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private lateinit var viewModel: SlasherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        menu.findItem(R.id.action_instructions)?.icon?.setTint(Color.YELLOW)
        val toolbar = view?.findViewById<Toolbar>(R.id.dashboardToolbar)
        toolbar?.overflowIcon?.setTint(Color.YELLOW)


        // Setting up the font/text size for the menu
        val typeface = ResourcesCompat.getFont(requireContext(), R.font.slash_font)

        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            val spanString = SpannableString(item.title)

            spanString.setSpan(AbsoluteSizeSpan(24, true), 0, spanString.length, 0)

            typeface?.let {
                spanString.setSpan(CustomTypefaceSpan(it), 0, spanString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            item.title = spanString
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    class CustomTypefaceSpan(private val customTypeface: Typeface) : TypefaceSpan("") {
        override fun updateDrawState(textPaint: TextPaint) {
            apply(textPaint)
        }

        override fun updateMeasureState(textPaint: TextPaint) {
            apply(textPaint)
        }

        private fun apply(paint: Paint) {
            paint.typeface = customTypeface
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_view_profile -> {
                findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
                true
            }
            R.id.action_instructions -> {
                findNavController().navigate(R.id.instructionsFragment)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    // to refresh so you see new stuff
    private suspend fun refreshTasksAndUI(view: View, slasherList: List<Slasher>) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val activeTaskLocations = mutableListOf<String>()

        for (slasher in slasherList) {
            val slasherIntId = slasher.requiredLevel

            val task = viewModel.getActiveTask(userId, slasherIntId).firstOrNull()
            if (task != null && !task.isCompleted) {
                activeTaskLocations.add(slasher.locationType)
            } else {
                viewModel.loadOrInsertNextTask(userId, slasherIntId)
            }
        }


        updateSlasherButtons(view, slasherList, activeTaskLocations)
    }

    private fun updateSlasherButtons(
        view: View,
        slasherList: List<Slasher>,
        activeTaskLocations: List<String>
    ) {

        // I've got everything connected by the name of their rooms instead of their full names. Seemed easier that way...
        val buttons = mapOf(
            "Cabin" to R.id.buttonJason,
            "Boiler Room" to R.id.buttonFreddy,
            "Suburbs" to R.id.buttonScream,
            "Swamp" to R.id.buttonVictor,
            "Warehouse" to R.id.buttonJigsaw,
            "Hive" to R.id.buttonCandyman
        )

        // Exclamation mark shows when there's a task that needs to be done.
        val alertIcons = mapOf(
            "Cabin" to R.id.iconJasonAlert,
            "Boiler Room" to R.id.iconFreddyAlert,
            "Suburbs" to R.id.iconScreamAlert,
            "Swamp" to R.id.iconVictorAlert,
            "Warehouse" to R.id.iconJigsawAlert,
            "Hive" to R.id.iconCandymanAlert
        )

        slasherList.forEach { slasher ->
            val buttonId = buttons[slasher.locationType] ?: return@forEach
            val button = view.findViewById<ImageButton>(buttonId)

            // they're grayed out until you unlock 'em
            button.isEnabled = slasher.isUnlocked
            button.alpha = if (slasher.isUnlocked) 1.0f else 0.5f

            val iconId = alertIcons[slasher.locationType]
            val iconView = iconId?.let { view.findViewById<ImageView>(it) }
            val hasTask = activeTaskLocations.contains(slasher.locationType)
            iconView?.visibility = if (hasTask) View.VISIBLE else View.GONE

            if (slasher.isUnlocked) {
                val action = when (slasher.locationType) {
                    "Cabin" -> R.id.action_dashboardFragment_to_jasonCabinFragment
                    "Boiler Room" -> R.id.action_dashboardFragment_to_freddyBoilerFragment
                    "Suburbs" -> R.id.action_dashboardFragment_to_ghostfaceSuburbFragment
                    "Swamp" -> R.id.action_dashboardFragment_to_victorSwampFragment
                    "Warehouse" -> R.id.action_dashboardFragment_to_jigsawWarehouseFragment
                    "Hive" -> R.id.action_dashboardFragment_to_candymanHiveFragment
                    else -> null
                }

                button.setOnClickListener {
                    if (slasher.locationType == "Boiler Room") {
                        findNavController().navigate(R.id.action_dashboardFragment_to_freddyBoilerFragment)
                    } else {
                        action?.let { findNavController().navigate(it) }
                    }
                }
            } else {
                button.setOnClickListener {
                    Toast.makeText(
                        requireContext(),
                        "${slasher.name} unlocks at level ${slasher.requiredLevel}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.dashboardToolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)

        setHasOptionsMenu(true)


        for (i in 0 until toolbar.childCount) {
            val view = toolbar.getChildAt(i)
            if (view is TextView && view.text == toolbar.title) {
                view.textSize = 45f
                view.setTextColor(Color.WHITE)
                view.typeface = ResourcesCompat.getFont(requireContext(), R.font.slash_font)
                break
            }
        }

        viewModel = ViewModelProvider(
            this,
            SlasherViewModelFactory(requireActivity().application as Application)
        )[SlasherViewModel::class.java]

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        var initialized = false

        // Making sure the slashers are loaded with all their info
        lifecycleScope.launchWhenStarted {
            viewModel.userInfo.collect { user ->
                if (user != null) {
                    viewModel.slashers.collect { slasherList ->
                        if (!initialized) {
                            initialized = true
                            if (slasherList.isEmpty()) {
                                viewModel.insertOrUpdateSlasherIfNeeded(
                                    Slasher(
                                        name = "Freddy Krueger",
                                        locationType = "Boiler Room",
                                        weapon = "Glove Claws",
                                        isUnlocked = true,
                                        kills = 0,
                                        requiredLevel = 1
                                    )
                                )

                                viewModel.insertOrUpdateSlasherIfNeeded(
                                    Slasher(
                                        name = "Jason Voorhees",
                                        locationType = "Cabin",
                                        weapon = "Machete",
                                        isUnlocked = false,
                                        kills = 0,
                                        requiredLevel = 2
                                    )
                                )

                                viewModel.insertOrUpdateSlasherIfNeeded(
                                    Slasher(
                                        name = "Ghostface",
                                        locationType = "Suburbs",
                                        weapon = "Hunting Knife",
                                        isUnlocked = false,
                                        kills = 0,
                                        requiredLevel = 3
                                    )
                                )

                                viewModel.insertOrUpdateSlasherIfNeeded(
                                    Slasher(
                                        name = "Victor Crowley",
                                        locationType = "Swamp",
                                        weapon = "Hatchet",
                                        isUnlocked = false,
                                        kills = 0,
                                        requiredLevel = 4
                                    )
                                )

                                viewModel.insertOrUpdateSlasherIfNeeded(
                                    Slasher(
                                        name = "Jigsaw",
                                        locationType = "Warehouse",
                                        weapon = "Traps",
                                        isUnlocked = false,
                                        kills = 0,
                                        requiredLevel = 5
                                    )
                                )

                                viewModel.insertOrUpdateSlasherIfNeeded(
                                    Slasher(
                                        name = "Candyman",
                                        locationType = "Hive",
                                        weapon = "Hook",
                                        isUnlocked = false,
                                        kills = 0,
                                        requiredLevel = 6
                                    )
                                )

                            }
                            // Always refresh task list so you see the updates
                            refreshTasksAndUI(view, slasherList)
                        }


                        val activeTaskLocations = mutableListOf<String>()
                        if (userId != null) {

                            val candymanTask = viewModel.getActiveTask(userId, 6).firstOrNull()
                            if (candymanTask != null && !candymanTask.isCompleted) {
                                activeTaskLocations.add("Hive")
                            } else {
                                viewModel.loadOrInsertNextTask(userId, 6)
                                val newTask = viewModel.getActiveTask(userId, 6).firstOrNull()
                                if (newTask != null && !newTask.isCompleted) {
                                    activeTaskLocations.add("Hive")
                                }
                            }

                            val jigsawTask = viewModel.getActiveTask(userId, 5).firstOrNull()
                            if (jigsawTask != null && !jigsawTask.isCompleted) {
                                activeTaskLocations.add("Warehouse")
                            } else {
                                viewModel.loadOrInsertNextTask(userId, 5)
                                val newTask = viewModel.getActiveTask(userId, 5).firstOrNull()
                                if (newTask != null && !newTask.isCompleted) {
                                    activeTaskLocations.add("Warehouse")
                                }
                            }

                            val victorTask = viewModel.getActiveTask(userId, 4).firstOrNull()
                            if (victorTask != null && !victorTask.isCompleted) {
                                activeTaskLocations.add("Swamp")
                            } else {
                                viewModel.loadOrInsertNextTask(userId, 4)
                                val newTask = viewModel.getActiveTask(userId, 4).firstOrNull()
                                if (newTask != null && !newTask.isCompleted) {
                                    activeTaskLocations.add("Swamp")
                                }
                            }

                            val ghostfaceTask = viewModel.getActiveTask(userId, 3).firstOrNull()
                            if (ghostfaceTask != null && !ghostfaceTask.isCompleted) {
                                activeTaskLocations.add("Suburbs")
                            } else {
                                viewModel.loadOrInsertNextTask(userId, 3)
                                val newTask = viewModel.getActiveTask(userId, 3).firstOrNull()
                                if (newTask != null && !newTask.isCompleted) {
                                    activeTaskLocations.add("Suburbs")
                                }
                            }

                            val jasonTask = viewModel.getActiveTask(userId, 2).firstOrNull()
                            if (jasonTask != null && !jasonTask.isCompleted) {
                                activeTaskLocations.add("Cabin")
                            } else {
                                viewModel.loadOrInsertNextTask(userId, 2)
                                val newTask = viewModel.getActiveTask(userId, 2).firstOrNull()
                                if (newTask != null && !newTask.isCompleted) {
                                    activeTaskLocations.add("Cabin")
                                }
                            }

                            val freddyTask = viewModel.getActiveTask(userId, 1).firstOrNull()
                            if (freddyTask != null && !freddyTask.isCompleted) {
                                activeTaskLocations.add("Boiler Room")
                            } else {
                                viewModel.loadOrInsertNextTask(userId, 1)
                                val newTask = viewModel.getActiveTask(userId, 1).firstOrNull()
                                if (newTask != null && !newTask.isCompleted) {
                                    activeTaskLocations.add("Boiler Room")
                                }
                            }


                        }

                        updateSlasherButtons(view, slasherList, activeTaskLocations)
                    }
                }
            }
        }

        view.findViewById<ImageButton>(R.id.buttonJason)?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_jasonCabinFragment)
        }

        view.findViewById<ImageButton>(R.id.buttonFreddy)?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_freddyBoilerFragment)
        }

        view.findViewById<ImageButton>(R.id.buttonScream)?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_ghostfaceSuburbFragment)
        }

        view.findViewById<ImageButton>(R.id.buttonVictor)?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_victorSwampFragment)
        }

        view.findViewById<ImageButton>(R.id.buttonJigsaw)?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_jigsawWarehouseFragment)
        }

        view.findViewById<ImageButton>(R.id.buttonCandyman)?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_candymanHiveFragment)
        }

        view.findViewById<Button>(R.id.buttonNeedCash).setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToExtraCashFragment())
        }

        // Getting user's level and progress
        val xpTextView = view.findViewById<TextView>(R.id.xpTextView)
        val xpProgressBar = view.findViewById<ProgressBar>(R.id.xpProgressBar)
        val moneyTextView = view.findViewById<TextView>(R.id.textMoneyDisplay)

        userId?.let {
            lifecycleScope.launch {
                viewModel.getUserInfo(it).collect { user ->
                    if (user != null) {
                        val totalXp = user.xp
                        val level = user.level
                        val xpAtCurrentLevelStart = XpUtils.getTotalXpToReachLevel(level)
                        val xpForNextLevel = XpUtils.getXpForNextLevel(level)
                        val xpIntoCurrentLevel = totalXp - xpAtCurrentLevelStart

                        xpTextView.text = "XP: $xpIntoCurrentLevel / $xpForNextLevel (Level $level)"
                        xpProgressBar.max = xpForNextLevel
                        xpProgressBar.progress = xpIntoCurrentLevel
                        moneyTextView.text = "💰 $${user.money}"
                    }
                }
            }
        }
    }
}
