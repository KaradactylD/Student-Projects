// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kcrumptonslashertycoon.R
import com.kcrumptonslashertycoon.models.SlasherViewModel
import com.kcrumptonslashertycoon.models.SlasherViewModelFactory
import kotlinx.coroutines.launch

class ExtraCashFragment : Fragment() {

    private lateinit var viewModel: SlasherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_extra_cash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            SlasherViewModelFactory(requireActivity().application)
        )[SlasherViewModel::class.java]

        // User's money shows up top
        val moneyTextView = view.findViewById<TextView>(R.id.textMoneyDisplay)

        lifecycleScope.launch {
            viewModel.userInfo.collect { user ->
                if (user != null) {
                    moneyTextView.text = "💰 $${user.money}"
                }
            }
        }

        // Buttons to go to the Chili Cookoff or the Rave
        view.findViewById<Button>(R.id.buttonChiliCookoff).setOnClickListener {
            val action = ExtraCashFragmentDirections.actionExtraCashFragmentToChiliCookoffFragment()
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.buttonZombieRave).setOnClickListener {
            findNavController().navigate(R.id.action_extraCashFragment_to_ravePhotographerFragment)
        }
    }
}
