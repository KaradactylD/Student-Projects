// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon.models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kcrumptonslashertycoon.repository.SlasherRepository

class StoreViewModelFactory(
    private val context: Context,
    private val roomName: String? = null,
    private val userLevel: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            val repository = SlasherRepository.getInstance(context)
            return StoreViewModel(repository, roomName.toString(), userLevel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}