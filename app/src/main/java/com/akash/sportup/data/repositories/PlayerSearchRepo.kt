package com.akash.sportup.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.akash.sportup.data.datamodels.EventsApiModel
import com.akash.sportup.data.datamodels.PlayerApiModel
import com.akash.sportup.data.datamodels.PlayerMilestoneApiModel
import com.akash.sportup.data.datamodels.PlayerSearchUiState
import com.akash.sportup.data.datamodels.TeamApiModel
import com.akash.sportup.data.datamodels.TeamSearchUiState
import com.akash.sportup.data.datasources.Results
import com.akash.sportup.data.datasources.networksource.SportsDBApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerSearchRepo(_SportsDBClient: SportsDBApi) {

    private val sportsDBClient : SportsDBApi
    private var playerSearchDataResponse: Results<PlayerApiModel>? = null
    private var playerMilestoneDataResponse: Results<PlayerMilestoneApiModel>? = null
    private val playerSearchUIStateResult : MutableLiveData<Results<PlayerSearchUiState>> = MutableLiveData<Results<PlayerSearchUiState>>()


    init {
        this.sportsDBClient = _SportsDBClient
    }

    /** Fetch the player result on request*/
    suspend fun fetchPlayerSearchResult(name : String){
        fetchPlayerDetails(name);
    }

    private fun fetchPlayerDetails(name : String){
        Log.i("ApiTestPlayer", "FetchTeamCalled")
        playerSearchDataResponse = Results.Loading()
        val call: Call<PlayerApiModel> = sportsDBClient.getPlayersData(name)
        call.enqueue(object : Callback<PlayerApiModel?> {
            override fun onResponse(call: Call<PlayerApiModel?>, response: Response<PlayerApiModel?>) {
                if (response.isSuccessful) {
                    Log.i("ApiTestPlayer", "TeamFetch Success")
                    playerSearchDataResponse = Results.Success(response.body())
                    val playerId = response.body()?.playersList?.get(0)?.idPlayer
                    Log.i("ApiTestPlayer", "TeamId ${response.body()?.playersList?.get(0)?.idPlayer}")
                    fetchPlayerMilesotens(playerId)


                } else {
                    Log.i("ApiTestPlayer", "Team Fetch Failed")
                    playerSearchDataResponse = Results.Error(response.message())
                }
            }

            override fun onFailure(call: Call<PlayerApiModel?>, t: Throwable) {
                playerSearchDataResponse = Results.Error(t.message)
                playerMilestoneDataResponse = Results.Error(t.message)
                playerSearchUIStateResult.value = Results.Success(PlayerSearchUiState(playerSearchDataResponse?.data, playerMilestoneDataResponse?.data))
            }
        })
    }


    private fun fetchPlayerMilesotens(id : String?){
        playerMilestoneDataResponse = Results.Loading()
        Log.i("ApiTestPlayer", "last event called")
        val call: Call<PlayerMilestoneApiModel> = sportsDBClient.getPlayersMilestonesData(id)
        call.enqueue(object : Callback<PlayerMilestoneApiModel?> {
            override fun onResponse(call: Call<PlayerMilestoneApiModel?>, response: Response<PlayerMilestoneApiModel?>) {
                Log.i("ApiTestPlayer", "last event Success"+response.message()+" j")
                playerMilestoneDataResponse = if (response.isSuccessful) {
                    Results.Success(response.body())
                } else {
                    Results.Error(response.message())
                }
                playerSearchUIStateResult.value = Results.Success(PlayerSearchUiState(playerSearchDataResponse?.data, playerMilestoneDataResponse?.data))
            }

            override fun onFailure(call: Call<PlayerMilestoneApiModel?>, t: Throwable) {
                Log.i("ApiTestPlayer", "last event failed")
                playerMilestoneDataResponse = Results.Error(t.message)
                playerSearchUIStateResult.value = Results.Success(PlayerSearchUiState(playerSearchDataResponse?.data, playerMilestoneDataResponse?.data))

            }
        })
    }
    public fun getPlayerUiStateData() : MutableLiveData<Results<PlayerSearchUiState>>{
        return this.playerSearchUIStateResult
    }
}