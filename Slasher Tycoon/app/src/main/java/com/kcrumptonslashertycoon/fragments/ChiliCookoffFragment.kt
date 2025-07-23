// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.ChiliCookoffManager
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.adapters.IngredientAdapter
import com.kcrumptonslashertycoon.database.ChiliIngredient
import com.kcrumptonslashertycoon.database.Judge
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChiliCookoffFragment : Fragment() {

    private val selectedIngredients = mutableSetOf<ChiliIngredient>()
    private lateinit var judgeResultsTextView: TextView
    private lateinit var slasherViewModel: SlasherViewModel

    // Gross ingredients - flavor score, scare score, and whether it's meat or not
    // Ended up not using the meat part... I couldn't decide how I wanted to do it.
    private val availableIngredients = listOf(
        ChiliIngredient("Rotten Meat", 2, 5, true),
        ChiliIngredient("Ghost Pepper", 8, 2),
        ChiliIngredient("Chainsaw Oil", 1, 7),
        ChiliIngredient("Screaming Mushrooms", 4, 4),
        ChiliIngredient("Eyeballs", 5, 6, true),
        ChiliIngredient("Burnt Toenails", 1, 6, true),
        ChiliIngredient("Zombie Liver", 3, 7, true),
        ChiliIngredient("Blood Sausage", 7, 3),
        ChiliIngredient("Hellfire Beans", 9, 1),
        ChiliIngredient("Witch's Basil", 6, 4),
        ChiliIngredient("Pickled Ears", 4, 5, true),
        ChiliIngredient("Cursed Onions", 5, 5),
        ChiliIngredient("Bat Wings", 6, 6),
        ChiliIngredient("Slime Mold", 2, 8, true),
        ChiliIngredient("Haunted Jalapenos", 7, 2)
    )

    // The judges and what they like or hate... all Texas Chainsaw Massacre characters.
    private val allJudges = listOf(
        Judge("Leatherface", listOf("Eyeballs", "Chainsaw Oil"), listOf("Ghost Pepper"), 10, 8),
        Judge("Grandpa", listOf("Rotten Meat", "Blood Sausage"), listOf("Chainsaw Oil"), 8, 6),
        Judge("Drayton Sawyer (The Cook)", listOf("Cursed Onions", "Rotten Meat"), listOf("Witch's Basil", "Bat Wings"), 12, 5),
        Judge("Nubbins Sawyer (The Hitchhiker)", listOf("Zombie Liver", "Screaming Mushrooms"), listOf("Slime Mold"), 9, 9),
        Judge("Chop Top", listOf("Burnt Toenails", "Pickled Ears"), listOf("Ghost Pepper", "Hellfire Beans"), 7, 10),
        Judge("Vilmer Slaughter", listOf("Blood Sausage", "Haunted Jalapenos"), listOf("Eyeballs", "Cursed Onions"), 10, 12)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_chili_cookoff, container, false)

    @SuppressLint("SetTextI18n") // I don't know what this is... I clicked it to get rid of the yellow lines in my text and this showed up.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        judgeResultsTextView = view.findViewById(R.id.judgeResultsTextView)

        slasherViewModel = ViewModelProvider(
            requireActivity(),
            SlasherViewModelFactory(requireActivity().application as Application)
        )[SlasherViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.ingredientRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.isVerticalScrollBarEnabled = true

        lateinit var adapter: IngredientAdapter

        adapter = IngredientAdapter(availableIngredients, selectedIngredients) { ingredient ->
            val index = availableIngredients.indexOf(ingredient)

            if (selectedIngredients.contains(ingredient)) {
                selectedIngredients.remove(ingredient)
            } else if (selectedIngredients.size >= 5) {
                // Changing some of my stuff to Snackbar instead of toasts... I've got too much stuff going on at once, it's annoying.
                view?.let { rootView ->
                    val snackbar = Snackbar.make(rootView, "You can only use 5 ingredients!", Snackbar.LENGTH_SHORT)
                    val snackbarView = snackbar.view
                    val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                    textView.textSize = 24f
                    snackbar.show()
                }
                return@IngredientAdapter
            } else {
                selectedIngredients.add(ingredient)
            }

            adapter.notifyItemChanged(index)
        }

        recyclerView.adapter = adapter

        view.findViewById<Button>(R.id.submitChiliButton).setOnClickListener {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@setOnClickListener

            lifecycleScope.launch {
                val user = slasherViewModel.getUserInfo(userId).firstOrNull()
                val now = System.currentTimeMillis()
                val cooldownMillis = 5 * 60 * 1000 // Gotta wait 5 minutes before you can play again

                if (user != null && now - user.lastCookoffTime < cooldownMillis) {
                    val minutesLeft = ((cooldownMillis - (now - user.lastCookoffTime)) / 60000) + 1
                    withContext(Dispatchers.Main) {
                        view?.let { rootView ->
                            val snackbar = Snackbar.make(
                                rootView,
                                "You must wait $minutesLeft minute(s) before entering the Chili Cook-Off again.",
                                Snackbar.LENGTH_LONG
                            )
                            val snackbarView = snackbar.view
                            val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                            textView.textSize = 24f
                            snackbar.show()
                        }
                    }
                    return@launch
                }

                // Uses 3 judges from my judge list, picked at random each time.
                val selectedJudges = allJudges.shuffled().take(3)
                val result = ChiliCookoffManager.scoreChili(selectedIngredients.toList(), selectedJudges)

                // Results from the judges - random and means absolutely nothing
                judgeResultsTextView.text = (
                        result.feedback.trimIndent() +
                                "\n\nFinal Score: ${result.totalScore}\nReward: $${result.rewardMoney}"
                        )

                val updatedUser = user!!.copy(
                    money = user.money + result.rewardMoney,
                    lastCookoffTime = now
                )
                slasherViewModel.updateUserInfo(updatedUser)

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "You earned $${result.rewardMoney} from the Chili Cook-Off!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}



