// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import com.kcrumptonslashertycoon.adapters.InventoryAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.XpUtils
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import com.kcrumptonslashertycoon.models.StoreViewModel
import com.kcrumptonslashertycoon.models.StoreViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileFragment : Fragment() {

    private lateinit var slasherViewModel: SlasherViewModel
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var inventoryAdapter: InventoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slasherViewModel = ViewModelProvider(
            this,
            SlasherViewModelFactory(requireActivity().application)
        )[SlasherViewModel::class.java]

        val inventoryRecycler = view.findViewById<RecyclerView>(R.id.inventoryRecyclerView)
        inventoryAdapter = InventoryAdapter(emptyList())
        inventoryRecycler.layoutManager = LinearLayoutManager(requireContext())
        inventoryRecycler.adapter = inventoryAdapter

        val moneyText = view.findViewById<TextView>(R.id.moneyTextView)

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            Toast.makeText(requireContext(), "You're not logged in!", Toast.LENGTH_SHORT).show()
            return
        }


        viewLifecycleOwner.lifecycleScope.launch {
            slasherViewModel.getUserInfo(userId).collect { user ->
                user?.let {
                    val userLevel = it.level

                    storeViewModel = ViewModelProvider(
                        this@ProfileFragment,
                        StoreViewModelFactory(requireContext(), null, userLevel)
                    )[StoreViewModel::class.java]


                    launch {
                        storeViewModel.getInventoryForUser(userId).collect { items ->
                            val groupedItems = items
                                .groupBy { it.itemName }
                                .map { (name, itemList) ->
                                    val totalQuantity = itemList.sumOf { it.quantity }
                                    itemList.first().copy(quantity = totalQuantity)
                                }
                                .sortedBy { it.itemName }

                            inventoryAdapter.updateItems(groupedItems)
                        }
                    }
                }
            }
        }

        // Load user's display name - using cached version because it was super slow to update and it bothered me...
        val sharedPrefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val cachedUsername = sharedPrefs.getString("username", null)
        val usernameTextView = view.findViewById<TextView>(R.id.profileUsernameTextView)
        usernameTextView.text = "Welcome, ${cachedUsername ?: "..."}"

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val doc = FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(userId)
                    .get()
                    .await()

                val username = doc.getString("username") ?: "Unknown"
                usernameTextView.text = "Welcome, $username"
                sharedPrefs.edit().putString("username", username).apply()
            } catch (e: Exception) {

            }
        }

        // User's level and money
        val xpTextView = view.findViewById<TextView>(R.id.xpTextView)
        val xpProgressBar = view.findViewById<ProgressBar>(R.id.xpProgressBar)

        viewLifecycleOwner.lifecycleScope.launch {
            slasherViewModel.getUserInfo(userId).collect { user ->
                user?.let {
                    moneyText.text = "Money: $${it.money}"

                    val totalXp = it.xp
                    val level = it.level
                    val xpAtCurrentLevelStart = XpUtils.getTotalXpToReachLevel(level)
                    val xpForNextLevel = XpUtils.getXpForNextLevel(level)
                    val xpIntoCurrentLevel = totalXp - xpAtCurrentLevelStart

                    xpTextView.text = "XP: $xpIntoCurrentLevel / $xpForNextLevel (Level $level)"
                    xpProgressBar.max = xpForNextLevel
                    xpProgressBar.progress = xpIntoCurrentLevel
                }
            }
        }

        view.findViewById<Button>(R.id.buttonViewCollectibles).setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToCollectiblesFragment())
        }
    }
}