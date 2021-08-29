package dev.zaqueu.eventfinder.common.data.remote.repository

import dev.zaqueu.eventfinder.common.data.remote.mapper.mapToModel
import dev.zaqueu.eventfinder.common.data.remote.services.EventApi
import dev.zaqueu.eventfinder.common.domain.model.CheckIn
import dev.zaqueu.eventfinder.common.domain.model.Event
import dev.zaqueu.eventfinder.common.domain.repository.EventRepository

class ApiEventRepository(
    private val eventApi: EventApi
) : EventRepository {
    override suspend fun getEvents(): List<Event> {
        return eventApi.getEvents().map { it.mapToModel() }
    }

    override suspend fun checkInEvent(checkIn: CheckIn) {
        eventApi.checkInEvent(
            eventId = checkIn.eventId,
            name = checkIn.name,
            email = checkIn.email
        )
    }
}
