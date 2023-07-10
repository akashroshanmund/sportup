package com.akash.sportup.data.datamodels
import com.google.gson.annotations.SerializedName


data class EventsApiModel (
    @SerializedName("results" ) var eventList : ArrayList<Events> = arrayListOf()
)
data class Events (

    @SerializedName("idEvent"           ) var idEvent           : String? = null,
    @SerializedName("idSoccerXML"       ) var idSoccerXML       : String? = null,
    @SerializedName("idAPIfootball"     ) var idAPIfootball     : String? = null,
    @SerializedName("strEvent"          ) var strEvent          : String? = null,
    @SerializedName("strEventAlternate" ) var strEventAlternate : String? = null,
    @SerializedName("strFilename"       ) var strFilename       : String? = null,
    @SerializedName("strSport"          ) var strSport          : String? = null,
    @SerializedName("idLeague"          ) var idLeague          : String? = null,
    @SerializedName("strLeague"         ) var strLeague         : String? = null,
    @SerializedName("strSeason"         ) var strSeason         : String? = null,
    @SerializedName("strDescriptionEN"  ) var strDescriptionEN  : String? = null,
    @SerializedName("strHomeTeam"       ) var strHomeTeam       : String? = null,
    @SerializedName("strAwayTeam"       ) var strAwayTeam       : String? = null,
    @SerializedName("intHomeScore"      ) var intHomeScore      : String? = null,
    @SerializedName("intRound"          ) var intRound          : String? = null,
    @SerializedName("intAwayScore"      ) var intAwayScore      : String? = null,
    @SerializedName("intSpectators"     ) var intSpectators     : String? = null,
    @SerializedName("strOfficial"       ) var strOfficial       : String? = null,
    @SerializedName("strTimestamp"      ) var strTimestamp      : String? = null,
    @SerializedName("dateEvent"         ) var dateEvent         : String? = null,
    @SerializedName("dateEventLocal"    ) var dateEventLocal    : String? = null,
    @SerializedName("strTime"           ) var strTime           : String? = null,
    @SerializedName("strTimeLocal"      ) var strTimeLocal      : String? = null,
    @SerializedName("strTVStation"      ) var strTVStation      : String? = null,
    @SerializedName("idHomeTeam"        ) var idHomeTeam        : String? = null,
    @SerializedName("idAwayTeam"        ) var idAwayTeam        : String? = null,
    @SerializedName("intScore"          ) var intScore          : String? = null,
    @SerializedName("intScoreVotes"     ) var intScoreVotes     : String? = null,
    @SerializedName("strResult"         ) var strResult         : String? = null,
    @SerializedName("strVenue"          ) var strVenue          : String? = null,
    @SerializedName("strCountry"        ) var strCountry        : String? = null,
    @SerializedName("strCity"           ) var strCity           : String? = null,
    @SerializedName("strPoster"         ) var strPoster         : String? = null,
    @SerializedName("strSquare"         ) var strSquare         : String? = null,
    @SerializedName("strFanart"         ) var strFanart         : String? = null,
    @SerializedName("strThumb"          ) var strThumb          : String? = null,
    @SerializedName("strBanner"         ) var strBanner         : String? = null,
    @SerializedName("strMap"            ) var strMap            : String? = null,
    @SerializedName("strTweet1"         ) var strTweet1         : String? = null,
    @SerializedName("strTweet2"         ) var strTweet2         : String? = null,
    @SerializedName("strTweet3"         ) var strTweet3         : String? = null,
    @SerializedName("strVideo"          ) var strVideo          : String? = null,
    @SerializedName("strStatus"         ) var strStatus         : String? = null,
    @SerializedName("strPostponed"      ) var strPostponed      : String? = null,
    @SerializedName("strLocked"         ) var strLocked         : String? = null

)