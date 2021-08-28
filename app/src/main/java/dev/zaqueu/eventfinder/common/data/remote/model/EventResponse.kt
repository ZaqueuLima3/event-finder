package dev.zaqueu.eventfinder.common.data.remote.model

data class EventResponse(
    val id: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Long,
    val latitude: Long,
    val price: Long,
    val title: Long
)
