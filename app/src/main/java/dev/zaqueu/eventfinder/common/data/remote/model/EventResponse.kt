package dev.zaqueu.eventfinder.common.data.remote.model

data class EventResponse(
    val id: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String
)
