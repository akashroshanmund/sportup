package com.akash.sportup.ui.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.akash.sportup.R
import com.akash.sportup.data.datasources.networksource.SportsDBApi
import com.akash.sportup.data.datasources.networksource.SportsDBClient
import com.akash.sportup.data.repositories.TeamSearchRepo
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val bottomNavigationView = findViewById<View>(R.id.navBar_Btm) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_teamSearch -> {
                    val teamSearchFragment: TeamSearchFragment = TeamSearchFragment()
                    openFragment(teamSearchFragment)
                }

                R.id.action_playerSearch -> {
                    val playerSearchFragment = PlayerSearchFragment()
                    openFragment((playerSearchFragment))
                }


            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.action_playerSearch


        }
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
    }


