package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.mobdeve.s21.pokesoul.R
import com.mobdeve.s21.pokesoul.fragment.PokemonFragment
import com.mobdeve.s21.pokesoul.fragment.SummaryFragment
import com.mobdeve.s21.pokesoul.fragment.TimelineFragment
import com.mobdeve.s21.pokesoul.model.Run

class RunDetailsActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private var run: Run? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.run_details)

        // Retrieve the Run instance from the intent
        run = intent.getSerializableExtra("RUN_INSTANCE") as? Run

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        // Set the default fragment
        if (savedInstanceState == null) {
            val summaryFragment = SummaryFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("RUN_INSTANCE", run)
                }
            }
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, summaryFragment)
            }
        }

        // Set up the ActionBarDrawerToggle
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Set navigation view item selected listener
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment) {
        // Pass the Run instance to the fragment
        fragment.arguments = Bundle().apply {
            putSerializable("RUN_INSTANCE", run)
        }

        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null) // Optional: adds to back stack for navigation
        }
        drawerLayout.closeDrawers() // Close the drawer after selection
    }
}
