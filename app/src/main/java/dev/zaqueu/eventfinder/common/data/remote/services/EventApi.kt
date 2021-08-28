package dev.zaqueu.eventfinder.common.data.remote.services

import dev.zaqueu.eventfinder.common.data.remote.model.EventResponse
import retrofit2.http.GET

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<EventResponse>
}