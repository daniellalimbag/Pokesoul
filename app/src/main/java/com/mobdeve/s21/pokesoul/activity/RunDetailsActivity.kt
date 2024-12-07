package com.mobdeve.s21.pokesoul.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.database.DatabaseManager
import com.mobdeve.s21.pokesoul.fragment.SummaryFragment
import com.mobdeve.s21.pokesoul.fragment.TimelineFragment
import com.mobdeve.s21.pokesoul.fragment.PokemonFragment
import com.mobdeve.s21.pokesoul.model.Run

class RunDetailsActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var dbManager: DatabaseManager

    private var run: Run? = null

    // Result launcher for editing a run
    private val editRunResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val editedRun = result.data?.getSerializableExtra("edited_run") as? Run
            editedRun?.let { updatedRun ->
                // Update the local run instance
                run = updatedRun

                // Update all current fragments
                updateAllFragments(updatedRun)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.run_details)

        // Initialize DatabaseManager
        dbManager = DatabaseManager(this)
        setSupportActionBar(findViewById(R.id.toolbar))

        // Retrieve Run instance from Intent
        run = intent.getSerializableExtra("RUN_INSTANCE") as? Run

        // Initialize drawer and navigation components
        drawerLayout = findViewById(R.id.mainDl)
        navigationView = findViewById(R.id.navigationView)

        val backButton: ImageButton = findViewById(R.id.backIbtn)
        backButton.setOnClickListener {
            finish()
        }

        val menuButton: ImageButton = findViewById(R.id.menuIbtn)
        menuButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_summary -> {
                    loadFragment(SummaryFragment())
                    true
                }
                R.id.nav_pokemon -> {
                    loadFragment(PokemonFragment())
                    true
                }
                R.id.nav_timeline -> {
                    loadFragment(TimelineFragment())
                    true
                }
                else -> false
            }
        }

        // Load initial fragment
        if (savedInstanceState == null && run != null) {
            val summaryFragment = SummaryFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("RUN_INSTANCE", run)
                }
            }
            supportFragmentManager.commit {
                replace(R.id.fragmentFl, summaryFragment)
            }
        }
    }

    // Launch EditRunActivity with the result launcher
    fun launchEditRunActivity() {
        val intent = Intent(this, EditRunActivity::class.java)
        intent.putExtra("RUN_INSTANCE", run)
        editRunResultLauncher.launch(intent)
    }

    // Update all fragments with the new run data
    private fun updateAllFragments(updatedRun: Run) {
        // Get the current fragments in the container
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentFl)

        // Reload the current fragment with updated run details
        currentFragment?.let {
            loadFragment(it)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Create a new bundle with the updated run
        val bundle = Bundle().apply {
            putSerializable("RUN_INSTANCE", run)
        }

        // If the fragment supports updating, call a method to update its data
        if (fragment is RunDataUpdateListener) {
            fragment.updateRunData(run)
        } else {
            // For standard fragment loading
            fragment.arguments = bundle
        }

        // Replace the fragment
        supportFragmentManager.commit {
            replace(R.id.fragmentFl, fragment)
            addToBackStack(null)
        }
        drawerLayout.closeDrawers()
    }

    // Interface for fragments that can be updated directly
    interface RunDataUpdateListener {
        fun updateRunData(run: Run?)
    }
}