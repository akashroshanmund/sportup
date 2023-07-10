package com.akash.sportup.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.akash.sportup.data.datamodels.EventsApiModel
import com.akash.sportup.data.datamodels.TeamApiModel
import com.akash.sportup.data.datamodels.TeamSearchUiState
import com.akash.sportup.data.datasources.Results
import com.akash.sportup.data.datasources.networksource.SportsDBApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamSearchRepo(_SportsDBClient: SportsDBApi) {
    private val sportsDBClient : SportsDBApi
    private var teamsDataResponse: Results<TeamApiModel> ? = null
    private var lastEventsDataResponse: Results<EventsApiModel>? = null
    private var nextEventsDataResponse: Results<EventsApiModel>? = null
    private val teamSearchUIStateResult : MutableLiveData<Results<TeamSearchUiState>> = MutableLiveData<Results<TeamSearchUiState>>()


    init {
        this.sportsDBClient = _SportsDBClient
    }

     fun fetchTeamSearchResult(name : String){
        fetchTeamDetails(name);
    }

    private fun fetchTeamDetails(name : String){
        Log.i("ApiTest", "FetchTeamCalled")
        teamsDataResponse = Results.Loading()
        val call: Call<TeamApiModel> = sportsDBClient.getTeamsData(name)
        call.enqueue(object : Callback<TeamApiModel?> {
            override fun onResponse(call: Call<TeamApiModel?>, response: Response<TeamApiModel?>) {
                if (response.isSuccessful) {
                    Log.i("ApiTest", "TeamFetch Success")
                    teamsDataResponse = Results.Success(response.body())
                    val teamId = response.body()?.teamsList?.get(0)?.idTeam
                    Log.i("ApiTest", "TeamId ${response.body()?.teamsList?.get(0)?.idTeam}")
                    fetchLastEvents(teamId)
                    fetchNextEvents(teamId)

                } else {
                    Log.i("ApiTest", "Team Fetch Failed")
                    teamsDataResponse = Results.Error(response.message())
                }
            }

            override fun onFailure(call: Call<TeamApiModel?>, t: Throwable) {
                teamsDataResponse = Results.Error(t.message)
            }
        })
    }

    private fun fetchLastEvents(id : String?){
        lastEventsDataResponse = Results.Loading()
        Log.i("ApiTest", "last event called")
        val call: Call<EventsApiModel> = sportsDBClient.getLastEventsData(id)
        call.enqueue(object : Callback<EventsApiModel?> {
            override fun onResponse(call: Call<EventsApiModel?>, response: Response<EventsApiModel?>) {
                Log.i("ApiTest", "last event Success"+response.message()+" j")
                lastEventsDataResponse = if (response.isSuccessful) {
                    Results.Success(response.body())
                } else {
                    Results.Error(response.message())
                }
                loadTeamSearchUiState()
            }

            override fun onFailure(call: Call<EventsApiModel?>, t: Throwable) {
                Log.i("ApiTest", "last event failed")
                lastEventsDataResponse = Results.Error(t.message)
            }
        })
    }

    private fun fetchNextEvents(id : String?){
        Log.i("ApiTest", "next event called")
        nextEventsDataResponse = Results.Loading()
        val call: Call<EventsApiModel> = sportsDBClient.getNextEventsData(id)
        call.enqueue(object : Callback<EventsApiModel?> {
            override fun onResponse(call: Call<EventsApiModel?>, response: Response<EventsApiModel?>) {
                Log.i("ApiTest", "next event Success")
                nextEventsDataResponse = if (response.isSuccessful) {
                    Results.Success(response.body())
                } else {
                    Results.Error(response.message())
                }
                loadTeamSearchUiState()
            }
            override fun onFailure(call: Call<EventsApiModel?>, t: Throwable) {
                Log.i("ApiTest", "next Event Failed")
                nextEventsDataResponse = Results.Error(t.message)
            }
        })
    }


    private fun loadTeamSearchUiState(){
        if((lastEventsDataResponse?.hasResult == true && nextEventsDataResponse?.hasResult == true)){
            teamSearchUIStateResult.value = Results.Success(TeamSearchUiState(teamsDataResponse?.data, lastEventsDataResponse?.data, nextEventsDataResponse?.data))
        }
    }



}
