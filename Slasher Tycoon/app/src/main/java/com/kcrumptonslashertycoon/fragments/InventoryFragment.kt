// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import com.kcrumptonslashertycoon.adapters.InventoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import com.kcrumptonslashertycoon.models.StoreViewModel
import com.kcrumptonslashertycoon.models.StoreViewModelFactory
import kotlinx.coroutines.launch

class InventoryFragment : Fragment() {

    private lateinit var slasherViewModel: SlasherViewModel
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var adapter: InventoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inventory, container, false)
    }

    // Checks to see if they're signed in
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slasherViewModel = ViewModelProvider(
            this,
            SlasherViewModelFactory(requireActivity().application)
        )[SlasherViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.inventoryRecyclerView)
        adapter = InventoryAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) {
            Toast.makeText(requireContext(), "User not signed in!", Toast.LENGTH_SHORT).show()
            return
        }

        // Get's their inventory
        lifecycleScope.launch {
            slasherViewModel.getUserInfo(userId).collect { userInfo ->
                userInfo?.let {
                    val userLevel = it.level

                    storeViewModel = ViewModelProvider(
                        this@InventoryFragment,
                        StoreViewModelFactory(requireContext(), null, userLevel)
                    )[StoreViewModel::class.java]

                    storeViewModel.getInventoryForUser(userId).collect { items ->
                        adapter.updateItems(items)
                    }
                }
            }
        }
    }
}