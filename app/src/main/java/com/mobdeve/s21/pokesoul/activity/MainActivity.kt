package com.mobdeve.s21.pokesoul.activity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.s21.pokesoul.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navbar: BottomNavigationView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this) // Ensure Firebase is initialized
        Log.d("FirebaseInit", "FirebaseApp initialized successfully")

        db = Firebase.firestore // Initialize Firestore after Firebase is ready

        FirebaseApp.initializeApp(this)?.let {
            Log.d("FirebaseInit", "FirebaseApp is initialized")
            db = FirebaseFirestore.getInstance()
            db.collection("Notification")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("Firestore", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("Firestore", "Error getting documents.", exception)
                }
        } ?: run {
            Log.e("FirebaseInit", "FirebaseApp is not initialized")
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navbar = findViewById(R.id.navbarBnv)

        // Automatically connect BottomNavigationView with NavController
        navbar.setupWithNavController(navController)
    }
}
