package dev.zaqueu.eventfinder.common.domain.model

data class Event(
    val id: String,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Long,
    val latitude: Long,
    val price: Long,
    val title: Long
)
