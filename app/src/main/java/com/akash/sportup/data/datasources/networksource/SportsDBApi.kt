package com.akash.sportup.data.datasources.networksource

import com.akash.sportup.data.datamodels.EventsApiModel
import com.akash.sportup.data.datamodels.PlayerApiModel
import com.akash.sportup.data.datamodels.PlayerMilestoneApiModel
import com.akash.sportup.data.datamodels.TeamApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsDBApi {
    @GET("searchteams.php?")
    fun getTeamsData(@Query("t") team : String) : Call<TeamApiModel>

    @GET("eventslast.php?")
    fun getLastEventsData(@Query("id") id : String?) : Call<EventsApiModel>

    @GET("eventsnext.php?")
    fun getNextEventsData(@Query("t") id : String?) : Call<EventsApiModel>

    @GET("searchplayers.php?")
    fun getPlayersData(@Query("p") p : String) : Call<PlayerApiModel>

    @GET("lookupmilestones.php?")
    fun getPlayersMilestonesData(@Query("id") id : String?) : Call<PlayerMilestoneApiModel>
}