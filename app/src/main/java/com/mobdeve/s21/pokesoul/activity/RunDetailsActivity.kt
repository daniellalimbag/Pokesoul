package com.mobdeve.s21.pokesoul.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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

    private var run: Run? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.run_details)

        setSupportActionBar(findViewById(R.id.toolbar))

        run = intent.getSerializableExtra("RUN_INSTANCE") as? Run

        drawerLayout = findViewById(R.id.mainDl)
        navigationView = findViewById(R.id.navigationView)

        val backButton: ImageButton = findViewById(R.id.backIbtn)
        backButton.setOnClickListener {
            finish()
        }

        if (savedInstanceState == null) {
            val summaryFragment = SummaryFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("RUN_INSTANCE", run)
                }
            }
            supportFragmentManager.commit {
                replace(R.id.fragmentFl, summaryFragment)
            }
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun loadFragment(fragment: Fragment) {
        fragment.arguments = Bundle().apply {
            putSerializable("RUN_INSTANCE", run)
        }

        supportFragmentManager.commit {
            replace(R.id.fragmentFl, fragment)
            addToBackStack(null)
        }
        drawerLayout.closeDrawers()
    }
}
