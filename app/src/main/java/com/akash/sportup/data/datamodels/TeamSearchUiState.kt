package com.akash.sportup.data.datamodels

data class TeamSearchUiState(
    var teamDetails: TeamApiModel?,
    var lastEvents:  EventsApiModel?,
    var nextEvents:  EventsApiModel?
)
