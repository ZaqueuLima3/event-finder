package dev.zaqueu.eventfinder.common.domain.model

data class Event(
    val id: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String
)
