// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import com.kcrumptonslashertycoon.adapters.CollectibleAdapter
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import kotlinx.coroutines.launch

class CollectiblesFragment : Fragment() {

    private lateinit var slasherViewModel: SlasherViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CollectibleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_collectibles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slasherViewModel = ViewModelProvider(
            requireActivity(),
            SlasherViewModelFactory(requireActivity().application as Application)
        )[SlasherViewModel::class.java]

        recyclerView = view.findViewById(R.id.collectiblesRecyclerView)
        adapter = CollectibleAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // Changed it to group collectibles if there's more than one, so "Dream Vinyl x2" instead of each one individual.
        lifecycleScope.launch {
            slasherViewModel.getCollectiblesForUser(userId).collect { collectibles ->
                val grouped = collectibles
                    .groupBy { it.name }
                    .map { (name, items) ->
                        val base = items.maxByOrNull { it.collectedAt }!!
                        base.copy(collectedAt = base.collectedAt, name = "$name x${items.size}")
                    }

                adapter.submitList(grouped)
            }
        }
    }
}