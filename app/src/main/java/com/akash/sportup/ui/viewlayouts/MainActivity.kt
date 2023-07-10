package com.akash.sportup.ui.viewlayouts

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.akash.sportup.R
import com.akash.sportup.data.datasources.networksource.SportsDBApi
import com.akash.sportup.data.datasources.networksource.SportsDBClient
import com.akash.sportup.data.repositories.TeamSearchRepo
import com.akash.sportup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val client = SportsDBClient.createService(SportsDBApi::class.java)

        val teamsearch = TeamSearchRepo(client)
        Log.i("ApiTest", "Process start")
        teamsearch.fetchTeamSearchResult("chelsea")
        Log.i("ApiTest", "Process Complete")

        }
    }


