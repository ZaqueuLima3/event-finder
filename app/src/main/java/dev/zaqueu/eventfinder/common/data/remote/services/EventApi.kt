package dev.zaqueu.eventfinder.common.data.remote.services

import dev.zaqueu.eventfinder.common.data.remote.model.EventResponse
import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<EventResponse>

    @POST
    suspend fun checkInEvent(
        @Field("eventId") eventId: String,
        @Field("name") name: String,
        @Field("email") email: String,
    )
}