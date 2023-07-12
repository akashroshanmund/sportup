package com.akash.sportup.ui.views

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.akash.sportup.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var adBanner : AdView
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)



        //To hide the title bar in the activity
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
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
        MobileAds.initialize(this) {}

        adBanner = findViewById(R.id.adBanner)
        var token : AdRequest = AdRequest.Builder().build()
        adBanner.loadAd(token)

        }
    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        finish()
    }
    }


