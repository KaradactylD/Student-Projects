// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.adapters.StoreAdapter
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import com.kcrumptonslashertycoon.models.StoreViewModel
import com.kcrumptonslashertycoon.models.StoreViewModelFactory
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class StoreFragment : Fragment() {

    private lateinit var slasherViewModel: SlasherViewModel
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var adapter: StoreAdapter
    private val args: StoreFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slasherViewModel = ViewModelProvider(
            requireActivity(),
            SlasherViewModelFactory(requireActivity().application)
        )[SlasherViewModel::class.java]

        val moneyTextView = view.findViewById<TextView>(R.id.moneyTextView)
        val recyclerView = view.findViewById<RecyclerView>(R.id.storeRecyclerView)

        // Adapter setup
        adapter = StoreAdapter(emptyList()) { item ->
            lifecycleScope.launch {
                slasherViewModel.userInfo.value?.let { user ->
                    storeViewModel.buyItem(user, item)
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // Wait for actual user level before initializing StoreViewModel
        lifecycleScope.launch {
            slasherViewModel.getUserInfo(userId).collect { user ->
                user?.let {
                    val userLevel = it.level

                    storeViewModel = ViewModelProvider(
                        this@StoreFragment,
                        StoreViewModelFactory(requireContext(), args.roomName, userLevel)
                    )[StoreViewModel::class.java]

                    storeViewModel.insertStoreItemsIfEmpty()

                    // Items shown in the store depend on the room and user's level
                    viewLifecycleOwner.lifecycleScope.launch {
                        combine(
                            storeViewModel.storeItems,
                            slasherViewModel.userInfo
                        ) { items, userInfo ->
                            val lvl = userInfo?.level ?: 1
                            items.filter { it.levelRequired <= lvl }
                        }.collect { filteredItems ->
                            adapter.updateItems(filteredItems)
                        }
                    }

                    // Changing toasts to snackbar here to clean it up a little. Lotta stuff going on...
                    viewLifecycleOwner.lifecycleScope.launch {
                        storeViewModel.toastMessages.collect { message ->
                            view?.let { rootView ->
                                val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
                                val snackbarView = snackbar.view
                                val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                                textView.textSize = 24f
                                snackbar.show()
                            }
                        }
                    }

                    // Shows user's money
                    viewLifecycleOwner.lifecycleScope.launch {
                        slasherViewModel.userInfo.collect { userInfo ->
                            userInfo?.let {
                                moneyTextView.text = "Money: $${it.money}"
                            }
                        }
                    }
                }
            }
        }
    }
}