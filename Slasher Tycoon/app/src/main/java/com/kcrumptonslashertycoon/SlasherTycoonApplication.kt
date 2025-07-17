// Kara Crumpton - CPT 188 Final Project
// "Slasher Tycoon"

package com.kcrumptonslashertycoon

import android.app.Application
import com.google.firebase.FirebaseApp

// Had to put this in for Firebase
class SlasherTycoonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}