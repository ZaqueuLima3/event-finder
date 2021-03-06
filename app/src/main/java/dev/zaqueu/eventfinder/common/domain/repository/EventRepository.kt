package dev.zaqueu.eventfinder.common.domain.repository

import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import dev.zaqueu.eventfinder.common.domain.model.Event

interface EventRepository {
    suspend fun getEvents(): List<Event>
    suspend fun checkInEvent(checkIn: CheckIn)
}