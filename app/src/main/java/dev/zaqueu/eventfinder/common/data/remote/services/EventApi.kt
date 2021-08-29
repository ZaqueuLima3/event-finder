package dev.zaqueu.eventfinder.common.data.remote.services

import dev.zaqueu.eventfinder.common.data.remote.model.EventResponse
import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {
    @GET("events")
    suspend fun getEvents(): List<EventResponse>

    @POST("checkin")
    suspend fun checkInEvent(@Body checkIn: CheckIn)
}
