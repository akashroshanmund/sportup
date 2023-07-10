package com.akash.sportup.data.datamodels
import com.google.gson.annotations.SerializedName

data class PlayerMilestoneApiModel (

    @SerializedName("milestones" ) var milestonesList : ArrayList<Milestones> = arrayListOf()

)

data class Milestones (

    @SerializedName("idMilestone"      ) var idMilestone      : String? = null,
    @SerializedName("idPlayer"         ) var idPlayer         : String? = null,
    @SerializedName("strPlayer"        ) var strPlayer        : String? = null,
    @SerializedName("idTeam"           ) var idTeam           : String? = null,
    @SerializedName("strTeam"          ) var strTeam          : String? = null,
    @SerializedName("strSport"         ) var strSport         : String? = null,
    @SerializedName("strMilestone"     ) var strMilestone     : String? = null,
    @SerializedName("strMilestoneLogo" ) var strMilestoneLogo : String? = null,
    @SerializedName("dateMilestone"    ) var dateMilestone    : String? = null

)