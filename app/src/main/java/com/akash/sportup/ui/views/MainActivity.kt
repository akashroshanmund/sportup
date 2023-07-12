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
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)

        /** Integrate google analytics */
        var firebaseAnalytics  = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"SportUp Interaction Log")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)

        val bottomNavigationView = findViewById<View>(R.id.navBar_Btm) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_teamSearch -> {
                    val teamSearchFragment: TeamSearchFragment = TeamSearchFragment()
                    openFragment(teamSearchFragment)
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)

                }

                R.id.action_playerSearch -> {
                    val playerSearchFragment = PlayerSearchFragment()
                    openFragment((playerSearchFragment))
                    firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,bundle)
                }


            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.action_playerSearch

        /** Add ad to the main activity*/
        MobileAds.initialize(this) {}
        var adBanner : AdView = findViewById(R.id.adBanner)
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


