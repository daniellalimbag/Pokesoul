package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.MyDatabaseHelper
import com.mobdeve.s21.pokesoul.fragment.RunFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is for testing if the Database Connects properly
        val dbHelper = MyDatabaseHelper(this)
        val db = dbHelper.writableDatabase // or dbHelper.readableDatabase


        // Load RunFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RunFragment())
                .commit()
        }
    }
}
