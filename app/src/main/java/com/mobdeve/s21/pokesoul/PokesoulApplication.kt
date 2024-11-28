package com.mobdeve.s21.pokesoul

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class PokesoulApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (FirebaseApp.initializeApp(this) != null) {
            Log.d("FirebaseInit", "FirebaseApp initialized in Application class")
        } else {
            Log.e("FirebaseInit", "FirebaseApp initialization failed")
        }
    }
}