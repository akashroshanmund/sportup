package com.akash.sportup.ui.viewmodels

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akash.sportup.data.datamodels.TeamSearchUiState
import com.akash.sportup.data.datasources.Results
import com.akash.sportup.data.datasources.networksource.SportsDBApi
import com.akash.sportup.data.datasources.networksource.SportsDBClient
import com.akash.sportup.data.repositories.TeamSearchRepo

class TeamSearchViewModel() : ViewModel() {
     private val teamSearchUiStateData : MutableLiveData<Results<TeamSearchUiState>>
     private val teamSearchRepo: TeamSearchRepo = TeamSearchRepo(SportsDBClient.createService(SportsDBApi::class.java))


    init {
        this.teamSearchUiStateData = this.teamSearchRepo.getTeamUiStateData()
     }

    suspend fun fetchTeamSearchResult(teamName : String): MutableLiveData<Results<TeamSearchUiState>> {
        this.teamSearchRepo.fetchTeamSearchResult(teamName)
        return this.teamSearchRepo.getTeamUiStateData()
    }

    fun getObservableData() : MutableLiveData<Results<TeamSearchUiState>>{
        return this.teamSearchUiStateData;
    }


}
