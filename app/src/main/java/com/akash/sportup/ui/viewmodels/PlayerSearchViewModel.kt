package com.akash.sportup.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akash.sportup.data.datamodels.PlayerSearchUiState
import com.akash.sportup.data.datamodels.TeamSearchUiState

import com.akash.sportup.data.datasources.Results
import com.akash.sportup.data.datasources.networksource.SportsDBApi
import com.akash.sportup.data.datasources.networksource.SportsDBClient
import com.akash.sportup.data.repositories.PlayerSearchRepo
import com.akash.sportup.data.repositories.TeamSearchRepo


class PlayerSearchViewModel() : ViewModel() {
    private val playerSearchUiState : MutableLiveData<Results<PlayerSearchUiState>>
    private val playerSearchRepo: PlayerSearchRepo = PlayerSearchRepo(
        SportsDBClient.createService(SportsDBApi::class.java))

    init {
        this.playerSearchUiState = this.playerSearchRepo.getPlayerUiStateData()
    }

    suspend fun fetchPlayerSearchResult(playerName : String): MutableLiveData<Results<PlayerSearchUiState>> {
        this.playerSearchRepo.fetchPlayerSearchResult(playerName)
        return this.playerSearchRepo.getPlayerUiStateData()
    }

    fun getObservableData() : MutableLiveData<Results<PlayerSearchUiState>>{
        return this.playerSearchUiState;
    }
}